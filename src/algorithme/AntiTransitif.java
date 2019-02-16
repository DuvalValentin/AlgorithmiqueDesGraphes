package algorithme;

import graphElements.Elements.*;

public class AntiTransitif
{
	public static <S> GrapheNonValue<S> TauMinalite(GrapheNonValue<S> G)
	{
		EnsembleSommet<S>X=G.getX();
		GrapheNonValue<S> RW=new GrapheNonValue<S>(G);
		GrapheNonValue<S> TauMin=new GrapheNonValue<S>(G);//Graphe tauMinimal
		
		for(Sommet<S> z : X.getEnsemble()) 
		{
			for(Sommet<S> x : X.getEnsemble())
			{
				if(RW.existeArc(x,z))
				{
					for(Sommet<S> y : X.getEnsemble())
					{
						if(RW.existeArc(z,y))
						{
							RW.ajouteArc(x, y);
							TauMin.supprArc(x, y);
						}
					}
				}
			}
		}
		return TauMin;
	}
}
