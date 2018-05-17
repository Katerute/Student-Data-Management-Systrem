package main;

public final class Name {//should be immutable
	
	//initialization of variables
	private final String firstName;
	private final String lastName;
	
	//constructor
	public Name(String firstName, String lastName){
		
		//check whether firstName and lastName are valid. Throw an exception if the information passed is invalid
		if(firstName.length() == 0)
		{
			throw new IllegalArgumentException("Empty first name");//throw an exception if the first name is empty
		}
		if(lastName.length() == 0)
		{
			throw new IllegalArgumentException("Empty last name");//throw an exception if the last name is empty
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//getters for private variables
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	//toString method returns a string representation of the object
	public String toString(){
		return firstName+" "+lastName;
		
	}
	
	//method returns a value of a String object passed -> returns a Name object
	public static Name valueOf(String name) {
		final String[] parts = name.split(", ");
		
		final String firstName = parts[0].equals("null") ? null : parts[0];	
		final String lastName = parts[1].equals("null")? null : parts[1];
		
		return new Name(firstName, lastName);
	}
}
