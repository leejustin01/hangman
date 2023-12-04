import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;  
import javax.swing.*;  
/**
 * Main Class that will run the game
 *
 * @author Justin Lee
 * @version 1
 */
public class Main
{
    public static final String buttonSound = "button.wav";
    public static final String winSound = "win.wav";
    public static final String loseSound = "lose.wav";
    public static final SoundEffect sound = new SoundEffect();
    
    /**
     * Main method that starts the game
     */
    public static void main(String[] args) throws IOException {
            startup();
    }
    
    /**
     * Method the greets the user and selects difficulty
     */
    public static void startup() {
        JFrame f=new JFrame("Hangman");
        f.setSize(1150,650);  
        f.setLayout(null);  
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);
        
        JLabel welcome = new JLabel();
        welcome.setText("Welcome to Hangman");
        welcome.setLocation(350 - 15, 50);
        welcome.setSize(350,100);
        welcome.setFont(welcome.getFont().deriveFont(30.0f));
        welcome.setBackground(Color.white);
        welcome.setOpaque(true);
        
        WordList wordList = createWordList();
        
        JButton easyButton = new JButton("easy");
        JButton medButton = new JButton("medium");
        JButton hardButton = new JButton("hard");
        
        easyButton.setBounds(100, 275, 200, 100);
        easyButton.setFont(easyButton.getFont().deriveFont(30.0f));
        easyButton.addActionListener(new ActionListener(){   
                public void actionPerformed(ActionEvent e){  
                    sound.playSound(buttonSound);
                    Word selectedWord = wordList.selectWord("easy");
                    disableStartup(f,easyButton,medButton,hardButton,welcome);
                    gameSetup(selectedWord, f);
                }  
            });
        
        
        medButton.setBounds(333 + 66, 275, 200, 100);
        medButton.setFont(medButton.getFont().deriveFont(30.0f));
        medButton.addActionListener(new ActionListener(){   
                public void actionPerformed(ActionEvent e){  
                    sound.playSound(buttonSound);
                    Word selectedWord = wordList.selectWord("medium");
                    disableStartup(f,easyButton,medButton,hardButton,welcome);
                    gameSetup(selectedWord, f);
                }  
            });
        
        
        hardButton.setBounds(666 + 66 - 34, 275, 200, 100);
        hardButton.setFont(hardButton.getFont().deriveFont(30.0f));
        hardButton.addActionListener(new ActionListener(){   
                public void actionPerformed(ActionEvent e){  
                    sound.playSound(buttonSound);
                    Word selectedWord = wordList.selectWord("hard");
                    disableStartup(f,easyButton,medButton,hardButton,welcome);
                    gameSetup(selectedWord, f);
                }  
            });
        f.add(welcome);
        f.add(medButton);
        f.add(easyButton);
        f.add(hardButton);
    }
    
    /**
     * Method for setting up the game
     */
    public static boolean gameSetup(Word selectedWord, JFrame f) { 
        GameVariables game = new GameVariables(selectedWord);
        JLabel wordOutput = new JLabel(selectedWord.toString());
        int wordx = 0;
        if (selectedWord.getDifficulty().equals("hard")) {
            wordx = 500;
        } else {
            wordx = 550;
        }
        wordOutput.setLocation(wordx, 30);
        wordOutput.setSize(550,100);
        wordOutput.setFont(wordOutput.getFont ().deriveFont (30.0f));
        wordOutput.setBackground(Color.white);
        wordOutput.setOpaque(true);
        
        JLabel categoryOutput = new JLabel(selectedWord.getCategory());
        categoryOutput.setLocation(700, 100);
        categoryOutput.setSize(400,100);
        categoryOutput.setFont(wordOutput.getFont ().deriveFont (20.0f));
        categoryOutput.setBackground(Color.white);
        categoryOutput.setOpaque(true);
        
        
        Hangman hangman = new Hangman();
        
        JLabel l = hangman.getImage();
        
       
        String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        int xoffset = 512;
        int row = 0;
        JButton[] letterbuttons = new JButton[26];
        
        JLabel alreadyGuessed = new JLabel("Already Guessed");
        alreadyGuessed.setLocation(500, 100);
        alreadyGuessed.setSize(150, 100);
        alreadyGuessed.setFont(alreadyGuessed.getFont().deriveFont(18.0f));
        alreadyGuessed.setOpaque(true);
        alreadyGuessed.setBackground(Color.white);
        
        JLabel guessedOutput = new JLabel("");
        guessedOutput.setLocation(30, 450);
        guessedOutput.setSize(450, 50);
        guessedOutput.setFont(guessedOutput.getFont().deriveFont(12.0f));
        guessedOutput.setOpaque(true);
        guessedOutput.setBackground(Color.white);
        for (int i = 0; i < 26; i ++) {
            JButton letterButton = new JButton(letters[i]);
            letterButton.setBounds(xoffset + 75 * i, 200 + row * 75, 50, 50);
            letterButton.setFont(letterButton.getFont().deriveFont(15.0f));
            letterbuttons[i] = letterButton;
            letterButton.addActionListener(new ActionListener(){   
                public void actionPerformed(ActionEvent e){  
                    f.remove(alreadyGuessed);
                    f.repaint();
                    sound.playSound(buttonSound);
                    String guessedLetter = "";
                    for (int i = 0; i < 26; i++) {
                        if (e.getSource() == letterbuttons[i]) {
                            guessedLetter = letters[i];
                            break;
                        }
                    }
                    
                    if (contains(game.getGuessed(), guessedLetter)) {
                        f.add(alreadyGuessed);
                        f.repaint();
                    } else {
                        
                        if (game.getWord().checkCorrect(guessedLetter)) {
                            wordOutput.setText(selectedWord.toString());
                            if(selectedWord.checkWin()) {
                                endGame(true, f, l, wordOutput);
                            }
                        } else {
                            if (!hangman.updateImage()) {
                                wordOutput.setText(selectedWord.getWord());
                                endGame(false, f, l, wordOutput);
                            }
                        }
                        game.addLetter(guessedLetter);
                        guessedOutput.setText("Guessed Letters: " + game.getGuessed().toString());
                        
                    }
                    
                    
                }  
            });
            
            f.add(letterButton);
            
            if ((i + 1) % 6 == 0 && i + 1 >= 6) {
                row ++;
                xoffset -= 450;
            }
        }
        
        


        f.add(wordOutput); f.add(l);
        f.add(categoryOutput); 
        f.add(guessedOutput);
        
        
        return false;
    }
    
    public static void endGame(boolean win, JFrame f,JLabel l, JLabel wordOutput) {
        f.getContentPane().removeAll();
        f.add(wordOutput);
        f.add(l);
        f.repaint();
        JLabel endText = new JLabel();
        endText.setLocation(575,200);
        endText.setSize(300,100);
        endText.setFont(endText.getFont().deriveFont(30.0f));
        endText.setBackground(Color.white);
        if (win) {
            sound.playSound(winSound);
            endText.setText("You Win!");
        } else {
            sound.playSound(loseSound);
            endText.setText("You Lose...");
        }
        
        JLabel playAgain = new JLabel("Would you like to play again?");
        playAgain.setLocation(500,325);
        playAgain.setSize(500,50);
        playAgain.setFont(playAgain.getFont().deriveFont(30.0f));
        playAgain.setBackground(Color.white);
        
        JButton yes = new JButton("Yes");
        yes.setBounds(550, 425, 100, 50);
        yes.setFont(yes.getFont().deriveFont(15.0f));
        yes.addActionListener(new ActionListener(){   
                public void actionPerformed(ActionEvent e){  
                   startup();
                   f.dispose();
                }  
            });
        JButton no = new JButton("No");
        no.setBounds(670, 425, 100, 50);
        no.setFont(no.getFont().deriveFont(15.0f));
        no.addActionListener(new ActionListener(){   
                public void actionPerformed(ActionEvent e){  
                   f.dispose();
                }  
            });
            
            f.add(endText);
            f.add(playAgain);
            f.add(yes);
            f.add(no);
    }
    
    /**
     * Method for checking if a value is in an arraylist
     */
    public static boolean contains(ArrayList<String> list, String str) {
        for (String item : list) {
            if (item.equals(str)) {
                return true;
            }
        }
        return false;
    }
    
  
    /**
     * Method that initialises the word list 
     */
    public static WordList createWordList() {
        try {
            WordList wordList = new WordList("wordList.txt");
            return wordList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Method that erases startup screen 
     */
    public static void disableStartup(JFrame f, JButton easy, JButton med, JButton hard, JLabel welcome) {
        f.remove(easy);
        f.remove(med);
        f.remove(hard);
        f.remove(welcome);
        f.repaint();
    }
}
