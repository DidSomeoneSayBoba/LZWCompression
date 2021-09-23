import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class LeTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LZW lzw = new LZW();
		lzw.convertToBinary(new File("lzw-file1.txt"));		
		lzw.convertToBinary(new File("lzw-file2.txt"));
		lzw.convertToBinary(new File("lzw-file3.txt"));
		String file = "compressedlzw-file1.txt";
		lzw.decompress(lzw.readFromFile(file),"decompressedlzw-file1.txt");
		String file1 = "compressedlzw-file2.txt";
		lzw.decompress(lzw.readFromFile(file1),"2.txt");
		String file2 = "compressedlzw-file3.txt";
		lzw.decompress(lzw.readFromFile(file2),"3.txt");
	}

}
