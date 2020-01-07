package algorithme;


import java.util.Optional;

import factory.Factory;
import graphelements.interfaces.*;

public class FermetureTransitive
{
	private FermetureTransitive(){}
	
	@SuppressWarnings("unchecked")
	private static <S,A extends InterfaceArc<S>> InterfaceGraphe<S,A> composition (InterfaceGraphe<S,A> graph1,InterfaceGraphe<S,A> graph2) 
	{
		InterfaceEnsembleSommet<S>ensembleSommet=graph1.getEnsembleSommet();//L'ensemble de sommet
		InterfaceEnsembleArc<S,A>gamma1=graph1.getGamma();//L'ensemble d'arc du graphe1
		InterfaceEnsembleArc<S,A>gamma2=graph2.getGamma();//L'ensemble d'arc du graphe2
		InterfaceGraphe<S,A> g3 =Factory.graphe(ensembleSommet, Factory.ensembleArc(gamma1.getClass().getSimpleName()));//Le graphe final
		for(InterfaceSommet<S> x : ensembleSommet.getEnsemble())
		{
			for(InterfaceSommet<S> y : ensembleSommet.getEnsemble())
			{
				for(InterfaceSommet<S> z : ensembleSommet.getEnsemble())
				{
					InterfaceArc<S> arc1=Factory.arcNonValue(x,z);
					InterfaceArc<S> arc2=Factory.arcNonValue(z,y);
					if(gamma1.existeArc(arc1)&&gamma2.existeArc(arc2))
					{
						if(g3 instanceof InterfaceGrapheNonValue)
						{
							((InterfaceGrapheNonValue<S>)g3).ajouteArc(x,y);
						}
						else if (g3 instanceof InterfaceGrapheValue)
						{
							InterfaceCout cout1=((InterfaceGrapheValue<S>)graph1).getCout(x, z).get();
							InterfaceCout cout2=((InterfaceGrapheValue<S>)graph2).getCout(z, y).get();
							InterfaceCout cout=Factory.cout(cout1.getValeur()+cout2.getValeur());
							((InterfaceGrapheValue<S>)g3).ajouteArc(x,y,cout);
						}
						
						break;
					}
				}
			}
		}
		return g3;
	}
	
	public static <S,A extends InterfaceArc<S>> InterfaceGraphe<S,A> puissanceDeGraphe (InterfaceGraphe<S,A> graphe)
	{
		InterfaceGraphe<S,A> p;//Le graphe résultat
		InterfaceGraphe<S,A> r;
		p=Factory.graphe(graphe.getClass().getSimpleName());
		r=Factory.graphe(graphe);
		while(!r.equals(p))
		{
			p=Factory.graphe(r);
			r= InterfaceGraphe.union(r, composition(graphe,r));
		}
		return(p);
	}
	//Non valué
	@SuppressWarnings("unchecked")
	public static <S,A extends InterfaceArc<S>> InterfaceGraphe<S,A> royWarshall(InterfaceGraphe<S,A> graphe)
	{
		InterfaceEnsembleSommet<S>ensembleSommet=graphe.getEnsembleSommet();
		InterfaceGraphe<S,A> royWarshal=Factory.graphe(graphe);
		for(InterfaceSommet<S> z : ensembleSommet.getEnsemble())//sommet pivot
		{
			for(InterfaceSommet<S> x : ensembleSommet.getEnsemble())//sommet précédant z
			{
				if(royWarshal.existeArc(x,z))
				{
					for(InterfaceSommet<S> y : ensembleSommet.getEnsemble())//sommet procédant z
					{
						if(royWarshal.existeArc(z,y))
						{
							if(royWarshal instanceof InterfaceGrapheNonValue)
							{
								((InterfaceGrapheNonValue<S>)royWarshal).ajouteArc(x,y);
							}
							else if (royWarshal instanceof InterfaceGrapheValue)
							{
								Optional<InterfaceCout> oCxz=((InterfaceGrapheValue<S>)royWarshal).getCout(x, z);
								Optional<InterfaceCout> oCzy=((InterfaceGrapheValue<S>)royWarshal).getCout(z, y);
								InterfaceCout cout=InterfaceCout.somme(oCxz.get(), oCzy.get());
								((InterfaceGrapheValue<S>)royWarshal).ajouteArc(x,y,cout);
							}
						}
					}
				}
			}
		}
		return royWarshal;
	}
	
	//Valué
	/*public static <S> InterfaceGrapheValue<S> Roy_Warshall(InterfaceGrapheValue<S> G)
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
	}*/
	
	//TODO Roy-Warshall avec routage
}