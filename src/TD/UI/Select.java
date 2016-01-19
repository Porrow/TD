package TD.UI;

import TD.TD;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Select {
    public static float[] size = new float [Ground.tabMap.length];
    public static int[][] col;
    
    public static void init(){
        col = new int[Ground.tabMap.length][];
        for(int i=0;i<Ground.tabMap.length;++i)
            col[i]=TD.COLOR1;
    }
    
    public static void draw(PGraphics g){
        g.background(Menu.tabImg[1]);
        g.textAlign(PApplet.CENTER,PApplet.TOP);
        for(int i=0;i<Ground.tabMap.length;++i) {
            g.fill(col[i][0], col[i][1], col[i][2]);
            g.text("Terrain n°" + (i+1), 640, ((i+1)*(600/Ground.tabMap.length)));
            g.fill(255, 255, 255);
            size[i]=g.textWidth("Terrain n°" + (i+1));
        }  
        g.textAlign(PApplet.CENTER,PApplet.BASELINE);
    }
}
