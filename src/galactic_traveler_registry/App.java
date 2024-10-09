package galactic_traveler_registry;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
	private Scanner scanner = new Scanner(System.in);
	private ArrayList<Model> dataList = new ArrayList<Model>();
	
	public void menu() {
		int choice;
		int flag = 0;
		
		do {
			System.out.println(">> Galactic Traveler Registry <<");
			System.out.println();
			System.out.println("1. Register Traveler");
			System.out.println("2. Update Traveler Info");
			System.out.println("3. Delete Traveler Info");
			System.out.println("4. View All Travelers");
			System.out.println("5. Close Registry");
			
			do {
				System.out.printf(">> ");
				choice = scanner.nextInt();
				scanner.nextLine();
				System.out.println();
				if (choice == 1) {
					registerData();
				} else if (choice == 2) {
					updateData();
				} else if (choice == 3) {
					deleteData();
				} else if (choice == 4) {
					viewTravelers();
					pressEnter();
				} else if (choice == 5) {
					System.out.println("Closing the registry...");
					flag = 1;
				}
			} while(choice < 1 || choice > 5);		
		} while(flag == 0);
		
	}
	
	private void registerData() {
		String name;
		int id;
		String input;
		
		do {
			System.out.printf("Input Traveler ID (1-9999): ");	
			input = scanner.nextLine();
			
			try {
				id = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				id = 0;
				System.out.println("Traveler's ID must be numeric!");
			}
			
		} while(id < 1 || id > 9999);
		
		int flag = 1;
		do {
			flag = 0;
			System.out.printf("Input Traveler Name (3-30 chars): ");
			name = scanner.nextLine();
			
			for (Model traveler : dataList) {
				if(traveler.getName().equalsIgnoreCase(name)) {
					System.out.println("Traveler's name must be unique!");
					flag = 1;
					break;
				}
			}
		} while(name.length() < 3 || name.length() > 30 || flag == 1);
		
		Model travelerData = new Model(name, id);
		dataList.add(travelerData);
		
		System.out.println();
		System.out.println("Traveler Registered:");
		System.out.println("Traveler ID: " + id);
		System.out.println("Traveler Name: " + name);

		pressEnter();
	}
	
	private void updateData() {
		viewTravelers();
		if (dataList.isEmpty()) {
			pressEnter();
			return;
		}
		
		int choice = 0;
		int id;
		String input;
		
		System.out.println();	
		System.out.printf("Which traveler do you want to update? (Enter 0 to return to the main menu): ");			
		choice = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		
		if (choice == 0) return;
		int flag = 1;
		do {
			if (flag == 0) {
				System.out.println("New Traveler ID must be between 1-9999!");
			}

			System.out.printf("Input new Traveler ID (1-9999): ");
			input = scanner.nextLine();
			
			try {
				id = Integer.parseInt(input);
				flag = 0;
			} catch (NumberFormatException e) {
				id = 0;
				System.out.println("Traveler's ID must be numeric!");
				flag = 1;
			} 
		} while(id < 1 || id > 9999);
		
		Model updateTravelerData = dataList.get(choice - 1);
		updateTravelerData.setId(id);
		
		System.out.println();
		System.out.println("Traveler Updated:");
		System.out.println("Traveler ID: " + id);
		System.out.println("Traveler Name: " + updateTravelerData.getName());
		pressEnter();
	}
	
	private void deleteData() {
		int choice = 0;
		viewTravelers();
		if (dataList.isEmpty()) {
			pressEnter();
			return;
		}
		System.out.println();
		System.out.printf("Which traveler do you want to delete? (Enter 0 to return to the main menu): ");
		choice = scanner.nextInt();
		scanner.nextLine();
		System.out.println();
		
		if (choice == 0) return;
		Model deleteTravelerData = dataList.get(choice - 1);
		dataList.remove(choice - 1);
		System.out.println("Traveler Deleted: " + deleteTravelerData.getName());
		pressEnter();
	}
	
	private void viewTravelers() {
		if (dataList.isEmpty()) {
			System.out.println("There is no Traveler's data yet." + "\n");
			return;
		} else {
			System.out.println("Registered Travelers:");
			int i = 1;
			
			for (Model travelersData : dataList) {
				System.out.println(i + ". ID: " + travelersData.getId() + ", Name: " + travelersData.getName());
				i++;
			}			
		}
		
		
	}
	
	private void pressEnter() {
		System.out.println("Press Enter to continue...");
		scanner.nextLine();
	}
	
	public App() {
		menu();
	}
}
