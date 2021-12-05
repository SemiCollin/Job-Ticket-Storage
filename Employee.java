/* 
	File Name: Employee.java
	Name: Collin Kwan
	Class: ICS4U1-01
	Date: Nov 07, 2020
	Description: *CULMINATING PROJECT* -- payroll database for part time/full time staff
				-- Abstract class to be extended by FullTimeStaff and PartTimeStaff. Includes general definitions for methods to be used, instance fields, a toString method for printing
					basic employee info, and constructors.
*/
//	-----------------------------------------------------------------------------------------------------------------------------------
/*
	Change Log:
	> Nov 08 ---- created class;
				> abstract methods
				> instance fields
				> Constructor(int , String , String)
				> toString method
	> Nov 10 ---- 
				> Constructor(Employee) -- create a copy of another employee
	> Nov 16 ----
				> Documentation
*/
//	-----------------------------------------------------------------------------------------------------------------------------------

abstract class Employee {
	//initialize protected instance fields to be accessed by subclasses
	protected int employeeNum;
	protected String firstName , lastName;
	
	//Employee constructor that creates an Employee object with the given parameters for employee number, first name, and last name
	//@param int employeeNumIn -- integer to represent the employee's identification number
	//@param String firstNameIn -- String to use as the employee's first name
	//@param String lastNameIn -- String to use as the employee's last name
	// *CONSTRUCTOR* ------------------------------------------------------------------------------------------------------------// 
	public Employee (int employeeNumIn , String firstNameIn , String lastNameIn) {
		employeeNum = employeeNumIn; //set the value of the employeeNum field equal to the first parameter passed
		firstName = firstNameIn;	//set the value of the firstName field equal to the second parameter passed
		lastName = lastNameIn;	//set the value of the lastName field equal tot he third parameter passed
	}
	
	//Employee constructor that creates an Employee object with the same properties as another given Employee object
	//@param Employee other -- second employee to copy fields
	// *CONSTRUCTOR* ----------------------------------------------------------------------------------------------// 
	public Employee (Employee other) {
		this.employeeNum = other.employeeNum;	//copy the employeeNum field of the employee parameter
		this.firstName = other.firstName;	//copy the firstName field of the employee parameter
		this.lastName = other.lastName;	//copy the lastName field of the employee parameter
	}
	
	//toString method to return the calling Employee's fields as a string; allows for printing
	//*no parameters*
	//return String -- instance fields of the calling employee object returned as a string to allow for printing
	//--------------------------------------------------------------------------------------------------------// 
	public String toString () {
		return this.employeeNum + " -- " + this.lastName + ", " + this.firstName;	//return the fields of the calling employee as a string formatted with specific separating characters
	}
	
	//Check if two Employees have the same first name
	//Employee other -- second Employee to perform comparisons with
	//return boolean -- true if they are the same, false if they aren't
	//--------------------------------------------------------------------------------------------------------// 
	public boolean compareFirst (Employee other) {
		//check the first names of both employees
		if (this.firstName.compareTo(other.firstName) == 0) {
			return true;	//return true if they are the same
		} else {
			return false;	//return false otherwise
		}
	}
	
	//Check if two Employees have the same last name
	//Employee other -- second Employee to perform comparisons with
	//return boolean -- true if they are the same, false if they aren't
	//--------------------------------------------------------------------------------------------------------// 
	public boolean compareLast (Employee other) {
		//check the last names of both employees
		if (this.lastName.compareTo(other.lastName) == 0) {
			return true;	//return true if they are the same
		} else {
			return false;	//return false otherwise
		}
	}
	
	//Compare the employee numbers of two employees and return the one with the lower number
	//Employee other -- second Employee to perform comparisons with
	//return Employee -- return the employee with the lower employee number
	//--------------------------------------------------------------------------------------------------------// 
	public Employee compareEmployeeNum (Employee other) {
		//check if the calling employee has a lower number than the passed employee
		if (this.employeeNum < other.employeeNum) {
			return this;	//return the calling employee if so
		} else {
			return other;	//return the passed employee otherwise
		}
	}
	
	//abstract method initialization; to be extended in sub classes
	abstract String fullToString ();	//return all information belonging to the calling employee for printing
	abstract String infoToString ();	//return any relevant information not returned by the toString method belonging to the calling employee for printing
	abstract double pay ();		//calculate and return the amount of the calling employee's monthly pay
	abstract void updateSickDays (double sickDaysTaken);	//update the amount of sick days taken & sick days left for the calling employee
	abstract void resetSickDay ();	//reset the sick days take and left for the calling employee (days left to max, days taken to 0)
	abstract void printPayStub ();	//print out the calling employee's pay stub
}
