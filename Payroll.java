import java.io.*;
/* 
	File Name: Payroll.java
	Name: Collin Kwan
	Class: ICS4U1-01
	Date: Nov 08, 2020
	Description: *CULMINATING PROJECT* -- payroll database for part time/full time staff
				-- Class that operates in conjunction with Employee.java. Performs various processing and input/output functions
					using information involving the Employee class hierarchy; FullTimeStaff and PartTimeStaff data values
*/
//	-----------------------------------------------------------------------------------------------------------------------------------
/*
	Change Log:
	> Nov 09 ---- created class;
				> getStaffList() mutator method
				> setStaffList() accessor method
				> class array staffList
				> listAllStaff method
				> sortByName method
				> sortByNumber method
				> searchEmployee(String) method
				> searchEmployee(int) method
				> enterSickDay method
	> Nov 10 ----
				> saveStaffList method
				> loadStaffList method
				> addEmployee method
				> deleteEmployee method
	> Nov 11 ----
				> averageSalary method
				> averageHourlyRate method
				> mostAbsentFullTime method
				> mostAbsentPartTime method
				> yearalySickDayReset method
				> monthlySickDayReset method
	> Nov 16 ----
				> Documentation
*/
//	-----------------------------------------------------------------------------------------------------------------------------------
/*
	Bug Testing/Fixing:
	> **ArrayOutOfBounds when creating employee objects; staffList array needs to be initialized externally. Requires an extra mutator
		method to initialize the staffList size.
*/
//	-----------------------------------------------------------------------------------------------------------------------------------

//separate class that works in conjunction with the Employee class hierarchy
public class Payroll {
	private static Employee[] staffList;	//class field array of Employees
	
	//*MUTATOR METHOD* for private field hoursAssigned
	// int size -- desired size of the staff list
	// void -- initialize the size of the private field staffList equal to the passed parameter
	//-------------------------------------------------// 
	public static void setStaffList(int size) {
		staffList = new Employee[size];	//initialize the size of staffList equal to the pass parameter
	}
	
	//*ACCESSOR METHOD* for private field staffList
	//*no parameters*
	//return Employee[] -- return the staffList array
	//-------------------------------------------------// 
	public static Employee[] getStaffList() {
		return staffList;	//return the staffList array
	}

	//*MUTATOR METHOD* for private field staffList
	//int index -- index of the array that should be changed
	//Employee newStaff -- Employee to be entered at the passed index
	//void -- set the value for a specific index of the staffList array
	//-------------------------------------------------// 
	public static void setStaffList (int index , Employee newStaff) {
		staffList[index] = newStaff;	//set the value for the passed index of the staffList array equal to the passed Employee
	}

	//sort the staffList class array in alphabetical order using each element's last name; Selection sort algorithm
	//@param Employee[] list -- array of employees to be sorted
	// return Employee[] -- return the sorted array
	//-----------------------------------------------------------------------------------------------------------//
	public static Employee[] sortByName (Employee[] list) {
		int upper = list.length;
		int alphaIndex;	//index of the employee who's name is lexicographically first relative to the sorted list
		Employee temp;
		
		//run through every element in the array
		for (int i = 0; i < upper - 1; i++) {
			alphaIndex = i;	//set the alphabetically first element equal to the one at current index i
			
			//compare with every element starting from the element after the one at current index i
			for (int j = i + 1; j < upper; j++) {
				//compare the last names of elements at i and j lexicographically
				
				//check if the two employees being compared have the same last name
				if (list[j].compareLast(list[alphaIndex])) {
					
					//if so, compare their first names
					if (list[j].compareFirst(list[alphaIndex])) {
						
						//check for the lower employee number; order between the two by number
						if (list[j] == list[j].compareEmployeeNum(list[alphaIndex])) {
							alphaIndex = j;	//set the alphabetically first element equal to j if it has the lower employee number
						}
						
					} else if (list[j].firstName.compareTo(list[alphaIndex].lastName) < 0) {
						alphaIndex = j;	//set the alphabetically first element equal to j if its first name lexicographically precedes current element i
					}
				} else if ((list[j].lastName).compareTo(list[alphaIndex].lastName) < 0) {
					alphaIndex = j;	//set the alphabetically first element equal to the one at index j if it lexicographically precedes element i
				}
			}
			//switch the current element (i) in the array with the alphabetically first element successively
			temp = list[alphaIndex];
			list[alphaIndex] = list[i];
			list[i] = temp;
		}
		
		return list;	//return the sorted list
	}

	//sort the staffList class array in ascending order using each element's employee number; Selection sort algorithm
	//@param Employee[] list -- array of employees to be sorted
	// return Employee[] -- return the sorted array
	//-------------------------------------------------------------------------------------------------------------//
	public static Employee[] sortByNum (Employee[] list) {
		int upper = list.length;
		int minIndex;	//index of the employee with the lowest employee number relative to the sorted list
		Employee temp;
		
		//run through every element in the array
		for (int i = 0; i < upper - 1; i++) {
			minIndex = i;	//set the numerically first element equal to the one at current index i
			
			//compare with every element starting from the element after the one at current index i
			for (int j = i + 1; j < upper; j ++) {
				//compare the last names of elements at i and j numerically
				if (list[j].employeeNum < list[minIndex].employeeNum) {
					minIndex = j;	//set the alphabetically first element equal to the one at index j if it numerically precedes element i
				}
			}
			//switch the current element (i) in the array with the numerically first element successively
			temp = list[minIndex];
			list[minIndex] = list[i];
			list[i] = temp;
		}
		
		return list;	//return the sorted list
	}
	
	//search through the staffList class array for a specific Employee given a first and last name; linear search
	//@param String searchFirstName -- first name to search for
	//@param String searchLastName -- last name to search for
	// return int -- return the array index at which the sought employee was found or -1 if not found
	//-------------------------------------------------------------------------------------------------------------//
	public static int searchEmployee (String searchFirstName , String searchLastName) {
		//run through every element in the staffList array
		for (int i = 0; i < staffList.length; i++) {
			//check if the current element has the same first and last name as the passed first and last names to search for
			if ((staffList[i].firstName).compareToIgnoreCase(searchFirstName) == 0 && (staffList[i].lastName).compareToIgnoreCase(searchLastName) == 0) {
				return i;	//return the current element index
			}
		}
		return -1;	//return -1 if the sought employee does not exist in the staffList array
	}

	//search through the staffList class array for a specific Employee given their employee number; linear search
	//@param int searchEmployeeNum -- employee number to search for
	// return int -- return the array index at which the sought employee was found or -1 if not found
	//-------------------------------------------------------------------------------------------------------------//
	public static int searchEmployee (int searchEmployeeNum) {
		//run through every element in the staffList array
		for (int i = 0; i < staffList.length; i++) {
			//check if the current element has the same employee number as the passed employee number to search for
			if (staffList[i].employeeNum == searchEmployeeNum) {
				return i;	//return the current element index if so
			}
		}
		return -1;	//return -1 if the sought employee does not exist in the staffList array
	}
	
	//save the current existing staffList array and all of it's associated employee fields to a separate text file
	//@param String fileName -- name of the file to save to
	// void -- writes all the associated employee fields for every element in the staffList to a text file
	//-------------------------------------------------------------------------------------------------------------//
	public static void saveStaffList (String fileName) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			
			int numLines = staffList.length;	//determine the number of lines to print (1 for every employee in the staffList)
			out.write(numLines + "");	//write the number of employees in the list to the first line (used for file loading)
			out.newLine();	//move to the next line
			
			//run for every employee in the staffList
			for (int i = 0; i < staffList.length; i++) {
				//write all of the relevant instance fields for the current employee element at index i
				out.write(staffList[i].fullToString());
				out.newLine();	//move to the next line
			}
			out.close();	//close the file writer
		} catch (IOException iox) {		//catch the file output exception
			System.out.println("\nError saving file. Ensure you have entered the file name properly (omit .txt)\n");	//print an error message
		}
	}

	//load a text file with sufficiently formatted data into a new staffList to be used for processing
	//@param String fileName -- name of the file to load
	// void -- read from the desired file and create employee elements using the read information
	//-----------------------------------------------------------------------------------------------//
	public static void loadStaffList (String fileName) {
		String input;
		String[] info;	//String array made to hold parsed information from a read line
		String[] names;	//String array made to hold the first and last name of an employee
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			int numStaff = Integer.parseInt(in.readLine());	//read the first line to determine the size of the staffList to be created
			staffList = new Employee[numStaff];	//initialize the staffList with the read list size
			
			//run for the number of staff/lines
			for (int i = 0; i < numStaff; i++) {
				input = in.readLine();	//read the list of text
				info = input.split(" -- ");	//separate the line of text into usable data; formatted with " -- " between each field
				names = info[2].split(", ");	//separate the name field specifically into usable data; formatted with ", " (eg. last, first)
				
				//considering the test format: <Full/Part> -- <employeeNum> -- <lastName>, <firstName> -- <yearlySalary>/<hoursAssigned> -- <sickDaysTaken>/<hourlyRate> -- (null)/<sickDaysTaken>
				//check if the current line indicates a full or part time staff
				if (info[0].compareToIgnoreCase("Full Time Staff") == 0) {
					//create a new FullTimeStaff using the appropriate read fields that satisfy the necessary parameters
					FullTimeStaff newFullTime = new FullTimeStaff(Integer.parseInt(info[1]) , names[1] , names[0] , Double.parseDouble(info[3].substring(1)) , Double.parseDouble(info[4]));	//Full Time Staff have 5 parameters; number, first name, last name, salary and sick days taken
					staffList[i] = newFullTime;	//add the created FullTimeStaff to the staffList
				} else if (info[0].compareToIgnoreCase("Part Time Staff") == 0) {
					//create a new PartTimeStaff using the appropriate read fields that satisfy the necessary parameters
					PartTimeStaff newPartTime = new PartTimeStaff(Integer.parseInt(info[1]) , names[1] , names[0] , Integer.parseInt(info[3]) , Double.parseDouble(info[4].substring(1)) , Double.parseDouble(info[5]));	//Part Time Staff have 6 parameters; number, first name, last name, assigned hours, hourly rate and sick days taken
					staffList[i] = newPartTime;	//add the created PartTimeStaff to the staffList
				}
			}
			in.close();	//close the file reader
		} catch (NumberFormatException nfx) {	//catch number format exception from parsing a number from a string
			System.out.println("\nEnsure the file is formatted with numbers where necessary (# of staff, employee number, salary, hourly rate, hours assigned, sick days taken)\n");	//print instructions of the possible areas of error
		} catch (StringIndexOutOfBoundsException sbx) {	//catch string out of bounds exception from failed split
			System.out.println("\nEnsure the file has all information properly formatted as was indicated\n");	//print a reminder for the proper formatting
		} catch (IOException iox) {	//catch the input exception
			System.out.println("\nUnable to find/load file. Ensure you have entered the file name properly (omit .txt)\n");	//output an error message
		}
	}

	//calculate and return the average salary (FullTimeStaff) for a given array of Employees
	//@param Employee[] list -- array of Employees to analyze
	// return double -- return the average salary of all full time staff as a double
	//-------------------------------------------------------------------------------------------------------------//
	public static double averageSalary (Employee[] list) {
		int numFullTime = 0;
		double sum = 0;
		double average;
		
		//run through every element in the passed Employee array
		for (int i = 0; i < list.length; i++) {
			//check if the employee at the current index is a FullTimeStaff
			if (list[i] instanceof FullTimeStaff) {
				sum += ((FullTimeStaff)list[i]).getYearlySalary();	//update the sum of all yearly salaries by that of the current element if so
				numFullTime++;	//update the count of full time staff by +1 each time
			}
		}
		average = sum / numFullTime;	//calculate the average salary
		return average;	//return the average salary
	}
	
	//calculate and return the average hourly rate (PartTimeStaff) for a given array of Employees
	//@param Employee[] list -- array of Employees to analyze
	// return double -- return the average hourly pay rates of all part time staff as a double
	//-------------------------------------------------------------------------------------------------------------//
	public static double averageHourlyRate (Employee[] list) {
		int numPartTime = 0;
		double sum = 0;
		double average;
		
		//run through every element in the Employee array
		for (int i = 0; i < list.length; i++) {
			//check if the Employee at the current index is a PartTimeStaff
			if (list[i] instanceof PartTimeStaff) {
				sum += ((PartTimeStaff)list[i]).getHourlyRate();	//update the sum of all hourly rates by that of the current element if so
				numPartTime++;	//update the count of part time staff by +1 each time
			}
		}
		average = sum / numPartTime;	//calculate the hourly pay rate
		return average;	//return the average hourly pay rate
	}

	//determine and return the full time staff with the most sick days (excluding part time staff)
	//@param Employee[] list -- array of Employees to analyze
	// return Employee -- return the Employee with the most sick days taken
	//-------------------------------------------------------------------------------------------------------------//
	public static Employee mostAbsentFullTime (Employee[] list) {
		int mostAbsentIndex = 0;
		double mostSickDaysTaken = 0;
		
		//run through every element in the Employee array
		for (int i = 0; i < list.length; i++) {
			//check if the current element is a FullTimeStaff
			if (list[i] instanceof FullTimeStaff) {
				//compare the number of sick days taken to the established highest sick day count (automatically set to sick day count of the first element
				if (((FullTimeStaff)list[i]).getSickDaysTaken() > mostSickDaysTaken || i == 0) {
					mostAbsentIndex = i;	//set the index at which the current element exists to the current index i if so
					mostSickDaysTaken = ((FullTimeStaff)list[i]).getSickDaysTaken();	//update the highest sick day count
				}
			}
		}
		
		return list[mostAbsentIndex];	//return the element at index with the most sick days taken
	}
	
	//determine and return the part time staff with the most sick days (excluding full time staff)
	//@param Employee[] list -- array of Employees to analyze
	// return Employee -- return the Employee with the most sick days taken
	//-------------------------------------------------------------------------------------------------------------//
	public static Employee mostAbsentPartTime (Employee[] list) {
		int mostAbsentIndex = 0;
		double mostSickDaysTaken = 0;
		
		//run through every element in the Employee array
		for (int i = 0; i < list.length; i++) {
			//check if the current element is a PartTimeStaff
			if (list[i] instanceof PartTimeStaff) {
				//compare the number of sick days taken to the established highest sick day count (automatically set to sick day count of the first element
				if (((PartTimeStaff)list[i]).getSickDaysTaken() > mostSickDaysTaken || i == 0) {
					mostAbsentIndex = i;	//set the index at which the current element exists to the current index i if so
					mostSickDaysTaken = ((PartTimeStaff)list[i]).getSickDaysTaken();	//update the highest sick day count
				}
			}
		}
		
		return list[mostAbsentIndex];	//return the element at index with the most sick days taken
	}
	
	//reset the sick day counts for every full time staff (sick days left to 20, sick days taken to 0)
	//@param Employee[] list -- array of Employees to read through and reset sick days
	// void -- reset the sickDaysTaken and sickDaysLeft fields to 0 and 20 respectively
	//-------------------------------------------------------------------------------------------------------------//
	public static void yearlySickDayReset (Employee[] list) {
		//run through every element in the Employee array
		for (int i = 0; i < list.length; i++) {
			//check if the current element is a FullTimeStaff
			if (list[i] instanceof FullTimeStaff) {
				list[i].resetSickDay();	//call the method to reset the current element's sick day information if so
			}
		}
	}
	
	//reset the sick day counts for every part time staff (sick days taken to 0)
	//@param Employee[] list -- array of Employees to read through and reset sick days
	// void -- reset the sickDaysTaken to 0
	//-------------------------------------------------------------------------------------------------------------//
	public static void monthlySickDayReset (Employee[] list) {
		//run through every element in the Employee array
		for (int i = 0; i < list.length; i++) {
			//check if the current element is a PartTimeStaff
			if (list[i] instanceof PartTimeStaff) {
				list[i].resetSickDay();	//call the method to reset the current element's sick day information if so
			}
		}
	}
	
	//lists the current staffList with each element's basic fields (employee number, first and last name)
	//*no parameters*
	// void -- writes the current existing staffList to an external text file
	//-------------------------------------------------------------------------------------------------------------//
	public static void listAllEmployee () {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("EmployeeList.txt"));
			//run through every element in the existing staffList
			for (int i = 0; i < staffList.length; i++) {
				//write the current element (employee number, last name, first name)
				out.write(staffList[i].toString());
				out.newLine();	//move to the next line
			}
			out.close();	//close the writer
		} catch (IOException iox) {	//catch the output exception
			System.out.println("Error writing to file");	//print an error message
		}
	}
	
	//add another Employee to the existing staffList and then sorts the new staffList by employee number
	//@param Employee add -- new employee to add the the staffList
	// void -- set the staffList equal to a new staffList which includes the added employee
	//-------------------------------------------------------------------------------------------------------------//
	public static void addEmployee (Employee add) {
		Employee[] listExtend = new Employee[staffList.length + 1];	//second employee array to hold 1 more employee
		
		//run through every element in the staffList
		for (int i = 0 ; i < staffList.length; i++) {
			listExtend[i] = staffList[i];	//copy all existing Employees in the staffList
		}
		
		listExtend[listExtend.length - 1] = add;	//set the last element in the new extended list as the employee to add
		Payroll.sortByNum(listExtend);	//sort the extended list by employee number
		staffList = listExtend;	//set the old staffList equal to the new extended staffList
	}

	//remove an Employee specified by employee number from the staffList
	//@param int indexDelete -- index of the staffList that indicates which employee to remove
	// void -- set the staffList equal to a new staffList which excludes the specified employee
	//-------------------------------------------------------------------------------------------------------------//
	public static void deleteEmployee (int indexDelete) {
		Employee[] listShorten = new Employee[staffList.length - 1];	//create a second array of employees that holds 1 less employee
		
		//run through the array until it reaches the index to remove
		for (int i = 0; i < indexDelete; i++) {
			listShorten[i] = staffList[i];	//copy each Employee to the shorted list
		}
		
		//run through the remainder of the array
		for (int i = indexDelete + 1; i < listShorten.length; i++) {
			listShorten[i] = staffList[i];	//copy each remaining Employee to the shortened list
		}
		staffList = listShorten;
	}

	//update the sick day information for a specified employee using the employee number
	//@param int employeeNum -- employee number to indicate which employee to update
	//@param double numSickDays -- number of sick days to add to the indicated employee's sick day count (min 0.5)
	// void -- updates the sick day information for an employee
	//-------------------------------------------------------------------------------------------------------------//
	public static void enterSickDay (int employeeNum , double numSickDays) {
		int employee = Payroll.searchEmployee(employeeNum);	//find the employee with the given employee number
		
		//as long as the employee index is not -1 (not found)
		if (employee != -1) {
			staffList[employee].updateSickDays(numSickDays);	//update the sick day information for the employee at the indicated index using the passed sick day parameter
			System.out.println("\n> Sick day information updated");
		} else {
			System.out.println("\nEmployee does not exist in the current staff list");	//print a message indicating the employee isn't in the staff list otherwise
		}
	}
}
