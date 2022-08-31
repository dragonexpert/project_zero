package revature;

public abstract class Menu {

	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	public static void generateMainMenu()
	{
		System.out.println("Please choose from the following options:");
		System.out.println("1) Login to your account.");
		System.out.println("2) Register for an online account.");
		System.out.println("3) If you are a bank employee");
	}

	public static void generateCustomerMenu()
	{
		System.out.println("What would you like to do next?");
		System.out.println("1) Check balance");
		System.out.println("2) Withdraw Money");
		System.out.println("3) Deposit Money");
		System.out.println("4) Transfer Money");
		System.out.println("5) Apply for new bank account");
		System.out.println("6) Change Password");
		System.out.println("7) Logout");
		System.out.println("Selection: ");
	}
	
	public static void generateEmployeeMenu(Employee employee)
	{
		System.out.println("Please select from the following options: ");
		System.out.println("0) Logout of account");
		System.out.println("1) View Accounts");
		System.out.println("2) View pending applications");
		System.out.println("3) Approve pending application");
		System.out.println("4) Reject pending application");
		if(employee.isAdmin())
		{
			System.out.println("5) Deposit money into account");
			System.out.println("6) Withdraw money from an account");
			System.out.println("7) Transfer money from an account");
			System.out.println("8) Delete an account");
		}
		System.out.println("Your choice: ");
	}
}
