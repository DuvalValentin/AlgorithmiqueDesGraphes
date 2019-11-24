package algorithme;

import factory.Factory;
import graphElements.Interfaces.*;
public class DetectionCircuit
{
	private DetectionCircuit(){}
	
	public static <S> boolean Roy_Warshall(InterfaceGrapheNonValue<S> G)
	{
		InterfaceGrapheNonValue<S> RW=(InterfaceGrapheNonValue<S>) FermetureTransitive.Roy_Warshall(G);
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
	public static <S> boolean MarimontEntree(InterfaceGrapheNonValue<S> G)
	{
		InterfaceGrapheNonValue<S>SG=Factory.grapheNonValue(G);//sous graphe
		InterfaceEnsembleSommet<S> Ent=SG.pointsEntree();
		while(!Ent.isEmpty())
		{
			for(InterfaceSommet<S> S : Ent.getEnsemble())
			{
				SG.supprSommet(S);
			}
			Ent=SG.pointsEntree();
		}
		return !SG.isEmpty();
	}
	
	public static <S> boolean MarimontSortie(InterfaceGrapheNonValue<S> G)
	{
		InterfaceGrapheNonValue<S>SG=Factory.grapheNonValue(G);//sous graphe
		InterfaceEnsembleSommet<S> Sor=SG.pointsSortie();
		while(!Sor.isEmpty())
		{
			for(InterfaceSommet<S> S : Sor.getEnsemble())
			{
				SG.supprSommet(S);
			}
			Sor=SG.pointsSortie();
		}
		return !SG.isEmpty();
	}
}