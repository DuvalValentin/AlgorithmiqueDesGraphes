package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algorithme.PlusCC;
import factory.Factory;
import graphElements.Interfaces.*;

public class PlusCCTest
{
	//G pour Graphe, O pour OrdinalRacine, P pour Positif, N pour Négatif, A pour Absorbant
	private InterfaceSommet<Integer>s1,s2,s3,s4;
	private InterfaceCout c1,c2,c3,c5,c6,c9;
	private InterfaceCout c1N,c2N,c3N,c5N,/*c6N*,*/c9N;
	private InterfaceArcValue<Integer>a121,a135,a149,a232,a246,a343;
	private InterfaceArcValue<Integer>a121N,a135N,a149N,a232N,/*a246N,*/a343N,a215N;
	private InterfaceGrapheValue<Integer>GOP,GON,/*GN,*/GA;
	private InterfaceTableauPlusCC<Integer> TableauGOP,TableauGON/*,TableauGN*/;
	
	

	@Before
	public void setUp()
	{
		s1=Factory.sommet(1);s2=Factory.sommet(2);s3=Factory.sommet(3);s4=Factory.sommet(4);
		
		c1=Factory.cout(1);c2=Factory.cout(2);c3=Factory.cout(3);c5=Factory.cout(5);c6=Factory.cout(6);c9=Factory.cout(9);
		c1N=Factory.cout(-1);c2N=Factory.cout(-2);c3N=Factory.cout(-3);c5N=Factory.cout(-5);/*c6N=Factory.cout(-6);*/c9N=Factory.cout(-9);
		
		
		a121=Factory.arcValue(s1,s2,c1);a135=Factory.arcValue(s1,s3,c5);a149=Factory.arcValue(s1,s4,c9);
		a232=Factory.arcValue(s2,s3,c2);a246=Factory.arcValue(s2,s4,c6);a343=Factory.arcValue(s3,s4,c3);
		a121N=Factory.arcValue(s1,s2,c1N);a135N=Factory.arcValue(s1,s3,c5N);a149N=Factory.arcValue(s1,s4,c9N);
		a232N=Factory.arcValue(s2,s3,c2N);/*a246N=Factory.arcValue(s2,s4,c6N);*/a343N=Factory.arcValue(s3,s4,c3N);
		a215N=Factory.arcValue(s2,s1,c5N);
		
		GOP=Factory.grapheValue();
		GOP.ajouteSommet(s1);GOP.ajouteSommet(s2);GOP.ajouteSommet(s3);GOP.ajouteSommet(s4);
		GOP.ajouteArc(a121);GOP.ajouteArc(a135);GOP.ajouteArc(a149);GOP.ajouteArc(a232);GOP.ajouteArc(a246);GOP.ajouteArc(a343);
		
		TableauGOP=Factory.tableauPlusCC(s1,GOP);
		TableauGOP.modifDistance(s3, c3);TableauGOP.modifDistance(s4, c6);
		TableauGOP.modifPredecesseur(s3, s2);TableauGOP.modifPredecesseur(s4, s3);
		
		GON=Factory.grapheValue();
		GON.ajouteSommet(s1);GON.ajouteSommet(s2);GON.ajouteSommet(s3);GON.ajouteSommet(s4);
		GON.ajouteArc(a121);GON.ajouteArc(a135N);GON.ajouteArc(a149);GON.ajouteArc(a232N);GON.ajouteArc(a246);GON.ajouteArc(a343N);
		
		TableauGON=Factory.tableauPlusCC(s1,GON);
		TableauGON.modifDistance(s4, Factory.cout(-8));
		TableauGON.modifPredecesseur(s4, s3);
		
		GA=Factory.grapheValue();
		GA.ajouteSommet(s1);GA.ajouteSommet(s2);GA.ajouteSommet(s3);GA.ajouteSommet(s4);
		GA.ajouteArc(a121N);GA.ajouteArc(a135N);GA.ajouteArc(a232N);GA.ajouteArc(a215N);GA.ajouteArc(a149N);
	}

	@Test
	public void testDijkstra()
	{
		assertEquals("Dijkstra /OrdinalPositif",TableauGOP,PlusCC.Dijkstra(GOP, s1));
		PlusCC.Dijkstra(GA,s1);//Mieux à faire
	}
	
	@Test
	public void testOrdinalRacine()
	{
		assertEquals("OrdinalRacine /Positif",TableauGOP,PlusCC.OrdinalRacine(GOP, s1));
		assertEquals("OrdinalRacine /Negatif",TableauGON,PlusCC.OrdinalRacine(GON, s1));
		PlusCC.OrdinalRacine(GA, s1);
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
