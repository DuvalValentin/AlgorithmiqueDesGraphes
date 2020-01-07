package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithme.Parcours;
import factory.Factory;
import graphelements.interfaces.*;

public class ParcoursTest
{
	private InterfaceGrapheNonValue<Integer> G;
	private InterfaceEnsembleArcNonValue<Integer>Gamma;
	private InterfaceEnsembleSommet<Integer> X;
	private InterfaceSommet<Integer> s1,s2,s3,s4;
	private InterfaceArc<Integer> a12,a23,a32,a34,a33;

	@BeforeEach
	public void setUp()
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		X=Factory.ensembleSommet();
		X.ajouteElement(s1);X.ajouteElement(s2);X.ajouteElement(s3);X.ajouteElement(s4);
		
		a12 = Factory.arcNonValue(s1,s2);
		a23 = Factory.arcNonValue(s2,s3);
		a32 = Factory.arcNonValue(s3,s2);
		a34 = Factory.arcNonValue(s3,s4);
		a33 = Factory.arcNonValue(s3,s3);
		Gamma=Factory.ensembleArcNonValue();
		Gamma.ajouteElement(a12);Gamma.ajouteElement(a23);Gamma.ajouteElement(a32);Gamma.ajouteElement(a34);Gamma.ajouteElement(a33);
		
		G=Factory.grapheNonValue(X,Gamma);
	}
	@Test
	public void testDFS()
	{
		Parcours.dfs(G);
	}

	@Test
	public void testWFS()
	{
		Parcours.wfs(G,s1);
	}
}
