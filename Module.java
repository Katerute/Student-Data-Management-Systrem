package main;

//import main.java.Module;

public class Module {
	
	//initialization of variables
	private String moduleName;
	private String moduleCode;
	private int numberOfCredits;
	
	//constructor
	public Module(String moduleCode, String moduleName, int numberOfCredits){
		
		setModuleName(moduleName);
		setModuleCode(moduleCode);
		setNumberOfCredits(numberOfCredits);
	}
	
	//Set methods
	public void setModuleName(String moduleName) {//set module's name
		
		//check whether moduleName is valid. Throw an exception if the information passed is invalid
		if(moduleName.length() == 0)
		{
			throw new IllegalArgumentException("Empty module name");
		}
		
		this.moduleName = moduleName;
	}

	public void setModuleCode(String moduleCode) {//set module's code
		
		//check whether moduleCode is valid. Throw an exception if the information passed is invalid
		if(moduleCode.length() == 0)
		{
			throw new IllegalArgumentException("Empty module code");
		}
		
		this.moduleCode = moduleCode;
	}

	public void setNumberOfCredits(int numberOfCredits) {//set the number of credits for a module
		
		//check whether number of credits for one module is valid. Throw an exception if the information passed is invalid
		if(numberOfCredits <= 0)//number of credits cannot be negative
		{
			throw new IllegalArgumentException("Negative number of credits for the module");
		}
		else if(numberOfCredits > 50)//assume that 50 is the max possible number of credits for one module
		{
			throw new IllegalArgumentException("The number of credits for one module cannot be greater than 50");
		}
		
		this.numberOfCredits = numberOfCredits;
	}
	
	//Getters for private variables
	public String getModuleName() {
		return moduleName;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public int getNumberOfCredits() {
		return numberOfCredits;
	}
	
	//toString method returns a string representation of the object
	public String toString(){
		return "Module info: "+moduleCode+", "+moduleName+", "+numberOfCredits;
	}
	
	//method returns a value of a String object passed -> returns a Module object
	public static Module valueOf(String module) {
		final String[] parts = module.split(", ");
		
		final String moduleName = parts[0].equals("null") ? null : parts[0];	
		final String moduleCode = parts[1].equals("null")? null : parts[1];
		
		final int credits = Integer.parseInt(parts[2]);
		
		return new Module(moduleName, moduleCode, credits);
	}
}
