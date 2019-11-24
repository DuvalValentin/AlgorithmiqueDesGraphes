package testing;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceSommet;

import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.Test;

import factory.Factory;

public class ArcTest
{
	private InterfaceArc<Integer> arcTest;
	private InterfaceSommet<Integer> depart;
	private InterfaceSommet<Integer> arrivee;
	
	@Before
	public void setup()
	{
		depart=Factory.sommet(5);
		arrivee=Factory.sommet(8);
		arcTest=Factory.arcNonValue(depart, arrivee);
	}
	
	@Test
	public void testConstructeurAndClone()
	{
		InterfaceArc<Integer> arcClone = Factory.arcNonValue(arcTest);
		assertEquals("Le constructeur clone",arcTest,arcClone);
		assertNotSame("Le constructeur clone créé un objet identique",arcTest,arcClone);
		arcClone.setArrivee(Factory.sommet(9));
		assertNotEquals("Modifier le clone modifie l'original",arcTest,arcClone);
		InterfaceArc<Integer> arcId = Factory.arcNonValue(5,8);
		assertEquals("Constructeur juste avec les id des sommets ne marchent pas",arcTest,arcId);
	}
	
	@Test
	public void testGetDepart()
	{
		assertEquals("Le getDepart de Arc n'est pas fonctionnel",arcTest.getDepart(),depart);
	}
	
	@Test
	public void testGetArrivee()
	{
		assertEquals("Le getArrivee de Arc n'est pas fonctionnel",arcTest.getArrivee(),arrivee);
	}
	
	@Test
	public void testSetDepart()
	{
		InterfaceSommet<Integer> changerDepart = Factory.sommet(3);
		arcTest.setDepart(changerDepart);
		assertEquals("Le setDepart de Arc n'est pas fonctionnel",arcTest.getDepart(),changerDepart);
	}
	
	@Test
	public void testSetArrivee()
	{
		InterfaceSommet<Integer> changerArrivee = Factory.sommet(10);
		arcTest.setDepart(changerArrivee);
		assertEquals("Le setArrivee de Arc n'est pas fonctionnel",arcTest.getDepart(),changerArrivee);
	}
	
		@SuppressWarnings("unlikely-arg-type")
		@Test
	public void testEqualsMemeArc()
	{
		InterfaceArc<Integer> arcEq = Factory.arcNonValue(depart, arrivee);
		InterfaceArc<Integer> arcNDepEq = Factory.arcNonValue(Factory.sommet(6),arrivee);
		InterfaceArc<Integer> arcNArrEq = Factory.arcNonValue(depart,Factory.sommet(7));
		InterfaceArc<Integer> arcNEq = Factory.arcNonValue(Factory.sommet(6),Factory.sommet(7));
		assertEquals("Le equals de Arc rends faux pour deux arcs sensé être equals",arcTest,arcEq);
		assertNotEquals("Le equals de Arc rends vrai pour deux arcs avec un depart différent",arcTest,arcNDepEq);
		assertNotEquals("Le equals de Arc rends vrai pour deux arcs avec une arrivee différente",arcTest,arcNArrEq);
		assertNotEquals("Le equals de Arc rends vrai pour deux arcs avec un depart et une arrivee différents",arcTest,arcNEq);
		assertTrue("Le memeArc de Arc rends faux pour deux arcs sensé être les même",arcTest.memeArc(arcEq));
		assertFalse("Le memeArc de Arc rends vrai pour deux arcs avec un depart différent",arcTest.memeArc(arcNDepEq));
		assertFalse("Le memeArc de Arc rends vrai pour deux arcs avec une arrivee différente",arcTest.memeArc(arcNArrEq));
		assertFalse("Le memeArc de Arc rends vrai pour deux arcs avec un depart et une arrivee différents",arcTest.memeArc(arcNEq));
		assertFalse(arcTest.equals("cnjfbb"));
		assertFalse(arcTest.equals(null));
	}
	
	@Test
	public void testToString()
	{
		assertEquals("("+depart.toString()+","+arrivee.toString()+")",arcTest.toString());
	}
	
	@Test
	public void testHashCode()
	{
		assertSame("Test du HashCode",13,arcTest.hashCode());
	}
}
