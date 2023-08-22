package com.igr.media.service;

import com.igr.media.dto.PostDto;
import com.igr.media.dto.UserDto;
import com.igr.media.entity.Post;
import com.igr.media.entity.UserEntity;
import com.igr.media.mapper.PostMapper;
import com.igr.media.repository.PostRepository;
import com.igr.media.repository.UserRepository;
import com.igr.media.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springdoc.core.SecurityService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {
    @Mock
    PostServiceImpl postService;
    @Mock
    SecurityService securityService;
    @Mock
    PostRepository postRepository;
    @Mock
    PostMapper postMapper;
    @Mock
    UserService userService;
    @Mock
    UserRepository userRepository;
    int ONE =1;
@Test
    void getAllPostsTest() {
    List<Post> postCollection = new ArrayList<>();
    postCollection.add(getPost());
    List<PostDto> postDtoList = new ArrayList<>();
    postDtoList.add(getPostDto());
    when(postRepository.findAll()).thenReturn(postCollection);
    when(postMapper.toDTOList(any())).thenReturn(postDtoList);
    assertThat(postService.getAllPosts(any())).isEqualTo(postDtoList);
    verify(postRepository,times(ONE)).findAll();
    }

    @Test
    void addPostTest() throws IOException {
        when(userService.getUser(any())).thenReturn(getUserDto());
        when(postRepository.findMaxID()).thenReturn(1);
        assertThat(postService.addPost(any(),any(),any())).isEqualTo(getPostDto());
        verify(postRepository,times(ONE)).findAll();
    }

    @Test
    void updatePostTest() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(getUser()));
        when(postRepository.findById(any())).thenReturn(Optional.of(getPost()));
        assertThat(postService.updatePost(any(),any(),any())).isEqualTo(getPostDto());
        verify(postRepository,times(ONE)).findAll();
    }

    private UserEntity getUser() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName("name");
        user.setEmail("email.ru");
        user.setPassword("111111");
        return user;
    }

    private Post getPost() {
        Post savePost = new  Post();
        savePost.setId(1);
        savePost.setTitle("title");
        savePost.setAuthorId(1);
        savePost.setContent("text");
        savePost.setData(LocalDateTime.of(2022, 2, 23, 8, 9, 10));
        savePost.setAuthorId(2);
        return savePost;
    }
    private PostDto getPostDto() {
        PostDto savePostDto = new PostDto();
        savePostDto.setId(1);
        savePostDto.setTitle("title");
        savePostDto.setContent("text");
        savePostDto.setAuthorId(1);
        return savePostDto;
    }
    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("name");
        userDto.setEmail("email.ru");
        userDto.setPassword("111111");
        return userDto;
    }

}
