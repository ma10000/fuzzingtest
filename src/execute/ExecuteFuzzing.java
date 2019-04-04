package execute;

import createfuzzing.GenerateFuzzing;
/**
 * 
 * @author mace
 *
 *
 */
public class ExecuteFuzzing {
	public static String[] execute(String example) {
		
		String str = GenerateFuzzing.translationChar(example);
		String[] strlist = null;

		String searchname = "\""+str+"\""; 
		String commandstr ="java -jar C:\\Users\\mace\\Desktop\\sqltest.jar "+searchname;
		String tmp= Executecmd.Execmd(commandstr);
		strlist = tmp.split("\n");
	
		return strlist;
	}
	
	public void executeFuzzing() {
		while(true) {
			String temp = GenerateFuzzing.generateCase();
			int num=GenerateFuzzing.number;
			System.out.println("��"+num+"��!");
			System.out.println("����������"+temp+"��");
			String[] strlist = execute(temp);
			if(strlist != null) {
				for(String ss:strlist) {
					System.out.println(ss);
					if(ss.contains("sqlע��")) {
						System.out.println("����SQLע��ۼ���");
					}
				}
			}
		}
		
	}

}
