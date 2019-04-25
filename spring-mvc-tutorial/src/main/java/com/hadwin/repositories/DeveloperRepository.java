package com.hadwin.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hadwin.models.Developer;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {

}
