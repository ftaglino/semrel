package it.cnr.iasi.saks.semrel.method.proximity;

import java.util.Vector;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.Path;
import it.cnr.iasi.saks.semrel.method.ic.IC_Abstract;
import it.cnr.iasi.saks.semrel.method.ic.IC_Simple;

public class Proximity_IC_Simple extends Proximity_Abstract {
	protected static Proximity_IC_Simple instance = null;
	IC_Abstract method = null;

	/*
	 * Since to calculate the degree of the entire semantic network (i.e., DBPedia) will need too much time, or
	 * it will be even impossible due to the reduced computational resources,
	 * I fixed the degree of the graph, and the value is given by the constant @Constants.METHOD_PROXIMITY_GRAPH_DEGREE
	 */
	private Proximity_IC_Simple (KnowledgeBase kb, int minLength, int maxLength, String mode, boolean acyclic, IC_Simple method) {
		this.setKb(kb);
		this.setMinLength(minLength);
		this.setMaxLength(maxLength);
		this.setMode(mode);
		this.setAcyclic(acyclic);
		this.setMax_graph_degree(Constants.METHOD_PROXIMITY_GRAPH_DEGREE);
		this.setMethod(method);
	}

	public synchronized static Proximity_IC_Simple getInstance(KnowledgeBase kb, int minLength, int maxLength, String mode, boolean acyclic, IC_Simple method){
    	if (instance == null){
    		instance = new Proximity_IC_Simple(kb, minLength, maxLength, mode, acyclic, method);
        	instance.setLowestupperbound(instance.lowestUpperBoundOfPredicatesTypesWeights());
    	}
    	

    	return instance;
    }
	
	public IC_Abstract getMethod() {
		return method;
	}

	public void setMethod(IC_Simple method) {
		this.method = method;
	}

	/*
	 * Computes the Omega(G), that is the lowest upper bound value of weights for all predicates types 
	 */
	public double lowestUpperBoundOfPredicatesTypesWeights() {
		double result = 0.0d;
/*		Vector<Node> predicateTypes = predicateTypes();
		for(Node n:predicateTypes) {
			double weight = predicateTypeWeight(n);
			if(weight > result)
				result = weight;
		}
*/
		result = this.getMethod().ic(1.0d/((double)this.getKb().countAllTriples()));
		return result;
	}
	
	/*
	 * Computes the weight of a predicate.
	 * Natively, the method does not specify any method to calculate the weight of a predicate
	 */
	public double predicateTypeWeight(Node p) {
		double result = 0.0d;
		// the triple is built to call the tripleWeight method, even if s and o are never used.
		Node s = NodeFactory.createVariable("s");
		Node o = NodeFactory.createVariable("o");
		Triple t = new Triple(s, p, o);
		result = ((IC_Simple)(this.getMethod())).tripleWeight(t);
		
		return result;		
	}
}
