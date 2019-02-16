package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graphElements.Elements.Cout;

public class CoutTest
{
	Cout coutTest;
	float valeur=5;

	@Before
	public void setUp() 
	{
		
		coutTest=new Cout(valeur);
	}
	
	@Test
	public void testConstructeurClone()
	{
		Cout coutClone = new Cout(coutTest);
		assertEquals("Le constructeur clone",coutTest,coutClone);
		assertNotSame("Le constructeur clone créé un objet identique",coutTest,coutClone);
		coutClone.setValeur(9);
		assertNotEquals("Modifier le clone modifie l'original",coutTest,coutClone);
	}

	@Test
	public void testGetValeur()
	{
		assertTrue("GetValeur",valeur==coutTest.getValeur());
	}

	@Test
	public void testSetValeur()
	{
		coutTest.setValeur(8);
		assertTrue("SetValeur",8==coutTest.getValeur());
	}

	@Test
	public void testSomme()
	{
		assertTrue("Somme",10==Cout.somme(coutTest, coutTest).getValeur());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals()
	{
		assertFalse(coutTest.equals("jnbb"));
		assertFalse(coutTest.equals(null));
	}
	
	@Test
	public void testToString()
	{
		assertEquals(""+valeur,coutTest.toString());
	}
	
	@Test
	public void testHashCode()
	{
		assertTrue(valeur==coutTest.hashCode());
	}
}