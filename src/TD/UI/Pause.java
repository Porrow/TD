package TD.UI;

import TD.TD;
import processing.core.*;

public class Pause {
        public static final int[] COLOR = {0, 0, 0,150};
        public static PImage background=null;
        public static int[] colR = TD.COLOR1;
        public static int[] colS = TD.COLOR1;
        public static int[] colQ = TD.COLOR1;
        
        public static void draw (PGraphics g) {
            g.background(background);
            g.fill(COLOR[0], COLOR[1], COLOR[2], COLOR[3]);
            g.rect(0, 0, TD.w, TD.h);
            g.fill(255,255,255);
            g.textAlign(PApplet.CENTER);
            g.fill(colR[0], colR[1], colR[2]);
            g.text("Reprendre", 640, 360);
            g.fill(colS[0], colS[1], colS[2]);
            g.text("Abandonner", 640, 500);
            g.fill(colQ[0], colQ[1], colQ[2]);
            g.text("Quitter", 640, 640);
            
        }
}
