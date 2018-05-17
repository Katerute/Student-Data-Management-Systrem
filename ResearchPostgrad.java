package main;


import java.util.Date;

public final class ResearchPostgrad extends AbstractStudent{
	
	//variable initialization
	private Supervisor supervisor=null;//while the student is not registered supervisor is not allocated

	//constructor
	public ResearchPostgrad(Name name, Date dateOfBirth){				
		super(name,dateOfBirth);//call the constructor of a super class
	}

	//boolean method which checks whether the student is registered correctly
	public boolean correctlyRegistred() {
		if(supervisor != null)
		{
			return (true && super.correctlyRegistred());
		}
		else {
			//in order to know why the student is registered incorrectly --> write the reason
			System.out.println("Supervisor is not allocated");
			return false;
		}
	}
	
	//getter for a private variable supervisor
	public Supervisor getSupervisor() {
		return supervisor;
	}
	
	//method that sets supervisor
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
}