package Packaging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.naming.Context;

public class StuInfoPreserve {
	
	private static String filename="StuInof.dat";
	
	public static void writeObjectToFile(Object obj)
    {
		File file =new File(filename);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }
	
	
	public static Object readObjectFromFile()
    {
		File file =new File(filename);
        Object temp=null;
        
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
        } catch (IOException e) {
          System.out.println("read object failed");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	System.out.println("class not exict");
            //e.printStackTrace();
        }
        return temp;
    }
   

}
