import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class LeTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LZW lzw = new LZW();
		lzw.convertToBinary(new File("lzw-file3.txt"));
		String file = "lzw-file3.txt";
		
		System.out.println(lzw.decompress(file));
		
	}

}
