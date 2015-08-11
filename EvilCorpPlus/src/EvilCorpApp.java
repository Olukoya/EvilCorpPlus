import java.util.*;

public class EvilCorpApp {

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	Bank bank = new Bank();
	WeatherMan w = new WeatherMan();
	boolean foundAccount = false, anotherAcct = true;
	System.out.println("Welcome to Evil Corp Savings and Loan");
	while (anotherAcct) {
	System.out.println("New User? Type \"new\"\n"
					 + "Existing User? Type Acc. No\n"
					 + "> ");
	Account user = null;
	while (!foundAccount) {
	String account = sc.nextLine();
	if (account.equalsIgnoreCase("new")) {
	System.out.println("Please Create an Account");
	System.out.println("Enter your name : ");

	
	String name = sc.nextLine().trim();
	while(!Validator.checkName(name)){
	System.out.println("Incorrect name, retry:");
	name = sc.nextLine().trim();
	}
	user = bank.createMemberAccount(name);
	foundAccount = true;
	System.out.println("An account has been created for "+
	 user.getName() + ". \nYour account"+" number is : " + user.getNumber());
	System.out.println("Enter your starting balance\n> ");

	// validate
	String initialAmount=sc.nextLine().trim();
	while(!Validator.checkTransactionAmount(initialAmount)){
	System.out.println("Please enter a correct amount :");
	initialAmount=sc.nextLine().trim();
	}
	bank.processTransaction(
	new Transaction("deposit", Double.parseDouble(initialAmount)), user);
	System.out.println("You have " + user.getFormattedBalance()+
	 " in your account.");
	
	}
	else if(!Validator.checkAccount(account)){
	System.out.println("You must enter a valide account.");
	foundAccount=false;
	}
	else if(bank.findMember(Integer.parseInt(account))) {
	user = bank.getMemberAccount(Integer.parseInt(account));
	foundAccount = true;
	System.out.println("Your account has been found : ");
	System.out.println(user);
	} else {
	System.out.println("Sorry your account has not been found please re-enter your account number : ");
	foundAccount = false;
	}
	}
	String type = "continue";
	while (!type.equals("-1")) {
	System.out
	.println("Enter a transaction type (Check, Debit, Deposit, Withdrawal) or -1 to finish : ");
	
	//validate
	type = sc.nextLine().trim().toLowerCase();
	while(!Validator.checkTransactionType(type)){
	System.out.println("Please Enter correct type :");
	type = sc.nextLine().trim().toLowerCase();
	}
	
	if (type.equals("-1")) {
	break;
	}
	System.out.println("Enter the amount of the "  +type+  ":");
	
	// validate
	String amt = sc.nextLine().trim();
	Double amount;
	
	while(!Validator.checkTransactionAmount(amt)){
	System.out.println("please enter a correct amount : ");
	amt = sc.nextLine().trim();
	}
	amount = Double.parseDouble(amt);
	System.out.println("Enter the date of the check (mm/dd/yyyy): ");
	
	// validate
	String date = sc.nextLine().trim();
	while(!Validator.checkDate(date)){
	System.out.println("please enter a valide date in the form of mm/dd/yyyy");
	date = sc.nextLine().trim();
	}
	
	Date d = w.getDate(date);
	Transaction t = new Transaction(type, amount);
	t.setDate(d);
	user.addTransaction(t);
	}
	bank.processAllTransactions(user);
	System.out.println("Transaction Summary");
	System.out.println(user.printTransactions());
	
	System.out.println("The account balance for " + user.getNumber()+  " is "  +user.getFormattedBalance());
	System.out.println("Do you want to enter another account? (Y/N)");
	String cont = sc.nextLine();
	if (cont.equalsIgnoreCase("n")) {
	anotherAcct = false;
	}
	foundAccount=false;
	}

	}
}