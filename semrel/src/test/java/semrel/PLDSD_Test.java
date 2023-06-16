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

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.RDFGraph_Endpoint;
import it.cnr.iasi.saks.semrel.method.pldsd.PLDSD;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;
import it.cnr.iasi.saks.semrel.KnowledgeBase;

/**
 * 
 * @author francesco
 *
 */
public class PLDSD_Test {
		
	public void semrel(String t1, String t2) {
		System.out.println("pldsd");
		long start = System.currentTimeMillis();

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		PLDSD pldsd = new PLDSD(kb, 1, 2, Constants.DIRECTED_PATH, true);

		SemanticRelatedness sr = new SemanticRelatedness();
		double semrel = sr.semrel(n1, n2, pldsd);

		long end = System.currentTimeMillis();
		long elapsedtime = end - start;

		System.out.println("RESULT");
		System.out.println("semrel_pldsd("+n1.getURI()+", "+n2.getURI()+")="+semrel);			
		System.out.println(elapsedtime);	
		
	}
}
