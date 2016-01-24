package TD.UI;

import TD.IO.Read;
import processing.core.*;
import java.util.ArrayList;

public class Unit
{
    public static final String IMGPATH = "res/img/unit/";                       //Chemin d'accès aux images des unités
    public static final String PROFILE = "res/dat/unit/lsg.txt";                //fichier des propriétés (life speed gold)
    public static final String ANIFILE = "res/dat/unit/anim.txt";               //fichier des propriétés (life speed gold)
    public static final int colli = 10;
    
    public static PImage[] tabBaseImg;                                          //Tableau qui contient les sprites sheet
    public static PImage[][][] tabImg = new PImage[13][][];                     //Double tableau d'image
    public static ArrayList<Unit> units = new ArrayList<>();
    public static ArrayList<Unit> toSpawn = new ArrayList<>();
    public static int[][] tabProp;                                              //Contient les propriétés de chaque type d'unité
    public static int[] spawn;                                                  //Coordonnées du spawn
    public static int[][] tabAnim;
    public static int wave = 1;                                                 //Nombre d'unités à faire spawn
    public static int[] colB1 = {0, 200, 0, 255};
    public static int[] colB2 = {200, 200, 0, 255};
    public static int[] colB3 = {200, 0, 0, 255};

    public int img;                                                             //Index de l'image et la première vague
    public long time = 0;                                                       //Variable repère pour les animations
    public int xc, yc;                                                          //Coordonnées de la case associée
    public float x, y;                                                          //Dans l'ordre : coordonnées, type, vie, vitesse(pix/s), or, direction
    public int type, life, speed, gold, direc;
    
    public Unit(int type)
    {
        this.type = type;
        img = 0;
        life = tabProp[type][0];
        speed = tabProp[type][1];
        gold = tabProp[type][2];
        xc = spawn[0];
        yc = spawn[1];
        x = xc * Ground.W;
        y = yc * Ground.W;
        direc = getDirection();
    }
    
    public int getDirection() {
        int c;
        if (yc != 0) {
            c = Ground.tabMap[Ground.map][yc - 1][xc];                          //Case juste au-dessus
            if (Ground.tabProp[c][0] == 1 && direc != 1)                        //Si on peut marcher dessus et qu'il ne s'agit pas de la direction opposée
            {
                yc--;
                return 0;
            }
        }
        if (yc != 18) {
            c = Ground.tabMap[Ground.map][yc + 1][xc];                          //Case juste en-dessous
            if (Ground.tabProp[c][0] == 1 && direc != 0) {
                yc++;
                return 1;
            }
        }
        if (xc != 0) {
            c = Ground.tabMap[Ground.map][yc][xc - 1];                          //Case juste à gauche
            if (Ground.tabProp[c][0] == 1 && direc != 3) {
                xc--;
                return 2;
            }
        }
        xc++;                                                                   //Fonctionne : pas d'impasses
        return 3;
    }

    public static void init()
    {
        loadProperties();
        //units.add(new Unit(3));
    }
    
    public static void newWave(int wave) 
    {
        
        loadEnemies((int) ((wave/2)*1.5),0);
        loadEnemies(wave/2,1);
        loadEnemies((int)((wave/3)*0.9),2);
        loadEnemies(wave/5,3);
    }

    
    private static void loadEnemies(int number,int type) 
    {
        for (int i = 0; i < number; ++i)
            toSpawn.add(new Unit(type));
    }

    public static void loadProperties()                                         //Chargement des informations sur chaque unités : life, speed..
    {
        tabProp = Read.string2Int(Read.readString(PROFILE));
        tabAnim = Read.string2Int(Read.readString(ANIFILE));
    }
    
    public static void draw(PGraphics g)
    {
        synchronized(units)
        {
            for(Unit u : units)
            {
                g.image(tabImg[u.type][u.direc][u.img], u.x, u.y);
                g.rectMode(g.CENTER);
                g.strokeWeight(1);
                g.stroke(1);
                if(tabProp[u.type][0] <= 100)
                {
                    g.fill(colB1[0], colB1[1], colB1[2], colB1[3]);
                    g.rect(u.x + 20, u.y - 5, u.life/2, 3);
                }
                else if(tabProp[u.type][0] > 100 && tabProp[u.type][0] <= 400)
                {
                    g.fill(colB2[0], colB2[1], colB2[2], colB2[3]);
                    g.rect(u.x + 20, u.y - 5, u.life / 4, 4);
                }
                else
                {
                    g.fill(colB3[0], colB3[1], colB3[2], colB3[3]);
                    g.rect(u.x + 20, u.y - 5, u.life / 20, 6);
                }
                //g.rect(u.x + 20, u.y - 5, u.life, 3);
                g.rectMode(g.CORNER);
            }
        }
    }
}
