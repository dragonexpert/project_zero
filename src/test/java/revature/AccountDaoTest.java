package revature;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
@TestInstance(Lifecycle.PER_CLASS)
public class AccountDaoTest {

	@Mock
	private AccountDaoInterface accountDaoInterface;
	
	private AccountDao accountDao;
	
	private Account validAccount;
	
	@BeforeAll
	void setup()
	{
		MockitoAnnotations.openMocks(this);
		
		validAccount = new Account(6, "checking", 100198, 5, 500.00, "Miranda", "Palmer");
		
		accountDao = new AccountDao();
	}
	
	@Test
	public void getAccountbyUserIdTest() throws AccountNotFoundException
	{
		Mockito.when(accountDaoInterface.getAccountByUserId("checking", 5)).thenReturn(validAccount);
		assertEquals(validAccount, accountDao.getAccountByUserId("checking", 5));
	}
	
	@Test
	public void getAccountByUserIdWrongIdTest() throws AccountNotFoundException
	{
		Mockito.when(accountDaoInterface.getAccountByUserId("checking", 9999)).thenThrow(AccountNotFoundException.class);
		assertThrows(AccountNotFoundException.class, () -> accountDao.getAccountByUserId("checking", 9999));
	}
	
	@Test
	public void getAccountByUserIdWrongAccountTypeTest() throws AccountNotFoundException
	{
		Mockito.when(accountDaoInterface.getAccountByUserId("savings", 5)).thenThrow(AccountNotFoundException.class);
		assertThrows(AccountNotFoundException.class, () -> accountDao.getAccountByUserId("savings", 5));
	}

	@Test
	public void getAccountByAccountIdTest() throws AccountNotFoundException
	{
		Mockito.when(accountDaoInterface.getAccountByAccountId(6)).thenReturn(validAccount);
		assertEquals(validAccount, accountDao.getAccountByAccountId(6));
	}
	
	@Test
	public void getAccountByAccountIdWrongIdTest() throws AccountNotFoundException
	{
		Mockito.when(accountDaoInterface.getAccountByAccountId(5)).thenThrow(AccountNotFoundException.class);
		assertThrows(AccountNotFoundException.class, () -> accountDao.getAccountByAccountId(5));
	}
}
