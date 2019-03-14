package testing;

import org.junit.Before;
import org.junit.Test;

import algorithme.Parcours;
import factory.Factory;
import graphElements.Interfaces.*;

public class ParcoursTest
{
	private InterfaceGrapheNonValue<Integer> G;
	private InterfaceEnsembleArcNonValue<Integer>Gamma;
	private InterfaceEnsembleSommet<Integer> X;
	private InterfaceSommet<Integer> s1,s2,s3,s4;
	private InterfaceArc<Integer> a12,a23,a32,a34,a33;

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
		a33 = Factory.arcNonValue(s3,s3);
		Gamma=Factory.ensembleArcNonValue();
		Gamma.ajouteArc(a12);Gamma.ajouteArc(a23);Gamma.ajouteArc(a32);Gamma.ajouteArc(a34);Gamma.ajouteArc(a33);
		
		G=Factory.grapheNonValue(X,Gamma);
	}
	@Test
	public void testDFS() throws Exception
	{
		Parcours.DFS(G);
	}

	@Test
	public void testWFS()
	{
		Parcours.WFS(G,s1);
	}
}
