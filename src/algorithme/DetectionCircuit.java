package algorithme;

import graphElements.*;

public class DetectionCircuit
{
	public static <S> boolean Roy_Warshall(Graphe<S> G)
	{
		Graphe<S> RW=FermetureTransitive.Roy_Warshall(G);
		boolean result=false;
		for(Sommet<S> S : RW.getX())
		{
			if(RW.existeBoucle(S))
			{
				result=true;
				break;
			}
		}
		return result;
	}
	//TODO DFS et WFS
	public static <S> boolean MarimontEntree(Graphe<S> G)
	{
		Graphe<S>SG=new Graphe<S>(G);//sous graphe
		EnsembleSommet<S> E=SG.pointsEntree();
		while(!E.isEmpty())
		{
			for(Sommet<S> S : E)
			{
				SG.supprSommet(S);
			}
			E=SG.pointsEntree();
		}
		return !SG.isEmpty();
	}
}