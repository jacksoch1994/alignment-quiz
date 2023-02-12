public class Choice {
    /*
    ########################################   Attributes   ##########################################
     */

    private String parentScenario;
    private String nextScenario = null;

    private String description;

    private int lawChaosModifier = 0;
    private int goodEvilModifier = 0;


    /*
    ######################################## Getter Methods ##########################################
     */


    public String getParentScenario() {
        return parentScenario;
    }

    public String getNextScenario() {
        return nextScenario;
    }

    public int getLawChaosModifier() {
        return lawChaosModifier;
    }

    public int getGoodEvilModifier() {
        return goodEvilModifier;
    }

    public String getDescription() {
        return description;
    }

    /*
    ########################################   Constructor   ##########################################
     */

    public Choice(String parentScenario, String description, int lawChaosModifier, int goodEvilModifier) {
        this.description = description;
        this.lawChaosModifier = lawChaosModifier;
        this.goodEvilModifier = goodEvilModifier;
        this.parentScenario = parentScenario;
    }

    public Choice(String parentScenario, String nextScenario, String description, int lawChaosModifier, int goodEvilModifier) {
        this(parentScenario, description, lawChaosModifier, goodEvilModifier);
        this.nextScenario = nextScenario;
    }

}
