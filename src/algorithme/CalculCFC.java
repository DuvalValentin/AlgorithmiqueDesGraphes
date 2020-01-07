package algorithme;

import java.util.HashMap;
import java.util.Stack;

import factory.Factory;
import graphelements.interfaces.*;

public class CalculCFC
{
	private CalculCFC(){}
	
	public static <S,A extends InterfaceArc<S>> InterfaceCFC<S> foulkes(InterfaceGraphe<S,A> graph) 
	{
		//Ici on considère que G est fermé transitivement
		InterfaceEnsembleSommet<S> ensembleSommet =Factory.ensembleSommet(graph.getEnsembleSommet());
		InterfaceEnsembleSommet<S> pris = Factory.ensembleSommet();
		InterfaceEnsembleSommet<S> pasPris = Factory.ensembleSommet(ensembleSommet);
		InterfaceCFC<S>cfc = Factory.CFC(ensembleSommet);
		for (InterfaceSommet<S> x : ensembleSommet.getEnsemble())
		{
			if(!pris.existeSommet(x))
			{
				pasPris.supprElement(x);
				if(graph.existeBoucle(x))
				{
					for (InterfaceSommet<S> y : pasPris.getEnsemble())
					{
						if(graph.existeArc(x, y)&&graph.existeArc(y, x))
						{
						cfc.replace(x, (InterfaceEnsembleSommet<S>) InterfaceEnsemble.union(cfc.get(x), cfc.get(y)));
						}
					}
				}
				for(InterfaceSommet<S> y : cfc.get(x).getEnsemble())
				{
					pasPris.supprElement(x);
					cfc.replace(y, cfc.get(x));
				}
				pris =(InterfaceEnsembleSommet<S>) InterfaceEnsemble.union(pris, cfc.get(x));
			}
		}
		return cfc;
	}
	
	public static <S,A extends InterfaceArc<S>> InterfaceCFC<S> tarjanDFS (InterfaceGraphe<S,A> graph)
	{
		InterfaceEnsembleSommet<S> sommetNonVisites;//Ensemble des sommets non visités
		InterfaceEnsembleSommet<S> successeursY;
		InterfaceEnsembleSommet<S> successeursZ;
		Stack<InterfaceSommet<S>> pile=new Stack<>(); 
		InterfaceCFC<S> cfc;
		HashMap<InterfaceSommet<S>,Integer> pospile=new HashMap<>();
		
		InterfaceSommet<S>y;
		InterfaceSommet<S>z;
		//Init
		cfc=Factory.CFC(graph.getEnsembleSommet());
		for(InterfaceSommet<S> sommet : graph.getEnsembleSommet().getEnsemble())
		{
			pospile.put(sommet, 0);
		}
		sommetNonVisites=Factory.ensembleSommet(graph.getEnsembleSommet());
		InterfaceGraphe<S,A> graphToVisit=Factory.graphe(graph);

		while(!sommetNonVisites.isEmpty())
		{
			pile.push(graphToVisit.pickSommet());
			pospile.replace(graphToVisit.pickSommet(), pile.size());
			sommetNonVisites.supprElement(graphToVisit.pickSommet());
			while(!pile.isEmpty())
			{
				y=pile.peek();
				successeursY=graphToVisit.listSucc(y);
				if(!successeursY.isEmpty())
				{
					z=successeursY.pickSommet();
					graphToVisit.supprArc(y, z);
					if (sommetNonVisites.existeSommet(z))
					{
						sommetNonVisites.supprElement(z);
						successeursZ=graphToVisit.listSucc(z);
						if (!successeursZ.isEmpty())
						{
							pile.push(z);
							pospile.replace(z, pile.size());
						}
					}
					else
					{
						//Pour le moment la répétition permet au tout de marcher
						for(int i = pospile.get(z);i<pile.size();i++)//pospile.get(z) correspond à la position du sommet au dessus de z sur la pile
						{
							cfc.get(z).ajouteElement(pile.get(i));
						}
						for(int i = pospile.get(z);i<pile.size();i++)//pospile.get(z) correspond à la position du sommet au dessus de z sur la pile
						{
							cfc.memeCFC(z,pile.get(i));
						}
					}
				}
				else
				{
					pile.pop();
				}
			}
		}
		return cfc;
	}
}