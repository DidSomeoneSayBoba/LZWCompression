import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;

import java.io.*;

public class OriginalLZW{
    final int NUM=9;
    public OriginalLZW()
    {

    }
    
    private String convertToBinary(File file){
		StringBuilder ret = new StringBuilder("");
		ArrayList<String> dict = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		for(int i =0;i<=255;i++)
		{
			dict.add(""+(char)i);
		}
		String current = ""+((char)(reader.read()));
		String next = ""+((char)(reader.read()));
		while(reader.ready())
		{
			if(!dict.contains(current+next)&&dict.size()<512)
			{
				dict.add(current+next);
				current +=next;
			}
			else
			{
				current = next;  
			}
			next = ""+((char)(reader.read()));
		}
	}
        public String decompress(String compressed){
            ArrayList<String> dict=new ArrayList<String>();
            for (int i=0;i<256;i++){
                dict.add(""+(char)i);
            }
            String prev=""+(char)(Integer.parseInt(compressed.substring(0,NUM),2)),decompressed=prev;
            int c=0;
            while(compressed.length()>NUM){
                c=Integer.parseInt(compressed.substring(NUM,2*NUM),2);
                if(c<dict.size()){
                    dict.add(prev+dict.get(c).charAt(0));
                    decompressed+=dict.get(c);
                }
                else{
                    dict.add(prev+prev.charAt(0));
                    decompressed+=prev+prev.charAt(0);
                }
                prev=dict.get(c);
                compressed=compressed.substring(NUM);
            }
            return(decompressed);
    }
    /**
     * writeToFile() and decompress() methods are working. I haven't touched the 
     * convertToBinary() method since I forked initially. 
     */
}
