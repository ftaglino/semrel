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
import it.cnr.iasi.saks.semrel.method.ic.IC_Comb;
import it.cnr.iasi.saks.semrel.method.ic.IC_Joint;
import it.cnr.iasi.saks.semrel.method.ic.IC_PMI;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;
import it.cnr.iasi.saks.semrel.KnowledgeBase;

/**
 * 
 * @author francesco
 *
 */
public class IC_Test {
	

	@Test
	public void semrel_joint_ic() {
		System.out.println("ic_joint");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		int minLength = 1;
		int maxLength = 2; 
		String mode = Constants.UNDIRECTED_PATH;
		boolean acyclic = true;
		
		SemanticRelatedness sr = new SemanticRelatedness();
		
		System.out.println("RESULT");
		
		long start = System.currentTimeMillis();
		IC_Joint ic_j = new IC_Joint(kb, minLength, maxLength, mode, acyclic);
		double semrel_ic_j = sr.semrel(n1, n2, ic_j);
		long end = System.currentTimeMillis();
		System.out.println("semrel_jointIC("+n1.getURI()+", "+n2.getURI()+")="+semrel_ic_j);
		long elapsedtime = end - start;
		System.out.println("elapsed time="+elapsedtime);		
	}

	@Ignore
	@Test
	public void semrel_comb_ic() {
		System.out.println("comb_ic");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		int minLength = 1;
		int maxLength = 2; 
		String mode = Constants.UNDIRECTED_PATH;
		boolean acyclic = true;
		
		SemanticRelatedness sr = new SemanticRelatedness();
		
		System.out.println("RESULT");
		
		long start = System.currentTimeMillis();
		IC_Comb ic_c = new IC_Comb(kb, minLength, maxLength, mode, acyclic);
		double semrel_ic_c = sr.semrel(n1, n2, ic_c);
		long end = System.currentTimeMillis();
		System.out.println("semrel_combIC("+n1.getURI()+", "+n2.getURI()+")="+semrel_ic_c);
		long elapsedtime = end - start;
		System.out.println("elapsed time="+elapsedtime);		
	}
	
	@Ignore
	@Test
	public void semrel_pmi() {
		System.out.println("ic_pmi");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		int minLength = 1;
		int maxLength = 2; 
		String mode = Constants.UNDIRECTED_PATH;
		boolean acyclic = true;
		
		SemanticRelatedness sr = new SemanticRelatedness();
		
		System.out.println("RESULT");
				
		long start = System.currentTimeMillis();
		IC_PMI pmi = new IC_PMI(kb, minLength, maxLength, mode, acyclic);
		double semrel_ic_pmi = sr.semrel(n1, n2, pmi);
		long end = System.currentTimeMillis();
		System.out.println("semrel_pmiIC("+n1.getURI()+", "+n2.getURI()+")="+semrel_ic_pmi);
		long elapsedtime = end - start;
		System.out.println("elapsed time="+elapsedtime);
						
		kb.clearCache();
	}
}
