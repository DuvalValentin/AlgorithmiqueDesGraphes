package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algorithme.PlusCC;
import graphElements.Elements.*;

public class PlusCCTest
{
	Sommet<Integer>s1,s2,s3,s4;
	Cout c1,c2,c3,c5,c6,c9;
	ArcValue<Integer>a121,a135,a149,a232,a246,a343;
	GrapheValue<Integer>GV;
	TableauPlusCC<Integer> TableauFin;

	@Before
	public void setUp() 
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		
		c1=new Cout(1);
		c2=new Cout(2);
		c3=new Cout(3);
		c5=new Cout(5);
		c6=new Cout(6);
		c9=new Cout(9);
		
		
		a121=new ArcValue<Integer>(s1,s2,c1);
		a135=new ArcValue<Integer>(s1,s3,c5);
		a149=new ArcValue<Integer>(s1,s4,c9);
		a232=new ArcValue<Integer>(s2,s3,c2);
		a246=new ArcValue<Integer>(s2,s4,c6);
		a343=new ArcValue<Integer>(s3,s4,c3);
		
		GV=new GrapheValue<Integer>();
		GV.ajouteSommet(s1);GV.ajouteSommet(s2);GV.ajouteSommet(s3);GV.ajouteSommet(s4);
		GV.ajouteArc(a121);GV.ajouteArc(a135);GV.ajouteArc(a149);GV.ajouteArc(a232);GV.ajouteArc(a246);GV.ajouteArc(a343);
		
		TableauFin=new TableauPlusCC<Integer>(s1);
		TableauFin.initSommet(s2);TableauFin.initSommet(s3);TableauFin.initSommet(s4);
		TableauFin.modifDistance(s2, c1);TableauFin.modifDistance(s3, c3);TableauFin.modifDistance(s4, c6);
		TableauFin.modifPredecesseur(s2, s1);TableauFin.modifPredecesseur(s3, s2);TableauFin.modifPredecesseur(s4, s3);
	}

	@Test
	public void testDijkstra()
	{
		assertEquals("Dijkstra",TableauFin,PlusCC.Dijkstra(GV, s1));
	}
	
	@Test
	public void testOrdinalRacine()
	{
		assertEquals("OrdinalRacine",TableauFin,PlusCC.OrdinalRacine(GV, s1));
	}
	
	@Test
	public void testBellmanFord()
	{
		assertEquals("BellmanFord",TableauFin,PlusCC.BellmanFord(GV, s1));
	}
}
