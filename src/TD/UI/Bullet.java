package TD.UI;

import TD.Move;
import java.util.ArrayList;
import processing.core.*;

public class Bullet 
{
    public static final String IMGPATH = "res/img/bullet/";                     //Chemin d'accès aux images des projectiles
    
    public static PImage[] tabImg;
    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public Tower t;                                                             //Tourelle à l'origine du projectile
    public Unit u;                                                              //Unité ciblée
    public int speed, time;
    public float[] vect = new float[2];
    public float d;
    public float x, y;                                                          //position du projectile
    public float x1, y1;                                                        //position unité
    
    public Bullet(Tower t)
    {
        this.t = t;
        u = t.focus;
        if(t.type != 4)
        {
            speed = Tower.tabProp[4][t.type][0];
            x = t.xc * Ground.W + Ground.W / 2;
            y = t.yc * Ground.W + Ground.W / 2;
            set();
        }
    }
    
    public void set()
    {
        x1 = u.x + Ground.W / 2;
        y1 = u.y + Ground.W / 2;
        d = Move.getDistance(x, y, x1, y1);
        vect[0] = (x - x1) / d * 20f;
        vect[1] = (y - y1) / d * 20f;
    }
    
    public static void draw(PGraphics g) 
    {
        synchronized(bullets)
        {
            g.imageMode(g.CENTER);
            for(Bullet b : bullets)
            {
                if(b.t.type != 4)
                    g.image(tabImg[b.t.type], b.x, b.y);
                else
                {
                }
            }
            g.imageMode(g.CORNER);
        }
    }
}
