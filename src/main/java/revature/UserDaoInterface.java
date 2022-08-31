package revature;

public interface UserDaoInterface {
	
	public boolean usernameExists(String username);
	
	public boolean validateUsername(String username);

	public boolean createUser(String username, String password, String email);
	
	public Customer getUser(String username, String password) throws UserNotFoundException;
	
	public void updateUser(Customer customer);
	
	public void changePassword(Customer customer, String newPassword);
	
	public boolean deleteUser(int uid);
}
