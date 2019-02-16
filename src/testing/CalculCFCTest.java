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
	private Arc<Integer>a41;
	
	private GrapheNonValue<Integer>G1;
	private CFC<Integer> CFCres1;
	private EnsembleSommet<Integer> ES23;

	private GrapheNonValue<Integer>G2;
	private CFC<Integer> CFCres2;
	private EnsembleSommet<Integer> ES1234;
	
	
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
		
		G1=new GrapheNonValue<Integer>();
		
		G1.ajouteSommet(s1);G1.ajouteSommet(s2);G1.ajouteSommet(s3);G1.ajouteSommet(s4);
		G1.ajouteArc(a12);G1.ajouteArc(a23);G1.ajouteArc(a32);G1.ajouteArc(a34);
		
		CFCres1=new CFC<Integer>(G1.getX());
		ES23=new EnsembleSommet<Integer>();
		ES23.ajouteSommet(s2);ES23.ajouteSommet(s3);
		CFCres1.replace(s2, ES23);CFCres1.replace(s3, ES23);
		
		G2=new GrapheNonValue<Integer>();
		a41=new Arc<Integer>(s4,s1);
		G2.ajouteSommet(s1);G2.ajouteSommet(s2);G2.ajouteSommet(s3);G2.ajouteSommet(s4);
		
		G2.ajouteArc(a12);G2.ajouteArc(a23);G2.ajouteArc(a34);G2.ajouteArc(a41);
		
		CFCres2=new CFC<Integer>(G2.getX());
		ES1234=new EnsembleSommet<Integer>();
		ES1234.ajouteSommet(s1);ES1234.ajouteSommet(s2);ES1234.ajouteSommet(s3);ES1234.ajouteSommet(s4);
		CFCres2.replace(s1, ES1234);CFCres2.replace(s2, ES1234);CFCres2.replace(s3, ES1234);CFCres2.replace(s4, ES1234);
		
	}

	@Test
	public void testFoulkes()
	{
		assertEquals("Calcul des CFC grâce à l'algorithme de Foulkes",CFCres1,CalculCFC.Foulkes(FermetureTransitive.Roy_Warshall(G1)));
		assertEquals("Calcul des CFC grâce à l'algorithme de Foulkes",CFCres2,CalculCFC.Foulkes(FermetureTransitive.Roy_Warshall(G2)));
	}
	@Test
	public void testTarjan()
	{
		assertEquals("Calcul des CFC grâce à l'algorithme de Tarjan",CFCres1,CalculCFC.TarjanDFS(G1));
		assertEquals("Calcul des CFC grâce à l'algorithme de Tarjan",CFCres2,CalculCFC.TarjanDFS(G2));
	}
}