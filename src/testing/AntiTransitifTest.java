package testing;

import static org.junit.Assert.*;
import algorithme.AntiTransitif;
import graphElements.Elements.*;

import org.junit.Before;
import org.junit.Test;

public class AntiTransitifTest
{
	private Sommet<Integer> s1;
	private Sommet<Integer> s2;
	private Sommet<Integer> s3;
	private Sommet<Integer> s4;
	
	private Arc<Integer> a12;
	private Arc<Integer> a13;
	private Arc<Integer> a14;
	private Arc<Integer> a23;
	private Arc<Integer> a24;
	private Arc<Integer> a34;
	
	private Graphe<Integer> Gini;
	private Graphe<Integer> Gres;

	@Before
	public void setUp()
	{
		s1=new Sommet<Integer>(1);
		s2=new Sommet<Integer>(2);
		s3=new Sommet<Integer>(3);
		s4=new Sommet<Integer>(4);
		
		a12=new Arc<Integer>(s1,s2);
		a13=new Arc<Integer>(s1,s3);
		a14=new Arc<Integer>(s1,s4);
		a23=new Arc<Integer>(s2,s3);
		a24=new Arc<Integer>(s2,s4);
		a34=new Arc<Integer>(s3,s4);
		
		Gres=new Graphe<Integer>();
		Gres.ajouteSommet(s1);Gres.ajouteSommet(s2);Gres.ajouteSommet(s3);Gres.ajouteSommet(s4);
		Gres.ajouteArc(a12);Gres.ajouteArc(a23);Gres.ajouteArc(a34);
		Gini=new Graphe<Integer>(Gres);
		Gini.ajouteArc(a13);Gini.ajouteArc(a14);Gini.ajouteArc(a24);
	}

	@Test
	public void testTauMinalite()
	{
		assertEquals("test de la TauMinalite pour un graphe sans circuits",Gres,AntiTransitif.TauMinalite(Gini));
	}
}
