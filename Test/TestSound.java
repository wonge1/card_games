import javax.sound.sampled.*;
import java.io.*;

public class TestSound implements LineListener {

    public void Test() {
        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
              clip.start();
              listener.waitUntilDone();
            } finally {
              clip.close();
            }
        } finally {
            audioInputStream.close();
        }
    }

    public void update(LineEvent event) {
        
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
