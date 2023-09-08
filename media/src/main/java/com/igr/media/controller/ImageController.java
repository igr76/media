package com.igr.media.controller;


import com.igr.media.dto.ImageDTO;
import com.igr.media.loger.FormLogInfo;
import com.igr.media.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/post")
@Tag(name = "Изображение")
@Slf4j
public class ImageController {

    private final PostService postService;

    public ImageController(PostService adsService) {
        this.postService = adsService;
    }


    @Operation(summary = "Загрузить картинку в пост")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",description = "OK",content = {
                            @Content(
                                    mediaType = "application/octet-stream",
                                    array = @ArraySchema(schema = @Schema(implementation = ImageDTO.class)))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Not Found"
            )
    })
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image,
        @PathVariable(value = "id") Integer id) throws IOException {
        postService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }

  @Operation(summary = "Получить аватарку поста")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "404", description = "Not Found")
  })
  @GetMapping(value = "{id}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> getPhotoById(@PathVariable(value = "id") Integer id) {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(postService.getPhotoById(id));
  }

}
