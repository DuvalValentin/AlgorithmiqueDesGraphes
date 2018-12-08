package testing;

import graphElements.EnsembleSommet;
import graphElements.Sommet;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class EnsembleSommetTest
{
	private EnsembleSommet<Integer> ensembleSommetTest;
	@Before
	public void setup()
	{
		ensembleSommetTest = new EnsembleSommet<Integer>();
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
		ensembleSommetTest.add(new Sommet<Integer>(3));;
		assertNotEquals("La méthode clone créé un objet lié à l'objet cloné",ensembleSommetTest,ensembleSommet);
	}
	
	@Test 
	public void testFirstSommet()
	{
		ensembleSommetTest.add(new Sommet<Integer>(3));
		ensembleSommetTest.add(new Sommet<Integer>(5));
		ensembleSommetTest.add(new Sommet<Integer>(4));
		assertEquals("La méthode FirstSommet ne marche pas ou ne renvoie pas l'objet de plus faible Hashcode",ensembleSommetTest.firstSommet(),new Sommet<Integer>(3));
	}

}
