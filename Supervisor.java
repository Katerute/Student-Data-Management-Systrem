package main;

public final class Supervisor {
	
	//initialization of variables
	private Name supervisorName;
	private String department;
	
	//constructor
	public Supervisor(Name supervisorName, String department){
		
		//check whether supervisorName and department are valid. Throw an exception if the information passed is invalid
		if(supervisorName == null)
		{
			throw new IllegalArgumentException("Empty supervisor's name");
		}
		if(department.length() == 0)
		{
			throw new IllegalArgumentException("Empty department");
		}
		
		this.supervisorName = supervisorName;
		this.department = department;
	}

	//Getters for private variables
	public Name getSupervisorName() {
		return supervisorName;
	}

	public String getDepartment() {
		return department;
	}
	
	//toString method returns a string representation of the object
	public String toString(){
		return supervisorName.getFirstName()+" "+supervisorName.getLastName()+" ("+department+")";
	}
}
