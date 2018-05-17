package main;

import java.util.*;

public final class Undergrad extends TaughtStudent{

	//constants
	private final static int CREDITS = 120;
	private final int PASS_MARK = 40;

	//constructor
	public Undergrad(Name name, Date dateOfBirth){
		super(name,dateOfBirth);//call the constructor of a super class		
	}
	
	//boolean method which checks whether the student is registered correctly
	public boolean correctlyRegistred() {
		
		int creditNumber=0;
		for(Module mod : getModuleList())
		{
			//access the field numberOfCredits of each Module object in the moduleList list and calculate total amount of credits
			creditNumber += mod.getNumberOfCredits();
		}
		
		if(creditNumber==CREDITS)//check whether there is enough of credits
		{
			return (true && super.correctlyRegistred());
		}
		else {
				//in order to know why the student is registered incorrectly --> write the reason
				System.out.println("The student is registred for less credits than has to. The number of credits now: "+creditNumber+" out of "+CREDITS+". Add modules!");
				return false;
		}
	}
	
	// Getters for private constants
	public static int getCREDITS() {
		return CREDITS;
	}

	public int getPASS_MARK() {
		return PASS_MARK;
	}
}
