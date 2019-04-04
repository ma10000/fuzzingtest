package createfuzzing;
import java.util.*;
public class GenerateFuzzing {
	public  static int number =0;
	public static String generateCase() {
		StringBuffer br = new StringBuffer();
		StringBuffer br1 = new StringBuffer();
		
		int num=(int)(Math.random()*49)+1;
		//System.out.println(num);
		for(int i=0;i<num;i++) {
			int charnum=(int)(Math.random()*94)+32;
			//System.out.println(charnum);
			char a = (char)charnum;
			//System.out.println(a);
			br.append(String.valueOf(a));
			number++;
		}
		//br1 = translationChar(br);
		//System.out.println(br1.toString());
		return br.toString();
		
	}
	
	public static String translationChar(String br) {
		
		br=br.replace("\\","\\\\");	
		
		br=br.replace("\"","\\\"");
		
		
		return br;
	}



}
