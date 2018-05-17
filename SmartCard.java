package main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import main.Name;
import main.SmartCard;
import main.SmartCardNumber;

public final class SmartCard {//immutable and unique
	
	//initialization of variables
	private final Name studentName;
	private final Date dateOfBirth;
	private final Date dateOfIssue;
	private final SmartCardNumber numberSC;
	
	//the map SMs is static --> will store unique SmartCard objects
	private static final Map<String, SmartCard> SMs = new HashMap<String, SmartCard>();
	
	//private constructor
	private SmartCard(Name studentName,Date dateOfBirth){
		//check whether student name and student's date of birth passed are valid. Throw an exception if not.
		if(studentName==null)
		{
			throw new IllegalArgumentException("Empty student's name");
		}
		
		if(dateOfBirth==null)
		{
			throw new IllegalArgumentException("Empty student's date of birth");
		}
		
		this.studentName = new Name(studentName.getFirstName(),studentName.getLastName());
		this.dateOfBirth= new Date(dateOfBirth.getTime());
		this.dateOfIssue = new Date();
		this.numberSC = SmartCardNumber.getInstance(studentName);
	}

	//factory - creates unique objects of the class
	public static SmartCard getInstance(Name studentName,Date dateOfBirth){
		
		//check whether the value is in a map
		final String k = studentName.getFirstName()+studentName.getLastName();
		if (!SMs.containsKey(k)){
			
			SMs.put(k, new SmartCard(studentName,dateOfBirth));//if not --> put the value to the map
		}
		return SMs.get(k);
	}
	
	//Getters for private variables
	public Name getStudentName() {
		return new Name(studentName.getFirstName(),studentName.getLastName());
	}

	public Date getDateOfBirth() {
		return (Date) dateOfBirth.clone();
	}

	public Date getDateOfIssue() {
		return (Date) dateOfIssue.clone();
	}

	public SmartCardNumber getNumberSC() {
		return numberSC;
	}

	//toString method returns a string representation of the object
	public String toString() {
		return "SmartCard [studentName=" + studentName + ", dateOfBirth=" + dateOfBirth + ", dateOfIssue=" + dateOfIssue
				+ ", numberSC=" + numberSC + "]";
	}
}
