package main;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import main.Name;
import main.SmartCardNumber;

public final class SmartCardNumber {//unique and immutable

	//initialization of variables
	private final String scNumber;
	
	//the map SM_NUMBERS is static --> will store unique SmartCardNumber objects
	private static final Map<String, SmartCardNumber> SM_NUMBERS = new HashMap<String, SmartCardNumber>();
	
	private static int count = 0;//using this variable serialNumber will be incremented creating unique serialNumbers
	
	//private constructor
	private SmartCardNumber(String scNumber){
	
		//check whether scNumber is valid. Throw an exception if not
		if(scNumber.length() == 0)
		{
			throw new IllegalArgumentException("Invalid smart card number");
		}
		
		this.scNumber=scNumber;
	}
	
	//factory - creates unique objects of the class
	public static SmartCardNumber getInstance(Name stName){
		
		//get the initial of the first name
		char [] nameInitials = stName.getFirstName().toCharArray();
		char nameI = Character.toUpperCase(nameInitials[0]);
		
		//get the initial of the last name
		char [] surnameInitials = stName.getLastName().toCharArray();
		char surnameI = Character.toUpperCase(surnameInitials[0]);	
		
		//get the year of issue
		Date dateOfI = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOfI);
		int year = cal.get(Calendar.YEAR);
		
		//create a serial number
		count++;
		int serialNumber = count;
		
		//check whether the value is in a map
		final String k = Character.toString(nameI) + Character.toString(surnameI) + "-" + Integer.toString(year) + "-" + Integer.toString(serialNumber);
		if (!SM_NUMBERS.containsKey(k)){
			
			SM_NUMBERS.put(k, new SmartCardNumber(k));//if not --> put the value to the map
		}
		return SM_NUMBERS.get(k);
		
	}
	
	//a String representation of smart card number
	public String toString(){
		return scNumber;
	}
	
}
