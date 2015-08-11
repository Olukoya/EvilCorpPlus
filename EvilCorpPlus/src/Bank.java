import java.util.*;
import java.util.Random;
public class Bank {

	private HashMap<Integer,Account> members= new HashMap<Integer,Account>();

	public Account createMemberAccount(String name){
		Random r = new Random();
		int acct = 100000+r.nextInt(899999);
		Account a = new Account(acct, name);
		addMember(a);
		return a;
	}
	public void closeAccount(int acctnum){

		if(members.containsKey(acctnum)){
			Account a = members.get(acctnum);
			if(a.getBalance()==0){
				members.remove(acctnum);
			}
		}
	}

	public HashMap<Integer, Account> getMembers() {
		return members;
	}

	public void addMember(Account a){
		members.put(a.getNumber(), a);
	}

	public boolean findMember(int key){
		if(members.containsKey(key)){
			return true;
		}
		else return false;
	}

	public Account getMemberAccount(int num){
		return members.get(num);
	}

	public void processAllTransactions(Account a){
		Iterator<Transaction> i = a.getDatedTransactions().values().iterator();
		while(i.hasNext()){
			Transaction t = (Transaction)i.next();
			processTransaction(t,a);
			a.transactionInvoice(t);
		}
		a.removeTransactions();
	}

	public void processTransaction(Transaction t, Account a){
		double amount = t.getAmount();
		switch(t.getType()){
		case("check"):a.calcBalance(-amount);
		break;
		case("debit"): a.calcBalance(-amount);
		break;
		case("withdraw"):a.calcBalance(-amount);
		break;
		case("deposit"):a.calcBalance(amount);
		break;
		}
	}

}