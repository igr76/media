package com.igr.media.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
/**    Сущность сообщения     */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    /**    Идентификатор сообщения     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
    /**    Заголовок сообщения     */
    @Column(name = "title")
     String title;
    /**    Содержание сообщения     */
    @Column(name = "content")
     String content;
    /**    Дата сообщения     */
    @Column(name = "data")
     LocalDateTime data;
    /**    автор сообщения     */
    @Column(name = "authorId")
     int authorId;
    /**     * Статус прочтения сообщений     */
    @OneToMany(mappedBy = "postId")
    Collection<PostReading> postReading;
    /**    Изображение сообщения     */
    @OneToMany(mappedBy = "postIdImage")
    @ToString.Exclude
    List<ImageEntity> imageEntities;
    /**    Автор  сообщения     */
    @ManyToOne
    @JoinColumn(name = "users_id")
    UserEntity userPost;
}
