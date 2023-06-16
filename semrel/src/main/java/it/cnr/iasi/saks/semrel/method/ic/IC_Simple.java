package it.cnr.iasi.saks.semrel.method.ic;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.PathPattern;

public class IC_Simple extends IC_Abstract {
	
	public IC_Simple(KnowledgeBase kb, int minLength, int maxLength, String mode, boolean acyclic) {
		super(kb, minLength, maxLength, mode, acyclic);
	}
		
	public double likelihood_predicate(Node p) {
		double result = 0.0d;
		result = ((double)this.getKb().countTriplesWithPredicate(p)) /  ((double)this.getKb().countAllTriples());
//		System.out.println("predicateWeight("+p+")="+result);
		return result;
	}
	
	public double tripleWeight(Triple t) {
		double result = 0.0d;
		result = ic(this.likelihood_predicate(t.getPredicate()));
		return result;
	}
	
	public double maxWeight() {
		double result = 0.0d;
		result = ic(1.0d / ((double)this.getKb().countAllTriples()));
//		System.out.println("maw weight = "+result);
		return result;
	}
}
