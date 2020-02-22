package algorithme;

import factory.Factory;
import graphelements.interfaces.*;

public class DetectionCircuit
{
	private DetectionCircuit()
	{}
	public static <S> boolean royWarshall(GrapheNonValue<S> graph)
	{
		GrapheNonValue<S> royWarshall=(GrapheNonValue<S>)FermetureTransitive.royWarshall(graph);
		boolean result;
		if(royWarshall.existeBoucle())
		{
			result=true;
		}
		else
		{
			result=false;
		}
		return result;
	}
	// TODO DFS et WFS
	public static <S> boolean marimontEntree(GrapheNonValue<S> graph)
	{
		GrapheNonValue<S> sousGraphe=Factory.grapheNonValue(graph);
		EnsembleSommet<S> ensembleEntree=sousGraphe.pointsEntree();
		while(!ensembleEntree.isEmpty())
		{
			for(Sommet<S> entree : ensembleEntree.getEnsemble())
			{
				sousGraphe.supprSommet(entree);
			}
			ensembleEntree=sousGraphe.pointsEntree();
		}
		return !sousGraphe.isEmpty();
	}
	public static <S> boolean marimontSortie(GrapheNonValue<S> graph)
	{
		GrapheNonValue<S> sousGraphe=Factory.grapheNonValue(graph);
		EnsembleSommet<S> ensembleSortie=sousGraphe.pointsSortie();
		while(!ensembleSortie.isEmpty())
		{
			for(Sommet<S> sortie : ensembleSortie.getEnsemble())
			{
				sousGraphe.supprSommet(sortie);
			}
			ensembleSortie=sousGraphe.pointsSortie();
		}
		return !sousGraphe.isEmpty();
	}
}