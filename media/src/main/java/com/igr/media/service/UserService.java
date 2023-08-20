package com.igr.media.service;

import com.igr.media.dto.NewPassword;
import com.igr.media.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

/**
 * сервис пользователя
 */
public interface UserService {

  /**
   * получить пользователя
   */
  UserDto getUser(Authentication authentication);

  /**
   * обновить пользователя
   */
  UserDto updateUser(UserDto userDto, Authentication authentication) ;

  /**
   * установить новый пароль пользователя
   */
  NewPassword setPassword(NewPassword newPassword);

  /**
   * обновить фото пользователя
   */
  void updateUserImage(MultipartFile image, Authentication authentication);


  /**
   * получить фото пользователя
   */
  byte[] getPhotoById(Integer id);
}
