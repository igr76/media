package com.igr.media.service.impl;

import com.igr.media.dto.GreatPostDto;
import com.igr.media.dto.ImageDTO;
import com.igr.media.dto.PostDto;
import com.igr.media.dto.UserDto;
import com.igr.media.entity.ImageEntity;
import com.igr.media.entity.Post;
import com.igr.media.entity.UserEntity;
import com.igr.media.exception.ElemNotFound;
import com.igr.media.exception.SecurityAccessException;
import com.igr.media.loger.FormLogInfo;
import com.igr.media.mapper.ImageMapper;
import com.igr.media.mapper.PostMapper;
import com.igr.media.mapper.UserMapper;
import com.igr.media.repository.ImageRepository;
import com.igr.media.repository.PostRepository;
import com.igr.media.repository.UserRepository;
import com.igr.media.service.PostService;
import com.igr.media.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Slf4j
@Transactional
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private PostMapper postMapper;
    private UserService userService;
    private ImageMapper imageMapper;
    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private UserMapper userMapper;
    private SecurityService securityService;
    @Value("${image.ads.dir.path}")
    private String imagePostDir;


    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, UserService userService, ImageMapper imageAdsDir, UserRepository userRepository, UserMapper userMapper, SecurityService securityService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userService = userService;
        this.imageMapper = imageMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.securityService = securityService;
        this.imageRepository = imageRepository;
    }



    public Collection<PostDto> getAllPosts(Authentication authentication) {
        log.info(FormLogInfo.getInfo());
        changeDataTime(authentication);
        Collection<Post> postCollection = postRepository.findAll();
        return postMapper.toDTOList(postCollection);
    }

    /**
     * Метод, который создает новое сообщениe с картинкой
     *
     * @param greatPostDto      неполная инфа про сообщения
     * @param multipartFile  само изображение
     * @param authentication инфа про юзера
     * @return дто объявления
     * @throws IOException
     */
    @Override
    public PostDto addPost(GreatPostDto greatPostDto, MultipartFile multipartFile, Authentication authentication) throws IOException {
        log.info(FormLogInfo.getInfo());

        if (greatPostDto == null || multipartFile == null) {
            log.error(FormLogInfo.getException());
            throw new IllegalArgumentException();
        }

        UserDto userDTO = userService.getUser(authentication);
        int count = postRepository.findMaxID();
        int countReal = count + 1;
        Path filePath = getPath(imagePostDir, countReal);

        String linkToGetImage = getLinkToGetImage(countReal);

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = multipartFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        PostDto postDto= new PostDto();
        postDto.setTitle(greatPostDto.getTitle());
        postDto.setContent(greatPostDto.getContent());
        postDto.setData(greatPostDto.getData());
        postDto.setImage(linkToGetImage);

        Post post = postMapper.toEntity(postDto);
        post.setAuthorId(userDTO.getId());
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImage(linkToGetImage);
        ImageEntity imageEntity = imageMapper.toEntity(imageDTO);
        imageEntity.setPost(post);
        imageEntity.setPath(linkToGetImage);
        imageRepository.save(imageEntity);
        post.setImageEntities(List.of(imageEntity));
        postRepository.save(post);
        post.setData(LocalDateTime.now());
        return postDto;

    }
    /**
     * Добавление фото в сообщениe
     *
     */
    @Override
    public void uploadImage(Integer id, MultipartFile image) throws IOException {
        log.info(FormLogInfo.getInfo());
        Post post = postRepository.findById(id).orElseThrow(ElemNotFound::new);

        Path filePath = getPath(imagePostDir, post.getId());

        if(!post.getImageEntities().isEmpty()){
            int idAds = post.getImageEntities().get(0).getId();
            imageRepository
                    .deleteById(idAds);
            post.getImageEntities().clear();
            Files.deleteIfExists(filePath);
        }

        String linkToGetImage = getLinkToGetImage(post.getId());

        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setPost(post);
        imageEntity.setPath(linkToGetImage);
        post.setImageEntities(List.of(imageEntity));

        imageRepository.save(imageEntity);
        post.setData(LocalDateTime.now());
    }

    /**
     * Получаем только свои сообщениe
     *
     * @param authentication данные о пользователе
     * @return общий подсчет своих объявлений + объявления
     */
    @Override
    public Collection<PostDto> getPostMe(Authentication authentication) {
        log.info(FormLogInfo.getInfo());
        UserDto userDTO = userService.getUser(authentication);
        Collection<PostDto> postAll = postMapper.toDTOList(postRepository.findAll());
        Collection<PostDto> postMe = postAll.stream().
                filter(x -> x.getAuthorId().equals(userDTO.getId())).collect(Collectors.toList());

        return postMe;
    }

    /**
     * получить аватарку сообщениe
     *
     * @param id объявления
     * @return байтовое представление картинки
     */
    public byte[] getPhotoById(Integer id) {
        String linkToGetImage = "ads_photo_dir/" + id;
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(linkToGetImage));
        } catch (IOException e) {
            log.info(FormLogInfo.getCatch());
            throw new RuntimeException(e);
        }
        return bytes;
    }
    /**
     * вспомогательный медот для загрузки фотографий
     *
     * @return расширение файла
     */
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Удаление сообщениe по id
     *
     * @param id
     */
    @Override
    public void removePost(int id, Authentication authentication) {
        log.info(FormLogInfo.getInfo());
        Post post = postRepository.findById(id).orElseThrow(ElemNotFound::new);
        UserEntity user = userMapper.toEntity(userService.getUser(authentication));
        if (securityService.isAdmin(authentication)) {
            postRepository.delete(post);
        } else if (Objects.equals(post.getAuthorId(), user)) {
            postRepository.delete(post);
        } else throw new SecurityAccessException();

    }

    /**
     * Получить сообщениe по id
     *
     * @param id
     */
    @Override
    public PostDto getPostById(int id, Authentication authentication) {
        return postMapper.toDTO(postRepository.findById(id).orElseThrow(ElemNotFound::new));
    }

    /**
     * Обновить сообщениe по id
     *
     * @param id
     */
    @Override
    public PostDto updatePost(int id, PostDto postDto, Authentication authentication) {
        Post post = postRepository.findById(id).orElseThrow(ElemNotFound::new);

        if (!securityService.isAdsUpdateAvailable(authentication, post.getAuthorId())) {
            throw new SecurityAccessException();
        }

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setData(postDto.getData());
        UserEntity user = userRepository.findByEmail(authentication.getName()).orElseThrow(ElemNotFound::new);
        post.setAuthorId(user.getId());
        return postMapper.toDTO(postRepository.save(post));
    }
    private Path getPath(String nameDir, Integer id) {
        return Path.of(nameDir,
                Objects.requireNonNull(String.valueOf(id)));
    }
    /**
     * Обновить дату последнего прочтения сообщениe
     */
    private void changeDataTime(Authentication authentication) {
        UserEntity user = userRepository.findByName(authentication.getName()).orElseThrow(ElemNotFound::new);
        user.setData(LocalDateTime.now());
    }
    /**
     * Получить  новыe  сообщения
     */
    public Collection<PostDto> getAllPostsNew(Authentication authentication) {
        log.info(FormLogInfo.getInfo());
        UserEntity user = userRepository.findByName(authentication.getName()).orElseThrow(ElemNotFound::new);
        Collection<Post> postCollection = postRepository.findAllNew(user.getData());
        return postMapper.toDTOList(postCollection);
    }
    /**
     * Получить список новых  сообщений по подпискам
     */
    public Collection<PostDto> getAllPostsNewSubscriptions(Authentication authentication) {
        log.info(FormLogInfo.getInfo());
        UserEntity user = userRepository.findByName(authentication.getName()).orElseThrow(ElemNotFound::new);
        return postMapper.toDTOList(postRepository.getAllPostsNewSubscriptions(user.getData()));
    }


    private String getLinkToGetImage(Integer id) {
        return "/post" + "/" + id;
    }

}
