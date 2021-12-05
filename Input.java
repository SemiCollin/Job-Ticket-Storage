import java.util.*;
/* 
	File Name: Input.java
	Name: Collin Kwan
	Class: ICS4U1-01
	Date: Nov 16, 2020
	Description: *CULMINATING PROJECT* -- payroll database for part time/full time staff
				-- Class made specifically for catching exceptions that may be thrown when reading user input. Includes various
					methods to be used for safely reading input
*/
//	-----------------------------------------------------------------------------------------------------------------------------------
/*
	Change Log:
	> Nov 16 ---- created class; 
				- inputMenu()
				- inputEmployeeNum();
				- inputFullorPart();
				- inputNumStaff();
				- inputHoursAssigned();
				- inputSickDaysTaken();
				- inputSalary()
				- inputHourlyRate()
				- inputName();
				- inputFileName();
*/
//	-----------------------------------------------------------------------------------------------------------------------------------

//separate method that handles all user inputs for the PayrollRunner class
public class Input {
	static Scanner sc = new Scanner(System.in);
	
	//Handle input for any of the major menus
	//*no parameters*
	//return int -- returns the user's valid input
	//-------------------------------------------------// 
	public static int inputMenu () {
		boolean valid = false;
		String input;
		int select = -1;
		//continue prompting for input until the user enters valid data
		while (!valid) {
			try {
				System.out.print("\n> ");
				input = sc.nextLine();	//read user input
				select = Integer.parseInt(input);	//take the integer value of the user's input
				valid = true;	//update exit condtion; valid
			} catch (NumberFormatException nfx) {	//catch number format exception
				System.out.println("\nPlease enter a valid number");	//ask user for proper input
			}
		}
		return select;	//return the users integer input
	}
	
	//Handle input for employee numbers
	//*no parameters*
	//return int -- returns the user's valid input
	//-------------------------------------------------// 
	public static int inputEmployeeNum () {
		boolean valid = false;
		String input;
		int employeeNum = -1;
		
		//continue prompting for input until the user enters valid data
		while (!valid) {
			try {
				System.out.print("\n> ");
				input = sc.nextLine();	//read user input
				employeeNum = Integer.parseInt(input);	//take the integer value of the users input
				
				//check if the employee number is positive (valid)
				if (employeeNum > 0) {
					valid = true;	//update exit condition
				} else {
					System.out.println("\nEmployee Number cannot be negative. Please enter a valid number");	//prompt for proper input
				}
			} catch (NumberFormatException nfx) {	//catch number format exception
				System.out.println("\nPlease enter a valid number");	//ask for valid input
			}
		}
		return employeeNum;	//return the user's valid input
	}
	
	//Handle input for determining full or part time
	//*no parameters*
	//return int -- returns the user's valid input
	//-------------------------------------------------// 
	public static int inputFullorPart () {
		boolean valid = false;
		int input = -1;
		
		//run until the user enters valid input
		while (!valid) {
			try {
				System.out.print("\n> (1) / (2): ");
				input = Integer.parseInt(sc.nextLine());	//read user input
				
				//check for a valid input, being 1 or 2
				if (input == 1) {
					valid = true;	//update exit cond.
				} else if (input == 2) {
					valid = true;	//update exit cond.
				} else {
					System.out.println("\nPlease enter either 1 or 2 to indicate a Full Time or Part Time staff");	//prompt for proper input
				}
			} catch (NumberFormatException nfx) {	//catch number format exception
				System.out.println("\nPlease enter a valid number");	//ask for valid input
			}
		}
		return input;	//return the user's valid input
	}
	
	//Handle input for the number of staff in a list
	//*no parameters*
	//return int -- returns the user's valid input
	//-------------------------------------------------// 
	public static int inputNumStaff () {
		boolean valid = false;
		String input;
		int numStaff = -1;
		
		//run until the user enters valid input
		while (!valid) {
			try {
				System.out.print("\n> ");
				input = sc.nextLine();	//read user input
				numStaff = Integer.parseInt(input);	//take the integer value of the input
				
				//check if the number of staff is positive; valid
				if (numStaff > 0) {
					valid = true;	//update exit cond.
				} else {
					System.out.println("Staff list size cannot be negative. Please try again");	//prompt for proper input
				}
			} catch (NumberFormatException nfx) {	//catch number format exception
				System.out.println("\nPlease enter a valid number");	//ask for valid input
			}
		}
		return numStaff;	//return the user's valid input
	}
	
	//Handle input for the number of hours assigned
	//*no parameters*
	//return int -- returns the user's valid input
	//-------------------------------------------------// 
	public static int inputHoursAssigned () {
		boolean valid = false;
		String input;
		int hoursAssigned = -1;
		//run until the user enters valid input
		while (!valid) {
			try {
				System.out.print("\n> ");
				input = sc.nextLine();	//read user input
				hoursAssigned = Integer.parseInt(input);	//take the integer value of the input
				
				//check if the hours assigned is positive; valid
				if (hoursAssigned > 0) {
					valid = true;	//update exit cond.
				} else {
					System.out.println("Hours assigned cannot be negative. Please try again");	//prompt for proper data
				}
			} catch (NumberFormatException nfx) {	//catch number format exception
				System.out.println("\nPlease enter a valid number");	//ask for valid input
			}
		}
		return hoursAssigned;	//return the user's valid input
	}
	
	//Handle input for the sick days taken
	//*no parameters*
	//return double -- returns the user's valid input
	//-------------------------------------------------// 
	public static double inputSickDaysTaken () {
		boolean valid = false;
		String input;
		double sickDaysTaken = -1;
		//run until the user enters valid input
		while (!valid) {
			try {
				System.out.print("> ");
				input = sc.nextLine();	//read user input
				sickDaysTaken = Double.parseDouble(input);	//take the double value of the user's input
				
				//check if the sick days taken is positive; valid
				if (sickDaysTaken > 0) {
					valid = true;	//update exit cond
				} else {
					System.out.println("Sick days taken cannot be negative. Please try again");	//prompt for proper data
				}
			} catch (NumberFormatException nfx) {	//catch number format exception
				System.out.println("\nPlease enter a valid number");	//ask for valid input
			}
		}
		return sickDaysTaken;	//return the user's valid input
	}
	
	//Handle input for the yearly salary
	//*no parameters*
	//return int -- returns the user's valid input
	//-------------------------------------------------// 
	public static double inputSalary () {
		boolean valid = false;
		String input;
		double salary = -1;
		//run until the user enters valid input
		while (!valid) {
			try {
				System.out.print("\n> $");
				input = sc.nextLine();	//read user input
				salary = Double.parseDouble(input);	//take the double value of the user's input
				
				//check if the salary is positive; valid
				if (salary > 0) {
					valid = true;	//update exit cond.
				} else {
					System.out.println("Yearly salary cannot be negative. Please try again");	//prompt for proper input
				}
			} catch (NumberFormatException nfx) {	//catch number format exception
				System.out.println("\nPlease enter a valid number");	//ask for valid input
			}
		}
		return salary;	//return the user's valid input
	}
	
	//Handle input for the hourly rate
	//*no parameters*
	//return int -- returns the user's valid input
	//-------------------------------------------------// 
	public static double inputHourlyRate () {
		boolean valid = false;
		String input;
		double hourlyRate = -1;
		//run until the user enters valid input
		while (!valid) {
			try {
				System.out.print("\n> $");
				input = sc.nextLine();	//read user input
				hourlyRate = Double.parseDouble(input);	//take the double value of the user's input
				
				//check if the hourly rate is positive; valid
				if (hourlyRate > 0) {
					valid = true;	//update exit cond.
				} else {
					System.out.println("Hourly rate cannot be negative. Please try again");	//prompt for proper input
				}	
			} catch (NumberFormatException nfx) {	//catch number format exception
				System.out.println("\nPlease enter a valid number");	//ask for valid input
			}
		}
		return hourlyRate;	//return the user's valid input
	}
	
	//Handle input for names
	//*no parameters*
	//return String -- user input
	//-------------------------------------------------// 
	public static String inputName () {
		System.out.print("\n> ");
		String input = sc.nextLine();
		return input;
	}
	
	//Handle input for file names
	//*no parameters*
	//return String -- user input
	//-------------------------------------------------// 
	public static String inputFileName () {
		System.out.print("\n> ");
		String input = sc.nextLine();
		return input + ".txt";
	}
}
