package hotel_system_string_array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author BhanukaBandara
 */

public class HotelArray {

   // Declaring variables using the keywords public and static for them
	public static String[] hotel = new String[11];
	public static int roomNo;
	public static String roomName;
	public static Scanner sc = new Scanner(System.in);

	// Main method and the main in the program
	public static void main(String[] args) {
		System.out.println();
		System.out.println("     WELCOME TO HOTEL JETWING YALA  ");
		System.out.println("$++++++++++++++++++++++++++++++++++++$");
		System.out.println();

		// calling the method initialize
		initialise(hotel);

		// calling the method Main interface
		mainPage();

		// calling the method selections
		selections();
	}
        // Initializing the array as empty one
	private static void initialise(String hotel[]) {
		for (int i = 0; i < hotel.length; i++) {
			hotel[i] = "empty";
		}
	}
        
        //Main interface of the program
	public static void mainPage() {
		System.out.println("--- Selections---");
		System.out.println("===============================");
		System.out.println("--- Enter V : View Rooms ---");
		System.out.println("--- Enter A : Add new Customer ---");
		System.out.println("--- Enter E : Display Empty Room ---");
		System.out.println("--- Enter D : Delete Customer from Room ---");
		System.out.println("--- Enter F : Find Room from customer name ---");
		System.out.println("--- Enter T : Store program data into a text file ---");
		System.out.println("--- Enter L : Load Program data from file ---");
		System.out.println("--- Enter O : View rooms ordered alphabetically by name ---");
		System.out.println("--- Enter Q : Quit the program ---");
	}
        
        
        
        //Selecting sub menus in the main menu 
	public static void selections() {

		boolean startAgain = true;
		while (startAgain == true) {
			System.out.print("--- Enter your selections: ");
			String selected = sc.next().toLowerCase();
			if (selected.equals("v")) {
				view();
			} else if (selected.equals("a") || selected.equals("A")) {
				addDetails();
			} else if (selected.equals("e") || selected.equals("E")) {
				emptyRooms();
			} else if (selected.equals("d") || selected.equals("D")) {
				deleteDetails();
			} else if (selected.equals("f") || selected.equals("F")) {
				search();
			} else if (selected.equals("t") || selected.equals("T")) {
				storeDetails();
			} else if (selected.equals("l") || selected.equals("L")) {
				load();
			} else if (selected.equals("o") || selected.equals("O")) {
				displayOrderedRooms();
			} else if (selected.equals("q") || selected.equals("Q")) {
				System.exit(0);
			} else {
				System.out.println("--- Invaild Input Check The Main Menu... ---");
			}
			System.out.print("--- Do you want to continue press (Y/N): ");
			String Addagain = sc.next().toLowerCase();
			if (Addagain.equals("n") || Addagain.equals("No") || Addagain.equals("NO") || Addagain.equals("no") || Addagain.equals("N")) {
				startAgain = false;
			} else if (Addagain.equals("y") || Addagain.equals("Yes") || Addagain.equals("YES") || Addagain.equals("yes") || Addagain.equals("Y")) {
				startAgain = true;
			} else {
				System.out.println("---Your Input is Invalid... Try Again... ---");
			}
		}
	}


	// Adding customer details to the program
	public static void addDetails() {

		System.out.print("--- Enter room number 1 to 10: --- ");
		if (sc.hasNextInt()) {
			roomNo = sc.nextInt();
		} else {
			System.out.println("--- Invaild input!!! Enter room number 1 to 10: ---");
			
		}
		if((roomNo>0)&& (roomNo<11)){
		System.out.print("---Enter name for room " + roomNo + " : ");
		if (sc.hasNext()) {
			roomName = sc.next().toLowerCase();
		} else {
			System.out.println("--- Invaild Input!! Enter name for room " + roomNo+ " : ");
			System.exit(0);
		}
		hotel[roomNo] = roomName;
		}else{
			System.out.println("--- Invaild Input!!! Please enter a valid number withing the range of 1 to 10 ---");
		}
	}

	// View empty and occupied rooms and empty rooms
	public static void view() {
		for (int i = 1; i < hotel.length; i++) {
			if (hotel[i].equals("empty")) {
				System.out.println("--- Room " + (i) + " is Empty ---");
			} else {
				System.out.println("--- Room " + (i) + " is occupied by "+ hotel[i]);
			}
		}
	}

	// Displaying empty rooms in the hotel program after added some details to the program
	public static void emptyRooms() {
		for (int i = 1; i < hotel.length; i++) {
			if (hotel[i].equals("empty")) {
				System.out.println("--- Room " + (i) + " is Empty ---");
			}
		}
	}

	// Deleting the customer details from the program
	public static void deleteDetails() {
		try {
			System.out.println("--- Enter room number: ");
			roomNo = sc.nextInt();

			if (roomNo < hotel.length) {
				hotel[roomNo] = "empty";
				System.out.println("--- File successfully deleted!!! ---");
			}
		} catch (Exception e) {
			System.out.println("--- Room Details.txt not found --- ");
		}
	}

	// Search for room information
	public static void search() {
		System.out.print("--- Enter the Room Name: ");
		String answer = sc.next().toLowerCase();
		int i = 1;
		try{
			for (;i < hotel.length; i++) {
				if (hotel[i].equals(answer)) {
					break;
				} 
			}
			if (hotel[i].equals(answer)) {
				System.out.println("--- The customer "+answer+" is occupying room no." +i);
			
			} 
			
		} catch (Exception e){
			System.out.println("--- The customer you are looking for is unavailable ---");

		}
		
	}


	// Store room data in Room Details.txt file
	public static void storeDetails() {
		try {
			File room = new File("Room_Details.txt");
			room.createNewFile();

			FileWriter roomInfo = new FileWriter(room, true);

			BufferedWriter bWriter = new BufferedWriter(roomInfo);

			bWriter.write(" Room Number \t\t Room Name ");
			bWriter.newLine();
			for (int x = 0; x < hotel.length; x++) {
				bWriter.write("\n" + x + "\t\t" + hotel[x]);
				bWriter.newLine();
			}

			bWriter.flush();

			bWriter.close();

			System.out.println("--- Successfully ADDED the Details ---");

		} catch (IOException e) {
			System.out.println("--- File Not Found Please Check Customer File ---");
		}
	}

	// Load details in text file to an array structure
	public static void load() {

		// BufferedReader br = new BufferedReader(reader);

		BufferedReader bufferreader = null;

		try {
			// FileReader bra = new FileReader("res/database.txt");
			bufferreader = new BufferedReader(new InputStreamReader(
					new FileInputStream("Room Details.txt")));
			String delims = "\t" + "\t";
			int Num = 0;
			String Name = "";

			bufferreader.readLine();
			bufferreader.readLine();

			for (String line = bufferreader.readLine(); line != null; line = bufferreader.readLine()) {
				StringTokenizer st = new StringTokenizer(line, delims);

				while (st.hasMoreElements()) {
					Num = Integer.valueOf((String) st.nextElement());
					Name = (String) st.nextElement();

					if (hotel.length > Num) {
						hotel[Num] = Name;
					}
				}
			}

		} catch (Exception e) {

			System.err.println("---Data not Found---");

		} finally {
			try {
				bufferreader.close();
			} catch (IOException e) {
			}
		}
	}
        
	// Displaying rooms according to alphabetical order
	public static void orderRooms(String array[]) {
		boolean flag = true;
		String temp;

		while (flag) {
			flag = false;
			for (int j = 0; j < (array.length - 1); j++) {
				if (array[j].compareToIgnoreCase(array[j + 1]) > 0) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					flag = true;
				}
		}
		}
	}
        
         //Displaying the room that have been orderd 
	public static void displayOrderedRooms() {
		orderRooms(hotel);
		for (int i = 0; i < hotel.length; i++) {
			if (!(hotel[i].equals("--- Empty ---"))) {
				System.out.println("--- Room Name is: " + hotel[i]+" ----");
			}
		}
	}
        
        

}
