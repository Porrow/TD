package TD.UI;

import TD.IO.*;
import TD.TD;
import processing.core.*;

public class Ground 
{
    public static final String DATPATH = "res/dat/";                            //Chemin d'accès aux données
    
    static int[] tabMap;
            
    public PImage img;
    public int x;
    public int y;
    public boolean isWalkable;
    
    public Ground(PImage img, int x, int y, boolean isWalkable, boolean isBuildable)
    {
        this.img = img;
        this.x = x;
        this.y = y;
        this.isWalkable = isWalkable;
    }
    public static void loadMaps()
    {  
        tabMap = Read.read(DATPATH+"map1.dat");
    }
    
    public static void draw(PGraphics g)
    {
        //g.image(TD.grass, 0, 0);
        //g.image(TD.path, 40, 0);
        for(int i = 0; i < tabMap.length; i++)
        {
            g.image(TD.tabImg[tabMap[i]], i * 40, (i % 720) * 40);
        }
    }
}
