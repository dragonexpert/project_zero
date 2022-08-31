package revature;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao extends DatabaseConnection implements EmployeeDaoInterface {

	public EmployeeDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Employee getEmployee(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		Employee employee = null;
        String sql = "SELECT * FROM users u, employees e WHERE u.userid=e.userid AND  u.username = ?";
        try
        {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setString(1, username);
		
        	ResultSet resultSet = preparedStatement.executeQuery();
        	if(resultSet.next())
        	{
        		if(password.equals(resultSet.getString("password")))
        		{
        			
            			 employee = new Employee(resultSet.getInt("userid"), username, password, resultSet.getString("email"), 
            					 resultSet.getInt("employeeid"), resultSet.getBoolean("admin"), resultSet.getString("first_name"), resultSet.getString("last_name"));
        		}
        		else
            	{
            		throw new UserNotFoundException("The username or password is not correct.");
            	}
        	}
        	else
        	{
        		throw new UserNotFoundException("The username or password is not correct.");
        	}
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        return employee;
	}

}
