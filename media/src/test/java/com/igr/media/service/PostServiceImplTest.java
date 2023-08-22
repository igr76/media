package com.igr.media.service;

import com.igr.media.dto.PostDto;
import com.igr.media.entity.Post;
import com.igr.media.mapper.PostMapper;
import com.igr.media.repository.PostRepository;
import com.igr.media.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springdoc.core.SecurityService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
@Test
    void getAllPostsTest() {
    List<Post> postCollection = new ArrayList<>();
    postCollection.add(getPost());
    when(postRepository.findAll()).thenReturn(postCollection);
    when(postMapper.toDTOList(any())).thenReturn(autoDtoList);
    assertThat(postService()).isEqualTo(autoDtoList);
    verify(postRepository,times(ONE)).findAll();
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
    private Post getPostDto() {
        PostDto savePost = new PostDto();
        savePost.setId(1);
        savePost.setTitle("title");
        savePost.setTitle("text");
        savePost.setData(LocalDateTime.of(2022, 2, 23, 8, 9, 10));
        savePost.setAuthorId(2);
        return savePost;
    }
}
