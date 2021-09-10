import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;
public class LZW {
	public static void main (String[] args)
	{
		
	}
	private String convertToBinary(File file)
	{
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
}
/**
 * hey really sorry whoever got this I could only get the patterns into the dictionary
 * */
