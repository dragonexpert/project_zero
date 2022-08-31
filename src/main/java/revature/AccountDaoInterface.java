package revature;


public interface AccountDaoInterface {
	public Account getAccountByUserId(String accountType, int userId) throws AccountNotFoundException;
	
	public void withdrawFunds(Account account);
	
	public void depositFunds(Account account);
	
	public void getAccountList();
	
	public Account getCheckingAccountById(int accountId);
	
	public Account getSavingsAccountById(int accountId);
	
	public void deleteAccount(int accountId);

	public Account getAccountByAccountId(int accountId) throws AccountNotFoundException;
}
