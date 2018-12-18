package testing;

import org.junit.Before;
import org.junit.Test;

import algorithme.Parcours;
import graphElements.*;

public class ParcoursTest
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
	public void testDFS()
	{
		Parcours.DFS(G, s1);
	}

	@Test
	public void testWFS()
	{
		Parcours.WFS(G, s1);
	}
}
