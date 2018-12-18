package testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import graphElements.Arc;
import graphElements.EnsembleArc;
import graphElements.EnsembleSommet;
import graphElements.Sommet;

public class EnsembleArcTest
{
	private EnsembleArc<Integer> ensembleArcTest;
	
	private Sommet<Integer> s1;
	private Sommet<Integer> s2;
	private Sommet<Integer> s3;
	
	Arc<Integer> a11;
	Arc<Integer> a12;
	Arc<Integer> a21;
	Arc<Integer> a22;
	Arc<Integer> a23;
	
	@Before
	public void setup()
	{
		ensembleArcTest = new EnsembleArc<Integer>();
		s1 = new Sommet<Integer>(1);
		s2 = new Sommet<Integer>(2);
		s3 = new Sommet<Integer>(3);
		a11 = new Arc<Integer>(s1,s1);
		a12 = new Arc<Integer>(s1,s2);
		a21 = new Arc<Integer>(s2,s1);
		a22 = new Arc<Integer>(s2,s2);
		a23 = new Arc<Integer>(s2,s3);
	}

	@Test
	public void testEnsembleArc()
	{
		EnsembleArc<Integer> ensembleArc=new EnsembleArc<Integer>(ensembleArcTest);
		assertEquals("Le constructeur à argument de EnsembleArc ne marche pas",ensembleArcTest,ensembleArc);
	}
	
	@Test
	public void testClone()
	{
		EnsembleArc<Integer> ensembleArc = ensembleArcTest.clone();
		assertEquals("La méthode clone de EnsembleArc ne marche pas",ensembleArcTest,ensembleArc);
		ensembleArc.add(new Arc<Integer>(new Sommet<Integer>(1),new Sommet<Integer>(2)));
		assertNotEquals("La méthode clone de EnsembleArc ne créé pas un nouvel objet",ensembleArcTest,ensembleArc);
	}
	
	@Test 
	public void testListSuccPred()
	{
		
		ensembleArcTest.add(a11);ensembleArcTest.add(a12);ensembleArcTest.add(a21);ensembleArcTest.add(a22);ensembleArcTest.add(a23);
		EnsembleSommet<Integer> succ1 = new EnsembleSommet<Integer>();
		succ1.add(s2);succ1.add(s1);
		EnsembleSommet<Integer> pred3 = new EnsembleSommet<Integer>();
		pred3.add(s2);
		assertEquals("la méthode listSucc de EnsembleArc ne marche pas",ensembleArcTest.listSucc(s1),succ1);
		assertEquals("la méthode listPred de EnsembleArc ne marche pas",ensembleArcTest.listPred(s3),pred3);	
	}
	
	@Test
	public void testAdd()
	{
		ensembleArcTest.add(s1,s2);ensembleArcTest.add(s2,s2);ensembleArcTest.add(s2,s3);
		
		EnsembleArc<Integer> normalAdd = new EnsembleArc<Integer>();
		normalAdd.add(a12);normalAdd.add(a22);normalAdd.add(a23);
		
		assertEquals("Le add avec 2 somment en paramètre ne marche pas",normalAdd,ensembleArcTest);
	}
}
