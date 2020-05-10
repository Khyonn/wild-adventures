package com.wildadventures.mscomments.proxies;

import com.wildadventures.mscomments.proxies.dto.AdventureDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "adventures")
@RibbonClient(name = "adventures")
public interface AdventureProxy {
    @GetMapping(value = "/aventures/{adventureId}")
    AdventureDTO getOneAdventure(@PathVariable("adventureId") int id);
}
