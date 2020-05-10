package com.wildadventures.msadventures.web.controller.dto.mappers;
import com.wildadventures.msadventures.dao.entity.AdventureImage;
import com.wildadventures.msadventures.web.controller.dto.AdventureImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface AdventureImageMapper {


    @Mappings({
            @Mapping(target = "label", source = "label"),
            @Mapping(target = "url", source = "url"),
            @Mapping(target = "width", source = "width"),
            @Mapping(target = "height", source = "height")
    })
    AdventureImageDto fromEntityToDto(AdventureImage adventureImage);
}
