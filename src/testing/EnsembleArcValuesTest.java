package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import graphElements.Elements.ArcValue;
import graphElements.Elements.Cout;
import graphElements.Elements.EnsembleArcValue;
import graphElements.Elements.Sommet;

public class EnsembleArcValuesTest
{
	private EnsembleArcValue<Integer> ensembleArcValueTest;
	
	private Sommet<Integer> s1, s2;
	
	private ArcValue<Integer> av115, av113, av121, av217;
	
	private Cout c1, c3, c5, c7;
	
	@Before
	public void setUp()
	{
		ensembleArcValueTest = new EnsembleArcValue<Integer>();
		s1 = new Sommet<Integer>(1);
		s2 = new Sommet<Integer>(2);
		
		c1 = new Cout(1);
		c3 = new Cout(3);
		c5 = new Cout(5);
		c7 = new Cout(7);
		
		av115=new ArcValue<Integer>(s1,s1,c5);
		av113=new ArcValue<Integer>(s1,s1,c3);
		av121=new ArcValue<Integer>(s1,s2,c1);
		av217=new ArcValue<Integer>(s2,s1,c7);
		ensembleArcValueTest.ajouteArc(av115);
		ensembleArcValueTest.ajouteArc(av121);
		ensembleArcValueTest.ajouteArc(av217);
		
	}

	@Test
	public void testEnsembleArcValues()
	{
		EnsembleArcValue<Integer>ensembleArcValueClone=new EnsembleArcValue<Integer>(ensembleArcValueTest);
		assertEquals("Le constructeur clone",ensembleArcValueTest,ensembleArcValueClone);
		assertNotSame("Le constructeur clone créé un objet identique",ensembleArcValueTest,ensembleArcValueClone);
		ensembleArcValueClone.setValeur(s1, s1, c3);
		assertNotEquals("Modifier le clone modifie l'original",ensembleArcValueTest,ensembleArcValueClone);
	}
	
	@Test
	public void testAjouteArc()
	{
		ensembleArcValueTest.ajouteArc(av113);
		assertEquals("Le ajouteArc ajoute même si l'arc est présent",av115.getCout(),ensembleArcValueTest.getCout(s1, s1));
	}

	@Test
	public void testGetCout()
	{
		assertEquals("getCout ne marche pas ",av121.getCout(),ensembleArcValueTest.getCout(s1, s2));
	}
	
	@Test
	public void testSetValeur()
	{
		ensembleArcValueTest.setValeur(s1, s2, c7);
		assertEquals("setValeur ne marche pas",c7,ensembleArcValueTest.getCout(s1,s2));
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