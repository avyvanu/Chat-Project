package relationship.dao; 


import relationship.persistence.PublicPersistence;



public interface UserDAO {

	public boolean checkUserLogin(String username, String password);
	
	
}
