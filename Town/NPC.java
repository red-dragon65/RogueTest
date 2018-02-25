package RogueGame.Town;

import RogueGame.Sprite.SpriteImage;

public class NPC extends SpriteImage {


    private String characterName;
    private String dialogue;

    //Constructor
    public NPC() {

    }

    public void setDialogue(String mess) {
        dialogue = mess;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setName(String name) {
        characterName = name;
    }


}


/*
Holds dialogue data (as script object?)
Reads character data from file. (Reads chapter file? Reads character file that has chapter info?)

*/