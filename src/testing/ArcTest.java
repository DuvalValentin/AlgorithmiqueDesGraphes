package testing;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceSommet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import factory.Factory;

public class ArcTest
{
	private InterfaceArc<Integer> arcTest;
	private InterfaceSommet<Integer> depart;
	private InterfaceSommet<Integer> arrivee;
	
	@BeforeEach
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
		assertEquals(arcTest,arcClone,"Le constructeur clone");
		assertNotSame(arcTest,arcClone,"Le constructeur clone créé un objet identique");
		arcClone.setArrivee(Factory.sommet(9));
		assertNotEquals(arcTest,arcClone,"Modifier le clone modifie l'original");
		InterfaceArc<Integer> arcId = Factory.arcNonValue(5,8);
		assertEquals(arcTest,arcId,"Constructeur juste avec les id des sommets ne marchent pas");
	}
	
	@Test
	public void testGetDepart()
	{
		assertEquals(arcTest.getDepart(),depart,"Le getDepart de Arc n'est pas fonctionnel");
	}
	
	@Test
	public void testGetArrivee()
	{
		assertEquals(arcTest.getArrivee(),arrivee,"Le getArrivee de Arc n'est pas fonctionnel");
	}
	
	@Test
	public void testSetDepart()
	{
		InterfaceSommet<Integer> changerDepart = Factory.sommet(3);
		arcTest.setDepart(changerDepart);
		assertEquals(arcTest.getDepart(),changerDepart,"Le setDepart de Arc n'est pas fonctionnel");
	}
	
	@Test
	public void testSetArrivee()
	{
		InterfaceSommet<Integer> changerArrivee = Factory.sommet(10);
		arcTest.setDepart(changerArrivee);
		assertEquals(arcTest.getDepart(),changerArrivee,"Le setArrivee de Arc n'est pas fonctionnel");
	}
	
		@SuppressWarnings("unlikely-arg-type")
		@Test
	public void testEqualsMemeArc()
	{
		InterfaceArc<Integer> arcEq = Factory.arcNonValue(depart, arrivee);
		InterfaceArc<Integer> arcNDepEq = Factory.arcNonValue(Factory.sommet(6),arrivee);
		InterfaceArc<Integer> arcNArrEq = Factory.arcNonValue(depart,Factory.sommet(7));
		InterfaceArc<Integer> arcNEq = Factory.arcNonValue(Factory.sommet(6),Factory.sommet(7));
		assertEquals(arcTest,arcEq,"Le equals de Arc rends faux pour deux arcs sensé être equals");
		assertNotEquals(arcTest,arcNDepEq,"Le equals de Arc rends vrai pour deux arcs avec un depart différent");
		assertNotEquals(arcTest,arcNArrEq,"Le equals de Arc rends vrai pour deux arcs avec une arrivee différente");
		assertNotEquals(arcTest,arcNEq,"Le equals de Arc rends vrai pour deux arcs avec un depart et une arrivee différents");
		assertTrue(arcTest.memeArc(arcEq),"Le memeArc de Arc rends faux pour deux arcs sensé être les même");
		assertFalse(arcTest.memeArc(arcNDepEq),"Le memeArc de Arc rends vrai pour deux arcs avec un depart différent");
		assertFalse(arcTest.memeArc(arcNArrEq),"Le memeArc de Arc rends vrai pour deux arcs avec une arrivee différente");
		assertFalse(arcTest.memeArc(arcNEq),"Le memeArc de Arc rends vrai pour deux arcs avec un depart et une arrivee différents");
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
		assertSame(13,arcTest.hashCode(),"Test du HashCode");
	}
}
