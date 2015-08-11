import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
public class Account {
	private int number;
	private String name;
	private double balance=0;
	private SortedMap<Date,Transaction> datedTransactions= new TreeMap<Date, Transaction>(new DateComp());
	private ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();
	
	private HashMap <Integer,String> accountMap = new HashMap <Integer,String>();
	
	
	public Account(int number, String name){
		this.number=number;
		this.name=name;
	}
	
	public void removeTransactions(){
		datedTransactions.clear();
	}
	public void transactionInvoice(Transaction t){
		transactionHistory.add(t);
	}
	
	public void addTransaction(Transaction t){
		datedTransactions.put(t.getDate(), t);
	}
	
	public String printTransactions(){
		String translist=String.format("%-20s%-15s%s","Date","Type","Amount");
		translist+="\n";
		for(Transaction t: transactionHistory){
			translist+=String.format("%-20s%-15s%s",new SimpleDateFormat("EEE, d MMM yyyy").format(t.getDate()),t.getType(),t.getFormattedAmount());
			translist+="\n";
		}
		return translist;
	}

	
	
	public SortedMap<Date, Transaction> getDatedTransactions() {
		return datedTransactions;
	}

	public int getNumber() {
		return number;
	}
	
	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}
	
	public String getFormattedBalance(){
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		if (balance == 0)
			return "Do you want to close your account? ";
		else
		return nf.format(balance);
	}

	public void calcBalance(double balance) {
		
		this.balance+=balance;
		if(this.balance<0){
			this.balance-=35;
		}
	}

	@Override
	public String toString() {
		return String.format("%-10s%-10s%-10s","Account #","Name","Balance")+"\n" + String.format("%-10s%-10s%-10s",this.number,this.name,this.balance);
	}
	
}


class DateComp implements Comparator<Date>{
	
	@Override
	public int compare(Date d1, Date d2){
		return d1.compareTo(d2);
	}
}