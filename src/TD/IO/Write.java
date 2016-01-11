package TD.IO;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class Write 
{   
    public Write(String name, int data[])
    {
        try 
        {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(name));
            for(int i = 0; i < data.length; i++)
            {
                dos.writeInt(data[i]);
            }
            dos.close();
        }
        catch (IOException ex) {System.out.println("Erreur");}
    }
}
