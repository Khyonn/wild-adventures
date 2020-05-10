package com.wildadventures.msadventures.dao.repository;

import com.wildadventures.msadventures.dao.entity.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRepository extends JpaRepository<Adventure, Integer> {
}
