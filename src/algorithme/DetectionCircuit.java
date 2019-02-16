package algorithme;

import graphElements.Elements.*;

public class DetectionCircuit
{
	public static <S> boolean Roy_Warshall(GrapheNonValue<S> G)
	{
		GrapheNonValue<S> RW=FermetureTransitive.Roy_Warshall(G);
		boolean result;
		if(RW.existeBoucle())
		{
			result=true;
		}
		else
		{
			result=false;
		}
		return result;
	}
	//TODO DFS et WFS
	public static <S> boolean MarimontEntree(GrapheNonValue<S> G)
	{
		GrapheNonValue<S>SG=new GrapheNonValue<S>(G);//sous graphe
		EnsembleSommet<S> Ent=SG.pointsEntree();
		while(!Ent.isEmpty())
		{
			for(Sommet<S> S : Ent.getEnsemble())
			{
				SG.supprSommet(S);
			}
			Ent=SG.pointsEntree();
		}
		return !SG.isEmpty();
	}
	
	public static <S> boolean MarimontSortie(GrapheNonValue<S> G)
	{
		GrapheNonValue<S>SG=new GrapheNonValue<S>(G);//sous graphe
		EnsembleSommet<S> Sor=SG.pointsSortie();
		while(!Sor.isEmpty())
		{
			for(Sommet<S> S : Sor.getEnsemble())
			{
				SG.supprSommet(S);
			}
			Sor=SG.pointsSortie();
		}
		return !SG.isEmpty();
	}
}