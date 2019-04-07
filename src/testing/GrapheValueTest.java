package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import factory.Factory;
import graphElements.Interfaces.InterfaceArcValue;
import graphElements.Interfaces.InterfaceCout;
import graphElements.Interfaces.InterfaceEnsembleArcValue;
import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceGrapheValue;
import graphElements.Interfaces.InterfaceSommet;

public class GrapheValueTest
{
	private InterfaceSommet<Integer>s1,s2,s3,s4;
	private InterfaceEnsembleSommet<Integer>X;
	
	private InterfaceArcValue<Integer>a121,a135,a149,a232,a246,a343;
	private InterfaceEnsembleArcValue<Integer>Gamma;
	private InterfaceCout c1,c2,c3,c5,c6,c9;
	private InterfaceGrapheValue<Integer>GV,GV1,GV2;

	@Before
	public void setUp()
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		X=Factory.ensembleSommet();
		X.ajouteSommet(s1);X.ajouteSommet(s2);X.ajouteSommet(s3);X.ajouteSommet(s4);
		
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
		Gamma.ajouteArc(a121);Gamma.ajouteArc(a135);Gamma.ajouteArc(a149);Gamma.ajouteArc(a232);Gamma.ajouteArc(a246);Gamma.ajouteArc(a343);
		
		GV=Factory.grapheValue();
		GV1=Factory.grapheValue(X, Gamma);
		GV.ajouteSommet(s1);GV.ajouteSommet(s2);GV.ajouteSommet(s3);GV.ajouteSommet(s4);
		GV.ajouteArc(a121);GV.ajouteArc(a135);GV.ajouteArc(a149);GV.ajouteArc(a232);GV.ajouteArc(a246);GV.ajouteArc(a343);
		GV2=Factory.grapheValue(GV);
	}

	@Test
	public void testGrapheValue()
	{
		assertEquals("Test des constructeurs avec et sans paramètres",GV,GV1);
		assertEquals("Test du constructeur avec un graphe en paramètre",GV,GV2);
		assertNotSame("On vérifie que les deux objets sont différents",GV,GV2);
		GV2.setValeur(s1, s2, c5);
		assertNotEquals("Modifier le clone modifie l'original",GV,GV2);
	}

	@Test
	public void testGetGamma()
	{
		assertEquals("GetGamma",Gamma,GV.getGamma());
		assertNotSame("GetGamma renvoie bien un graphe différent",Gamma,GV1.getGamma());
	}

	@Test
	public void testGetCout()
	{
		
		InterfaceCout cout=GV.getCout(s3, s4).get();
		assertEquals("GetCout",c3,cout);
		assertNotSame("GetCout renvoie bien un cout différent",c3,cout);
	}

	@Test
	public void testAjouteSupprArc()
	{
		GV.ajouteArc(s2, s1, c5);
		InterfaceArcValue<Integer>av=Factory.arcValue(s2,s1,c5);
		assertFalse("L'ajout ne s'est pas fait",GV.ajoutableArc(av));
		assertNotEquals("L'ajout a aussi été fait dans un autre graphe",GV,GV2);
		assertEquals("L'arc ajouté n'a pas la bonne valeur",c5,GV.getCout(s2, s1).get());
		GV.supprArc(s2, s1);
		assertTrue("La suppression c'est effectuée",GV.ajoutableArc(av));
	}

	@Test
	public void testSetValeur()
	{
		GV.setValeur(s1, s2, c9);
		assertEquals("Le cout n'a pas été modifié",c9,GV.getCout(s1,s2).get());
	}
	
	@Test
	public void testUnion()
	{
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
		InterfaceGrapheValue<Integer> GVbis = Factory.grapheValue();
		GVbis.ajouteSommet(s2);GVbis.ajouteSommet(s3);GVbis.ajouteSommet(s4);GVbis.ajouteSommet(s5);
		GVbis.ajouteArc(s2,s3,c5);GVbis.ajouteArc(s3,s4,c1);GVbis.ajouteArc(s4, s5, c9);GVbis.ajouteArc(s3, s2, c5);
		InterfaceGrapheValue<Integer> Gunion = Factory.grapheValue(GV);
		Gunion.ajouteSommet(s5);
		Gunion.ajouteArc(s3, s4, c1);Gunion.ajouteArc(s4, s5, c9);Gunion.ajouteArc(s3, s2, c5);
		assertEquals(Gunion,InterfaceGrapheValue.union(GV, GVbis));
	}
	
	@Test
	public void testClone()
	{
		InterfaceGrapheValue<Integer> clone = Factory.grapheValue(GV);
		assertEquals(GV,clone);
		assertNotSame(GV,clone);
	}
}