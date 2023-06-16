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
import org.junit.Assert;
import org.junit.Test;

import it.cnr.iasi.saks.semrel.RDFGraph_Endpoint;
import it.cnr.iasi.saks.semrel.method.SemanticRelatednessStrategy;
import it.cnr.iasi.saks.semrel.method.loddo.Lod_Jaccard;
import it.cnr.iasi.saks.semrel.method.loddo.Lod_Overlap;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;
import it.cnr.iasi.saks.semrel.KnowledgeBase;

/**
 * 
 * @author francesco
 *
 */
public class Loddo_Test {
	
	@Test
	public void semrel() {
		long start = System.currentTimeMillis();

		String graph = "http://dbpedia.org";
		String ns = graph+"/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);
		
		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie.");
		Node n2 = NodeFactory.createURI(ns+"Cornelia_Wallace");
		
		SemanticRelatednessStrategy lod_jaccard = new Lod_Jaccard(kb);  
		SemanticRelatednessStrategy lod_overlap = new Lod_Overlap(kb);

		SemanticRelatedness rel = new SemanticRelatedness();
		
		double semrel_lod_jaccard = rel.semrel(n1, n2, lod_jaccard);
		double semrel_lod_overlap = rel.semrel(n1, n2, lod_overlap);
		
		System.out.println("RESULT");
		System.out.println(n1.getURI().toString()+", "+n2.getURI().toString());
		System.out.println("lod_jaccard"+"\t\t"+"lod_overlap");
		System.out.println(semrel_lod_jaccard+"\t"+semrel_lod_overlap);
		
		boolean result = false;
		
		if((semrel_lod_jaccard == 0.625) &&
				(semrel_lod_overlap == 0.8333333333333334))
			result = true;
		
		Assert.assertTrue(result);
		
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		System.out.println(elapsedtime);		

	}
}
