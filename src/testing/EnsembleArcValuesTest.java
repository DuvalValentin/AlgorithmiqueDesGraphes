package testing;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import factory.Factory;
import graphElements.Interfaces.InterfaceArcValue;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceEnsembleArcValue;
import graphElements.Interfaces.InterfaceSommet;

public class EnsembleArcValuesTest
{
	private InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>> ensembleArcValueTest, ensembleEmpty;
	
	private InterfaceSommet<Integer> s1, s2;
	
	private InterfaceArcValue<Integer> av115, av113, av121, av217;
	
	private InterfaceCout c1, c3, c5, c7;
	
	@Before
	public void setUp()
	{
		ensembleArcValueTest = Factory.ensembleArcValue();
		s1 = Factory.sommet(1);
		s2 = Factory.sommet(2);
		
		c1 = Factory.cout(1);
		c3 = Factory.cout(3);
		c5 = Factory.cout(5);
		c7 = Factory.cout(7);
		
		av115=Factory.arcValue(s1,s1,c5);
		av113=Factory.arcValue(s1,s1,c3);
		av121=Factory.arcValue(s1,s2,c1);
		av217=Factory.arcValue(s2,s1,c7);
		ensembleArcValueTest.ajouteElement(av115);
		ensembleArcValueTest.ajouteElement(av121);
		ensembleArcValueTest.ajouteElement(av217);
		ensembleEmpty=Factory.ensembleArcValue();
		
	}

	@Test
	public void testEnsembleArcValues()
	{
		InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>>ensembleArcValueClone=Factory.ensembleArcValue(ensembleArcValueTest);
		assertEquals("Le constructeur clone",ensembleArcValueTest,ensembleArcValueClone);
		assertNotSame("Le constructeur clone créé un objet identique",ensembleArcValueTest,ensembleArcValueClone);
		ensembleArcValueClone.setValeur(s1, s1, c3);
		assertNotEquals("Modifier le clone modifie l'original",ensembleArcValueTest,ensembleArcValueClone);
	}
	
	@Test
	public void testGetEnsemble()
	{
		HashSet<InterfaceArcValue<Integer>> ensemble = new HashSet<InterfaceArcValue<Integer>>();
		ensemble.add(av115);ensemble.add(av121);ensemble.add(av217);
		assertEquals(ensemble,ensembleArcValueTest.getEnsemble());
	}
	
	@Test
	public void testajouteElement()
	{
		ensembleArcValueTest.ajouteElement(av113);
		assertEquals("Coût de l'arc plus faible mais n'a pas remplacé le précédent",av113.getCout(),ensembleArcValueTest.getCout(s1, s1).get());
		ensembleArcValueTest.ajouteElement(s1,s1,c5);
		assertEquals("Cout de l'arc plus fort mais a quand même remplacé le précedent",c3,ensembleArcValueTest.getCout(s1, s1).get());
	}
	
	@Test
	public void testsupprElement()
	{
		ensembleArcValueTest.supprElement(s1,s1);
		assertFalse(ensembleArcValueTest.existeArc(s1,s1));
		assertFalse(ensembleArcValueTest.existeArc(s2,s2));
		ensembleArcValueTest.supprElement(s2, s2);
	}

	@Test
	public void testGetCout()
	{
		assertEquals("getCout ne marche pas ",av121.getCout(),ensembleArcValueTest.getCout(s1, s2).get());
		assertTrue(ensembleArcValueTest.getCout(s2, s2).isEmpty());
	}
	
	@Test
	public void testSetValeur()
	{
		ensembleArcValueTest.setValeur(s1, s2, c7);
		assertEquals("setValeur ne marche pas",c7,ensembleArcValueTest.getCout(s1,s2).get());
		ensembleEmpty.setValeur(s1, s2, c7);//on vérifie que ça ne plante pas
	}
	
	@Test
	public void testUnion()
	{
		InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>> ajout = Factory.ensembleArcValue(ensembleArcValueTest);
		ajout.ajouteElement(s2, s2,c5);
		InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>> copie = Factory.ensembleArcValue(ensembleArcValueTest);
		InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>> unionVide = InterfaceEnsembleArcValue.union(ensembleArcValueTest, ensembleEmpty);
		assertEquals(copie,unionVide);
		copie.ajouteElement(s2,s2,c5);
		InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>> union =InterfaceEnsembleArcValue.union(ensembleArcValueTest, ajout);
		assertEquals(copie,union);
	}
	
	@Test
	public void testIntersection()
	{
		InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>> copie = Factory.ensembleArcValue(ensembleArcValueTest);
		copie.supprElement(av115);copie.supprElement(av121);copie.ajouteElement(s2, s2,c5);
		InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>> intersection  =InterfaceEnsembleArcValue.intersection(ensembleArcValueTest,copie);
		copie.supprElement(s2,s2);
		assertEquals(copie,intersection);
		InterfaceEnsembleArcValue<Integer,InterfaceArcValue<Integer>> intersectionVide =InterfaceEnsembleArcValue.intersection(ensembleArcValueTest, ensembleEmpty);
		assertEquals(ensembleEmpty,intersectionVide);
	}
	
	@Test
	public void encapsulation()
	{
		c1.setValeur(5);
		assertTrue("Modification d'un cout exterieur",ensembleArcValueTest.existeArc(av121));
		s2.setId(9);
		assertTrue("Modification d'un sommet exterieur",ensembleArcValueTest.existeArc(av217));
		av115.setArrivee(s2);
		assertFalse("Modification d'un arc exterieur",ensembleArcValueTest.existeArc(av115));
	}
}