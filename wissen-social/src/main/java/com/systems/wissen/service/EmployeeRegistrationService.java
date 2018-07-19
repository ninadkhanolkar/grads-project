package com.systems.wissen.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systems.wissen.model.Address;
import com.systems.wissen.model.AllSkill;
import com.systems.wissen.model.Certification;
import com.systems.wissen.model.Employee;
import com.systems.wissen.model.Skill;
import com.systems.wissen.model.UserCredential;
import com.systems.wissen.repo.EmployeeRepository;
import com.systems.wissen.repo.UserCredentialRepository;

@Service
public class EmployeeRegistrationService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	UserCredentialRepository userCredentialRepository;
	
	public void registerEmployee(JSONObject jo) {

		Employee employee = new Employee();
		employee.setEmpId((String) jo.get("empId"));
		employee.setFirstName((String) jo.get("firstName"));
		employee.setLastName((String) jo.get("lastName"));
		employee.setBioPic((String) jo.get("bioPic"));
		employee.setGender((String) jo.get("gender"));
		employee.setMaritalStatus((String) jo.get("maritalStatus"));
		employee.setEmailId((String) jo.get("emailId"));
		try {
			employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse((String) jo.get("dateOfBirth")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(!jo.get("contactNumberPersonal").toString().equals("")) {
			employee.setContactNumberPersonal(Long.parseLong(jo.get("contactNumberPersonal").toString()));
		}
		
		employee.setContactNumberWork(Long.parseLong(jo.get("contactNumberWork").toString()));
		Employee managerEmployee=null;
		if(!(((String)jo.get("managerId")).equals("")||(String)jo.get("managerId")==null)) {
			managerEmployee = new Employee();
			managerEmployee.setEmpId((String) jo.get("managerId"));
		}
		employee.setEmployee(managerEmployee);
		employee.setCurrentPosition((String) jo.get("currentPosition"));

		LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) jo.get("address");
		Address address = new Address();
		address.setStreet((String) map.get("street"));
		address.setCity((String) map.get("city"));
		address.setState((String) map.get("state"));
		address.setCountry((String) map.get("country"));
		address.setZipcode((int) map.get("zipcode"));
		address.setEmployee(employee);
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		employee.setAddresses(addresses);

		employee.setQualificationDegree((String) jo.get("qualificationDegree"));
		employee.setInstituteName((String) jo.get("instituteName"));
		employee.setBio((String) jo.get("bio"));

		List<Certification> certifications = new ArrayList<>();
		ArrayList<?> data = (ArrayList<?>) jo.get("certifications");

		Iterator<?> i = data.iterator();
		while (i.hasNext()) {
			Certification certification = new Certification();
			LinkedHashMap<?, ?> map2 = (LinkedHashMap<?, ?>) i.next();
			certification.setCertificationName((String) map2.get("certificationName"));
			String completionYear = (String) map2.get("completionYear");
			certification.setCompletionYear(Integer.parseInt(completionYear));
			certification.setEmployee(employee);
			certifications.add(certification);
		}
		employee.setCertifications(certifications);

		employee.setResume((String) jo.get("resume"));
		employee.setApplicationStatus(0);// default : not approved

		List<Skill> skills = new ArrayList<>();
		ArrayList<?> data2 = (ArrayList<?>) jo.get("skills");
		Iterator<?> i2 = data2.iterator();
		while (i2.hasNext()) {
			Skill skill = new Skill();
			LinkedHashMap<?, ?> map2 = (LinkedHashMap<?, ?>) i2.next();
			LinkedHashMap<?, ?> map22 = (LinkedHashMap<?, ?>) map2.get("allSkillId"); 

			AllSkill allSkill = new AllSkill();
			allSkill.setAllSkillId((int) map22.get("allSkillId"));
			skill.setAllSkill(allSkill);
			skill.setEmployee(employee);
			skills.add(skill);
		}
		employee.setSkills(skills);

		UserCredential userCredential = new UserCredential();
		userCredential.setPassword((String) jo.get("password"));
		userCredential.setRoleId(3);
		userCredential.setEmployee(employee);

		employeeRepository.addEmpoloyee(employee);
		userCredentialRepository.addUserCredential(userCredential);
	}

}
