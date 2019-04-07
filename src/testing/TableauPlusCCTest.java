package testing;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import factory.Factory;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceSommet;
import graphElements.Interfaces.InterfaceTableauPlusCC;

public class TableauPlusCCTest
{
	private InterfaceTableauPlusCC<Integer> tableau;
	private InterfaceSommet<Integer> s1,s2;
	private InterfaceCout c1;
	private HashMap<InterfaceSommet<Integer>,InterfaceCout> d;
	private HashMap<InterfaceSommet<Integer>,InterfaceSommet<Integer>> pred;

	@Before
	public void setUp()
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		c1=Factory.cout(1);
		tableau=Factory.tableauPlusCC(s1);
		tableau.initSommet(s2);
		tableau.modifDistance(s2, c1);
		d=new HashMap<InterfaceSommet<Integer>,InterfaceCout>();
		d.put(s1, Factory.cout());
		d.put(s2, c1);
		pred=new HashMap<InterfaceSommet<Integer>,InterfaceSommet<Integer>>();
		pred.put(s1, s1);
		pred.put(s2, s1);
	}

	@Test
	public void testGetD()
	{
		assertEquals("getD",d,tableau.getD());
	}

	@Test
	public void testGetPred()
	{
		assertEquals("getPred",pred,tableau.getPred());
	}

	@Test
	public void testGetPrincipal()
	{
		assertEquals("getPrincipal",s1,tableau.getPrincipal());
	}

	@Test
	public void testInitSommet()
	{
		InterfaceSommet<Integer>s3=Factory.sommet(3);
		tableau.initSommet(s3);
	}

	@Test
	public void testModifDistance()
	{
		InterfaceCout c5 = Factory.cout(5);
		tableau.modifDistance(s1, c5);
		assertEquals("modifCout",c5,tableau.getDistance(s1));
	}

	@Test
	public void testModifSommet()
	{
		tableau.modifPredecesseur(s1, s2);
		assertEquals("modifSommet",s2,tableau.getPredecesseur(s1));
	}

	@Test
	public void testGetDistance()
	{
		assertEquals("getDistance",c1,tableau.getDistance(s2));
	}

	@Test
	public void testGetPredecesseur()
	{
		assertEquals("getPredecesseur",s1,tableau.getPredecesseur(s2));
	}
	
	@Test
	public void testToString()
	{
		assertEquals(s1.toString()+"=>"+d.toString()+pred.toString(),tableau.toString());
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals()
	{
		assertFalse(tableau.equals(null));
		assertFalse(tableau.equals("ngrjhn"));
	}
}
