package pack;

import java.util.ArrayList;

public class GuestList {
	private int maxGuests;
	private int guestCount = 0;
	private int iterator = -1; // return index
	private int select = 0; // return the list 
	private ArrayList<Guest> guestList = new ArrayList<Guest>();
	private ArrayList<Guest> waitList = new ArrayList<Guest>();
	
	public GuestList(int maxGuests) {
		this.maxGuests = maxGuests;
	}
	
	public int addGuest (Guest guest) {

		if (this.isSignedUp(guest) == true) {
			System.out.println("Esti deja inscris la eveniment XX");
			return -1;
		}
		
		guestCount ++;
		if(guestCount <= maxGuests) {
			guestList.add(guest);
			System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
			return 0;
		} else {
			waitList.add(guest); 
			System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " +
			                   ( waitList.size() ) + ". Te vom notifica daca un loc devine disponibil.");
			return guestCount;
		}
	}
	
	public boolean isSignedUp (Guest guest) {
		if (guestList.contains(guest) || waitList.contains(guest)) {
			return true;
		}
		return false;
	}
	
	public boolean isSignedUpByName (String lastName, String firstName) {
		for ( Guest g : guestList) {
			if ( g.getLastName().equalsIgnoreCase(lastName) && g.getFirstName().equalsIgnoreCase(firstName) )  {
				iterator = guestList.indexOf(g);
				select = 1;
				return true;
			}
		}
		
		for ( Guest g : waitList ) {
			if( g.getLastName().equalsIgnoreCase(lastName) && g.getFirstName().equalsIgnoreCase(firstName) ) {
				iterator = waitList.indexOf(g);
				select = 2;
				return true;
			}
		}
		select = 0;
		iterator = -1;
		return false;
	}
	
	public Guest returnGuestByName(String lastName, String firstName) {
		if ( isSignedUpByName(lastName, firstName) ) {
			if ( select == 1 ) {
				return guestList.get(iterator);
			} else if ( select == 2 ) {
				return waitList.get(iterator);
			}
		}
		return new Guest(null, null, null, null);
	}

	public boolean isSignedUpByEmail (String email) {
		int i = 0;
		for ( Guest g : guestList ) {
			if ( g.getEmail().equalsIgnoreCase(email) ) {
				iterator = i;
				select = 1;
				return true;
			}
			i++;
		}
		
		for ( Guest g : waitList ) {
			if ( g.getEmail().equalsIgnoreCase(email) ) {
				iterator = waitList.indexOf(g);
				select = 2;
				return true;
			}
		}
		select = 0;
		iterator = -1;
		return false;
	}
	
	public Guest returnGuestByEmail(String email) {
		if ( isSignedUpByEmail(email) ) {
			if ( select == 1 ) {
				return guestList.get(iterator);
			} else if ( select == 2 ) {
				return waitList.get(iterator);
			}
		}
		return null;
	}
	
	public boolean isSignedUpByPhone ( String phone ) {
		int i = 0;
		for ( Guest g : guestList ) {
			if ( g.getPhoneNumber().equalsIgnoreCase(phone) ) {
				iterator = i;
				select = 1;
				return true;
			}
			i++;
		}
		
		for ( Guest g : waitList ) {
			if ( g.getPhoneNumber().equalsIgnoreCase(phone) ) {
				iterator = waitList.indexOf(g);
				select = 2;
				return true;
			}
		}
		select = 0;
		iterator = -1;
		return false;
	}
	
	public Guest returnGuestByPhoneNumber ( String phoneNumber ) {
		if ( isSignedUpByPhone(phoneNumber) ) {
			if ( select == 1 ) {
				return guestList.get(iterator);
			} else if ( select == 2 ) {
				return waitList.get(iterator);
			}
		}
		return new Guest(null, null, null, null);
	}
	
	public boolean unsign (Guest guest) {
		
		if (this.isSignedUp(guest) == true) {
			guestCount--;
			// persoana este in waitList
			if(waitList.indexOf(guest) >= 0) {
				waitList.remove(waitList.indexOf(guest));
			} 
			// persoana este in guestList
			else if ( guestList.indexOf(guest) >= 0) {
				guestList.remove( guestList.indexOf(guest) );
				if ( !waitList.isEmpty() ) {
					guestList.add(waitList.get(0));
					waitList.remove(0);
					guestCount ++;
				} 
				
			}
			return true;
		}
		return false;
	}
	
	public boolean unsignByName(String lastName, String firstName) {
		if ( this.isSignedUpByName(lastName, firstName) ) {
			guestCount --;
			// persoana este in waitList
			if (select == 2) {
				this.waitList.remove(iterator);
			} else if ( select == 1 ) {
				this.guestList.remove(iterator);
				if (this.waitList.size() > 0) {
					this.guestList.add(this.waitList.remove(0));
					
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean unsignByEmail(String email) {
		if ( this.isSignedUpByEmail(email) ) {
			guestCount --;
			// persoana este in waitlist
			if ( select == 2 ) {
				this.waitList.remove(iterator);
			} else if ( select == 1 ) {
				this.guestList.remove(iterator);
				if ( this.waitList.size() > 0 ) {
					this.guestList.add(this.waitList.get(0));
					this.waitList.remove(0);
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean unsignByPhone(String phoneNumber) {
		if ( this.isSignedUpByPhone(phoneNumber) ) {
			guestCount --;
			if ( select == 2 ) {
				this.waitList.remove(iterator);
			} else if ( select == 1 ) {
				this.guestList.remove(iterator);
				if ( this.waitList.size() > 0 ) {
					this.guestList.add(this.waitList.get(0));
					this.waitList.remove(0);
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean update (Guest oldGuest, Guest updatedGuest) {
		String updatedGuestLastName = updatedGuest.getLastName();
		String updatedGuestFirstName = updatedGuest.getFirstName();
		String updatedGuestPhoneNumber = updatedGuest.getPhoneNumber();
		String updatedGuestEmail = updatedGuest.getEmail();

		String lastName = updatedGuestLastName.isEmpty() ? oldGuest.getLastName() : updatedGuest.getLastName();
		String firstName = updatedGuestFirstName.isEmpty() ? oldGuest.getFirstName() : updatedGuest.getFirstName();
		String phoneNumber = updatedGuestPhoneNumber.isEmpty() ? oldGuest.getPhoneNumber() : updatedGuest.getPhoneNumber();
		String email = updatedGuestEmail.isEmpty() ? oldGuest.getEmail() : updatedGuest.getEmail();
				
		Guest aux = new Guest(lastName, firstName, email, phoneNumber);
		
		if ( this.isSignedUp(aux) == true ) {
			System.out.println("Person already in list.");
			return false;
		} else {
			if (this.guestList.indexOf(oldGuest) >= 0) {
				this.guestList.set(this.guestList.indexOf(oldGuest), aux);
			} else {
				this.waitList.set(this.waitList.indexOf(oldGuest), aux);
			}
			return true;
		}
	}
	
	public boolean updateGuestByFirstName(String oldLastName, String oldFirstName, String newFirstName) {
		if ( returnGuestByName(oldLastName, oldFirstName) != new Guest(null,null,null,null) ) {
			Guest guest = returnGuestByName(oldLastName, oldFirstName);
			String email = returnGuestByName(oldLastName, oldFirstName).getEmail();
			String phoneNumber = returnGuestByName(oldLastName, oldFirstName).getPhoneNumber();
			Guest updatedGuest = new Guest(oldLastName, newFirstName, email, phoneNumber);
			return this.update(guest, updatedGuest);
		}
		return false;
	}

	public boolean updateGuestByLastName(String oldLastName, String oldFirstName, String newLastName) {
		if ( returnGuestByName(oldLastName, oldFirstName) != new Guest(null,null,null,null) ) {
			Guest guest = returnGuestByName(oldLastName, oldFirstName);
			String email = returnGuestByName(oldLastName, oldFirstName).getEmail();
			String phoneNumber = returnGuestByName(oldLastName, oldFirstName).getPhoneNumber();
			Guest updatedGuest = new Guest(newLastName, oldFirstName, email, phoneNumber);
			return this.update(guest, updatedGuest);
		}
		return false;
	}
	
	public boolean updateGuestByEmail(String oldEmail, String newEmail) {
		if ( returnGuestByEmail(oldEmail) != new Guest(null,null,null,null) ) {
			Guest guest = returnGuestByEmail(oldEmail);
			String lastName = guest.getLastName();
			String firstName = guest.getFirstName();
			String phoneNumber = guest.getPhoneNumber();
			Guest updatedGuest = new Guest(lastName, firstName, newEmail, phoneNumber);
			return this.update(guest, updatedGuest);
		}
		return false;
	}
	
	public ArrayList<Guest> getGuestList() {
		return this.guestList;
	}
	
	public ArrayList<Guest> getWaitList() {
		return this.waitList;
	}
	
	public int getGuestNr () {
		return this.guestList.size();
	}
	
	public int getAvailableSeats() {
		return this.maxGuests - this.guestList.size();
	}
	
	public int getWaitingListNr() {
		return this.waitList.size();
	}
	
	public int getTotalGuests() {
		return this.waitList.size() + this.guestList.size();
	}
	
	public ArrayList<Guest> partialSearch (String criteria) {
		ArrayList<Guest> matches = new ArrayList<>();
		for ( Guest g : this.guestList ) {
			if ( g.getFirstName().toLowerCase().contains(criteria.toLowerCase()) || g.getLastName().toLowerCase().contains(criteria.toLowerCase()) ||
					g.getEmail().toLowerCase().contains(criteria.toLowerCase()) || g.getPhoneNumber().toLowerCase().contains(criteria.toLowerCase()) ) {
				matches.add(g);
			}
			
		}
		
		for ( Guest g : this.waitList ) {
			if ( g.getFirstName().toLowerCase().contains(criteria.toLowerCase()) || g.getLastName().toLowerCase().contains(criteria.toLowerCase()) ||
					g.getEmail().toLowerCase().contains(criteria.toLowerCase()) || g.getPhoneNumber().toLowerCase().contains(criteria.toLowerCase()) ) {
				matches.add(g);
			}
		}
		return matches;
	}
}
