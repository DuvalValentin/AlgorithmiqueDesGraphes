package algorithme;

import factory.Factory;
import graphelements.interfaces.*;

public class AntiTransitif
{
	private AntiTransitif()
	{}
	public static <S> GrapheNonValue<S> tauMinalite(GrapheNonValue<S> graphe)
	{
		EnsembleSommet<S> ensembleSommet=graphe.getEnsembleSommet();
		GrapheNonValue<S> royWarshall=Factory.grapheNonValue(graphe);
		GrapheNonValue<S> tauMin=Factory.grapheNonValue(graphe);// Graphe tauMinimal
		for(Sommet<S> z : ensembleSommet.getEnsemble())
		{
			for(Sommet<S> x : ensembleSommet.getEnsemble())
			{
				if(graphe.existeArc(x,z))
				{
					for(Sommet<S> y : ensembleSommet.getEnsemble())
					{
						if(graphe.existeArc(z,y))
						{
							royWarshall.ajouteArc(x,y);
							tauMin.supprArc(x,y);
						}
					}
				}
			}
		}
		return tauMin;
	}
}
