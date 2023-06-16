package semrel;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.junit.Test;

import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.RDFGraph_Endpoint;
import it.cnr.iasi.saks.semrel.method.wlm.WLM;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;

public class WLM_Test {
		
//	@Ignore
	@Test
	public void semrel() {
		System.out.println("wlm");
		long start = System.currentTimeMillis();

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");
 
		WLM wlm = new WLM(kb);
		
		SemanticRelatedness sr = new SemanticRelatedness();

		double semrel = sr.semrel(n1, n2, wlm);
		
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		
		System.out.println("RESULT");
		System.out.println("semrel_wlm("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		System.out.println(elapsedtime);		
	}
}
