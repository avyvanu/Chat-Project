package relationship.dao; 


import relationship.persistence.PublicPersistence;


public interface PublicDAO {
	
	public int savePublicUserInformation(PublicPersistence publicPersistence);
	
	public int updatePublicUserStatus(int publicId);
	
	public String getPublicNameByPublicId(int publicId);
	
}
