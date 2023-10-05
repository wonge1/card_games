import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer {
    private Clip clip;
    private String fileName;
    public SoundPlayer(String fName) {
        try {
            fileName = fName;
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
                if(fName.equals("Sound/Music.wav")) {
                    FloatControl gainControl = 
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-30.0f); // Reduce volume by 10 decibels.
                }
                
            } else {
                throw new RuntimeException("Sound: file not found: " + file.getAbsolutePath());
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }

    public void stop(){
        clip.setFramePosition(0);
        clip.stop();
    }
}