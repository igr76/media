package com.igr.media.entity;

import com.igr.media.dto.StatusFriend;
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
    /**
     * id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /**
     * Имя пользователя
     */
    @Column(name = "user_id1")
    String user_id1;

    /**
     * почта пользователя
     */
    @Column(name = "user_id2")
    String user_id2;
    /**
     * почта пользователя
     */
    @Column(name = "status")
    StatusFriend status;

}


