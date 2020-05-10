package com.wildadventures.msadventures.web.controller.dto.mappers;

import com.wildadventures.msadventures.dao.entity.Adventure;
import com.wildadventures.msadventures.dao.entity.AdventureImage;
import com.wildadventures.msadventures.web.controller.dto.AdventureDto;
import com.wildadventures.msadventures.web.controller.dto.AdventureImageDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel="spring")
public abstract class AdventureMapper {

    @Autowired
    private AdventureImageMapper adventureImageMapper;

    @BeforeMapping
    protected void setImages(Adventure adventure, @MappingTarget AdventureDto adventureDto){
        List<AdventureImage> images = adventure.getImages();
        List<AdventureImageDto> imagesDtos = new ArrayList<AdventureImageDto>();
        for(AdventureImage imgToMap : images){
            imagesDtos.add(adventureImageMapper.fromEntityToDto(imgToMap));
        }
        adventureDto.setAdventureImages(imagesDtos);
    }
    @Mappings({
            @Mapping(target = "startDate", source = "dateTime"),
            @Mapping(target = "id", source = "adventureId"),
            @Mapping(target = "categoryId", source = "category"),
    })
    public abstract AdventureDto fromEntityToDto(Adventure adventure);
}
