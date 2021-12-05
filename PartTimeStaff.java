/* 
	File Name: PartTimeStaff.java
	Name: Collin Kwan
	Class: ICS4U1-01
	Date: Nov 07, 2020
	Description: *CULMINATING PROJECT* -- payroll database for part time/full time staff
				-- Subclass of Employee.java. Handles functions pertaining to part time staff; [assigned amount of hours, hourly salary rate,
				deduct pay based on sick days taken (8 hours per day), monthly payment]
*/
//	-----------------------------------------------------------------------------------------------------------------------------------
/*
	Change Log:
	> Nov 08 ---- created class;
				> class constant HOURS_PER_DAY
				> instance fields
				> Constructor(int , String , String , int , double , double)
				> extend pay() method
				> extend updateSickDays() method
				> extend resetSickDays() method
				> extend printPayStub() method
				> toString method
				> compareToSickDay method
	> Nov 10 ----
				> Constructor(PartTimeStaff) -- create a copy of another part time staff
				> getHoursAssigned() accessor method
				> getHourlyRate() accessor method
				> getSickDaysTaken() accessor method
	> Nov 11 ----
				> infoToString method -- return any other relevant information for a part time staff; individual hours assigned, hourly rate, 
				  and sick days taken
	> Nov 16 ----
				> Documentation
*/
//	-----------------------------------------------------------------------------------------------------------------------------------

//subclass PartTimeStaff that extends the abstract class Employee
public class PartTimeStaff extends Employee{
	//initializing private instance fields
	private static final int HOURS_PER_DAY = 8;	//number of hours per work day
	private int numHoursAssigned;
	private double hourlyRate;
	private double sickDaysTaken = 0;
	
	//*ACCESSOR METHOD* for private field hoursAssigned
	//*no parameters*
	//return double -- return the value of hoursAssigned
	//-------------------------------------------------// 
	public int getHoursAssigned () {
		return numHoursAssigned;	//return the value of hoursAssigned
	}
	
	//*ACCESSOR METHOD* for private field hourlyRate
	//*no parameters*
	//return double -- return the value of hourlyRate
	//-------------------------------------------------// 
	public double getHourlyRate () {
		return hourlyRate;	//return the value of hourlyRate
	}
	
	//*ACCESSOR METHOD* for private field sickDaysTaken
	//*no parameters*
	//return double -- return the value of sickDaysTaken
	//-------------------------------------------------// 
	public double getSickDaysTaken () {
		return sickDaysTaken;	//return the value of sickDaysTaken
	}
	
	//PartTImeStaff constructor that creates a PartTimeStaff object using the given parameters
	//@param int employeeNum -- integer to represent the employee's identification number
	//@param String firstNameIn -- String to use as the employee's first name
	//@param String lastNameIn -- String to use as the employee's last name
	//@param int numHoursAssignedIn -- integer amount of hours assigned to the part time staff per month
	//@param double hourlyRateIn -- double to use as the part time staff's hourly pay rate
	//@param double sickDaysIn -- double to use as the amount of sick days taken by the part time staff, min. 0.5
	// *CONSTRUCTOR* -----------------------------------------------------------------------// 
	public PartTimeStaff (int employeeNumIn , String firstNameIn , String lastNameIn , int numHoursAssignedIn , double hourlyRateIn , double sickDaysIn) {
		super(employeeNumIn , firstNameIn , lastNameIn);	//use the Employee constructor to initialize the fields employee number, first name and last name using parameters passed with this constructor
		numHoursAssigned = numHoursAssignedIn;	//set the value of the numHoursAssigned instance field equal to the fourth parameter passed
		hourlyRate = hourlyRateIn;	//set the value of the hourlyRate instance field equal to the fifth parameter passed
		sickDaysTaken = sickDaysIn;	//set the value of the sickDaysTaken instance field equal to the sixth parameter passed
	}
	
	//PartTimeStaff constructor that creates an PartTimeStaff object with the same properties as another given PartTimeStaff object
	//@param PartTimeStaff other -- second part time staff to copy fields
	// *CONSTRUCTOR* ------------------------------------------------------------------------------------------------------------//
	public PartTimeStaff (PartTimeStaff other) {
		super(other);	//use the Employee constructor to copy the fields of for the employee number, first name and last name using the parameter passed in this constructor
		this.numHoursAssigned = other.numHoursAssigned;	//copy the humHoursAssigned of the PartTimeStaff parameter
		this.hourlyRate = other.hourlyRate;	//copy the hourlyRate field of the PartTimeStaff parameter
		this.sickDaysTaken = other.sickDaysTaken;	//copy the sickDaysTaken field of the PartTimeStaff parameter
	}
	
	//extended version of the abstract class pay(). Calculates and returns the monthly payment for a PartTimeStaff
	//*no parameters*
	// return double -- return the monthly payment for the calling full time staff as a double
	//----------------------------------------------------------------------------------------------------------//
	double pay () {
		return (this.numHoursAssigned - (this.sickDaysTaken * HOURS_PER_DAY)) * this.hourlyRate;	//calculates the monthly payment with the hourly rate (considering any sick days) and returns it
	}
	
	//extended version of the abstract class updateSickDays(double sickDaysTaken). Updates the amount of sick days taken for the calling part time staff
	//@param double sickDaysIn -- amount of sick days taken by the part time staff to be used to update the relevant information
	//void -- change the values of instance fields sickDaysLeft and sickDaysTaken
	//--------------------------------------------------------------------------------------------------------------------------------------------------------//
	void updateSickDays (double sickDaysTaken) {
		this.sickDaysTaken += sickDaysTaken;	//add the amount of sick days taken passed to the existing amount of sick days taken
	}
	
	//extended version of abstract method resetSickDay(). Resets the amount of sick days taken by the calling part time staff to 0
	//*no parameters*
	// void -- set the values of the instance field sickDaysTaken to 0
	//---------------------------------------------------------------------------------------------------------------------------//
	void resetSickDay () {
		this.sickDaysTaken = 0;	//set sickDaysTaken to 0; reset
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
	
	//toString method to return the PartTimeStaff's inherited fields as a string and their title as a part time staff
	//*no parameters*
	// return String -- inherited fields of the calling PartTimeStaff and their title
	//-------------------------------------------------------------------------------------------------------------//
	public String toString () {
		return "Part Time Staff -- " + super.toString();	//return the title as a "Part Time Staff" and inherited fields for the calling PartTimeStaff for printing
	}
	
	//extended version of the abstract method infoToString(). Return any other field not returned by the toString method as a String
	//*no parameters*
	//return String -- the remaining fields which were not returned by the toString method
	//----------------------------------------------------------------------------------------------------------------------------//
	public String infoToString () {
		return " -- " + this.numHoursAssigned + " -- " + this.hourlyRate + " -- " + this.sickDaysTaken;	//return the remaining fields (numHoursAssigned, hourlyRate and sickDaysTaken) not returned by toString() as a String for printing
	}
	
	//extended version of the abstract method fullToString(). Return all fields belonging to the calling FullTimeStaff as a String
	//*no parameters*
	// return String -- all relevant fields belonging to the calling FullTimeStaff
	//--------------------------------------------------------------------------------------------------------------------------//
	public String fullToString () {
		return this.toString() + this.infoToString();	//return all relevant fields for the calling PartTimeStaff; inherited and remaining (toString and infoToString)
	}
	
	//compare two PartTimeStaff using the amount of sick days taken by both and return the one with more sick days taken
	// PartTimeStaff other -- second PartTimeStaff to perform comparisons with
	//return FullTimeStaff -- return the PartTimeStaff that has taken more sick days
	//-----------------------------------------------------------------------------------------------------------------//
	PartTimeStaff  compareToSickDay (PartTimeStaff other) {
		//check if the calling PartTimeStaff has more sickDaysTaken than the parameter; ensure that the passed PartTimeStaff is not empty
		if (this.sickDaysTaken > other.sickDaysTaken) {
			return this;	//return the calling object if so
		} else {
			return other;	//return the passed obejct if not
		}
	}
}
