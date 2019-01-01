package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algorithme.FermetureTransitive;
import graphElements.Elements.*;

public class FermetureTransitiveTest
{
	private Graphe<Integer> G;
	private Graphe<Integer> GT;
	
	private EnsembleArc<Integer>Gamma;
	private EnsembleArc<Integer>GammaT;
	private EnsembleSommet<Integer> X;
	private Sommet<Integer> s1;
	private Sommet<Integer> s2;
	private Sommet<Integer> s3;
	private Sommet<Integer> s4;
	private Arc<Integer> a12;
	private Arc<Integer> a23;
	private Arc<Integer> a32;
	private Arc<Integer> a34;
	private Arc<Integer> a44;
	
	private Arc<Integer> a13;
	private Arc<Integer> a14;
	private Arc<Integer> a22;
	private Arc<Integer> a24;
	private Arc<Integer> a33;

	@Before
	public void setUp() throws Exception
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		X=new EnsembleSommet<Integer>();
		X.ajouteSommet(s1);X.ajouteSommet(s2);X.ajouteSommet(s3);X.ajouteSommet(s4);
		
		a12 = new Arc<Integer>(s1,s2);
		a23 = new Arc<Integer>(s2,s3);
		a32 = new Arc<Integer>(s3,s2);
		a34 = new Arc<Integer>(s3,s4);
		a44 = new Arc<Integer>(s4,s4);
		Gamma=new EnsembleArc<Integer>();
		Gamma.ajouteArc(a12);Gamma.ajouteArc(a23);Gamma.ajouteArc(a32);Gamma.ajouteArc(a34);Gamma.ajouteArc(a44);
		
		G=new Graphe<Integer>(X,Gamma);
		
		a13 = new Arc<Integer>(s1,s3);
		a14 = new Arc<Integer>(s1,s4);
		a22 = new Arc<Integer>(s2,s2);
		a24 = new Arc<Integer>(s2,s4);
		a33 = new Arc<Integer>(s3,s3);
		GammaT=new EnsembleArc<Integer>(Gamma);
		GammaT.ajouteArc(a13);GammaT.ajouteArc(a14);GammaT.ajouteArc(a22);GammaT.ajouteArc(a24);GammaT.ajouteArc(a33);
		GT=new Graphe<Integer>(X,GammaT);
	}

	/*
	@Test
	public void testCompostion()
	{
		Graphe<Integer> Gcomp=FermetureTransitive.Composition(G,G);
		EnsembleArc<Integer> Gammacomp=new EnsembleArc<Integer>();
		Gammacomp.ajouteArc(a13);
		Gammacomp.ajouteArc(a22);
		Gammacomp.ajouteArc(a24);
		Gammacomp.ajouteArc(a33);
		Gammacomp.ajouteArc(a34);
		Gammacomp.ajouteArc(a44);
		assertEquals("La composition ne marche pas",Gammacomp,Gcomp.getGamma());
	}//Commenté tant que composition est privé */
	
	@Test
	public void testPuissanceGraphe()
	{
		Graphe<Integer> PG = FermetureTransitive.PuissanceDeGraphe(G);
		assertEquals("La puissance de graphe ne marche pas",GT,PG);
	}
	@Test
	public void testRoy_Warshall()
	{
		Graphe<Integer>RW=FermetureTransitive.Roy_Warshall(G);
		assertEquals("Roy_Warshall ne marche pas",GT,RW);
	}
}
