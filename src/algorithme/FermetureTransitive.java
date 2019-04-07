package algorithme;


import java.util.Optional;

import factory.Factory;
import graphElements.Interfaces.*;

public class FermetureTransitive
{
	@SuppressWarnings("unchecked")
	private static <S,A extends InterfaceArc<S>> InterfaceGraphe<S,A> Composition (InterfaceGraphe<S,A> G1,InterfaceGraphe<S,A> G2) 
	{
		assert G1.getX().equals(G2.getX()) : "La composition ne marche que pour deux Graphe se reposant sur le même ensemble de Sommet";
		InterfaceEnsembleSommet<S>X=G1.getX();//L'ensemble de sommet
		InterfaceEnsembleArc<S,A>Gamma1=G1.getGamma();//L'ensemble d'arc du graphe1
		InterfaceEnsembleArc<S,A>Gamma2=G2.getGamma();//L'ensemble d'arc du graphe2
		InterfaceGraphe<S,A> G3 =Factory.graphe(X, Factory.ensembleArc(Gamma1.getClass().getSimpleName()));//Le graphe final
		for(InterfaceSommet<S> x : X.getEnsemble())
		{
			for(InterfaceSommet<S> y : X.getEnsemble())
			{
				for(InterfaceSommet<S> z : X.getEnsemble())
				{
					InterfaceArc<S> arc1=Factory.arcNonValue(x,z);
					InterfaceArc<S> arc2=Factory.arcNonValue(z,y);
					if(Gamma1.existeArc(arc1)&&Gamma2.existeArc(arc2))
					{
						if(G3.getClass().getSimpleName().equals("GrapheNonValue"))
						{
							InterfaceArc<S> arc3=Factory.arcNonValue(x,y);
							((InterfaceGrapheNonValue<S>)G3).ajouteArc(arc3);
						}
						else
						{
							//TODO mettre une méthode pour les graphes valués
						}
						
						break;
					}
				}
			}
		}
		return G3;
	}
	
	public static <S,A extends InterfaceArc<S>> InterfaceGraphe<S,A> PuissanceDeGraphe (InterfaceGraphe<S,A> G)
	{
		InterfaceGraphe<S,A> P;//Le graphe résultat
		InterfaceGraphe<S,A> R;
		P=Factory.graphe(G.getClass().getSimpleName());
		R=Factory.graphe(G);
		while(!R.equals(P))
		{
			P=Factory.graphe(R);
			R= InterfaceGraphe.union(R, Composition(G,R));
		}
		return(P);
	}
	//Non valué
	@SuppressWarnings("unchecked")
	public static <S,A extends InterfaceArc<S>> InterfaceGraphe<S,A> Roy_Warshall(InterfaceGraphe<S,A> G)
	{
		InterfaceEnsembleSommet<S>X=G.getX();
		InterfaceGraphe<S,A> RW=Factory.graphe(G);
		for(InterfaceSommet<S> z : X.getEnsemble())//sommet pivot
		{
			for(InterfaceSommet<S> x : X.getEnsemble())//sommet précédant z
			{
				if(RW.existeArc(x,z))
				{
					for(InterfaceSommet<S> y : X.getEnsemble())//sommet procédant z
					{
						if(RW.existeArc(z,y))
						{
							if(G.getClass().getSimpleName().equals("GrapheNonValue"))
							{
								((InterfaceGrapheNonValue<S>)RW).ajouteArc(x, y);
							}
							else
							{
								//TODO mettre une méthode pour les graphes valués
							}
						}
					}
				}
			}
		}
		return RW;
	}
	
	//Valué
	public static <S> InterfaceGrapheValue<S> Roy_Warshall(InterfaceGrapheValue<S> G)
	{
		InterfaceEnsembleSommet<S>X=G.getX();
		InterfaceGrapheValue<S> RW=Factory.grapheValue(G);
		Optional<InterfaceCout> oCxz,oCzy;
		InterfaceCout Cxy;
		for(InterfaceSommet<S> z : X.getEnsemble()) 
		{
			for(InterfaceSommet<S> x : X.getEnsemble())
			{
				oCxz=RW.getCout(x,z);
				if(oCxz.isPresent())//Si on récupère un coup c'est que l'arc est présent
				{
					for(InterfaceSommet<S> y : X.getEnsemble())
					{
						oCzy=RW.getCout(z,y);
						if(oCzy.isPresent())
						{
							Cxy=InterfaceCout.somme(oCxz.get(), oCzy.get());
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