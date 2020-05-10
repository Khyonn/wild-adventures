package com.wildadventures.msadventures.dao.repository;

import com.wildadventures.msadventures.dao.entity.AdventureImage;
import com.wildadventures.msadventures.dao.entity.pk.AdventureImagePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureImageRepository extends JpaRepository<AdventureImage, AdventureImagePK> {
}
