package com.igr.media.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
/**
 * A DTO for the {@link com.igr.media.entity.Post} entity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {
    /**    Идентификатор сообщения     */
     int id;
    /**    Заголовок сообщения     */
    String title;
    /**    Содержание сообщения     */
    String content;
    /**    Дата сообщения     */
     LocalDateTime data;
    /**    автор сообщения     */
     Integer authorId;
    /**    изображение сообщения     */
     String image;
}
