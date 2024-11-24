package barsat_project.ai_chatbot.profiles;

import com.google.gson.Gson;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfilesRepository profilesRepository;

    private List<Profile> generatedProfiles = new ArrayList<>();

    private OpenAiChatModel chatClient;

    private static final String PROFILE_FILE_PATH = "profiles.json";

    @Value("${startup-actions.initializeProfiles}")
    private Boolean initializeProfile;

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

        if(!this.initializeProfile){
            return;
        }

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


        for(int i = 0; i < numberOfProfiles; i++) {

            int randomAgeIndex = random.nextInt(ages.size());
            int randomEthnicityIndex = random.nextInt(ethnicities.size());
            int randomDereTypeIndex = random.nextInt(DereTypes.size());

            int age = ages.get(randomAgeIndex);
            Gender gender = genders.get(0);
            String ethnicity = ethnicities.get(randomEthnicityIndex);
            String dereType = DereTypes.get(randomDereTypeIndex);


            String prompt = "Create a Tinder profile for an anime waifu persona. This character is " + age + " " +
                    "years old, identifies as " + gender.toString() + ", and has an ethnicity of " + ethnicity + ". " +
                    "They also have a " + dereType + " dere type. Please include their first name, last name, dere Type , Myers-Briggs Personality Type, and a short, engaging Tinder bio that is kind of human and simple (dont use flashy words)" +
                    " that reflects their personality.";

            ChatResponse response = chatClient.call(new Prompt(prompt , OpenAiChatOptions.builder().withFunction("saveProfile").build()));

        }
        saveProfiletoJson(this.generatedProfiles);
    }


    @Bean
    @Description("Save profile and get response in this format")
    public Function<Profile,Boolean> saveProfile(){
        return (profile) -> {
            System.out.println(profile);
            this.generatedProfiles.add(profile);
            return true;
        };
    }

    public void saveProfiletoJson(List<Profile> generatedProfiles) {
        String jsonString = new Gson().toJson(generatedProfiles);

        try{
            FileWriter writer = new FileWriter(PROFILE_FILE_PATH);
            writer.write(jsonString);
            writer.close();

        }
        catch(IOException e){}

    }



    public void seeProfile(){
        String represent = generatedProfiles.stream()
                .map(profile -> profile.toString())
                .collect(Collectors.joining());
        System.out.println(represent);
    }


    public void deleteById(String id){
        profilesRepository.deleteById(id);

    }







}
