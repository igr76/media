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
    @Mapping(target = "data", ignore = true)
    @Mapping(target = "friend", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    @Mapping(target = "message", ignore = true)
    @Mapping(target = "postUser", ignore = true)
    UserEntity toEntity(UserDto userDto);


    UserDto toDTO(UserEntity userEntity);

    Collection<UserEntity> toEntityList(Collection<UserDto> userDTOS);

    Collection<UserDto> toDTOList(Collection<UserEntity> userEntities);
}
