package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import factory.Factory;
import graphElements.Elements.GrapheNonValue;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArcNonValue;
import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceGrapheNonValue;
import graphElements.Interfaces.InterfaceSommet;

public class GrapheTest
{
	private InterfaceGrapheNonValue<Integer> G;
	private InterfaceEnsembleArcNonValue<Integer>Gamma;
	private InterfaceEnsembleSommet<Integer> X;
	private InterfaceSommet<Integer> s1;
	private InterfaceSommet<Integer> s2;
	private InterfaceSommet<Integer> s3;
	private InterfaceSommet<Integer> s4;
	private InterfaceArc<Integer> a12;
	private InterfaceArc<Integer> a23;
	private InterfaceArc<Integer> a32;
	private InterfaceArc<Integer> a34;
	private InterfaceArc<Integer> a44;
	
	@Before
	public void setUp()
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
	}

	@Test
	public void testGraphe()
	{
		assertEquals("Test du constructeur et des getteurs",X,G.getX());
		assertEquals("Test du constructeur et des getteurs",Gamma,G.getGamma());
		InterfaceGrapheNonValue<Integer> G1=Factory.grapheNonValue(G);
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
		InterfaceSommet<Integer> es=Factory.sommet(100);
		assertTrue("Test de existSommet pour un sommet existant",G.existeSommet(s1));
		assertFalse("Test de existSommet pour un sommet non existant",G.existeSommet(es));
	}
	
	@Test
	public void testExistArc()
	{
		InterfaceArc<Integer> ea = Factory.arcNonValue(s4,s1);
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
	public void testFirstSommet()
	{
		assertEquals(s1,G.firstSommet());
	}
	
	@Test
	public void testAjouteSommet()
	{
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
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
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
		InterfaceArc<Integer> a51 = Factory.arcNonValue(s5, s1);
		InterfaceArc<Integer> a41 = Factory.arcNonValue(s4, s1);
		G.ajouteArc(a51);
		G.ajouteArc(a41);
		G.ajouteArc(s3, s1);
		G.ajouteArc(a32);
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
		InterfaceGrapheNonValue<Integer> Ge = Factory.grapheNonValue();
		assertTrue("Test de isEmpty et du constructeur sans paramètres",Ge.isEmpty());
		Ge.ajouteSommet(s1);
		assertFalse(Ge.isEmpty());
	}
	
	@Test
	public void testCorrectGamma()
	{
		InterfaceGrapheNonValue<Integer> graphe;
		InterfaceEnsembleSommet<Integer> x = Factory.ensembleSommet();
		x.ajouteSommet(s1);x.ajouteSommet(s2);
		InterfaceEnsembleArcNonValue<Integer> gamma = Factory.ensembleArcNonValue();
		gamma.ajouteArc(a12);gamma.ajouteArc(a23);
		graphe=Factory.grapheNonValue(x, gamma);
		boolean erreur=false;
		try
		{
			graphe.getGamma();
		}
		catch(Exception e)
		{
			erreur=true;
		}
		assertTrue(erreur);
	}
	
	@Test
	public void testPointsEntree()
	{
		InterfaceEnsembleSommet<Integer> Pe=Factory.ensembleSommet();
		Pe.ajouteSommet(s1);
		assertEquals("Test de pointsEntree",Pe,G.pointsEntree());
	}
	
	@Test
	public void testPointsSortie()
	{
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
		G.ajouteSommet(s5);
		G.ajouteArc(s4, s5);
		InterfaceEnsembleSommet<Integer> Ps=Factory.ensembleSommet();
		Ps.ajouteSommet(s5);
		assertEquals("Test de pointsSortie",Ps,G.pointsSortie());
	}
	
	@Test
	public void testUnion()
	{
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
		InterfaceArc<Integer> a15 = Factory.arcNonValue(s1,s5);
		InterfaceGrapheNonValue<Integer> Gajout=Factory.grapheNonValue();
		Gajout.ajouteSommet(s1); Gajout.ajouteSommet(s5);
		Gajout.ajouteArc(a15);
		Gajout =(GrapheNonValue<Integer>) GrapheNonValue.union(Gajout, G);
		InterfaceGrapheNonValue<Integer> Gunion=Factory.grapheNonValue(G);
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
		InterfaceEnsembleSommet<Integer> Xg =G.getX();
		Xg.supprSommet(s3);
		assertNotEquals("On modifie l'ensemble donné par getX",Xg,G.getX());
		InterfaceEnsembleArcNonValue<Integer> Gammag = G.getGamma();
		Gammag.supprArc(a32);
		assertNotEquals("On modifie l'ensemble donné par getGamma",Gammag,G.getGamma());
		s4=Factory.sommet(6);
		assertFalse("On modifie un des sommets par l'exterieur",G.existeSommet(s4));
		s3.setId(9);
		assertFalse("On modifie un des sommets par l'exterieur",G.existeSommet(s3));
		InterfaceGrapheNonValue<Integer>G2=Factory.grapheNonValue(G);
		G2.supprArc(a12);
		assertNotEquals("On modifie le graphe créé à partir de G",G,G2);
		a32.setDepart(s4);
		assertFalse("On modifie un arc par l'exterieur",G.existeArc(a32));
	}
	
	@Test
	public void testToString()
	{
		String string ="["+X+" | "+Gamma+"]";
		assertEquals(string,G.toString());
	}
	
	@Test
	public void testHashCode()
	{
		int hash = X.hashCode()+Gamma.hashCode();
		assertEquals(hash,G.hashCode());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test 
	public void testEquals()
	{
		assertFalse(G.equals(null));
		assertFalse(G.equals("gjqhru"));
		InterfaceGrapheNonValue<Integer> graphe=Factory.grapheNonValue(G);
		graphe.supprSommet(s3);
		assertFalse(G.equals(graphe));
	}
}