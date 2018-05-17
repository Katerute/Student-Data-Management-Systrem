package main;

import java.util.*;

public class TaughtStudent extends AbstractStudent{

	//variable initialization
	private List<Module> moduleList = new ArrayList<Module>();

	//constructor
	public TaughtStudent(Name name, Date dateOfBirth){
		super(name,dateOfBirth);//call the constructor of a super class
	}	

	//get method for moduleList
	public List<Module> getModuleList() {
		return moduleList;
	}

	//set method for moduleList
	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}
	
	//boolean method which checks whether the student is registered correctly
	public boolean correctlyRegistred() {
		if(moduleList!= null)
		{
			return (true && super.correctlyRegistred());
		}
		else {
			//in order to know why the student is registered incorrectly --> write the reason
			System.out.println("Module list is empty");
			return false;
		}
	}
}
