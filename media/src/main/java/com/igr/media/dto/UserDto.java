package com.igr.media.dto;



import com.igr.media.entity.Friends;
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
    String Name;

    /**     * почта пользователя     */
    String email;

    /**     * пароль пользователя     */
    String password;
    /**
     * фото пользователя
     */

    String image;
    /**     * друзья пользователя     */
    Collection<Friends> friend;
    /**     * сообщения друзей пользователя     */
    Collection<String> message;


}
