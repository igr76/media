package com.igr.media.service;

import com.igr.media.dto.NewPassword;
import com.igr.media.dto.UserDto;
import com.igr.media.entity.Friend;
import com.igr.media.entity.UserEntity;
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
   * удалить пользователя
   */
  void deleteUser(UserDto userDto, Authentication authentication) ;
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
  /** найти пользователя по id */
  UserDto findById(int id, Authentication authentication);
  /** отправить сообщение другу */
  void messageOfFriend(int id,String message);
  /** Пригласить в друзья */
  void goFriend(String user, String friend);
  /** добавить в друзья */
  void addFriend(int userId,String nameFriends);
/** добавить пользователя в подписку */
  void addSubscription(String friend, Authentication authentication);
}
