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
	public void testConstructeurClone()
	{
		ArcValue<Integer> arcValueClone =new ArcValue<Integer>(arcValueTest);
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
		Sommet<Integer> wrongSommet= new Sommet<Integer>(6);
		Cout wrongCout = new Cout(10);
		ArcValue<Integer> memeArc = new ArcValue<Integer>(depart,arrivee,wrongCout);
		ArcValue<Integer> wrongArc = new ArcValue<Integer>(depart,wrongSommet,cout);
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
