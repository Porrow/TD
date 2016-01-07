package TD.UI;

import TD.IO.*;
import TD.TD;
import processing.core.*;

public class Ground 
{
    public static final String DATPATH = "res/dat/";                            //Chemin d'accès aux données
    public static final int w = 40;                                             //Largeur d'un carré du terrain
    public static final int width = 27;                                         //Largeur du terrain en carreaux
    
    static int[] tabMap;
    
    public static void loadMaps()                                               //Chargement des maps
    {
        tabMap = Read.string2Int(Read.readString(DATPATH+"map1.txt"));
    }
    
    public static void draw(PGraphics g)                                        //Affichage du terrain (60 / s)
    {
        for(int i = 0; i < tabMap.length; i++)
            g.image(TD.tabImg[tabMap[i]], (i % width) * w, (i / width) * w);
    }
}
