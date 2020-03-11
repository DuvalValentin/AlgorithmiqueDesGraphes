package algorithme;

import factory.Factory;
import graphelements.interfaces.*;

public class FermetureTransitive
{
	private FermetureTransitive()
	{}
	@SuppressWarnings("unchecked")
	private static <S,A extends Arc<S>> Graphe<S,A> composition(Graphe<S,A> graph1, Graphe<S,A> graph2)
	{
		EnsembleSommet<S> ensembleSommet=graph1.getEnsembleSommet();// L'ensemble de sommet
		EnsembleArc<S,A> gamma1=graph1.getGamma();// L'ensemble d'arc du graphe1
		EnsembleArc<S,A> gamma2=graph2.getGamma();// L'ensemble d'arc du graphe2
		Graphe<S,A> finalGraph=Factory.graphe(ensembleSommet,Factory.ensembleArc(gamma1.getClass().getSimpleName()));// Le graphefinal
		for(Sommet<S> x : ensembleSommet.getEnsemble())
		{
			for(Sommet<S> y : ensembleSommet.getEnsemble())
			{
				for(Sommet<S> z : ensembleSommet.getEnsemble())
				{
					Arc<S> arc1=Factory.arcNonValue(x,z);
					Arc<S> arc2=Factory.arcNonValue(z,y);
					if(gamma1.existeArc(arc1)&&gamma2.existeArc(arc2))
					{
						if(finalGraph instanceof GrapheNonValue)
						{
							((GrapheNonValue<S>)finalGraph).ajouteArc(x,y);
						}
						else if(finalGraph instanceof GrapheValue)
						{
							Float cout1=((GrapheValue<S>)graph1).getCout(x,z).orElseThrow();
							Float cout2=((GrapheValue<S>)graph2).getCout(z,y).orElseThrow();
							Float cout=cout1+cout2;
							((GrapheValue<S>)finalGraph).ajouteArc(x,y,cout);
						}
						break;
					}
				}
			}
		}
		return finalGraph;
	}
	public static <S,A extends Arc<S>> Graphe<S,A> puissanceDeGraphe(Graphe<S,A> graphe)
	{
		Graphe<S,A> p;// Le graphe résultat
		Graphe<S,A> r;
		p=Factory.graphe(graphe.getClass().getSimpleName());
		r=Factory.graphe(graphe);
		while(!r.equals(p))
		{
			p=Factory.graphe(r);
			r=Graphe.union(r,composition(graphe,r));
		}
		return(p);
	}
	// Non valué
	@SuppressWarnings("unchecked")
	public static <S,A extends Arc<S>> Graphe<S,A> royWarshall(Graphe<S,A> graphe)
	{
		EnsembleSommet<S> ensembleSommet=graphe.getEnsembleSommet();
		Graphe<S,A> royWarshal=Factory.graphe(graphe);
		for(Sommet<S> z : ensembleSommet.getEnsemble())// sommet pivot
		{
			for(Sommet<S> x : ensembleSommet.getEnsemble())// sommet précédant z
			{
				if(royWarshal.existeArc(x,z))
				{
					for(Sommet<S> y : ensembleSommet.getEnsemble())// sommet procédant z
					{
						if(royWarshal.existeArc(z,y))
						{
							if(royWarshal instanceof GrapheNonValue)
							{
								((GrapheNonValue<S>)royWarshal).ajouteArc(x,y);
							}
							else if(royWarshal instanceof GrapheValue)
							{
								Float cxz=((GrapheValue<S>)royWarshal).getCout(x,z).orElseThrow();
								Float czy=((GrapheValue<S>)royWarshal).getCout(z,y).orElseThrow();
								Float cout=cxz+czy;
								((GrapheValue<S>)royWarshal).ajouteArc(x,y,cout);
							}
						}
					}
				}
			}
		}
		return royWarshal;
	}
	// Valué
	/*
	 * public static <S> InterfaceGrapheValue<S>
	 * Roy_Warshall(InterfaceGrapheValue<S> G) {
	 * InterfaceEnsembleSommet<S>X=G.getX(); InterfaceGrapheValue<S>
	 * RW=Factory.grapheValue(G); Optional<InterfaceCout> oCxz,oCzy; InterfaceCout
	 * Cxy; for(InterfaceSommet<S> z : X.getEnsemble()) { for(InterfaceSommet<S> x
	 * : X.getEnsemble()) { oCxz=RW.getCout(x,z); if(oCxz.isPresent())//Si on
	 * récupère un coup c'est que l'arc est présent { for(InterfaceSommet<S> y :
	 * X.getEnsemble()) { oCzy=RW.getCout(z,y); if(oCzy.isPresent()) {
	 * Cxy=InterfaceCout.somme(oCxz.get(), oCzy.get()); RW.ajouteArc(x, y, Cxy); }
	 * } } } } return RW; }
	 */
	// TODO Roy-Warshall avec routage
}