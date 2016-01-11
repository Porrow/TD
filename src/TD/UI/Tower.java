package TD.UI;

import java.util.ArrayList;
import processing.core.*;

public class Tower
{
    public static final String IMGPATH = "res/img/tower/";                       //Chemin d'accès aux images des tours
    
    public static PImage[] tabImg;
    public static ArrayList<Unit> towers = new ArrayList<>();
    
    public PImage img;
    public int x, y, att, lvl, reach, speed, cost;                                    //Dans l'ordre : coordonnées, attaque, niveau, portée, vitesse, coût
    
    public Tower(int type, int x, int y)
    {
        
    }
    public static void draw(PGraphics g)
    {
        
    }
}
