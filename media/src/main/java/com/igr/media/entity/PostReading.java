package com.igr.media.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
/**    Идентификатор сообщения     */
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostReading {
    /**    Идентификатор сообщения     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
    @Column(name = "user_id")
    Integer user_id;
     boolean reading;
    /**    Дата сообщения     */
    @Column(name = "data")
     LocalDateTime data;
}
