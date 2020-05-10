package com.wildadventures.mscomments.controllers.dto.mappers;

import com.wildadventures.mscomments.controllers.dto.CommentDto;
import com.wildadventures.mscomments.dao.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
        @Mapping(target = "dateTime", source = "date"),
    })
    Comment fromDtoToEntity(CommentDto commentDto);

    @Mappings({
        @Mapping(target = "date", source = "dateTime")
    })
    CommentDto fromEntityToDto(Comment comment);
}
