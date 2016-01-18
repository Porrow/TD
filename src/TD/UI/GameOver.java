package TD.UI;

import TD.TD;
import processing.core.*;


public class GameOver {
    
    public static final String IMGPATH = "res/img/gameover/";
    
    public static PImage[] tabImg;
    public static int[] colR = TD.COLOR1;
    
    public static void draw(PGraphics g) {
        g.background(tabImg[0]);
        g.textAlign(PApplet.CENTER);
        g.fill(colR[0], colR[1], colR[2]);
        g.text("Menu principal", 640, 650);
        g.fill(255, 255, 255);
    }
}
