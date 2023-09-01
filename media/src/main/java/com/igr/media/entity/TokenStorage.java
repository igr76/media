package com.igr.media.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tokenStorage")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenStorage {
    @Id
    @Column(name = "login", nullable = false)
    String login;

    /** refreshToken     */
    @Column(name = "refreshToken")
    String refreshToken;
}
