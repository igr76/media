package com.igr.media.entity;

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
@Table(name = "posts")
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
    @Column(name = "password")
    Collection<String> friend;
}
