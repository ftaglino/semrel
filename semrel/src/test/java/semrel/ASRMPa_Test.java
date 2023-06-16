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
import it.cnr.iasi.saks.semrel.method.asrmp.ASRMP_a;
import it.cnr.iasi.saks.semrel.method.asrmp.aggregation.TNorm_H_Lambda;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;
import it.cnr.iasi.saks.semrel.KnowledgeBase;

/**
 * 
 * @author francesco
 *
 */
public class ASRMPa_Test {
		
//	@Ignore
	@Test
	public void semrel() {
		System.out.println("asrmp_a");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		int length = 2;
		String mode = Constants.DIRECTED_PATH;
		boolean acyclic = true;
		
		TNorm_H_Lambda tnorm = new TNorm_H_Lambda(0);
		ASRMP_a asrmp_a = new ASRMP_a(kb, length, mode, acyclic, tnorm);

		SemanticRelatedness sr = new SemanticRelatedness();
		
		long start = System.currentTimeMillis();
		double semrel = sr.semrel(n1, n2, asrmp_a);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		
		System.out.println("RESULT");
		System.out.println("semrel_asrmp_a("+n1.getURI()+", "+n2.getURI()+")="+semrel);			
		System.out.println(elapsedtime);	
	}
	
}
