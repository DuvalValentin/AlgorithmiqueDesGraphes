package algorithme;

import graphElements.*;

public class AntiTransitif
{
	public static <S> Graphe<S> TauMinalite(Graphe<S> G)
	{
		EnsembleSommet<S>X=G.getX();
		Graphe<S> RW=new Graphe<S>(G);
		Graphe<S> TauMin=new Graphe<S>(G);
		
		for(Sommet<S> z : X) 
		{
			for(Sommet<S> x : X)
			{
				if(RW.existArc(x,z))
				{
					for(Sommet<S> y : X)
					{
						if(RW.existArc(z,y))
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
