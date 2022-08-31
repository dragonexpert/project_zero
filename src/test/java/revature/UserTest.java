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
public class UserTest {
	@Mock
	private UserDaoInterface userDaoInterface;
	
	private UserDao userDao;
	
	private Customer validCustomer;
	
	
	@BeforeAll
	void setup()
	{
		MockitoAnnotations.openMocks(this);
		
		validCustomer = new Customer(1, "user1", "pass1", "email1@example.com", "Mario", "Plumber");
		
		userDao = new UserDao();
	}
	
	@Test
	public void getUserWithCorrectCredentialsTest() throws UserNotFoundException  {
		Mockito.when(userDaoInterface.getUser(validCustomer.getUsername(), validCustomer.getPassword())).thenReturn(validCustomer);
		
		assertEquals(validCustomer, userDaoInterface.getUser("user1", "pass1"));
		
	}
	
	@Test
	public void getUserWithInvalidUsernameTest() throws UserNotFoundException {
		Mockito.when(userDaoInterface.getUser("InvalidUsername", "pass1")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> userDao.getUser("InvalidUsername", "pass1"));
	}
	
	@Test
	public void getUserWithInvalidPasswordTest() throws UserNotFoundException {
		Mockito.when(userDaoInterface.getUser("user1", "WrongPassword")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> userDao.getUser("user1", "WrongPassword"));
	}
	
	@Test
	public void usernameExistsDoesExistTest()
	{
		Mockito.when(userDaoInterface.usernameExists("user1")).thenReturn(true);
		assertEquals(true, userDao.usernameExists("user1"));
	}
	
	@Test
	public void usernameExistsDoesNotExistTest()
	{
		Mockito.when(userDaoInterface.usernameExists("nonExistingUsername")).thenReturn(false);
		assertEquals(false, userDao.usernameExists("nonExistingUsername"));
	}
	
	@Test
	public void validateUsernameTest()
	{
		Mockito.when(userDaoInterface.validateUsername("ValidUsername")).thenReturn(true);
		assertEquals(true, userDao.validateUsername("ValidUsername"));
	}
	
	@Test
	public void validateUsernameTooShortUsernameTest()
	{
		Mockito.when(userDaoInterface.validateUsername("U")).thenReturn(false);
		assertEquals(false, userDao.validateUsername("U"));
	}
	
	@Test
	public void validateUsernameTooLongUsernameTest()
	{
		Mockito.when(userDaoInterface.validateUsername("WayTooLongOfAUsernameToPossiblyExist")).thenReturn(false);
		assertEquals(false, userDao.validateUsername("WayTooLongOfAUsernameToPossiblyExist"));
	}
	
	@Test
	public void validateUsernameInvalidCharacterTest()
	{
		Mockito.when(userDaoInterface.validateUsername("user79#")).thenReturn(false);
		assertEquals(false, userDao.validateUsername("user79#"));
	}
	
	
	@Test
	public void createUserTest()
	{
		Mockito.when(userDaoInterface.createUser("user999", "pass999", "email999@example.com")).thenReturn(true);
		assertEquals(true, userDao.createUser("user999", "pass999", "email999@example.com"));
		userDao.deleteUserByUsername("user999", "pass999");
	}
	
	
	@Test
	public void deleteUserTest()
	{
		Mockito.when(userDaoInterface.deleteUser(9)).thenReturn(true);
		assertEquals(true, userDao.deleteUser(9));
	}
	
}
