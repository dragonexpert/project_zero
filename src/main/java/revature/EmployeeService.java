package revature;

import java.util.Scanner;

public class EmployeeService {

	public static void approvePendingAccountApplication(AccountApplicationDao accountApplicationDao,  Scanner sc)
	{
		int recordChoice = -1;
		accountApplicationDao.viewPendingAccountApplications();
		System.out.println("Which record would you like to approve? ( Enter 0 to go back )");
		recordChoice = 0;
		try
		{
				recordChoice = sc.nextInt();
				accountApplicationDao.approveAccountApplication(recordChoice);
		}
		catch(Exception e)
		{
			sc.close();
			recordChoice = 0;
			sc = new Scanner(System.in);
			e.printStackTrace();
		}
	}
	
	public static void rejectPendingAccountApplication(AccountApplicationDao accountApplicationDao, Scanner sc)
	{
		int recordChoice = 0;
		accountApplicationDao.viewPendingAccountApplications();
		System.out.print("Which record would you like to reject? ");
		try
		{
			recordChoice = sc.nextInt();
			accountApplicationDao.rejectApplication(recordChoice);
		}
		catch(Exception e)
		{
			sc.close();
			recordChoice = 0;
			sc = new Scanner(System.in);
			e.printStackTrace();
		}
	}
}
