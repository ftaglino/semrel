package semrel;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.RDFGraph_Endpoint;
import it.cnr.iasi.saks.semrel.method.ldsd.LDSD_combined_weighted;
import it.cnr.iasi.saks.semrel.method.ldsd.LDSD_direct;
import it.cnr.iasi.saks.semrel.method.ldsd.LDSD_direct_weighted;
import it.cnr.iasi.saks.semrel.method.ldsd.LDSD_indirect;
import it.cnr.iasi.saks.semrel.method.ldsd.LDSD_indirect_weighted;
import it.cnr.iasi.saks.semrel.relatedness.SemanticRelatedness;

public class LDSD_Test {

	@Ignore	
	@Test
	public void ldsd_direct() {
		System.out.println("ldsd_direct");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");
		
		LDSD_direct method = new LDSD_direct(kb);
		
		SemanticRelatedness sr = new SemanticRelatedness();

		long start = System.currentTimeMillis();
		double semrel = sr.semrel(n1, n2, method);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;

		System.out.println("RESULT");
		System.out.println("semrel("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		System.out.println(elapsedtime);	
	}
	
	@Test
	public void ldsd_direct_weighted() {
		System.out.println("ldsd_direct_weighted");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");
		
		LDSD_direct_weighted method = new LDSD_direct_weighted(kb);
		
		SemanticRelatedness sr = new SemanticRelatedness();

		long start = System.currentTimeMillis();
		double semrel = sr.semrel(n1, n2, method);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;

		System.out.println("RESULT");
		System.out.println("semrel("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		System.out.println(elapsedtime);	
	}

		
	@Ignore	
	@Test
	public void ldsd_indirect() {
		System.out.println("ldsd_indirect");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");
		
		LDSD_indirect method = new LDSD_indirect(kb);
		
		SemanticRelatedness sr = new SemanticRelatedness();

		long start = System.currentTimeMillis();
		double semrel = sr.semrel(n1, n2, method);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;

		System.out.println("RESULT");
		System.out.println("semrel("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		System.out.println(elapsedtime);	
	}
	
	
	@Test
	public void ldsd_indirect_weighted() {
		System.out.println("ldsd_indirect_weighted");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");
		
		LDSD_indirect_weighted method = new LDSD_indirect_weighted(kb);
		
		SemanticRelatedness sr = new SemanticRelatedness();

		long start = System.currentTimeMillis();
		double semrel = sr.semrel(n1, n2, method);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;

		System.out.println("RESULT");
		System.out.println("semrel("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		System.out.println(elapsedtime);	
	}
	
	@Test
	public void ldsd_combined_weighted() {
		System.out.println("ldsd_combined_weighted");

		String graph = "http://dbpedia.org";
		String ns = graph + "/resource/";
		
		KnowledgeBase kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node n1 = NodeFactory.createURI(ns+"Angelina_Jolie");
		Node n2 = NodeFactory.createURI(ns+"Brad_Pitt");
		
		LDSD_combined_weighted method = new LDSD_combined_weighted(kb);
		
		SemanticRelatedness sr = new SemanticRelatedness();

		long start = System.currentTimeMillis();
		double semrel = sr.semrel(n1, n2, method);
		long end = System.currentTimeMillis();
		long elapsedtime = end - start;

		System.out.println("RESULT");
		System.out.println("semrel("+n1.getURI()+", "+n2.getURI()+")="+semrel);
		System.out.println(elapsedtime);	
	}
}
