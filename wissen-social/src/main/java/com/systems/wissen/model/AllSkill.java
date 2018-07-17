package com.systems.wissen.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the all_skill database table.
 * 
 */
@Entity
@Table(name="all_skill")
@NamedQuery(name="AllSkill.findAll", query="SELECT a FROM AllSkill a")
public class AllSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="all_skill_id")
	private int allSkillId;

	private String name;

	//bi-directional many-to-one association to Skill
	@OneToMany(mappedBy="allSkill")
	@JsonIgnore
	private List<Skill> skills;

	public AllSkill() {
	}

	public int getAllSkillId() {
		return this.allSkillId;
	}

	public void setAllSkillId(int allSkillId) {
		this.allSkillId = allSkillId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Skill addSkill(Skill skill) {
		getSkills().add(skill);
		skill.setAllSkill(this);

		return skill;
	}

	public Skill removeSkill(Skill skill) {
		getSkills().remove(skill);
		skill.setAllSkill(null);

		return skill;
	}

}