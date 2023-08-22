package com.igr.media.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.igr.media.dto.Role;
import jakarta.persistence.*;
import lombok.*;

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
    private LocalDateTime data;
    /**
     * фото пользователя
     */
    @Column(name = "image")
    String image;

    /**     * друзья пользователя     */
    @ManyToMany
    @JoinTable(name="users_friends",
            joinColumns=  @JoinColumn(name="users_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="friends_id", referencedColumnName="id") )
    private  Collection<Friends> friend;

    /**     * роль пользователя     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    /**     * подписки пользователя     */
    @ElementCollection
    @CollectionTable(name = "user_subscriptions", joinColumns = @JoinColumn(name = "users_id"))
    @Column(name = "subscriptions")
    Collection<Integer> subscriptions;
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
