package testing;

import graphElements.Interfaces.InterfaceArcValue;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceSommet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.Factory;

public class ArcValueTest
{
	private InterfaceArcValue<Integer> arcValueTest;
	private InterfaceSommet<Integer> depart;
	private InterfaceSommet<Integer> arrivee;
	private InterfaceCout cout;

	@BeforeEach
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
		assertEquals(arcValueTest,arcValueClone,"Le constructeur clone");
		assertNotSame(arcValueTest,arcValueClone,"Le constructeur clone créé un objet identique");
		arcValueClone.setValeur(0);
		assertNotEquals(arcValueTest,arcValueClone,"Modifier le clone modifie l'original");
	}
	
	@Test
	public void testGetCout()
	{
		assertEquals(cout,arcValueTest.getCout(),"Test du getCout");
	}
	
	@Test
	public void testGetValeur()
	{
		assertTrue(5==arcValueTest.getValeur(),"Test du getValeur");
	}
	
	@Test
	public void testSetValeur()
	{
		arcValueTest.setValeur(9);
		assertTrue(9==arcValueTest.getValeur(),"Test du setValeur");
	}
	@Test
	public void testMemeArc()
	{
		InterfaceSommet<Integer> wrongSommet= Factory.sommet(6);
		InterfaceCout wrongCout = Factory.cout(10);
		InterfaceArcValue<Integer> memeArc = Factory.arcValue(depart,arrivee,wrongCout);
		InterfaceArcValue<Integer> wrongArc = Factory.arcValue(depart,wrongSommet,cout);
		assertTrue(arcValueTest.memeArc(memeArc),"MemeArc pour deux arcs avec seulement le cout différent");
		assertFalse(arcValueTest.memeArc(wrongArc),"MemeArc pour deux arcs avec seulement l'arrivée differente");
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
