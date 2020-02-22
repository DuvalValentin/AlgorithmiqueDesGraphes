package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import algorithme.FermetureTransitive;
import factory.Factory;
import graphelements.interfaces.*;

public class FermetureTransitiveTest
{
	private GrapheNonValue<Integer> G, GT;
	private GrapheValue<Integer> GV, GVT;
	private EnsembleArcNonValue<Integer> Gamma, GammaT;
	private EnsembleArcValue<Integer> GammaV, GammaVT;
	private EnsembleSommet<Integer> X;
	private Sommet<Integer> s1, s2, s3, s4;
	private Arc<Integer> a12, a23, a32, a34, a44;
	private Arc<Integer> a13, a14, a22, a24, a33;
	private Cout c1, c2, c3, c5, c6;
	private ArcValue<Integer> a121, a232, a343;
	private ArcValue<Integer> a133, a146, a245;

	@BeforeEach
	public void setUp() throws Exception
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		X=Factory.ensembleSommet();
		X.ajouteElement(s1);
		X.ajouteElement(s2);
		X.ajouteElement(s3);
		X.ajouteElement(s4);
		a12=Factory.arcNonValue(s1,s2);
		a23=Factory.arcNonValue(s2,s3);
		a32=Factory.arcNonValue(s3,s2);
		a34=Factory.arcNonValue(s3,s4);
		a44=Factory.arcNonValue(s4,s4);
		Gamma=Factory.ensembleArcNonValue();
		Gamma.ajouteElement(a12);
		Gamma.ajouteElement(a23);
		Gamma.ajouteElement(a32);
		Gamma.ajouteElement(a34);
		Gamma.ajouteElement(a44);
		G=Factory.grapheNonValue(X,Gamma);
		a13=Factory.arcNonValue(s1,s3);
		a14=Factory.arcNonValue(s1,s4);
		a22=Factory.arcNonValue(s2,s2);
		a24=Factory.arcNonValue(s2,s4);
		a33=Factory.arcNonValue(s3,s3);
		GammaT=Factory.ensembleArcNonValue(Gamma);
		GammaT.ajouteElement(a13);
		GammaT.ajouteElement(a14);
		GammaT.ajouteElement(a22);
		GammaT.ajouteElement(a24);
		GammaT.ajouteElement(a33);
		GT=Factory.grapheNonValue(X,GammaT);
		c1=Factory.cout(1);
		c2=Factory.cout(2);
		c3=Factory.cout(3);
		c5=Factory.cout(5);
		c6=Factory.cout(6);
		a121=Factory.arcValue(s1,s2,c1);
		a232=Factory.arcValue(s2,s3,c2);
		a343=Factory.arcValue(s3,s4,c3);
		GammaV=Factory.ensembleArcValue();
		GammaV.ajouteElement(a121);
		GammaV.ajouteElement(a232);
		GammaV.ajouteElement(a343);
		GV=Factory.grapheValue(Factory.grapheValue(X,GammaV));
		a133=Factory.arcValue(s1,s3,c3);
		a146=Factory.arcValue(s1,s4,c6);
		a245=Factory.arcValue(s2,s4,c5);
		GammaVT=Factory.ensembleArcValue(GammaV);
		GammaVT.ajouteElement(a133);
		GammaVT.ajouteElement(a146);
		GammaVT.ajouteElement(a245);
		GVT=Factory.grapheValue(Factory.grapheValue(X,GammaVT));
	}
	/*
	 * @Test public void testCompostion() { Graphe<Integer>
	 * Gcomp=FermetureTransitive.Composition(G,G); EnsembleArc<Integer>
	 * Gammacomp=new EnsembleArc<Integer>(); Gammacomp.ajouteElement(a13);
	 * Gammacomp.ajouteElement(a22); Gammacomp.ajouteElement(a24);
	 * Gammacomp.ajouteElement(a33); Gammacomp.ajouteElement(a34);
	 * Gammacomp.ajouteElement(a44);
	 * assertEquals("La composition ne marche pas",Gammacomp,Gcomp.getGamma());
	 * }//Commenté tant que composition est privé
	 */
	@Test
	public void testPuissanceGraphe()
	{
		GrapheNonValue<Integer> PG=(GrapheNonValue<Integer>)FermetureTransitive.puissanceDeGraphe(G);
		assertEquals(GT,PG,"La puissance de graphe ne marche pas");
		GrapheValue<Integer> PGV=(GrapheValue<Integer>)FermetureTransitive.puissanceDeGraphe(GV);
		assertEquals(GVT,PGV,"La puissance de graphe ne marche pas");
	}
	@Test
	public void testRoy_Warshall()
	{
		GrapheNonValue<Integer> RW=(GrapheNonValue<Integer>)FermetureTransitive.royWarshall(G);
		assertEquals(GT,RW,"Roy_Warshall ne marche pas");
		GrapheValue<Integer> RWV=(GrapheValue<Integer>)FermetureTransitive.royWarshall(GV);
		assertEquals(GVT,RWV,"Roy_Warshall valué ne marche pas");
	}
}
