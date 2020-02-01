package relationship.persistence; 

import java.io.Serializable;




public class ChatMessagePersistence implements Serializable
{

	private int chatMessage_oid;
	private String chatMessage;
	private int publicId;
	private String chatBy;
	private java.sql.Time timeIn;
	private java.sql.Date chatDate;
	
	public java.sql.Date getChatDate() {
		return chatDate;
	}

	public void setChatDate(java.sql.Date chatDate) {
		this.chatDate = chatDate;
	}
	
	
	public java.sql.Time getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(java.sql.Time timeIn) {
		this.timeIn = timeIn;
	}
	
	public String getChatBy() {
		return chatBy;
	}

	public void setChatBy(String chatBy) {
		this.chatBy = chatBy;
	}
	
	
	public int getChatMessage_oid() {
		return chatMessage_oid;
	}

	public void setChatMessage_oid(int chatMessageOid) {
		chatMessage_oid = chatMessageOid;
	}
	public String getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}

	public int getPublicId() {
		return publicId;
	}
	public void setPublicId(int publicId) {
		this.publicId = publicId;
	}

	ChatMessagePersistence(){}
	
	public ChatMessagePersistence(int chatMessageOid, String chatMessage,String chatBy,java.sql.Date chatDate, java.sql.Time timeIn, int publicId) {
		chatMessage_oid = chatMessageOid;
		this.chatMessage = chatMessage;
		this.chatBy=chatBy;
		this.chatDate=chatDate;
		this.timeIn=timeIn;
		this.publicId = publicId;
	}
	
}
