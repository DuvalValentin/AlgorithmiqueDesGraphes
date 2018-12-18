package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algorithme.FermetureTransitive;
import graphElements.*;

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
		X.add(s1);X.add(s2);X.add(s3);X.add(s4);
		
		a12 = new Arc<Integer>(s1,s2);
		a23 = new Arc<Integer>(s2,s3);
		a32 = new Arc<Integer>(s3,s2);
		a34 = new Arc<Integer>(s3,s4);
		a44 = new Arc<Integer>(s4,s4);
		Gamma=new EnsembleArc<Integer>();
		Gamma.add(a12);Gamma.add(a23);Gamma.add(a32);Gamma.add(a34);Gamma.add(a44);
		
		G=new Graphe<Integer>(X,Gamma);
		
		a13 = new Arc<Integer>(s1,s3);
		a14 = new Arc<Integer>(s1,s4);
		a22 = new Arc<Integer>(s2,s2);
		a24 = new Arc<Integer>(s2,s4);
		a33 = new Arc<Integer>(s3,s3);
		GammaT=new EnsembleArc<Integer>(Gamma);
		GammaT.add(a13);GammaT.add(a14);GammaT.add(a22);GammaT.add(a24);GammaT.add(a33);
		GT=new Graphe<Integer>(X,GammaT);
	}

	/*
	@Test
	public void testCompostion()
	{
		Graphe<Integer> Gcomp=FermetureTransitive.Composition(G,G);
		EnsembleArc<Integer> Gammacomp=new EnsembleArc<Integer>();
		Gammacomp.add(a13);
		Gammacomp.add(a22);
		Gammacomp.add(a24);
		Gammacomp.add(a33);
		Gammacomp.add(a34);
		Gammacomp.add(a44);
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
