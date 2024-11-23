package barsat_project.ai_chatbot.profiles;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfilesRepository extends MongoRepository<Profile, String> {
}
