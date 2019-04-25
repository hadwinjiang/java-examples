package com.hadwin.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hadwin.models.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {
	List<Skill> findByLable(String label);
}
