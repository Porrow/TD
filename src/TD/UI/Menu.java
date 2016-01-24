package TD.UI;

import TD.TD;
import processing.core.*;

public class Menu 
{
    public static final String IMGPATH = "res/img/menu/";
    public static PImage[] tabImg;
    public static int[] colJ = TD.COLOR1;
    public static int[] colS = TD.COLOR1;
    public static int[] colQ = TD.COLOR1;
    
    public static void draw(PGraphics g) 
    {
        g.background(tabImg[0]);
        g.textAlign(PApplet.BASELINE);
        g.fill(colJ[0], colJ[1], colJ[2]);
        g.text("JOUER", 530, 360);
        g.fill(colS[0], colS[1], colS[2]);
        g.text("SCORE", 520, 530);
        g.fill(colQ[0], colQ[1], colQ[2]);
        g.text("EXIT",560,700);
    }
}
