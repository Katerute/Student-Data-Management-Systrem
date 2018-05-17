package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.Module;

public class ModuleTest {

	@Test
	public void testGetModuleName() {
		String modName="Programming";
		Module m = new Module("CSC2018", modName,  20);
		assertEquals(modName, m.getModuleName());
	}
	
	@Test
	public void testGetModuleCode() {
		String modCode="CSC2018";
		Module m = new Module(modCode, "Programming",  20);
		assertEquals(modCode, m.getModuleCode());
	}
	
	@Test
	public void testGetNumberOfCredits() {
		int numberOfCredits=20;
		Module m = new Module("CSC2018", "Programming", numberOfCredits);
		assertEquals(numberOfCredits, m.getNumberOfCredits());
	}
	
	@Test
	public void testToString() {
		Module m = new Module("CSC2018", "Programming", 20);
		assertEquals("Module info: CSC2018, Programming, 20", m.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberOfCreditsFail_Negative() {
		int numberOfCredits=-5;
		Module m = new Module("CSC2018", "Programming", numberOfCredits);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNumberOfCreditsFail_MoreThan50() {
		int numberOfCredits=55;
		Module m = new Module("CSC2018", "Programming", numberOfCredits);
	}
	
	@Test
	public void testSetNumberOfCredits() {
		Module m = new Module("CSC2018", "Programming", 20);
		int numberOfCredits=30;
		m.setNumberOfCredits(numberOfCredits);
		assertEquals(numberOfCredits, m.getNumberOfCredits());
	}
	
	@Test
	public void testSetModuleName() {
		Module m = new Module("CSC2018", "Programming", 20);
		String mName="Computer Architecture";
		m.setModuleName(mName);
		assertEquals(mName, m.getModuleName());
	}
	
	@Test
	public void testSetModuleCode() {
		
		Module m = new Module("CSC2018", "Programming", 20);
		String mCode="CSC1022";
		m.setModuleCode(mCode);
		assertEquals(mCode, m.getModuleCode());
	}
	
}
