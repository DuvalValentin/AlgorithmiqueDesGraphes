package testing;
import graphElements.Ensemble;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class EnsembleTest
{
	private Ensemble<Integer> ensembleTest;
	@Before
	public void setup()
	{
		ensembleTest=new Ensemble<Integer>();
		ensembleTest.add(2);
		ensembleTest.add(5);
	}
	
	@Test
	public void testEnsemble()
	{
		Ensemble<Integer> ensemble = new Ensemble<Integer>(ensembleTest);
		assertEquals("Le constructeur avec paramètres de Ensemble ne marche pas",ensembleTest,ensemble);
	}

	@Test
	public void testClone()
	{
		Ensemble<Integer> ensembleClone = ensembleTest.clone();
		assertEquals("La méthode clone de Ensemble ne marche pas.",ensembleTest,ensembleClone);
		assertNotSame("Le clone pointe vers le même objet",ensembleTest,ensembleClone);
	}

}
