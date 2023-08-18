package com.igr.media.entity;

import com.igr.media.dto.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
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

    /**     * друзья пользователя     */
    @ElementCollection
    @CollectionTable(name = "user_list_of_friends", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "list_of_friends")
    Collection<Integer> friend;
    /**     * роль пользователя     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
