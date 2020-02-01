package relationship.forms; 

import org.apache.struts.action.ActionForm;


public class PublicForm extends ActionForm 
{
	private String customerEmail;
	private String customerPhoneNo;
	private String customerName;
	private String parameter;
	private String message;
	public String username;
	public String password;
	public String chatMessage;
	private int public_oid;
	
	
	public int getPublic_oid() {
		return public_oid;
	}
	public void setPublic_oid(int publicOid) {
		public_oid = publicOid;
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
	public String getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}
	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
