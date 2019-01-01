package testing;

import graphElements.Elements.ArcValue;
import graphElements.Elements.Cout;
import graphElements.Elements.Sommet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ArcValueTest
{
	private ArcValue<Integer> arcValueTest;
	private Sommet<Integer> depart;
	private Sommet<Integer> arrivee;
	private Cout cout;

	@Before
	public void setUp() 
	{
		depart=new Sommet<Integer>(3);
		arrivee=new Sommet<Integer>(9);
		cout=new Cout (5);
		arcValueTest=new ArcValue<Integer>(depart,arrivee,cout);
	}

	@Test
	public void testGetValeur()
	{
		assertTrue("Test du getValeur",5==arcValueTest.getCout().getValeur());
	}

}
