package com.igr.media.mapper;

import com.igr.media.dto.ImageDTO;
import com.igr.media.entity.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * маппер для {@link com.igr.media.entity.ImageEntity} готовый DTO {@link com.igr.media.dto.ImageDTO}
 */
@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "path", source = "image")
    ImageEntity toEntity(ImageDTO imageDTO);
    @Mapping(target = "image", source = "path")
    ImageDTO toDTO(ImageEntity imageEntity);

}
