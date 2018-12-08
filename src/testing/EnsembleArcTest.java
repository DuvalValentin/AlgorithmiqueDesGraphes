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
	@Before
	public void setup()
	{
		ensembleArcTest = new EnsembleArc<Integer>();
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
		Sommet<Integer> s1 = new Sommet<Integer>(1);
		Sommet<Integer> s2 = new Sommet<Integer>(2);
		Sommet<Integer> s3 = new Sommet<Integer>(3);
		Arc<Integer> a1 = new Arc<Integer>(s1,s1);
		Arc<Integer> a2 = new Arc<Integer>(s1,s2);
		Arc<Integer> a3 = new Arc<Integer>(s2,s1);
		Arc<Integer> a4 = new Arc<Integer>(s2,s2);
		Arc<Integer> a5 = new Arc<Integer>(s2,s3);
		ensembleArcTest.add(a1);ensembleArcTest.add(a2);ensembleArcTest.add(a3);ensembleArcTest.add(a4);ensembleArcTest.add(a5);
		EnsembleSommet<Integer> succ1 = new EnsembleSommet<Integer>();
		succ1.add(s2);succ1.add(s1);
		EnsembleSommet<Integer> pred3 = new EnsembleSommet<Integer>();
		pred3.add(s2);
		assertEquals("la méthode listSucc de EnsembleArc ne marche pas",ensembleArcTest.listSucc(s1),succ1);
		assertEquals("la méthode listPred de EnsembleArc ne marche pas",ensembleArcTest.listPred(s3),pred3);	
	}
}
