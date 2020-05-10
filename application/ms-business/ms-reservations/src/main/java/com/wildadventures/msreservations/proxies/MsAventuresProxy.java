package com.wildadventures.msreservations.proxies;

import com.wildadventures.msreservations.beans.AdventureBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RibbonClient(name="adventures")
@FeignClient(name = "adventures")
public interface MsAventuresProxy {

    @GetMapping(value = "/aventures/{adventureId}")
    AdventureBean getOneAdventure(@PathVariable("adventureId") int id);

}
