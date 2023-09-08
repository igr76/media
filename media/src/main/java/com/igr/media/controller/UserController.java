package com.igr.media.controller;

import com.igr.media.dto.NewPassword;
import com.igr.media.dto.PostDto;
import com.igr.media.dto.UserDto;
import com.igr.media.entity.Friend;
import com.igr.media.loger.FormLogInfo;
import com.igr.media.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/users")
@Slf4j
@Tag(name = "Пользователи")
@RestController
public class UserController {


  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Установить новый пароль")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK", content =
          @Content(
              array = @ArraySchema(schema = @Schema(implementation = NewPassword.class)))),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
  })
  @PostMapping(value = "/setPassword")
  public ResponseEntity<NewPassword> setPassword(
      @RequestBody NewPassword newPassword) {
    log.info(FormLogInfo.getInfo());
    NewPassword newPasswordDTO = userService.setPassword(newPassword);
    return ResponseEntity.ok(newPasswordDTO);
  }

  @Operation(summary = "Получить пользователя")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
      @ApiResponse(responseCode = "401", description = "Unauthorized",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "403", description = "Forbidden",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(schema = @Schema()))
  })
  @GetMapping(value = "/me")
  public ResponseEntity<UserDto> getUser(Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(userService.getUser(authentication));
  }

  @Operation(summary = "Обновить пользователя")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(
              array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
      @ApiResponse(responseCode = "204", description = "No Content",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "401", description = "Unauthorized",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "403", description = "Forbidden",
          content = @Content(schema = @Schema())),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(schema = @Schema()))
  })
  @PatchMapping(value = "/me")
  public ResponseEntity<UserDto> updateUser(
      @RequestBody
      UserDto userDto, Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(userService.updateUser(userDto, authentication));
  }

  @Operation(summary = "Обновить изображение пользователя")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(array = @ArraySchema())),
      @ApiResponse(responseCode = "404", description = "Not Found",
          content = @Content(schema = @Schema()))
  })
  @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image,
      Authentication authentication) {
    log.info(FormLogInfo.getInfo());
    userService.updateUserImage(image, authentication);
    return ResponseEntity.ok().build();
  }


  @Operation(summary = "Получить аватарку юзера")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "404", description = "Not Found")
  })
  @GetMapping(value = "{id}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> getUserImage(@PathVariable(value = "id") Integer id) {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(userService.getPhotoById(id));
  }
    @Operation(summary = "Получить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema()))
    })
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> findById(
            @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id,
            Authentication authentication) {
        log.info(FormLogInfo.getInfo());
        return ResponseEntity.ok(userService.findById(id,authentication));
    }
    @Operation(summary = "отправить сообщение другу")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(
                                    schema = @Schema(ref = "#/components/schemas/AdsDTO"))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PatchMapping("/message/{id}")
    public void messageOfFriend(
            @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id,
            @RequestBody @NonNull String message) {
      userService.messageOfFriend(id,message);

    }
    @Operation(summary = "добавить в друзья")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(
                                    schema = @Schema(ref = "#/components/schemas/AdsDTO"))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PatchMapping("/friend/{id}")
    public void addFriend(
            @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id,
            @RequestBody String friend) {
        userService.addFriend(id,friend);

    }
    @Operation(summary = "Пригласить в друзья")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(
                                    schema = @Schema(ref = "#/components/schemas/AdsDTO"))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PatchMapping("/friend/{user}")
    public void goFriend(
            @PathVariable(name = "user") @NonNull  String user,
            @RequestBody
            String nameFriends) {
        userService.goFriend(user,nameFriends);

    }



}