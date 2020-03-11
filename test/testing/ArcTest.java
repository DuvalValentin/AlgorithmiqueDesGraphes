package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import factory.Factory;
import graphelements.interfaces.Arc;
import graphelements.interfaces.Sommet;

public class ArcTest
{
	private Arc<Integer> arcTest;
	private Sommet<Integer> depart;
	private Sommet<Integer> arrivee;

	@BeforeEach
	public void setup()
	{
		depart=Factory.sommet(5);
		arrivee=Factory.sommet(8);
		arcTest=Factory.arcNonValue(depart,arrivee);
	}
	@Test
	public void testConstructeurAndClone()
	{
		Arc<Integer> arcClone=Factory.arcNonValue(arcTest);
		assertEquals(arcTest,arcClone,"Le constructeur clone");
		assertNotSame(arcTest,arcClone,"Le constructeur clone créé un objet identique");
		arcClone.setArrivee(Factory.sommet(9));
		assertNotEquals(arcTest,arcClone,"Modifier le clone modifie l'original");
		Arc<Integer> arcId=Factory.arcNonValue(5,8);
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
		Sommet<Integer> changerDepart=Factory.sommet(3);
		arcTest.setDepart(changerDepart);
		assertEquals(arcTest.getDepart(),changerDepart,"Le setDepart de Arc n'est pas fonctionnel");
	}
	@Test
	public void testSetArrivee()
	{
		Sommet<Integer> changerArrivee=Factory.sommet(10);
		arcTest.setDepart(changerArrivee);
		assertEquals(arcTest.getDepart(),changerArrivee,"Le setArrivee de Arc n'est pas fonctionnel");
	}
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals()
	{
		Arc<Integer> arcEq=Factory.arcNonValue(depart,arrivee);
		Arc<Integer> arcNDepEq=Factory.arcNonValue(Factory.sommet(6),arrivee);
		Arc<Integer> arcNArrEq=Factory.arcNonValue(depart,Factory.sommet(7));
		Arc<Integer> arcNEq=Factory.arcNonValue(Factory.sommet(6),Factory.sommet(7));
		assertEquals(arcTest,arcEq,"Le equals de Arc rends faux pour deux arcs sensé être equals");
		assertNotEquals(arcTest,arcNDepEq,"Le equals de Arc rends vrai pour deux arcs avec un depart différent");
		assertNotEquals(arcTest,arcNArrEq,"Le equals de Arc rends vrai pour deux arcs avec une arrivee différente");
		assertNotEquals(arcTest,arcNEq,"Le equals de Arc rends vrai pour deux arcs avec un depart et une arrivee différents");
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
