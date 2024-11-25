package barsat_project.ai_chatbot;

import barsat_project.ai_chatbot.conversations.ChatMessages;
import barsat_project.ai_chatbot.conversations.Conversation;
import barsat_project.ai_chatbot.conversations.ConversationRepository;
import barsat_project.ai_chatbot.profiles.Gender;
import barsat_project.ai_chatbot.profiles.Profile;
import barsat_project.ai_chatbot.profiles.ProfileService;
import barsat_project.ai_chatbot.profiles.ProfilesRepository;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class AiChatbotApplication implements CommandLineRunner {

	@Autowired
	private ProfileService profileService;



	public static void main(String[] args) {
		SpringApplication.run(AiChatbotApplication.class, args);
	}

	public void run(String... args) throws Exception {

		profileService.createProfiles(4);
		profileService.seeProfile();



		profileService.getProfile();



	}


}
