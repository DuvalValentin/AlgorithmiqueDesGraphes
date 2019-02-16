package algorithme;

import java.util.Stack;

import graphElements.Elements.*;

public class PlusCC
{
	public static <S> TableauPlusCC<S> Dijkstra(GrapheValue<S> G,Sommet<S> x0)
	{
		EnsembleSommet<S>M=new EnsembleSommet<S>(G.getX());//Liste des sommets non traités
		EnsembleSommet<S>S=new EnsembleSommet<S>(G.listSucc(x0));//Liste de Successeur du sommet en cours
		TableauPlusCC<S> Dijkstra=new TableauPlusCC<S>(x0,G);//Tableau résultat
		float v;//valeur de la distance par un sommet intermédiaire
		M.supprSommet(x0);
		S.supprSommet(x0);//Supprime x si x se succède à lui même
		while(!M.isEmpty())
		{
			Cout coutm=null;
			Sommet<S> m=null;
			for(Sommet<S> sommet : M.getEnsemble())
			{
				if (coutm==null)
				{
					m=sommet;
					coutm=Dijkstra.getDistance(sommet);
				}
				else
				{
					if(Dijkstra.getDistance(sommet)!=null&&Dijkstra.getDistance(sommet).getValeur()<coutm.getValeur())
					{
						m=sommet;
						coutm=Dijkstra.getDistance(sommet);
					}
				}
				if(coutm!=null)
				{
					S=G.listSucc(m);
					for(Sommet<S> y : S.getEnsemble())
					{
						if(M.existeSommet(y))
						{
							Cout cout= G.getCout(m, y);
							v=Dijkstra.getDistance(m).getValeur()+cout.getValeur();
							if(v<Dijkstra.getDistance(y).getValeur())
							{
								Dijkstra.getDistance(y).setValeur(v);
								Dijkstra.modifPredecesseur(y, m);
							}
						}
					}
				}
			}
			M.supprSommet(m);
		}
		return Dijkstra;
	}
	
	public static <S> TableauPlusCC<S>  OrdinalRacine(GrapheValue<S> G,Sommet<S> x0)
	{
		GrapheValue<S> H=new GrapheValue<S>(G);//Le reste du graphe qu'il reste à parcourir
		EnsembleSommet<S> E;//Liste des points d'entrée
		EnsembleSommet<S> P;//Liste de prédecesseurs
		Sommet<S> y,m;//y : le sommet que l'on traite m prédécesseur de y au coup le plus faible pour atteindre y,
		TableauPlusCC<S> OrdinalRacine=new TableauPlusCC<S>(x0,G);
		H.supprSommet(x0);
		E=H.pointsEntree();
		while(!H.isEmpty()&&!E.isEmpty())
		{
			y=E.firstSommet();
			P=G.listPred(y);
			Cout coutm=null;
			m=null;
			Cout coutp;
			for(Sommet<S> sommet : P.getEnsemble())
			{
				coutp=Cout.somme(OrdinalRacine.getDistance(sommet),G.getCout(sommet, y));
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
			OrdinalRacine.modifDistance(y, Cout.somme(OrdinalRacine.getDistance(m), G.getCout(m, y)));
			OrdinalRacine.modifPredecesseur(y, m);
			H.supprSommet(y);
			E=H.pointsEntree();
		}
		if(!H.isEmpty())
		{
			System.err.println("Le graphe avait un circuit, le tableau obtenu n'est donc pas complet");
		}
		return OrdinalRacine;
	}
	
	public static <S> TableauPlusCC<S> BellmanFord(GrapheValue<S> G, Sommet<S> x0)
	{
		TableauPlusCC<S> BellmanFord=new TableauPlusCC<S>(x0,G);//Tableau final
		GrapheValue<S> Gm=new GrapheValue<S>(G);//G moins x0
		Cout cout;//cout entre les sommet actuel et son successeur
		Gm.supprSommet(x0);
		for(Sommet<S> x : G.getX().getEnsemble())
		{
			for(Sommet<S> y : G.listSucc(x).getEnsemble())
			{
				cout=Cout.somme(BellmanFord.getDistance(x), G.getCout(x, y));
				if(BellmanFord.getDistance(y)==null)
				{
					BellmanFord.modifPredecesseur(y, x);
					BellmanFord.modifDistance(y, cout);
				}
				else
				{
					if(BellmanFord.getDistance(y).getValeur()>cout.getValeur())
					{
						BellmanFord.modifPredecesseur(y, x);
						BellmanFord.modifDistance(y, cout);
					}
				}
				
			}
		}
		for(Sommet<S>x : G.getX().getEnsemble())
		{
			for(Sommet<S> y : G.listSucc(x).getEnsemble())
			{
				if(BellmanFord.getDistance(y).getValeur()>Cout.somme(BellmanFord.getDistance(x), G.getCout(x, y)).getValeur())
				{
					System.err.println("Il y a un circuit absorbant dans le graphe, la table obtenue par BellmanFord n'est pas exploitable");
				}
			}
		}
		return BellmanFord;
	}
	
	public static <S> TableauPlusCC<S> Ford(GrapheValue<S> G, Sommet<S> x0)
	{
		TableauPlusCC<S> Ford = new TableauPlusCC<S>(x0,G);
		Sommet<S> y,z;
		EnsembleSommet<S> Y;
		boolean absorbant=false;
		Stack<Sommet<S>> A=new Stack<Sommet<S>>();
		EnsembleSommet<S> T = new EnsembleSommet<S>();
		EnsembleSommet<S> D = new EnsembleSommet<S>(G.getX());
		D.supprSommet(x0);
		A.push(x0); 
		GrapheValue<S> Gavisiter=new GrapheValue<S>(G);
		while(!absorbant&&!A.empty())
		{
			y=A.peek();
			Y=Gavisiter.listSucc(y);
			if (!Y.isEmpty())
			{
				z=Y.firstSommet();
				Gavisiter.supprArc(y, z);//Supprimer l'arc ici empêche l'arc de pouvoir être revisité
				if (D.existeSommet(z))
				{
					D.supprSommet(z);
					Ford.modifDistance(z, Cout.somme(Ford.getDistance(y), G.getCout(y,z)));
					Ford.modifPredecesseur(z, y);
					A.push(z);
				}
				else
				{
					if(T.existeSommet(z))
					{
						if(Ford.getDistance(z).getValeur()>Cout.somme(Ford.getDistance(y), G.getCout(y,z)).getValeur())
						{
							T.supprSommet(z);
							for (Sommet<S> sommet : G.listSucc(z).getEnsemble())
							{
								Gavisiter.ajouteArc(z, sommet, G.getCout(z, sommet));
							}
							Ford.modifDistance(z, Cout.somme(Ford.getDistance(y), G.getCout(y,z)));
							Ford.modifPredecesseur(z, y);
							A.push(z);
						}
					}
					else
					{
						if(Ford.getDistance(z).getValeur()>Cout.somme(Ford.getDistance(y), G.getCout(y,z)).getValeur())
						{
							absorbant=true;
						}
					}
				}
			}
			else
			{
				T.ajouteSommet(y);
				A.pop();
			}
		}
		if(absorbant)
		{
			System.out.println("Circuit absorbant détécté, on ne peut pas calculer le plusCC");
		}
		return Ford;
	}
}