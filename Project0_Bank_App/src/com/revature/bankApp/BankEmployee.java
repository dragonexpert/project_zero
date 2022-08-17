package com.revature.bankApp;

import java.util.Objects;

public class BankEmployee {
	private int id;
	private String name;
	private boolean admin;
	public BankEmployee(int id, String name, boolean admin) {
		super();
		this.id = id;
		this.name = name;
		this.admin = admin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	@Override
	public int hashCode() {
		return Objects.hash(admin, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankEmployee other = (BankEmployee) obj;
		return admin == other.admin && id == other.id && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "BankEmployee [id=" + id + ", name=" + name + ", admin=" + admin + "]";
	}
	
	public void viewAccount(int accountNumber)
	{
		// TODO implement view
	}
	
	public void approveAccountApplication(int applicationId)
	{
		// TODO implement method
	}
	
	public void denyAccountApplication(int applicationId)
	{
		// TODO implement method
	}
	
}
