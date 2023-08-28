package com.igr.media.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
/**    Статус прочтения сообщения     */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "post_reading")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostReading {
    /**    Идентификатор сообщения     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
    /**    Номер читателя     */
    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false)
    Integer userId;
    /**    номер сообщения     */
    @ManyToOne
    @JoinColumn(name = "posts_id",nullable = false)
    Integer postId;
    /**    Статус прочтения сообщения     */
     boolean reading;

}
