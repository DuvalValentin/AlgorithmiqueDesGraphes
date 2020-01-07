package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithme.CalculCFC;
import algorithme.FermetureTransitive;
import factory.Factory;
import graphelements.interfaces.*;

public class CalculCFCTest
{
	private InterfaceSommet<Integer> s1,s2,s3,s4;
	
	private InterfaceArc<Integer> a12,a23,a32,a34,a41;
	
	private InterfaceGrapheNonValue<Integer>G1;
	private InterfaceCFC<Integer> CFCres1;
	private InterfaceEnsembleSommet<Integer> ES23;

	private InterfaceGrapheNonValue<Integer>G2;
	private InterfaceCFC<Integer> CFCres2;
	private InterfaceEnsembleSommet<Integer> ES1234;
	
	
	@BeforeEach
	public void setUp()
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		
		a12=Factory.arcNonValue(s1,s2);
		a23=Factory.arcNonValue(s2,s3);
		a32=Factory.arcNonValue(s3,s2);
		a34=Factory.arcNonValue(s3,s4);
		
		G1=Factory.grapheNonValue();
		
		G1.ajouteSommet(s1);G1.ajouteSommet(s2);G1.ajouteSommet(s3);G1.ajouteSommet(s4);
		G1.ajouteArc(a12);G1.ajouteArc(a23);G1.ajouteArc(a32);G1.ajouteArc(a34);
		
		CFCres1=Factory.CFC(G1.getEnsembleSommet());
		ES23=Factory.ensembleSommet();
		ES23.ajouteElement(s2);ES23.ajouteElement(s3);
		CFCres1.replace(s2, ES23);CFCres1.replace(s3, ES23);
		
		G2=Factory.grapheNonValue();
		a41=Factory.arcNonValue(s4,s1);
		G2.ajouteSommet(s1);G2.ajouteSommet(s2);G2.ajouteSommet(s3);G2.ajouteSommet(s4);
		
		G2.ajouteArc(a12);G2.ajouteArc(a23);G2.ajouteArc(a34);G2.ajouteArc(a41);
		
		CFCres2=Factory.CFC(G2.getEnsembleSommet());
		ES1234=Factory.ensembleSommet();
		ES1234.ajouteElement(s1);ES1234.ajouteElement(s2);ES1234.ajouteElement(s3);ES1234.ajouteElement(s4);
		CFCres2.replace(s1, ES1234);CFCres2.replace(s2, ES1234);CFCres2.replace(s3, ES1234);CFCres2.replace(s4, ES1234);
		
	}

	@Test
	public void testFoulkes()
	{
		assertEquals(CFCres1,CalculCFC.foulkes(FermetureTransitive.royWarshall(G1)),"Calcul des CFC grâce à l'algorithme de Foulkes");
		assertEquals(CFCres2,CalculCFC.foulkes(FermetureTransitive.royWarshall(G2)),"Calcul des CFC grâce à l'algorithme de Foulkes");
		//System.err.println(CalculCFC.TarjanDFS(G1));
	}
	@Test
	public void testTarjan()
	{
		assertEquals(CFCres1,CalculCFC.tarjanDFS(G1),"Calcul des CFC grâce à l'algorithme de Tarjan");
		assertEquals(CFCres2,CalculCFC.tarjanDFS(G2),"Calcul des CFC grâce à l'algorithme de Tarjan");
	}
}