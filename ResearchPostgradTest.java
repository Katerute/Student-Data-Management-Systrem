package test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import main.AbstractStudent;
import main.ManageStudents;
import main.Name;
import main.ResearchPostgrad;
import main.Student;
import main.Supervisor;

public class ResearchPostgradTest {

	private Student postgrad;
	Date theDate21;
	Name n;
	
	@Before
	public void setUp(){
		
		n = new Name("John", "Smith");
		
		// AGE = 21
		Calendar myCal = Calendar.getInstance();
		myCal.set(Calendar.YEAR, 1997);
		myCal.set(Calendar.MONTH, 1);
		myCal.set(Calendar.DATE, 11);
		theDate21 = myCal.getTime();
		
		postgrad = new ResearchPostgrad(n,theDate21);
		ManageStudents.registerStudent((AbstractStudent) postgrad);
	}
	
	@Test
	public void testSetAndGetSupervisor() {
		Name supName=new Name("Ted","Mosby");
		Supervisor newSupervisor = new Supervisor(supName,"Architecture");
		
		((ResearchPostgrad) postgrad).setSupervisor(newSupervisor);
		assertEquals(newSupervisor, ((ResearchPostgrad) postgrad).getSupervisor());
	}
}
