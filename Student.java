package main;

import java.util.Date;

public interface Student {// Student interface
	
	public Name getName();
	public Date getDateOfBirth();
	public SmartCard getSmartCard(); 
	public StudentID getId();
		
	public int getAge();
}
