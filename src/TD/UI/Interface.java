package TD.UI;

import processing.core.PGraphics;
import processing.core.PImage;

public class Interface
{
    public static final String IMGPATH = "res/img/interface/";                  //Chemin d'accès aux images de l'interface
    
    public static PImage[] tabImg;
    public static int selec = 0;
    public static int mx, my;
    
    public static void draw(PGraphics g)
    {
        if(selec != 0)
            g.image(Tower.tabImg[0], mx - Ground.W / 2, my - Ground.W * 3 / 4);
        g.image(tabImg[selec], Ground.WIDTH * Ground.W, 0);
    }
}