package barsat_project.ai_chatbot.conversations;

import barsat_project.ai_chatbot.profiles.Profile;

import java.util.List;

public record Conversation(
        String id,
        String profileId,
        List<ChatMessages> messages
) {



}


