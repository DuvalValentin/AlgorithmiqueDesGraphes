package algorithme;

import java.util.Stack;
import exception.AbsorbingException;
import exception.CircuitException;
import exception.NotRootException;
import factory.Factory;
import graphelements.interfaces.*;

/*TODO créer une classe d'exception si le graphe passé en paramètre ne respecte pas les préconditions*/
public class PlusCC
{
	private PlusCC()
	{}
	public static <S> TableauPlusCC<S> dijkstra(GrapheValue<S> graph, Sommet<S> sommet0)
	{
		EnsembleSommet<S> ensembleNonTraite=Factory.ensembleSommet(graph.getEnsembleSommet());// Liste des sommets non traités
		EnsembleSommet<S> successeurs=Factory.ensembleSommet(graph.listSucc(sommet0));// Liste de Successeur du sommet en cours
		TableauPlusCC<S> dijkstra=Factory.tableauPlusCC(sommet0,graph);// Tableau résultat
		float distance;// valeur de la distance par un sommet intermédiaire
		ensembleNonTraite.supprElement(sommet0);
		successeurs.supprElement(sommet0);// Supprime x si x se succède à lui même
		// FIXME si le graphe est absorbant car x0->x0 est négatif, on ne le reperera jamais.
		while(!ensembleNonTraite.isEmpty())
		{
			Float coutm=null;
			Sommet<S> m=null;
			for(Sommet<S> sommet : ensembleNonTraite.getEnsemble())
			{
				if(coutm==null)
				{
					m=sommet;
					coutm=dijkstra.getDistance(sommet);
				}
				else
				{
					if(dijkstra.getDistance(sommet)!=null&&dijkstra.getDistance(sommet)<coutm)
					{
						m=sommet;
						coutm=dijkstra.getDistance(sommet);
					}
				}
				successeurs=graph.listSucc(m);
				for(Sommet<S> y : successeurs.getEnsemble())
				{
					if(ensembleNonTraite.existeSommet(y))
					{
						Float cout=graph.getCout(m,y).orElseThrow();
						distance=dijkstra.getDistance(m)+cout;
						if(distance<dijkstra.getDistance(y))
						{
							dijkstra.modifDistance(y,distance);
							dijkstra.modifPredecesseur(y,m);
						}
					}
				}
			}
			ensembleNonTraite.supprElement(m);
		}
		return dijkstra;
		// FIXME revoir tout le dijkstra => complexité trop élévée
	}
	public static <S> TableauPlusCC<S> ordinalRacine(GrapheValue<S> graph, Sommet<S> sommet0) throws NotRootException, CircuitException
	{
		GrapheValue<S> remainingGraph=Factory.grapheValue(graph);// Le reste du graphe qu'il reste à parcourir
		EnsembleSommet<S> entrees;// Liste des points d'entrée
		EnsembleSommet<S> predecesseurs;// Liste de prédecesseurs
		Sommet<S> y;// le sommet que l'on traite
		Sommet<S> m;// prédécesseur de y au coup le plus faible pour atteindre y
		TableauPlusCC<S> ordinalRacine=Factory.tableauPlusCC(sommet0,graph);
		if(!graph.listPred(sommet0).equals(Factory.ensembleSommet()))
		{
			throw new NotRootException(sommet0+" n'est pas la racine de "+graph);
		}
		remainingGraph.supprSommet(sommet0);
		entrees=remainingGraph.pointsEntree();
		while(!remainingGraph.isEmpty()&&!entrees.isEmpty())
		{
			y=entrees.pickSommet();
			predecesseurs=graph.listPred(y);
			Float coutm=null;
			m=null;
			Float coutp;
			for(Sommet<S> sommet : predecesseurs.getEnsemble())
			{
				coutp=ordinalRacine.getDistance(sommet)+graph.getCout(sommet,y).orElseThrow();
				if(coutm==null)
				{
					m=sommet;
					coutm=coutp;
				}
				else
				{
					if(coutm>coutp)
					{
						m=sommet;
						coutm=coutp;
					}
				}
			}
			ordinalRacine.modifDistance(y,ordinalRacine.getDistance(m)+graph.getCout(m,y).orElseThrow());
			ordinalRacine.modifPredecesseur(y,m);
			remainingGraph.supprSommet(y);
			entrees=remainingGraph.pointsEntree();
		}
		if(!remainingGraph.isEmpty())
		{
			throw new CircuitException("Ordinal racine n'est pas applicable si le graph contient un circuit");
		}
		return ordinalRacine;
	}
	public static <S> TableauPlusCC<S> bellmanFord(GrapheValue<S> graph, Sommet<S> sommet0) throws AbsorbingException
	{
		TableauPlusCC<S> bellmanFord=Factory.tableauPlusCC(sommet0,graph);// Tableau final
		GrapheValue<S> graphM=Factory.grapheValue(graph);// G moins x0
		Float cout;// cout entre les sommet actuel et son successeur
		graphM.supprSommet(sommet0);// FIXME Gm n'est pas utilisé par la suite => pas normal
		for(Sommet<S> x : graph.getEnsembleSommet().getEnsemble())
		{
			for(Sommet<S> y : graph.listSucc(x).getEnsemble())
			{
				if(bellmanFord.getDistance(x)!=null)
				{
					cout=bellmanFord.getDistance(x)+graph.getCout(x,y).orElseThrow();
					if(bellmanFord.getDistance(y)==null)
					{
						bellmanFord.modifPredecesseur(y,x);
						bellmanFord.modifDistance(y,cout);
					}
					else
					{
						if(bellmanFord.getDistance(y)>cout)
						{
							bellmanFord.modifPredecesseur(y,x);
							bellmanFord.modifDistance(y,cout);
						}
					}
				}
			}
		}
		for(Sommet<S> x : graph.getEnsembleSommet().getEnsemble())
		{
			for(Sommet<S> y : graph.listSucc(x).getEnsemble())
			{
				if(bellmanFord.getDistance(y)>bellmanFord.getDistance(x)+graph.getCout(x,y).orElseThrow())
				{
					throw new AbsorbingException("BellmanFord doesn't work with absorbing graph");
				}
			}
		}
		return bellmanFord;
	}
	public static <S> TableauPlusCC<S> ford(GrapheValue<S> graph, Sommet<S> sommet0) throws AbsorbingException
	{
		TableauPlusCC<S> ford=Factory.tableauPlusCC(sommet0,graph);
		Sommet<S> y, z;
		EnsembleSommet<S> successeursY;
		boolean absorbant=false;
		Stack<Sommet<S>> pile=new Stack<>();
		EnsembleSommet<S> t=Factory.ensembleSommet();
		EnsembleSommet<S> d=Factory.ensembleSommet(graph.getEnsembleSommet());
		d.supprElement(sommet0);
		pile.push(sommet0);
		GrapheValue<S> graphToVisit=Factory.grapheValue(graph);
		while(!absorbant&&!pile.empty())
		{
			y=pile.peek();
			successeursY=graphToVisit.listSucc(y);
			if(!successeursY.isEmpty())
			{
				z=successeursY.pickSommet();
				graphToVisit.supprArc(y,z);// Supprimer l'arc ici empêche l'arc de pouvoir être revisité
				if(d.existeSommet(z))
				{
					d.supprElement(z);
					ford.modifDistance(z,ford.getDistance(y)+graph.getCout(y,z).orElseThrow());
					ford.modifPredecesseur(z,y);
					pile.push(z);
				}
				else
				{
					if(t.existeSommet(z))
					{
						if(ford.getDistance(z)>ford.getDistance(y)+graph.getCout(y,z).orElseThrow())
						{
							t.supprElement(z);
							for(Sommet<S> sommet : graph.listSucc(z).getEnsemble())
							{
								graphToVisit.ajouteArc(z,sommet,graph.getCout(z,sommet).orElseThrow());
							}
							ford.modifDistance(z,ford.getDistance(y)+graph.getCout(y,z).orElseThrow());
							ford.modifPredecesseur(z,y);
							pile.push(z);
						}
					}
					else
					{
						if(ford.getDistance(z)>ford.getDistance(y)+graph.getCout(y,z).orElseThrow())
						{
							absorbant=true;
						}
					}
				}
			}
			else
			{
				t.ajouteElement(y);
				pile.pop();
			}
		}
		if(absorbant)
		{
			throw new AbsorbingException("Ford doesn't work with absorbing graph");
		}
		return ford;
	}
}