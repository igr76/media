package com.igr.media.controller;

import com.igr.media.dto.GreatPostDto;
import com.igr.media.dto.PostDto;
import com.igr.media.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
@RequestMapping("/post")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Сообщения")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Получить все объявления")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = PostDto.class)))
                    }
            )
    })
    @GetMapping
    public ResponseEntity<Collection<PostDto>> getAllPosts(Authentication authentication) {
        return ResponseEntity.ok(postService.getAllPosts( authentication));
    }

    @Operation(summary = "Получить сообщение по номеру")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = PostDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @GetMapping("{id}")
    public ResponseEntity<?> getPostById(
            @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id,
            Authentication authentication) {
        return ResponseEntity.ok().body(postService.getPostById(id, authentication));
    }
    @Operation(summary = "Получить только свои сообщениe")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = PostDto.class)))
                    }
            )
    })
    @GetMapping("/me")
    public ResponseEntity<Collection<PostDto>> getPostMe(Authentication authentication) {
        return ResponseEntity.ok(postService.getPostMe(authentication));
    }
    @Operation(summary = "Получить новыe  сообщения")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = PostDto.class)))
                    }
            )
    })
    @GetMapping("/allNew")
    public ResponseEntity<Collection<PostDto>> getAllPostsNew(Authentication authentication) {
        return ResponseEntity.ok(postService.getAllPostsNew(authentication));
    }
    @Operation(summary = "Получить новыe  сообщения по подпискам")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = PostDto.class)))
                    }
            )
    })
    @GetMapping("newSubscriptions")
    public ResponseEntity<Collection<PostDto>> getAllPostsNewSubscriptions(Authentication authentication) {
        return ResponseEntity.ok(postService.getAllPostsNewSubscriptions(authentication));
    }
    @Operation(summary = "Добавить пост")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = PostDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            )
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDto> addPost(
            @RequestPart("image") MultipartFile file,
            @RequestPart("properties") @Valid GreatPostDto greatPostDto,
            Authentication authentication) throws IOException {
        return ResponseEntity.ok(postService.addPost(greatPostDto, file, authentication));
    }
    @Operation(summary = "Обновить пост")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    schema = @Schema(ref = "#/components/schemas/AdsDTO"))
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            )
    })
    @PatchMapping("{id}")
    public ResponseEntity<?> updatePost(
            @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id,
            @RequestBody PostDto postDto,
            Authentication authentication) {

        return ResponseEntity.ok().body(postService.updatePost(id, postDto, authentication));
    }
    @Operation(summary = "Удалить пост по id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            )
    })
    @DeleteMapping(value = "/{id}")
    public void removePost(@PathVariable(name = "id")
                          @NotBlank(message = "id не должен быть пустым")
                          @Min(value = 1, message = "Идентификатор должен быть больше 0")
                          @Parameter(description = "Идентификатор объявления",
                                  example = "1") int id, Authentication authentication) {
        postService.removePost(id, authentication);
    }

}
