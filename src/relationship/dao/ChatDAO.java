package relationship.dao; 

import java.util.ArrayList;

import relationship.persistence.ChatMessagePersistence;


public interface ChatDAO {

	
	public int saveChatMessage(ChatMessagePersistence chatPersistence);
	
	public ArrayList getChatMessageListByPublicId(int publicId);
}
