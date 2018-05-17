package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Name;

public class NameTest {
	
	@Test
	public void testGetFirstName() {
		String firstName="Will";
		Name name = new Name(firstName, "Smith");
		assertEquals(firstName, name.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		String lastName="Smith";
		Name name = new Name("Will", lastName);
		assertEquals(lastName, name.getLastName());
	}
	
	@Test
	public void testToString() {
		Name name = new Name("Will", "Smith");
		assertEquals("Will Smith", name.toString());
	}

}
