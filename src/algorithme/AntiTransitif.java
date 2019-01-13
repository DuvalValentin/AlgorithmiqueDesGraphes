package algorithme;

import graphElements.Elements.*;

public class AntiTransitif
{
	public static <S> Graphe<S> TauMinalite(Graphe<S> G)
	{
		EnsembleSommet<S>X=G.getX();
		Graphe<S> RW=new Graphe<S>(G);
		Graphe<S> TauMin=new Graphe<S>(G);//Graphe tauMinimal
		
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
