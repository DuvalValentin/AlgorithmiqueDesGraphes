package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import factory.Factory;
import graphelements.interfaces.Arc;
import graphelements.interfaces.EnsembleArcNonValue;
import graphelements.interfaces.EnsembleSommet;
import graphelements.interfaces.Sommet;

//Comprends les test de AbstractEnsembleArc
public class EnsembleArcTest
{
	private EnsembleArcNonValue<Integer> ensembleArcTest;
	private EnsembleArcNonValue<Integer> ensembleEmpty;
	private Sommet<Integer> s1;
	private Sommet<Integer> s2;
	private Sommet<Integer> s3;
	private Arc<Integer> a11;
	private Arc<Integer> a12;
	private Arc<Integer> a21;
	private Arc<Integer> a22;
	private Arc<Integer> a23;

	@BeforeEach
	public void setup()
	{
		ensembleArcTest=Factory.ensembleArcNonValue();
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		a11=Factory.arcNonValue(s1,s1);
		a12=Factory.arcNonValue(s1,s2);
		a21=Factory.arcNonValue(s2,s1);
		a22=Factory.arcNonValue(s2,s2);
		a23=Factory.arcNonValue(s2,s3);
		ensembleArcTest.ajouteElement(a11);
		ensembleArcTest.ajouteElement(a12);
		ensembleArcTest.ajouteElement(a21);
		ensembleArcTest.ajouteElement(a22);
		ensembleArcTest.ajouteElement(a23);
		ensembleEmpty=Factory.ensembleArcNonValue();
	}
	@Test
	public void testEnsembleArc()
	{
		EnsembleArcNonValue<Integer> ensembleArcClone=Factory.ensembleArcNonValue(ensembleArcTest);
		assertEquals(ensembleArcTest,ensembleArcClone,"Le constructeur clone");
		assertNotSame(ensembleArcTest,ensembleArcClone,"Le constructeur clone créé un objet identique");
		ensembleArcClone.supprElement(a22);
		assertNotEquals(ensembleArcTest,ensembleArcClone,"Modifier le clone modifie l'original");
	}
	@Test
	public void testGetEnsemble()
	{
		HashSet<Arc<Integer>> ensemble=new HashSet<Arc<Integer>>();
		ensemble.add(a11);
		ensemble.add(a12);
		ensemble.add(a21);
		ensemble.add(a22);
		ensemble.add(a23);
		assertEquals(ensemble,ensembleArcTest.getEnsemble());
	}
	@Test
	public void testListSuccPred()
	{
		EnsembleSommet<Integer> succ1=Factory.ensembleSommet();
		succ1.ajouteElement(s2);
		succ1.ajouteElement(s1);
		EnsembleSommet<Integer> pred3=Factory.ensembleSommet();
		pred3.ajouteElement(s2);
		assertEquals(succ1,ensembleArcTest.listSucc(s1),"listSucc");
		assertEquals(pred3,ensembleArcTest.listPred(s3),"listPred");
	}
	@Test
	public void testAjouteSupprExist()
	{
		EnsembleArcNonValue<Integer> sommetAjout=Factory.ensembleArcNonValue();
		assertTrue(ensembleArcTest.existeArc(s1,s2),"Les arcs s'ajoutent bien");
		assertTrue(ensembleArcTest.existeBoucle(),"Test de présence de boucle lorsqu'elle existe");
		assertTrue(ensembleArcTest.existeBoucle(s2),"Test de présence de boucle avec un sommet en paramètre lorsqu'elle existe");
		assertFalse(ensembleArcTest.existeBoucle(s3),"Test de présence de boucle avec un sommet en paramètre lorsqu'elle n'existe pas");
		sommetAjout.ajouteElement(s1,s1);
		sommetAjout.ajouteElement(1,2);
		sommetAjout.ajouteElement(s2,s1);
		sommetAjout.ajouteElement(2,2);
		sommetAjout.ajouteElement(s2,s3);
		sommetAjout.ajouteElement(1,1);// ajouter un arc déja existant ne pose pas
																		// de problème
		assertTrue(sommetAjout.existeArc(a12),"Les arcs s'ajoutent bien");
		assertTrue(sommetAjout.existeArc(a22),"Les arcs s'ajoutent bien");
		assertTrue(sommetAjout.existeArc(a23),"Les arcs s'ajoutent bien");
		assertEquals(sommetAjout,ensembleArcTest,"Les deux ajouteElement ont un effet différent");
		ensembleArcTest.supprElement(a11);
		ensembleArcTest.supprElement(s2,s2);
		assertFalse(ensembleArcTest.existeArc(a22),"Arc supposé être enlevé");
		assertFalse(ensembleArcTest.existeArc(s1,s1),"Arc supposé être enlevé");
		ensembleArcTest.supprElement(a11);// enlever un arc déjà enlever ne pose pas d'erreur
		assertFalse(ensembleArcTest.existeBoucle(),"Test de présence de boucle lorsqu'elle n'existe pas");
	}
	@Test
	public void testUnion()
	{
		EnsembleArcNonValue<Integer> ajout=Factory.ensembleArcNonValue(ensembleArcTest);
		ajout.ajouteElement(s3,s2);
		ajout.ajouteElement(s3,s1);
		EnsembleArcNonValue<Integer> copie=Factory.ensembleArcNonValue(ensembleArcTest);
		EnsembleArcNonValue<Integer> unionVide=EnsembleArcNonValue.union(ensembleArcTest,ensembleEmpty);
		assertEquals(copie,unionVide);
		copie.ajouteElement(s3,s2);
		copie.ajouteElement(s3,s1);
		EnsembleArcNonValue<Integer> union=EnsembleArcNonValue.union(ensembleArcTest,ajout);
		assertEquals(copie,union);
	}
	@Test
	public void testIntersection()
	{
		EnsembleArcNonValue<Integer> copie=Factory.ensembleArcNonValue(ensembleArcTest);
		copie.supprElement(a22);
		copie.supprElement(a11);
		copie.ajouteElement(s3,s3);
		EnsembleArcNonValue<Integer> intersection=EnsembleArcNonValue.intersection(ensembleArcTest,copie);
		copie.supprElement(s3,s3);
		assertEquals(copie,intersection);
		EnsembleArcNonValue<Integer> intersectionVide=EnsembleArcNonValue.intersection(ensembleArcTest,ensembleEmpty);
		assertEquals(ensembleEmpty,intersectionVide);
	}
	@Test
	public void testEncapsulation()
	{
		s3.setId(9);
		assertFalse(ensembleArcTest.existeArc(s2,s3),"Modification d'un sommet depuis l'exterieur");
		assertTrue(ensembleArcTest.existeArc(a23),"Modification d'un sommet depuis l'exterieur");
		a12.setArrivee(s3);
		assertFalse(ensembleArcTest.existeArc(a12),"Modification d'un arc depuis l'exterieur");
	}
	@Test
	public void testToString()
	{
		String string="{"+a11.toString()+","+a12.toString()+","+a21.toString()+","+a22.toString()+","+a23.toString()+"}";
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
		somme+=a11.hashCode();
		somme+=a12.hashCode();
		somme+=a21.hashCode();
		somme+=a22.hashCode();
		somme+=a23.hashCode();
		assertEquals(somme,ensembleArcTest.hashCode());
	}
}