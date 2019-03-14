package testing;

import graphElements.Interfaces.InterfaceArcValue;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceSommet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import factory.Factory;

public class ArcValueTest
{
	private InterfaceArcValue<Integer> arcValueTest;
	private InterfaceSommet<Integer> depart;
	private InterfaceSommet<Integer> arrivee;
	private InterfaceCout cout;

	@Before
	public void setUp() 
	{
		depart=Factory.sommet(3);
		arrivee=Factory.sommet(9);
		cout=Factory.cout(5);
		arcValueTest=Factory.arcValue(depart,arrivee,cout);
	}

	@Test
	public void testConstructeurClone()
	{
		InterfaceArcValue<Integer> arcValueClone =Factory.arcValue(arcValueTest);
		assertEquals("Le constructeur clone",arcValueTest,arcValueClone);
		assertNotSame("Le constructeur clone créé un objet identique",arcValueTest,arcValueClone);
		arcValueClone.setValeur(0);
		assertNotEquals("Modifier le clone modifie l'original",arcValueTest,arcValueClone);
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
	public void testMemeArc()
	{
		InterfaceSommet<Integer> wrongSommet= Factory.sommet(6);
		InterfaceCout wrongCout = Factory.cout(10);
		InterfaceArcValue<Integer> memeArc = Factory.arcValue(depart,arrivee,wrongCout);
		InterfaceArcValue<Integer> wrongArc = Factory.arcValue(depart,wrongSommet,cout);
		assertTrue("MemeArc pour deux arcs avec seulement le cout différent",arcValueTest.memeArc(memeArc));
		assertFalse("MemeArc pour deux arcs avec seulement l'arrivée differente",arcValueTest.memeArc(wrongArc));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals()
	{
		assertFalse(arcValueTest.equals("bguyG"));
	}
	
	@Test
	public void testHashCode()
	{
		int result=depart.hashCode()+arrivee.hashCode()+cout.hashCode();
		assertEquals(result,arcValueTest.hashCode());
	}
	
	@Test
	public void testToString()
	{
		String string = "("+depart.toString()+","+arrivee.toString()+")=>["+cout.toString()+"]";
		assertEquals(string,arcValueTest.toString());
	}
}
