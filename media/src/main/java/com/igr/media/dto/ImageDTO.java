package com.igr.media.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO  для {@link com.igr.media.entity.ImageEntity} картинки
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageDTO {

    String image;
}
