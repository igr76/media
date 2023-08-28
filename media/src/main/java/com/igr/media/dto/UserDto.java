package com.igr.media.dto;



import com.igr.media.entity.Friend;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
/**
 * DTO пользователя
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    /** id пользователя     */
        Integer id;

    /** Имя пользователя     */
    String name;

    /**     * почта пользователя     */
    String email;

    /**     * пароль пользователя     */
    String password;
    /**
     * фото пользователя
     */

    String image;
    /**     * друзья пользователя     */
    Collection<Friend> friend;
    /**     * сообщения друзей пользователя     */
    Collection<String> message;


}
