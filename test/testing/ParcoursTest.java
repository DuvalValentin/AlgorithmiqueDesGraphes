package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import algorithme.Parcours;
import factory.Factory;
import graphelements.interfaces.*;

public class ParcoursTest
{
	private GrapheNonValue<Integer> G;
	private EnsembleArcNonValue<Integer> Gamma;
	private EnsembleSommet<Integer> X;
	private Sommet<Integer> s1, s2, s3, s4;
	private Arc<Integer> a12, a23, a32, a34, a33;
	private Cout c1;
	private GrapheValue<Integer> grapheValue;
	private EnsembleArcValue<Integer> gammaValue;
	private ArcValue<Integer> a121, a231, a321, a341, a331;

	@BeforeEach
	public void setUp()
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
		a33=Factory.arcNonValue(s3,s3);
		Gamma=Factory.ensembleArcNonValue();
		Gamma.ajouteElement(a12);
		Gamma.ajouteElement(a23);
		Gamma.ajouteElement(a32);
		Gamma.ajouteElement(a34);
		Gamma.ajouteElement(a33);
		G=Factory.grapheNonValue(X,Gamma);
		c1=Factory.cout(1);
		a121=Factory.arcValue(s1,s2,c1);
		a231=Factory.arcValue(s2,s3,c1);
		a321=Factory.arcValue(s3,s2,c1);
		a341=Factory.arcValue(s3,s4,c1);
		a331=Factory.arcValue(s3,s3,c1);
		gammaValue=Factory.ensembleArcValue();
		gammaValue.ajouteElement(a121);
		gammaValue.ajouteElement(a231);
		gammaValue.ajouteElement(a321);
		gammaValue.ajouteElement(a331);
		gammaValue.ajouteElement(a341);
		grapheValue=Factory.grapheValue(X,gammaValue);
	}
	@Test
	public void testDFS()
	{
		Parcours.dfs(G);
		Parcours.dfs(grapheValue);
	}
	@Test
	public void testWFS()
	{
		// Parcours.wfs(G,s1);
		// Parcours.wfs(grapheValue, s1);
	}
}
