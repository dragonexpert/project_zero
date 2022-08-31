package revature;

public interface AccountApplicationDaoInterface {
	boolean applyForAccount(int userid, String first_name, String last_name, double deposit, String account_type);
	
	void approveAccountApplication(int id);
	
	void rejectApplication(int id);
}
