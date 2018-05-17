package main;

import java.util.*;

public final class StudentID {//immutable and unique

	//initialization of variables
	private final String letter;
	private final String digits;
	
	private static int count=0;//using this variable letter and digits in StudentID will be incremented creating unique IDs
	
	//the map IDs is static --> will store unique StudentID objects
	private static final Map<String, StudentID> IDs = new HashMap<String, StudentID>();
	
	//private constructor - accessible only within this class
	private StudentID(String letter, String digits){
		
		//check whether letter and digits are valid. Throw an exception if not.
		if(letter.length() == 1)
		{
			this.letter=letter;
		}
		else throw new IllegalArgumentException("Invalid letter");
		
		if(digits.length() == 4)
		{
			this.digits=digits;
		}
		else throw new IllegalArgumentException("Invalid digits");

	}
	
	//factory - creates unique objects of the class
	public static StudentID getInstance()
	{
		
		if(count<259999){//the max number of IDs that can be issued is 260000 (26 letters in the alphabet * 10^4), so if more is required --> throw an exception
			count++;// every time getInstance()  is called the counter is incremented
			int digits = count % 10000;//remainder - 4 digit number
			char letter = (char)('a'+(count/10000));//increments letter when the previous was used 9999 times
		
			//check whether the value is in a map
			final String k = String.format("%s%04d",letter,digits);
			if (!IDs.containsKey(k)){
				
				IDs.put(k, new StudentID(String.format("%s",letter), String.format("%04d",digits)));//if not --> put the value to the map
			}
			
			return IDs.get(k);
		}
		else{
				throw new NullPointerException("There is no more available IDs");
		}
	}
	
	// Getters for variables letter and digits to access them separately
	public String getLetter() {
		return letter; 
	}
	
	public String getDigits() {
		return digits; 
	}
	
	//a String representation of StudentID
	public String toString(){
		return letter+digits;
	}
}
