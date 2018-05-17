package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.Name;
import main.Supervisor;

public class SupervisorTest {

	
	@Test
	public void testGetSupervisorName() {
		Name name = new Name("Will", "Smith");
		Supervisor s = new Supervisor(name,"Computing");
		assertEquals(name, s.getSupervisorName());
	}
	
	@Test
	public void testGetDepartment() {
		String department="Computing";
		Name name = new Name("Will", "Smith");
		Supervisor s = new Supervisor(name, department);
		assertEquals(department, s.getDepartment());
	}
	
	@Test
	public void testToString() {
		Name name = new Name("Will", "Smith");
		Supervisor s = new Supervisor(name,"Computing");
		assertEquals("Will Smith (Computing)",s.toString());
	}
}
