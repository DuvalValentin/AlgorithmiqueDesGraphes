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
	
	private Sommet<Integer> s1;
	private Sommet<Integer> s2;
	
	private ArcValue<Integer> av115;
	private ArcValue<Integer> av113;
	private ArcValue<Integer> av121;
	private ArcValue<Integer> av217;
	
	private Cout c1;
	private Cout c3;
	private Cout c5;
	private Cout c7;
	
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
		ensembleArcValueTest.add(av115);
		ensembleArcValueTest.add(av121);
		ensembleArcValueTest.add(av217);
		
	}

	@Test
	public void testEnsembleArcValues()
	{
		EnsembleArcValue<Integer>ensemble2=new EnsembleArcValue<Integer>(ensembleArcValueTest);
		assertEquals("Constructeur avec un ensemble en entrée",ensembleArcValueTest,ensemble2);
		assertNotSame("Constructeur avec un ensemble en entrée rend le même objet",ensembleArcValueTest,ensemble2);
	}
	
	@Test
	//TODO à REFAIRE
	public void testAjouteArc()
	{
		Cout res=new Cout();
		ensembleArcValueTest.add(av113);
		ensembleArcValueTest.getCout(s1, s1, res);
		assertEquals("Le add ajoute même si l'arc est présent",av115.getCout(),res);
	}

	@Test
	public void testGetValue()
	{
		Cout res=new Cout();
		ensembleArcValueTest.getCout(s1, s2, res);
		assertEquals("getValue ne marche pas ",av121.getCout(),res);
	}
}
