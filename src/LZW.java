import java.util.*;
import java.io.*;

public class LZW{
    final int NUM=9;

    ArrayList<String> initialdict;
    public LZW()
    {
        //initialized dictionary to save time in each method
    	resetArray();
    }

    public void convertToBinary(File file) throws IOException{
    	long start = System.currentTimeMillis();
    	
		StringBuilder ret = new StringBuilder("");
		ArrayList<String> dict = new ArrayList(initialdict);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String current = ""+((char)(reader.read()));
		String next = ""+((char)(reader.read()));
		FileWriter os=new FileWriter("compressed"+file.getName());
		BufferedWriter o = new BufferedWriter(os);
    	PrintWriter writer = new PrintWriter(o);
		while(reader.ready())
		{
			//max entry size is 2^NUM
			if(!dict.contains(current+next)&&dict.size()<Math.pow(2,NUM))
			{
				//begin binary conversion code(seems like encoder-writer intended to do this)
				dict.add(current+next);
				//get most recent dict index, convert to binary str, pad out zeroes
				
				String binaryver = Integer.toBinaryString(dict.indexOf(current));
				binaryver = String.format("%"+NUM+"s",binaryver);
				binaryver = binaryver.replaceAll(" ","0");
				//write to file
		        writer.print(binaryver);
				current +=next;
			}
			else
			{
				current = next;  
				
			}
			next = ""+((char)(reader.read()));
		}
		writer.close();
		
		long finish = System.currentTimeMillis();
		System.out.println("Time Elapsed: "+(finish - start));
	}
        public void decompress(String compressed, String outname) throws IOException{//changed to void to match compression
        	long start = System.currentTimeMillis();
        	ArrayList<String> dict = new ArrayList(initialdict);
        	
            
            FileWriter os=new FileWriter(outname);
            		BufferedWriter o = new BufferedWriter(os);
        	PrintWriter writer = new PrintWriter(o);
        	if(compressed.length()<NUM)
        	{
        		long finish = System.currentTimeMillis();
        		System.out.println("Time Elapsed decompress: "+(finish - start));
        		writer.print("file empty");
        		writer.close();
        		return;
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
                prev=dict.get(c-1);
                compressed=compressed.substring(NUM);
            }
            writer.print(decompressed);
            

               
            writer.close();
            long finish = System.currentTimeMillis();
    		System.out.println("Time Elapsed decompress: "+(finish - start));
    		
    		
    		
    }

        public String readFromFile(String file)
        {
        	
        	StringBuffer builder = new StringBuffer();
        	try {
        		BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
				while(reader.ready())
				{
					builder.append(reader.readLine());
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return builder.toString();
        }
        private void resetArray() {
        	initialdict = new ArrayList();
        	for(int i =0;i<=255;i++)
    		{
    			initialdict.add(""+(char)i);
    		}
        }
}

