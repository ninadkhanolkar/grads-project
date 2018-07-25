package com.systems.wissen.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.systems.wissen.model.Address;

public class AddressTest {

	private Address address = null;

	@Before
	public void setUp() {
		address = new Address();
	}

	@Test
	public void testGetSetAddressId() {
		int addressId = 10;
		address.setAddressId(addressId);
		assertEquals(addressId, address.getAddressId());
	}

	@Test
	public void testGetSetCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCountry() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCountry() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStreet() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStreet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetZipcode() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetZipcode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEmployee() {
		fail("Not yet implemented");
	}

}
