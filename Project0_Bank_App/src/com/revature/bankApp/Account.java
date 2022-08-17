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
	 * @throws Exception Throws exception if amount is negative.
	 */
	public void addFunds(double amount) throws Exception
	{
		if(amount > 0)
		{
			setBalance(getBalance() + amount);
		// TODO check if over a certain amount. If so, must fill out MSAR form
		}
		else
		{
			throw new Exception("Amount must be greater than $0.00");
		}
	}
	
	/**
	 * This withdraws money from the account.
	 * @param amount The amount to withdraw.
	 * @throws Exception Throws exception when funds exceed the balance or the withdrawal amount is higher than the bank daily limit.
	 */
	public void withdrawFunds(double amount) throws Exception
	{
		if(amount < balance)
		{
			if(amount <= Bank.getMaxWithdrawalPerDay())
			{
				setBalance(balance - amount);
			}
			else
			{
				throw new Exception("Amount withdrawn must be less than $" + Bank.getMaxWithdrawalPerDay());
			}
		}
		else
		{
			throw new Exception("Insufficient funds. Your current balance is: $" + balance);
		}
	}
	
}
