package it.cnr.iasi.saks.semrel.method.proximity;

import org.apache.jena.graph.Node;

import it.cnr.iasi.saks.semrel.method.SemanticRelatednessStrategy;

public interface Proximity_Interface extends SemanticRelatednessStrategy {
	public double predicateTypeWeight(Node p);
	public double lowestUpperBoundOfPredicatesTypesWeights();
}
