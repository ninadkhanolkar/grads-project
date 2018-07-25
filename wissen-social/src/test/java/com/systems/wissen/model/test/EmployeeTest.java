package com.systems.wissen.model.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.systems.wissen.model.Address;
import com.systems.wissen.model.Certification;
import com.systems.wissen.model.Employee;
import com.systems.wissen.model.Skill;

public class EmployeeTest {

	private Employee employee;

	@Before
	public void setUp() {
		employee = new Employee();
	}

	@Test
	public void testGetSetManagerId() {
		employee.setManagerId();
		assertEquals("", employee.getManagerId());
	}

	@Test
	public void testGetSetManagerIdNotNull() {
		Employee empl = new Employee();
		empl.setEmpId("WT203");
		employee.setEmployee(empl);
		employee.setManagerId();
		assertEquals("WT203", employee.getManagerId());
	}

	@Test
	public void testGetSetEmpId() {
		employee.setEmpId("WT203");
		assertEquals("WT203", employee.getEmpId());
	}

	@Test
	public void testGetSetApplicationStatus() {
		employee.setApplicationStatus(1);
		assertEquals(1, employee.getApplicationStatus());
	}

	@Test
	public void testGetSetBio() {
		employee.setBio("Test Bio");
		assertEquals("Test Bio", employee.getBio());
	}

	@Test
	public void testGetSetBioPic() {
		employee.setBioPic("biopic.jpeg");
		assertEquals("biopic.jpeg", employee.getBioPic());
	}

	@Test
	public void testGetSetContactNumberPersonal() {
		employee.setContactNumberPersonal(9952520026L);
		assertEquals(9952520026L, employee.getContactNumberPersonal());
	}

	@Test
	public void testGetSetContactNumberWork() {
		employee.setContactNumberWork(9952520026L);
		assertEquals(9952520026L, employee.getContactNumberWork());
	}

	@Test
	public void testGetSetCurrentPosition() {
		employee.setCurrentPosition("Trainee Analyst");
		assertEquals("Trainee Analyst", employee.getCurrentPosition());
	}

	@Test
	public void testGetDateOfBirth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse("2018-06-28");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		employee.setDateOfBirth(date);
		assertEquals(date, employee.getDateOfBirth());
	}

	@Test
	public void testGetSetEmailId() {
		String emailId = "sjain8157@gmail.com";
		employee.setEmailId(emailId);
		assertEquals(emailId, employee.getEmailId());
	}

	@Test
	public void testGetSetFirstName() {
		String firstName = "WiseConnect";
		employee.setFirstName(firstName);
		assertEquals(firstName, employee.getFirstName());
	}

	@Test
	public void testGetSetGender() {
		String gender = "Male";
		employee.setGender(gender);
		assertEquals(gender, employee.getGender());
	}

	@Test
	public void testGetSetInstituteName() {
		String instituteName = "VIT";
		employee.setInstituteName(instituteName);
		assertEquals(instituteName, employee.getInstituteName());
	}

	@Test
	public void testGetSetLastName() {
		String lastName = "VIT";
		employee.setLastName(lastName);
		assertEquals(lastName, employee.getLastName());
	}

	@Test
	public void testGetSetMaritalStatus() {
		String maritalStatus = "Married";
		employee.setMaritalStatus(maritalStatus);
		assertEquals(maritalStatus, employee.getMaritalStatus());
	}

	@Test
	public void testGetSetQualificationDegree() {
		String qualificationDegree = "B-Tech";
		employee.setQualificationDegree(qualificationDegree);
		assertEquals(qualificationDegree, employee.getQualificationDegree());
	}

	@Test
	public void testGetSetResume() {
		String resume = "resume.pdf";
		employee.setResume(resume);
		assertEquals(resume, employee.getResume());
	}

	@Test
	public void testGetSetAddresses() {
		List<Address> addresses = new ArrayList<>();
		employee.setAddresses(addresses);
		assertEquals(addresses, employee.getAddresses());
	}

	@Test
	public void testAddAddress() {
		List<Address> addresses = new ArrayList<>();
		employee.setAddresses(addresses);
		Address address = new Address();
		employee.addAddress(address);
		assertEquals(address, employee.removeAddress(address));
	}

	@Test
	public void testGetSetAddRemoveCertifications() {
		List<Certification> certifications = new ArrayList<>();
		employee.setCertifications(certifications);
		assertEquals(certifications, employee.getCertifications());
		Certification certification = new Certification();
		employee.addCertification(certification);
		assertEquals(certification, employee.removeCertification(certification));
	}

	@Test
	public void testGetSetEmployee() {
		employee.setEmployee(employee);
		assertEquals(employee, employee.getEmployee());
	}

	@Test
	public void testGetSetAddRemoveEmployees() {
		List<Employee> employees = new ArrayList<>();
		employee.setEmployees(employees);
		assertEquals(employees, employee.getEmployees());
		Employee addEmployee = employee.addEmployee(employee);
		assertEquals(addEmployee, employee.removeEmployee(employee));
	}

	@Test
	public void testGetSetAddRemoveSkills() {
		List<Skill> skills = new ArrayList<>();
		employee.setSkills(skills);
		assertEquals(skills, employee.getSkills());
		Skill skill = new Skill();
		employee.addSkill(skill);
		assertEquals(skill, employee.removeSkill(skill));
	}

}
