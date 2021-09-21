import java.util.*;
import java.io.*;

public class LZW{
    final int NUM=9;
    ArrayList<String> initialdict;
    public LZW()
    {
    	//initializes 0-255 dictionary for use in both methods
    	initialdict = new ArrayList();
    	for(int i =0;i<=255;i++)
		{
			initialdict.add(""+(char)i);
		}
       
    }
    private void writeToFile(File file,String str) throws IOException{
    	//modified to just write to file (file)the input string str
        try(FileWriter os=new FileWriter(file)){
            os.write(str);
            os.close();
        }
    }
    public void convertToBinary(File file) throws IOException{
    	long start = System.currentTimeMillis();
    	
		StringBuilder ret = new StringBuilder("");
		ArrayList<String> dict = initialdict;
		BufferedReader reader = new BufferedReader(new FileReader(file));

		
		String current = ""+((char)(reader.read()));
		String next = ""+((char)(reader.read()));
		while(reader.ready())
		{
			//optimized to fit to bitsize
			if(!dict.contains(current+next)&&dict.size()<Math.pow(2,NUM))
			{
				dict.add(current+next);
				//get most recent dict index, convert to binary str, pad out zeroes
				String binaryver = Integer.toBinaryString((dict.size()-1));
				binaryver = String.format("%"+NUM+"s",binaryver);
				binaryver = binaryver.replaceAll(" ","0");
				//write
				ret.append(binaryver);
				
				//System.out.println(ret.toString());
				current +=next;
			}
			else
			{
				current = next;  
				
			}
			next = ""+((char)(reader.read()));
		}
		File out = new File("compressed"+file.getName());
		writeToFile(out, ret.toString());
		long finish = System.currentTimeMillis();
		System.out.println("Time Elapsed Original: "+(finish - start));
	}
        public String decompress(String compressed){//changed to void to match compression
        	long start = System.currentTimeMillis();
            ArrayList<String> dict=initialdict;
            
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
            
            long finish = System.currentTimeMillis();
    		System.out.println("Time Elapsed Original: "+(finish - start));
    		return(decompressed);
    }
    /**
     * writeToFile() and decompress() methods are working. I haven't touched the 
     * convertToBinary() method since I forked initially. 
     */
}

