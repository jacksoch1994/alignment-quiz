import java.util.ArrayList;
import java.util.List;

public class Scenario {

    /*
    ########################################   Attributes   ##########################################
     */

    private String identifier;
    private String description = "";

    private List<Choice> choices = new ArrayList<>();

    boolean isStartingScenario = false;

    /*
    ######################################## Getter Methods ##########################################
     */

    public String getDescription() {
        return description;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public String getIdentifier() {
        return identifier;
    }


    /*
    ######################################## Setter Methods ##########################################
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /*
    ########################################  Other Methods  ##########################################
     */

    public void addChoice(Choice choice) {
        choices.add(choice);
    }


    /*
    ########################################   Constructor   ##########################################
     */

    public Scenario(String identifier) {
        this.identifier = identifier;
    }

    public Scenario(String identifier, boolean isStartingScenario) {
        this(identifier);
        this.isStartingScenario = isStartingScenario;
    }

    public Scenario(String identifier, boolean isStartingScenario, String description) {
        this(identifier, isStartingScenario);
        this.description = description;
    }



}
