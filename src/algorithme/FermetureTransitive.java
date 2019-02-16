package algorithme;

import graphElements.Elements.*;

public class FermetureTransitive
{
	private static <S> GrapheNonValue<S> Composition (GrapheNonValue<S> G1,GrapheNonValue<S> G2) 
	{
		assert G1.getX().equals(G2.getX()) : "La composition ne marche que pour deux Graphe se reposant sur le même ensemble de Sommet";
		EnsembleSommet<S>X=G1.getX();//L'ensemble de sommet
		EnsembleArcNonValue<S>Gamma1=G1.getGamma();//L'ensemble d'arc du graphe1
		EnsembleArcNonValue<S>Gamma2=G2.getGamma();//L'ensemble d'arc du graphe2
		GrapheNonValue<S> G3 = new GrapheNonValue<S>(X,new EnsembleArcNonValue<S>());//Le graphe final
		for(Sommet<S> x : X.getEnsemble())
		{
			for(Sommet<S> y : X.getEnsemble())
			{
				for(Sommet<S> z : X.getEnsemble())
				{
					Arc<S> arc1=new Arc<S>(x,z);
					Arc<S> arc2=new Arc<S>(z,y);
					if(Gamma1.existeArc(arc1)&&Gamma2.existeArc(arc2))
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
	
	public static <S> GrapheNonValue<S> PuissanceDeGraphe (GrapheNonValue<S> G)  throws CloneNotSupportedException
	{
		GrapheNonValue<S> P;//Le graphe résultat
		GrapheNonValue<S> R;
		P=new GrapheNonValue<S>();
		R=new GrapheNonValue<S>(G);
		while(!R.equals(P))
		{
			P=new GrapheNonValue<S>(R);
			R= (GrapheNonValue<S>) GrapheNonValue.union(R, Composition(G,R));
		}
		return(P);
	}
	//Non valué
	public static <S> GrapheNonValue<S> Roy_Warshall(GrapheNonValue<S> G)
	{
		EnsembleSommet<S>X=G.getX();
		GrapheNonValue<S> RW=new GrapheNonValue<S>(G);
		for(Sommet<S> z : X.getEnsemble())//sommet pivot
		{
			for(Sommet<S> x : X.getEnsemble())//sommet précédant z
			{
				if(RW.existeArc(x,z))
				{
					for(Sommet<S> y : X.getEnsemble())//sommet procédant z
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
		for(Sommet<S> z : X.getEnsemble()) 
		{
			for(Sommet<S> x : X.getEnsemble())
			{
				Cxz=RW.getCout(x,z);
				if(Cxz!=null)//Si getValue rend faux c'est que l'arc x,z n'existe pas, 
				{
					for(Sommet<S> y : X.getEnsemble())
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