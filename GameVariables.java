import java.util.*;
/**
 * Game object
 *
 * @Justin Lee
 * @version 1
 */
public class GameVariables
{
    // instance variables - replace the example below with your own
    private ArrayList<String> guessedLetters = new ArrayList<String>();
    private Word selectedWord;
    
    /**
     * Constructor for objects of class Game
     */
    public GameVariables(Word w)
    {
        // initialise instance variables
        selectedWord = w;
    }
    
    /**
     * Adds a letter to the arraylist
     */
    public void addLetter(String l) {
        this.guessedLetters.add(l);
    }
    
    
    public ArrayList<String> getGuessed() {
        return this.guessedLetters;
    }
    
    public Word getWord() {
        return this.selectedWord;
    }
}
