package barsat_project.ai_chatbot.conversations;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
}
