package com.vip.nlp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
public class StoreFileObject {
	//java serialize a object to file	
	public static void writeObject(String path,Object map) throws IOException{
		File f=new File(path); 
		FileOutputStream out=new FileOutputStream(f); 
		ObjectOutputStream objwrite=new ObjectOutputStream(out);
		objwrite.writeObject(map);
		objwrite.flush(); 
		objwrite.close(); 
	}
 
	// read the object from the file
	public static Object readObject(String path) throws IOException, ClassNotFoundException{		
		FileInputStream in=new FileInputStream(path); 
		ObjectInputStream objread=new ObjectInputStream(in);
		Object map=objread.readObject();		
		objread.close();
		return map;
	}
 
}
