package testing;
import graphElements.Sommet;

import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.Test;

public class SommetTest
{
	private Sommet<Integer> sommetTest;
	private Integer value;
	@Before
	public void setup()
	{
		value=5;
		sommetTest= new Sommet<Integer>(value);
	}
	
	@Test 
	public void testEquals()
	{
		Sommet<Integer> sommetEq = new Sommet<Integer>(5);
		Sommet<Integer> sommetNEq = new Sommet<Integer>(7);
		assertTrue("Equals rend faux pour deux Sommets censés être equals",sommetTest.getId().equals(sommetEq.getId()));
		assertFalse("Equals rend vrai pour deux Sommets censéq être not equals",sommetTest.getId().equals(sommetNEq.getId()));	
	}
	
	@Test
	public void testGetId()
	{
		assertEquals("Le getId de Sommet n'est pas fonctionnel",sommetTest.getId(),value);
	}
	
	@Test
	public void testSetId()
	{
		Integer value2=7;
		sommetTest.setId(value2);
		assertEquals("Le setId de Sommet n'est pas fonctionnel",sommetTest.getId(),value2);
	}
}
