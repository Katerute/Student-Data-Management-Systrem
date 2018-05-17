package main;


import java.util.*;

public abstract class AbstractStudent implements Student{

	//variable initialization
	private Name name;
	private Date dateOfBirth;
	private StudentID id = null;//while the student is not registered he does not have an ID
	private SmartCard smartCard = null;//while the student is not registered he does not have a Smart Card
	
	//constructor
	public AbstractStudent(Name name, Date dateOfBirth){
				
			//check whether the information passed to the constructor is valid
			if(name == null)
			{
				throw new IllegalArgumentException("Empty name");//if the name passed is empty -> throw an exception
			}
			if(dateOfBirth == null)
			{
				throw new IllegalArgumentException("Empty date of birth");//if the date of birth passed is empty -> throw an exception
			}
			
			//to create a student object the age of a person should be at least 17(an undergraduate --> the age test for being a postgraduate student is done during registration)
			if( calculateAge(dateOfBirth)>=17 && calculateAge(dateOfBirth)<=100){//assume that the maximum age of the student is 100

				this.name=name;
				this.dateOfBirth=dateOfBirth;
			}
			//if the age of person is <17 and >100 he/she cannot be a student -> throw an exception
			else throw new IllegalArgumentException("A person has to be minimum 17 and maximum 100 years old to be a student");
	}
	
	/* Getters for private variables */
	
	public Name getName(){	//method returns student's name
		return name;
	}
	
	//
	public Date getDateOfBirth(){ //method returns student's date of birth
		return dateOfBirth;
	}
	
	public SmartCard getSmartCard(){ //method returns student's smart card
		return smartCard;
	}
	

	public StudentID getId() { // method returns student's ID
		return id;
	}
	
	
	public int getAge(){	//method returns the age of student
		return calculateAge(dateOfBirth);
	}
	
	//method that sets the smart card --> used during registration of a new student
	public void setSmartCard(SmartCard smartCard) {
		this.smartCard = smartCard;
	}
	
	//method which sets student's id --> used during registration
	public void setId(StudentID id) {
		this.id = id;
	}
	
	//method calculates the age given the date of birth
	public int calculateAge(Date dateOfBirth){
		
		//create a Calendar object for date of birth
		Calendar dob = Calendar.getInstance();
		dob.setTime(dateOfBirth);
		
		//split the date between variables day, month and year
		int day = dob.DATE;
		int month = dob.MONTH;
		int year = dob.YEAR;
		
		//create a Calendar object for the current date
		Calendar curDate = Calendar.getInstance();
		
		//calculate the age
		int age = curDate.get(year)-dob.get(year);
		if( dob.get(month) > curDate.get(month) || dob.get(month) == curDate.get(month) && dob.get(day) > curDate.get(day) ){
			age--;
		}
			
		return age;
	}
	
	//boolean method which checks whether the student is registered correctly
	public boolean correctlyRegistred(){
		if(getName()!=null && getDateOfBirth()!=null && getAge()>=17 && getId()!=null && getSmartCard()!=null)
		{
			return true;
		}
		//in order to know 	WHY the student is registered incorrectly --> write the reason
		System.out.println("Some information is empty");
		return false;
	}
	
	//toString method returns a string representation of the object
	public String toString(){
		return "Student: "+name+"( "+dateOfBirth+", ID: "+id+", Smart Card number: "+smartCard.getNumberSC()+"( "+smartCard.getDateOfIssue()+" )";
	}

}

