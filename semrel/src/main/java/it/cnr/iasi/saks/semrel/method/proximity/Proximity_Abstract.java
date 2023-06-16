package it.cnr.iasi.saks.semrel.method.proximity;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.Filter;
import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.Path;
import it.cnr.iasi.saks.semrel.PathPattern;

public abstract class Proximity_Abstract implements Proximity_Interface {
	protected KnowledgeBase kb;
	protected int minLength = 0;
	protected int maxLength = 0;
	protected boolean acyclic;
	protected String mode = "";
	protected double lowestUpperBound = 0.0d;
	protected int graphDegree = 0;
	

	public KnowledgeBase getKb() {
		return kb;
	}

	public void setKb(KnowledgeBase kb) {
		this.kb = kb;
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

	public boolean isAcyclic() {
		return acyclic;
	}

	public void setAcyclic(boolean acyclic) {
		this.acyclic = acyclic;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setLowestUpperBound(double lowestUpperBound) {
		this.lowestUpperBound = lowestUpperBound;
	}

	public void setGraphDegree(int graphDegree) {
		this.graphDegree = graphDegree;
	}

	public double getLowestUpperBound() {
		return lowestUpperBound;
	}

	public void setLowestupperbound(double lowestUpperBound) {
		this.lowestUpperBound = lowestUpperBound;
	}

	public int getGraphDegree() {
		return graphDegree;
	}

	public void setMax_graph_degree(int graphDegree) {
		this.graphDegree = graphDegree;
	}
	
	/* Returns all the predicates types. Here, predicate type stands for a kind of predicate.
	 * That is, given all the occurences of predicates having the same URI, only one result is returned, that is the URI.
	 * Indeed, the distinct clause in the query is applied.
	 */
	public Vector<Node> predicateTypes() {
		Vector<Node> result = new Vector<Node>();
		PathPattern pattern = new PathPattern(null);
		Set<Filter> filters = new HashSet<Filter>(); 
		Node u1 = NodeFactory.createVariable("u1");
		filters.addAll(kb.instantiateFilters("u1", Constants.SUBJECT));
		Node p1 = NodeFactory.createVariable("p1");
		filters.addAll(kb.instantiateFilters("p1", Constants.PREDICATE));
		Node u2 = NodeFactory.createVariable("u2");
		filters.addAll(kb.instantiateFilters("u2", Constants.OBJECT));
		Triple t = new Triple(u1,p1,u2);
		pattern.setFilters(filters);
		pattern.getTriples().add(t);
		pattern.setDistinct(Constants.SPARQL_DISTINCT);
		pattern.getVarsToSelect().add("p1");
		
		result = this.getKb().nodesByPattern(pattern);
		return result;
	}

	public double nodeDegree(Node n) {
		double result = 0.0d;
		PathPattern pattern = new PathPattern(null);
		Set<Filter> filters = new HashSet<Filter>(); 
		Node u1 = n;
		Node p1 = NodeFactory.createVariable("p1");
		filters.addAll(kb.instantiateFilters("p1", Constants.PREDICATE));
		Node u2 = NodeFactory.createVariable("u2");
		filters.addAll(kb.instantiateFilters("u2", Constants.OBJECT));
		Triple t = new Triple(u1,p1,u2);
		pattern.setFilters(filters);
		pattern.getTriples().add(t);
		pattern.getVarsToSelect().add("p1");
		pattern.setDistinct(Constants.SPARQL_NOT_DISTINCT);
		
		int outgoingPredicates = this.getKb().countNodesByPattern(pattern);
		
		PathPattern pattern2 = new PathPattern(null);
		Set<Filter> filters2 = new HashSet<Filter>(); 
		u1 = NodeFactory.createVariable("u1");
		filters2.addAll(kb.instantiateFilters("u2", Constants.SUBJECT));
		p1 = NodeFactory.createVariable("p1");
		filters2.addAll(kb.instantiateFilters("p1", Constants.PREDICATE));
		u2 = n;
		Triple t2 = new Triple(u1,p1,u2);
		pattern2.setFilters(filters);
		pattern2.getTriples().add(t2);
		pattern.getVarsToSelect().add("p1");
		pattern2.setDistinct(Constants.SPARQL_NOT_DISTINCT);		

		int incomingPredicates = this.getKb().countNodesByPattern(pattern2);
		
		result = outgoingPredicates + incomingPredicates;
		return result;				
	}
	
	/*
	 * Returns the weight of a Path
	 */
	public double pathWeight(Path path) {
		double result = 0.0d;
		Vector<Triple> triples = path.getTriples();
		for(Triple t:triples) {
			double predWeight = this.predicateTypeWeight(t.getPredicate());
			result = result + predWeight;
		}
		return result;
	}

	/*
	 * Returns the sum of the weights of all the paths of length equals to pathLength
	 */
	public double pathsWeight(Vector<Path> paths, int pathLength) {
		double result = 0.0d;
		for(Path path:paths) {
			if(path.getTriples().size()==pathLength)
				result = result + this.pathWeight(path);
		}
		return result;
	}
	
	public double semrel(Node n1, Node n2) {
		double result = 0.0d;
		if (n1.getURI().equalsIgnoreCase(n2.getURI()))
			result = 1;
		else {
			Vector<Path> paths = this.getKb().paths(n1, n2, this.getMinLength(), this.getMaxLength(), this.getMode(), this.isAcyclic());
			for(int i=this.getMinLength(); i<=this.getMaxLength(); i++) {
				result = result + this.pathsWeight(paths, i)/(Math.pow(2, i)*i*Math.pow(this.getGraphDegree(),i));
			}
			result = result / this.getLowestUpperBound();	
		}		
		return result;
	}
}
