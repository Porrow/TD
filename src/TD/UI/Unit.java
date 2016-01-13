package TD.UI;

import TD.IO.Read;
import processing.core.*;
import java.util.ArrayList;

public class Unit 
{
    public static final String IMGPATH = "res/img/unit/";                       //Chemin d'accès aux images des unités
    public static final String PROFILE = "res/dat/unit/lsg.txt";                //fichier des propriétés (life speed gold)
    
    public static PImage[] tabImg;
    public static ArrayList<Unit> units = new ArrayList<>();
    public static int[][] tabProp;                                              //Contient les propriétés de chaque type d'unité
    public static int[] spawn;                                                  //Coordonnées du spawn
    
    public PImage img;
    public int xc, yc;                                                          //Coordonnées de la case associée
    public float x, y;                                                          //Dans l'ordre : coordonnées, vie, vitesse(pix/s), or, direction
    public int life, speed, gold, direc;
    
    public Unit(int type)
    {
        img = tabImg[type];
        life = tabProp[type][0];
        speed = tabProp[type][1];
        gold = tabProp[type][2];
        xc = spawn[0];
        yc = spawn[1];
        x = xc * Ground.W;
        y = yc * Ground.W;
        direc = getDirection();
    }
    
    public int getDirection()
    {
        int c;
        c = Ground.tabMap[Ground.map][yc - 1][xc];                              //Case juste au-dessus
        System.out.println(Ground.tabProp[c][0]);
        if(Ground.tabProp[c][0] == 1 && direc != 1)
        {
            yc--;
            return 0;
        }
        c = Ground.tabMap[Ground.map][yc + 1][xc];                              //Case juste en-dessous
        if(Ground.tabProp[c][0] == 1 && direc != 0)
        {
            yc++;
            return 1;
        }
        c = Ground.tabMap[Ground.map][yc][xc - 1];                              //Case juste à gauche
        if(Ground.tabProp[c][0] == 1 && direc != 3)
        {
            xc--;
            return 2;
        }
        xc++;                                                                   //Fonctionne : pas d'impasses
        return 3;
    }
    
    public static void init()
    {
        loadProperties();
        spawn = Ground.getSpawn();
        units.add(new Unit(0));
    }
    public static void loadProperties()                                         //Chargement des informations sur chaque textures : life, speed
    {
        tabProp = Read.string2Int(Read.readString(PROFILE));
    }
    
    public static synchronized void draw(PGraphics g)
    {
        
        for(Unit u : units)
        {
            g.image(u.img, (int)(u.x), (int)(u.y));
            //System.out.println(u.y+"x = "+u.x);
            //u.y += 1;
        }
    }
}
