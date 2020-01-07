package algorithme;

import java.util.Stack;

import factory.Factory;
import graphelements.interfaces.*;

/*TODO créer une classe d'exception si le graphe passé en paramètre ne respecte pas les préconditions*/
public class PlusCC
{
	private PlusCC(){}
	
	public static <S> InterfaceTableauPlusCC<S> dijkstra(InterfaceGrapheValue<S> graph,InterfaceSommet<S> sommet0)
	{
		InterfaceEnsembleSommet<S>ensembleNonTraite=Factory.ensembleSommet(graph.getEnsembleSommet());//Liste des sommets non traités
		InterfaceEnsembleSommet<S>successeurs=Factory.ensembleSommet(graph.listSucc(sommet0));//Liste de Successeur du sommet en cours
		InterfaceTableauPlusCC<S> dijkstra=Factory.tableauPlusCC(sommet0,graph);//Tableau résultat
		float distance;//valeur de la distance par un sommet intermédiaire
		ensembleNonTraite.supprElement(sommet0);
		successeurs.supprElement(sommet0);//Supprime x si x se succède à lui même//FIXME si le graphe est absorbant car x0->x0 est négatif, on ne le reperera jamais.
		while(!ensembleNonTraite.isEmpty())
		{
			InterfaceCout coutm=null;
			InterfaceSommet<S> m=null;
			for(InterfaceSommet<S> sommet : ensembleNonTraite.getEnsemble())
			{
				if (coutm==null)
				{
					m=sommet;
					coutm=dijkstra.getDistance(sommet);
				}
				else
				{
					if(dijkstra.getDistance(sommet)!=null&&dijkstra.getDistance(sommet).getValeur()<coutm.getValeur())
					{
						m=sommet;
						coutm=dijkstra.getDistance(sommet);
					}
				}
				//if(coutm!=null)
				//{
					successeurs=graph.listSucc(m);
					for(InterfaceSommet<S> y : successeurs.getEnsemble())
					{
						if(ensembleNonTraite.existeSommet(y))
						{
							InterfaceCout cout= graph.getCout(m, y).get();
							distance=dijkstra.getDistance(m).getValeur()+cout.getValeur();
							if(distance<dijkstra.getDistance(y).getValeur())
							{
								dijkstra.getDistance(y).setValeur(distance);
								dijkstra.modifPredecesseur(y, m);
							}
						}
					}
				//}
			}
			ensembleNonTraite.supprElement(m);
		}
		return dijkstra;
		//FIXME revoir tout le dijkstra => complexité trop élévée
	}
	
	public static <S> InterfaceTableauPlusCC<S>  ordinalRacine(InterfaceGrapheValue<S> graph,InterfaceSommet<S> sommet0)
	{
		InterfaceGrapheValue<S> remainingGraph=Factory.grapheValue(graph);//Le reste du graphe qu'il reste à parcourir
		InterfaceEnsembleSommet<S> entrees;//Liste des points d'entrée
		InterfaceEnsembleSommet<S> predecesseurs;//Liste de prédecesseurs
		InterfaceSommet<S> y,m;//y : le sommet que l'on traite m prédécesseur de y au coup le plus faible pour atteindre y,
		InterfaceTableauPlusCC<S> ordinalRacine=Factory.tableauPlusCC(sommet0,graph);
		if(!graph.listPred(sommet0).equals(Factory.ensembleSommet()))
		{
			System.err.println(sommet0+" n'est pas la racine de "+graph);
		}
		remainingGraph.supprSommet(sommet0);
		entrees=remainingGraph.pointsEntree();
		while(!remainingGraph.isEmpty()&&!entrees.isEmpty())
		{
			y=entrees.pickSommet();
			predecesseurs=graph.listPred(y);
			InterfaceCout coutm=null;
			m=null;
			InterfaceCout coutp;
			for(InterfaceSommet<S> sommet : predecesseurs.getEnsemble())
			{
				coutp=InterfaceCout.somme(ordinalRacine.getDistance(sommet),graph.getCout(sommet, y).get());
				if(coutm==null)
				{
					m=sommet;
					coutm=coutp;
				}
				else
				{
					if(coutm.getValeur()>coutp.getValeur())
					{
						m=sommet;
						coutm=coutp;
					}
				}
			}
			ordinalRacine.modifDistance(y, InterfaceCout.somme(ordinalRacine.getDistance(m), graph.getCout(m, y).get()));
			ordinalRacine.modifPredecesseur(y, m);
			remainingGraph.supprSommet(y);
			entrees=remainingGraph.pointsEntree();
		}
		if(!remainingGraph.isEmpty())
		{
			System.err.println("Le graphe avait un circuit, le tableau obtenu n'est donc pas complet");
		}
		return ordinalRacine;
	}
	
	public static <S> InterfaceTableauPlusCC<S> bellmanFord(InterfaceGrapheValue<S> graph, InterfaceSommet<S> sommet0)
	{
		InterfaceTableauPlusCC<S> bellmanFord=Factory.tableauPlusCC(sommet0,graph);//Tableau final
		InterfaceGrapheValue<S> graphM=Factory.grapheValue(graph);//G moins x0
		InterfaceCout cout;//cout entre les sommet actuel et son successeur
		graphM.supprSommet(sommet0);//FIXME Gm n'est pas utilisé par la suite => pas normal
		for(InterfaceSommet<S> x : graph.getEnsembleSommet().getEnsemble())
		{
			for(InterfaceSommet<S> y : graph.listSucc(x).getEnsemble())
			{
				if(bellmanFord.getDistance(x)!=null)
				{
					cout=InterfaceCout.somme(bellmanFord.getDistance(x), graph.getCout(x, y).get());
					if(bellmanFord.getDistance(y)==null)
					{
						bellmanFord.modifPredecesseur(y, x);
						bellmanFord.modifDistance(y, cout);
					}
					else
					{
						if(bellmanFord.getDistance(y).getValeur()>cout.getValeur())
						{
							bellmanFord.modifPredecesseur(y, x);
							bellmanFord.modifDistance(y, cout);
						}
					}
				}
			}
		}
		for(InterfaceSommet<S>x : graph.getEnsembleSommet().getEnsemble())
		{
			for(InterfaceSommet<S> y : graph.listSucc(x).getEnsemble())
			{
				if(bellmanFord.getDistance(y).getValeur()>InterfaceCout.somme(bellmanFord.getDistance(x), graph.getCout(x, y).get()).getValeur())
				{
					System.err.println("Il y a un circuit absorbant dans le graphe, la table obtenue par BellmanFord n'est pas exploitable");
					break;
				}
			}
		}
		return bellmanFord;
	}
	
	public static <S> InterfaceTableauPlusCC<S> ford(InterfaceGrapheValue<S> graph, InterfaceSommet<S> sommet0)
	{
		InterfaceTableauPlusCC<S> ford = Factory.tableauPlusCC(sommet0,graph);
		InterfaceSommet<S> y,z;
		InterfaceEnsembleSommet<S> successeursY;
		boolean absorbant=false;
		Stack<InterfaceSommet<S>> pile=new Stack<>();
		InterfaceEnsembleSommet<S> t = Factory.ensembleSommet();
		InterfaceEnsembleSommet<S> d = Factory.ensembleSommet(graph.getEnsembleSommet());
		d.supprElement(sommet0);
		pile.push(sommet0); 
		InterfaceGrapheValue<S> graphToVisit=Factory.grapheValue(graph);
		while(!absorbant&&!pile.empty())
		{
			y=pile.peek();
			successeursY=graphToVisit.listSucc(y);
			if (!successeursY.isEmpty())
			{
				z=successeursY.pickSommet();
				graphToVisit.supprArc(y, z);//Supprimer l'arc ici empêche l'arc de pouvoir être revisité
				if (d.existeSommet(z))
				{
					d.supprElement(z);
					ford.modifDistance(z, InterfaceCout.somme(ford.getDistance(y), graph.getCout(y,z).get()));
					ford.modifPredecesseur(z, y);
					pile.push(z);
				}
				else
				{
					if(t.existeSommet(z))
					{
						if(ford.getDistance(z).getValeur()>InterfaceCout.somme(ford.getDistance(y), graph.getCout(y,z).get()).getValeur())
						{
							t.supprElement(z);
							for (InterfaceSommet<S> sommet : graph.listSucc(z).getEnsemble())
							{
								graphToVisit.ajouteArc(z, sommet, graph.getCout(z, sommet).get());
							}
							ford.modifDistance(z, InterfaceCout.somme(ford.getDistance(y), graph.getCout(y,z).get()));
							ford.modifPredecesseur(z, y);
							pile.push(z);
						}
					}
					else
					{
						if(ford.getDistance(z).getValeur()>InterfaceCout.somme(ford.getDistance(y), graph.getCout(y,z).get()).getValeur())
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
			System.err.println("Circuit absorbant détécté, on ne peut pas calculer le plusCC");
		}
		return ford;
	}
}