package relationship.persistence; 

import java.io.Serializable;


public class UserPersistence implements Serializable
{

	private int user_oid;
	private String name;
	private String emailAddress;
	private String phoneNo;
	private String username;
	private String password;
	private String status;
	
	public UserPersistence(){}
	
	public UserPersistence(int userOid, String name, String emailAddress,String phoneNo, String username, String password, String status) 
	{
		user_oid = userOid;
		this.name = name;
		this.emailAddress = emailAddress;
		this.phoneNo = phoneNo;
		this.username = username;
		this.password = password;
		this.status = status;
	}
	
	public int getUser_oid() {
		return user_oid;
	}
	public void setUser_oid(int userOid) {
		user_oid = userOid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
