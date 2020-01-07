package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.Factory;
import graphelements.interfaces.InterfaceSommet;

public class SommetTest
{
	private InterfaceSommet<Integer> sommetTest;
	private Integer value;
	
	@BeforeEach
	public void setup()
	{
		value=5;
		sommetTest= Factory.sommet(value);
	}
	
	@Test
	public void testConstructorClone()
	{
		InterfaceSommet<Integer> sommetClone=Factory.sommet(sommetTest);
		assertEquals(sommetTest,sommetClone,"Le constructeur clone");
		assertNotSame(sommetTest,sommetClone,"Le constructeur clone créé un objet identique");
		sommetClone.setId(7);
		assertNotEquals(sommetTest,sommetClone,"Modifier le clone modifie l'original");
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test 
	public void testEquals()
	{
		InterfaceSommet<Integer> sommetEq = Factory.sommet(5);
		InterfaceSommet<Integer> sommetNEq = Factory.sommet(7);
		String string= "okidoki";
		assertTrue(sommetTest.equals(sommetEq),"Equals rend faux pour deux Sommets censés être equals");
		assertFalse(sommetTest.equals(sommetNEq),"Equals rend vrai pour deux Sommets censés être not equals");
		assertFalse(sommetTest.equals(null),"Equals rend vrai pour lorsque comparé à null");
		assertFalse(sommetTest.equals(string),"Equals rend vrai pour lorsque comparé à un objet n'étant pas un sommet");
	}
	
	@Test
	public void testGetId()
	{
		assertEquals(sommetTest.getId(),value,"Le getId de Sommet n'est pas fonctionnel");
	}
	
	@Test
	public void testSetId()
	{
		Integer value2=7;
		sommetTest.setId(value2);
		assertEquals(sommetTest.getId(),value2,"Le setId de Sommet n'est pas fonctionnel");
	}
	
	@Test 
	public void testToString()
	{
		assertEquals(value.toString(),sommetTest.toString(),"ToString ne marche pas bien");
	}
	
	@Test
	public void testHashCode()
	{
		assertSame(value.hashCode(),sommetTest.hashCode(),"HashCode");
	}
}
