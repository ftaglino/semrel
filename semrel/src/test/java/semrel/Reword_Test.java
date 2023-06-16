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
import org.junit.Test;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.RDFGraph_Endpoint;
import it.cnr.iasi.saks.semrel.method.reword.Reword_Complete;
import it.cnr.iasi.saks.semrel.method.reword.Reword_Mip;
import it.cnr.iasi.saks.semrel.method.reword.Reword_Simple;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;
import it.cnr.iasi.saks.semrel.KnowledgeBase;

/**
 * 
 * @author francesco
 *
 */
public class Reword_Test {
	
	@Test
	public void reword_in() {
		System.out.println("reword_in");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		SemanticRelatedness sr = new SemanticRelatedness();
		
		long start = System.currentTimeMillis();
		Reword_Simple reword_simple = new Reword_Simple(kb);
		reword_simple.setDirection(Constants.IN);
		double semrel = sr.semrel(n1, n2, reword_simple);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
			
		System.out.println("RESULT");
		System.out.println("semrel_reword_in("+n1.getURI()+", "+n2.getURI()+")="+semrel);			
		System.out.println(elapsedtime);
	}
	
	@Test
	public void reword_out() {
		System.out.println("reword_out");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		SemanticRelatedness sr = new SemanticRelatedness();
		
		long start = System.currentTimeMillis();
		Reword_Simple reword_simple = new Reword_Simple(kb);
		reword_simple.setDirection(Constants.OUT);
		double semrel = sr.semrel(n1, n2, reword_simple);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;		
		
		System.out.println("RESULT");
		System.out.println("semrel_reword_out("+n1.getURI()+", "+n2.getURI()+")="+semrel);			
		System.out.println(elapsedtime);
	}
	
	
	@Test
	public void reword_in_out() {
		System.out.println("reword_in_out");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		SemanticRelatedness sr = new SemanticRelatedness();
		
		long start = System.currentTimeMillis();
		Reword_Simple reword_simple = new Reword_Simple(kb);
		reword_simple.setDirection(Constants.IN_OUT);
		double semrel = sr.semrel(n1, n2, reword_simple);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;		
		
		System.out.println("RESULT");
		System.out.println("semrel_reword_in_out("+n1.getURI()+", "+n2.getURI()+")="+semrel);			
		System.out.println(elapsedtime);
	}

	@Test
	public void reword_mip() {
		System.out.println("reword_mip");

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
		
		long start = System.currentTimeMillis();
		Reword_Mip reword_mip = new Reword_Mip(kb, minLength, maxLength, mode, acyclic);
		reword_mip.setMinLength(minLength);
		reword_mip.setMaxLength(maxLength);
		reword_mip.setMode(mode);
		reword_mip.setAcyclic(acyclic);
		double semrel = sr.semrel(n1, n2, reword_mip);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		
		System.out.println("RESULT");
		System.out.println("semrel_reword_mip("+n1.getURI()+", "+n2.getURI()+")="+semrel);			
		System.out.println(elapsedtime);
	}
	
	@Test
	public void reword() {
		System.out.println("reword");

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
		
		long start = System.currentTimeMillis();
		Reword_Complete reword_complete = new Reword_Complete(kb, minLength, maxLength, mode, acyclic);
		reword_complete.setMinLength(minLength);
		reword_complete.setMaxLength(maxLength);
		reword_complete.setMode(mode);
		reword_complete.setAcyclic(acyclic);
		double semrel = sr.semrel(n1, n2, reword_complete);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		
		System.out.println("RESULT");
		System.out.println("semrel_reword("+n1.getURI()+", "+n2.getURI()+")="+semrel);			
		System.out.println(elapsedtime);
	}
}
