package testing;

import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceSommet;

import org.junit.Before;
import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import factory.Factory;

public class EnsembleSommetTest
{
	private InterfaceEnsembleSommet<Integer> ensembleSommetTest;
	private InterfaceSommet<Integer> s3,s4,s5;
	
	@Before
	public void setup()
	{
		ensembleSommetTest = Factory.ensembleSommet();
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		s5=Factory.sommet(5);
		ensembleSommetTest.ajouteElement(s4);
		ensembleSommetTest.ajouteElement(s3);
	}

	@Test
	public void testConstructeurClone()
	{
		InterfaceEnsembleSommet<Integer> ensembleSommetClone=Factory.ensembleSommet(ensembleSommetTest);
		assertEquals("Le constructeur clone",ensembleSommetTest,ensembleSommetClone);
		assertNotSame("Le constructeur clone créé un objet identique",ensembleSommetTest,ensembleSommetClone);
		ensembleSommetClone.ajouteElement(s5);
		assertNotEquals("Les ensembles sont liés",ensembleSommetTest,ensembleSommetClone);
	}
	
	@Test 
	public void testFirstSommet()
	{
		ensembleSommetTest.ajouteElement(s5);
		assertEquals("La méthode FirstSommet ne marche pas ou ne renvoie pas l'objet de plus faible Hashcode",ensembleSommetTest.pickSommet(),s3);
	}
	
	@Test
	public void testExistSommet()
	{
		assertTrue("Test de l'existence d'un sommet present",ensembleSommetTest.existeSommet(s3));
		assertFalse("Test de l'existence d'un sommet non present",ensembleSommetTest.existeSommet(s5));
	}
	
	@Test
	public void testAjouteSommet()
	{
		ensembleSommetTest.ajouteSommet(2);
		InterfaceSommet<Integer> s2 = Factory.sommet(2);
		assertTrue("Le sommet a bien été rajouté",ensembleSommetTest.existeSommet(s2));
	}
	
	@Test
	public void testSupprSommet()
	{
		ensembleSommetTest.supprElement(s4);
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
		HashSet<InterfaceSommet<Integer>> ensemble = new HashSet<InterfaceSommet<Integer>>();
		ensemble.add(s3); ensemble.add(s4);
		assertEquals(ensemble,ensembleSommetTest.getEnsemble());
	}
	
	@Test
	public void testUnion()
	{
		InterfaceEnsembleSommet<Integer> ajout = Factory.ensembleSommet();
		ajout.ajouteElement(s3);ajout.ajouteElement(s5);
		InterfaceEnsembleSommet<Integer> union = Factory.ensembleSommet();
		union.ajouteElement(s3); union.ajouteElement(s4); union.ajouteElement(s5);
		InterfaceEnsembleSommet<Integer> resultatUnion = InterfaceEnsembleSommet.union(ajout, ensembleSommetTest);
		assertEquals(union,resultatUnion);
		InterfaceEnsembleSommet<Integer> vide = Factory.ensembleSommet();
		InterfaceEnsembleSommet<Integer> resultatVide = InterfaceEnsembleSommet.union(vide, ensembleSommetTest);
		assertEquals(ensembleSommetTest,resultatVide);
	}
	
	@Test
	public void testIntersection()
	{
		InterfaceEnsembleSommet<Integer> ajout = Factory.ensembleSommet();
		ajout.ajouteElement(s3);ajout.ajouteElement(s5);
		InterfaceEnsembleSommet<Integer> intersection = Factory.ensembleSommet();
		intersection.ajouteElement(s3);
		InterfaceEnsembleSommet<Integer> resultatIntersection = InterfaceEnsembleSommet.intersection(ajout, ensembleSommetTest);
		assertEquals(intersection,resultatIntersection);
		InterfaceEnsembleSommet<Integer> vide = Factory.ensembleSommet();
		InterfaceEnsembleSommet<Integer> resultatVide = InterfaceEnsembleSommet.intersection(vide, ensembleSommetTest);
		assertEquals(vide,resultatVide);
	}
	
	@Test
	public void testContient()
	{
		InterfaceEnsembleSommet<Integer> inclut = Factory.ensembleSommet();
		InterfaceEnsembleSommet<Integer> nonInclut = Factory.ensembleSommet();
		inclut.ajouteElement(s4);
		nonInclut.ajouteElement(s5);
		nonInclut.ajouteElement(s3);
		assertTrue(ensembleSommetTest.contient(inclut));
		assertFalse(ensembleSommetTest.contient(nonInclut));
	}
}