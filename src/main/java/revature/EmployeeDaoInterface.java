package revature;


public interface EmployeeDaoInterface {
	public Employee getEmployee(String username, String password) throws UserNotFoundException;
	
}