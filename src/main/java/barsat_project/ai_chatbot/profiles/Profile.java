package barsat_project.ai_chatbot.profiles;

public record Profile(String id,
                      String firstName,
                      String lastName,
                      String ethnicity,
                      Gender gender ,
                      int age,
                      String bio,
                      String imageUrl,
                      String myersBriggsPersonalityType) {

}
