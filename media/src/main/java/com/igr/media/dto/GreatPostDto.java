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
public class GreatPostDto {
    /**    Заголовок сообщения     */
    public String title;
    /**    Содержание сообщения     */
    private String content;
    /**    Дата сообщения     */
    private LocalDateTime data;
    /**    автор сообщения     */
    public String name;
}
