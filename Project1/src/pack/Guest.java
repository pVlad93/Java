package pack;

public class Guest {

	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	
	public Guest(String lastName, String firstName, String email, String phoneNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		
		
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		Guest guestObj = (Guest) obj;
		
		boolean nameCheck = this.lastName.equals(guestObj.getLastName()) && 
				            this.firstName.equals(guestObj.getFirstName());
		
		boolean emailCheck = this.email.equals(guestObj.getEmail());
		
		boolean phoneCheck = this.phoneNumber.equals(guestObj.getPhoneNumber());
		
		if (( nameCheck && emailCheck && phoneCheck) == true) {
			return true;
		}
		return false;
	}
	
	public int hashCode() {
		return 13 * this.lastName.hashCode() + 17 * this.firstName.hashCode() + 23 * this.email.hashCode() + 27 * this.phoneNumber.hashCode();
	}
	
	public String toString() {
		return "lastName: " + this.lastName + " firstName: " + this.firstName + " email: " + this.email 
				+ " phoneNumber: " + this.phoneNumber;
	}
}
