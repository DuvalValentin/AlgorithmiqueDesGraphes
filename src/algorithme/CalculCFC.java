package algorithme;

import java.util.HashMap;
import java.util.Stack;
import factory.Factory;
import graphelements.interfaces.*;

public class CalculCFC
{
	private CalculCFC()
	{}
	public static <S,A extends Arc<S>> CFC<S> foulkes(Graphe<S,A> graph)
	{
		// Ici on considère que G est fermé transitivement
		EnsembleSommet<S> ensembleSommet=Factory.ensembleSommet(graph.getEnsembleSommet());
		EnsembleSommet<S> pris=Factory.ensembleSommet();
		EnsembleSommet<S> pasPris=Factory.ensembleSommet(ensembleSommet);
		CFC<S> cfc=Factory.CFC(ensembleSommet);
		for(Sommet<S> x : ensembleSommet.getEnsemble())
		{
			if(!pris.existeSommet(x))
			{
				pasPris.supprElement(x);
				if(graph.existeBoucle(x))
				{
					for(Sommet<S> y : pasPris.getEnsemble())
					{
						if(graph.existeArc(x,y)&&graph.existeArc(y,x))
						{
							cfc.replace(x,(EnsembleSommet<S>)Ensemble.union(cfc.get(x),cfc.get(y)));
						}
					}
				}
				for(Sommet<S> y : cfc.get(x).getEnsemble())
				{
					pasPris.supprElement(x);
					cfc.replace(y,cfc.get(x));
				}
				pris=(EnsembleSommet<S>)Ensemble.union(pris,cfc.get(x));
			}
		}
		return cfc;
	}
	public static <S,A extends Arc<S>> CFC<S> tarjanDFS(Graphe<S,A> graph)
	{
		EnsembleSommet<S> sommetNonVisites;// Ensemble des sommets non visités
		EnsembleSommet<S> successeursY;
		EnsembleSommet<S> successeursZ;
		Stack<Sommet<S>> pile=new Stack<>();
		CFC<S> cfc;
		HashMap<Sommet<S>,Integer> pospile=new HashMap<>();
		Sommet<S> y;
		Sommet<S> z;
		// Init
		cfc=Factory.CFC(graph.getEnsembleSommet());
		for(Sommet<S> sommet : graph.getEnsembleSommet().getEnsemble())
		{
			pospile.put(sommet,0);
		}
		sommetNonVisites=Factory.ensembleSommet(graph.getEnsembleSommet());
		Graphe<S,A> graphToVisit=Factory.graphe(graph);
		while(!sommetNonVisites.isEmpty())
		{
			pile.push(graphToVisit.pickSommet());
			pospile.replace(graphToVisit.pickSommet(),pile.size());
			sommetNonVisites.supprElement(graphToVisit.pickSommet());
			while(!pile.isEmpty())
			{
				y=pile.peek();
				successeursY=graphToVisit.listSucc(y);
				if(!successeursY.isEmpty())
				{
					z=successeursY.pickSommet();
					graphToVisit.supprArc(y,z);
					if(sommetNonVisites.existeSommet(z))
					{
						sommetNonVisites.supprElement(z);
						successeursZ=graphToVisit.listSucc(z);
						if(!successeursZ.isEmpty())
						{
							pile.push(z);
							pospile.replace(z,pile.size());
						}
					}
					else
					{
						// Pour le moment la répétition permet au tout de marcher
						for(int i=pospile.get(z); i<pile.size(); i++)// pospile.get(z)
																													// correspond à la
																													// position du sommet
																													// au dessus de z sur
																													// la pile
						{
							cfc.get(z).ajouteElement(pile.get(i));
						}
						for(int i=pospile.get(z); i<pile.size(); i++)// pospile.get(z)
																													// correspond à la
																													// position du sommet
																													// au dessus de z sur
																													// la pile
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