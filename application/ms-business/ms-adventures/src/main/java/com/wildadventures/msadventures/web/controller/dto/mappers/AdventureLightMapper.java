package com.wildadventures.msadventures.web.controller.dto.mappers;

import com.wildadventures.msadventures.dao.entity.Adventure;
import com.wildadventures.msadventures.dao.entity.AdventureImage;
import com.wildadventures.msadventures.web.controller.dto.AdventureLightDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel="spring")
public abstract class AdventureLightMapper {

    @Autowired
    private AdventureImageMapper adventureImageMapper;

    @BeforeMapping
    protected void setMainImage(Adventure adventure, @MappingTarget AdventureLightDto adventureLightDto){
        List<AdventureImage> images = adventure.getImages();
        AdventureImage image = new AdventureImage();
        for(AdventureImage imgToReturn : images) {
            if(imgToReturn.isMain()){
                image = imgToReturn;
                break;
            }
            else image = null;
    }
        adventureLightDto.setImage(adventureImageMapper.fromEntityToDto(image));
}
    @Mappings({
            @Mapping(target = "startDate", source = "dateTime"),
            @Mapping(target = "id", source = "adventureId"),
            @Mapping(target = "categoryId", source = "category"),
    })
    public abstract AdventureLightDto fromEntityToDto(Adventure adventure);
}


