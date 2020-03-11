package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import factory.Factory;
import graphelements.interfaces.ArcValue;
import graphelements.interfaces.EnsembleArcValue;
import graphelements.interfaces.Sommet;

public class EnsembleArcValuesTest
{
	private EnsembleArcValue<Integer> ensembleArcValueTest, ensembleEmpty;
	private Sommet<Integer> s1, s2;
	private ArcValue<Integer> av115, av113, av121, av217;
	private Float c1, c3, c5, c7;

	@BeforeEach
	public void setUp()
	{
		ensembleArcValueTest=Factory.ensembleArcValue();
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		c1=1f;
		c3=3f;
		c5=5f;
		c7=7f;
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
		EnsembleArcValue<Integer> ensembleArcValueClone=Factory.ensembleArcValue(ensembleArcValueTest);
		assertEquals(ensembleArcValueTest,ensembleArcValueClone,"Le constructeur clone");
		assertNotSame(ensembleArcValueTest,ensembleArcValueClone,"Le constructeur clone créé un objet identique");
		ensembleArcValueClone.setValeur(s1,s1,3f);
		assertNotEquals(ensembleArcValueTest,ensembleArcValueClone,"Modifier le clone modifie l'original");
	}
	@Test
	public void testGetEnsemble()
	{
		HashSet<ArcValue<Integer>> ensemble=new HashSet<ArcValue<Integer>>();
		ensemble.add(av115);
		ensemble.add(av121);
		ensemble.add(av217);
		assertEquals(ensemble,ensembleArcValueTest.getEnsemble());
	}
	@Test
	public void testajouteElement()
	{
		ensembleArcValueTest.ajouteElement(av113);
		assertEquals(av113.getCout(),ensembleArcValueTest.getCout(s1,s1).get(),"Coût de l'arc plus faible mais n'a pas remplacé le précédent");
		ensembleArcValueTest.ajouteElement(s1,s1,5f);
		assertEquals(3f,ensembleArcValueTest.getCout(s1,s1).get(),"Cout de l'arc plus fort mais a quand même remplacé le précedent");
	}
	@Test
	public void testsupprElement()
	{
		ensembleArcValueTest.supprElement(s1,s1);
		assertFalse(ensembleArcValueTest.existeArc(s1,s1));
		assertFalse(ensembleArcValueTest.existeArc(s2,s2));
		ensembleArcValueTest.supprElement(s2,s2);
	}
	@Test
	public void testGetCout()
	{
		assertEquals(av121.getCout(),ensembleArcValueTest.getCout(s1,s2).get(),"getCout ne marche pas");
		assertTrue(ensembleArcValueTest.getCout(s2,s2).isEmpty());
	}
	@Test
	public void testSetValeur()
	{
		ensembleArcValueTest.setValeur(s1,s2,7f);
		assertEquals(7f,ensembleArcValueTest.getCout(s1,s2).get(),"setValeur ne marche pas");
		ensembleEmpty.setValeur(s1,s2,7f);// on vérifie que ça ne plante pas
	}
	@Test
	public void testUnion()
	{
		EnsembleArcValue<Integer> ajout=Factory.ensembleArcValue(ensembleArcValueTest);
		ajout.ajouteElement(s2,s2,5f);
		EnsembleArcValue<Integer> copie=Factory.ensembleArcValue(ensembleArcValueTest);
		EnsembleArcValue<Integer> unionVide=EnsembleArcValue.union(ensembleArcValueTest,ensembleEmpty);
		assertEquals(copie,unionVide);
		copie.ajouteElement(s2,s2,5f);
		EnsembleArcValue<Integer> union=EnsembleArcValue.union(ensembleArcValueTest,ajout);
		assertEquals(copie,union);
	}
	@Test
	public void testIntersection()
	{
		EnsembleArcValue<Integer> copie=Factory.ensembleArcValue(ensembleArcValueTest);
		copie.supprElement(av115);
		copie.supprElement(av121);
		copie.ajouteElement(s2,s2,5f);
		EnsembleArcValue<Integer> intersection=EnsembleArcValue.intersection(ensembleArcValueTest,copie);
		copie.supprElement(s2,s2);
		assertEquals(copie,intersection);
		EnsembleArcValue<Integer> intersectionVide=EnsembleArcValue.intersection(ensembleArcValueTest,ensembleEmpty);
		assertEquals(ensembleEmpty,intersectionVide);
	}
	@Test
	public void encapsulation()
	{
		c1=5f;
		assertTrue(ensembleArcValueTest.existeArc(av121),"Modification d'un cout exterieur");
		s2.setId(9);
		assertTrue(ensembleArcValueTest.existeArc(av217),"Modification d'un sommet exterieur");
		av115.setArrivee(s2);
		assertFalse(ensembleArcValueTest.existeArc(av115),"Modification d'un arc exterieur");
	}
}