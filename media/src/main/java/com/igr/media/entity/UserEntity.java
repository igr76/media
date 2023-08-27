package com.igr.media.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.igr.media.dto.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
/**     * Сущность пользователя     */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    /** id пользователя     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /** Имя пользователя     */
    @Column(name = "name")
    String Name;

    /**     * почта пользователя     */
    @Column(name = "email")
    String email;

    /**     * пароль пользователя     */
    @Column(name = "password")
    String password;
    /**    Время последнего прочтения     */
    @Column(name = "data")
     LocalDateTime data;
    /**
     * фото пользователя
     */
    @Column(name = "image")
    String image;

    /**     * друзья пользователя     */
    @OneToMany
    @JoinColumn(name = "user1")
      Collection<Friend> friend;

    /**     * роль пользователя     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
     Role role;
    /**     * Статус прочтения сообщений     */
    @OneToMany
    @JoinColumn(name = "user_id")
    Collection<PostReading> postReading;

    /**     * сообщения друзей пользователя     */
    @ElementCollection
    @CollectionTable(name = "user_message", joinColumns = @JoinColumn(name = "users_id"))
    @Column(name = "message")
    Collection<String> message;
    /**     посты пользователя     */
    @OneToMany(mappedBy = "userPost")
    @JsonBackReference
    @ToString.Exclude
    Collection<Post> postUser;
}
