package com.systems.wissen.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systems.wissen.model.AllSkill;
import com.systems.wissen.repo.AllSkillsRepository;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(value = "/api/wiseconnect/v1/allskills")
public class AllSkillController {

	@Autowired
	AllSkillsRepository allSkillsRepository;

	@GetMapping
	public List<AllSkill> find() {
		return allSkillsRepository.getSkills();
	}
}
