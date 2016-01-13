package TD.Sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Sound {
    private static final String SPATH = "res/snd/";
    public static AudioInputStream [] audioIn;
    
    private static AudioInputStream[] loadSounds(String path)
    {   
        String na;
        File f = new File(path);
        File[] files = f.listFiles();
        AudioInputStream[] tabSd = new AudioInputStream[files.length];
        for(int i = 0, ind; i < files.length; i++)
        {
            na = files[i].getName();
            String name = na.substring(0, na.indexOf("."));
            if(!name.equals("Thumbs"))
            {
                ind = Integer.parseInt(name);
                try {
                    File fa = new File(SPATH+na);
                    System.out.println(fa.exists());
                    tabSd[ind] = AudioSystem.getAudioInputStream(new File(na));
                } 
                catch (UnsupportedAudioFileException ex) {} 
                catch (IOException ex) {}
            }
        }
        return tabSd;
    }
    
    
    
    public static void init() {
        audioIn = loadSounds(SPATH);
        System.out.println();
    }
    
    public static void play(int track) {
        AudioInputStream listen = audioIn[track];
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(listen);
        } catch (LineUnavailableException ex) {}
          catch (IOException ex) {}
    }
}
