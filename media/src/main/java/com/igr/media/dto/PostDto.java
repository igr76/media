package com.igr.media.dto;


import java.time.LocalDateTime;

public class PostDto {
    /**    Идентификатор сообщения     */

    private Long id;
    /**    Заголовок сообщения     */
    public String title;
    /**    Содержание сообщения     */
    private String content;
    /**    Дата сообщения     */
    private LocalDateTime data;
    /**    автор сообщения     */
    public String name;
}
