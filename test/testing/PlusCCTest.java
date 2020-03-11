package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import algorithme.PlusCC;
import exception.AbsorbingException;
import exception.CircuitException;
import exception.NotRootException;
import factory.Factory;
import graphelements.interfaces.*;

public class PlusCCTest
{
	// G pour Graphe, O pour OrdinalRacine, P pour Positif, N pour Négatif, A pour
	// Absorbant, R pour Racine seul
	private Sommet<Integer> s1, s2, s3, s4;
	private Float c1, c2, c3, c5, c6, c9;
	private Float c1N, c2N, c3N, c5N, /* c6N*, */c9N;
	private ArcValue<Integer> a121, a135, a149, a232, a246, a343;
	private ArcValue<Integer> a121N, a135N, a149N, a232N, /* a246N, */a322N, a343N, a215N;
	private GrapheValue<Integer> GOP, GON, /* GN, */GA, GRA;
	private TableauPlusCC<Integer> TableauGOP, TableauGON/* ,TableauGN */;

	@BeforeEach
	public void setUp()
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		c1=1f;
		c2=2f;
		c3=3f;
		c5=5f;
		c6=6f;
		c9=9f;
		c1N=-1f;
		c2N=-2f;
		c3N=-3f;
		c5N=-5f;
		/* c6N=-6f; */
		c9N=-9f;
		a121=Factory.arcValue(s1,s2,c1);
		a135=Factory.arcValue(s1,s3,c5);
		a149=Factory.arcValue(s1,s4,c9);
		a232=Factory.arcValue(s2,s3,c2);
		a246=Factory.arcValue(s2,s4,c6);
		a343=Factory.arcValue(s3,s4,c3);
		a121N=Factory.arcValue(s1,s2,c1N);
		a135N=Factory.arcValue(s1,s3,c5N);
		a149N=Factory.arcValue(s1,s4,c9N);
		a232N=Factory.arcValue(s2,s3,c2N);
		/* a246N=Factory.arcValue(s2,s4,c6N); */a343N=Factory.arcValue(s3,s4,c3N);
		a215N=Factory.arcValue(s2,s1,c5N);
		a322N=Factory.arcValue(s3,s2,c2N);
		GOP=Factory.grapheValue();
		GOP.ajouteSommet(s1);
		GOP.ajouteSommet(s2);
		GOP.ajouteSommet(s3);
		GOP.ajouteSommet(s4);
		GOP.ajouteArc(a121);
		GOP.ajouteArc(a135);
		GOP.ajouteArc(a149);
		GOP.ajouteArc(a232);
		GOP.ajouteArc(a246);
		GOP.ajouteArc(a343);
		TableauGOP=Factory.tableauPlusCC(s1,GOP);
		TableauGOP.modifDistance(s3,3f);
		TableauGOP.modifDistance(s4,6f);
		TableauGOP.modifPredecesseur(s3,s2);
		TableauGOP.modifPredecesseur(s4,s3);
		GON=Factory.grapheValue();
		GON.ajouteSommet(s1);
		GON.ajouteSommet(s2);
		GON.ajouteSommet(s3);
		GON.ajouteSommet(s4);
		GON.ajouteArc(a121);
		GON.ajouteArc(a135N);
		GON.ajouteArc(a149);
		GON.ajouteArc(a232N);
		GON.ajouteArc(a246);
		GON.ajouteArc(a343N);
		TableauGON=Factory.tableauPlusCC(s1,GON);
		TableauGON.modifDistance(s4,-8f);
		TableauGON.modifPredecesseur(s4,s3);
		GA=Factory.grapheValue();
		GA.ajouteSommet(s1);
		GA.ajouteSommet(s2);
		GA.ajouteSommet(s3);
		GA.ajouteSommet(s4);
		GA.ajouteArc(a121N);
		GA.ajouteArc(a135N);
		GA.ajouteArc(a232N);
		GA.ajouteArc(a215N);
		GA.ajouteArc(a149N);
		GRA=Factory.grapheValue(GA);
		GRA.supprArc(a215N);
		GRA.supprArc(a121N);
		GRA.ajouteArc(a322N);
	}
	@Test
	public void testDijkstra()
	{
		assertEquals(TableauGOP,PlusCC.dijkstra(GOP,s1),"Dijkstra /OrdinalPositif");
		PlusCC.dijkstra(GA,s1);
		// PlusCC.Dijkstra(GRA,s1);//Si pas de valeur préexistante entre s1 et s2 ça
		// plante
	}
	@Test
	public void testOrdinalRacine()
	{
		try
		{
			assertEquals(TableauGOP,PlusCC.ordinalRacine(GOP,s1),"OrdinalRacine /Positif");
			assertEquals(TableauGON,PlusCC.ordinalRacine(GON,s1),"OrdinalRacine /Negatif");
		}
		catch(NotRootException e)
		{
			e.printStackTrace();
		}
		catch(CircuitException e)
		{
			e.printStackTrace();
		}
		assertThrows(NotRootException.class,()->{ PlusCC.ordinalRacine(GA,s1); });
		assertThrows(CircuitException.class,()->{ PlusCC.ordinalRacine(GRA,s1); });
	}
	@Test
	public void testBellmanFord()
	{
		try
		{
			assertEquals(TableauGOP,PlusCC.bellmanFord(GOP,s1),"BellmanFord /OrdinalPositif");
			assertEquals(TableauGON,PlusCC.bellmanFord(GON,s1),"BellmanFod /OrdinalNegatif");
		}
		catch(AbsorbingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThrows(AbsorbingException.class,()->{ PlusCC.bellmanFord(GA,s1); });
		assertThrows(AbsorbingException.class,()->{ PlusCC.bellmanFord(GRA,s1); });
	}
	@Test
	public void testFord()
	{
		try
		{
			assertEquals(TableauGOP,PlusCC.ford(GOP,s1),"Ford /OrdinalPositif");
			assertEquals(TableauGON,PlusCC.ford(GON,s1),"Ford /OrdinalNegatif");
		}
		catch(AbsorbingException e)
		{
			e.printStackTrace();
		}
		assertThrows(AbsorbingException.class,()->{ PlusCC.ford(GA,s1); });
		assertThrows(AbsorbingException.class,()->{ PlusCC.ford(GRA,s1); });
	}
}
