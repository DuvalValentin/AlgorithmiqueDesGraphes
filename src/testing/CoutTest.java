package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graphElements.Elements.Cout;

public class CoutTest
{
	Cout coutTest;

	@Before
	public void setUp() 
	{
		coutTest=new Cout(5);
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
		assertTrue("GetValeur",5==coutTest.getValeur());
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
}