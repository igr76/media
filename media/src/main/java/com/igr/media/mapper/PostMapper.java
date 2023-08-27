package com.igr.media.mapper;

import com.igr.media.dto.PostDto;
import com.igr.media.entity.ImageEntity;
import com.igr.media.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * маппер для {@link Post} готовый DTO {@link PostDto}
 */
@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "imageEntities", expression = "java(setImageEntities(postDto.getImage()))")
    Post toEntity(PostDto postDto);




    @Mapping(target = "image", expression = "java(setImage(post.getImageEntities()))")
    PostDto toDTO(Post post);

    default String setImage(List<ImageEntity> imageEntities) {
        if (imageEntities == null || imageEntities.size() == 0) {
            return null;
        }
        return imageEntities.get(0).getPath();
    }

    default List<ImageEntity> setImageEntities(String path) {

        List<ImageEntity> imageEntities = new ArrayList<>();
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setPath(path);
        imageEntities.add(imageEntity);

        return imageEntities;
    }

    Collection<Post> toEntityList(Collection<PostDto> postDtoCollection);

    Collection<PostDto> toDTOList(Collection<Post> postCollection);
}

