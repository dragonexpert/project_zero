package revature;

import java.util.Objects;

public class Employee extends User{

	private boolean admin;
	private int employeeid;
	private String first_name;
	private String last_name;
	public Employee(int id, String username, String password, String email, int employeeId, boolean admin, String first_name, String last_name) {
		super(id, username, password, email);
		setAdmin(admin);
		setEmployeeId(employeeId);
		setFirstName(first_name);
		setLastName(last_name);
		
		// TODO Auto-generated constructor stub
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public int getEmployeeId() {
		return employeeid;
	}
	public void setEmployeeId(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(admin, employeeid, first_name, last_name);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return admin == other.admin && employeeid == other.employeeid && Objects.equals(first_name, other.first_name)
				&& Objects.equals(last_name, other.last_name);
	}
	@Override
	public String toString() {
		return "Employee [admin=" + admin + ", employeeid=" + employeeid + ", first_name=" + first_name + ", last_name="
				+ last_name + ", id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}
	
	

}
