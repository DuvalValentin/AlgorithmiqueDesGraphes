package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import factory.Factory;
import graphelements.interfaces.InterfaceArcValue;
import graphelements.interfaces.InterfaceCout;
import graphelements.interfaces.InterfaceEnsembleArcValue;
import graphelements.interfaces.InterfaceSommet;

public class EnsembleArcValuesTest
{
	private InterfaceEnsembleArcValue<Integer> ensembleArcValueTest, ensembleEmpty;
	
	private InterfaceSommet<Integer> s1, s2;
	
	private InterfaceArcValue<Integer> av115, av113, av121, av217;
	
	private InterfaceCout c1, c3, c5, c7;
	
	@BeforeEach
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
		InterfaceEnsembleArcValue<Integer>ensembleArcValueClone=Factory.ensembleArcValue(ensembleArcValueTest);
		assertEquals(ensembleArcValueTest,ensembleArcValueClone,"Le constructeur clone");
		assertNotSame(ensembleArcValueTest,ensembleArcValueClone,"Le constructeur clone créé un objet identique");
		ensembleArcValueClone.setValeur(s1, s1, c3);
		assertNotEquals(ensembleArcValueTest,ensembleArcValueClone,"Modifier le clone modifie l'original");
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
		assertEquals(av113.getCout(),ensembleArcValueTest.getCout(s1, s1).get(),"Coût de l'arc plus faible mais n'a pas remplacé le précédent");
		ensembleArcValueTest.ajouteElement(s1,s1,c5);
		assertEquals(c3,ensembleArcValueTest.getCout(s1, s1).get(),"Cout de l'arc plus fort mais a quand même remplacé le précedent");
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
		assertEquals(av121.getCout(),ensembleArcValueTest.getCout(s1, s2).get(),"getCout ne marche pas");
		assertTrue(ensembleArcValueTest.getCout(s2, s2).isEmpty());
	}
	
	@Test
	public void testSetValeur()
	{
		ensembleArcValueTest.setValeur(s1, s2, c7);
		assertEquals(c7,ensembleArcValueTest.getCout(s1,s2).get(),"setValeur ne marche pas");
		ensembleEmpty.setValeur(s1, s2, c7);//on vérifie que ça ne plante pas
	}
	
	@Test
	public void testUnion()
	{
		InterfaceEnsembleArcValue<Integer> ajout = Factory.ensembleArcValue(ensembleArcValueTest);
		ajout.ajouteElement(s2, s2,c5);
		InterfaceEnsembleArcValue<Integer> copie = Factory.ensembleArcValue(ensembleArcValueTest);
		InterfaceEnsembleArcValue<Integer> unionVide = InterfaceEnsembleArcValue.union(ensembleArcValueTest, ensembleEmpty);
		assertEquals(copie,unionVide);
		copie.ajouteElement(s2,s2,c5);
		InterfaceEnsembleArcValue<Integer> union =InterfaceEnsembleArcValue.union(ensembleArcValueTest, ajout);
		assertEquals(copie,union);
	}
	
	@Test
	public void testIntersection()
	{
		InterfaceEnsembleArcValue<Integer> copie = Factory.ensembleArcValue(ensembleArcValueTest);
		copie.supprElement(av115);copie.supprElement(av121);copie.ajouteElement(s2, s2,c5);
		InterfaceEnsembleArcValue<Integer> intersection  =InterfaceEnsembleArcValue.intersection(ensembleArcValueTest,copie);
		copie.supprElement(s2,s2);
		assertEquals(copie,intersection);
		InterfaceEnsembleArcValue<Integer> intersectionVide =InterfaceEnsembleArcValue.intersection(ensembleArcValueTest, ensembleEmpty);
		assertEquals(ensembleEmpty,intersectionVide);
	}
	
	@Test
	public void encapsulation()
	{
		c1.setValeur(5);
		assertTrue(ensembleArcValueTest.existeArc(av121),"Modification d'un cout exterieur");
		s2.setId(9);
		assertTrue(ensembleArcValueTest.existeArc(av217),"Modification d'un sommet exterieur");
		av115.setArrivee(s2);
		assertFalse(ensembleArcValueTest.existeArc(av115),"Modification d'un arc exterieur");
	}
}