package com.wildadventures.msadventures.web.controller;

import com.wildadventures.msadventures.dao.entity.Adventure;
import com.wildadventures.msadventures.dao.repository.AdventureRepository;
import com.wildadventures.msadventures.web.controller.dto.AdventureDto;
import com.wildadventures.msadventures.web.controller.dto.AdventureLightDto;
import com.wildadventures.msadventures.web.controller.dto.mappers.AdventureLightMapper;
import com.wildadventures.msadventures.web.controller.dto.mappers.AdventureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AventuresController {

    @Autowired
    private AdventureRepository adventureRepository;
    @Autowired
    private AdventureLightMapper adventureLightMapper;

    @Autowired
    private AdventureMapper adventureMapper;


    private ResponseEntity<List<AdventureLightDto>> getAdventuresLightResponseEntity(List<Adventure> adventures){
        // S'il on ne trouve rien, on retourne un 204 : no content
        if (adventures == null || adventures.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Sinon, on retourne un status 200 avec la liste des aventures
            List<AdventureLightDto> toReturn = adventures.stream()
                    .map(adventureLightMapper::fromEntityToDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<List<AdventureLightDto>>(toReturn, HttpStatus.OK);
        }
    }
    private ResponseEntity<AdventureDto> getAdventureResponseEntity(Adventure adventure){
        // S'il on ne trouve rien, on retourne un 204 : no content
        if (adventure == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Sinon, on retourne un status 200 avec l'aventure correspondante
            AdventureDto toReturn = adventureMapper.fromEntityToDto(adventure);

            return new ResponseEntity<>(toReturn, HttpStatus.OK);
        }
    }

        @RequestMapping(value="/aventures", method= RequestMethod.GET)
        public ResponseEntity<List<AdventureLightDto>> getAllAdventures() {


        return getAdventuresLightResponseEntity(adventureRepository.findAll());
        }

        @RequestMapping(value="/aventures/{adventureId}", method= RequestMethod.GET)
        public ResponseEntity<AdventureDto> getOneAdventure(@PathVariable int adventureId){

            return getAdventureResponseEntity(adventureRepository.findById(adventureId).get());
        }

}
