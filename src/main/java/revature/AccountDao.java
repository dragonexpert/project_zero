package revature;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountDao extends DatabaseConnection implements AccountDaoInterface {
	
	private Logger fileLogger;

	public AccountDao() {
		super();
		this.fileLogger = LoggerFactory.getLogger("fileLogger");
	}


	@Override
	public Account getAccountByUserId(String accountType, int userId) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		String sql = "SELECT *, balance::numeric::float8 as account_balance FROM accounts WHERE account_type = ? AND userid = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, accountType);
			preparedStatement.setInt(2, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				fileLogger.debug("getAccountByUserId Success");
				return new Account(resultSet.getInt("id"), resultSet.getString("account_type"), resultSet.getInt("account_number"),  resultSet.getInt("userid"), resultSet.getDouble("account_balance"),
						resultSet.getString("first_name"), resultSet.getString("last_name"));
			}
			else
			{
				throw new AccountNotFoundException("Account not found");
			}
		}
		catch(SQLException e)
		{
			fileLogger.debug("SQL Exception for method getAccountByUserId\n" + e.getMessage());
		}
		return null;
	}

	
	public Account getAccountByAccountId(int accountId) throws AccountNotFoundException
	{
		String sql = "SELECT *, balance::numeric::float8 as account_balance FROM accounts WHERE id = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				fileLogger.debug("Getting account by id");
				return new Account(resultSet.getInt("id"), resultSet.getString("account_type"), resultSet.getInt("account_number"),  resultSet.getInt("userid"), resultSet.getDouble("account_balance"),
						resultSet.getString("first_name"), resultSet.getString("last_name"));
			}
		}
		catch(SQLException e)
		{
			fileLogger.debug("SQL Error getting account by id. " + e.getMessage());
			return null;
		}
		throw new AccountNotFoundException("Account not found");
	}
	
	@Override
	public void withdrawFunds(Account account) {
		String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, (Double) account.getBalance(), 2);
			preparedStatement.setInt(2, account.getId());
			preparedStatement.execute();
			fileLogger.debug("Withdrawing money from account: ID: " + account.getId() + " " + account.getFirstName() + " " + account.getLastName());
		}
		catch(SQLException e)
		{
			fileLogger.debug("SQL Error withdrawing funds." + e.getMessage());
		}
	}

	@Override
	public void depositFunds(Account account) {
		String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, (Double) account.getBalance(), 2);
			preparedStatement.setInt(2, account.getId());
			preparedStatement.execute();
			fileLogger.debug("Depositing money into account: ID: " + account.getId() + " " + account.getFirstName() + " " + account.getLastName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	};

	@Override
	public void getAccountList() {
		String sql = "SELECT *, balance::numeric::float8 as account_balance FROM accounts ORDER BY first_name ASC, last_name ASC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Account account = new Account(resultSet.getInt("id"), resultSet.getString("account_type"), resultSet.getInt("account_number"), resultSet.getInt("userid"), 
						resultSet.getDouble("account_balance"), resultSet.getString("first_name"), resultSet.getString("last_name"));
				System.out.println(account);
			}
		} catch (SQLException e) {
			fileLogger.debug("SQL Error retrieving account list." + e.getMessage());
		}
	}
	
	public void getAccountList(int userid)
	{
		String sql = "SELECT *, balance::numeric::float8 as account_balance FROM accounts WHERE userid = ? ORDER BY first_name ASC, last_name ASC";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Account account = new Account(resultSet.getInt("id"), resultSet.getString("account_type"), resultSet.getInt("account_number"), resultSet.getInt("userid"), 
						resultSet.getDouble("account_balance"), resultSet.getString("first_name"), resultSet.getString("last_name"));
				System.out.println(account);
			}
		} catch (SQLException e) {
			fileLogger.debug("SQL Error retrieving account list by userid." + e.getMessage());
		}
	}

	@Override
	public Account getCheckingAccountById(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getSavingsAccountById(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteAccount(int accountId) {
		String sql = "DELETE from accounts WHERE id = ?";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			preparedStatement.execute();
			fileLogger.debug("Deleting account id: " + accountId);
		}
		catch(SQLException e)
		{
			fileLogger.debug("SQL Error deleting bank account." + e.getMessage());
		}
	}	
}
