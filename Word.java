
/**
 * Word Object that the user will try to guess
 *
 * @author Justin Lee
 * @version 1
 */
public class Word
{
    // instance variables
    private String word; 
    private String category;
    private String difficulty;
    private boolean[] correctIndexes; //holds the indexes of correct guesses to show output (remember to check all letters in word for duplicate correct letters)
    
    
    /**
     * Constructor for Word Object
     */
    public Word(String w, String c, String d)
    {
        word = w;
        category = c;
        difficulty = d;
        correctIndexes = new boolean[word.length()];
        for (int i = 0; i < correctIndexes.length; i++) {
            correctIndexes[i] = false;
        }
    }

    /**
     * Getter Method
     */
    public String getDifficulty() {
        return this.difficulty;
    }
    
    /**
     * Getter Method
     */
    public String getWord() {
        return this.word;
    }
    
    /**
     * Getter Method
     */
    public String getCategory() {
        return this.category;
    }
    
    /**
     * Method that checks if a guessed letter is correct
     */
    public boolean checkCorrect(String letter) {
        letter = letter.toLowerCase();
        boolean correct = false;
        if (this.word.contains(letter)) {
            for (int i = 0; i < this.word.length(); i++) {
                if (this.word.substring(i, i + 1).equals(letter)) {
                    this.correctIndexes[i] = true;
                    correct = true;
                }
            }
        }
        return correct;
    }
    
    /**
     * Method that returns out the guessed letters and underscores
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < this.word.length(); i++) {
            String letter = this.word.substring(i, i + 1);
            if (letter.equals(" ")) {
                output += "   ";
                this.correctIndexes[i] = true;
            } else if (correctIndexes[i]) {
                output += letter + " ";
            } else {
                output += "_ ";
            }
        }
        return output;
    }
    
    
    /**
     * Method that checks if the user guessed all the letters correctly
     */
    public boolean checkWin() {
        for (boolean index : correctIndexes) {
            if (!index) {
                return false;
            }
        }
        return true;
    }
}
