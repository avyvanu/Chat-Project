package relationship.persistence; 

import java.io.Serializable;



public class PublicPersistence implements Serializable 
{
	private int public_oid;
	private String publicName;
	private String publicEmail;
	private String publicPhoneNo;
	private java.sql.Time timeIn;
	private java.sql.Time timeOut;
	private String status;
	private String message;
	private java.util.Date date;

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public java.sql.Time getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(java.sql.Time timeIn) {
		this.timeIn = timeIn;
	}
	public java.sql.Time getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(java.sql.Time timeOut) {
		this.timeOut = timeOut;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public PublicPersistence(){}
	
	public PublicPersistence(int publicOid, String publicName, String publicEmail,String publicPhoneNo,String message,java.util.Date date, java.sql.Time timeIn, java.sql.Time timeOut,String status) 
	{
		public_oid = publicOid;
		this.publicName = publicName;
		this.publicEmail = publicEmail;
		this.publicPhoneNo = publicPhoneNo;
		this.message=message;
		this.date=date;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.status = status;
	}
		
	public int getPublic_oid() {
		return public_oid;
	}
	public void setPublic_oid(int publicOid) {
		public_oid = publicOid;
	}
	public String getPublicName() {
		return publicName;
	}
	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}
	public String getPublicEmail() {
		return publicEmail;
	}
	public void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}
	public String getPublicPhoneNo() {
		return publicPhoneNo;
	}
	public void setPublicPhoneNo(String publicPhoneNo) {
		this.publicPhoneNo = publicPhoneNo;
	}
}
