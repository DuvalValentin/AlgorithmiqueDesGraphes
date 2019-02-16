package testing;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import graphElements.Elements.Cout;
import graphElements.Elements.Sommet;
import graphElements.Elements.TableauPlusCC;

public class TableauPlusCCTest
{
	private TableauPlusCC<Integer> tableau;
	private Sommet<Integer> s1,s2;
	private Cout c1;
	private HashMap<Sommet<Integer>,Cout> d;
	private HashMap<Sommet<Integer>,Sommet<Integer>> pred;

	@Before
	public void setUp()
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		c1=new Cout(1);
		tableau=new TableauPlusCC<Integer>(s1);
		tableau.initSommet(s2);
		tableau.modifDistance(s2, c1);
		d=new HashMap<Sommet<Integer>,Cout>();
		d.put(s1, new Cout());
		d.put(s2, c1);
		pred=new HashMap<Sommet<Integer>,Sommet<Integer>>();
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
		Sommet<Integer>s3=new Sommet<Integer>(3);
		tableau.initSommet(s3);
	}

	@Test
	public void testModifDistance()
	{
		Cout c5 = new Cout(5);
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
