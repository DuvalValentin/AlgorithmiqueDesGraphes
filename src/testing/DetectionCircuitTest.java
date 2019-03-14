package testing;

import static org.junit.Assert.*;

import algorithme.DetectionCircuit;
import factory.Factory;
import graphElements.Interfaces.*;

import org.junit.Before;
import org.junit.Test;

public class DetectionCircuitTest
{
	private InterfaceSommet<Integer> s1,s2,s3,s4;
	
	private InterfaceArc<Integer> a12,a23,a32,a34;
	
	private InterfaceGrapheNonValue<Integer> Gcirc, Gline;
	
	@Before
	public void setUp()
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		
		a12=Factory.arcNonValue(s1,s2);
		a23=Factory.arcNonValue(s2,s3);
		a32=Factory.arcNonValue(s3,s2);
		a34=Factory.arcNonValue(s3,s4);
		
		Gline=Factory.grapheNonValue();
		Gline.ajouteSommet(s1);Gline.ajouteSommet(s2);Gline.ajouteSommet(s3);Gline.ajouteSommet(s4);
		Gline.ajouteArc(a12);Gline.ajouteArc(a23);Gline.ajouteArc(a34);
		Gcirc=Factory.grapheNonValue(Gline);
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
