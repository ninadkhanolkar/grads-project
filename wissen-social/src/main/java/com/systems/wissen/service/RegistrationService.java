package com.systems.wissen.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.systems.wissen.model.Address;
import com.systems.wissen.model.AllSkill;
import com.systems.wissen.model.Certification;
import com.systems.wissen.model.Employee;
import com.systems.wissen.model.Skill;
import com.systems.wissen.model.UserCredential;

@Service
public class RegistrationService {

	public Map<String,Object> registerEmployee(JSONObject registrationObject) {
		Employee employee = createEmployee(registrationObject);
		try {
			employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse((String) registrationObject.get("dateOfBirth")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(!registrationObject.get("contactNumberPersonal").toString().equals("")) {
			employee.setContactNumberPersonal(Long.parseLong(registrationObject.get("contactNumberPersonal").toString()));
		}
		employee.setContactNumberWork(Long.parseLong(registrationObject.get("contactNumberWork").toString()));
		Employee managerEmployee=null;
		String managerId = (String)registrationObject.get("managerId");
		if (!(managerId.equals("") || managerId==null)) {
			managerEmployee = new Employee();
			managerEmployee.setEmpId((String) registrationObject.get("managerId"));
		}
		employee.setEmployee(managerEmployee);
		employee.setCurrentPosition((String) registrationObject.get("currentPosition"));
		setAddress(registrationObject, employee);
		setCertifications(registrationObject, employee);
		setSkills(registrationObject, employee);
		UserCredential userCredential = setUserCredentials(registrationObject, employee);
		Map<String,Object> map = new HashMap<>();
		map.put("employeeRepository", employee);
		map.put("userCredentialRepository", userCredential);
		return map;
	}

	private UserCredential setUserCredentials(JSONObject registrationObject, Employee employee) {
		UserCredential userCredential = new UserCredential();
		userCredential.setPassword((String) registrationObject.get("password"));
		userCredential.setRoleId(3);
		userCredential.setEmployee(employee);
		return userCredential;
	}

	private void setSkills(JSONObject registrationObject, Employee employee) {
		List<Skill> skills = new ArrayList<>();
		ArrayList<?> data2 = (ArrayList<?>) registrationObject.get("skills");
		Iterator<?> i2 = data2.iterator();
		while (i2.hasNext()) {
			Skill skill = new Skill();
			LinkedHashMap<?, ?> map2 = (LinkedHashMap<?, ?>) i2.next();
			try {
			LinkedHashMap<?, ?> map22 = (LinkedHashMap<?, ?>) map2.get("allSkillId"); 
			AllSkill allSkill = new AllSkill();
			allSkill.setAllSkillId((int) map22.get("allSkillId"));
			skill.setAllSkill(allSkill);
			skill.setEmployee(employee);
			skills.add(skill);}
			catch(Exception e) {
				System.out.println(e);			}
		}
		employee.setSkills(skills);
	}

	private void setCertifications(JSONObject registrationObject, Employee employee) {
		List<Certification> certifications = new ArrayList<>();
		ArrayList<?> data = (ArrayList<?>) registrationObject.get("certifications");
		Iterator<?> i = data.iterator();
		while (i.hasNext()) {
			Certification certification = new Certification();
			LinkedHashMap<?, ?> map2 = (LinkedHashMap<?, ?>) i.next();
			certification.setCertificationName((String) map2.get("certificationName"));
			String completionYear = map2.get("completionYear").toString();
			certification.setCompletionYear(Integer.parseInt(completionYear));
			certification.setEmployee(employee);
			certifications.add(certification);
		}
		employee.setCertifications(certifications);
	}

	private void setAddress(JSONObject registrationObject, Employee employee) {
		LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) registrationObject.get("address");
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
	}

	private Employee createEmployee(JSONObject registrationObject) {
		Employee employee = new Employee();
		employee.setEmpId((String) registrationObject.get("empId"));
		employee.setFirstName((String) registrationObject.get("firstName"));
		employee.setLastName((String) registrationObject.get("lastName"));
		employee.setBioPic((String) registrationObject.get("bioPic"));
		employee.setGender((String) registrationObject.get("gender"));
		employee.setMaritalStatus((String) registrationObject.get("maritalStatus"));
		employee.setEmailId((String) registrationObject.get("emailId"));
		employee.setQualificationDegree((String) registrationObject.get("qualificationDegree"));
		employee.setInstituteName((String) registrationObject.get("instituteName"));
		employee.setBio((String) registrationObject.get("bio"));
		employee.setResume((String) registrationObject.get("resume"));
		employee.setApplicationStatus(0);// default : not approved
		return employee;
	}
}
