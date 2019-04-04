package main;

import java.util.ArrayList;

import createfuzzing.GenerateFuzzing;
import creategene.StringChangeGene;
import execute.ExecuteFuzzing;
import execute.Executecmd;
import geneticachieve.GeneticAchieve;
import geneticoperate.geneticKid;
import population.Individual;

public class Main {
	public static void main(String[] args) {
		
		
//	
//		 System.out.println("这是主类！"); String searchname = "\" dsas' or 1=1#\""; String
//		 commandstr ="java -jar C:\\Users\\mace\\Desktop\\sqltest.jar "+searchname;
//		 String temp= Executecmd.Execmd(commandstr); System.out.println(temp);
//		 
/// 	 int b = '1';
//		 System.out.println(b);
		
		
//		ExecuteFuzzing ef = new ExecuteFuzzing();
//		ef.executeFuzzing();
		GeneticAchieve ga = new GeneticAchieve();
		ga.startIteration();
		
//		ArrayList<Individual> list = new ArrayList<Individual>();
//		Individual f = new Individual();
//		Individual m = new Individual();
//		String ftest = "qweirj423";
//		String mtest = "23478";
//		
//		String fascii= StringChangeGene.stringChangeGene(ftest);
//		String mascii= StringChangeGene.stringChangeGene(mtest);
//		
//		String fgcode = StringChangeGene.geneChangeGraycode(fascii);
//		String mgcode = StringChangeGene.geneChangeGraycode(mascii);
//		
//		f.setGene(fgcode);
//		f.mutation(3);
//		String tgcode = f.getGene();
//		String tascii = StringChangeGene.graycodeChangeGene(tgcode);
//		String tchars = StringChangeGene.geneChangeString(tascii);
//		System.out.println(tchars);
		
//		m.setGene(mgcode);
//		
//		list = geneticKid.geneticChild(f,m);
//		
//		String[] tgcode = new String[list.size()];
//		for(int i=0;i<list.size();i++) {
//			tgcode[i] = list.get(i).getGene();
//		}
//		String[] tascii = new String[list.size()];
//		for(int i=0;i<list.size();i++) {
//			tascii[i] = StringChangeGene.graycodeChangeGene(tgcode[i]);
//		}
//		String[] tchars = new String[list.size()];
//		for(int i=0;i<list.size();i++) {
//			tchars[i] = StringChangeGene.geneChangeString(tascii[i]);
//		}
//		for(int i=0;i<list.size();i++) {
//			System.out.println(tchars[i]);
//		}
		
		

	}


}
