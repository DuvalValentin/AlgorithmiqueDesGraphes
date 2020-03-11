package testing;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import factory.Factory;
import graphelements.interfaces.Sommet;
import graphelements.interfaces.TableauPlusCC;

public class TableauPlusCCTest
{
	private TableauPlusCC<Integer> tableau;
	private Sommet<Integer> s1, s2;
	private HashMap<Sommet<Integer>,Float> d;
	private HashMap<Sommet<Integer>,Sommet<Integer>> pred;

	@BeforeEach
	public void setUp()
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		tableau=Factory.tableauPlusCC(s1);
		tableau.initSommet(s2);
		tableau.modifDistance(s2,1f);
		d=new HashMap<Sommet<Integer>,Float>();
		d.put(s1,0f);
		d.put(s2,1f);
		pred=new HashMap<Sommet<Integer>,Sommet<Integer>>();
		pred.put(s1,s1);
		pred.put(s2,s1);
	}
	@Test
	public void testGetD()
	{
		assertEquals(d,tableau.getD(),"getD");
	}
	@Test
	public void testGetPred()
	{
		assertEquals(pred,tableau.getPred(),"getPred");
	}
	@Test
	public void testGetPrincipal()
	{
		assertEquals(s1,tableau.getPrincipal(),"getPrincipal");
	}
	@Test
	public void testInitSommet()
	{
		Sommet<Integer> s3=Factory.sommet(3);
		tableau.initSommet(s3);
		assertEquals(s1,tableau.getPredecesseur(s3));
		assertEquals(null,tableau.getDistance(s3));
	}
	@Test
	public void testModifDistance()
	{
		Float c5=5f;
		tableau.modifDistance(s1,c5);
		assertEquals(c5,tableau.getDistance(s1),"modifCout");
	}
	@Test
	public void testModifSommet()
	{
		tableau.modifPredecesseur(s1,s2);
		assertEquals(s2,tableau.getPredecesseur(s1),"modifSommet");
	}
	@Test
	public void testGetDistance()
	{
		assertEquals(1f,tableau.getDistance(s2),"getDistance");
	}
	@Test
	public void testGetPredecesseur()
	{
		assertEquals(s1,tableau.getPredecesseur(s2),"getPredecesseur");
	}
	@Test
	public void testToString()
	{
		assertEquals(s1.toString()+"=>"+d.toString()+pred.toString(),tableau.toString());
	}
	@Test
	public void testEquals()
	{
		assertNotEquals(null,tableau);
		assertNotEquals("ngrjhn",tableau);
		TableauPlusCC<Integer> otherTableau=Factory.tableauPlusCC(s2);
		assertNotEquals(otherTableau,tableau);
		otherTableau=Factory.tableauPlusCC(s1);
		otherTableau.initSommet(Factory.sommet(8));
		assertNotEquals(otherTableau,tableau);
		otherTableau=Factory.tableauPlusCC(s1);
		otherTableau.initSommet(s2);
		otherTableau.modifDistance(s2,9f);
		assertNotEquals(otherTableau,tableau);
		otherTableau.modifDistance(s2,1f);
		assertEquals(otherTableau,tableau);
	}
	@Test
	public void testHashCode()
	{
		assertEquals(s1.hashCode()+pred.hashCode()+d.hashCode(),tableau.hashCode());
	}
}
