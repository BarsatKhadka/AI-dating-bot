package barsat_project.ai_chatbot.conversations;

import barsat_project.ai_chatbot.profiles.ProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConversationService {

    @Autowired ConversationRepository conversationRepository;
    @Autowired ProfilesRepository profilesRepository;

    public ResponseEntity<Conversation> getUserConversation(String conversationId) {
        try{
            Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find conversation with id: " + conversationId));
            return new ResponseEntity<>(conversation, HttpStatus.FOUND);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }



    }

    public ResponseEntity<Conversation> createConversation(CreateConversationRequest conversationRequest){
        try {
            profilesRepository.findById(conversationRequest.profileId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Unable to find profile with "+ conversationRequest.profileId()));

            Conversation conversation = new Conversation(
                    UUID.randomUUID().toString(),
                    conversationRequest.profileId(),
                    new ArrayList<>()
            );
            conversationRepository.save(conversation);
            return new ResponseEntity<>(conversation, HttpStatus.CREATED);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


    }

    public void findAllConversation(){
        conversationRepository.findAll().forEach(System.out::println);
    }

    public ResponseEntity<Conversation> addMessage(String conversationId, ChatMessages chatMessage){
        try{
            Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find conversation with id: " + conversationId));
            profilesRepository.findById(chatMessage.authorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find profile with "+ chatMessage.authorId()));
            ChatMessages chatMessageswithTime= new ChatMessages(chatMessage.messageText() , chatMessage.authorId(), LocalDateTime.now());
            conversation.messages().add(chatMessageswithTime);
            conversationRepository.save(conversation);
            return new ResponseEntity<>(conversation, HttpStatus.CREATED);


        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public void deleteAllConversations(){
        conversationRepository.deleteAll();
    }
}
