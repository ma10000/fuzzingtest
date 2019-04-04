package geneticachieve;

import java.util.ArrayList;
import java.util.List;

import createfuzzing.GenerateFuzzing;
import creategene.StringChangeGene;
import execute.ExecuteFuzzing;
import geneticoperate.geneticKid;
import population.Individual;

/**
 * 
 * @author mace
 *用于实现遗传算法的类
 */
public class GeneticAchieve {
	private int populationnum = 1000;//种群数量
	private ArrayList<Individual> population = new ArrayList<Individual>();
	private int mutationnum = 3;//变异的字符数
	private double mutationrate = 0.001;//变异的概率
	private int iterationnum = 100;//种群迭代次数
	
	private int generation = 1;//当前迭代次数
	
	private double bestscore;
	private double totalscore;//总的得分
	private double averagescore;//平均得分
	
	public GeneticAchieve() {
		super();
	}
	public GeneticAchieve(int populationnum) {
		this.populationnum = populationnum;
	}
	public void init() {
		initialisePopulation();
		calculateRoute();
		calculateScore();
		calculateTotalScore();
		calculateAverageScore();
		calculateBeatScore();
	}
	/**
	 * 初始化种群
	 */
	public void initialisePopulation() {
		for(int i=0;i<populationnum;i++) {
			Individual indi = new Individual();
			boolean flage = true;
			String geneticStr = null;//可能会出现空指针错误
			while(flage) {
				geneticStr = GenerateFuzzing.generateCase();
				if(geneticStr.length()>=4) {
					flage = false;
				}
			}
			System.out.println(i+"测试用例"+geneticStr);
			String geneticAscii = StringChangeGene.stringChangeGene(geneticStr);
			String geneticGcode = StringChangeGene.geneChangeGraycode(geneticAscii);
			indi.setGene(geneticGcode);
			population.add(indi);
			
		}
//		calculateRoute();
//		calculateScore();
		
	}
	/**
	 * 计算每个测试用例（种群中的个体）的路径
	 */
	public void calculateRoute() {
		for(int i=0;i<population.size();i++) {
			String geneticGcode = population.get(i).getGene();
//			System.out.println(i+population.get(i).toString());
			String geneticAscii = StringChangeGene.graycodeChangeGene(geneticGcode);
			String geneticStr = StringChangeGene.geneChangeString(geneticAscii);
			String[] list = ExecuteFuzzing.execute(geneticStr);
			System.out.println("在第"+generation+"遗传的种群中对第"+i+"个测试用例："+geneticStr+"进行测试。");
			ArrayList<String> slist = new ArrayList<String>();
			if(list != null) {
				boolean flag = false;
				for(int m=0;m<list.length;m++) {
					if(list[m].contains("sql注入")) {
						flag = true;
					}
				}
				if(flag) {
					System.out.println("在第"+(generation+1)+"遗传的种群中发现sql注入痕迹："+geneticStr+"。");
				}
				for(int m=0;m<list.length;m++) {
					if(list[m].matches("[1-9][0-9]*")) {
						slist.add(list[m]);
					}
				}
			}
			population.get(i).setRoute(slist);
				
		}
	}
	/**
	 * 计算每个测试用例（种群中的个体）的分数
	 */
	public void calculateScore() {
		for(int i=0;i<population.size();i++) {
			double score = population.get(i).getRoute().size();
			population.get(i).setScore(score);
		}
	}
	/**
	 * 计算当前种群的总分数
	 */
	public void calculateTotalScore() {
		double sum = 0;
		for(int i=0;i<population.size();i++) {
			sum += population.get(i).getScore();
		}
		totalscore = sum;
	}
	/**
	 * 计算当前种群的平均分数
	 */
	public void calculateAverageScore() {
		averagescore = totalscore/populationnum;
	}
	/**
	 * 计算最好的成绩
	 */
	public void calculateBeatScore() {
		double bests = 0;
		for(int i=0;i<population.size();i++) {
			if(population.get(i).getScore()>bests) {
				bests = population.get(i).getScore();
			}
		}
		bestscore = bests;
	}
	/**
	 * 用于产生下一代种群的方法
	 */
	public void produceGeneration() {
		int usedlength = 0;
		ArrayList<Individual> childpopulation = new ArrayList<Individual>();
//		for(int i=0;i<population.size();i++) {
//			if(population.get(i).getScore()==bestscore) {
//				childpopulation.add(geneticKid.clone(population.get(i)));
//				usedlength++;
//			}
//		}
		while(usedlength<populationnum) {
			System.out.println(averagescore);
			Individual p1 = getParentIndividual();
			while(p1==null) {
				p1 = getParentIndividual();
			}
			Individual p2 = getParentIndividual();
			while(p2==null) {
				p2 = getParentIndividual();
			}
			
			System.out.println(p1.toString());
			System.out.println(p2.toString());
			List<Individual> children = geneticKid.geneticChild(p1,p2);
			if(children != null) {
				for(int m=0;((m<children.size())&&(usedlength < populationnum));m++) {
					childpopulation.add(children.get(m));
					usedlength++;
				}
			}
		}
		//将下一代种群替换上一代种群
		List<Individual> t = population;
		population = childpopulation;
		t.clear();
		t=null;
		//基因突变
		
	
	}
	public void inherit() {
		produceGeneration();
		mutation();
		//计算当前个体的路径和分数
		calculateRoute();
		calculateScore();
		calculateTotalScore();
		calculateAverageScore();
		calculateBeatScore();
	}
	/**
	 * 用轮盘赌发选择用于交换基因产生下一代的个体
	 * @return 返回选择的父类个体
	 */
	public Individual getParentIndividual() {
//		System.out.println(totalscore);
		double part = Math.random()*totalscore;
//		System.out.println(part);
//		System.out.println(averagescore);
		double count =0;
		for(Individual indi:population) {
			count += indi.getScore();
			if(count>part && indi.getScore()>= averagescore) {
				return indi;
			}
		}
		
		return null;
	}
	public void mutation() {
		for(Individual indi:population) {
			if(Math.random()<mutationrate) {//发生基因突变
				indi.mutation(mutationnum);
			}
		}
	}
	public void startIteration() {
		generation = 1;
		init();
		
		
		while(generation<iterationnum) {
			generation++;
			inherit();
			
		}
	}
	public int getPopulationnum() {
		return populationnum;
	}
	public void setPopulationnum(int populationnum) {
		this.populationnum = populationnum;
	}
	public ArrayList<Individual> getPopulation() {
		return population;
	}
	public void setPopulation(ArrayList<Individual> population) {
		this.population = population;
	}
	public int getMutationnum() {
		return mutationnum;
	}
	public void setMutationnum(int mutationnum) {
		this.mutationnum = mutationnum;
	}
	public double getMutationrate() {
		return mutationrate;
	}
	public void setMutationrate(double mutationrate) {
		this.mutationrate = mutationrate;
	}
	public int getIterationnum() {
		return iterationnum;
	}
	public void setIterationnum(int iterationnum) {
		this.iterationnum = iterationnum;
	}
	public int getGeneration() {
		return generation;
	}
	public void setGeneration(int generation) {
		this.generation = generation;
	}
	public double getBestscore() {
		return bestscore;
	}
	public void setBestscore(double bestscore) {
		this.bestscore = bestscore;
	}
	public double getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(double totalscore) {
		this.totalscore = totalscore;
	}
	public double getAveragescore() {
		return averagescore;
	}
	public void setAveragescore(double averagescore) {
		this.averagescore = averagescore;
	}

}
