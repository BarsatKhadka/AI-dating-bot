package barsat_project.ai_chatbot;

import barsat_project.ai_chatbot.conversations.ChatMessages;
import barsat_project.ai_chatbot.conversations.Conversation;
import barsat_project.ai_chatbot.conversations.ConversationRepository;
import barsat_project.ai_chatbot.profiles.Gender;
import barsat_project.ai_chatbot.profiles.Profile;
import barsat_project.ai_chatbot.profiles.ProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class AiChatbotApplication implements CommandLineRunner {

	@Autowired
	private ProfilesRepository profilesRepository;
    @Autowired
    private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(AiChatbotApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Profile profile = new Profile(
				"1",
				"Barsat",
				"Khadka",
				"Nepali",
				Gender.MALE,
				40,
				"Software engineer",
				"foo.jpg",
				"I AM GOOD"
		);
		profilesRepository.save(profile);
		profilesRepository.findAll().forEach(System.out::println);

		Conversation conversation = new Conversation(
				"1",
				profile.id(),
				List.of(
						new ChatMessages("Hey how are you", profile.id(), LocalDateTime.now())
				)
		);
		conversationRepository.save(conversation);
		conversationRepository.findAll().forEach(System.out::println);
	}


}
