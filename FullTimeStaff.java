/* 
	File Name: Employee.java
	Name: Collin Kwan
	Class: ICS4U1-01
	Date: Nov 07, 2020
	Description: *CULMINATING PROJECT* -- payroll database for part time/full time staff
				-- Subclass that extends abstract class Employee.java. Performs functions pertaining to full time staff; [20 sick days are given,
					salary is given annually]
*/
//	-----------------------------------------------------------------------------------------------------------------------------------
/*	
 	Change Log:
	> Nov 08 ---- created class;
				> class constant yearlySickDays
				> instance fields
				> Constructor(int , String , String , double)
				> extend pay() method
				> extend updateSickDays(double) method
				> extend resetSickDays() method
				> extend printPayStub() method
				> toString method
	> Nov 10 ---- 
				> Constructor(FullTimeStaff) -- create a copy of another full time staff
				> getSickDaysLeft() accessor method
				> getYearlySalar() accessor method
	> Nov 11 ---- 
				> infoToString() -- return any other relevant information for a full time staff; individual yearly salary
	> Nov 16 ----
				> Documentation
*/
//  -----------------------------------------------------------------------------------------------------------------------------------

//subclass FullTimeStaff that extends the abstract class Employee
public class FullTimeStaff extends Employee{
	//initializing private instance fields
	private static final int yearlySickDays = 20;	//universal amount of sick days permitted for full time staff
	private double sickDaysLeft;
	private double yearlySalary;
	private double sickDaysTaken;
	
	//*ACCESSOR METHOD* for private field sickDaysTaken
	//*no parameters*
	//return double -- return the value of sickDaysTaken
	//-------------------------------------------------// 
	public double getSickDaysTaken () {
		return sickDaysTaken;	//return the value of private field sickDaysTaken
	}
	
	//*ACCESSOR METHOD* for the private field sickDaysLeft
	//*no parameters*
	//return double -- return the value of sickDaysLeft
	//------------------------------------------------// 
	public double getSickDaysLeft () {
		return sickDaysLeft;	//return the value of private field sickDaysLeft
	}
	
	//*ACCESSOR METHOD* for the private field yearlySalary
	//*no parameters*
	//return double -- return the value of yearlySalary
	//------------------------------------------------// 
	public double getYearlySalary () {
		return yearlySalary;	//return the value of private field yearlySalary
	}
	
	//FullTimeStaff constructor that creates a FullTimeStaff object using the given parameters
	//@param int employeeNum -- integer to represent the employee's identification number
	//@param String firstNameIn -- String to use as the employee's first name
	//@param String lastNameIn -- String to use as the employee last name
	//@param double yearlySalaryIn -- double to use as the full time staff's yearly salary
	//@param double sickDaysIn -- double to use as the amount of sick days taken by the full time staff, min. 0.5
	// *CONSTRUCTOR* -----------------------------------------------------------------------// 
	public FullTimeStaff (int employeeNumIn , String firstNameIn , String lastNameIn , double yearlySalaryIn , double sickDaysIn) {
		super (employeeNumIn , firstNameIn , lastNameIn);	//use the Employee constructor to initialize the fields employee number, first name and last name using parameters passed with this constructor
		yearlySalary = yearlySalaryIn;	//set the value of the yearlySalary instance field equal to the fourth parameter passed
		sickDaysTaken = sickDaysIn;	//set the value of the sickDaysTaken instance field equal to the fifth parameter passed
	}
	
	//FullTimeStaff constructor that creates an FullTimeStaff object with the same properties as another given FullTimeStaff object
	//@param FullTimeStaff other -- second full time staff to copy fields
	// *CONSTRUCTOR* ----------------------------------------------------------------------------------------------//
	public FullTimeStaff (FullTimeStaff other) {
		super(other);	//use the Employee constructor to copy the fields of for the employee number, first name and last name using the parameter passed in this constructor
		this.yearlySalary = other.yearlySalary; //copy the yearlySalary field of the FullTimeStaff parameter
		this.sickDaysLeft = other.sickDaysLeft;	//copy the sickDaysLeft field of the FullTimeStaff parameter
		this.sickDaysTaken = other.sickDaysTaken;	//copy the sickDaysTaken field of the FullTimeStaff parameter
	}
	
	//extended version of the abstract class pay(). Calculates and returns the monthly payment for a FullTimeStaff
	//*no parameters*
	// return double -- return the monthly payment for the calling full time staff as a double
	//----------------------------------------------------------------------------------------------------------//
	double pay () {
		return this.yearlySalary / 12;	//calculate the monthly salary (yearly salary / 12 months) and return it
	}
	
	//extended version of the abstract class updateSickDays(double sickDaysTaken). Updates the amount of sick days taken and left for the calling full time staff
	//@param double sickDaysIn -- amount of sick days taken by the full time staff to be used to update the relevant information
	//void -- change the values of instance fields sickDaysLeft and sickDaysTaken
	//--------------------------------------------------------------------------------------------------------------------------------------------------------//
	void updateSickDays (double sickDaysIn) {
		this.sickDaysTaken += sickDaysIn;	//increase the amount of sick days taken by the calling full time staff by the passed amount of sick days taken
		this.sickDaysLeft = yearlySickDays - this.sickDaysTaken;	//calculate the amount of sick days the calling full time staff has left after updating sickDaysTaken
		
		//check if the calling full time staff has any sick days left to use; max of 20
		if (sickDaysLeft <= 0) {
			System.out.println("Employee has used all of their sick days.");	//print a warning message if so
		}
	}
	
	//extended version of abstract method resetSickDay(). Resets the amount of sick days taken by the calling full time staff to 0 and sick days left to 20
	//*no parameters*
	// void -- set the values of the instance fields sickDaysTaken and sickDaysLeft to 0 and 20 respectively
	//---------------------------------------------------------------------------------------------------------------------------------------------------//
	void resetSickDay () {
		this.sickDaysLeft = 20;	//set the amount of sick days left to 20 for the calling FullTimeStaff
		this.sickDaysTaken = 0;	//set the amount of sick days taken to 0 for the calling FulTimeStaff
	}
	
	//extended version of abstract method printPayStub(). Prints the pay stub for the calling employee, detailing their pay for the month
	//*no parameters*
	// void -- prints the payment info for the month
	//----------------------------------------------------------------------------------------------------------------------------------//
	void printPayStub () {
		PayrollRunner.uiBuffer();	//use the ui buffer from the PayrollRunner class
		System.out.println(this.toString());	//print the calling employee
		System.out.println("This month's payment:");
		System.out.printf("> $%.2f%n" , this.pay());	//print the calling employee's payment for the month
	}

	
	//toString method to return the FullTimeStaff's inherited fields as a string and their title as a full time staff
	//*no parameters*
	// return String -- inherited fields of the calling FullTimeStaff and their title
	//-------------------------------------------------------------------------------------------------------------//
	public String toString () {
		return "Full Time Staff -- " + super.toString();	//return the title as a "Full Time Staff" and inherited fields for the calling FullTimeStaff for printing
	}
	
	//extended version of the abstract method infoToString(). Return any other field not returned by the toString method as a String
	//*no parameters*
	//return String -- the remaining fields which were not returned by the toString method
	//----------------------------------------------------------------------------------------------------------------------------//
	public String infoToString () {
		return " -- " + this.yearlySalary + " -- " + this.sickDaysTaken;	//return the remaining fields (yearlySalary and sickDaysTaken) not returned by toString() as a String for printing
	}
	
	//extended version of the abstract method fullToString(). Return all fields belonging to the calling FullTimeStaff as a String
	//*no parameters*
	// return String -- all relevant fields belonging to the calling FullTimeStaff
	//---------------------------------------------------------------------------------------------------------------------------//
	public String fullToString () {
		return this.toString() + this.infoToString();	//return all relevant fields for the calling FullTimeStaff; inherited and remaining (toString and infoToString)
	}
	
	//compare two FullTimeStaff using the amount of sick days taken by both and return the one with more sick days taken
	// FullTimeStaff other -- second FullTimeStaff to perform comparisons with
	//return FullTimeStaff -- return the FullTimeStaff that has taken more sick days
	//-----------------------------------------------------------------------------------------------------------------//
	FullTimeStaff compareTo (FullTimeStaff other) {
		//check if the calling FullTimeStaff has more sickDaysTaken than the parameter; ensure that the passed FullTimeStaff is not empty
		if (this.sickDaysLeft > other.sickDaysLeft && other != null) {
			return this;	//return the calling object if so
		} else {
			return other;	//return the passed object if not
		}
	}
}
