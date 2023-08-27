package com.igr.media.controller;

import com.igr.media.dto.ImageDTO;
import com.igr.media.entity.ImageEntity;
import com.igr.media.entity.Post;
import com.igr.media.entity.UserEntity;
import com.igr.media.exception.ElemNotFound;
import com.igr.media.mapper.ImageMapper;
import com.igr.media.mapper.PostMapper;
import com.igr.media.mapper.UserMapper;
import com.igr.media.repository.ImageRepository;
import com.igr.media.repository.PostRepository;
import com.igr.media.repository.UserRepository;
import com.igr.media.service.UserService;
import com.igr.media.service.impl.PostServiceImpl;
import com.igr.media.service.impl.SecurityService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PostController.class)
class PostControllerTest {

  private final Integer ONE = 1;
  @Autowired
  private MockMvc mockMvc;
  @SpyBean
  private PostMapper postMapper;
  @MockBean
  private UserRepository userRepository;
  @MockBean
  private PostRepository postRepository;
  @MockBean
  private ImageMapper imageMapper;
  @MockBean
  private UserService userService;
  @MockBean
  private SecurityService securityService;
  @MockBean
  private UserMapper userMapper;
  @MockBean
  private ImageRepository imageRepository;
  @SpyBean
  private PostServiceImpl postService;
  @InjectMocks
  private PostController postController;
  private UserEntity userEntity;
  private ImageEntity imageEntity;
  private MockMultipartFile image;
  private MockMultipartFile properties;
  private MockMultipartFile propertiesNonValid;
  private MockMultipartFile authentication;
  private JSONObject propertiesNonValidJS;
  private JSONObject propertiesJS;
  private JSONObject authenticationJS;
  private String content;
  private String title;
  private String email = "email.ru";
  private String time;

  @BeforeEach
  void init() {
    time = "23-02-2022 08:09:10";

    image = new MockMultipartFile(
        "image",
        "image.jpeg",
        MediaType.MULTIPART_FORM_DATA_VALUE,
        "image.jpeg".getBytes()
    );
    properties = new MockMultipartFile(
        "properties",
        "properties.json",
        MediaType.APPLICATION_JSON_VALUE,
        propertiesJS.toString().getBytes()
    );
    authentication = new MockMultipartFile(
        "authentication",
        "authentication.json",
        MediaType.APPLICATION_JSON_VALUE,
        authenticationJS.toString().getBytes()
    );
    propertiesNonValid = new MockMultipartFile(
        "properties",
        "propertiesNonValidJS.json",
        MediaType.APPLICATION_JSON_VALUE,
        propertiesNonValidJS.toString().getBytes()
    );
  }

  @AfterEach
  void clearAllTestData() {
    content = null;
    title = null;
    email = null;
    propertiesJS = null;
    authenticationJS = null;
    image = null;
    authentication = null;
    properties = null;
  }

  @Test
  void updatePost() throws Exception {
    int id = 1;
    String url = "/post/" + id;

    JSONObject postObject = new JSONObject();
    postObject.put("id", 1);
    postObject.put("title", "title");
    postObject.put("text", "text");
    postObject.put("data", "23-02-2022 08:09:10");
    postObject.put("authorId", 2);


    Post savePost = new Post();
    savePost.setId(1);
    savePost.setTitle("title");
    savePost.setTitle("text");
    savePost.setData(LocalDateTime.of(2022, 2, 23, 8, 9, 10));
    savePost.setAuthorId(2);
    when(postRepository.findById(id)).thenReturn(Optional.of(getPost()));
    when(userRepository.findByEmail(email)).thenReturn(Optional.of(getNewAuthor()));
    when(postRepository.save(savePost)).thenReturn(savePost);
    when(securityService.isCommentUpdateAvailable(any(),any(),any())).thenReturn(true);

    mockMvc.perform(MockMvcRequestBuilders.patch(
                url)
            .content(postObject.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.author").value(1))
        .andExpect(jsonPath("$.createdAt").value("23-02-2022 08:09:10"))
        .andExpect(jsonPath("$.pk").value(2))
        .andExpect(jsonPath("$.text").value("text"));

  }


  @Test
  void updatePostNegative() throws Exception {
    int id = 1;
    String url = "/post/" + id;

    JSONObject CreatePostObject = new JSONObject();
    CreatePostObject.put("title", "title");
    CreatePostObject.put("content", "text");
    CreatePostObject.put("authorId", 2);

    when(postRepository.findById(id)).thenThrow(ElemNotFound.class);

    mockMvc.perform(MockMvcRequestBuilders.patch(
                url)
            .content(CreatePostObject.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void createPostWithValidArg() throws Exception {
    title = "title";
    content = "text";
    String url = "/post";

    when(imageMapper.toEntity(any(ImageDTO.class))).thenReturn(getImageEntity());

    mockMvc.perform(multipart(url, HttpMethod.POST)
            .file(image)
            .file(properties)
            .file(authentication)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(
            jsonPath("$.image[0]").value((Base64.getEncoder().encodeToString(image.getBytes()))))
        .andExpect(jsonPath("$.title").value(title))
        .andExpect(jsonPath("$.price").value(content))
        .andExpect(status().isOk());
  }

  @Test
  void createPostWithNonValidArg() throws Exception {
    String url = "/post";

    when(imageMapper.toEntity(any(ImageDTO.class))).thenReturn(getImageEntity());

    mockMvc.perform(multipart(url, HttpMethod.POST)
            .file(image)
            .file(propertiesNonValid)
            .file(authentication)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }



  @Test
  void getAdsMe() throws Exception {
    String url = "/ads/me";

    mockMvc.perform(get(url)
            .content(authenticationJS.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }


  private Post getPost() {
    Post savePost = new  Post();
    savePost.setId(1);
    savePost.setTitle("title");
    savePost.setTitle("text");
    savePost.setData(LocalDateTime.of(2022, 2, 23, 8, 9, 10));
    savePost.setAuthorId(2);
    return savePost;
  }

  private UserEntity getNewAuthor() {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(1);
    userEntity.setName("name");
    userEntity.setEmail("email.ru");
    userEntity.setPassword("111111");
    return userEntity;
  }

  private ImageEntity getImageEntity() {
    return new ImageEntity(1, "path/to/image", getPost());
  }

}