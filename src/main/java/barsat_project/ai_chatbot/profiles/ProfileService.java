package barsat_project.ai_chatbot.profiles;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProfileService {

    @Autowired
    private ProfilesRepository profilesRepository;

    private OpenAiChatModel chatClient;

    public ProfileService(OpenAiChatModel chatClient) {
        this.chatClient = chatClient;
    }

    public void saveProfile(Profile profile) {
        profilesRepository.save(profile);
    }

    public void getProfile() {
      profilesRepository.findAll().forEach(System.out::println);
    }

    public void createProfiles(int numberOfProfiles) {
        Integer []randomAges = new Integer[numberOfProfiles];
        Random random = new Random();
        for(int i = 0; i < numberOfProfiles; i++) {
            randomAges[i] = random.nextInt(25)+ 20;
        }
        List<Integer> ages = new ArrayList<>(List.of(randomAges));
        List <Gender> genders = new ArrayList<>(List.of(Gender.MALE , Gender.FEMALE , Gender.NONBINARY));
        List<String> ethnicities = new ArrayList<>(List.of("American" , "Japanese" , "European" , "Indian", "African" , "Nepali"));
        List<String> Dere = List.of("Tsundere", "Yandere", "Kuudere", "Deredere", "Dandere");


    }




}
