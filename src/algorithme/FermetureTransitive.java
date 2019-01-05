package algorithme;

import graphElements.Elements.*;

public class FermetureTransitive
{
	private static <S> Graphe<S> Composition (Graphe<S> G1,Graphe<S> G2)
	{
		EnsembleSommet<S>X=G1.getX();
		EnsembleArc<S>Gamma1=G1.getGamma();
		EnsembleArc<S>Gamma2=G2.getGamma();
		Graphe<S> G3 = new Graphe<S>(X,new EnsembleArc<S>());
		for(Sommet<S> x : X)
		{
			for(Sommet<S> y : X)
			{
				for(Sommet<S> z : X)
				{
					Arc<S> arc1=new Arc<S>(x,z);
					Arc<S> arc2=new Arc<S>(z,y);
					if(Gamma1.contains(arc1)&&Gamma2.contains(arc2))
					{
						Arc<S> arc3=new Arc<S>(x,y);
						G3.ajouteArc(arc3);
						break;
					}
				}
			}
		}
		return G3;
	}
	
	public static <S> Graphe<S> PuissanceDeGraphe (Graphe<S> G)
	{
		Graphe<S> P;
		Graphe<S> R;
		P=new Graphe<S>();
		R=new Graphe<S>(G);
		while(!R.equals(P))
		{
			P=R.clone();
			R.union(Composition(G,R));
		}
		return(P);
	}
	//Non valué
	public static <S> Graphe<S> Roy_Warshall(Graphe<S> G)
	{
		EnsembleSommet<S>X=G.getX();
		Graphe<S> RW=new Graphe<S>(G);
		for(Sommet<S> z : X) 
		{
			for(Sommet<S> x : X)
			{
				if(RW.existeArc(x,z))
				{
					for(Sommet<S> y : X)
					{
						if(RW.existeArc(z,y))
						{
							RW.ajouteArc(x, y);
						}
					}
				}
			}
		}
		return RW;
	}
	
	//Valué
	public static <S> GrapheValue<S> Roy_Warshall(GrapheValue<S> G)
	{
		EnsembleSommet<S>X=G.getX();
		GrapheValue<S> RW=new GrapheValue<S>(G);
	  Cout Cxz,Czy,Cxy;
		for(Sommet<S> z : X) 
		{
			for(Sommet<S> x : X)
			{
				Cxz=RW.getCout(x,z);
				if(Cxz!=null)//Si getValue rend faux c'est que l'arc x,z n'existe pas, 
				{
					for(Sommet<S> y : X)
					{
						Czy=RW.getCout(z,y);
						if(Czy!=null)
						{
							Cxy=Cout.somme(Cxz, Czy);
							RW.ajouteArc(x, y, Cxy);
						}
					}
				}
			}
		}
		return RW;
	}
	
	//TODO Roy-Warshall avec routage
}