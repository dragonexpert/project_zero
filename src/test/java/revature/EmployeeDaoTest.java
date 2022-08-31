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
public class EmployeeDaoTest {
	@Mock
	private EmployeeDaoInterface employeeDaoInterface;
	
	private EmployeeDao employeeDao;
	
	private Employee validEmployee;
	
	@BeforeAll
	void setup()
	{
		MockitoAnnotations.openMocks(this);
		
		validEmployee = new Employee(6, "user6", "pass6", "email6@example.com", 1, true, "Mark", "Janssen");
		
		employeeDao = new EmployeeDao();
	}
	
	@Test
	public void getEmployeeTest() throws UserNotFoundException
	{
		Mockito.when(employeeDaoInterface.getEmployee("user6", "pass6")).thenReturn(validEmployee);
		assertEquals(validEmployee, employeeDao.getEmployee("user6", "pass6"));
	}
	
	@Test
	public void getEmployeeWrongUsernameTest() throws UserNotFoundException
	{
		Mockito.when(employeeDaoInterface.getEmployee("user66", "pass6")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> employeeDao.getEmployee("user66", "pass6"));
	}

	@Test
	public void getEmployeeeWrongPasswordTest() throws UserNotFoundException
	{
		Mockito.when(employeeDaoInterface.getEmployee("user6", "pass66")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> employeeDao.getEmployee("user6", "pass66"));
	}
}
