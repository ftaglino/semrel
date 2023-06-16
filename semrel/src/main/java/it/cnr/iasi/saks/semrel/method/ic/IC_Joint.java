package it.cnr.iasi.saks.semrel.method.ic;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.PathPattern;

public class IC_Joint extends IC_Simple {
	
	public IC_Joint(KnowledgeBase kb, int minLength, int maxLength, String mode, boolean acyclic) {
		super(kb, minLength, maxLength, mode, acyclic);
	}
		
	/**
	 * Computes the conditional probability Pr(p|n1)
	 * @param p
	 * @param n1
	 * @return
	 */
	public double likelihood_conditional(Node o, Node p) {
		double result = 0;
		
		double likelihood_joint = likelihood_joint(o, p);
		double likelihood_pred = likelihood_predicate(p);
		result = likelihood_joint / likelihood_pred; 
		
		return result;
	}
	
	public double likelihood_joint(Node o, Node p) {
		double result = 0.0d;

		Node nx = NodeFactory.createVariable("nx");
		PathPattern pattern = new PathPattern(null);

		Triple t = new Triple(nx, p, o);
		pattern.getTriples().add(t);
		result = ((double)this.getKb().countPathsByPattern(pattern)) / ((double)this.getKb().countAllTriples());
		return result;
	}
	
	public double tripleWeight(Triple t) {
		double result = 0.0d;
		result = super.tripleWeight(t) + 
				ic(this.likelihood_conditional(t.getObject(), t.getPredicate()));
		return result;
	}
		
	/**
	 * In the case of the IC_Joint w_joint(t) = w(p)+w(o|p) = w_unweighted(p) + w(o|p)
	 * max{w_joint(p)} = max{w_unweighted(p)} + max{w(o|p)}
	 * un upper bound for max{w(o|p)} is when there is only one triple with o and all the triples have p
	 * Assuming that, max{w(o|p)} = 1/N, where N is the number of triples.
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
