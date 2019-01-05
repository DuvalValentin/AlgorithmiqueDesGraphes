package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import algorithme.CalculCFC;
import algorithme.FermetureTransitive;
import graphElements.Elements.*;

public class CalculCFCTest
{
	private Sommet<Integer> s1;
	private Sommet<Integer> s2;
	private Sommet<Integer> s3;
	private Sommet<Integer> s4;
	
	private Arc<Integer> a12;
	private Arc<Integer> a23;
	private Arc<Integer> a32;
	private Arc<Integer> a34;
	
	private Graphe<Integer>G;
	private CFC<Integer> CFCres;
	private EnsembleSommet<Integer> ES23;
	
	
	@Before
	public void setUp()
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		
		a12=new Arc<Integer>(s1,s2);
		a23=new Arc<Integer>(s2,s3);
		a32=new Arc<Integer>(s3,s2);
		a34=new Arc<Integer>(s3,s4);
		
		G=new Graphe<Integer>();
		
		G.ajouteSommet(s1);G.ajouteSommet(s2);G.ajouteSommet(s3);G.ajouteSommet(s4);
		G.ajouteArc(a12);G.ajouteArc(a23);G.ajouteArc(a32);G.ajouteArc(a34);
		
		CFCres=new CFC<Integer>(G.getX());
		ES23=new EnsembleSommet<Integer>();
		ES23.ajouteSommet(s2);ES23.ajouteSommet(s3);
		CFCres.replace(s2, ES23);CFCres.replace(s3, ES23);
	}

	@Test
	public void testFoulkes()
	{
		assertEquals("Calcul des CFC grâce à l'algorithme de Foulkes",CFCres,CalculCFC.Foulkes(FermetureTransitive.Roy_Warshall(G)));
	}
}