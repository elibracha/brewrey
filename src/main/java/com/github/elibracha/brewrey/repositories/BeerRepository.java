package com.github.elibracha.brewrey.repositories;

import com.github.elibracha.brewrey.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
