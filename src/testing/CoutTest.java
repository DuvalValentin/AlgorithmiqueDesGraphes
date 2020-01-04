package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.Factory;
import graphElements.Interfaces.InterfaceCout;

public class CoutTest
{
	InterfaceCout coutTest;
	float valeur=5;

	@BeforeEach
	public void setUp() 
	{
		
		coutTest=Factory.cout(valeur);
	}
	
	@Test
	public void testConstructeurClone()
	{
		InterfaceCout coutClone = Factory.cout(coutTest);
		assertEquals(coutTest,coutClone,"Le constructeur clone");
		assertNotSame(coutTest,coutClone,"Le constructeur clone créé un objet identique");
		coutClone.setValeur(9);
		assertNotEquals(coutTest,coutClone,"Modifier le clone modifie l'original");
	}

	@Test
	public void testGetValeur()
	{
		assertTrue(valeur==coutTest.getValeur(),"GetValeur");
	}

	@Test
	public void testSetValeur()
	{
		coutTest.setValeur(8);
		assertTrue(8==coutTest.getValeur(),"SetValeur");
	}

	@Test
	public void testSomme()
	{
		assertTrue(10==InterfaceCout.somme(coutTest, coutTest).getValeur(),"Somme");
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