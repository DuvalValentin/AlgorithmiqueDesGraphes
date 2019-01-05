package testing;

import graphElements.Elements.ArcValue;
import graphElements.Elements.Cout;
import graphElements.Elements.Sommet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArcValueTest
{
	private ArcValue<Integer> arcValueTest;
	private Sommet<Integer> depart;
	private Sommet<Integer> arrivee;
	private Cout cout;

	@Before
	public void setUp() 
	{
		depart=new Sommet<Integer>(3);
		arrivee=new Sommet<Integer>(9);
		cout=new Cout (5);
		arcValueTest=new ArcValue<Integer>(depart,arrivee,cout);
	}

	@Test
	public void testGetCout()
	{
		assertEquals("Test du getCout",cout,arcValueTest.getCout());
	}
	
	@Test
	public void testGetValeur()
	{
		assertTrue("Test du getValeur",5==arcValueTest.getValeur());
	}
	
	@Test
	public void testSetValeur()
	{
		arcValueTest.setValeur(9);
		assertTrue("Test du setValeur",9==arcValueTest.getValeur());
	}
	
	@Test
	public void testClone()
	{
		ArcValue<Integer> arcValueClone = arcValueTest.clone();
		assertEquals("Clone ne marche pas",arcValueTest,arcValueClone);
		assertNotSame("Le clone a le même cout",arcValueTest.getCout(),arcValueClone.getCout());
		arcValueClone.setValeur(0);
		assertNotEquals("Modifier le clone modifie l'original",arcValueTest.getCout(),arcValueClone.getCout());
	}
	
	

}
