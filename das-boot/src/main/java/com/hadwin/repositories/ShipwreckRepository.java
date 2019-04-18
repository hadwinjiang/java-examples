package com.hadwin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hadwin.models.Shipwreck;

public interface ShipwreckRepository extends JpaRepository<Shipwreck, Long> {

}
