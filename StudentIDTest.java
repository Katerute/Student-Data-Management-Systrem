package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.StudentID;

public class StudentIDTest {

	private StudentID id;
	
	@Test(expected = NullPointerException.class)
	public void testGetInstanceFail_TooManyTimes(){
		for(int i=0; i<260000; i++)
		{
			id = StudentID.getInstance();
		}
	}	

}
