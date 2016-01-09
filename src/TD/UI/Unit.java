package TD.UI;

import processing.core.*;
import TD.TD;
import java.util.ArrayList;

public class Unit 
{
    public static final String IMGPATH = "res/img/unit/";                       //Chemin d'accès aux images des unités
    
    public static PImage[] tabImg;
    public static PImage[] tabUnit = new PImage[1];
    public static int[] unitLife = new int[1];
    public static ArrayList<Unit> units = new ArrayList<>();
    
    public PImage img;
    public int life, x, y;
    
    public Unit(int type)
    {
        img = Ground.tabImg[3];//tabUnit[type];
        life= 1; //unitLife[type];
        x=1280/2;
        y=720/2;
        units.add(this);
    }
    
    public static void draw(PGraphics g)
    {
        for(Unit u : units)
            g.image(u.img, u.x, u.y);
    }
}
