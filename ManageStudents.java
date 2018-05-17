package main;

import java.io.*;
import java.util.*;

public class ManageStudents {

	//variable initialization
	public static Map<StudentID,AbstractStudent> studentData = new HashMap<StudentID,AbstractStudent>();//stores all registred students
	public static List<Module> moduleData = new ArrayList<Module>();//stores modules read from file
	public static List<Supervisor> supervisorData = new ArrayList<Supervisor>();//stores supervisors read from file
	
	private static final String moduleFileName = "moduleDataFile.txt";//the name of the file with module information
	private static final String supervisorFileName = "supervisorDataFile.txt";//the name of the file with supervisor information
	
	//default values which are used during registration of a new student
	private final static Supervisor defaultSupervisor = new Supervisor(new Name("Neil","Speirs"),"Computing");
	private final static List<Module> defaultEmptyModuleList = new ArrayList<Module>();
	
	//method which adds a student to the database of all University students
	public static void registerStudent(AbstractStudent student){

		/** If the student object was created, it means that the person is old enough to become a student(undergraduate).
		//  To be registred as a postgraduate student the age of the person must be >=20  | 
		//                                                                  			  V                     */
		
		if(student.getClass().getSimpleName().contains("Postgrad") && student.getAge()<20)
		{
			//throw an exception if a postgraduate student passed is younger than 20 --> such student is not registered
			throw new IllegalArgumentException("A postgraduate student has to be at least 20 years old to be registred");
		}
		else {
				if (!studentData.containsKey(student.getId())){//if there is no such student in the database --> allocate with student id and smart cart and add to the database of University students
				
					student.setId(StudentID.getInstance());//allocates the student with a unique ID
					student.setSmartCard(SmartCard.getInstance(student.getName(), student.getDateOfBirth()));//allocates a student with a unique Smart Card
					
					//allocate a new research postgraduate student with a default supervisor/a new taught student with a default module list
					if(student instanceof ResearchPostgrad)
					{
						((ResearchPostgrad) student).setSupervisor(defaultSupervisor);//default
					}
					else if(student instanceof TaughtStudent)
					{
						((TaughtStudent) student).setModuleList(defaultEmptyModuleList);//default - empty module list
					}
					studentData.put(student.getId(),student);//add to the database of University students
				}	
			}
	}
	
	//method amends a module list for Taught Students (undergrads or taught postgrads)
	public static void amendStudentData(StudentID stID, List<Module> newModuleList){
		
		//amend only if the total number of credits in a new module list is <= than a maximum permitted
		if( ( studentData.get(stID) instanceof Undergrad && numberOfCreditsInModuleList(newModuleList)<=Undergrad.getCREDITS() ) || ( studentData.get(stID) instanceof TaughtPostgrad && numberOfCreditsInModuleList(newModuleList)<=TaughtPostgrad.getCREDITS() )){			
			((TaughtStudent) studentData.get(stID)).setModuleList(newModuleList);
		}
	}

	//method amends supervisor for Research students
	public static void amendStudentData(StudentID stID, Supervisor newSupervisor){

		((ResearchPostgrad) studentData.get(stID)).setSupervisor(newSupervisor);
	}

	//method adds modules to taught student's module list
	public static void addModule(TaughtStudent student, Module newModule){
		//add a module to taught student's module list ONLY if a total number of credits doesn't transcend the maximum
		int creditNumber=currentNumberOfCredits(student);
		if((student instanceof Undergrad && (creditNumber+newModule.getNumberOfCredits())<Undergrad.getCREDITS())||student instanceof TaughtPostgrad && (creditNumber+newModule.getNumberOfCredits())<TaughtPostgrad.getCREDITS())
		{
			((TaughtStudent) studentData.get(student.getId())).getModuleList().add(newModule);
		}
	}
	
	//method calculates the number of credits in the module list passed
	public static int numberOfCreditsInModuleList(List<Module> moduleList) {
		int creditNumber=0;
		for(Module mod : moduleList)
		{
			//access the field numberOfCredits of each Module object in the moduleList list and calculate total amount of credits
			creditNumber += mod.getNumberOfCredits();
		}
		//return total amount of credits
		return creditNumber;
	}

	//method calculates a current amount of credits in the module list of a passed student
	public static int currentNumberOfCredits(TaughtStudent student) {
		return numberOfCreditsInModuleList(student.getModuleList());
	}
	
	//method read module information from appropriately defined file
	public static void readModuleInfoFromFile() throws FileNotFoundException{
		
		Scanner inFile = new Scanner(new FileReader("src/main/resources/"+moduleFileName));
		while(inFile.hasNextLine()){//while there is more info in the file (one line - one module)
			String inputLine = inFile.nextLine();
			Scanner lineScanner= new Scanner(inputLine);
			String[] tokens = lineScanner.nextLine().split(", ");
			lineScanner.close();//close Scanner
				
			//create a new Module object using the information read
			Module module = new Module(tokens[0],tokens[1],Integer.parseInt(tokens[2]));//tokens[0]=moduleCode, tokens[1]=moduleName, tokens[2]=numberOfCredits
			moduleData.add(module);//add the module to the database of modules
		}
		inFile.close();//close Scanner
	}
	
	public static void readSupervisorInfoFromFile() throws FileNotFoundException{
		
		Scanner inFile = new Scanner(new FileReader("src/main/resources/"+supervisorFileName));
		while(inFile.hasNextLine()){//while there is more info in the file (one line - one supervisor)
			String inputLine = inFile.nextLine();
			Scanner lineScanner= new Scanner(inputLine);
			String[] tokens = lineScanner.nextLine().split(", ");
			lineScanner.close();//close Scanner
			
			//create a new Supervisor object using the information read
			Name supervisorName = new Name(tokens[0],tokens[1]);//tokens[0]=supervisor's name, tokens[2]=supervisor's last name
			Supervisor supervisor = new Supervisor(supervisorName,tokens[2]);//tokens[2]=department
			supervisorData.add(supervisor);//add the supervisor to the database of supervisors
		}
		inFile.close();//close Scanner
	}
	
	//method calculates the number of students of a particular type who is registered in the University database of students
	public static int numberOfStudents(String typeOfStudent){
		
		int numberOfStudents=0;
		
		for(Student st: studentData.values()){//look through the database to find students of a particular type --> count them
			if(st.getClass().getSimpleName().equals(typeOfStudent)){
				numberOfStudents++;
			}
		}
		//return the number of registered students of a particular type
		return numberOfStudents;
	}
	
	//method removes the student from the University database of students
	public static void terminateStudent(StudentID stID){
		studentData.remove(stID);
	}
	
	public static void main(String[] args){
		 
//      !!!!  I was not sure what should have been tested using JUnit and what within the main method, 
//             so I decided to demonstrate in the main method that the things I did not test in JUnit are working correctly(reading from file, correctlyRegistred() methods, uniqueness provided by static factories)
		
		
		/**----------------------------- Demonstrate that reading from file is working for both modules and supervisors ----------------------*/
		
		try {
			readModuleInfoFromFile();
		} 
		catch (FileNotFoundException e) {
			
				e.printStackTrace();
		}
		
		//expected output -> a list of modules read from  the file
		System.out.println("List Of Modules from file: ");
		for(int i=0; i<moduleData.size(); i++)
		{
			System.out.println("\t\t"+moduleData.get(i));
		}
		
		try {
			readSupervisorInfoFromFile();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		//expected output -> a list of supervisors read from  the file
		System.out.println("List Of Supervisors from file: ");
		for(int i=0; i<supervisorData.size(); i++)
		{
			System.out.println("\t\t"+supervisorData.get(i));
		}
		
		/** ---------------------- Demonstrate that correctlyRegistred() works for every type of student (was overwritten down the hierarchy)---------------------------------------*/
		
		//create Name objects
		Name name = new Name("Nickolas", "Brown");
		Name name2 = new Name("Nickolas", "Cage");
		
		//Create the date
		Calendar myCal = Calendar.getInstance();
		myCal.set(Calendar.YEAR, 1996);
		myCal.set(Calendar.MONTH, 12);
		myCal.set(Calendar.DATE, 7);
		Date theDate = myCal.getTime();
		
		//create students of different types
		Student undergrad = new Undergrad(name,theDate);
		Student tPostgrad = new TaughtPostgrad(name, theDate);
		Student rPostgrad = new ResearchPostgrad(name,theDate);
		
		//register all of them
		registerStudent((AbstractStudent) undergrad);
		registerStudent((AbstractStudent) tPostgrad);
		registerStudent((AbstractStudent) rPostgrad);
		
		//expected output -> "false" + explanation  because the list of modules is now empty (when a student is registered the list of modules is set to a default which is an empty list). addModule() and amentStudent() methods are tested in JUnit (ManageStudentsTest)
		System.out.println("Undergraduate student is registred correctly: "+((AbstractStudent) undergrad).correctlyRegistred());
		System.out.println("Taught postgraduate student is registred correctly: "+((AbstractStudent) tPostgrad).correctlyRegistred());
		
		
		/*  Demonstrate that after adding a list with an appropriate number of credits the method correctlyRegistred() returns true  */
		
		//Create a list with appropriate number of modules, so that the number of credits = 120
		List<Module> modList = new ArrayList<Module>();
		Module mod1=new Module("Module 1","M1",45);
		Module mod2=new Module("Module 2","M2",20);
		Module mod3=new Module("Module 3","M3",35);
		Module mod4=new Module("Module 4","M4",20);
				
		modList.add(mod1);
		modList.add(mod2);
		modList.add(mod3);
		modList.add(mod4);
		
		amendStudentData(undergrad.getId(), modList);
		
		//expected output -> true(now the total number of credits is 120)
		System.out.println("After amending --> Undergraduate student is registred correctly: "+((AbstractStudent) undergrad).correctlyRegistred());

		
		//expected output -> true 
		System.out.println("Research postgraduate student is registred correctly: "+((AbstractStudent) rPostgrad).correctlyRegistred());
		
		
		/**------------------------ Demonstrate that a smart card is unique for every student --------------*/
		for(int i=0; i<5; i++)
		{
			//expected output --> 5 outputs will be the same ( factory doesn't create a new smart card object for the same student, so if the static map of smart cards contains a smart card for the passed student -> just returns a smart card for an existing Student object )
			System.out.println(SmartCard.getInstance(name, theDate));
		}
		//expected output -> new smart card for a new student
		System.out.println(SmartCard.getInstance(name2, theDate));
		
		/**------------------------ Demonstrate that every student number is unique --------------*/
		for(int i=0; i<5; i++)
		{
			//expected output -> 5 different (unique) outputs
			System.out.println(StudentID.getInstance());
		}
		
		/**------------------------ Demonstrate that every smart card number is unique --------------*/
		for(int i=0; i<5; i++)
		{
			//expected output -> 5 different (unique) outputs
			System.out.println(SmartCardNumber.getInstance(name));
		}
		
	}
}
          