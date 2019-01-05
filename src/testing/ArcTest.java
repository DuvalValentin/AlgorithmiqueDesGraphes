package testing;
import graphElements.Elements.Arc;
import graphElements.Elements.Sommet;

import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.Test;

public class ArcTest
{
	private Arc<Integer> arcTest;
	private Sommet<Integer> depart;
	private Sommet<Integer> arrivee;
	
	@Before
	public void setup()
	{
		depart=new Sommet<Integer>(5);
		arrivee=new Sommet<Integer>(8);
		arcTest=new Arc<Integer>(depart,arrivee);
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
		Sommet<Integer> changerDepart = new Sommet<Integer>(3);
		arcTest.setDepart(changerDepart);
		assertEquals("Le setDepart de Arc n'est pas fonctionnel",arcTest.getDepart(),changerDepart);
	}
	
	@Test
	public void testSetArrivee()
	{
		Sommet<Integer> changerArrivee = new Sommet<Integer>(10);
		arcTest.setDepart(changerArrivee);
		assertEquals("Le setArrivee de Arc n'est pas fonctionnel",arcTest.getDepart(),changerArrivee);
	}
	
	@Test
	public void testEquals()
	{
		Arc<Integer> arcEq = new Arc<Integer>(depart,arrivee);
		Arc<Integer> arcNDepEq = new Arc<Integer>(new Sommet<Integer>(6),arrivee);
		Arc<Integer> arcNArrEq = new Arc<Integer>(depart,new Sommet<Integer>(7));
		Arc<Integer> arcNEq = new Arc<Integer>(new Sommet<Integer>(6),new Sommet<Integer>(7));
		assertEquals("Le equals de Arc rends faux pour deux arcs sensé être equals",arcTest,arcEq);
		assertNotEquals("Le equals de Arc rends vrai pour deux arcs avec un depart différent",arcTest,arcNDepEq);
		assertNotEquals("Le equals de Arc rends vrai pour deux arcs avec une arrivee différente",arcTest,arcNArrEq);
		assertNotEquals("Le equals de Arc rends vrai pour deux arcs avec un depart et une arrivee différents",arcTest,arcNEq);
	}
	
	@Test
	public void testHashCode()
	{
		assertSame("Test du HashCode",13,arcTest.hashCode());
	}
	
	@Test
	public void testClone()
	{
		Arc<Integer> arcClone = arcTest.clone();
		assertEquals("Clone ne marche pas",arcTest,arcClone);
		arcClone.setArrivee(new Sommet<Integer>(9));
		assertNotEquals("Modifier le clone modifie l'original",arcTest,arcClone);
	}
}
