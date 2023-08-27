package com.igr.media.entity;

import com.igr.media.dto.StatusFriend;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
/** Друзья, подписчики сущность     */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "friends")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Friend {
    /**
     * id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /**
     * Номер пользователя
     */
    @Column(name = "user_id1")
    int user1;

    /**
     *Номер другв
     */
    @Column(name = "user_id2")
    int user2;
    /**
     * почта пользователя
     */
    @Column(name = "status")
    StatusFriend status;

}


