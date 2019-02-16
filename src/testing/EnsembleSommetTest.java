package testing;

import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;

import org.junit.Before;
import static org.junit.Assert.*;

import java.util.HashSet;

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
	public void testConstructeurClone()
	{
		EnsembleSommet<Integer> ensembleSommetClone=new EnsembleSommet<Integer>(ensembleSommetTest);
		assertEquals("Le constructeur clone",ensembleSommetTest,ensembleSommetClone);
		assertNotSame("Le constructeur clone créé un objet identique",ensembleSommetTest,ensembleSommetClone);
		ensembleSommetClone.ajouteSommet(s5);
		assertNotEquals("Les ensembles sont liés",ensembleSommetTest,ensembleSommetClone);
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
		assertTrue("Test de l'existence d'un sommet present",ensembleSommetTest.existeSommet(s3));
		assertFalse("Test de l'existence d'un sommet non present",ensembleSommetTest.existeSommet(s5));
	}
	
	@Test
	public void testSupprSommet()
	{
		ensembleSommetTest.supprSommet(s4);
		assertFalse("Le sommet n'a pas été supprimé",ensembleSommetTest.existeSommet(s4));
	}
	
	@Test
	public void testEncapsulation()
	{
		s3.setId(9);
		assertFalse("Modification d'un sommet depuis l'exterieur",ensembleSommetTest.existeSommet(s3));
	}
	
	@Test
	public void testGetEnsemble()
	{
		HashSet<Sommet<Integer>> ensemble = new HashSet<Sommet<Integer>>();
		ensemble.add(s3); ensemble.add(s4);
		assertEquals(ensemble,ensembleSommetTest.getEnsemble());
	}
	
	@Test
	public void testUnion()
	{
		EnsembleSommet<Integer> ajout = new EnsembleSommet<Integer>();
		ajout.ajouteSommet(s3);ajout.ajouteSommet(s5);
		EnsembleSommet<Integer> union = new EnsembleSommet<Integer>();
		union.ajouteSommet(s3); union.ajouteSommet(s4); union.ajouteSommet(s5);
		EnsembleSommet<Integer> resultatUnion = EnsembleSommet.union(ajout, ensembleSommetTest);
		assertEquals(union,resultatUnion);
		EnsembleSommet<Integer> vide = new EnsembleSommet<Integer>();
		EnsembleSommet<Integer> resultatVide = EnsembleSommet.union(vide, ensembleSommetTest);
		assertEquals(ensembleSommetTest,resultatVide);
	}
	
	@Test
	public void testIntersection()
	{
		EnsembleSommet<Integer> ajout = new EnsembleSommet<Integer>();
		ajout.ajouteSommet(s3);ajout.ajouteSommet(s5);
		EnsembleSommet<Integer> intersection = new EnsembleSommet<Integer>();
		intersection.ajouteSommet(s3);
		EnsembleSommet<Integer> resultatIntersection = EnsembleSommet.intersection(ajout, ensembleSommetTest);
		assertEquals(intersection,resultatIntersection);
		EnsembleSommet<Integer> vide = new EnsembleSommet<Integer>();
		EnsembleSommet<Integer> resultatVide = EnsembleSommet.intersection(vide, ensembleSommetTest);
		assertEquals(vide,resultatVide);
	}
}