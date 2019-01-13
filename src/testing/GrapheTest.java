package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleArc;
import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Graphe;
import graphElements.Elements.Sommet;

public class GrapheTest
{
	private Graphe<Integer> G;
	private EnsembleArc<Integer>Gamma;
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
	
	@Before
	public void setUp()
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		X=new EnsembleSommet<Integer>();
		X.ajouteSommet(s1);X.ajouteSommet(s2);X.ajouteSommet(s3);X.ajouteSommet(s4);
		
		a12 = new Arc<Integer>(s1,s2);
		a23 = new Arc<Integer>(s2,s3);
		a32 = new Arc<Integer>(s3,s2);
		a34 = new Arc<Integer>(s3,s4);
		a44 = new Arc<Integer>(s4,s4);
		Gamma=new EnsembleArc<Integer>();
		Gamma.ajouteArc(a12);Gamma.ajouteArc(a23);Gamma.ajouteArc(a32);Gamma.ajouteArc(a34);Gamma.ajouteArc(a44);
		
		G=new Graphe<Integer>(X,Gamma);
	}

	@Test
	public void testGraphe()
	{
		assertEquals("Test du constructeur et des getteurs",X,G.getX());
		assertEquals("Test du constructeur et des getteurs",Gamma,G.getGamma());
		Graphe<Integer> G1=new Graphe<Integer>(G);
		assertEquals("Test du constructeur prenant un graphe en entrée",G,G1);
		assertNotSame("On vérifie que les deux graphes n'ont pas la même réferrence",G,G1);
	}
	
	@Test
	public void testGetters()
	{
		assertEquals("Test du getX",X,G.getX());
		assertNotSame("Test de l'encapsulation de getX",X,G.getX());
		assertEquals("Test du getGamma",Gamma,G.getGamma());
		assertNotSame("Test de l'encapsulation de getGamma",Gamma,G.getGamma());
	}
	
	@Test
	public void testExistSommet()
	{
		Sommet<Integer> es=new Sommet<Integer>(100);
		assertTrue("Test de existSommet pour un sommet existant",G.existeSommet(s1));
		assertFalse("Test de existSommet pour un sommet non existant",G.existeSommet(es));
	}
	
	@Test
	public void testExistArc()
	{
		Arc<Integer> ea = new Arc<Integer>(s4,s1);
		assertTrue("Test de existArc pour un arc existant",G.existeArc(a12));
		assertTrue("Test de existArc avec 2 sommet en paramètres pour un arc existant",G.existeArc(s4,s4));
		assertFalse("Test de existArc pour un arc non existant",G.existeArc(ea));
		assertFalse("Test de existArc avec 2 sommet en paramètres pour un arc non existant",G.existeArc(s4,s1));
	}
	
	@Test
	public void testExisteBoucle()
	{
		assertTrue("Test de existeBoucle pour une boucle existante",G.existeBoucle());
		assertTrue("Test de existeBoucle avec un sommet en paramètre pour une boucle existante",G.existeBoucle(s4));
		assertFalse("Test de existeBoucle avec un sommet en paramètre pour une boucle non existante",G.existeBoucle(s1));
		G.supprArc(a44);
		assertFalse("Test de existeBoucle pour une boucle non existante",G.existeBoucle());
	}
	
	@Test
	public void testListSuccPred()
	{
		assertEquals("Test de listSucc sur un graphe",Gamma.listSucc(s1),G.listSucc(s1));
		assertEquals("Test de listPred sur un graphe",Gamma.listPred(s4),G.listPred(s4));
	}
	
	@Test
	public void testAjouteSommet()
	{
		Sommet<Integer> s5 = new Sommet<Integer>(5);
		G.ajouteSommet(s5);
		assertTrue("Test du ajouteSommet sur un graphe",G.existeSommet(s5));
	}
	
	@Test 
	public void testSupprSommet()
	{
		G.supprSommet(s2);
		assertFalse("Test de la suppression de sommet de suprrSommet",G.existeSommet(s2));
		assertFalse("Test de la suppression des arcs liés au sommet de suprrSommet",G.existeArc(a12)||G.existeArc(a23)||G.existeArc(a32));
	}
	
	@Test
	public void testAjouteArc()
	{
		Sommet<Integer> s5 = new Sommet<Integer>(5);
		Arc<Integer> a51 = new Arc<Integer>(s5,s1);
		Arc<Integer> a41 = new Arc<Integer> (s4,s1);
		G.ajouteArc(a51);
		G.ajouteArc(a41);
		G.ajouteArc(s3, s1);
		assertTrue("Test de l'ajout d'un arc ajoutable",G.existeArc(a41));
		assertFalse("Test de l'ajout d'un arc non ajoutable",G.existeArc(a51));
		assertTrue("Test de l'ajout d'un arc avec 2 sommets en paramètres",G.existeArc(s3,s1));
	}
	
	@Test
	public void testSupprArc()
	{
		G.supprArc(a34);
		assertFalse("Test de la suppression d'un arc",G.existeArc(a34));
		G.supprArc(s3,s2);
		assertFalse("Test de la suppression d'un arc avec deux sommets en paramètres",G.existeArc(s3,s2));
	}
	
	@Test
	public void testIsEmpty()
	{
		Graphe<Integer> Ge = new Graphe<Integer>();
		assertTrue("Test de isEmpty et du constructeur sans paramètres",Ge.isEmpty());
	}
	
	@Test
	public void testPointsEntree()
	{
		EnsembleSommet<Integer> Pe=new EnsembleSommet<Integer>();
		Pe.ajouteSommet(s1);
		assertEquals("Test de pointsEntree",Pe,G.pointsEntree());
	}
	
	@Test
	public void testUnion()
	{
		Sommet<Integer> s5 = new Sommet<Integer>(5);
		Arc<Integer> a15 = new Arc<Integer>(s1,s5);
		Graphe<Integer> Gajout=new Graphe<Integer>();
		Gajout.ajouteSommet(s1); Gajout.ajouteSommet(s5);
		Gajout.ajouteArc(a15);
		Gajout.union(G);
		Graphe<Integer> Gunion=new Graphe<Integer>(G);
		Gunion.ajouteSommet(s5);
		Gunion.ajouteArc(a15);
		assertEquals("Test de l'union",Gunion,Gajout);
	}
	
	
	@Test
	public void encapsulation()
	{
		//Graphe<Integer> Gbis=new Graphe<Integer>(G);
		X.supprSommet(s3);
		assertNotEquals("On modifie le X ayant initialisé G", X, G.getX());
		Gamma.supprArc(a32);
		assertNotEquals("On modifie le Gamma ayant initialisé G",Gamma,G.getGamma());
		EnsembleSommet<Integer> Xg =G.getX();
		Xg.supprSommet(s3);
		assertNotEquals("On modifie l'ensemble donné par getX",Xg,G.getX());
		EnsembleArc<Integer> Gammag = G.getGamma();
		Gammag.supprArc(a32);
		assertNotEquals("On modifie l'ensemble donné par getGamma",Gammag,G.getGamma());
		s4=new Sommet<Integer>(6);
		assertFalse("On modifie un des sommets par l'exterieur",G.existeSommet(s4));
		s3.setId(9);
		assertFalse("On modifie un des sommets par l'exterieur",G.existeSommet(s3));
		Graphe<Integer>G2=new Graphe<Integer>(G);
		G2.supprArc(a12);
		assertNotEquals("On modifie le graphe créé à partir de G",G,G2);
		a32.setDepart(s4);
		assertFalse("On modifie un arc par l'exterieur",G.existeArc(a32));
	}
}