package com.igr.media.controller;


import com.igr.media.WebSecurityConfigTest;
import com.igr.media.repository.ImageRepository;
import com.igr.media.repository.PostRepository;
import com.igr.media.service.ImageService;
import com.igr.media.service.PostService;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageController.class)
@Import(value = WebSecurityConfigTest.class)
class ImageControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @InjectMocks
  private ImageController imageController;

  @MockBean
  private PostService postService;

  @MockBean
  private PostRepository postRepository;

  @MockBean
  private ImageService imageService;

  @MockBean
  private ImageRepository imageRepository;

  @Test
  public void imageControllerTest() throws Exception {

    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    MockMultipartFile image = new MockMultipartFile("image", "image.jpeg",
        MediaType.IMAGE_JPEG_VALUE, "image.jpeg".getBytes());

    mockMvc.perform(multipart(HttpMethod.PATCH, "/post/image/{id}", 1).file(image)
            .with(user("user@gmail.com").password("password").roles("USER"))
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE).accept(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andDo(print())
            .andExpect(status().isOk());

    mockMvc.perform(multipart("/post/image/{id}", 1).file(image)
                    .with(user("user@gmail.com").password("password").roles("USER"))
                    .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andDo(print())
            .andExpect(status().isOk());

    MockMultipartHttpServletRequestBuilder builder =
            multipart("/post/image/{id}",1);
    builder.with(new RequestPostProcessor() {
      @Override
      public @NotNull MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
        request.setMethod("PATCH");
        return request;
      }
    });
    mockMvc.perform(builder
                    .file(image))
            .andDo(print())
            .andExpect(status().isOk());
  }





}