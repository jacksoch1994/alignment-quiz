public class Choice {
    /*
    ########################################   Attributes   ##########################################
     */

    private String description;

    private int lawChaosModifier = 0;
    private int goodEvilModifier = 0;


    /*
    ######################################## Getter Methods ##########################################
     */

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

    public Choice(String description, int lawChaosModifier, int goodEvilModifier) {
        this.description = description;
        this.lawChaosModifier = lawChaosModifier;
        this.goodEvilModifier = goodEvilModifier;
    }

}
