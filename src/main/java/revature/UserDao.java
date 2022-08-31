package revature;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao extends DatabaseConnection implements UserDaoInterface {

	private Logger fileLogger;
	
	public UserDao() {
		super();
		this.fileLogger = LoggerFactory.getLogger("fileLogger");
	}

	public boolean validateUsername(String username)
	{
		String pattern ="^([a-zA-Z]){1}([a-zA-Z0-9_]){5,29}$";
		boolean valid = username.matches(pattern);
		if(valid)
		{
			return true;
		}
		System.out.println("Invalid username. Username Rules: ");
		System.out.println("6 to 30 characters with first character being a letter.");
		System.out.println("Only letters, numbers, and an underscore are allowed.");
		return false;
	}
	
	public boolean usernameExists(String username)
	{
		 String sql = "SELECT * FROM users WHERE username = ?";
	        try
	        {
	        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        	preparedStatement.setString(1, username);
			
	        	ResultSet resultSet = preparedStatement.executeQuery();
	        	fileLogger.debug("Checking if username exists: " + username);
	        	return resultSet.next();
	        }
	        catch(SQLException e)
	        {
	        	fileLogger.debug("SQL Exception checking if username exists.\n" + e.getMessage());
	        	return false;
	        }
	}
	
    public Customer getUser(String username, String password) throws UserNotFoundException
    {
    	Customer customer = null;
        String sql = "SELECT * FROM users WHERE username = ?";
        try
        {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setString(1, username);
		
        	ResultSet resultSet = preparedStatement.executeQuery();
        	fileLogger.debug("Using getUser method for username: " + username);
        	if(resultSet.next())
        	{
        		if(password.equals(resultSet.getString("password")))
        		{
        			fileLogger.debug("Account found");
        			// Retrieve their account
        			sql = "SELECT * FROM users u, accounts a WHERE u.userid=a.userid AND u.userid = ?";
        			PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
        			preparedStatement2.setInt(1, resultSet.getInt("userid"));
        			ResultSet customerResultSet = preparedStatement2.executeQuery();
        			if(customerResultSet.next())
        			{
            			 customer = new Customer(resultSet.getInt("userid"), username, password, resultSet.getString("email"), 
            					 customerResultSet.getString("first_name"), customerResultSet.getString("last_name"));
        			}
        			else
        			{
        				// They do not have a banking account.
        				customer = new Customer(resultSet.getInt("userid"), username, password, resultSet.getString("email"));
        			}
        		}
        		else
        		{
        			fileLogger.debug("Account not found");
        			// Invalid login
        			throw new UserNotFoundException("Invalid username or password.");
        		}
        	} // End username does exist
        	else
        	{
        		throw new UserNotFoundException("Invalid username or password.");
        	}
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        return customer;
    }
    

	@Override
	public void updateUser(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "UPDATE users SET username = ? password = ? email = ? WHERE userid = ?";
		fileLogger.debug("Attempting to update user information");
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getUsername());
			preparedStatement.setString(2, customer.getPassword());
			preparedStatement.setString(3,  customer.getEmail());
			preparedStatement.setInt(4, customer.getId());
			preparedStatement.execute();
			fileLogger.debug("User update successful");
		}
		catch(SQLException e)
		{
			fileLogger.debug("User update failed due to SQL Exception\n" + e.getMessage());
		}
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM users WHERE userid = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			fileLogger.debug("Deleting user: " + id + " from the user table.");
			return true;
		}
		catch(SQLException e)
		{
			fileLogger.debug("There was an SQL error deleting a user. " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * This function should primarily be used by unit tests to clean up unwanted user accounts.
	 * @param username The name of the user
	 * @param password The password for the user.
	 * @return true or false
	 */
	public boolean deleteUserByUsername(String username, String password) {
		String sql = "DELETE FROM users WHERE username = ? AND password = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.execute();
			fileLogger.debug("Deleting a user by username / password combination");
			return true;
		}
		catch(SQLException e)
		{
			fileLogger.debug("SQL Error deleting a user by username / password");
			return false;
		}
	}

	@Override
	public boolean createUser(String username, String password, String email) {
		// TODO Auto-generated method stub
		fileLogger.debug("Inserting a new user into the database");
		String sql = "INSERT INTO users (username, password, email) VALUES ( ? , ?, ? )";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.execute();
			return true;
		}
		catch(SQLException e)
		{
			fileLogger.debug("SQL Error inserting a user\n" + e.getMessage());
		}
		return false;
	}

	@Override
	public void changePassword(Customer customer, String newPassword) {
		String sql = "UPDATE users SET password = ? WHERE userid = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, customer.getId());
			preparedStatement.execute();
			fileLogger.debug("Changed password for user");
		}
		catch(SQLException e)
		{
			fileLogger.debug("SQL Error updating password\n" + e.getMessage());
		}
	}
}