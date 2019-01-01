package testing;

import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class EnsembleSommetTest
{
	private EnsembleSommet<Integer> ensembleSommetTest;
	private Sommet<Integer> s3;
	private Sommet<Integer> s4;
	private Sommet<Integer> s5;
	
	@Before
	public void setup()
	{
		ensembleSommetTest = new EnsembleSommet<Integer>();
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		s5=new Sommet<Integer>(5);
		ensembleSommetTest.ajouteSommet(s4);
		ensembleSommetTest.ajouteSommet(s3);
		
	}

	@Test
	public void testEnsembleSommet()
	{
		EnsembleSommet<Integer> ensembleSommet=new EnsembleSommet<Integer>(ensembleSommetTest);
		assertEquals("Le constructeur à argument de EnsembleSommet ne marche pas",ensembleSommetTest,ensembleSommet);
	}
	
	@Test
	public void testClone()
	{
		EnsembleSommet<Integer> ensembleSommet=ensembleSommetTest.clone();
		assertEquals("La méthode clone de EnsembleSommet ne marche pas",ensembleSommetTest,ensembleSommet);
		assertNotSame("Le clone pointe vers le même objet",ensembleSommetTest,ensembleSommet);
		ensembleSommetTest.ajouteSommet(s5);
		assertNotEquals("La méthode clone créé un objet lié à l'objet cloné",ensembleSommetTest,ensembleSommet);
	}
	
	@Test 
	public void testFirstSommet()
	{
		ensembleSommetTest.ajouteSommet(s5);
		assertEquals("La méthode FirstSommet ne marche pas ou ne renvoie pas l'objet de plus faible Hashcode",ensembleSommetTest.firstSommet(),s3);
	}
	
	@Test
	public void testExistSommet()
	{
		assertTrue("Test de l'existence d'un sommet present",ensembleSommetTest.existSommet(s3));
		assertFalse("Test de l'existence d'un sommet non present",ensembleSommetTest.existSommet(s5));
	}
	
	@Test
	public void testSupprSommet()
	{
		ensembleSommetTest.supprSommet(s4);
		assertFalse("Le sommet n'a pas été supprimé",ensembleSommetTest.existSommet(s4));
	}
}