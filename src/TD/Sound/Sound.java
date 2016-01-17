package TD.Sound;

import ddf.minim.*;
import java.io.File;

public class Sound {
    private static final String SPATH = "res/snd/";    
    static AudioPlayer[] audiotab;
    static AudioSample[] ping;
    private static int song = 0;
    
    private static AudioPlayer[] loadSongs(String path,Minim minim)
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
                File fa = new File(path+na);
                System.out.println(fa.exists());
                tabSd[ind]= minim.loadFile(path+na);
            }
        }
        return tabSd;
    }

    private static AudioSample[] loadSounds(String path,Minim minim)
    {
        String na;
        File f = new File(path);
        File[] files = f.listFiles();
        AudioSample[] tabSd = new AudioSample[files.length];
        for(int i = 0, ind; i < files.length; i++)
        {
            na = files[i].getName();
            String name = na.substring(0, na.indexOf("."));
            String ext = na.substring(na.lastIndexOf("."));
            if(ext.equals(".wav") || ext.equals(".mp3"))
            {
                ind = Integer.parseInt(name);
                File fa = new File(path+na);
                System.out.println(fa.exists());
                tabSd[ind]= minim.loadSample(path+na);
            }
        }
        return tabSd;
    }
    
    public static void init(Minim minim) {                                      //Charge le tableau de musiques
        audiotab = loadSongs(SPATH+"song/",minim);
        ping=loadSounds(SPATH+"snippet/",minim);
    }
    
    public static void playSounds(int track) {
       ping[track].trigger();
    }
    
    public static void play(int track) {                                        //Lance la fonction
        song=track;
        audiotab[track].loop();
    }
    
    public static void stop(){                                                  //met la musique en pause et la remet au début
        audiotab[song].pause();
        audiotab[song].rewind();
    }
    
    public static void quit(){
        for(int i =0;i<audiotab.length;++i)
            audiotab[i].close();
    }
}