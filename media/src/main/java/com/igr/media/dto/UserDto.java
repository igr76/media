package com.igr.media.dto;



import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
/**
 * DTO сущности
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


}
