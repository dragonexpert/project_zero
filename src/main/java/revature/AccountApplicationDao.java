package revature;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountApplicationDao extends DatabaseConnection implements AccountApplicationDaoInterface {

	@Override
	public boolean applyForAccount(int userid, String first_name, String last_name, double deposit, String account_type) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO account_applications (userid, first_name, last_name, deposit, account_type) VALUES ( ? , ?, ?, ?, ? )";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userid);
			preparedStatement.setString(2, first_name);
			preparedStatement.setString(3, last_name);
			preparedStatement.setDouble(4, deposit);
			preparedStatement.setString(5, account_type);
			preparedStatement.execute();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void viewPendingAccountApplications()
	{
		// TODO Auto-generated method stub
				String sql = "SELECT * FROM applications WHERE approved = 0";
				try
				{
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet resultSet = preparedStatement.executeQuery();
					System.out.println("Record\t\tFirst Name\t\tLast Name\t\tDeposit\t\tAccount Type");
					while(resultSet.next())
					{
						System.out.println(resultSet.getInt("id") + "\t\t" + resultSet.getString("first_name") + "\t\t\t" + resultSet.getString("last_name") + "\t\t\t" + 
					resultSet.getDouble("deposit") + "\t\t" + resultSet.getString("account_type"));
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
	}

	@Override
	public void approveAccountApplication(int id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE account_applications SET approved = 1 WHERE id = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
			// The account was approved so now it is necessary to insert the record into the accounts table
			sql = "SELECT * from applications where id = ?";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
			preparedStatement2.setInt(1, id);
			ResultSet resultSet = preparedStatement2.executeQuery();
			if(resultSet.next())
			{
				sql = "INSERT INTO accounts (account_type, account_number, userid, balance, first_name, last_name) " +
						"VALUES (?, ?, ?, ?, ?, ?)";
				
				double deposit = resultSet.getDouble("deposit");
				
				int account_number = 98526 + (int)Math.floor(Math.random() * 10000);
				PreparedStatement preparedStatement3 = connection.prepareStatement(sql);
				preparedStatement3.setString(1, resultSet.getString("account_type"));
				preparedStatement3.setInt(2, account_number);
				preparedStatement3.setInt(3, resultSet.getInt("userid"));
				preparedStatement3.setObject(4, (Double) deposit, 2);
				preparedStatement3.setString(5, resultSet.getString("first_name"));
				preparedStatement3.setString(6, resultSet.getString("last_name"));
				
				preparedStatement3.execute();
				System.out.println("The application has been approved.");

			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void rejectApplication(int id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE account_applications SET approved = -1 WHERE id = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			System.out.println("The application has been rejected.");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
