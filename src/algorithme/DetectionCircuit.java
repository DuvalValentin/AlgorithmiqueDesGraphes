package algorithme;

import factory.Factory;
import graphelements.interfaces.*;
public class DetectionCircuit
{
	private DetectionCircuit(){}
	
	public static <S> boolean royWarshall(InterfaceGrapheNonValue<S> graph)
	{
		InterfaceGrapheNonValue<S> royWarshall=(InterfaceGrapheNonValue<S>) FermetureTransitive.royWarshall(graph);
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
	//TODO DFS et WFS
	public static <S> boolean marimontEntree(InterfaceGrapheNonValue<S> graph)
	{
		InterfaceGrapheNonValue<S>sousGraphe=Factory.grapheNonValue(graph);
		InterfaceEnsembleSommet<S> ensembleEntree=sousGraphe.pointsEntree();
		while(!ensembleEntree.isEmpty())
		{
			for(InterfaceSommet<S> entree : ensembleEntree.getEnsemble())
			{
				sousGraphe.supprSommet(entree);
			}
			ensembleEntree=sousGraphe.pointsEntree();
		}
		return !sousGraphe.isEmpty();
	}
	
	public static <S> boolean marimontSortie(InterfaceGrapheNonValue<S> graph)
	{
		InterfaceGrapheNonValue<S>sousGraphe=Factory.grapheNonValue(graph);
		InterfaceEnsembleSommet<S> ensembleSortie=sousGraphe.pointsSortie();
		while(!ensembleSortie.isEmpty())
		{
			for(InterfaceSommet<S> sortie : ensembleSortie.getEnsemble())
			{
				sousGraphe.supprSommet(sortie);
			}
			ensembleSortie=sousGraphe.pointsSortie();
		}
		return !sousGraphe.isEmpty();
	}
}