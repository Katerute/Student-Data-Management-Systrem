package test;

import static org.junit.Assert.*;

import java.util.*;

//import junit.framework.Test;

import org.junit.Before;
import org.junit.Test;

import main.*;

public class ManageStudentTest {
	
	private Student undergrad;
	private Student postgrad;
	private Date theDate16;
	private Date theDate18;
	private Date theDate21;
	Name n;
	List<Module> newModList = new ArrayList<Module>();
	List<Module> newModListFail = new ArrayList<Module>();
	Supervisor newSupervisor;
	
	@Before
	public void setUp(){
		
		n = new Name("John", "Smith");
		
		// AGE = 21
		Calendar myCal = Calendar.getInstance();
		myCal.set(Calendar.YEAR, 1997);
		myCal.set(Calendar.MONTH, 1);
		myCal.set(Calendar.DATE, 11);
		theDate21 = myCal.getTime();
		
		// AGE = 18
		Calendar myCal2 = Calendar.getInstance();
		myCal2.set(Calendar.YEAR, 2000);
		myCal2.set(Calendar.MONTH, 1);
		myCal2.set(Calendar.DATE, 11);
		theDate18 = myCal2.getTime();
		
		// AGE = 16
		Calendar myCal3 = Calendar.getInstance();
		myCal3.set(Calendar.YEAR, 2002);
		myCal3.set(Calendar.MONTH, 1);
		myCal3.set(Calendar.DATE, 11);
		theDate16 = myCal3.getTime();
		
		//Modules for newModList = 100 credits
		Module mod1=new Module("Module 1","M1",45);
		Module mod2=new Module("Module 2","M2",20);
		Module mod3=new Module("Module 3","M3",35);
		
		newModList.add(mod1);
		newModList.add(mod2);
		newModList.add(mod3);
		
		// Module to add. Credits>180 (and so >120)
		Module new_mod1=new Module("NewModule 1","M1",20);
		Module new_mod2=new Module("NewModule 2","M2",30);
		Module new_mod3=new Module("NewModule 3","M3",40);
		
		
		newModListFail.addAll(newModList);
		newModListFail.add(new_mod1);
		newModListFail.add(new_mod2);
		newModListFail.add(new_mod3);
		
		Name supName=new Name("Ted","Mosby");
		newSupervisor = new Supervisor(supName,"Architecture");
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateStudent_NullName(){
		undergrad = new Undergrad(null,theDate18);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateStudent_NullDob(){
		undergrad = new Undergrad(n,null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateStudent_tooYoung(){
		undergrad = new Undergrad(n,theDate16);
	}
	
	@Test
	public void testRegisterStudent_OldEnoughtForUndergrad() {
		// This method should successfully register an Undergrad student because the student is old enough(age>=17)
		undergrad = new Undergrad(n,theDate18);
		ManageStudents.registerStudent((AbstractStudent) undergrad);
		assertTrue(ManageStudents.studentData.containsKey(undergrad.getId()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRegisterStudent_TooYoungForPostgrad() {
		postgrad = new ResearchPostgrad(n,theDate18);
		ManageStudents.registerStudent((AbstractStudent) postgrad);
	}
	
	@Test
	public void testRegisterStudent_OldEnoughtForPostgrad() {
		postgrad = new ResearchPostgrad(n,theDate21);
		ManageStudents.registerStudent((AbstractStudent) postgrad);
		assertTrue(ManageStudents.studentData.containsKey(postgrad.getId()));
	}
	
	@Test
	public void testAmendStudentData_UndergradStudent() {
		// This method should successfully amends taught student's module list
		undergrad = new Undergrad(n,theDate18);		
		ManageStudents.registerStudent((AbstractStudent) undergrad);
		ManageStudents.amendStudentData(undergrad.getId(),newModList);
		assertEquals(newModList,(( (TaughtStudent) ManageStudents.studentData.get(undergrad.getId())).getModuleList()));
	}
	
	@Test
	public void testAmendStudentData_UndergradStudent_Fail() {
		// This method should fail to amend the module list because the sum of credits for all modules in the new module list is greater than an undergrad can have
		undergrad = new Undergrad(n,theDate18);		
		ManageStudents.registerStudent((AbstractStudent) undergrad);
		ManageStudents.amendStudentData(undergrad.getId(),newModListFail);
		assertNotEquals(newModListFail,(((TaughtStudent) ManageStudents.studentData.get(undergrad.getId())).getModuleList()));
	}
	
	@Test
	public void testAmendStudentData_PostgradStudent() {
		// This method should successfully amends taught student's module list
		postgrad = new TaughtPostgrad(n,theDate21);		
		ManageStudents.registerStudent((AbstractStudent) postgrad);
		ManageStudents.amendStudentData(postgrad.getId(),newModList);
		assertEquals(newModList,(((TaughtStudent) ManageStudents.studentData.get(postgrad.getId())).getModuleList()));
	}
	
	@Test
	public void testAmendStudentData_PostgradStudent_Fail() {
		// This method should fail to amend the module list because the sum of credits for all modules in the new module list is greater than postgrad can have
		postgrad = new TaughtPostgrad(n,theDate21);		
		ManageStudents.registerStudent((AbstractStudent) postgrad);
		ManageStudents.amendStudentData(postgrad.getId(),newModListFail);
		assertNotEquals(newModListFail,(((TaughtStudent) ManageStudents.studentData.get(postgrad.getId())).getModuleList()));
	}
	
	@Test
	public void testAmendStudentData_ResearchPostgrad() {
		// This method should successfully amends research student's supervisor
		postgrad = new ResearchPostgrad(n,theDate21);		
		ManageStudents.registerStudent((AbstractStudent) postgrad);
		ManageStudents.amendStudentData(postgrad.getId(),newSupervisor);
		assertEquals(newSupervisor,(((ResearchPostgrad) ManageStudents.studentData.get(postgrad.getId())).getSupervisor()));
	}
	
	@Test
	public void testNumberOfCreditsInModuleList(){
		assertEquals(100,ManageStudents.numberOfCreditsInModuleList(newModList));
	}
	
	@Test
	public void testCurrentNumberOfCredits(){
		undergrad= new Undergrad(n, theDate18);
		ManageStudents.registerStudent((AbstractStudent) undergrad);
		
		assertEquals(0,ManageStudents.currentNumberOfCredits((TaughtStudent) undergrad));
		
		ManageStudents.amendStudentData(undergrad.getId(),newModList);
		assertEquals(100,ManageStudents.currentNumberOfCredits((TaughtStudent) undergrad));
	}
	
	
	@Test
	public void testNumberOfStudents(){
		
		//Within this class there were registred 4 Undergrad students,2 ResearchPostgrads and 2 TaughtPostgrads
		assertEquals(4,ManageStudents.numberOfStudents("Undergrad"));
		assertEquals(2,ManageStudents.numberOfStudents("ResearchPostgrad"));
		assertEquals(2,ManageStudents.numberOfStudents("TaughtPostgrad"));
	}
	
	@Test
	public void testTerminateStudent(){
		undergrad= new Undergrad(n, theDate18);
		ManageStudents.registerStudent((AbstractStudent) undergrad);
		assertTrue(ManageStudents.studentData.containsKey(undergrad.getId()));
		
		ManageStudents.terminateStudent(undergrad.getId());
		assertFalse(ManageStudents.studentData.containsKey(undergrad.getId()));
	}
	
}
