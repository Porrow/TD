package TD.IO;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read
{
    public static int[] readInt(String name)                                    //Lit des entiers dans le fichier name
    {
        int data[];
        try
        {
            int n = -1;
            File f = new File(name);
            if(f.exists())
            {
                System.out.println("taille fichier : "+f.length()+" Byte(s)");
                data = new int[(int)(f.length()/4)];
                DataInputStream dis = new DataInputStream(new FileInputStream(f));
                boolean fin = false;
                for(int i = 0; !fin; i++)
                {
                    try
                    {
                        n = dis.readInt();
                    } 
                    catch (EOFException ex) {fin = true;}
                    if(!fin)
                    {
                        data[i] = n;
                        //System.out.println("valeur lue = "+n);
                    }
                }
                dis.close();
            }
            else
            {
                data = new int[1];
                data[0] = 0;
            }
        }
        catch(FileNotFoundException fnfe)
        {
            data = null;
            System.out.println("Un fichier n'a pas été trouvé.");
        }
        catch (IOException ex) 
        {
            data = null;
            System.out.println("Erreur lors de la lecture d'un fichier.");
        }
        catch(NegativeArraySizeException nase)
        {
            data = null;
            System.out.println("Taille de tableau négative.");
        }
        return data;
    }
    
    public static String readString(String name)                                //Lit des chaînes de caractères dans le fichier name
    { 
        String txt = "";
        try 
        {
            String ligne;
            BufferedReader fichier = new BufferedReader(new FileReader(name));
            while ((ligne = fichier.readLine()) != null) 
            {
                txt += ligne;
            }
            fichier.close();
        }
        catch (Exception e) {System.out.println("Impossible de lire le fichier \"name\"");}     
        return txt;
    }
    
    public static int[] string2Int(String ch)
    {
        int[] tab = new int[ch.length()];
        for(int i = 0; i < ch.length(); i++)
            tab[i] = Integer.parseInt(ch.substring(i, i+1));
        return tab;
    }
}
