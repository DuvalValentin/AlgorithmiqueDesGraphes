package algorithme;

import java.util.HashMap;

import graphElements.Elements.*;

public class PlusCC
{
	//très très moche
	public static <S> void Dijkstra(GrapheValue<S> G,Sommet<S> x)
	{
		HashMap<Sommet<S>,Cout> d=new HashMap<Sommet<S>,Cout>();
		HashMap<Sommet<S>,Sommet<S>> pred= new HashMap<Sommet<S>,Sommet<S>>();
		EnsembleSommet<S>M;//Liste des sommets non traités
		EnsembleSommet<S>S;//Liste de Successeur du sommet en cours
		float v;
		for(Sommet<S> sommet : G.getX())
		{
			d.put(sommet, null);
			pred.put(sommet, x);
		}
		for(Sommet<S> sommet : G.listPred(x))
		{
			d.replace(sommet, G.getCout(sommet, x));
		}
		M=new EnsembleSommet<S>(G.getX());
		M.supprSommet(x);
		while(!M.isEmpty())
		{
			Cout coutm=null;
			Sommet<S> m=null;
			for(Sommet<S> sommet : M)
			{
				if (coutm==null)
				{
					m=sommet;
					coutm=d.get(sommet);
				}
				else
				{
					if(d.get(sommet)!=null&&d.get(sommet).getValeur()<coutm.getValeur())
					{
						m=sommet;
						coutm=d.get(sommet);
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
							v=d.get(m).getValeur()+cout.getValeur();
							if(v<d.get(y).getValeur())
							{
								d.get(y).setValeur(v);
							}
						}
					}
				}
			}
			M.supprSommet(m);
		}
	}
}