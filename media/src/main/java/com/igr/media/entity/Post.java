package com.igr.media.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private Integer id;
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
    @Column(name = "authorId")
    public int authorId;
    /**    Изображение сообщения     */
    @OneToMany(mappedBy = "images_id")
    @JsonBackReference
    @ToString.Exclude
    List<ImageEntity> imageEntities;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userPost")
    UserEntity userPost;
}
