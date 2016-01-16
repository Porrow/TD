package TD.UI;

import TD.IO.Read;
import java.util.ArrayList;
import processing.core.*;

public class Tower
{
    public static final String IMGPATH = "res/img/tower/";                      //Chemin d'accès aux images des tours
    public static final String PROPATH = "res/dat/tower/";                      //chemin d'accès aux propriétés : att, range, speed, cost
    
    public static PImage[] tabImg;
    public static ArrayList<Unit> towers = new ArrayList<>();
    public static int[][][] tabProp;                                            //Contient les caractéristiques de chaque type de tours à chacun de ses level : 0:att; 1:range; 2:speed; 3:cost
    
    public int xc, yc, att, lvl, range, speed, cost;                            //Dans l'ordre : coordonnées, attaque, niveau, portée, vitesse, coût
    
    public Tower(int type, int x, int y)                                        //Type de tour, coordonnées sur la grille
    {
        xc = x;
        yc = y;
        lvl = 0;
        att = tabProp[0][type][lvl];
        range = tabProp[1][type][lvl];
        speed = tabProp[2][type][lvl];
        cost = tabProp[3][type][lvl];
    }
    
    public static void init()
    {
        tabProp = Read.loadFiles(PROPATH);
    }
    
    public static void draw(PGraphics g)
    {
        
    }
}
