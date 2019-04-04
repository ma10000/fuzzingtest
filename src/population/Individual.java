package population;

import java.util.ArrayList;
import java.util.Arrays;

public class Individual {
	private String gene;//��������
	private double score;//��Ӧ�ĺ����÷�
	private ArrayList<String> route;//�ò���������������·��
	public String getGene() {
		return gene;
	}
	public void setGene(String gene) {
		this.gene = gene;
	}
	public Individual() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public ArrayList<String> getRoute() {
		return route;
	}
	public void setRoute(ArrayList<String> route) {
		this.route = route;
	}
	
	
	public void mutation(int num) {
		int size = gene.length();
		StringBuffer sb = new StringBuffer(gene);
		for(int i=0;i<num;i++) {
			int at = ((int)(Math.random()*size))%size;
			if(sb.substring(at,at+1).equals("0")) {
				sb.replace(at,at+1,"1");
			}else {
				sb.replace(at,at+1,"0");
			}
		}
		gene = String.valueOf(sb);
	}
	@Override
	public String toString() {
		return "Individual [gene=" + gene + ", score=" + score + ", route=" + route + "]";
	}
 
}
