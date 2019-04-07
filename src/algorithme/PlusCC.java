package algorithme;

import java.util.Stack;

import factory.Factory;
import graphElements.Interfaces.*;

/*TODO créer une classe d'exception si le graphe passé en paramètre ne respecte pas les préconditions
 */
public class PlusCC
{
	public static <S> InterfaceTableauPlusCC<S> Dijkstra(InterfaceGrapheValue<S> G,InterfaceSommet<S> x0)
	{
		InterfaceEnsembleSommet<S>M=Factory.ensembleSommet(G.getX());//Liste des sommets non traités
		InterfaceEnsembleSommet<S>S=Factory.ensembleSommet(G.listSucc(x0));//Liste de Successeur du sommet en cours
		InterfaceTableauPlusCC<S> Dijkstra=Factory.tableauPlusCC(x0,G);//Tableau résultat
		float v;//valeur de la distance par un sommet intermédiaire
		M.supprSommet(x0);
		S.supprSommet(x0);//Supprime x si x se succède à lui même
		while(!M.isEmpty())
		{
			InterfaceCout coutm=null;
			InterfaceSommet<S> m=null;
			for(InterfaceSommet<S> sommet : M.getEnsemble())
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
				//if(coutm!=null)
				//{
					S=G.listSucc(m);
					for(InterfaceSommet<S> y : S.getEnsemble())
					{
						if(M.existeSommet(y))
						{
							InterfaceCout cout= G.getCout(m, y).get();
							v=Dijkstra.getDistance(m).getValeur()+cout.getValeur();
							if(v<Dijkstra.getDistance(y).getValeur())
							{
								Dijkstra.getDistance(y).setValeur(v);
								Dijkstra.modifPredecesseur(y, m);
							}
						}
					}
				//}
			}
			M.supprSommet(m);
		}
		return Dijkstra;
	}
	
	public static <S> InterfaceTableauPlusCC<S>  OrdinalRacine(InterfaceGrapheValue<S> G,InterfaceSommet<S> x0)
	{
		InterfaceGrapheValue<S> H=Factory.grapheValue(G);//Le reste du graphe qu'il reste à parcourir
		InterfaceEnsembleSommet<S> E;//Liste des points d'entrée
		InterfaceEnsembleSommet<S> P;//Liste de prédecesseurs
		InterfaceSommet<S> y,m;//y : le sommet que l'on traite m prédécesseur de y au coup le plus faible pour atteindre y,
		InterfaceTableauPlusCC<S> OrdinalRacine=Factory.tableauPlusCC(x0,G);
		if(!G.listPred(x0).equals(Factory.ensembleSommet()))
		{
			System.err.println(x0+" n'est pas la racine de "+G);
		}
		H.supprSommet(x0);
		E=H.pointsEntree();
		while(!H.isEmpty()&&!E.isEmpty())
		{
			y=E.pickSommet();
			P=G.listPred(y);
			InterfaceCout coutm=null;
			m=null;
			InterfaceCout coutp;
			for(InterfaceSommet<S> sommet : P.getEnsemble())
			{
				coutp=InterfaceCout.somme(OrdinalRacine.getDistance(sommet),G.getCout(sommet, y).get());
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
			OrdinalRacine.modifDistance(y, InterfaceCout.somme(OrdinalRacine.getDistance(m), G.getCout(m, y).get()));
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
	
	public static <S> InterfaceTableauPlusCC<S> BellmanFord(InterfaceGrapheValue<S> G, InterfaceSommet<S> x0)
	{
		InterfaceTableauPlusCC<S> BellmanFord=Factory.tableauPlusCC(x0,G);//Tableau final
		InterfaceGrapheValue<S> Gm=Factory.grapheValue(G);//G moins x0
		InterfaceCout cout;//cout entre les sommet actuel et son successeur
		Gm.supprSommet(x0);
		for(InterfaceSommet<S> x : G.getX().getEnsemble())
		{
			for(InterfaceSommet<S> y : G.listSucc(x).getEnsemble())
			{
				cout=InterfaceCout.somme(BellmanFord.getDistance(x), G.getCout(x, y).get());
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
		for(InterfaceSommet<S>x : G.getX().getEnsemble())
		{
			for(InterfaceSommet<S> y : G.listSucc(x).getEnsemble())
			{
				if(BellmanFord.getDistance(y).getValeur()>InterfaceCout.somme(BellmanFord.getDistance(x), G.getCout(x, y).get()).getValeur())
				{
					System.err.println("Il y a un circuit absorbant dans le graphe, la table obtenue par BellmanFord n'est pas exploitable");
				}
			}
		}
		return BellmanFord;
	}
	
	public static <S> InterfaceTableauPlusCC<S> Ford(InterfaceGrapheValue<S> G, InterfaceSommet<S> x0)
	{
		InterfaceTableauPlusCC<S> Ford = Factory.tableauPlusCC(x0,G);
		InterfaceSommet<S> y,z;
		InterfaceEnsembleSommet<S> Y;
		boolean absorbant=false;
		Stack<InterfaceSommet<S>> A=new Stack<InterfaceSommet<S>>();
		InterfaceEnsembleSommet<S> T = Factory.ensembleSommet();
		InterfaceEnsembleSommet<S> D = Factory.ensembleSommet(G.getX());
		D.supprSommet(x0);
		A.push(x0); 
		InterfaceGrapheValue<S> Gavisiter=Factory.grapheValue(G);
		while(!absorbant&&!A.empty())
		{
			y=A.peek();
			Y=Gavisiter.listSucc(y);
			if (!Y.isEmpty())
			{
				z=Y.pickSommet();
				Gavisiter.supprArc(y, z);//Supprimer l'arc ici empêche l'arc de pouvoir être revisité
				if (D.existeSommet(z))
				{
					D.supprSommet(z);
					Ford.modifDistance(z, InterfaceCout.somme(Ford.getDistance(y), G.getCout(y,z).get()));
					Ford.modifPredecesseur(z, y);
					A.push(z);
				}
				else
				{
					if(T.existeSommet(z))
					{
						if(Ford.getDistance(z).getValeur()>InterfaceCout.somme(Ford.getDistance(y), G.getCout(y,z).get()).getValeur())
						{
							T.supprSommet(z);
							for (InterfaceSommet<S> sommet : G.listSucc(z).getEnsemble())
							{
								Gavisiter.ajouteArc(z, sommet, G.getCout(z, sommet).get());
							}
							Ford.modifDistance(z, InterfaceCout.somme(Ford.getDistance(y), G.getCout(y,z).get()));
							Ford.modifPredecesseur(z, y);
							A.push(z);
						}
					}
					else
					{
						if(Ford.getDistance(z).getValeur()>InterfaceCout.somme(Ford.getDistance(y), G.getCout(y,z).get()).getValeur())
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
			System.err.println("Circuit absorbant détécté, on ne peut pas calculer le plusCC");
		}
		return Ford;
	}
}