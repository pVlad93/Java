package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	static Scanner scanner;
	final static boolean ReadFromFile = true;
	final static String FileName = "TestJava.txt";
	
	private static void showMenu() {
		System.out.println("Menu:");
		System.out.println("help\t\t- Afiseaza aceasta lista de comenzi");
		System.out.println("add\t\t- Adauga o noua persoana (inscriere)");
		System.out.println("check\t\t- Verifica daca o persoana este inscrisa la eveniment");
		System.out.println("remove\t\t- Sterge o persoana existenta din lista");
		System.out.println("update\t\t- Actualizeaza detaliile unei persoane");
		System.out.println("guests\t\t- Lista de persoane care participa la eveniment");
		System.out.println("waitlist\t- Persoanele din lista de asteptare");
		System.out.println("available\t- Numarul de locuri libere");
		System.out.println("guests_no\t- Numarul de persoane care participa la eveniment");
		System.out.println("waitlist_no\t- Numarul de persoane din lista de asteptare");
		System.out.println("subscribe_no\t- Numarul total de persoane inscrise");
		System.out.println("search\t\t- Cauta toti invitatii conform sirului de caractere introdus");
		System.out.println("quit\t\t- Inchide aplicatia");
	}
	
	private static void ShowInput(String input) {
		if (ReadFromFile) {
			System.out.println(input);
		}
	}
	
	private static Guest readNewGuest() {
		String lastName, firstName, email, phoneNumber;
		System.out.println("Se adauga o noua persoana");
		System.out.print("lastName: ");
		lastName = scanner.nextLine();
		ShowInput(lastName);
		System.out.print("firstName: ");
		firstName = scanner.nextLine();
		ShowInput(firstName);
		System.out.print("email: ");
		email = scanner.nextLine();
		ShowInput(email);
		System.out.print("phoneNumber: ");
		phoneNumber = scanner.nextLine();
		ShowInput(phoneNumber);
		return new Guest(lastName, firstName, email, phoneNumber);
	}
	
	private static boolean checkByName(GuestList guestList) {
		String lastName, firstName;
		System.out.print("lastName: ");
		lastName = scanner.nextLine();
		ShowInput(lastName);
		System.out.print("firstname: ");
		firstName = scanner.nextLine();
		ShowInput(firstName);
		if ( guestList.isSignedUpByName(lastName, firstName) ) {
			System.out.println("Persoana " + lastName + " " + firstName + " este inscrisa la eveniment.");
			return true;
		} else {
			System.out.println("Persoana " + lastName + " " + firstName + " nu este inscrisa la eveniment.");
			return false;
		}
		
	}
	
	private static boolean checkByEmail(GuestList guestList) {
		String email;
		System.out.print("email: ");
		email = scanner.nextLine();
		ShowInput(email);
		if ( guestList.isSignedUpByEmail(email) ) {
			System.out.println("Persoana avand e-mail " + email + " este inscrisa la eveniment");
			return true;
		} else {
			System.out.println("Persoana avand e-mail " + email + " nu este inscrisa la eveniment");
			return true;
		}
	}
	
	private static boolean checkByPhoneNumber(GuestList guestList) {
		String phoneNumber;
		System.out.print("telefon: ");
		phoneNumber = scanner.nextLine();
		ShowInput(phoneNumber);
		if ( guestList.isSignedUpByPhone(phoneNumber) ) {
			System.out.println("Persoana avand numarul de telefon " + phoneNumber + " este inscrisa la eveniment");
			return true;
		} else {
			System.out.println("Persoana avand numarul de telefon " + phoneNumber + " nu este inscrisa la eveniment");
			return false;
		}
	}
	 
	private static boolean checkGuest(GuestList guestList) {
		System.out.println("Indicati criteriul de cautare: ");
		System.out.println("1. nume");
		System.out.println("2. email");
		System.out.println("3. telefon");
		int choice = scanner.nextInt();
		scanner.nextLine();
		ShowInput(Integer.toString(choice));
		if ( choice == 1 ) {
			return checkByName(guestList);
		} else if ( choice == 2 ) {
			return checkByEmail(guestList);
		} else if ( choice == 3 ) {
			return checkByPhoneNumber(guestList);
		}
		return false;
	}
	
	private static void removeByName (GuestList guestList) {
		String lastName, firstName;
		System.out.print("lastName: ");
		lastName = scanner.nextLine();
		ShowInput(lastName);
		System.out.print("firstName: ");
		firstName = scanner.nextLine();
		ShowInput(firstName);
		if ( guestList.unsignByName(lastName, firstName) ) {
			System.out.println("Persoana " + lastName + " " + firstName + " a fost stearsa.");
		} else {
			System.out.println("Persoana " + lastName + " " + firstName + " nu a fost gasita.");
		}
	}
	
	private static void removeByEmail (GuestList guestList) {
		System.out.print("email: ");
		String email = scanner.nextLine();
		ShowInput(email);
		if ( guestList.unsignByEmail(email) ) {
			System.out.println("Persoana avand email " + email + " a fost stearsa.");
		} else {
			System.out.println("Persoana avand email " + email + " nu a putut fi gasita.");
		}
	}
	
	private static void removeByPhoneNumber (GuestList guestList) {
		System.out.print("telefon: ");
		String phoneNumber = scanner.nextLine();
		ShowInput(phoneNumber);
		if ( guestList.unsignByPhone(phoneNumber) ) {
			System.out.println("Persoana avand numarul de telefon " + phoneNumber + " a fost stearsa.");
		} else {
			System.out.println("Persoana avand numarul de telfon " + phoneNumber + " nu a putut fi gasita.");
		}
	}
	
	private static void removeGuest(GuestList guestList) {
		System.out.println("Indicati criteriul de stergere: ");
		System.out.println("1.\tnume");
		System.out.println("2.\temail");
		System.out.println("3.\ttelefon");
		int choice = scanner.nextInt();
		ShowInput(Integer.toString(choice));
		scanner.nextLine();
		if ( choice == 1 ) {
			removeByName(guestList);
		} else if ( choice == 2 ) {
			removeByEmail(guestList);
		} else if ( choice == 3 ) {
			removeByPhoneNumber(guestList);
		}
	}
	
	private static Guest authenticate(GuestList guestList) {
		System.out.println("Alegeti modul de autentificare, tastand");
		System.out.println("1. Nume si prenume");
		System.out.println("2. Email");
		System.out.println("3. Numar de telefon");
		
		int choice = 0;
		if ( scanner.hasNextInt() ) {
			choice = scanner.nextInt();
			ShowInput(Integer.toString(choice));
		}
		scanner.nextLine();
		if ( choice == 1 ) {
			String lastName = "", firstName = "";
			System.out.print("lastName: ");
			lastName = scanner.nextLine();
			System.out.print("firstName: ");
			firstName = scanner.nextLine();
			return guestList.returnGuestByName(lastName, firstName);
		} else if ( choice == 2 ) {
			String email = "";
			System.out.println("email: ");
			email = scanner.nextLine();
			return guestList.returnGuestByEmail(email);
		} else if ( choice == 3 ) {
			String phoneNumber = "";
			System.out.print("phoneNumber: ");
			phoneNumber = scanner.nextLine();
			return guestList.returnGuestByPhoneNumber(phoneNumber);
		}
		return new Guest(null,null,null,null);
	}
	
	private static void updateGuest ( GuestList guestList ) {
		Guest authenticatedGuest = authenticate(guestList);
		int choice = 0;
		if ( authenticatedGuest != new Guest(null,null,null,null) ) {
			System.out.println("Introduceti campul ce va fi actualizat:");
			System.out.println("1. lastName");
			System.out.println("2. firstName");
			System.out.println("3. email");
			System.out.println("4. phoneNumber");
			
			if ( scanner.hasNextInt() ) {
				choice = scanner.nextInt();
				ShowInput(Integer.toString(choice));
			}
			scanner.nextLine();
			
			if ( choice == 1 ) {
				System.out.print("Introduceti noul lastName: ");
				String newLastName = scanner.nextLine();
				ShowInput(newLastName);
				String firstName = authenticatedGuest.getFirstName();
				String email = authenticatedGuest.getEmail();
				String phoneNumber = authenticatedGuest.getPhoneNumber();
				Guest updatedGuest = new Guest (newLastName, firstName, email, phoneNumber);
				guestList.update(authenticatedGuest, updatedGuest);
			} else if ( choice == 2 ) {
				System.out.print("Introduceti noul firstName: ");
				String newFirstName = scanner.nextLine();
				ShowInput(newFirstName);
				String lastName = authenticatedGuest.getLastName();
				String email = authenticatedGuest.getEmail();
				String phoneNumber = authenticatedGuest.getPhoneNumber();
				Guest updatedGuest = new Guest (lastName, newFirstName, email, phoneNumber);
				guestList.update(authenticatedGuest, updatedGuest);
			} else if ( choice == 3 ) {
				System.out.println("Introduceti noul email: ");
				String newEmail = scanner.nextLine();
				ShowInput(newEmail);
				String firstName = authenticatedGuest.getFirstName();
				String lastName = authenticatedGuest.getLastName();
				String phoneNumber = authenticatedGuest.getPhoneNumber();
				Guest updatedGuest = new Guest (lastName, firstName, newEmail, phoneNumber);
				guestList.update(authenticatedGuest, updatedGuest);
			} else if ( choice == 4 ) {
				System.out.println("Introduceti noul numar de telefon: ");
				String newPhoneNumber = scanner.nextLine();
				ShowInput(newPhoneNumber);
				String firstName = authenticatedGuest.getFirstName();
				String lastName = authenticatedGuest.getLastName();
				String email = authenticatedGuest.getEmail();
				Guest updatedGuest = new Guest (lastName, firstName, email, newPhoneNumber);
				guestList.update(authenticatedGuest, updatedGuest);
			}
		}
	}
	
	private static void search (GuestList guestList) {
		System.out.print( "Introduceti sirul de caractere pentru cautare: " );
		String searchCriteria = scanner.nextLine();
		ShowInput(searchCriteria);
		System.out.println(guestList.partialSearch(searchCriteria));
	}	
	
	private static int getNoOfInvites() {
		while (true) {
			try {
				System.out.print("Introduceti numarul de locuri disponibile: ");
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Nu ai introdus o valoare intreaga. Te rog sa reincerci.");
				scanner.nextLine();
			} catch (NoSuchElementException e) {
				System.out.println("INPUT was closed and cannot be reopened! Abort...");
				return 0;
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			if (ReadFromFile) {
				scanner = new Scanner(new File(FileName));
			}
			else {
				scanner = new Scanner(System.in);
			}
			
			int nrLocuri = getNoOfInvites();

			ShowInput(Integer.toString(nrLocuri));
			GuestList guestListApp = new GuestList(nrLocuri);
			String input = new String();
			showMenu();
			
			try {
				scanner.nextLine();
			} catch (NoSuchElementException e) {
				System.out.println("INPUT was closed and cannot be reopened! Abort...");
			}
			
			while ( !(input.equalsIgnoreCase("quit")) ) {
				System.out.print("Astept comanda: ");
				input = scanner.nextLine();
				ShowInput(input);
				if ( input.equalsIgnoreCase("help") ) {
					showMenu();
				} else if ( input.equalsIgnoreCase("add") ) {
					guestListApp.addGuest(readNewGuest());
				} else if ( input.equalsIgnoreCase("check" ) ) {
					checkGuest(guestListApp);
				} else if ( input.equalsIgnoreCase("remove") ) {
					removeGuest(guestListApp);
				} else if ( input.equalsIgnoreCase("update") ) {
					updateGuest(guestListApp);
				} else if ( input.equalsIgnoreCase("search") ) {
					search( guestListApp );
				} else if ( input.equalsIgnoreCase("guests") ) {
					System.out.println("guestList: ");
					System.out.println( guestListApp.getGuestList() );
				} else if ( input.equalsIgnoreCase("waitlist") ) {
					System.out.println("waitList: ");
					System.out.println(guestListApp.getWaitList() );
				} else if ( input.equalsIgnoreCase("available") ) {
					System.out.println("Numarul de locuri disponibile: " + guestListApp.getAvailableSeats());
				} else if ( input.equalsIgnoreCase("guests_no") ) {
					System.out.println("Numarul de participanti la eveniment: " + guestListApp.getGuestNr() );
				} else if ( input.equalsIgnoreCase("waitlist_no") ) {
					System.out.println("Numarul de persoane din lista de asteptare: " + guestListApp.getWaitingListNr() );
				} else if ( input.equalsIgnoreCase("subscribe_no") ) {
					System.out.println("Numarul de persoane inscrise: " + guestListApp.getTotalGuests());
				} else if ( ! input.equalsIgnoreCase("quit") ) {
					System.out.println("Comanda eronata. Va rog repatati comanda. Pentru ajutor tastati help.");
				}
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
