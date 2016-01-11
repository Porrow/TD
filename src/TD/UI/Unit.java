package TD.UI;

import TD.IO.Read;
import processing.core.*;
import java.util.ArrayList;

public class Unit 
{
    public static final String IMGPATH = "res/img/unit/";                       //Chemin d'accès aux images des unités
    public static final String PROFILE = "res/dat/unit/lsg.txt";                 //fichier des propriétés (life speed)
    
    public static PImage[] tabImg;
    public static ArrayList<Unit> units = new ArrayList<>();
    public static int[][] tabProp;                                              //Contient les propriétés de chaque type d'unité
    
    public PImage img;
    public int x, y, life, speed, gold;                                         //Dans l'ordre : coordonnées, vie, vitesse, or
    
    public Unit(int type)
    {
        img = Ground.tabImg[type];
        life = tabProp[type][0];
        speed = tabProp[type][1];
        gold = tabProp[type][2];
        x = 0;
        y = 0;
    }
    
    public static void init()
    {
        loadProperties();
        units.add(new Unit(3));
    }
    public static void loadProperties()                                         //Chargement des informations sur chaque textures : life, speed
    {
        tabProp = Read.string2Int(Read.readString(PROFILE));
    }
    
    public static void draw(PGraphics g)
    {
        for(Unit u : units)
            g.image(u.img, u.x, u.y);
    }
}
