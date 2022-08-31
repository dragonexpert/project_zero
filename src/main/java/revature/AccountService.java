package revature;

import java.util.Scanner;

public abstract class AccountService {
	

	public static boolean withdrawMoney(Account account, AccountDao accountDao, Scanner sc)
	{
		double amount = 0;
		System.out.println("How much would you like to withdraw? ( Do not use $)");
		try
		{
			amount = sc.nextDouble();
			if(amount < 0)
			{
				throw new Exception("You must enter a number greater than 0.");
			}
			return account.withdrawMoney(amount, accountDao);
		}
		catch(Exception e)
		{
			sc.close();
			sc = new Scanner(System.in);
			System.out.println("");
			return false;
		}
	}
	
	public static boolean withdrawMoneyEmployee(Customer customer1, AccountDao accountDao, Scanner sc)
	{
		System.out.println("Which account do you want to withdraw money from?");
		accountDao.getAccountList();
		System.out.print("Account Id: ");
		Account account = null;
		double amount = 0.0;
		try
		{
			int accountIdChoice = sc.nextInt();
			account = accountDao.getAccountByAccountId(accountIdChoice);
			System.out.println("How much would you like to withdraw from the account?");
			amount = sc.nextDouble();
			if(amount < 0)
			{
				throw new Exception("Amount must be greater than 0");
			}
			if(amount > account.getBalance())
			{
				throw new Exception("The amount is more than the account has.");
			}
		}
		catch(Exception e)
		{
			sc = new Scanner(System.in);
			e.printStackTrace();
		}
		return account.withdrawMoney(amount, accountDao);
	}

	public static boolean depositMoney(Account account, AccountDao accountDao, Scanner sc)
	{
		double depositAmount = 0.0;
		System.out.println("How much would you like to deposit into your account?");
		try
		{
			depositAmount = sc.nextDouble();
			if(depositAmount <= 0)
			{
				throw new Exception("You must enter a number greater than 0.");
			}
			return account.depositMoney(depositAmount, accountDao);
		}
		catch(Exception e)
		{
			sc.close();
			sc = new Scanner(System.in);
			System.out.println("");
			return false;
		}
	}
	
	public static boolean depositMoneyEmployee(AccountDao accountDao, Scanner sc)
	{
		int accountIdChoice = 0;
		double amount = 0.0;
		Account account = null;
		System.out.println("Which account do you want to deposit money into?");
		accountDao.getAccountList();
		System.out.print("Account Id: ");
		try
		{
			accountIdChoice = sc.nextInt();
			System.out.println("How much would you like to deposit into the account?");
			amount = sc.nextDouble();
			if(amount < 0)
			{
				throw new Exception("Amount must be greater than 0");
			}
			account = accountDao.getAccountByAccountId(accountIdChoice);
			return account.depositMoney(amount, accountDao);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean transferMoneyCustomer(Customer customer1, AccountDao accountDao, Scanner sc)
	{
		Account destinationAccount = null;
		Account account = null;
		double amount = 0.0;
		System.out.println("Which account would you like to transfer money from?");
		accountDao.getAccountList(customer1.getId());
		try
		{
			int accountIdChoice = sc.nextInt();
			account = accountDao.getAccountByAccountId(accountIdChoice);
			if(account.getId() != customer1.getId())
			{
				throw new Exception("You do not own that account.");
			}
			System.out.println("Which account would you like to transfer money to?");
			accountDao.getAccountList(customer1.getId());
			int accountIdChoice2 = sc.nextInt();
			if(accountIdChoice == accountIdChoice2)
			{
				throw new Exception("Can't have the same account for withdraw as deposit.");
			}
			destinationAccount = accountDao.getAccountByAccountId(accountIdChoice2);
			System.out.println("How much would you like to transfer?");
			amount = sc.nextDouble();
			return account.transferMoney(amount, accountDao, destinationAccount);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean transferMoneyEmployee(Customer customer1, AccountDao accountDao, Scanner sc)
	{
		System.out.println("Which account would you like to transfer money from?");
		accountDao.getAccountList();
		Account account = null;
		Account destinationAccount = null;
		double amount = 0.0;
		try
		{
			int accountIdChoice = sc.nextInt();
			account = accountDao.getAccountByAccountId(accountIdChoice);
			System.out.println("Which account would you like to transfer money to?");
			accountDao.getAccountList();
			int accountIdChoice2 = sc.nextInt();
			if(accountIdChoice == accountIdChoice2)
			{
				throw new Exception("Can't have the same account for withdraw as deposit.");
			}
			destinationAccount = accountDao.getAccountByAccountId(accountIdChoice2);
			System.out.println("How much would you like to transfer?");
			amount = sc.nextDouble();
			account.transferMoney(amount, accountDao, destinationAccount);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void applyForAccount(Customer customer1 ,AccountApplicationDao accountApplicationDao, Scanner sc)
	{
		String account_type = null;
		System.out.println("New Account Application");
		System.out.print("Please enter your first name: ");
		String fname = sc.next();
		System.out.print("Please enter your last name: ");
		String lname = sc.next();
		int accountTypeChoice = 0;
		do
		{
			System.out.println("Please select the account type");
			System.out.println("1) Checking");
			System.out.println("2) Savings");
			try
			{
				accountTypeChoice = sc.nextInt();
				switch(accountTypeChoice)
				{
				case 1:
					account_type = "checking";
					break;
				case 2:
					account_type = "savings";
					break;
					default:
						accountTypeChoice = 0;
				}
			}
			catch(Exception e)
			{
				sc = new Scanner(System.in);
				System.out.println("Must choose the corresponding number.");
				accountTypeChoice = 0;
			}
		}
		while(accountTypeChoice == 0);
		boolean depositValid = true;
		double deposit = 0;
		do
		{
			System.out.print("Enter the amount of the deposit: ");
			try
			{
				deposit = sc.nextDouble();
				if(deposit <= 0)
				{
					depositValid = false;
					throw new Exception("Deposit must be greater than zero.");
				}
			}
			catch(Exception e)
			{
				sc = new Scanner(System.in);
			}
		}
		while(depositValid == false);
		boolean applicationSuccess = accountApplicationDao.applyForAccount(customer1.getId(), fname, lname, deposit, account_type);
		if(applicationSuccess)
		{
			System.out.println("Your application has been submitted and is awaiting approval.");
		}
		else
		{
			System.out.println("There was an error submitting your application.");
		}
	}
	
	public static void deleteAccount(AccountDao accountDao, Scanner sc)
	{
		int choice = 0;
		System.out.println("Which account do you wish to delete?");
		accountDao.getAccountList();
		System.out.println("Account ID: ( Press 0 to cancel )");
		try
		{
			choice = sc.nextInt();
			if(choice != 0)
			{
				String confirmString = "confirm" + choice;
				System.out.println("Please type 'confirm" + choice + "' to confirm");
				String inputConfirmString = sc.next();
				if(confirmString.equals(inputConfirmString))
				{
					accountDao.deleteAccount(choice);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void changePassword(Customer customer1, UserDao userDao, Scanner sc)
	{
		String currentPassword = null;
		String newPassword1, newPassword2 = null;
		System.out.println("Password Change Request");
		System.out.println("Please type your current password");
		currentPassword = sc.next();
		System.out.println("Enter your new password: ");
		newPassword1 = sc.next();
		System.out.println("Enter your new password again for confirmation: ");
		newPassword2 = sc.next();
		if(!customer1.getPassword().equals(currentPassword))
		{
			System.out.println("Wrong password.");
			return;
		}
		if(!newPassword1.equals(newPassword2))
		{
			System.out.println("New passwords do not match.");
			return;
		}
		userDao.changePassword(customer1, newPassword2);
	}
}
