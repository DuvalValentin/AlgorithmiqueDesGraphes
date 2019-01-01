package algorithme;

import graphElements.Elements.*;

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
				if(RW.existeArc(x,z))
				{
					for(Sommet<S> y : X)
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
