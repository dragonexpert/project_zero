package com.revature.bankApp;

public class Account {
	private int accountNumber;
	private int routingNumber;
	private double balance;
	private String accountType;
	// This references the id in users.
	private int accountHolderId;
	
	public Account(int accountNumber, int routingNumber, double balance, String accountType, int accountHolderId) {
		super();
		setAccountNumber(accountNumber);
		setRoutingNumber(routingNumber);
		setAccountType(accountType);
		setAccountHolderId(accountHolderId);
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public int getRoutingNumber() {
		return routingNumber;
	}
	
	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = routingNumber;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		if(accountType.equals("Checking") || accountType.equals("Savings"))
		{
			this.accountType = accountType;
		}
		else
		{
			this.accountType = "Unknown";
		}
	}
	
	public int getAccountHolderId() {
		return accountHolderId;
	}
	public void setAccountHolderId(int accountHolderId) {
		this.accountHolderId = accountHolderId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * This method adds money to the balance.
	 * @param amount The amount to add.
	 */
	public void addFunds(double amount)
	{
		if(amount > 0)
		{
			setBalance(getBalance() + amount);
		// TODO check if over a certain amount. If so, must fill out MSAR form
		}
		else
		{
			// TODO throw an exception
		}
	}
	
	/**
	 * This withdraws money from the account.
	 * @param amount The amount to withdraw.
	 * @return Whether the operation succeeded.
	 */
	public boolean withdrawFunds(double amount)
	{
		if(amount < balance)
		{
			setBalance(balance - amount);
			return true;
		}
		// TODO consider throwing an exception
		return false;
	}
	
}
