package barsat_project.ai_chatbot.profiles;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

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
        List <Gender> genders = new ArrayList<>(List.of(Gender.FEMALE));
        List<String> ethnicities = new ArrayList<>(List.of("American" , "Japanese" , "European" , "Indian", "African" , "Nepali"));
        List<String> DereTypes = new ArrayList<>(List.of("Tsundere", "Yandere", "Kuudere", "Deredere", "Dandere"));
        Collections.shuffle(ages);
        Collections.shuffle(genders);
        Collections.shuffle(ethnicities);
        Collections.shuffle(DereTypes);

        int age = ages.get(0);
        Gender gender = genders.get(0);
        String ethnicity = ethnicities.get(0);
        String dereType = DereTypes.get(0);


        String prompt = "Create a Tinder profile for an anime waifu persona. This character is " + age + " " +
                "years old, identifies as " + gender.toString() + ", and has an ethnicity of " + ethnicity + ". " +
                "They also have a " + dereType + " dere type. Please include their first name, last name, Myers-Briggs Personality Type, and a short, engaging Tinder bio that is kind of human and simple (dont use flashy words)" +
                " that reflects their personality.";



        ChatResponse response = chatClient.call(new Prompt(prompt , OpenAiChatOptions.builder().withFunction("saveProfile").build()));
        System.out.println(response.getResult().getOutput().getContent());





    }

    @Bean
    @Description("Save profile and get response in this format")
    public Function<Profile,Boolean> saveProfile(){
        return (profile) -> {
            System.out.println(profile);
            return true;
        };
    }






}
