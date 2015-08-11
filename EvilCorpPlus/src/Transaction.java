import java.text.NumberFormat;
import java.util.*;
public class Transaction {
	private String type;
	private double amount;
	private Date date;
	

	public Transaction(String type, double amount){
		this.type = type;
		this.amount = amount;
		
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getType() {
		return type;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		
		return amount;
	}
	
	public String getFormattedAmount(){
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(amount);
	}
	
	
}