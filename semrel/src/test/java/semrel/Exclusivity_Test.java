/*
 * 	 This file is part of SemRel, originally promoted and
 *	 developed at CNR-IASI. For more information visit:
 *	 http://saks.iasi.cnr.it/tools/semrel
 *	     
 *	 This is free software: you can redistribute it and/or modify
 *	 it under the terms of the GNU General Public License as 
 *	 published by the Free Software Foundation, either version 3 of the 
 *	 License, or (at your option) any later version.
 *	 
 *	 This software is distributed in the hope that it will be useful,
 *	 but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	 GNU General Public License for more details.
 * 
 *	 You should have received a copy of the GNU General Public License
 *	 along with this source.  If not, see <http://www.gnu.org/licenses/>.
 */
package semrel;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.junit.Ignore;
import org.junit.Test;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.RDFGraph_Endpoint;
import it.cnr.iasi.saks.semrel.method.exclusivity.Exclusivity;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;
import it.cnr.iasi.saks.semrel.KnowledgeBase;

/**
 * 
 * @author francesco
 *
 */
public class Exclusivity_Test {
	
	@Ignore
	@Test
	public void semrel() {
		System.out.println("Exclusivity");
		long start = System.currentTimeMillis();

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		int minLength = 1;
		int maxLength = 2; 
		String mode = Constants.UNDIRECTED_PATH;
		int k = 5;
		boolean acyclic = Constants.ACYCLIC;
		SemanticRelatedness sr = new SemanticRelatedness();
		
		double alpha = 0.25;
		Exclusivity excl_025 = new Exclusivity(kb, minLength, maxLength, mode, acyclic, k, alpha);
		double semrel_025 = sr.semrel(n1, n2, excl_025);
		
		alpha = 0.50;		
		Exclusivity excl_050 = new Exclusivity(kb, minLength, maxLength, mode, acyclic, k, alpha);
		double semrel_050 = sr.semrel(n1, n2, excl_050);

		alpha = 0.75;		
		Exclusivity excl_075 = new Exclusivity(kb, minLength, maxLength, mode, acyclic, k, alpha);
		double semrel_075 = sr.semrel(n1, n2, excl_075);

		
		System.out.println("RESULT");
		System.out.println(n1.getURI().toString()+", "+n2.getURI().toString());
		System.out.println("exclusivity_025\t\t\texclusivity_050\t\t\texclusivity_075");
		System.out.println(semrel_025+"\t\t"+semrel_050+"\t\t"+semrel_075+"\t\t");
		

		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		System.out.println(elapsedtime);

		
	}
	
}
