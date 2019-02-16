package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algorithme.PlusCC;
import graphElements.Elements.*;

public class PlusCCTest
{
	//G pour Graphe, O pour OrdinalRacine, P pour Positif, N pour Négatif, A pour Absorbant
	Sommet<Integer>s1,s2,s3,s4;
	Cout c1,c2,c3,c5,c6,c9;
	Cout c1N,c2N,c3N,c5N,c6N,c9N;
	ArcValue<Integer>a121,a135,a149,a232,a246,a343;
	ArcValue<Integer>a121N,a135N,a149N,a232N,a246N,a343N;
	GrapheValue<Integer>GOP;
	GrapheValue<Integer>GON;
	GrapheValue<Integer>GN;
	GrapheValue<Integer>GA;
	TableauPlusCC<Integer> TableauGOP;
	TableauPlusCC<Integer> TableauGON;
	TableauPlusCC<Integer> TableauGN;
	TableauPlusCC<Integer> TableauGA;

	@Before
	public void setUp()
	{
		s1=new Sommet<Integer>(1);s2=new Sommet<Integer>(2);s3=new Sommet<Integer>(3);s4=new Sommet<Integer>(4);
		
		c1=new Cout(1);c2=new Cout(2);c3=new Cout(3);c5=new Cout(5);c6=new Cout(6);c9=new Cout(9);
		c1N=new Cout(-1);c2N=new Cout(-2);c3N=new Cout(-3);c5N=new Cout(-5);c6N=new Cout(-6);c9N=new Cout(-9);
		
		
		a121=new ArcValue<Integer>(s1,s2,c1);a135=new ArcValue<Integer>(s1,s3,c5);a149=new ArcValue<Integer>(s1,s4,c9);
		a232=new ArcValue<Integer>(s2,s3,c2);a246=new ArcValue<Integer>(s2,s4,c6);a343=new ArcValue<Integer>(s3,s4,c3);
		a121N=new ArcValue<Integer>(s1,s2,c1N);a135N=new ArcValue<Integer>(s1,s3,c5N);a149N=new ArcValue<Integer>(s1,s4,c9N);
		a232N=new ArcValue<Integer>(s2,s3,c2N);a246N=new ArcValue<Integer>(s2,s4,c6N);a343N=new ArcValue<Integer>(s3,s4,c3N);
		
		GOP=new GrapheValue<Integer>();
		GOP.ajouteSommet(s1);GOP.ajouteSommet(s2);GOP.ajouteSommet(s3);GOP.ajouteSommet(s4);
		GOP.ajouteArc(a121);GOP.ajouteArc(a135);GOP.ajouteArc(a149);GOP.ajouteArc(a232);GOP.ajouteArc(a246);GOP.ajouteArc(a343);
		
		TableauGOP=new TableauPlusCC<Integer>(s1,GOP);
		TableauGOP.modifDistance(s3, c3);TableauGOP.modifDistance(s4, c6);
		TableauGOP.modifPredecesseur(s3, s2);TableauGOP.modifPredecesseur(s4, s3);
		
		GON=new GrapheValue<Integer>();
		GON.ajouteSommet(s1);GON.ajouteSommet(s2);GON.ajouteSommet(s3);GON.ajouteSommet(s4);
		GON.ajouteArc(a121);GON.ajouteArc(a135N);GON.ajouteArc(a149);GON.ajouteArc(a232N);GON.ajouteArc(a246);GON.ajouteArc(a343N);
		
		TableauGON=new TableauPlusCC<Integer>(s1,GON);
		TableauGON.modifDistance(s4, new Cout(-8));
		TableauGON.modifPredecesseur(s4, s3);
	}

	@Test
	public void testDijkstra()
	{
		assertEquals("Dijkstra /OrdinalPositif",TableauGOP,PlusCC.Dijkstra(GOP, s1));
	}
	
	@Test
	public void testOrdinalRacine()
	{
		assertEquals("OrdinalRacine /Positif",TableauGOP,PlusCC.OrdinalRacine(GOP, s1));
		assertEquals("OrdinalRacine /Negatif",TableauGON,PlusCC.OrdinalRacine(GON, s1));
	}
	
	@Test
	public void testBellmanFord()
	{
		assertEquals("BellmanFord /OrdinalPositif",TableauGOP,PlusCC.BellmanFord(GOP, s1));
		assertEquals("BellmanFord /OrdinalNegatif",TableauGON,PlusCC.BellmanFord(GON, s1));
	}
	
	@Test
	public void testFord()
	{
		assertEquals("Ford /OrdinalPositif",TableauGOP,PlusCC.Ford(GOP, s1));
		assertEquals("Ford /OrdinalNegatif",TableauGON,PlusCC.Ford(GON, s1));
	}
}
