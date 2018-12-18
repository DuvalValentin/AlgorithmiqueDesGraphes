package graphElements;

public class GrapheValue<S,V> extends Graphe<S>
{
	//private EnsembleArcValués<S,V> Gamma;
	public GrapheValue(EnsembleSommet<S> x,EnsembleArcValues<S,V> gamma)
	{
		super(x,gamma);
	}
	
	/*public void valeurMin()
	{
		V min;
		for(ArcValué<S,V> arc : Gamma)
		{
			if (arc.getValeur()==arc.getValeur())
			{
				min=arc.getValeur();
			}
		}
		return min;
	}*/
	
	public V valeurArc(ArcValue<S,V> arc)
	{
		return arc.getValeur();
	}
}
