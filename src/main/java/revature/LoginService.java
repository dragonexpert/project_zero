package revature;

import java.util.Scanner;



public class LoginService {
	

	public static Customer login(UserDao userDao, Scanner sc) throws UserNotFoundException
	{
		String username, password = null;
		System.out.print("Please enter your username: ");
		username = sc.next();
		System.out.print("Please enter your password: ");
		password = sc.next();
		return userDao.getUser(username, password);
	}
	
	public static Employee employeeLogin(EmployeeDao employeeDao, Scanner sc) throws UserNotFoundException
	{
		String username, password = null;
		System.out.print("Please enter your username: ");
		username = sc.next();
		System.out.print("Please enter your password: ");
		password = sc.next();
		return employeeDao.getEmployee(username, password);
	}
	
	
	public boolean register(String username, String password, String email, UserDao userDao)
	{
		// New username
		if(!userDao.usernameExists(username))
		{				
			// Make sure username follows rules.
			if(userDao.validateUsername(username))
			{
				userDao.createUser(username, password, email);
				return true;
			}
			return false;
		}
		System.out.println("Username already exists.");
		return false;
	}
	
	public void createAccount(UserDao userDao, Scanner sc)
	{
		String username, password, email = null;
		boolean accountCreated = false;
		do
		{
			System.out.println("Create An Account");
			System.out.print("Please enter your desired username ( Type exit to go back ): ");
			username = sc.next();
			if(username.toLowerCase().equals("exit"))
			{
				break;
			}
			System.out.print("Please enter your email address: ");
			email = sc.next();
			System.out.print("Create password: ");
			password = sc.next();
			accountCreated = this.register(username, password, email, userDao);
		}
		while(accountCreated == false);
	}

}
