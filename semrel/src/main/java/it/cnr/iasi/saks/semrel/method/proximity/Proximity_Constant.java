package it.cnr.iasi.saks.semrel.method.proximity;

import java.util.Vector;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.Path;
import it.cnr.iasi.saks.semrel.method.ic.IC_Simple;

public class Proximity_Constant extends Proximity_Abstract {
	protected static Proximity_Constant instance = null;
	private final double PREDICATE_WEIGHT = 1.0d; 
	
	/*
	 * Since to calculate the degree of the entire semantic network (i.e., DBPedia) will need too much time, or
	 * it will be even impossible due to the reduced computational resources,
	 * I fixed the degree of the graph, and the value is given by the constant @Constants.METHOD_PROXIMITY_GRAPH_DEGREE
	 */
	private Proximity_Constant (KnowledgeBase kb, int minLength, int maxLength, String mode, boolean acyclic) {
		this.setKb(kb);
		this.setMinLength(minLength);
		this.setMaxLength(maxLength);
		this.setMode(mode);
		this.setAcyclic(acyclic);
		this.setMax_graph_degree(Constants.METHOD_PROXIMITY_GRAPH_DEGREE);
		this.setLowestupperbound(this.PREDICATE_WEIGHT);
	}

	public synchronized static Proximity_Constant getInstance(KnowledgeBase kb, int minLength, int maxLength, String mode, boolean acyclic){
    	if (instance == null){
    		instance = new Proximity_Constant(kb, minLength, maxLength, mode, acyclic);
    	}
    	return instance;
    }
	
	/*
	 * Return a constant value
	 */
	public double predicateTypeWeight(Node p) {
		return this.PREDICATE_WEIGHT;
	}
	
	public double lowestUpperBoundOfPredicatesTypesWeights() {
		return PREDICATE_WEIGHT;
	}
	
}
