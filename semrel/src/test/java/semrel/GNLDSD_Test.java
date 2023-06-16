package semrel;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.junit.Ignore;
import org.junit.Test;


import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.RDFGraph_Endpoint;
import it.cnr.iasi.saks.semrel.method.gnldsd.GNLDSD_alpha;
import it.cnr.iasi.saks.semrel.method.gnldsd.GNLDSD_beta;
import it.cnr.iasi.saks.semrel.method.gnldsd.GNLDSD_gamma;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;

public class GNLDSD_Test {

	
	@Ignore	
	@Test
	public void gnldsd_alpha() {
		System.out.println("gnldsd_alpha");
		long start = System.currentTimeMillis();

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		GNLDSD_alpha method = new GNLDSD_alpha(kb);
		
		SemanticRelatedness sr = new SemanticRelatedness();
		
		double semrel = sr.semrel(n1, n2, method);
		
		System.out.println("semrel_gnldsd_alpha("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		System.out.println(elapsedtime);	

	}
	
	@Ignore	
	@Test
	public void gnldsd_beta() {
		System.out.println("gnldsd_beta");
		long start = System.currentTimeMillis();

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		GNLDSD_beta method = new GNLDSD_beta(kb);
		
		SemanticRelatedness sr = new SemanticRelatedness();

		double semrel = sr.semrel(n1, n2, method);
		
		System.out.println("semrel_gnldsd_beta("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		System.out.println(elapsedtime);	

	}
	
//	@Ignore	
	@Test
	public void gnldsd_gamma() {
		System.out.println("gnldsd_gamma");
		long start = System.currentTimeMillis();

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");

		GNLDSD_gamma method = new GNLDSD_gamma(kb);
				
		SemanticRelatedness sr = new SemanticRelatedness();
		
		double semrel = sr.semrel(n1, n2, method);
		
		System.out.println("semrel_gnldsd_gamma("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;
		System.out.println(elapsedtime);	
	}
}
