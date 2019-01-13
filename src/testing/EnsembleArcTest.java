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
		
		ensembleArcTest.ajouteArc(a11);ensembleArcTest.ajouteArc(a12);ensembleArcTest.ajouteArc(a21);
		ensembleArcTest.ajouteArc(a22);ensembleArcTest.ajouteArc(a23);
	}

	@Test
	public void testEnsembleArc()
	{
		EnsembleArc<Integer> ensembleArcClone=new EnsembleArc<Integer>(ensembleArcTest);
		assertEquals("Le constructeur clone",ensembleArcTest,ensembleArcClone);
		assertNotSame("Le constructeur clone créé un objet identique",ensembleArcTest,ensembleArcClone);
		ensembleArcClone.supprArc(a22);
		assertNotEquals("Modifier le clone modifie l'original",ensembleArcTest,ensembleArcClone);
	}
	
	@Test 
	public void testListSuccPred()
	{
		EnsembleSommet<Integer> succ1 = new EnsembleSommet<Integer>();
		succ1.ajouteSommet(s2);succ1.ajouteSommet(s1);
		EnsembleSommet<Integer> pred3 = new EnsembleSommet<Integer>();
		pred3.ajouteSommet(s2);
		assertEquals("listSucc",succ1,ensembleArcTest.listSucc(s1));
		assertEquals("listPred",pred3,ensembleArcTest.listPred(s3));	
	}
	
	@Test
	public void testAjouteSupprExist()
	{
		EnsembleArc<Integer> sommetAjout = new EnsembleArc<Integer>();
		assertTrue("Les arcs s'ajoutent bien",ensembleArcTest.existeArc(s1,s2));
		assertTrue("Test de présence de boucle lorsqu'elle existe",ensembleArcTest.existeBoucle());
		assertTrue("Test de présence de boucle avec un sommet en paramètre lorsqu'elle existe",ensembleArcTest.existeBoucle(s2));
		assertFalse("Test de présence de boucle avec un sommet en paramètre lorsqu'elle n'existe pas",ensembleArcTest.existeBoucle(s3));
		sommetAjout.ajouteArc(s1,s1);sommetAjout.ajouteArc(s1,s2);sommetAjout.ajouteArc(s2,s1);sommetAjout.ajouteArc(s2,s2);sommetAjout.ajouteArc(s2,s3);
		assertTrue("Les arcs s'ajoutent bien",sommetAjout.existeArc(a12));
		assertTrue("Les arcs s'ajoutent bien",sommetAjout.existeArc(a22));
		assertTrue("Les arcs s'ajoutent bien",sommetAjout.existeArc(a23));
		assertEquals("Les deux ajouteArc ont un effet différent",sommetAjout,ensembleArcTest);
		ensembleArcTest.supprArc(a11);ensembleArcTest.supprArc(s2, s2);
		assertFalse("Arc supposé être enlevé",ensembleArcTest.existeArc(a22));
		assertFalse("Arc supposé être enlevé",ensembleArcTest.existeArc(s1,s1));
		assertFalse("Test de présence de boucle lorsqu'elle n'existe pas",ensembleArcTest.existeBoucle());
	}
	
	@Test 
	public void testEncapsulation()
	{
		s3.setId(9);
		assertFalse("Modification d'un sommet depuis l'exterieur",ensembleArcTest.existeArc(s2,s3));
		assertTrue("Modification d'un sommet depuis l'exterieur",ensembleArcTest.existeArc(a23));
		a12.setArrivee(s3);
		assertFalse("Modification d'un arc depuis l'exterieur",ensembleArcTest.existeArc(a12));
	}
}
