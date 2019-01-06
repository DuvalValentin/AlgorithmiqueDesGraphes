package algorithme;

import graphElements.Elements.*;

public class PlusCC
{
	public static <S> TableauPlusCC<S> Dijkstra(GrapheValue<S> G,Sommet<S> x0)
	{
		EnsembleSommet<S>M=new EnsembleSommet<S>(G.getX());//Liste des sommets non traités
		EnsembleSommet<S>S=new EnsembleSommet<S>(G.listSucc(x0));//Liste de Successeur du sommet en cours
		TableauPlusCC<S> Dijkstra=new TableauPlusCC<S>(x0);
		float v;
		M.supprSommet(x0);
		S.supprSommet(x0);//Supprime x si x se succède à lui même
		for(Sommet<S> sommet : M)
		{
			Dijkstra.initSommet(sommet);
		}
		for(Sommet<S> sommet : S)
		{
			Dijkstra.modifCout(sommet, G.getCout(x0, sommet));
		}
		while(!M.isEmpty())
		{
			Cout coutm=null;
			Sommet<S> m=null;
			for(Sommet<S> sommet : M)
			{
				if (coutm==null)
				{
					m=sommet;
					coutm=Dijkstra.getCout(sommet);
				}
				else
				{
					if(Dijkstra.getCout(sommet)!=null&&Dijkstra.getCout(sommet).getValeur()<coutm.getValeur())
					{
						m=sommet;
						coutm=Dijkstra.getCout(sommet);
					}
				}
				if(coutm!=null)
				{
					S=G.listSucc(m);
					for(Sommet<S> y : S)
					{
						if(M.existSommet(y))
						{
							Cout cout= G.getCout(m, y);
							v=Dijkstra.getCout(m).getValeur()+cout.getValeur();
							if(v<Dijkstra.getCout(y).getValeur())
							{
								Dijkstra.getCout(y).setValeur(v);
								Dijkstra.modifSommet(y, m);
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
		GrapheValue<S> H=new GrapheValue<S>(G);
		EnsembleSommet<S> E;
		EnsembleSommet<S> P;//Liste de prédecesseurs
		Sommet<S> y,m;
		TableauPlusCC<S> OrdinalRacine=new TableauPlusCC<S>(x0);
		H.supprSommet(x0);
		E=H.pointsEntree();
		for(Sommet<S> sommet : H.getX())
		{
			OrdinalRacine.initSommet(sommet);
		}
		for(Sommet<S> sommet : H.listSucc(x0))
		{
			OrdinalRacine.modifCout(sommet, G.getCout(x0, sommet));
		}
		while(!H.isEmpty()&&!E.isEmpty())
		{
			y=E.firstSommet();
			//TODO trouver m
			P=G.listPred(y);
			Cout coutm=null;
			m=null;
			Cout coutp;
			for(Sommet<S> sommet : P)
			{
				coutp=Cout.somme(OrdinalRacine.getCout(sommet),G.getCout(sommet, y));
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
			OrdinalRacine.modifCout(y, Cout.somme(OrdinalRacine.getCout(m), G.getCout(m, y)));
			OrdinalRacine.modifSommet(y, m);
			H.supprSommet(y);
			E=H.pointsEntree();
		}
		if(!H.isEmpty())
		{
			System.out.println("Le graphe avait un circuit, le tableau obtenu n'est donc pas complet");
		}
		return OrdinalRacine;
	}
	
	public static <S> TableauPlusCC<S> BellmanFord(GrapheValue<S> G, Sommet<S> x0)
	{
		TableauPlusCC<S> BellmanFord=new TableauPlusCC<S>(x0);
		GrapheValue<S> Gm=new GrapheValue<S>(G);//G moins x0
		Cout cout;
		Gm.supprSommet(x0);
		for(Sommet<S> sommet : Gm.getX())
		{
			BellmanFord.initSommet(sommet);
		}
		for(Sommet<S> sommet : Gm.listSucc(x0))
		{
			BellmanFord.modifCout(sommet, G.getCout(x0, sommet));
		}
		for(Sommet<S> x : G.getX())
		{
			for(Sommet<S> y : G.listSucc(x))
			{
				cout=Cout.somme(BellmanFord.getCout(x), G.getCout(x, y));
				if(BellmanFord.getCout(y)==null)
				{
					BellmanFord.modifSommet(y, x);
					BellmanFord.modifCout(y, cout);
				}
				else
				{
					if(BellmanFord.getCout(y).getValeur()>cout.getValeur())
					{
						BellmanFord.modifSommet(y, x);
						BellmanFord.modifCout(y, cout);
					}
				}
				
			}
		}
		for(Sommet<S>x : G.getX())
		{
			for(Sommet<S> y : G.listSucc(x))
			{
				if(BellmanFord.getCout(y).getValeur()>Cout.somme(BellmanFord.getCout(x), G.getCout(x, y)).getValeur())
				{
					System.out.println("Il y a un circuit absorbant dans le graphe, la table obtenue par BellmanFord n'est pas exploitable");
				}
			}
		}
		return BellmanFord;
		
	}
}