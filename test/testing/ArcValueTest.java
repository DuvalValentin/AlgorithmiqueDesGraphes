package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import factory.Factory;
import graphelements.interfaces.ArcValue;
import graphelements.interfaces.Sommet;

public class ArcValueTest
{
	private ArcValue<Integer> arcValueTest;
	private Sommet<Integer> depart;
	private Sommet<Integer> arrivee;
	private Float cout;

	@BeforeEach
	public void setUp()
	{
		depart=Factory.sommet(3);
		arrivee=Factory.sommet(9);
		cout=5f;
		arcValueTest=Factory.arcValue(depart,arrivee,cout);
	}
	@Test
	public void testConstructeurClone()
	{
		ArcValue<Integer> arcValueClone=Factory.arcValue(arcValueTest);
		assertEquals(arcValueTest,arcValueClone,"Le constructeur clone");
		assertNotSame(arcValueTest,arcValueClone,"Le constructeur clone créé un objet identique");
		arcValueClone.setCout(0f);
		assertNotEquals(arcValueTest,arcValueClone,"Modifier le clone modifie l'original");
	}
	@Test
	public void testGetValeur()
	{
		assertTrue(5==arcValueTest.getCout(),"Test du getValeur");
	}
	@Test
	public void testSetValeur()
	{
		arcValueTest.setCout(9f);
		assertTrue(9==arcValueTest.getCout(),"Test du setValeur");
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
