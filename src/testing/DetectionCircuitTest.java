package testing;

import static org.junit.Assert.*;

import algorithme.DetectionCircuit;
import graphElements.Elements.*;

import org.junit.Before;
import org.junit.Test;

public class DetectionCircuitTest
{
	private Sommet<Integer> s1;
	private Sommet<Integer> s2;
	private Sommet<Integer> s3;
	private Sommet<Integer> s4;
	
	private Arc<Integer> a12;
	private Arc<Integer> a23;
	private Arc<Integer> a32;
	private Arc<Integer> a34;
	
	private Graphe<Integer> Gcirc;
	private Graphe<Integer> Gline;
	
	@Before
	public void setUp()
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		
		a12=new Arc<Integer>(s1,s2);
		a23=new Arc<Integer>(s2,s3);
		a32=new Arc<Integer>(s3,s2);
		a34=new Arc<Integer>(s3,s4);
		
		Gline=new Graphe<Integer>();
		Gline.ajouteSommet(s1);Gline.ajouteSommet(s2);Gline.ajouteSommet(s3);Gline.ajouteSommet(s4);
		Gline.ajouteArc(a12);Gline.ajouteArc(a23);Gline.ajouteArc(a34);
		Gcirc=Gline.clone();
		Gcirc.ajouteArc(a32);
	}

	@Test
	public void testRoy_Warshall()
	{
		assertTrue("Roy-Warshall sur un graphe avec circuit",DetectionCircuit.Roy_Warshall(Gcirc));
		assertFalse("Roy-Warshall sur un graphe sans circuit",DetectionCircuit.Roy_Warshall(Gline));
	}

	@Test
	public void testMarimontEntree()
	{
		assertTrue("Marimont par point d'entree sur un graphe avec circuit",DetectionCircuit.MarimontEntree(Gcirc));
		assertFalse("Marimont par point d'entree sur un graphe sans circuit",DetectionCircuit.MarimontEntree(Gline));
	}
	
	@Test
	public void testMarimontSortie()
	{
		assertTrue("Marimont par point de sortie sur un graphe avec circuit",DetectionCircuit.MarimontSortie(Gcirc));
		assertFalse("Marimont par point de sortie sur un graphe sans circuit",DetectionCircuit.MarimontSortie(Gline));
	}
}
