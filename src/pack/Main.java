package pack;
import java.util.Scanner;

public class Main {

	static Person[] person = new Person[3];
	static Scanner scanner = new Scanner(System.in);
	static int indexPerson = -1;
	
	public static void main(String[] args) {
		//we have an array with accounts
		for (int i = 0; i < person.length; i++) {
			person[i]= new Person();
		}
		person[0].name = "Apostol Marcel";
		person[0].password = "1234";
		person[0].amount = 1250;
			
		person[1].name = "Balaban Daniel";
		person[1].password = "4567";
		person[1].amount = 1550;
				
		person[2].name = "Sava Andrei";
		person[2].password = "7899";
		person[2].amount = 1200;
		String option;
		do {
			String name;
			String password;
			int fail = 0;
			do {
				System.out.println("Enter your name and your password.");
				name = readName();
				password = readPassword();
				//here checks the name and password
				indexPerson = indexPerson(name, password);
				if (indexPerson != -1) {
					System.out.println("Congratulations.You entred into account.");
				} else {
					fail++;
					System.out.println("You provided the wrong the name or password.");
					if (fail < 3)
						System.out.println("Please insert the correct password.");
				}
				
			} while (indexPerson == -1 && fail < 3);
			// if one fails to provide the correct username and password 3 times
			if (fail == 3) {
				System.out.println("Card blocked.");
			} else {
				executeOperationsOnAccount(person[indexPerson]);
			}
			System.out.println("To finish write 'end'.To continue anything else.");
			scanner = new Scanner(System.in);
			option = scanner.nextLine();
		} while (!option.equals("end"));
		System.out.println("Thank you.");
	}
	
	public static void executeOperationsOnAccount(Person p) {
		String optionChosen;
		do {
			//the list with the options
			System.out.println("Choose one option from the list:"); 															
			System.out.println("\tView");
			System.out.println("\tWithdraw");
			System.out.println("\tDeposit");
			System.out.println("\tExit account");
			System.out.println("\tChange password");
			Scanner scanner = new Scanner(System.in);
			optionChosen = scanner.nextLine();
			optionChosen = optionChosen.toLowerCase();
			
			if (optionChosen.equals("view")) {
				System.out.println("Amount in Euro: " + p.amount);
			} else if (optionChosen.equals("withdraw")) {
				System.out.println("Insert the amount you wish to withdraw:");
				int moneyWithdraw = scanner.nextInt();
				if (moneyWithdraw > p.amount) {
					System.out.println("Insufficient funds.");
				} else {
					p.amount -= moneyWithdraw;
				}
			} else if (optionChosen.equals("deposit")) {
				System.out.println("Insert the amount you wish to deposit:");
				int depositedAmount = scanner.nextInt();
				p.amount += depositedAmount;
			} else if (optionChosen.equals("change password")) {
				changePassword();
			}
		} while (!optionChosen.equals("exit account"));
		System.out.println("Are you out of your account.");
	}

	public static String readName() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name.");
		String enteredName = scanner.nextLine();
		return enteredName;
	}
	
	public static String readPassword() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your password.");
		String passIntroduced = scanner.nextLine();
		return passIntroduced;
	}
	
	public static int indexPerson(String name, String password) {
		int i;
		for (i = 0; i < person.length; i++) {
			if (name.equalsIgnoreCase(person[i].name) && password.equals(person[i].password)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Changes the user's password, first requesting him to provide old one
	 */
	public static void changePassword() {

		String oldPass = person[indexPerson].password;
		System.out.println("Enter the old password.");
		scanner = new Scanner(System.in);
		String pass = scanner.nextLine();
		if (pass.equals(oldPass)) {
			System.out.println("Enter the new password.");
			String newPass1 = scanner.nextLine();
			System.out.println("Type your password again.");
			String newPass2 = scanner.nextLine();
			if (newPass1.equals(newPass2)) {
				person[indexPerson].password = newPass2;
				System.out.println("Congratulations! You changed the password successfully.");
			}
		}
	}
	
	/**
	 * Identifies a person by his name, password and the amount of money on his card
	 */
	private static class Person {
		String name;
		String password;
		int amount;	
	}

}



