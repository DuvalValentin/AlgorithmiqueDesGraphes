package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algorithme.PlusCC;
import graphElements.Elements.*;

public class PlusCCTest
{
	Sommet<Integer>s1;
	Sommet<Integer>s2;
	Sommet<Integer>s3;
	Sommet<Integer>s4;
	
	ArcValue<Integer>a12;
	ArcValue<Integer>a13;
	ArcValue<Integer>a14;
	ArcValue<Integer>a23;
	ArcValue<Integer>a24;
	ArcValue<Integer>a34;
	
	GrapheValue<Integer>GV;
	GrapheValue<Integer>Gfin;

	@Before
	public void setUp() 
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		
		a12=new ArcValue<Integer>(s1,s2,new Cout(1));
		a13=new ArcValue<Integer>(s1,s3,new Cout(5));
		a14=new ArcValue<Integer>(s1,s4,new Cout(9));
		a23=new ArcValue<Integer>(s2,s3,new Cout(2));
		a24=new ArcValue<Integer>(s2,s4,new Cout(6));
		a34=new ArcValue<Integer>(s3,s4,new Cout(3));
		
		GV=new GrapheValue<Integer>();
		GV.ajouteSommet(s1);GV.ajouteSommet(s2);GV.ajouteSommet(s3);GV.ajouteSommet(s4);
		GV.ajouteArc(a12);GV.ajouteArc(a13);GV.ajouteArc(a14);GV.ajouteArc(a23);GV.ajouteArc(a24);GV.ajouteArc(a34);
		
		Gfin=new GrapheValue<Integer>(GV);
		Gfin.setCout(s1, s3, new Cout(3));Gfin.setCout(s2, s4, new Cout(5));Gfin.setCout(s1, s4, new Cout(6));
	}

	@Test
	public void testDijkstra()
	{
		PlusCC.Dijkstra(GV, s1);
		assertEquals("Dijkstra ne marche pas",GV,Gfin);
	}

}
