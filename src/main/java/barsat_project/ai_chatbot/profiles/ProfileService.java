package barsat_project.ai_chatbot.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfilesRepository profilesRepository;



    public void saveProfile(Profile profile) {
        profilesRepository.save(profile);
    }

    public void getProfile() {
      profilesRepository.findAll().forEach(System.out::println);
    }




}
