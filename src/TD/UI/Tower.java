package TD.UI;

import TD.IO.Read;
import java.util.ArrayList;
import processing.core.*;

public class Tower
{
    public static final String IMGPATH = "res/img/tower/";                      //Chemin d'accès aux images des tours
    public static final String PROPATH = "res/dat/tower/";                      //chemin d'accès aux propriétés : att, range, speed, cost
    public static final int[] colR = {0, 150, 0, 70};
    
    public static PImage[] tabImg;
    public static ArrayList<Tower> towers = new ArrayList<>();
    public static int[][][] tabProp;                                            //Contient les caractéristiques de chaque type de tours à chacun de ses level : 0:att; 1:range; 2:speed; 3:cost
    public static int selec;
    
    public int img, type;                                                       //Index de l'image dans tabImg, type de tour
    public int xc, yc, att, lvl, range, speed, cost;                            //Dans l'ordre : coordonnées, attaque, niveau, portée, vitesse, coût
    
    public Tower(int type, int x, int y)                                        //Type de tour, coordonnées sur la grille
    {
        this.type = type;
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
        if(!towers.isEmpty())
        {
            Tower t = towers.get(selec);
            int range = Tower.tabProp[1][t.type][0];
            g.noStroke();
            g.fill(colR[0], colR[1], colR[2], colR[3]);
            g.ellipse(t.xc * Ground.W + 20, t.yc * Ground.W + 20, range / 2, range / 2);
        }
        for(Tower u : towers)
        {
            g.image(tabImg[u.img], u.xc * Ground.W, u.yc * Ground.W);
        }
    }
}
