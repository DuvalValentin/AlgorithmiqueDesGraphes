package testing;

import algorithme.AntiTransitif;
import factory.Factory;
import graphElements.Interfaces.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AntiTransitifTest
{
	
	private InterfaceSommet<Integer> s1,s2,s3,s4;
	
	private InterfaceArc<Integer> a12,a13,a14,a23,a24,a34;
	
	private InterfaceGrapheNonValue<Integer> Gini,Gres;

	@BeforeEach
	void setUp() throws Exception
	{
		s1=Factory.sommet(1);
		s2=Factory.sommet(2);
		s3=Factory.sommet(3);
		s4=Factory.sommet(4);
		
		a12=Factory.arcNonValue(s1,s2);
		a13=Factory.arcNonValue(s1,s3);
		a14=Factory.arcNonValue(s1,s4);
		a23=Factory.arcNonValue(s2,s3);
		a24=Factory.arcNonValue(s2,s4);
		a34=Factory.arcNonValue(s3,s4);
		
		Gres=Factory.grapheNonValue();
		Gres.ajouteSommet(s1);Gres.ajouteSommet(s2);Gres.ajouteSommet(s3);Gres.ajouteSommet(s4);
		Gres.ajouteArc(a12);Gres.ajouteArc(a23);Gres.ajouteArc(a34);
		Gini=Factory.grapheNonValue(Gres);
		Gini.ajouteArc(a13);Gini.ajouteArc(a14);Gini.ajouteArc(a24);
	}

	@Test
	public void testTauMinalite()
	{
		assertEquals(Gres,AntiTransitif.TauMinalite(Gini),"test de la TauMinalite pour un graphe sans circuits");
	}

}
