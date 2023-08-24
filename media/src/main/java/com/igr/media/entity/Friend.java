package com.igr.media.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
/** Друзья сущность     */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "friends")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Friend {
    /** id пользователя     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /** Имя пользователя     */
    @Column(name = "name")
    String name;

    /**     * почта пользователя     */
    @Column(name = "email")
    String email;

    @ManyToMany
    @JoinTable(name="users_friends",
            joinColumns=  @JoinColumn(name="friends_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="users_id", referencedColumnName="id") )
     Collection<UserEntity> user;

    public Friend(Integer id, String name, String email) {
        this.id = id;
        name = name;
        this.email = email;
    }
}
