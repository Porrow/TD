package TD.Sound;

import ddf.minim.*;
import java.io.File;

public class Sound {
    private static final String SPATH = "res/snd/";    
    private static AudioPlayer[] audiotab;
    private static int song;
    
    //zone de test
    private static AudioPlayer[] loadSounds(String path, Minim minim)
    {   
        String na;
        File f = new File(path);
        File[] files = f.listFiles();
        AudioPlayer[] tabSd = new AudioPlayer[files.length];
        for(int i = 0, ind; i < files.length; i++)
        {
            na = files[i].getName();
            String name = na.substring(0, na.indexOf("."));
            String ext = na.substring(na.lastIndexOf("."));
            if(ext.equals(".wav") || ext.equals(".mp3"))
            {
                ind = Integer.parseInt(name);
                File fa = new File(SPATH+na);
                System.out.println(fa.exists());
                tabSd[ind]= minim.loadFile(SPATH+na);
            }
        }
        return tabSd;
    }

    public static void init(Minim minim) {
        audiotab = loadSounds(SPATH, minim);
    }
    
    public static void play(int track) {
        audiotab[track].play();
        song = track;
    }
    
    public static void stop()
    {
        audiotab[song].pause();
        audiotab[song].rewind();
    }
    
    public static void quit()
    {
        for(int i =0;i<audiotab.length;++i)
            audiotab[i].close();
    }
}
