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
	private InterfaceGrapheValue<Integer> GV, GVT;
	
	private InterfaceEnsembleArcNonValue<Integer>Gamma, GammaT;
	private InterfaceEnsembleArcValue<Integer>GammaV, GammaVT;
	private InterfaceEnsembleSommet<Integer> X;
	private InterfaceSommet<Integer> s1,s2,s3,s4;
	private InterfaceArc<Integer> a12,a23,a32,a34,a44;
	private InterfaceArc<Integer> a13,a14,a22,a24,a33;
	private InterfaceCout c1,c2,c3,c5,c6;
	private InterfaceArcValue<Integer> a121,a232,a343;
	private InterfaceArcValue<Integer> a133,a146,a245;

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
		
		c1=Factory.cout(1);
		c2=Factory.cout(2);
		c3=Factory.cout(3);
		c5=Factory.cout(5);
		c6=Factory.cout(6);
		
		a121=Factory.arcValue(s1, s2, c1);
		a232=Factory.arcValue(s2, s3, c2);
		a343=Factory.arcValue(s3, s4, c3);
		GammaV=Factory.ensembleArcValue();
		GammaV.ajouteArc(a121);GammaV.ajouteArc(a232);GammaV.ajouteArc(a343);
		GV=Factory.grapheValue(Factory.grapheValue(X,GammaV));
		
		a133=Factory.arcValue(s1, s3, c3);
		a146=Factory.arcValue(s1, s4, c6);
		a245=Factory.arcValue(s2, s4, c5);
		GammaVT=Factory.ensembleArcValue(GammaV);
		GammaVT.ajouteArc(a133);GammaVT.ajouteArc(a146);GammaVT.ajouteArc(a245);
		GVT=Factory.grapheValue(Factory.grapheValue(X,GammaVT));
		
		
		
		
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
		InterfaceGrapheNonValue<Integer> PG = (InterfaceGrapheNonValue<Integer>) FermetureTransitive.PuissanceDeGraphe(G);
		assertEquals("La puissance de graphe ne marche pas",GT,PG);
	}
	@Test
	public void testRoy_Warshall()
	{
		InterfaceGrapheNonValue<Integer>RW=(InterfaceGrapheNonValue<Integer>)FermetureTransitive.Roy_Warshall(G);
		assertEquals("Roy_Warshall ne marche pas",GT,RW);
		
		InterfaceGrapheValue<Integer>RWV=FermetureTransitive.Roy_Warshall(GV);
		assertEquals("Roy_Warshall valué ne marche pas",GVT,RWV);
	}
}
