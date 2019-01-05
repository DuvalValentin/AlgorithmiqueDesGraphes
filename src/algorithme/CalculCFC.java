package algorithme;

import graphElements.Abstract.AbstractGraphe;
import graphElements.Elements.*;

public class CalculCFC
{
	public static <S,A extends Arc<S>> CFC<S> Foulkes(AbstractGraphe<S,A> G)
	{
		//FermetureTransitive.Roy_Warshall(G);
		//Ici on considère que G est fermé transitivement
		EnsembleSommet<S> ensembleSommet = G.getX().clone();
		EnsembleSommet<S> Pris = new EnsembleSommet<S>();
		EnsembleSommet<S> PasPris = ensembleSommet.clone();
		CFC<S>cfc = new CFC<S>(ensembleSommet);
		for (Sommet<S> x : ensembleSommet)
		{
			if(!Pris.contains(x))
			{
				PasPris.supprSommet(x);
				if(G.existeBoucle(x))
				{
					for (Sommet<S> y : PasPris)
					{
						if(G.existeArc(x, y)&&G.existeArc(y, x))
						{
							cfc.get(x).union(cfc.get(y));
						}
					}
				}
				for(Sommet<S> y : cfc.get(x))
				{
					PasPris.supprSommet(x);
					cfc.replace(y, cfc.get(x));
				}
				Pris.union(cfc.get(x));
			}
		}
		return cfc;
	}
	//TODO Tarjan
	/*public static <S,A extends Arc<S>> Graphe<EnsembleSommet<S>> Tarjan(AbstractGraphe<S,A> G)
	{
		
	}*/
}