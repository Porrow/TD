package TD.UI;

import TD.IO.*;
import processing.core.*;

public class Ground 
{
    //Constantes :
    public static final String IMGPATH = "res/img/ground/";                     //Chemin d'accès aux images du terrain
    public static final String MAPPATH = "res/dat/ground/maps/";                //Chemin d'accès aux maps
    public static final String PROFILE = "res/dat/ground/wb.txt";               //fichier des propriétés (walkable buildable)
    public static final int W = 40;                                             //Largeur d'un carré du terrain
    public static final int SPAWN = 17;                                         //Index correspondant au spawn
    public static final int WIDTH = 27;                                         //Largeur du terrain en carreaux
    
    //Variables :
    public static PImage[] tabImg;                                              //Contient toutes les images du terrain
    public static int[][][] tabMap;                                             //Contient tous les terrains
    public static PImage imgMap;                                                //Contient l'image (construite par le prog) du terrain
    public static int map;                                                      //Indique la map choisis
    public static int[][] tabProp;                                              //Contient les propriétés de chaque type de carré de terrain
    
    public static void init(int map, PGraphics g)                               //Initiaise le terrain et choisis une 1ere map
    {
        tabMap = Read.loadFiles(MAPPATH);                                       //Chargement des maps
        tabProp = Read.string2Int(Read.readString(PROFILE));                    //Chargement des informations sur chaque textures : walkable, buildable
        g.beginDraw();
        for(int i = 0; i < tabMap[map].length; i++)
            for(int j = 0; j < tabMap[map][i].length; j++)
                g.image(tabImg[tabMap[map][i][j]], j * W, i * W);
        g.endDraw();
        imgMap = g;
        Ground.map = map;
    }
    
    public static int[] getSpawn()
    {
        int[] coord = new int[2];
        for(int i = 0; i < tabMap[map].length; i++)
            for(int j = 0; j < tabMap[map][i].length; j++)
                if(tabMap[map][i][j] == SPAWN)
                {
                    coord[0] = j;
                    coord[1] = i;
                }
        return coord;
    }
    
    public static void draw(PGraphics g)                                        //Affichage du terrain (60 / s)
    {
        g.image(imgMap, 0, 0);
    }
}