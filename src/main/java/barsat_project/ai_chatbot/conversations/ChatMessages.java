package barsat_project.ai_chatbot.conversations;

import java.time.LocalDateTime;

public record ChatMessages(
        String messageText,
        String authorId,
        LocalDateTime messageTime
) {
}
