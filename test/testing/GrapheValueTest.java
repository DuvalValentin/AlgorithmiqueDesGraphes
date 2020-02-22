package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import factory.Factory;
import graphelements.interfaces.ArcValue;
import graphelements.interfaces.Cout;
import graphelements.interfaces.EnsembleArcValue;
import graphelements.interfaces.EnsembleSommet;
import graphelements.interfaces.GrapheValue;
import graphelements.interfaces.Sommet;

public class GrapheValueTest
{
	private Sommet<Integer> s1, s2, s3, s4;
	private EnsembleSommet<Integer> X;
	private ArcValue<Integer> a121, a135, a149, a232, a246, a343;
	private EnsembleArcValue<Integer> Gamma;
	private Cout c1, c2, c3, c5, c6, c9;
	private GrapheValue<Integer> GV, GV1, GV2;

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
		c1=Factory.cout(1);
		c2=Factory.cout(2);
		c3=Factory.cout(3);
		c5=Factory.cout(5);
		c6=Factory.cout(6);
		c9=Factory.cout(9);
		a121=Factory.arcValue(s1,s2,c1);
		a135=Factory.arcValue(s1,s3,c5);
		a149=Factory.arcValue(s1,s4,c9);
		a232=Factory.arcValue(s2,s3,c2);
		a246=Factory.arcValue(s2,s4,c6);
		a343=Factory.arcValue(s3,s4,c3);
		Gamma=Factory.ensembleArcValue();
		Gamma.ajouteElement(a121);
		Gamma.ajouteElement(a135);
		Gamma.ajouteElement(a149);
		Gamma.ajouteElement(a232);
		Gamma.ajouteElement(a246);
		Gamma.ajouteElement(a343);
		GV=Factory.grapheValue();
		GV1=Factory.grapheValue(X,Gamma);
		GV.ajouteSommet(s1);
		GV.ajouteSommet(s2);
		GV.ajouteSommet(s3);
		GV.ajouteSommet(s4);
		GV.ajouteArc(a121);
		GV.ajouteArc(a135);
		GV.ajouteArc(a149);
		GV.ajouteArc(a232);
		GV.ajouteArc(a246);
		GV.ajouteArc(a343);
		GV2=Factory.grapheValue(GV);
	}
	@Test
	public void testGrapheValue()
	{
		assertEquals(GV,GV1,"Test des constructeurs avec et sans paramètres");
		assertEquals(GV,GV2,"Test du constructeur avec un graphe en paramètre");
		assertNotSame(GV,GV2,"On vérifie que les deux objets sont différents");
		GV2.setValeur(s1,s2,c5);
		assertNotEquals(GV,GV2,"Modifier le clone modifie l'original");
	}
	@Test
	public void testGetGamma()
	{
		assertEquals(Gamma,GV.getGamma(),"GetGamma");
		assertNotSame(Gamma,GV1.getGamma(),"GetGamma renvoie bien un graphe différent");
	}
	@Test
	public void testGetCout()
	{
		Cout cout=GV.getCout(s3,s4).get();
		assertEquals(c3,cout,"GetCout");
		assertNotSame(c3,cout,"GetCout renvoie bien un cout différent");
	}
	@Test
	public void testAjouteSupprArc()
	{
		GV.ajouteArc(s2,s1,c5);
		ArcValue<Integer> av=Factory.arcValue(s2,s1,c5);
		assertFalse(GV.ajoutableArc(av),"L'ajout ne s'est pas fait");
		assertNotEquals(GV,GV2,"L'ajout a aussi été fait dans un autre graphe");
		assertEquals(c5,GV.getCout(s2,s1).get(),"L'arc ajouté n'a pas la bonne valeur");
		GV.supprArc(s2,s1);
		assertTrue(GV.ajoutableArc(av),"La suppression c'est effectuée");
	}
	@Test
	public void testSetValeur()
	{
		GV.setValeur(s1,s2,c9);
		assertEquals(c9,GV.getCout(s1,s2).get(),"Le cout n'a pas été modifié");
	}
	@Test
	public void testUnion()
	{
		Sommet<Integer> s5=Factory.sommet(5);
		GrapheValue<Integer> GVbis=Factory.grapheValue();
		GVbis.ajouteSommet(s2);
		GVbis.ajouteSommet(s3);
		GVbis.ajouteSommet(s4);
		GVbis.ajouteSommet(s5);
		GVbis.ajouteArc(s2,s3,c5);
		GVbis.ajouteArc(s3,s4,c1);
		GVbis.ajouteArc(s4,s5,c9);
		GVbis.ajouteArc(s3,s2,c5);
		GrapheValue<Integer> Gunion=Factory.grapheValue(GV);
		Gunion.ajouteSommet(s5);
		Gunion.ajouteArc(s3,s4,c1);
		Gunion.ajouteArc(s4,s5,c9);
		Gunion.ajouteArc(s3,s2,c5);
		assertEquals(Gunion,GrapheValue.union(GV,GVbis));
	}
	@Test
	public void testClone()
	{
		GrapheValue<Integer> clone=Factory.grapheValue(GV);
		assertEquals(GV,clone);
		assertNotSame(GV,clone);
	}
}