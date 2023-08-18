package com.igr.media.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post {
    /**    Идентификатор сообщения     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**    Заголовок сообщения     */
    @Column(name = "title")
    public String title;
    /**    Содержание сообщения     */
    @Column(name = "content")
    private String content;
    /**    Дата сообщения     */
    @Column(name = "data")
    private LocalDateTime data;
    /**    автор сообщения     */
    @Column(name = "name")
    public String name;
}
