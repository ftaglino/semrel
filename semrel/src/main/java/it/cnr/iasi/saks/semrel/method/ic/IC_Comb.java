package it.cnr.iasi.saks.semrel.method.ic;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;

import it.cnr.iasi.saks.semrel.KnowledgeBase;

public class IC_Comb extends IC_Simple {
	
	public IC_Comb(KnowledgeBase kb, int minLength, int maxLength, String mode, boolean acyclic) {
		super(kb, minLength, maxLength, mode, acyclic);
	}
	
	public double likelihood_object(Node o) {
		double result = 0.0d;
		result = ((double)this.getKb().countTriplesWithObject(o)) /  ((double)this.getKb().countAllTriples());
		return result;
	}
	
	public double tripleWeight(Triple t) {
		double result = 0.0d;
		result = ic(likelihood_predicate(t.getPredicate())) + ic(likelihood_object(t.getObject()));
		return result;
	}
	
	/**
	 * In the case of the IC_Comb w_joint(t) = w(p)+w(o|p) = w_unweighted(p) + w(o)
	 * max{w_joint(p)} = max{w_unweighted(p)} + max{w(o)}
	 * un upper bound for max{w(o)} is when there is only one triple with o 
	 * Assuming that, max{w(o)} = 1/N, where N is the number of triples.
	 * Then we assume as max{w_joint(p)} = 2*1/N = 2*max{w_unweighted(p)}
	 */
	@Override
	public double maxWeight() {
		double result = 0.0d;
		result = 2*super.maxWeight();		
//		System.out.println("maw weight = "+result);
		return result;
	}
	
}
