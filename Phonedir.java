import java.util.*;
/*
 * 1.) purpose of this program is to help gain knowledge on how to work with linked list and object
 * oriented programming. Program calls to make linked list and methods to add, modified, and sort the 
 * list. 
 * 
 * 2.) solutions to this program are using methods to divide up work, switch case to work on 
 * options, if/else loops, and for loops. 
 * 
 * 3.)data structures used in this program is linked List.
 * 
 *4.) It would prompt the user a menu to do different roles in the programs. Once the user choices an option
 * it will allow the user to interact in a way to either modify, add, see all record, delete, and select a
 * certain list id. 
 * 
 * 5.) This is only once class used, but has multiple methods.
 * 
 *////////////////////////////////
/*
 * Jay Shah
 * Assignment 4. 
 * MW 5:30-6:45
 *////////////////////////////////

public class Phonedir {
	///////////////////////////////////////////////////////////////////////
	public static LinkedList<Object> firstName = new LinkedList<Object>();
	public static LinkedList<Object> lastName = new LinkedList<Object>();
	public static LinkedList<Object> phoneNumber = new LinkedList<Object>();
	public static int selectedId = -1;
	///////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		/*
		 * main method. displays the menu and takes in user input. contains the
		 * switch case for different fucntions.
		 */
		Scanner input = new Scanner(System.in);
		String selectedMenu = "";

		menu();
		// do while loop so the program keeps running until the user enters "q".
		// takes in user input and converts to lowercase.
		do {
			selectedMenu = input.next();
			switch (selectedMenu.toLowerCase()) {
			// option a will print all records and display the menu after.
			case "a":
				printallrecord();
				menu();
				break;
			///////////////////////////
			// n will allow user to enter new contact in the record.
			case "n":
				System.out.println("Enter the first name: ");
				Object fname = input.next();

				System.out.println("Enter the last name: ");
				Object lname = input.next();

				System.out.println("Enter the phone number: ");
				Object pnum = input.next();
				if (phoneNumber.contains(pnum)) {
					System.out.println("phone number already exits in the list. ");
					System.out.println("Enter revised number: ");
					pnum = input.next();
					phoneNumber.add(pnum);
				}

				if (lastName.size() == 0) {
					firstName.add(fname);
					lastName.add(lname);
					phoneNumber.add(pnum);
				} else {
					lastNameSort(fname, lname, pnum);
				}

				menu();
				break;
			/////////////////////////////
			// option f will allow the user to modify the first name of the
			// selected contact.
			case "f":

				if (checkSelected(selectedId)) {
					firstName.contains(firstName.get(selectedId));
					System.out.println("Enter new first name: ");
					String newfname = input.next();
					firstName.set(selectedId, newfname);
					System.out.println("updated: " + selectedId + " " + newfname);
				}
				menu();
				break;
			////////////////////////////
			// option l lets the user modify the last name of the selected
			// contact.
			case "l":
				/*
				 * if the seletcted id is less than 0 it will print error
				 */
				if (checkSelected(selectedId)) {
					lastName.contains(lastName.get(selectedId));
					System.out.println("Enter new last name: ");
					String newlname = input.next();
					if (lastName.size() == 0) {
						lastName.set(selectedId, newlname);
						System.out.println("Updated: " + selectedId + " " + newlname);
					} else {

						sortLastName(newlname);
					}

				}
				menu();
				break;
			//////////////////////////////
			// option p allows user to modify the phone number of the selected
			// contact.
			case "p":

				if (checkSelected(selectedId)) {
					phoneNumber.contains(phoneNumber.get(selectedId));
					System.out.println("Enter new phone number: ");
					String newpnum = input.next();

					if (phoneNumber.contains(newpnum)) {
						System.out.println("phone number already exits in the list. ");
					} else {
						phoneNumber.set(selectedId, newpnum);
						System.out.println("Updated: " + selectedId + " " + newpnum);
					}
				}

				menu();
				break;
			//////////////////////////////////
			// allows the user to delete the selected contact.
			case "d":

				selectedId = firstName.size() - 1;
				firstName.remove(selectedId);
				lastName.remove(selectedId);
				phoneNumber.remove(selectedId);
				System.out.println("Record deleted." + "\n");

				menu();
				break;
			/////////////////////////////
			// allows the user to select a contact to be the current contact.
			case "s":

				selectedId = firstName.size() - 1;
				firstName.contains(firstName.get(selectedId));

				printallrecord();

				System.out.println("Enter record id to be current Id.");

				selectedId = Integer.parseInt(input.next());
				System.out.println();
				showCurr();
				System.out.println(" ");

				menu();
				break;

			///////////////////////////
			// to terminate the program.
			case "q":
				System.exit(0);
				///////////////////////////
			default:
				System.out.println("Invalid entry.");
			}

		} while (!selectedMenu.equals("q"));

	}

	/////////////////////////// prints all the records that were
	/////////////////////////// inputed.//////////////////////////////
	/*
	 * pre- condition: none post-condition: prints all the records in the list.
	 * and assigns a record id to each of them. 0,1,2,....,n
	 */
	private static void printallrecord() {
		int id = -1;
		for (int i = 0; i < firstName.size(); i++) {
			id++;
			System.out.println(id + "\nFirst name: " + firstName.get(i) + "\nLastName: " + lastName.get(i)
					+ "\nPhone Number: " + phoneNumber.get(i) + "\n");
		}

	}

	///////////////////////// shows current
	///////////////////////// record/////////////////////////////////////////////////
	/*
	 * pre-condition: user has to enter "s" to select this. post: prints out the
	 * selected current record after the user types in record id.
	 */
	private static void showCurr() {
		System.out.println("Current record is: " + firstName.get(selectedId) + " " + lastName.get(selectedId) + " "
				+ phoneNumber.get(selectedId));
		System.out.println(" ");
	}

	///////////////////// check to see if selected witin
	///////////////////// index.////////////////////////
	/*
	 * pre-condition: none. post: makes sure that user input for the record id
	 * is greater than 0. if less than it will prompt "contacted selected".
	 */
	private static boolean checkSelected(int selectedId) {
		if (selectedId < 0) {
			System.out.println("no contacted selected. ");
			return false;
		} else {
			return true;
		}

	}

	//////////////////////////// menu method////////////////////////////////////
	/*
	 * pre-condition: none. post-condition: prints out the menu options.
	 */
	private static void menu() {
		System.out.println("a: Print all records.");
		System.out.println("d: Delete the current record.");
		System.out.println("f: Change the first name of the selected record.");
		System.out.println("l: Change the last name of the selected record.");
		System.out.println("n: Add a new record to the list.");
		System.out.println("p: Change the phone number [xxx-xxx-xxxx]");
		System.out.println("q: To exit.");
		System.out.println("S: To select a current contact.");

	}

	////////////// sorting last name///////////////////////////////////////////
	/*
	 * pre-condition: has to be used in option n and sorts in alphabetical order
	 * from last name needs to have an input for first name, last name, and
	 * phone number.
	 * 
	 * post-condition: goes through all the list and makes sure that last name
	 * gets sorted in alphabetical order.
	 */
	public static void lastNameSort(Object firstname1, Object lastname1, Object phonenumber1) {
		for (int i = 0; i < lastName.size(); i++) {
			String l = lastName.toString();
			String currentname = (lastName.get(i).toString());
			if (l.compareTo(currentname) < 0) {
				selectedId = lastName.lastIndexOf(lastname1);
				firstName.add(i, firstname1);
				lastName.add(i, lastname1);
				phoneNumber.add(i, phonenumber1);
				break;
			} else if (l.compareTo(currentname) == 0) {
				for (int j = 0; j < firstName.size(); j++) {
					String f = firstname1.toString();
					String currentSec = ((LinkedList<Object>) firstname1).get(j).toString();
					if (f.compareTo(currentSec) < 0) {
						selectedId = lastName.lastIndexOf(lastname1);
						firstName.add(j, firstname1);
						lastName.add(j, lastname1);
						phoneNumber.add(j, phonenumber1);
						break;
					} else if (l.compareTo(currentname) > 0 && (i == (lastName.size() - 1))) {
						selectedId = lastName.lastIndexOf(lastname1);
						firstName.add(firstname1);
						lastName.add(lastname1);
						phoneNumber.add(phonenumber1);

					}
				}

			}
		}
	}

	//////////////////////////////////////////////////////////////////////
	/*
	 * pre-condition: user need to use option "l". and takes in new last name.
	 * 
	 * post-condition: gets the new last name that will modify the last name of
	 * the current id it will go through the last name list and sorts it
	 * alphabetical order and puts that contact in it is proper position.
	 */
	public static void sortLastName(Object last_name) {
		for (int i = 0; i < lastName.size(); i++) {
			String l = lastName.toString();
			String currentname = (lastName.get(i).toString());
			if (l.compareTo(currentname) < 0) {
				selectedId = lastName.lastIndexOf(last_name);

				lastName.add(i, last_name);

				break;
			} else if (l.compareTo(currentname) == 0) {
				for (int j = 0; j < firstName.size(); j++) {
					String f = firstName.toString();
					String currentSec = ((LinkedList<Object>) firstName).get(j).toString();
					if (f.compareTo(currentSec) < 0) {
						selectedId = lastName.lastIndexOf(last_name);

						lastName.add(j, last_name);

						break;
					} else if (l.compareTo(currentname) > 0 && (i == (lastName.size() - 1))) {
						selectedId = lastName.lastIndexOf(last_name);

						lastName.add(last_name);

					}
				}

			}
		}

	}

}
