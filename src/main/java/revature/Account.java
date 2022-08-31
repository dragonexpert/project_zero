package revature;

import java.util.Objects;

public class Account {
	private int id;
	private String accountType;
	private int accountNumber;
	private int userId;
	private double balance;
	private String first_name;
	private String last_name;
	
	public Account(int id, String accountType, int accountNumber, int userId, double balance, String first_name, String last_name) {
		super();
		this.id = id;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.userId = userId;
		this.balance = balance;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	
	
	public String getFirstName() {
		return first_name;
	}



	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}



	public String getLastName() {
		return last_name;
	}



	public void setLastName(String last_name) {
		this.last_name = last_name;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, accountType, balance, first_name, id, last_name, userId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accountNumber == other.accountNumber && Objects.equals(accountType, other.accountType)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(first_name, other.first_name) && id == other.id
				&& Objects.equals(last_name, other.last_name) && userId == other.userId;
	}


	
	

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", accountNumber=" + accountNumber + ", userId="
				+ userId + ", balance=" + balance + ", first_name=" + first_name + ", last_name=" + last_name + "]";
	}



	public boolean withdrawMoney(double amount, AccountDao accountDao)
	{
		if(getBalance() < amount)
		{
			System.out.println("Insufficient funds.");
			return false;
		}
		// First update the object.
		setBalance(this.balance - amount);
		// Next update the database
		accountDao.withdrawFunds(this);
		System.out.println("Dispensing $" + amount + " Cash. New Balance: $" + String.format("%.2f", getBalance()));
		return true;
	}
	
	public boolean depositMoney(double amount, AccountDao accountDao)
	{
		if(amount < 0)
		{
			return false;
		}
		setBalance(this.balance + amount);
		// Update the database
		accountDao.depositFunds(this);
		System.out.println("Deposit Complete. New Balance: $" + String.format("%.2f", getBalance()));
		return true;
	}
	
	public boolean transferMoney(double amount, AccountDao accountDao, Account destinationAccount)
	{
		if(amount > this.balance)
		{
			System.out.println("The amount is greater than the balance.");
			return false;
		}
		setBalance(this.balance - amount);
		accountDao.withdrawFunds(this);
		destinationAccount.setBalance(destinationAccount.getBalance() + amount);
		accountDao.depositFunds(destinationAccount);
		System.out.println("The money transfer has been completed.");
		return true;
	}
}
