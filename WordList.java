import java.lang.*;
import java.util.*;
import java.io.*;
/**
 * WordList Object that holds all the words from the input file
 *
 * @author Justin Lee
 * @version 1
 */
public class WordList
{
    //instance variables
    private ArrayList<Word> easyWords = new ArrayList<Word>();
    private ArrayList<Word> medWords = new ArrayList<Word>();
    private ArrayList<Word> hardWords = new ArrayList<Word>();
    
    /**
     * Constructor for objects of class WordList
     */
    public WordList(String fileName) throws FileNotFoundException {
        Scanner inFile = new Scanner(new File(fileName));
        
        while(inFile.hasNext()) {
            String input = inFile.nextLine();
            
            String[] wordInfo = input.split(",");
            
            if (wordInfo.length != 2) {
                throw new FileNotFoundException("Invalid Input List Format."); 
            }
            
            
            if (wordInfo[0].contains(" ")) {
                hardWords.add(new Word(wordInfo[0], wordInfo[1], "hard"));
            } else if (wordInfo[0].length() >= 6) {
                medWords.add(new Word(wordInfo[0], wordInfo[1], "medium"));
            } else {
                easyWords.add(new Word(wordInfo[0], wordInfo[1], "easy"));
            }
        }
    }
    
    
    /**
     * Method that selects the word
     */
    public Word selectWord(String difficulty) {
        Word returnWord = null;
        if (difficulty.equals("hard")) {
            returnWord = hardWords.get((int) (Math.random() * hardWords.size()));
        } else if (difficulty.equals("medium")) {
            returnWord = medWords.get((int) (Math.random() * medWords.size()));
        } else {
            returnWord = easyWords.get((int) (Math.random() * easyWords.size()));
        }
        
        //erase arraylists to save memory
        this.easyWords = null;
        this.medWords = null;
        this.hardWords = null;
        
        return returnWord;
    }
}
