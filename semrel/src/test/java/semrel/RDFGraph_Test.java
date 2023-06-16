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

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.Filter;
import it.cnr.iasi.saks.semrel.RDFGraph_Endpoint;
import it.cnr.iasi.saks.semrel.Path;
import it.cnr.iasi.saks.semrel.PathPattern;

/**
 * 
 * @author francesco
 *
 */
public class RDFGraph_Test {

	@Ignore
	@Test
	public void nodesByPattern() {
		System.out.println("nodesByPattern");
		
		String graph = "http://graph_test.org";
		String ns = graph+"/";

		String distinct = Constants.SPARQL_NOT_DISTINCT;
		
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node s = NodeFactory.createURI(ns+"a1");
		Node o = NodeFactory.createVariable("u1");
		// Node p = NodeFactory.createURI(Constants.RDF_TYPE);
		Node p = NodeFactory.createVariable("p1");
		Triple t = new Triple(s,p,o);
		PathPattern pattern = new PathPattern(null);
		pattern.getTriples().add(t);
		pattern.setDistinct(distinct);
		pattern.getVarsToSelect().add("u1");
		Vector<Node> nodes = kb.nodesByPattern(pattern);
		for(Node n:nodes) {
			System.out.println(n.getURI());
		}
		
		Vector<Node> expectedResult = new Vector<Node>();
		//NOT DISTINCT
		if(distinct.equalsIgnoreCase(Constants.SPARQL_NOT_DISTINCT)) {
			expectedResult.add(NodeFactory.createURI(ns+"a2"));
			expectedResult.add(NodeFactory.createURI(ns+"a2"));
			expectedResult.add(NodeFactory.createURI(ns+"a4"));
			expectedResult.add(NodeFactory.createURI(ns+"a5"));
		}
		//DISTINCT
		else if(distinct.equalsIgnoreCase(Constants.SPARQL_DISTINCT)) {
			expectedResult.add(NodeFactory.createURI(ns+"a2"));
			expectedResult.add(NodeFactory.createURI(ns+"a4"));
			expectedResult.add(NodeFactory.createURI(ns+"a5"));
		}
				
		if(nodes.size()==expectedResult.size()) {
			nodes.removeAll(expectedResult);
			System.out.println(nodes);
			System.out.println(nodes.size());
			if(nodes.size()==0)
				Assert.assertTrue(true);
			else Assert.assertTrue(false);
		}
		else
			Assert.assertTrue(false);
	}
	
	
	@Ignore
	@Test
	public void predicateByPattern() {
		System.out.println("predicateByPattern");
		
		String graph = "http://graph_test.org";
		String ns = graph+"/";

		String distinct = Constants.SPARQL_DISTINCT;
		
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node s = NodeFactory.createURI(ns+"a1");
		Node o = NodeFactory.createVariable("u1");
		// Node p = NodeFactory.createURI(Constants.RDF_TYPE);
		Node p = NodeFactory.createVariable("p1");
		Triple t = new Triple(s,p,o);
		PathPattern pattern = new PathPattern(null);
		pattern.getTriples().add(t);
		pattern.setDistinct(distinct);
		pattern.getVarsToSelect().add("p1");
		Vector<Node> nodes = kb.nodesByPattern(pattern);
		for(Node n:nodes) {
			System.out.println(n.getURI());
		}
		
		Vector<Node> expectedResult = new Vector<Node>();
		//NOT DISTINCT
		if(distinct.equalsIgnoreCase(Constants.SPARQL_NOT_DISTINCT)) {
			expectedResult.add(NodeFactory.createURI(ns+"p1"));
			expectedResult.add(NodeFactory.createURI(ns+"p2"));
			expectedResult.add(NodeFactory.createURI(ns+"p2"));
			expectedResult.add(NodeFactory.createURI(ns+"p2"));
		}
		//DISTINCT
		else if(distinct.equalsIgnoreCase(Constants.SPARQL_DISTINCT)) {
			expectedResult.add(NodeFactory.createURI(ns+"p1"));
			expectedResult.add(NodeFactory.createURI(ns+"p2"));
		}
				
		if(nodes.size()==expectedResult.size()) {
			nodes.removeAll(expectedResult);
			System.out.println(nodes);
			System.out.println(nodes.size());
			if(nodes.size()==0)
				Assert.assertTrue(true);
			else Assert.assertTrue(false);
		}
		else
			Assert.assertTrue(false);
	}
	
	@Ignore
	@Test
	public void countNodesByPattern() {
		int result = 0;
		System.out.println("countNodesByPattern");
	
		String graph = "http://graph_test.org";
		String ns = graph+"/";
		String distinct = Constants.SPARQL_DISTINCT;
		
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		Node s = NodeFactory.createURI(ns+"a1");
		Node o = NodeFactory.createVariable("u1");
		// Node p = NodeFactory.createURI(Constants.RDF_TYPE);
		Node p = NodeFactory.createVariable("p1");
		Triple t = new Triple(s,p,o);
		PathPattern pattern = new PathPattern(null);
		pattern.getTriples().add(t);
		pattern.setDistinct(distinct);
		pattern.getVarsToSelect().add("u1");
		
		result = kb.countNodesByPattern(pattern);
		System.out.println(result);
		
		int expected_result = 0;
		if(distinct.equalsIgnoreCase(Constants.SPARQL_NOT_DISTINCT)) 
			expected_result = 4;
		else
			expected_result = 3;
		
		if(result-expected_result == 0)
			Assert.assertTrue(true);
		else 
			Assert.assertTrue(false);
	}
	
	@Ignore
	@Test
	public void countPathsByPattern() {
		System.out.println("countPathsByPattern");

		String graph = "http://graph_test.org";
		String ns = graph+"/";

		//RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(Constants.SPARQL_DBPEDIA_GRAPH, null, null);
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);
		
		Node s1 = NodeFactory.createURI(ns+"a1");
		Node p1 = NodeFactory.createVariable("p1");
		//Node p1 = NodeFactory.createURI(Constants.RDF_TYPE);
		Node o1 = NodeFactory.createVariable("u1");
		
		Node s2 = NodeFactory.createVariable("u1");
		//Node o2 = NodeFactory.createURI(Constants.OWL_CLASS);
		//Node p2 = NodeFactory.createURI(Constants.RDF_TYPE);
		Node o2 = NodeFactory.createVariable("u2");
		Node p2 = NodeFactory.createVariable("p2");
		
		PathPattern pattern = new PathPattern(null); 
		Triple t = new Triple(s1, p1, o1);
		pattern.getTriples().addElement(t);
		t = new Triple(s2, p2, o2);
		pattern.getTriples().addElement(t);
//		Filter f = new Filter();
//		f.setValue(Filter.varsMustBeDifferent("u2", "u3"));
//		pattern.getFilters().add(f);
		int n = kb.countPathsByPattern(pattern);
		
		System.out.println("n="+n);
		
		Assert.assertTrue(n>0);
	}
	
	@Ignore
	@Test
	public void countAllTriplesWithConstraints() {
		System.out.println("countAllTriples");

		String graph = "http://graph_test.org";
		
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);
		double n = kb.countAllTriples();
		System.out.println("n="+n);
		Assert.assertTrue(n>0);
	}

	@Ignore
	@Test
	public void countAllTriplesWithoutConstraints() {
		System.out.println("countAllTriples");

		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(Constants.SPARQL_DBPEDIA_GRAPH, null, null);
		double n = kb.countAllTriples();
		System.out.println("n="+n);
		Assert.assertTrue(n>0);
	}
	
	@Ignore
	@Test
	public void pathPatternEquality() {
		Node s1 = NodeFactory.createVariable("s");
		Node o1 = NodeFactory.createVariable("o");
		Node p1 = NodeFactory.createURI(Constants.RDF_TYPE);
		PathPattern pattern1 = new PathPattern(null); 
		Triple t1 = new Triple(s1, p1, o1);
		Filter f1 = new Filter();
		f1.setValue(Filter.generateFilterIn_nodesInDBO("a"));
		pattern1.getTriples().add(t1);
		pattern1.getFilters().add(f1);
		pattern1.setDistinct(Constants.SPARQL_DISTINCT);
		pattern1.getVarsToSelect().add("s");
		
		Node s2 = NodeFactory.createVariable("s");
		Node o2 = NodeFactory.createVariable("o");
		Node p2 = NodeFactory.createURI(Constants.RDF_TYPE);
		PathPattern pattern2 = new PathPattern(null); 
		Triple t2 = new Triple(s2, p2, o2);
		Filter f2 = new Filter();
		f2.setValue(Filter.generateFilterIn_nodesInDBO("a"));
		pattern2.getTriples().add(t2);
		pattern2.getFilters().add(f2);
		pattern2.setDistinct(Constants.SPARQL_DISTINCT);
		pattern2.getVarsToSelect().add("s");
		
		Assert.assertTrue(pattern1.equals(pattern2));
	}
	
	@Ignore
	@Test
	public void pathsByPattern() {
		String graph = "http://graph_test.org";
		String ns = graph+"/";

		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		
		Node s1 = NodeFactory.createURI(ns+"a1");
		Node p1 = NodeFactory.createVariable("p1");
		//Node p1 = NodeFactory.createURI(Constants.RDF_TYPE);
		Node o1 = NodeFactory.createVariable("u1");
		
		Node s2 = NodeFactory.createVariable("u1");
		//Node o2 = NodeFactory.createURI(Constants.OWL_CLASS);
		//Node p2 = NodeFactory.createURI(Constants.RDF_TYPE);
		Node o2 = NodeFactory.createVariable("u2");
		Node p2 = NodeFactory.createVariable("p2");
		
		PathPattern pattern = new PathPattern(null); 
		Triple t = new Triple(s1, p1, o1);
		pattern.getTriples().addElement(t);
		t = new Triple(s2, p2, o2);
		pattern.getTriples().addElement(t);
		
		Vector<Path> result = kb.pathsByPattern(pattern, Constants.ACYCLIC);
		for(int i=0; i<result.size(); i++)
			System.out.println("result("+i+1+")="+result.get(i).toString());
		
		Assert.assertTrue(true);

	}
	
//	@Ignore
	@Test
	public void paths() {
		
//		String graph = "http://graph_test.org";
//		String ns = graph+"/";

		
		String graph = "http://dbpedia.org";
		String ns = graph+"/resource/";

		
		//RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(Constants.SPARQL_DBPEDIA_GRAPH, null, null);
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);
		
		//Node n1 = NodeFactory.createURI(ns+"a1");
//		Node n2 = NodeFactory.createURI(ns+"a3");
		
		Node n1 = NodeFactory.createURI(ns+"Boy");
		Node n2 = NodeFactory.createURI(ns+"Dough");
		
		//System.out.println(n2.getName());
		int minLength = 1;
		int maxLength = 2;
		String mode = Constants.UNDIRECTED_PATH;
		Vector<Path> result = kb.paths(n1, n2, minLength, maxLength, mode, Constants.ACYCLIC);
		System.out.println("RESULT");
		for(int i=0; i<result.size(); i++) {
			String r = result.get(i).toString();
			if(!(r.contains("wikiPageWikiLink")))
				System.out.println("result("+i+")="+r);
		}
		
/*		for(int i=0; i<result.size(); i++) {
			if(result.get(i).toString().contains("wikiPageWikiLink"));
			else
				System.out.println("result("+i+")="+result.get(i).toString());
		}
*/
		Assert.assertTrue(true);
	}
	
	@Ignore
	@Test
	public void paths_2() {
		
//		String graph = "http://graph_test.org";
//		String ns = graph+"/";

		
		String graph = "http://dbpedia.org";
		String ns = graph+"/resource/";

		
		//RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(Constants.SPARQL_DBPEDIA_GRAPH, null, null);
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);
		
		//Node n1 = NodeFactory.createURI(ns+"a1");
//		Node n2 = NodeFactory.createURI(ns+"a3");
		
		Node n1 = NodeFactory.createURI(ns+"Boy");
		Node n2 = NodeFactory.createURI(ns+"Pizza");
		
		//System.out.println(n2.getName());
		int minLength = 1;
		int maxLength = 2;
		String mode = Constants.UNDIRECTED_PATH;
		Vector<Path> result = kb.paths_2(n1, n2, minLength, maxLength, mode, Constants.ACYCLIC);
		System.out.println("RESULT");
		for(int i=0; i<result.size(); i++)
			System.out.println("result("+i+")="+result.get(i).toString());
		
		Assert.assertTrue(true);
	}
	
	@Ignore
	@Test
	public void paths_3() {
		
//		String graph = "http://graph_test.org";
//		String ns = graph+"/";

		
		String graph = "http://dbpedia.org";
		String ns = graph+"/resource/";

		
		//RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(Constants.SPARQL_DBPEDIA_GRAPH, null, null);
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);
		
		//Node n1 = NodeFactory.createURI(ns+"a1");
//		Node n2 = NodeFactory.createURI(ns+"a3");
		
		Node n1 = NodeFactory.createURI(ns+"Europe");
		Node n2 = NodeFactory.createURI(ns+"Russia");
		
		//System.out.println(n2.getName());
		int minLength = 1;
		int maxLength = 3;
		String mode = Constants.UNDIRECTED_PATH;
		Vector<Path> result = kb.paths(n1, n2, minLength, maxLength, mode, Constants.ACYCLIC);
		System.out.println("RESULT");
		for(int i=0; i<result.size(); i++)
			System.out.println("result("+i+")="+result.get(i).toString());
		
		Assert.assertTrue(true);
	}
	
	@Ignore
	@Test
	public void nodeFreq() {
		String graph = "http://graph_test.org";
		String ns = graph+"/";
		
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);
		PathPattern pattern = new PathPattern(null);
		Node s = NodeFactory.createURI(ns+"a1");

		// create predicate variable and instantiate predicate's filters 
		Node p = NodeFactory.createVariable("p1");
		Set<Filter> p_filters = kb.instantiateFilters("p1", Constants.PREDICATE);
		// create predicate variable and instantiate object's filters 
		Node o = NodeFactory.createVariable("u1");
		Set<Filter> o_filters = kb.instantiateFilters("u1", Constants.OBJECT);

		
		Triple t = new Triple(s,p,o);
						
		pattern.getTriples().add(t);
		pattern.getFilters().addAll(p_filters);
		pattern.getFilters().addAll(o_filters);
		
		System.out.println("isValid="+pattern.isValid());
				
		int freq = kb. countPathsByPattern(pattern);
		System.out.println("freq="+freq);
		
		Assert.assertTrue(freq>0);
	}
	
	@Ignore
	@Test
	public void updateCache() {
		String graph = "http://graph_test.org";
		String ns = graph+"/";
		
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);

		{
		PathPattern pattern = new PathPattern(null);
		Node s1 = NodeFactory.createVariable("u1");
		Set<Filter> filters = new HashSet<Filter>();
		filters.addAll(kb.instantiateFilters("u1", Constants.SUBJECT));
		Node p1 = NodeFactory.createURI(ns+"p1");
		Node o1 = NodeFactory.createURI(ns+"a2");
		Triple t = new Triple(s1,p1,o1);
		pattern.setFilters(filters);
		pattern.getTriples().add(t);
		
		kb.getCache().update(pattern, 2);
		}
		
		kb.getCache().printCache();
		
		PathPattern pattern2 = new PathPattern(null);
		Node s2 = NodeFactory.createVariable("u1");
		Set<Filter> filters2 = new HashSet<Filter>();
		filters2.addAll(kb.instantiateFilters("u1", Constants.SUBJECT));
		Node p2 = NodeFactory.createURI(ns+"p1");
		Node o2 = NodeFactory.createURI(ns+"a2");
		Triple t2 = new Triple(s2,p2,o2);
		pattern2.setFilters(filters2);
		pattern2.getTriples().add(t2);
		
		System.out.println(pattern2.toString());
		
		int x = kb.getCache().getNumPathsByPattern(pattern2);
		
		System.out.println("x="+x);
		
	} 
	
	@Ignore
	@Test
	public void neighbours() {
//		String graph = "http://graph_test.org";
//		String ns = graph+"/";
		
		String graph = "http://dbpedia.org";
		String ns = graph+"/resource/";

		
		RDFGraph_Endpoint kb = RDFGraph_Endpoint.getInstance(graph, null, null);

//		Node n = NodeFactory.createURI(ns+"a1");
		Node n = NodeFactory.createURI(ns+"Steve_Jobs");
		
		Set<Node> neighbours= new HashSet<Node>();
		int distance = 2;
		for(int i=1; i<=distance; i++)
			neighbours.addAll(kb.neighbours(n, i, Constants.METHOD_LDSD_OUTGOING));
		
		System.out.println(neighbours.size());
	}
}
