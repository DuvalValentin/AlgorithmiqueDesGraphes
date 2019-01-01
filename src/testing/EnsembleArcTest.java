package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleArc;
import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Sommet;
//Comprends les test de AbstractEnsembleArc
public class EnsembleArcTest
{

	private EnsembleArc<Integer> ensembleArcTest;
	
	private Sommet<Integer> s1;
	private Sommet<Integer> s2;
	private Sommet<Integer> s3;
	
	private Arc<Integer> a11;
	private Arc<Integer> a12;
	private Arc<Integer> a21;
	private Arc<Integer> a22;
	private Arc<Integer> a23;
	
	@Before
	public void setup()
	{
		ensembleArcTest = new EnsembleArc<Integer>();
		s1 = new Sommet<Integer>(1);
		s2 = new Sommet<Integer>(2);
		s3 = new Sommet<Integer>(3);
		a11 = new Arc<Integer>(s1,s1);
		a12 = new Arc<Integer>(s1,s2);
		a21 = new Arc<Integer>(s2,s1);
		a22 = new Arc<Integer>(s2,s2);
		a23 = new Arc<Integer>(s2,s3);
	}

	@Test
	public void testEnsembleArc()
	{
		EnsembleArc<Integer> ensembleArc=new EnsembleArc<Integer>(ensembleArcTest);
		assertEquals("Le constructeur à argument de EnsembleArc ne marche pas",ensembleArcTest,ensembleArc);
	}
	
	@Test
	public void testClone()
	{
		EnsembleArc<Integer> clone1 = ensembleArcTest.clone();
		assertEquals("La méthode clone de EnsembleArc ne marche pas",ensembleArcTest,clone1);
		clone1.add(new Arc<Integer>(new Sommet<Integer>(1),new Sommet<Integer>(2)));
		assertNotEquals("La méthode clone de EnsembleArc ne créé pas un nouvel objet",ensembleArcTest,clone1);
	}
	
	@Test 
	public void testListSuccPred()
	{
		ensembleArcTest.ajouteArc(a11);ensembleArcTest.ajouteArc(a12);ensembleArcTest.ajouteArc(a21);ensembleArcTest.ajouteArc(a22);ensembleArcTest.ajouteArc(a23);
		EnsembleSommet<Integer> succ1 = new EnsembleSommet<Integer>();
		succ1.ajouteSommet(s2);succ1.ajouteSommet(s1);
		EnsembleSommet<Integer> pred3 = new EnsembleSommet<Integer>();
		pred3.ajouteSommet(s2);
		assertEquals("la méthode listSucc de EnsembleArc ne marche pas",ensembleArcTest.listSucc(s1),succ1);
		assertEquals("la méthode listPred de EnsembleArc ne marche pas",ensembleArcTest.listPred(s3),pred3);	
	}
	
	@Test
	public void testAjouteSupprExistArc()
	{
		ensembleArcTest.ajouteArc(s1,s2);ensembleArcTest.ajouteArc(s2,s2);ensembleArcTest.ajouteArc(s2,s3);
		EnsembleArc<Integer> normalAjout = new EnsembleArc<Integer>();
		assertTrue("Les arcs s'ajoutent bien",ensembleArcTest.existeArc(s1,s2));
		assertTrue("Les arcs s'ajoutent bien",ensembleArcTest.existeArc(s2,s2));
		assertTrue("Les arcs s'ajoutent bien",ensembleArcTest.existeArc(s2,s3));
		assertTrue("Test de présence de boucle lorsqu'elle existe",ensembleArcTest.existeBoucle());
		normalAjout.ajouteArc(a12);normalAjout.ajouteArc(a22);normalAjout.ajouteArc(a23);
		assertTrue("Les arcs s'ajoutent bien",normalAjout.existeArc(a12));
		assertTrue("Les arcs s'ajoutent bien",normalAjout.existeArc(a22));
		assertTrue("Les arcs s'ajoutent bien",normalAjout.existeArc(a23));
		assertEquals("Les deux ajouteArc ont un effet différent",normalAjout,ensembleArcTest);
		ensembleArcTest.supprArc(a12);ensembleArcTest.supprArc(s2, s2);
		assertFalse("Arc supposé être enlevé",ensembleArcTest.existeArc(a22));
		assertFalse("Arc supposé être enlevé",ensembleArcTest.existeArc(s1,s2));
		assertFalse("Test de présence de boucle lorsqu'elle n'existe pas",ensembleArcTest.existeBoucle());
	}
}
