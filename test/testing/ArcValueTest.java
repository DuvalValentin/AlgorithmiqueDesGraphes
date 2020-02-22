package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import factory.Factory;
import graphelements.interfaces.ArcValue;
import graphelements.interfaces.Cout;
import graphelements.interfaces.Sommet;

public class ArcValueTest
{
	private ArcValue<Integer> arcValueTest;
	private Sommet<Integer> depart;
	private Sommet<Integer> arrivee;
	private Cout cout;

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
		ArcValue<Integer> arcValueClone=Factory.arcValue(arcValueTest);
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
		Sommet<Integer> wrongSommet=Factory.sommet(6);
		Cout wrongCout=Factory.cout(10);
		ArcValue<Integer> memeArc=Factory.arcValue(depart,arrivee,wrongCout);
		ArcValue<Integer> wrongArc=Factory.arcValue(depart,wrongSommet,cout);
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
		String string="("+depart.toString()+","+arrivee.toString()+")=>["+cout.toString()+"]";
		assertEquals(string,arcValueTest.toString());
	}
}
