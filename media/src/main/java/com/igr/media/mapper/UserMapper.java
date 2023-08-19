package com.igr.media.mapper;

import com.igr.media.dto.UserDto;
import com.igr.media.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.Collection;
/**
 * маппер для {@link com.igr.media.entity.UserEntity} готовый dto {@link com.igr.media.dto.UserDto}
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "regDate", source = "regDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "adEntities", ignore = true)
    @Mapping(target = "commentEntities", ignore = true)
    UserEntity toEntity(UserDto userDto);

    @Mapping(target = "regDate", source = "regDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    UserDto toDTO(UserEntity userEntity);

    Collection<UserEntity> toEntityList(Collection<UserDto> userDTOS);

    Collection<UserDto> toDTOList(Collection<UserEntity> userEntities);
}
