package com.igr.media.service;

import com.igr.media.dto.GreatPostDto;
import com.igr.media.dto.PostDto;
import com.igr.media.entity.Post;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

/**
 * Сервис объявлений
 */
public interface PostService {
    Collection<PostDto> getAllPosts();

    /**
     * Возвращает объявление
     *
     * @param id - идентификатор объявления
     * @return - комментарий
     */
    PostDto getPostById(int id, Authentication authentication);

    /**
     * Обновляет объявление
     *
     * @param id - идентификатор объявления
     * @return - обнволенный комментарий
     */
    PostDto updatePost(int id, PostDto postDto, Authentication authentication);
    /**
     * Добавляем новое объявление
     *
     * @return возвращает созданное объявление
     */
    PostDto addPost(GreatPostDto greatPostDto, MultipartFile multipartFile, Authentication authentication)
            throws IOException;
    /**
     * Удаление сообщениe по id
     *
     * @param id
     */
    void removePost(int id, Authentication authentication);
    /**
     * Добавление фото в объявление
     *
     * @param id
     * @param image
     */
    void uploadImage(Integer id, MultipartFile image) throws IOException;

    Collection<PostDto> getPostMe(Authentication authentication);

    /**
     * получить аватарку объявления
     * @param id объявления
     * @return байтовое представление картинки
     */
    byte[] getPhotoById(Integer id);

}
