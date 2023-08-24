package com.igr.media.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
/**    Идентификатор сообщения     */
@Entity
public class PostReading {
    /**    Идентификатор сообщения     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean reading;
    /**    Дата сообщения     */
    @Column(name = "data")
    private LocalDateTime data;
}
