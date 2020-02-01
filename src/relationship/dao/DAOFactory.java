package relationship.dao; 


public class DAOFactory {

	public PublicDAO getPublicDAO(){
		return new PublicDAOImpl();
	}
	
	
	public UserDAO getUserDAO(){
		return new UserDAOImpl();
	}
	
	public ChatDAO getChatDAO(){
		return new ChatDAOImpl();
	}
	
	
}
