package barsat_project.ai_chatbot.conversations;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ConversationController {

    @Autowired
    ConversationService conversationService;

    @PostMapping("/conversations")
    public ResponseEntity<Conversation> createConversations(@RequestBody CreateConversationRequest conversationRequest) {
        return conversationService.createConversation(conversationRequest);
    }

    @PostMapping("/conversations/{conversationsId}")
    public ResponseEntity<Conversation> addMessageToConversation(@PathVariable String conversationsId , @RequestBody ChatMessages chatMessage) {
        return conversationService.addMessage(conversationsId , chatMessage);
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllConversations() {
        conversationService.deleteAllConversations();
    }


}
