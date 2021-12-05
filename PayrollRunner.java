import java.util.*;
/* 
	File Name: PayrollRunner.java
	Name: Collin Kwan
	Class: ICS4U1-01
	Date: Nov 12, 2020
	Description: *CULMINATING PROJECT* -- payroll database for part time/full time staff
				-- 
*/
//	-----------------------------------------------------------------------------------------------------------------------------------
/*
	Change Log:
	> Nov 12 ---- created class; 
				- uiBuffer method to format the console
				- menu method to print the main menu options
				- set variable for the user input *at the menu screen* (char input)
				- if/else if/else block to account for the user's input
				- do/while loop to keep the user in the menu
	> Nov 13 ---- 
				- individual parts of code to handle each of the user's inputs; save files, load files, create a new list, quit, improper input
	> Nov 14 ----
				- interact() -- handle all of the user's console interface and options when interacting with the staff list and related data
				- listOptionsMenu -- menu for interacting with the list data
				- individual menus for categorized interactions with the list data
					- sortByMenu()
					- searchByMenu()
					- sickDayMenu()
					- addRemoveMenu()
					- salaryMenu()
	> Nov 16 ----
				- Exception handling with user inputs; Input class
				- Documentation
*/
//	-----------------------------------------------------------------------------------------------------------------------------------
/*
	Bug Testing/Fixing:
	> **Unable to load files properly; the "file name" must include ".txt" when inputed (or add .txt to the end of an inputed file name
		before loading)
		
	> **ArrayOutOfBounds when creating employee objects; staffList array needs to be initialized externally. Requires an extra mutator
		method to initialize the staffList size.
		
	> **Unable to read String inputs; empty line inputs must be flushed using sc.nextLine() before reading another String from the user
	
	> **Catch inputMistmatchExceptions for improper input; separate Input class to specifically handle input exceptions
	
	> **Manipulate loop so as to not repeat certain code segments unnecessarily; Interaact() method with the interact boolean modifier
		conidtion
	
	> **NoSuchElementException; keeping scanners open. Eclipse speaks nothing but lies
*/
//	-----------------------------------------------------------------------------------------------------------------------------------

//separate class that processes user input for the Employee class hierarchy
public class PayrollRunner {
	static Scanner sc = new Scanner(System.in);

	//print a buffer to keep the ui readable
	//--------------------------------------------------------------------------//
	public static void uiBuffer () {
		System.out.println();
		System.out.println("=================================================================");
		System.out.println();
	}
	
	//print all main menu options for the user to choose from and prompt for input
	//--------------------------------------------------------------------------//
	public static void menuFull () {
		uiBuffer();
		System.out.println("Payroll Database Main Menu");
		System.out.println("Select an option: ");
		uiBuffer();
		System.out.println("> Load Existing List (enter l)");
		System.out.println("> Create a new List (enter n)");
		System.out.println("> Quit (enter q)");
		System.out.print("\n> ");
	}
	
	//print only the header of the main menu
	//--------------------------------------------------------------------------//
	public static void menuHeader () {
		uiBuffer();
		System.out.println("Payroll Database Main Menu");
	}
	
	//print only the body of the main menu
	//--------------------------------------------------------------------------//
	public static void menuBody () {
		System.out.println("Select an option: ");
		uiBuffer();
		System.out.println("> Load Existing List (enter l)");
		System.out.println("> Create a new List (enter n)");
		System.out.println("> Quit (enter q)");
		System.out.print("\n> ");
	}
	
	//print all the interactive options with the staff list for the user to choose from and prompt for input
	//----------------------------------------------------------------------------------------------------//
	public static void listOptionsMenu () {
		uiBuffer();
		System.out.println("Staff List Options Menu");
		System.out.println("Select an option using the corresponding number input:");
		uiBuffer();
		System.out.println("> (1) Sort by -");
		System.out.println("> (2) Search by -");
		System.out.println("> (3) Sick day info");
		System.out.println("> (4) Add/Remove an Employee");
		System.out.println("> (5) Salary info");
		System.out.println("> (6) Save list");
		System.out.println("> (7) Print list");
		System.out.println("< (0) Back to Main Menu");
	}

	//print all options for the user to choose from when sorting the staff list
	//--------------------------------------------------------------------------//
	public static void sortByMenu () {
		uiBuffer();
		System.out.println("> Sort by -");
		System.out.println("(1) Name");
		System.out.println("(2) Employee Number");
		System.out.println("(0) Back to Options Menu");
	}
	
	//print all the options for the user to choose from when searching through the staff list
	//-------------------------------------------------------------------------------------//
	public static void searchByMenu () {
		uiBuffer();
		System.out.println("> Search by -");
		System.out.println("(1) Name");
		System.out.println("(2) Employee Number");
		System.out.println("(0) Back to Options Menu");
	}
	
	//print all the options for the user to choose from when handling sick day information
	//----------------------------------------------------------------------------------//
	public static void sickDayMenu () {
		uiBuffer();
		System.out.println("> Sick Day Info Menu");
		System.out.println("(1) Update sick days for an Employee");
		System.out.println("(2) Reset sick day info");
		System.out.println("(3) Most Sick Days -- Part Time");
		System.out.println("(4) Most Sick Days -- Full Time");
		System.out.println("(5) Monthly Sick Day Reset -- Pary Time");
		System.out.println("(6) Yearly Sick Day Reset -- Full Time");
		System.out.println("(0) Back to Options Menu");
	}
	
	//print all the options for the user to choose from when adding/removing an employee
	//--------------------------------------------------------------------------------//
	public static void addRemoveMenu () {
		uiBuffer();
		System.out.println("> Add/Remove an Employee");
		System.out.println("(1) Add");
		System.out.println("(2) Remove");
		System.out.println("(0) Back to Options Menu");
	}
	
	//print all the options for the user to choose from when handling salary information
	//--------------------------------------------------------------------------------//
	public static void salaryMenu () {
		uiBuffer();
		System.out.println("> Salary Info Menu");
		System.out.println("(1) Average yearly salary -- Full time");
		System.out.println("(2) Average hourly rate -- Part time");
		System.out.println("(3) Print paystub");
		System.out.println("(0) Back to Options Menu");
	}

	//Handle all of the user's options when interacting with the staff list
	//--------------------------------------------------------------------------------//
	public static void interact() {
		boolean inMain = false;	//create an initialize as false; user is not in the main menu
		
		//run the interact body while the user is not in the main menu
		while (!inMain) {
			inMain = false;	//initialize inMain as false each iteration
			boolean inOptions = false;	//initialize inOptions as false each iteration
			
			listOptionsMenu();
			int select = Input.inputMenu();	//get user's input from the options menu
			
			//perform the corresponding option according to the user's input on the options menu:
			if (select == 0) {
				//BACK TO MAIN MENU
				menuHeader();
				inMain = true;	//update the exit condition and return to the main menu
				
			} else if (select == 1) {
				//SORTING
				
				//run while the user is not currently in the options menu
				while (!inOptions) {
					sortByMenu();
					select = Input.inputMenu();	//get user's input from the sort menu
					
					//perform the corresponding action
					if (select == 0) {
						inOptions = true;	//update the exit condition and leave the sort menu
					} else if (select == 1) {
						//sort the staff list by last name
						Payroll.sortByName(Payroll.getStaffList());
						System.out.println("\n> List sorted");	//indicate sort completion
						//wait for user to enter before continuing
						System.out.println("Enter to Continue");
						sc.nextLine();
					} else if (select == 2) {
						//sort the staff list by employee number
						Payroll.sortByNum(Payroll.getStaffList());
						System.out.println("\n> List sorted");	//indicate sort completion
						//wait for user to enter before continuing
						System.out.println("Enter to Continue");
					} else {
						System.out.println("\nPlease select an option using the corresponding number input");	//ask for proper input in the case the user input does not perform any actions
					}
					
				}
			} else if (select == 2) {
				//SEARCHING
				int employeeIndex = -1;
				Employee found;	//initialize Employee to store any found employees through the search method
				
				//run while the user is not currently in the options menu
				while (!inOptions) {
					searchByMenu();
					select = Input.inputMenu();	//get user's input from the search menu
					
					//perform the corresponding action
					if (select == 0) {
						inOptions = true;	//update the exit condition and leave the search menu
					} else if (select == 1) {
						System.out.println("\nEnter employee first and last name:");	//prompt for user to input an employee's full name
						
						//run and ask for input until the user enters a valid input
						boolean valid = false;
						while (!valid) {
							System.out.print("\n> ");
							String[] names = sc.nextLine().split(" ");	//read the user's input and split with space to separate first and last name
							
							try {
								employeeIndex = Payroll.searchEmployee(names[0] , names[1]);	//search for the employee with the given names
								valid = true;	//update the exit condition; input was valid
							} catch (ArrayIndexOutOfBoundsException abx) {	//catch the out of bounds exception if the user enters only one word
								System.out.println("\nEnter both the employee's first and last name");	//instruct user to enter both names
							}
						}
						
						//check if the employee was successfully found
						if (employeeIndex == -1) {
							System.out.println("\n> Employee Not Found");
						} else {
							//print the found employee if so
							found = Payroll.getStaffList()[employeeIndex];
							System.out.println("\n> " + found.toString() + found.infoToString());
							//wait for user to enter before continuing
							System.out.println("Enter to Continue");
							sc.nextLine();
						}
					} else if (select == 2) {
						System.out.println("\nEnter employee number:");
						int employeeNum = Input.inputEmployeeNum();	//prompt user to enter an employee's number to search for
				
						employeeIndex = Payroll.searchEmployee(employeeNum);	//search for the given employee number
						
						//check if the employee was successfully found
						if (employeeIndex == -1) {
							System.out.println("> Employee Not Found");
						} else {
							//print the employee if so
							found = Payroll.getStaffList()[employeeIndex];
							System.out.println("\n> " + found.toString() + found.infoToString());
							//wait for user to enter before continuing
							System.out.println("Enter to Continue");
							sc.nextLine();
						}
					} else {
						System.out.println("\nPlease select an option using the corresponding number input");	//ask for proper input in the case the user input does not perform an action
					}
				}
			} else if (select == 3) {
				//SICK DAY OPTIONS
				
				//run while the user is not in the options menu
				while (!inOptions) {
					sickDayMenu();
					select = Input.inputMenu(); //get user input from the sick day menu
					
					//perform the corresponding action
					if (select == 0) {
						inOptions = true;	//update the exit condition and leave the search menu
						
					} else if (select == 1) {
						System.out.println("Update which employee:");
						System.out.print("\n-- Employee Number");
						int update = Input.inputEmployeeNum();	//prompt for user to enter an employee number to update the sick day info for
						
						System.out.println("\nSick days taken (minimun 0.5): ");
						double updateSick = Input.inputSickDaysTaken();	//prompt user to enter the number of sick days to add to the employee's sick day count
						
						Payroll.enterSickDay(update , updateSick);	//run the sick day update method using the given employee number and sick day count
						
					} else if (select == 2) {
						System.out.println("Reset sick days for which employee:");
						System.out.println("\n-- Employee Number");
						int reset = Input.inputEmployeeNum();	//prompt user to enter an employee number to reset their sick day info
						
						int resetSearch = Payroll.searchEmployee(reset);	//search for the entered employee number
						//check if the employee was successfully found
						if (resetSearch == -1) {
							System.out.println("\n> Employee not found");
						} else {
							Payroll.getStaffList()[resetSearch].resetSickDay();	//reset the sick day info for the found employee if so
							System.out.println("\n> Sick day info for employee #" + Payroll.getStaffList()[resetSearch].employeeNum + " reset to default");	//print a completion message
							//wait for user to enter before continuing
							System.out.println("Enter to Continue");
							sc.nextLine();
						}
													
					} else if (select == 3) {
						//call the method to determine the most absent part time staff and print it accordingly
						PartTimeStaff mostAbsent = (PartTimeStaff)Payroll.mostAbsentPartTime(Payroll.getStaffList());
						System.out.println("\nPart Time Employee with the most sick days:");
						System.out.println("> " + mostAbsent.fullToString());
						System.out.println("> Sick days taken -- " + mostAbsent.getSickDaysTaken() + "\n");
						System.out.println("Enter to continue");
						sc.nextLine();
						
					} else if (select == 4) {
						//call the method to determine the most absent full time staff and print it accordingly
						FullTimeStaff mostAbsent = (FullTimeStaff)Payroll.mostAbsentFullTime(Payroll.getStaffList());
						System.out.println("\nFull Time Employee with the most sick days:");
						System.out.println("> " + mostAbsent.fullToString());
						System.out.println("> Sick days taken -- " + mostAbsent.getSickDaysTaken() + "\n");
						System.out.println("Enter to continue");
						sc.nextLine();
						
					} else if (select == 5) {
						//call the method to reset the sick day info for all the part time staff each month
						Payroll.monthlySickDayReset(Payroll.getStaffList());
						System.out.println("> Part time staff sick day info reset");
						//wait for user to enter before continuing
						System.out.println("Enter to Continue");
						sc.nextLine();
						
					} else if (select == 6) {
						//call the method to reset the sick day info for all full time staff each year
						Payroll.yearlySickDayReset(Payroll.getStaffList());
						System.out.println("> Full time staff sick day info reset");
						//wait for user to enter before continuing
						System.out.println("Enter to Continue");
						sc.nextLine();
						
					} else {
						System.out.println("\nPlease select an option using the corresponding number input");
					}
				}
					
			} else if (select == 4) {
				//ADD/REMOVE EMPLOYEE
				
				while (!inOptions) {
					addRemoveMenu();
					select = Input.inputMenu();	//get user input from the add/remove menu
					
					if (select == 0) {
						inOptions = true;
					} else if (select == 1) {
						//prompt user to enter the needed info to create and add another employee
						System.out.println("\nEnter info for the employee to add");
						
						//get input to indicate full or part time
						System.out.println("\n-- (1) Full time or (2) Part time");
						int fullOrPart = Input.inputFullorPart();
						
						System.out.println("-- Employee Number:");
						int employeeNum = Input.inputEmployeeNum();
						
						//get input for the employee's first name
						System.out.println("\n-- First Name:");
						String firstName = Input.inputName();
						
						//get input for the employee's last name
						System.out.println("\n-- Last Name:");
						String lastName = Input.inputName();
						
						//create a different employee object (full or part time) depending on the user's indication
						if (fullOrPart == 1) {
							//creation of a full time employee:
							
							//get input for yearly salary
							System.out.println("\n-- Yearly Salary:");
							double yearlySalary = Input.inputSalary();
							
							//get input for sick days taken
							System.out.println("\n-- Sick Days Taken:");
							double sickDays = Input.inputSickDaysTaken();
							
							//create the new FullTimeStaff
							FullTimeStaff newStaff = new FullTimeStaff(employeeNum , firstName , lastName , yearlySalary , sickDays);	//create the FullTimeStaff object with the given information
							Payroll.addEmployee(newStaff);	//call the method to add the new FullTimeStaff to the staffList
							
						} else if (fullOrPart == 2) {
							//creation of a part time employee
							
							//get input for assigned hours per month
							System.out.println("\n-- Amount of Hours Assigned per Month:");
							int hoursAssigned = Input.inputHoursAssigned();
							
							//get input for hourly rate
							System.out.println("\n-- Hourly Rate:");
							double hourlyRate = Input.inputHourlyRate();
							
							//get input for sick days taken
							System.out.println("\n-- Sick Days Taken:");
							double sickDays = Input.inputSickDaysTaken();
							
							//create the new PartTimeStaff
							PartTimeStaff newStaff = new PartTimeStaff(employeeNum , firstName , lastName , hoursAssigned , hourlyRate , sickDays);	//create a PartTimeStaff object with the given information
							Payroll.addEmployee(newStaff);	//call the method to add the new PartTimeStaff to the staffList
						}
						System.out.println("\n> Employee added");	//print a completion message
						//wait for user to enter before continuing
						System.out.println("Enter to Continue");
						sc.nextLine();
							
					} else if (select == 2) {
						
						System.out.println("\nWhich employee do you wish to remove");
						System.out.print("\n--Employee Number");
						int employeeDeleteNum = Input.inputEmployeeNum();	//prompt for user to enter an employee number to remove from the list
						
						//search the list for the given employee number
						int deleteIndex = Payroll.searchEmployee(employeeDeleteNum);
						
						//check if the employee was found succesfully
						if (deleteIndex == -1) {
							System.out.println("\nEmployee not found in list\n");
						} else {
							Payroll.deleteEmployee(deleteIndex);	//call the method to remove the employee if so
							System.out.println("> Employee removed");	//print a completion message
							//wait for user to enter before continuing
							System.out.println("Enter to Continue");
							sc.nextLine();
						}
					}
				}
				
			} else if (select == 5) {
				//SALARY OPTIONS
				
				while (!inOptions) {
					salaryMenu();
					select = Input.inputMenu();	//get user input from the salary menu
					
					if (select == 0) {
						inOptions = true;
					} else if (select == 1) {
						//call the method to find and print the average salary of the staff list
						System.out.println("\n" + Payroll.averageSalary(Payroll.getStaffList()) + "\n");
						//wait for user to enter before continuing
						System.out.println("Enter to Continue");
						sc.nextLine();
						
					} else if (select == 2) {
						//call the method to find and print the average hourly rate of the staff list
						System.out.println("\n" + Payroll.averageHourlyRate(Payroll.getStaffList()) + "\n");
						//wait for user to enter before continuing
						System.out.println("Enter to Continue");
						sc.nextLine();
						
					} else if (select == 3) {
						System.out.println("Who's pay stub do you wish to print");
						System.out.println("-- Employee Number:");
						int employeePayStub = Input.inputEmployeeNum();	//prompt user to enter an employee number to print their pay stub
	
						int payStubIndex = Payroll.searchEmployee(employeePayStub);	//search for the entered employee number
						
						if (payStubIndex == -1) {
							System.out.println("\nEmployee not found in list");
						} else {
							Payroll.getStaffList()[payStubIndex].printPayStub();
							//wait for user to enter before continuing
							System.out.println("Enter to Continue");
							sc.nextLine();
						}
					} else {
						System.out.println("\nPlease select an option using the corresponding number input");
					}
					
				}
				
			} else if (select == 6) {
				//SAVE STAFF LIST
				
				System.out.println("\nEnter file name to save the list as:");
				String saveName = Input.inputFileName();	//prompt user to enter the name of a file to save the list as
				Payroll.saveStaffList(saveName);	//call the method to save the staff list with the give name
				System.out.println("\nFile Saved");	//print a completion message
				
			} else if (select == 7) {
				//LIST EMPLOYEES
				
				Payroll.listAllEmployee();	//call the method to list the employees currently in the staff list
				System.out.println("\nCurrent staff list printed to \"EmployeeList.txt\"");	//print a completions message
				//wait for user to enter before continuing
				System.out.println("Enter to Continue");
				sc.nextLine();
				
			} else {
				System.out.println("Please select an option using the corresponding number input");
				System.out.print("\n> ");
			}
		}
	}
	
	//method for creating a new list of employees using user input
	public static void newList () {
		Scanner sc = new Scanner(System.in);
		String firstName , lastName;
		//Prompt for user to create the staff list
		
		uiBuffer();
		//List creation. Put on your big boy pants
		System.out.println("How Many Employees are to be Added to the List:");
		int numStaff = Input.inputNumStaff();
		Payroll.setStaffList(numStaff);
		
		System.out.println("Please enter the required information for each employee");
		
		//run the following for every employee to be added
		for (int i = 0; i < numStaff; i++) {
			uiBuffer();
			System.out.println("Employee #" + (i + 1) + ":");	//Indicate which employee is being created
			
			//determine whether the employee is full or part time using 1 and 2 as indicators
			System.out.println("\n-- (1) Full time or (2) Part time");
			int fullOrPart = Input.inputFullorPart();
			
			//get input for the employee's number id
			System.out.println("-- Employee Number:");
			int employeeNum = Input.inputEmployeeNum();
			
			//get input for the employee's first name
			System.out.println("-- First Name:");
			firstName = Input.inputName();
			
			//get input for the employee's last name
			System.out.println("-- Last Name:");
			lastName = Input.inputName();
			
			//create a different employee object (full or part time) depending on the user's indication
			if (fullOrPart == 1) {
				//creation of a full time employee:
				
				//get input for the full time employee's yearly salary
				System.out.println("-- Yearly Salary:");
				double yearlySalary = Input.inputSalary();
				
				//get input for sick days taken
				System.out.println("-- Sick Days Taken:");
				double sickDays = Input.inputSickDaysTaken();
				
				//create the new FullTimeStaff
				FullTimeStaff newStaff = new FullTimeStaff(employeeNum , firstName , lastName , yearlySalary , sickDays);	//create the FullTimeStaff object with the given information
				Payroll.setStaffList(i, newStaff);	//add that employee to the staffList
				
			} else if (fullOrPart == 2) {
				//creation of a part time employee
				
				//get input for the part time employee's assigned hours
				System.out.println("-- Amount of Hours Assigned per Month:");
				int hoursAssigned = Input.inputHoursAssigned();
				
				//get input for the part time employee's hourly salary rate
				System.out.println("-- Hourly Rate:");
				double hourlyRate = Input.inputHourlyRate();
				
				//get input for sick days taken
				System.out.println("-- Sick Days Taken:");
				double sickDays = Input.inputSickDaysTaken();
				
				//crate the new PartTimeStaff
				PartTimeStaff newStaff = new PartTimeStaff(employeeNum , firstName , lastName , hoursAssigned , hourlyRate , sickDays);	//create a PartTimeStaff object with the given information
				Payroll.setStaffList(i, newStaff);	//add that employee to the staffList
			}
		}
		System.out.println("Staff List Created.");	//print completion message
		//wait for user to enter before continuing
		System.out.println("Enter to Continue");
		sc.nextLine();
	}
	
	//main method
	public static void main(String[] args) {
		String input;
		
		menuHeader();
		do {
			boolean interact = false; 	//boolean flag to allow for interaction with the staff list; default to false
			menuBody();
			
			input = sc.nextLine().toLowerCase();
			
			if (input.charAt(0) == 'l') {
				//instruct the user on how their loaded file should look before loading
				System.out.println("\n***Ensure the loaded file is formatted properly for Full Time Staff and Part Time Staff***");
				System.out.println("Full Time --- [Full Time Staff -- <employee_number> -- <last_name>, <first_name> -- <yearly_salary> -- <sick_days>]");
				System.out.println("Part Time --- [Part Time Staff -- <employee_number> -- <last_name>, <first_name> -- <hours_assigned> -- <hourly_rate> -- <sick_days>]");
				
				boolean loaded = false;	//boolean to indicate whether a file has been successfully loaded
				while (!loaded) {
					System.out.println("\nEnter file to be loaded: ");
					String fileName = Input.inputFileName();	//prompt user to enter a name for the file to load
					Payroll.loadStaffList(fileName);	//call the method to load the file with the given name
					loaded = true;
				}
				
				//After File has been loaded, allow for user interaction with the file information
				interact = true;
				
			} else if (input.charAt(0) == 'n') {
				newList();	//call the method to allow the user to create the staff list manually
				
				uiBuffer();
				
				//After the staff list has been made, allow for user interaction with the list information
				interact = true;
				
			} else if (input.charAt(0) != 'q') {
				System.out.println("\nSelect an Appropriate Option with the Corresponding Key Input");	//ask for proper input if the user input wouldn't perform an action
				uiBuffer();
			}
			
			//if the conditions have been met to allow user interaction with the list data
			if (interact) {
				interact();	//call the method to print the options menu and handle user interaction
			}
			
		} while (input.charAt(0) != 'q');	//end the program when the user enters a word beginning with q; quit
	}
}