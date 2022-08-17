package com.revature.bankApp;

import java.util.Objects;

public class Users {
	private int uid;
	private String username;
	private String password;
	
	Users(String username, String password)
	{
		setUsername(username);
		setPassword(password);
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(password, uid, username);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(password, other.password) && uid == other.uid && Objects.equals(username, other.username);
	}



	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + "]";
	}
	
	
}
