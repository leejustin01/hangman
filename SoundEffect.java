import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
/**
 * Plays sound effect
 *
 * Justin Lee
 * @version 1
 */
public class SoundEffect
{
    public void playSound(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
