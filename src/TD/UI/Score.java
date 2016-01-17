package TD.UI;

import TD.IO.Read;
import TD.TD;
import processing.core.*;

public class Score {
    
    public static final String IMGPATH = "res/img/score/";
    public static final String SCOREPATH = "res/dat/score/";
    public static PImage[] tabImg;
    private static int [] score;
    public static int[] colR = TD.COLOR1;
    
    public static void init() 
    {
        score = Read.string2Int(Read.readString(SCOREPATH+"score.txt"))[0];   
    }
    
    public static void draw(PApplet p) 
    {
        p.g.background(tabImg[0]);
        p.g.fill(colR[0], colR[1], colR[2]);
        p.g.text("RETOUR", 1000, 680);
        p.g.fill(255, 255, 255);
        p.textAlign(PApplet.CENTER);
        for(int i = 0; i < score.length; ++i) 
        {
            p.g.text(score[i], 640, 240+(i*60));
        }
    }
}