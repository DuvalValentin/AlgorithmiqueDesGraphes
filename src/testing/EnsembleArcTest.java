package testing;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import factory.Factory;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArcNonValue;
import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceSommet;
//Comprends les test de AbstractEnsembleArc
public class EnsembleArcTest
{

	private InterfaceEnsembleArcNonValue<Integer> ensembleArcTest;
	private InterfaceEnsembleArcNonValue<Integer> ensembleEmpty;
	
	private InterfaceSommet<Integer> s1;
	private InterfaceSommet<Integer> s2;
	private InterfaceSommet<Integer> s3;
	
	private InterfaceArc<Integer> a11;
	private InterfaceArc<Integer> a12;
	private InterfaceArc<Integer> a21;
	private InterfaceArc<Integer> a22;
	private InterfaceArc<Integer> a23;
	
	@Before
	public void setup()
	{
		ensembleArcTest = Factory.ensembleArcNonValue();
		s1 = Factory.sommet(1);
		s2 = Factory.sommet(2);
		s3 = Factory.sommet(3);
		a11 = Factory.arcNonValue(s1, s1);
		a12 = Factory.arcNonValue(s1, s2);
		a21 = Factory.arcNonValue(s2, s1);
		a22 = Factory.arcNonValue(s2, s2);
		a23 = Factory.arcNonValue(s2, s3);
		
		ensembleArcTest.ajouteElement(a11);ensembleArcTest.ajouteElement(a12);ensembleArcTest.ajouteElement(a21);
		ensembleArcTest.ajouteElement(a22);ensembleArcTest.ajouteElement(a23);
		
		ensembleEmpty = Factory.ensembleArcNonValue();
	}

	@Test
	public void testEnsembleArc()
	{
		InterfaceEnsembleArcNonValue<Integer> ensembleArcClone=Factory.ensembleArcNonValue(ensembleArcTest);
		assertEquals("Le constructeur clone",ensembleArcTest,ensembleArcClone);
		assertNotSame("Le constructeur clone créé un objet identique",ensembleArcTest,ensembleArcClone);
		ensembleArcClone.supprElement(a22);
		assertNotEquals("Modifier le clone modifie l'original",ensembleArcTest,ensembleArcClone);
	}
	
	@Test 
	public void testGetEnsemble()
	{
		HashSet<InterfaceArc<Integer>> ensemble = new HashSet<InterfaceArc<Integer>>();
		ensemble.add(a11);ensemble.add(a12);ensemble.add(a21);ensemble.add(a22);ensemble.add(a23);
		assertEquals(ensemble,ensembleArcTest.getEnsemble());
	}
	
	@Test 
	public void testListSuccPred()
	{
		InterfaceEnsembleSommet<Integer> succ1 = Factory.ensembleSommet();
		succ1.ajouteElement(s2);succ1.ajouteElement(s1);
		InterfaceEnsembleSommet<Integer> pred3 = Factory.ensembleSommet();
		pred3.ajouteElement(s2);
		assertEquals("listSucc",succ1,ensembleArcTest.listSucc(s1));
		assertEquals("listPred",pred3,ensembleArcTest.listPred(s3));	
	}
	
	@Test
	public void testAjouteSupprExist()
	{
		InterfaceEnsembleArcNonValue<Integer> sommetAjout =Factory.ensembleArcNonValue();
		assertTrue("Les arcs s'ajoutent bien",ensembleArcTest.existeArc(s1,s2));
		assertTrue("Test de présence de boucle lorsqu'elle existe",ensembleArcTest.existeBoucle());
		assertTrue("Test de présence de boucle avec un sommet en paramètre lorsqu'elle existe",ensembleArcTest.existeBoucle(s2));
		assertFalse("Test de présence de boucle avec un sommet en paramètre lorsqu'elle n'existe pas",ensembleArcTest.existeBoucle(s3));
		sommetAjout.ajouteElement(s1,s1);sommetAjout.ajouteElement(1,2);sommetAjout.ajouteElement(s2,s1);sommetAjout.ajouteElement(2,2);sommetAjout.ajouteElement(s2,s3);
		sommetAjout.ajouteElement(1,1);//ajouter un arc déja existant ne pose pas de problème
		assertTrue("Les arcs s'ajoutent bien",sommetAjout.existeArc(a12));
		assertTrue("Les arcs s'ajoutent bien",sommetAjout.existeArc(a22));
		assertTrue("Les arcs s'ajoutent bien",sommetAjout.existeArc(a23));
		assertEquals("Les deux ajouteElement ont un effet différent",sommetAjout,ensembleArcTest);
		ensembleArcTest.supprElement(a11);ensembleArcTest.supprElement(s2, s2);
		assertFalse("Arc supposé être enlevé",ensembleArcTest.existeArc(a22));
		assertFalse("Arc supposé être enlevé",ensembleArcTest.existeArc(s1,s1));
		ensembleArcTest.supprElement(a11);//enlever un arc déjà enlever ne pose pas d'erreur
		assertFalse("Test de présence de boucle lorsqu'elle n'existe pas",ensembleArcTest.existeBoucle());
	}
	
	@Test
	public void testUnion()
	{
		InterfaceEnsembleArcNonValue<Integer> ajout = Factory.ensembleArcNonValue(ensembleArcTest);
		ajout.ajouteElement(s3, s2);ajout.ajouteElement(s3, s1);
		InterfaceEnsembleArcNonValue<Integer> copie = Factory.ensembleArcNonValue(ensembleArcTest);
		InterfaceEnsembleArcNonValue<Integer> unionVide = InterfaceEnsembleArcNonValue.union(ensembleArcTest, ensembleEmpty);
		assertEquals(copie,unionVide);
		copie.ajouteElement(s3,s2);copie.ajouteElement(s3,s1);
		InterfaceEnsembleArcNonValue<Integer> union = InterfaceEnsembleArcNonValue.union(ensembleArcTest, ajout);
		assertEquals(copie,union);
	}
	
	@Test
	public void testIntersection()
	{
		InterfaceEnsembleArcNonValue<Integer> copie = Factory.ensembleArcNonValue(ensembleArcTest);
		copie.supprElement(a22);copie.supprElement(a11);copie.ajouteElement(s3, s3);
		InterfaceEnsembleArcNonValue<Integer> intersection  = InterfaceEnsembleArcNonValue.intersection(ensembleArcTest,copie);
		copie.supprElement(s3,s3);
		assertEquals(copie,intersection);
		InterfaceEnsembleArcNonValue<Integer> intersectionVide =InterfaceEnsembleArcNonValue.intersection(ensembleArcTest, ensembleEmpty);
		assertEquals(ensembleEmpty,intersectionVide);
	}
	
	@Test 
	public void testEncapsulation()
	{
		s3.setId(9);
		assertFalse("Modification d'un sommet depuis l'exterieur",ensembleArcTest.existeArc(s2,s3));
		assertTrue("Modification d'un sommet depuis l'exterieur",ensembleArcTest.existeArc(a23));
		a12.setArrivee(s3);
		assertFalse("Modification d'un arc depuis l'exterieur",ensembleArcTest.existeArc(a12));
		
	}
	
	@Test
	public void testToString()
	{
		String string ="{"+a11.toString()+","+a12.toString()+","+a21.toString()+","+a22.toString()+","+a23.toString()+"}";
		assertEquals(string,ensembleArcTest.toString());
		assertEquals("{}",ensembleEmpty.toString());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals()
	{
		assertFalse(ensembleArcTest.equals(ensembleEmpty));
		assertFalse(ensembleArcTest.equals("qkjvbjqhgr"));
		assertFalse(ensembleArcTest.equals(null));
	}
	
	@Test
	public void testIsEmpty()
	{
		assertFalse(ensembleArcTest.isEmpty());
		assertTrue(ensembleEmpty.isEmpty());
	}
	
	@Test
	public void testClear()
	{
		ensembleArcTest.clear();
		assertTrue(ensembleArcTest.isEmpty());
	}
	@Test
	public void testHashCode()
	{
		int somme=0;
		somme+=a11.hashCode();somme+=a12.hashCode();somme+=a21.hashCode();somme+=a22.hashCode();somme+=a23.hashCode();
		assertEquals(somme,ensembleArcTest.hashCode());
	}
}