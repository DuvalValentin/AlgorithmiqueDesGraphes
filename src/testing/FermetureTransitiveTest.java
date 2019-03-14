package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algorithme.FermetureTransitive;
import factory.Factory;
import graphElements.Interfaces.*;

public class FermetureTransitiveTest
{
	private InterfaceGrapheNonValue<Integer> G, GT;
	
	private InterfaceEnsembleArcNonValue<Integer>Gamma, GammaT;
	private InterfaceEnsembleSommet<Integer> X;
	private InterfaceSommet<Integer> s1,s2,s3,s4;
	private InterfaceArc<Integer> a12,a23,a32,a34,a44;
	
	private InterfaceArc<Integer> a13,a14,a22,a24,a33;

	@Before
	public void setUp() throws Exception
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		X=Factory.ensembleSommet();
		X.ajouteSommet(s1);X.ajouteSommet(s2);X.ajouteSommet(s3);X.ajouteSommet(s4);
		
		a12 = Factory.arcNonValue(s1,s2);
		a23 = Factory.arcNonValue(s2,s3);
		a32 = Factory.arcNonValue(s3,s2);
		a34 = Factory.arcNonValue(s3,s4);
		a44 = Factory.arcNonValue(s4,s4);
		Gamma=Factory.ensembleArcNonValue();
		Gamma.ajouteArc(a12);Gamma.ajouteArc(a23);Gamma.ajouteArc(a32);Gamma.ajouteArc(a34);Gamma.ajouteArc(a44);
		
		G=Factory.grapheNonValue(X, Gamma);
		
		a13 = Factory.arcNonValue(s1,s3);
		a14 = Factory.arcNonValue(s1,s4);
		a22 = Factory.arcNonValue(s2,s2);
		a24 = Factory.arcNonValue(s2,s4);
		a33 = Factory.arcNonValue(s3,s3);
		GammaT=Factory.ensembleArcNonValue(Gamma);
		GammaT.ajouteArc(a13);GammaT.ajouteArc(a14);GammaT.ajouteArc(a22);GammaT.ajouteArc(a24);GammaT.ajouteArc(a33);
		GT=Factory.grapheNonValue(X, GammaT);
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
		InterfaceGrapheNonValue<Integer> PG = FermetureTransitive.PuissanceDeGraphe(G);
		assertEquals("La puissance de graphe ne marche pas",GT,PG);
	}
	@Test
	public void testRoy_Warshall()
	{
		InterfaceGrapheNonValue<Integer>RW=FermetureTransitive.Roy_Warshall(G);
		assertEquals("Roy_Warshall ne marche pas",GT,RW);
	}
}
