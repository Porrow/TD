package TD.UI;

import TD.UI.Tower;
import processing.core.PGraphics;
import processing.core.PImage;

public class Interface
{
    public static final String IMGPATH = "res/img/interface/";                  //Chemin d'accès aux images de l'interface
    
    public static PImage[] tabImg;
    public static int selec = 0;
    public static long inter = 0;
    public static int mx, my;
    
    public static void draw(PGraphics g)
    {
        if(selec != 0)
        {
            int x = mx - Ground.W / 2;
            int y = my - Ground.W * 3 / 4;
            int range = Tower.tabProp[1][selec - 1][0];
            g.noStroke();
            g.fill(Tower.colR[0], Tower.colR[1], Tower.colR[2], Tower.colR[3]);
            g.ellipse(mx, my - 10, range / 2, range / 2);
            g.image(Tower.tabImg[0], x, y);
        }
        g.image(tabImg[selec], Ground.WIDTH * Ground.W, 0);
        if(inter>50)
        {
            g.fill(255, 0, 0);
            g.textSize(20);
            g.text("Prochaine vague dans: "+((inter/1000)+1), 1010, 30);
            g.textSize(60);
        }
    }
}