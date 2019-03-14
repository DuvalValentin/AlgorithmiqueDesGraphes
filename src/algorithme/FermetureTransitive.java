package algorithme;


import factory.Factory;
import graphElements.Elements.GrapheNonValue;
import graphElements.Interfaces.*;

public class FermetureTransitive
{
	private static <S> InterfaceGrapheNonValue<S> Composition (InterfaceGrapheNonValue<S> G1,InterfaceGrapheNonValue<S> G2) 
	{
		assert G1.getX().equals(G2.getX()) : "La composition ne marche que pour deux Graphe se reposant sur le même ensemble de Sommet";
		InterfaceEnsembleSommet<S>X=G1.getX();//L'ensemble de sommet
		InterfaceEnsembleArcNonValue<S>Gamma1=G1.getGamma();//L'ensemble d'arc du graphe1
		InterfaceEnsembleArcNonValue<S>Gamma2=G2.getGamma();//L'ensemble d'arc du graphe2
		InterfaceGrapheNonValue<S> G3 =Factory.grapheNonValue(X, Factory.ensembleArcNonValue());//Le graphe final
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
						InterfaceArc<S> arc3=Factory.arcNonValue(x,y);
						G3.ajouteArc(arc3);
						break;
					}
				}
			}
		}
		return G3;
	}
	
	public static <S> InterfaceGrapheNonValue<S> PuissanceDeGraphe (InterfaceGrapheNonValue<S> G)
	{
		InterfaceGrapheNonValue<S> P;//Le graphe résultat
		InterfaceGrapheNonValue<S> R;
		P=Factory.grapheNonValue();
		R=Factory.grapheNonValue(G);
		while(!R.equals(P))
		{
			P=Factory.grapheNonValue(R);
			R= (InterfaceGrapheNonValue<S>) GrapheNonValue.union(R, Composition(G,R));
		}
		return(P);
	}
	//Non valué
	public static <S> InterfaceGrapheNonValue<S> Roy_Warshall(InterfaceGrapheNonValue<S> G)
	{
		InterfaceEnsembleSommet<S>X=G.getX();
		InterfaceGrapheNonValue<S> RW=Factory.grapheNonValue(G);
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
							RW.ajouteArc(x, y);
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
		InterfaceCout Cxz,Czy,Cxy;
		for(InterfaceSommet<S> z : X.getEnsemble()) 
		{
			for(InterfaceSommet<S> x : X.getEnsemble())
			{
				Cxz=RW.getCout(x,z);
				if(Cxz!=null)//Si getValue rend faux c'est que l'arc x,z n'existe pas, 
				{
					for(InterfaceSommet<S> y : X.getEnsemble())
					{
						Czy=RW.getCout(z,y);
						if(Czy!=null)
						{
							Cxy=InterfaceCout.somme(Cxz, Czy);
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