package it.cnr.iasi.saks.semrel.method.ic;

import java.util.Vector;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.Path;
import it.cnr.iasi.saks.semrel.method.SemanticRelatednessStrategy;

public abstract class IC_Abstract implements SemanticRelatednessStrategy {
	protected KnowledgeBase kb = null;
	protected double maxWeight = 0.0d;
	protected int minLength = 1;
	protected int maxLength = 2;
	protected String mode = Constants.UNDIRECTED_PATH;
	protected boolean acyclic = true;
	
	public IC_Abstract(KnowledgeBase kb, int minLength, int maxLength, String mode, boolean acyclic) {
		super();
		this.kb = kb;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.mode = mode;
		this.acyclic = acyclic;
		this.maxWeight = maxWeight();
	}
	public KnowledgeBase getKb() {
		return kb;
	}
	public void setKb(KnowledgeBase kb) {
		this.kb = kb;
	}
	public double getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(double max_weight) {
		this.maxWeight = max_weight;
	}
	public int getMinLength() {
		return minLength;
	}
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public boolean isAcyclic() {
		return acyclic;
	}
	public void setAcyclic(boolean acyclic) {
		this.acyclic = acyclic;
	}
	
	public double ic(double likelihood) {
		double result = 0.0d;
		result = - Math.log(likelihood);
		return result;
	}
	
	public double tripleCost(Triple t) {
		double result = 0.0d;
		result =  this.getMaxWeight() - tripleWeight(t);
		return result;
	}
	
	public double path_cost(Path path) {
		double result = 0.0d;
		for(Triple t:path.getTriples()) {
			result = result + this.tripleCost(t);
		}
		return result;
	}
	
	public double semrel(Node n1, Node n2) {
		double result = 0.0d;
		if(n1.getURI().toString().equals(n2.getURI().toString()))
			result = 1;
		else {
			double dist = this.maxWeight() * this.getMaxLength();
			Vector<Path> paths = this.getKb().paths(n1, n2, this.getMinLength(), this.getMaxLength(), this.getMode(), this.isAcyclic());
			if(paths.size() > 0) {
				Path bestPath = new Path();
				for(Path p:paths) {
					double p_cost = path_cost(p);
					if(p_cost<dist) {
						dist = p_cost;
						bestPath = p;
					}
				}
				result = 1.0d/(1+dist);
			}
		}
		return result;
	}
	
	public double maxWeight() {
		double result = 0.0d;
		this.maxWeight();
		return result;
	}
	
	public double tripleWeight(Triple t) {
		double result = 0.0d;
		this.tripleWeight(t);
		return result;
	}
}
