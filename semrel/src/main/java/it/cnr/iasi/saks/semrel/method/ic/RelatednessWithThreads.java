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
package it.cnr.iasi.saks.semrel.method.ic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;

import breeze.linalg.maxLowPrio;
import it.cnr.iasi.saks.semrel.Constants;
import it.cnr.iasi.saks.semrel.KnowledgeBase;
import it.cnr.iasi.saks.semrel.Path;
import it.cnr.iasi.saks.semrel.PathPattern;
import it.cnr.iasi.saks.semrel.method.ic.IC_Abstract;
import it.cnr.iasi.saks.semrel.method.ic.IC_Interface;

/**
 * The implementation of this semantic relatedness method refers to the following publication
 * Schuhumacher M., Ponzetto S.P. 
 * Knowledge-based Graph Document Modeling
 * WSDM'14 February 24-28, 2014, New York, New York, USA.
 *  
 * @author francesco
 *
 */
public class RelatednessWithThreads {

	public class Task_PathCost implements Callable<Double> {
		Path path; 
		IC_Interface method;
		
		public Task_PathCost(Path path, IC_Interface method) {
			this.path = path;
			this.method = method;
		}
		
		public Double call() {
			double result = 0.0d;
			result = path_cost(path, method);
			return result;
		}
	}
	
	public static double path_cost(Path path, IC_Interface method) {
		double result = 0.0d;
		for(Triple t:path.getTriples()) {
			result = result + method.tripleCost(t);
		}
		return result;
	}
	
	public double semrel(Node n1, Node n2, IC_Interface method) {
		double result = 0.0d;
		
		ExecutorService executor = Executors.newFixedThreadPool(6);
		List<Future<Double>> futures = new ArrayList<>();
		
		if(n1.getURI().toString().equals(n2.getURI().toString()))
			result = 1;
		else {
			double dist = method.maxWeight() * method.getMaxLength();
			Vector<Path> paths = method.getKb().paths(n1, n2, method.getMinLength(), method.getMaxLength(), method.getMode(), method.isAcyclic());
			for(Path p:paths) {
				// Parallelization
				Future<Double> future = executor.submit(new Task_PathCost(p, method));
				futures.add(future);
			}
			
			try {
//				System.out.println("dist="+dist);
				for(Future<Double> f:futures) {
					double p_cost = ((Double)f.get());
					if(p_cost<dist)
						dist = p_cost;
//					System.out.println("dist="+dist);
				}
			} catch (InterruptedException e) {
					e.printStackTrace();
			} catch (ExecutionException e) {
					e.printStackTrace();
			} finally {executor.shutdown();}
			
						
			result = 1.0d/(1+dist);
		}
		return result;
	}	

}
