package com.igr.media.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "friends")
public class Friends {
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

    @ManyToMany
    @JoinTable(name="users_friends",
            joinColumns=  @JoinColumn(name="friends_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="users_id", referencedColumnName="id") )
     Collection<UserEntity> user;

    public Friends(Integer id, String name, String email) {
        this.id = id;
        Name = name;
        this.email = email;
    }
}
