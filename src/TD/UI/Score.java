package TD.UI;

import TD.IO.Read;
import TD.IO.Write;
import TD.TD;
import java.util.ArrayList;
import processing.core.*;

public class Score {

    public static final String IMGPATH = "res/img/score/";
    public static final String SCOREPATH = "res/dat/score/";
    public static PImage[] tabImg;
    public static int[] score;
    public static int[] colR = TD.COLOR1;

    public static void init() {
        score = Read.readInt(SCOREPATH + "score.txt");
    }

    
    public static void save(int score) {
        boolean change = false;
        int i;
        for (i = 0; i < 8; ++i) {
            if (score > Score.score[i]) {
                change = true;
                break;
            }
        }
        if (change) {
            int aux=Score.score[i];
            for(int j=i;j<7;++j) {
                aux=Score.score[j];
                Score.score[j]=score;
                score=aux;
            }
            Score.score[i] = score;
            String all = "";
            /*for(i=0;i<8;++i)
                all=all+Score.score[i];*/
            Write write = new Write(Score.SCOREPATH+"score.txt",Score.score);
        }
    }
    
    public static void draw(PApplet p) {
        p.g.background(tabImg[0]);
        p.textAlign(PApplet.BASELINE);
        p.g.fill(colR[0], colR[1], colR[2]);
        p.g.text("RETOUR", 1000, 680);
        p.g.fill(255, 255, 255);
        p.textAlign(PApplet.CENTER);
        for (int i = 0; i < score.length; ++i) {
            p.g.text(score[i], 640, 240 + (i * 60));
        }
    }
}
