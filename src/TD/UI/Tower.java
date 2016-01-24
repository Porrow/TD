package TD.UI;

import TD.IO.Read;
import TD.TD;
import java.util.ArrayList;
import processing.core.*;

public class Tower
{
    public static final String IMGFILE = "res/img/tower/towers.png";            //Chemin d'accès aux images des tours
    public static final String IMGDFILE = "res/img/tower/destroy.png";          //chemin d'accès aux propriétés : att, range, speed, cost
    public static final String IMGIFILE = "res/img/tower/improve.png";          //chemin d'accès aux propriétés : att, range, speed, cost
    public static final String PROPATH = "res/dat/tower/";                      //chemin d'accès aux propriétés : att, range, speed, cost
    public static final int MAXLEVEL = 2;
    public static final int[] colR = {0, 150, 0, 70};                           //Couleur de la portée
    
    public static PImage[][] tabImg;
    public static ArrayList<Tower> towers = new ArrayList<>();
    public static int[][][] tabProp;                                            //Contient les caractéristiques de chaque type de tours à chacun de ses level : 0:att; 1:range; 2:speed; 3:cost
    public static int selec = -1;
    public static PImage imgDestroy;
    public static PImage imgImprove;
    
    public long time;                                                           //Timer les projectiles
    public Unit focus;                                                          //Unité ciblée
    public int type;                                                            //type de tour
    public int xc, yc, att, lvl, range, speed, cost;                            //Dans l'ordre : coordonnées, attaque, niveau, portée, vitesse, coût
    
    public Tower(int type, int x, int y)                                        //Type de tour, coordonnées sur la grille
    {
        this.type = type;
        time = 0;
        focus = null;
        xc = x;
        yc = y;
        lvl = 0;
        att = tabProp[0][type][lvl];
        range = tabProp[1][type][lvl];
        speed = tabProp[2][type][lvl];
        cost = tabProp[3][type][lvl];
    }
    
    public void levelUp()
    {
        if(lvl < MAXLEVEL)
        {
            TD.gold-=tabProp[3][type][lvl];
            lvl++;
            att = tabProp[0][type][lvl];
            range = tabProp[1][type][lvl];
            speed = tabProp[2][type][lvl];
            cost = tabProp[3][type][lvl];
        }
    }
    
    public static void init()
    {
        tabProp = Read.loadFiles(PROPATH);
    }
    
    public static void draw(PGraphics g)
    {
        int w = Ground.W;
        for(Tower u : towers)
            g.image(tabImg[u.type][u.lvl], u.xc * Ground.W, u.yc * Ground.W);
        if(!towers.isEmpty() && selec != -1)                                    //Si une tourelle est sélectionnée...
        {
            Tower t = towers.get(selec);
            
            g.noStroke();
            g.fill(colR[0], colR[1], colR[2], colR[3]);
            g.ellipse(t.xc * w + 20, t.yc * w + 20, t.range * 2, t.range * 2);
            g.image(imgDestroy, (t.xc-1) * w, t.yc * w - 5);
            if(t.lvl < MAXLEVEL)
                g.image(imgImprove, (t.xc+1) * w, t.yc * w - 5);
            g.image(tabImg[t.type][t.lvl], t.xc * Ground.W, t.yc * Ground.W);
        }
    }
}
