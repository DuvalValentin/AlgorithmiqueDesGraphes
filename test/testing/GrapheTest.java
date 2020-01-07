package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import factory.Factory;
import graphelements.interfaces.InterfaceArc;
import graphelements.interfaces.InterfaceEnsembleArcNonValue;
import graphelements.interfaces.InterfaceEnsembleSommet;
import graphelements.interfaces.InterfaceGrapheNonValue;
import graphelements.interfaces.InterfaceSommet;

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
		a44 = Factory.arcNonValue(s4,s4);
		Gamma=Factory.ensembleArcNonValue();
		Gamma.ajouteElement(a12);Gamma.ajouteElement(a23);Gamma.ajouteElement(a32);Gamma.ajouteElement(a34);Gamma.ajouteElement(a44);
		
		G=Factory.grapheNonValue(X, Gamma);
	}

	@Test
	public void testGraphe()
	{
		assertEquals(X,G.getEnsembleSommet(),"Test du constructeur et des getteurs");
		assertEquals(Gamma,G.getGamma(),"Test du constructeur et des getteurs");
		InterfaceGrapheNonValue<Integer> G1=Factory.grapheNonValue(G);
		assertEquals(G,G1,"Test du constructeur prenant un graphe en entrée");
		assertNotSame(G,G1,"On vérifie que les deux graphes n'ont pas la même réferrence");
	}
	
	@Test
	public void testGetters()
	{
		assertEquals(X,G.getEnsembleSommet(),"Test du getX");
		assertNotSame(X,G.getEnsembleSommet(),"Test de l'encapsulation de getX");
		assertEquals(Gamma,G.getGamma(),"Test du getGamma");
		assertNotSame(Gamma,G.getGamma(),"Test de l'encapsulation de getGamma");
	}
	
	@Test
	public void testExistSommet()
	{
		InterfaceSommet<Integer> es=Factory.sommet(100);
		assertTrue(G.existeSommet(s1),"Test de existSommet pour un sommet existant");
		assertFalse(G.existeSommet(es),"Test de existSommet pour un sommet non existant");
	}
	
	@Test
	public void testExistArc()
	{
		InterfaceArc<Integer> ea = Factory.arcNonValue(s4,s1);
		assertTrue(G.existeArc(a12),"Test de existArc pour un arc existant");
		assertTrue(G.existeArc(s4,s4),"Test de existArc avec 2 sommet en paramètres pour un arc existant");
		assertFalse(G.existeArc(ea),"Test de existArc pour un arc non existant");
		assertFalse(G.existeArc(s4,s1),"Test de existArc avec 2 sommet en paramètres pour un arc non existant");
	}
	
	@Test
	public void testExisteBoucle()
	{
		assertTrue(G.existeBoucle(),"Test de existeBoucle pour une boucle existante");
		assertTrue(G.existeBoucle(s4),"Test de existeBoucle avec un sommet en paramètre pour une boucle existante");
		assertFalse(G.existeBoucle(s1),"Test de existeBoucle avec un sommet en paramètre pour une boucle non existante");
		G.supprArc(a44);
		assertFalse(G.existeBoucle(),"Test de existeBoucle pour une boucle non existante");
	}
	
	@Test
	public void testListSuccPred()
	{
		assertEquals(Gamma.listSucc(s1),G.listSucc(s1),"Test de listSucc sur un graphe");
		assertEquals(Gamma.listPred(s4),G.listPred(s4),"Test de listPred sur un graphe");
	}
	
	@Test
	public void testFirstSommet()
	{
		assertEquals(s1,G.pickSommet());
	}
	
	@Test
	public void testAjouteSommet()
	{
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
		InterfaceSommet<Integer> s6 = Factory.sommet(5);
		G.ajouteSommet(s5);
		G.ajouteSommet(6);
		assertTrue(G.existeSommet(s5),"Test du ajouteSommet sur un graphe");
		assertTrue(G.existeSommet(s6),"Test du ajouteSommet sur un graphe");
	}
	
	@Test 
	public void testSupprSommet()
	{
		G.supprSommet(s2);
		assertFalse(G.existeSommet(s2),"Test de la suppression de sommet de suprrSommet");
		assertFalse(G.existeArc(a12)||G.existeArc(a23)||G.existeArc(a32),"Test de la suppression des arcs liés au sommet de suprrSommet");
	}
	
	@Test
	public void testAjouteArc()
	{
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
		InterfaceArc<Integer> a51 = Factory.arcNonValue(s5, s1);
		InterfaceArc<Integer> a41 = Factory.arcNonValue(s4, s1);
		G.ajouteArc(a51);
		G.ajouteArc(4,1);
		G.ajouteArc(s3, s1);
		G.ajouteArc(a32);
		assertTrue(G.existeArc(a41),"Test de l'ajout d'un arc ajoutable");
		assertFalse(G.existeArc(a51),"Test de l'ajout d'un arc non ajoutable");
		assertTrue(G.existeArc(s3,s1),"Test de l'ajout d'un arc avec 2 sommets en paramètres");
	}
	
	@Test
	public void testSupprArc()
	{
		G.supprArc(a34);
		assertFalse(G.existeArc(a34),"Test de la suppression d'un arc");
		G.supprArc(s3,s2);
		assertFalse(G.existeArc(s3,s2),"Test de la suppression d'un arc avec deux sommets en paramètres");
	}
	
	@Test
	public void testIsEmpty()
	{
		InterfaceGrapheNonValue<Integer> Ge = Factory.grapheNonValue();
		assertTrue(Ge.isEmpty(),"Test de isEmpty et du constructeur sans paramètres");
		Ge.ajouteSommet(s1);
		assertFalse(Ge.isEmpty());
	}
	
	@Test
	public void testCorrectGamma()
	{
		InterfaceGrapheNonValue<Integer> graphe;
		InterfaceEnsembleSommet<Integer> x = Factory.ensembleSommet();
		x.ajouteElement(s1);x.ajouteElement(s2);
		InterfaceEnsembleArcNonValue<Integer> gamma = Factory.ensembleArcNonValue();
		gamma.ajouteElement(a12);gamma.ajouteElement(a23);
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
		Pe.ajouteElement(s1);
		assertEquals(Pe,G.pointsEntree(),"Test de pointsEntree");
	}
	
	@Test
	public void testPointsSortie()
	{
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
		G.ajouteSommet(s5);
		G.ajouteArc(s4, s5);
		InterfaceEnsembleSommet<Integer> Ps=Factory.ensembleSommet();
		Ps.ajouteElement(s5);
		assertEquals(Ps,G.pointsSortie(),"Test de pointsSortie");
	}
	
	@Test
	public void testUnion()
	{
		InterfaceSommet<Integer> s5 = Factory.sommet(5);
		InterfaceArc<Integer> a15 = Factory.arcNonValue(s1,s5);
		InterfaceGrapheNonValue<Integer> Gajout=Factory.grapheNonValue();
		Gajout.ajouteSommet(s1); Gajout.ajouteSommet(s5);
		Gajout.ajouteArc(a15);
		Gajout =InterfaceGrapheNonValue.union(Gajout, G);
		InterfaceGrapheNonValue<Integer> Gunion=Factory.grapheNonValue(G);
		Gunion.ajouteSommet(s5);
		Gunion.ajouteArc(a15);
		assertEquals(Gunion,Gajout,"Test de l'union");
	}
	
	
	@Test
	public void encapsulation()
	{
		//Graphe<Integer> Gbis=new Graphe<Integer>(G);
		X.supprElement(s3);
		assertNotEquals(X,G.getEnsembleSommet(),"On modifie le X ayant initialisé G");
		Gamma.supprElement(a32);
		assertNotEquals(Gamma,G.getGamma(),"On modifie le Gamma ayant initialisé G");
		InterfaceEnsembleSommet<Integer> Xg =G.getEnsembleSommet();
		Xg.supprElement(s3);
		assertNotEquals(Xg,G.getEnsembleSommet(),"On modifie l'ensemble donné par getX");
		InterfaceEnsembleArcNonValue<Integer> Gammag = G.getGamma();
		Gammag.supprElement(a32);
		assertNotEquals(Gammag,G.getGamma(),"On modifie l'ensemble donné par getGamma");
		s4=Factory.sommet(6);
		assertFalse(G.existeSommet(s4),"On modifie un des sommets par l'exterieur");
		s3.setId(9);
		assertFalse(G.existeSommet(s3),"On modifie un des sommets par l'exterieur");
		InterfaceGrapheNonValue<Integer>G2=Factory.grapheNonValue(G);
		G2.supprArc(a12);
		assertNotEquals(G,G2,"On modifie le graphe créé à partir de G");
		a32.setDepart(s4);
		assertFalse(G.existeArc(a32),"On modifie un arc par l'exterieur");
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