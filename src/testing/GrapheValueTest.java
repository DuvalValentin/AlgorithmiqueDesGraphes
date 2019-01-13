package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import graphElements.Elements.*;

public class GrapheValueTest
{
	private Sommet<Integer>s1;
	private Sommet<Integer>s2;
	private Sommet<Integer>s3;
	private Sommet<Integer>s4;
	private EnsembleSommet<Integer>X;
	
	private ArcValue<Integer>a121;
	private ArcValue<Integer>a135;
	private ArcValue<Integer>a149;
	private ArcValue<Integer>a232;
	private ArcValue<Integer>a246;
	private ArcValue<Integer>a343;
	private EnsembleArcValue<Integer>Gamma;
	private Cout c1;
	private Cout c2;
	private Cout c3;
	private Cout c5;
	private Cout c6;
	private Cout c9;
	
	
	private GrapheValue<Integer>GV;
	private GrapheValue<Integer>GV1;
	private GrapheValue<Integer>GV2;

	@Before
	public void setUp() 
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		X=new EnsembleSommet<Integer>();
		X.ajouteSommet(s1);X.ajouteSommet(s2);X.ajouteSommet(s3);X.ajouteSommet(s4);
		
		c1=new Cout(1);
		c2=new Cout(2);
		c3=new Cout(3);
		c5=new Cout(5);
		c6=new Cout(6);
		c9=new Cout(9);
		
		a121=new ArcValue<Integer>(s1,s2,c1);
		a135=new ArcValue<Integer>(s1,s3,c5);
		a149=new ArcValue<Integer>(s1,s4,c9);
		a232=new ArcValue<Integer>(s2,s3,c2);
		a246=new ArcValue<Integer>(s2,s4,c6);
		a343=new ArcValue<Integer>(s3,s4,c3);
		Gamma=new EnsembleArcValue<Integer>();
		Gamma.ajouteArc(a121);Gamma.ajouteArc(a135);Gamma.ajouteArc(a149);Gamma.ajouteArc(a232);Gamma.ajouteArc(a246);Gamma.ajouteArc(a343);
		
		GV=new GrapheValue<Integer>();
		GV1=new GrapheValue<Integer>(X,Gamma);
		GV.ajouteSommet(s1);GV.ajouteSommet(s2);GV.ajouteSommet(s3);GV.ajouteSommet(s4);
		GV.ajouteArc(a121);GV.ajouteArc(a135);GV.ajouteArc(a149);GV.ajouteArc(a232);GV.ajouteArc(a246);GV.ajouteArc(a343);
		GV2=new GrapheValue<Integer>(GV);
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
		
		Cout cout=GV.getCout(s3, s4);
		assertEquals("GetCout",c3,cout);
		assertNotSame("GetCout renvoie bien un cout différent",c3,cout);
	}

	@Test
	public void testAjouteSupprArc()
	{
		GV.ajouteArc(s2, s1, c5);
		ArcValue<Integer>av=new ArcValue<Integer>(s2,s1,c5);
		assertFalse("L'ajout ne s'est pas fait",GV.ajoutableArc(av));
		assertNotEquals("L'ajout a aussi été fait dans un autre graphe",GV,GV2);
		assertEquals("L'arc ajouté n'a pas la bonne valeur",c5,GV.getCout(s2, s1));
		GV.supprArc(s2, s1);
		assertTrue("La suppression c'est effectuée",GV.ajoutableArc(av));
	}

	@Test
	public void testSetValeur()
	{
		GV.setValeur(s1, s2, c9);
		assertEquals("Le cout n'a pas été modifié",c9,GV.getCout(s1,s2));
	}
}