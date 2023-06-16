package it.cnr.iasi.saks.semrel.method.ic;

import org.apache.jena.graph.Triple;

import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.method.SemanticRelatednessStrategy;

public interface IC_Interface extends SemanticRelatednessStrategy {
	public KnowledgeBase getKb();
	public int getMinLength();
	public int getMaxLength();
	public String getMode();
	public boolean isAcyclic();
	public double tripleWeight(Triple t);
	public double tripleCost(Triple t);
	public double maxWeight();
}
