package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import graphElements.Arc;
import graphElements.EnsembleArc;
import graphElements.EnsembleSommet;
import graphElements.Graphe;
import graphElements.Sommet;

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
	public void setUp() throws Exception
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		X=new EnsembleSommet<Integer>();
		X.add(s1);X.add(s2);X.add(s3);X.add(s4);
		
		a12 = new Arc<Integer>(s1,s2);
		a23 = new Arc<Integer>(s2,s3);
		a32 = new Arc<Integer>(s3,s2);
		a34 = new Arc<Integer>(s3,s4);
		a44 = new Arc<Integer>(s4,s4);
		Gamma=new EnsembleArc<Integer>();
		Gamma.add(a12);Gamma.add(a23);Gamma.add(a32);Gamma.add(a34);Gamma.add(a44);
		
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
	public void testClone()
	{
		Graphe<Integer> Clone =G.clone();
		assertEquals("Test du clonage même variable à l'interieur",Clone,G);
		assertNotSame("Test du clonage différent pointeur",Clone,G);
	}
	/* commenté tant que les setters sont privés
	@Test
	public void testSetX()
	{
		EnsembleSommet<Integer> xSet=new EnsembleSommet<Integer>();
		xSet.add(s1);xSet.add(s2);xSet.add(s3);
		G.setX(xSet);
		assertEquals("Test de setX",xSet,G.getX());
	}
	
	@Test
	public void testSetGamma()
	{
		EnsembleArc<Integer> gammaSet=new EnsembleArc<Integer>();
		gammaSet.add(a12);gammaSet.add(a44);gammaSet.add(a32);
		G.setGamma(gammaSet);
		assertEquals("Test de setX",gammaSet,G.getGamma());
	}
	*/
	
	@Test
	public void testExistSommet()
	{
		Sommet<Integer> es=new Sommet<Integer>(100);
		assertTrue("Test de existSommet pour un sommet existant",G.existSommet(s1));
		assertFalse("Test de existSommet pour un sommet non existant",G.existSommet(es));
	}
	
	@Test
	public void testExistArc()
	{
		Arc<Integer> ea = new Arc<Integer>(s4,s1);
		assertTrue("Test de existArc pour un arc existant",G.existArc(a12));
		assertTrue("Test de existArc pour un arc existant",G.existArc(s4,s4));
		assertFalse("Test de existArc pour un arc non existant",G.existArc(ea));
		assertFalse("Test de existArc pour un arc non existant",G.existArc(s4,s1));
	}
	
	@Test
	public void testExisteBoucle()
	{
		assertTrue("Test de existBoucle pour une boucle existante",G.existeBoucle(s4));
		assertFalse("Test de existBoucle pour une boucle existante",G.existeBoucle(s1));
	}
	
	@Test
	public void testListSuccPred()
	{
		assertEquals("Test de listSucc sur un graphe",Gamma.listSucc(s1),G.listSucc(s1));
		assertEquals("Test de listPred sur un graphe",Gamma.listPred(s4),G.listPred(s4));
	}
	
	//TODO créer une fonction contains dans G pour savoir si un ensemble/sommet/arc existe dans G
	@Test
	public void testAjouteSommet()
	{
		Sommet<Integer> s5 = new Sommet<Integer>(5);
		G.ajouteSommet(s5);
		assertTrue("Test du ajouteSommet sur un graphe",G.existSommet(s5));
	}
	
	@Test 
	public void testSupprSommet()
	{
		G.supprSommet(s2);
		assertFalse("Test de la suppression de sommet de suprrSommet",G.getX().contains(s2));
		assertFalse("Test de la suppression des arcs liés au sommet de suprrSommet",G.getGamma().contains(a12)||G.getGamma().contains(a23)||G.getGamma().contains(a32));
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
		assertTrue("Test de l'ajout d'un arc ajoutable",G.existArc(a41));
		assertFalse("Test de l'ajout d'un arc non ajoutable",G.existArc(a51));
		assertTrue("Test de l'ajout d'un arc avec 2 sommets en paramètres",G.existArc(s3, s1));
	}
	
	@Test
	public void testSupprArc()
	{
		G.supprArc(a34);
		assertFalse("Test de la suppression d'un arc",G.existArc(a34));
	}
	
	@Test
	public void encapsulation()
	{
		X.remove(s3);
		assertNotEquals("On modifie le X ayant initialisé G", X, G.getX());
		Gamma.remove(a32);
		assertNotEquals("On modifie le Gamma ayant initialisé G",Gamma,G.getGamma());
		EnsembleSommet<Integer> Xg =G.getX();
		Xg.remove(s3);
		assertNotEquals("On modifie l'ensemble donné par getX",Xg,G.getX());
		EnsembleArc<Integer> Gammag =(EnsembleArc<Integer>) G.getGamma();//TODO cast bizzare
		Gammag.remove(a23);
		assertNotEquals("On modifie l'ensemble donné par getGamma",Gammag,G.getGamma());
		s4=new Sommet<Integer>(6);
		assertFalse("On modifie un des sommets par l'exterieur",G.getX().contains(s4));
	}
}