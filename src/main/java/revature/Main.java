package revature;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Endpoint: postgres82322.c0rmfsc1zrep.us-east-2.rds.amazonaws.com
// user: postgres
// pass: password1234
// port: 5432

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		Scanner sc = new Scanner(System.in);
		AccountDao accountDao = new AccountDao();
		AccountApplicationDao accountApplicationDao = new AccountApplicationDao();
		EmployeeDao employeeDao = new EmployeeDao();
		LoginService ls = new LoginService();
		int choice = 0;
		Customer customer1 = null;

		System.out.println("Welcome to Dragon Bank Inc.");
		do
		{
			Menu.generateMainMenu();
			try
			{
				choice = sc.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Invalid choice.");
				choice = 0;
				sc = new Scanner(System.in);
			}
		}
		while(choice == 0);
		
		switch(choice)
		{
			case 1:
				// Login 
			try 
			{
				customer1 = LoginService.login(userDao, sc);
			}
			catch (UserNotFoundException e2)
			{
				// TODO Auto-generated catch block
				e2.printStackTrace();
				break;
			}
				
			System.out.println("Welcome " + customer1.getFirstName() + " " + customer1.getLastName());
			int myChoice = 0;
			do
			{
				Account account = null;
				try {
					account = accountDao.getAccountByUserId("checking", customer1.getId());
				} catch (AccountNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Menu.generateCustomerMenu();
				try
				{
					myChoice = sc.nextInt();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Invalid choice.");
					myChoice = 0;
					sc = new Scanner(System.in);
				}
					
				switch(myChoice)
				{
					case 1:
						// Get current balance
						System.out.println("Displaying Balances");
						accountDao.getAccountList(customer1.getId());
						break;
					case 2:
						// Withdraw Money
						boolean validWithdraw = false;
						do
						{
							validWithdraw = AccountService.withdrawMoney(account, accountDao, sc);
						}
						while(validWithdraw == false);
							break;
					case 3:
						// Deposit Money
						boolean validDeposit = false;
						do
						{
							validDeposit = AccountService.depositMoney(account, accountDao, sc);
						}
						while(validDeposit == false);
						break;
					case 4:
						// Transfer Money
						AccountService.transferMoneyCustomer(customer1, accountDao, sc);
						break;
					case 5:
						// Apply for new account
						AccountService.applyForAccount(customer1, accountApplicationDao, sc);
						break;
					case 6:
						// Change Password
						AccountService.changePassword(customer1, userDao, sc);
						break;
					case 7:
						// Logout
						myChoice = 0;
						break;
					default:
						myChoice = 0;
						break;
				} // End switch
			} // End account activity menu
			while(myChoice != 0);
			break;
			case 2:
				// Register for account
				ls.createAccount(userDao, sc);
				break;
			case 3:
			Employee employee = null;
			try {
				employee = LoginService.employeeLogin(employeeDao, sc);
				}
			catch (UserNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				break;
			}
				System.out.println("Welcome to the employee terminal " + employee.getFirstName() + " " + employee.getLastName());
				choice = 0;
				do
				{
					Menu.generateEmployeeMenu(employee);
					try
					{
						choice = sc.nextInt();
						if(choice >= 5 && !employee.isAdmin())
						{
							throw new AccessDeniedException("Not authorized.");
						}
					
						// The choice is grabbed, now perform the next action
						switch(choice)
						{
						case 0:
							break;
						case 1:
							// View accounts
							accountDao.getAccountList();
							break;
						case 2:
							// View pending applications
							accountApplicationDao.viewPendingAccountApplications();
							break;
						case 3:
							// Approve pending application
							EmployeeService.approvePendingAccountApplication(accountApplicationDao, sc);
							break;
						case 4:
							// Reject pending application
							EmployeeService.rejectPendingAccountApplication(accountApplicationDao, sc);
							break;
						case 5:
							// Deposit money
							AccountService.depositMoneyEmployee(accountDao, sc);
							break;
						case 6:
							// Withdraw money
							AccountService.withdrawMoneyEmployee(customer1, accountDao, sc);
							break;
						case 7:
							// Transfer money
							AccountService.transferMoneyEmployee(customer1, accountDao, sc);
							break;
						case 8:
							// Delete account
							AccountService.deleteAccount(accountDao, sc);
							break;
						default:
							throw new Exception("Invalid choice.");
						}
					}
					catch(InputMismatchException e)
					{
						sc.close();
						e.printStackTrace();
						sc = new Scanner(System.in);
					}
					catch (AccessDeniedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while(choice != 0);
				break;
			default:
				break;
		} // Close initial menu choice
		System.out.println("Thank you for using the Dragon Bank system.");
		
		try {
			userDao.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}

}