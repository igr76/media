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
    @Column(name = "user_id")
    /**    номер сообщения     */
    Integer user_id;
    @Column(name = "post_id")
    Integer post_id;
    /**    Статус прочтения сообщения     */
     boolean reading;

}
