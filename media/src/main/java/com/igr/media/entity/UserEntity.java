package com.igr.media.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.igr.media.dto.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "user_subscriptions", joinColumns = @JoinColumn(name = "users_id"))
    @Column(name = "subscriptions")
    List<Integer> subscriptions;

    @ElementCollection
    @CollectionTable(name = "user_message", joinColumns = @JoinColumn(name = "users_id"))
    @Column(name = "message")
    List<String> message;

    @OneToMany(mappedBy = "userPost")
    @JsonBackReference
    @ToString.Exclude
    List<Post> postUser;
}
