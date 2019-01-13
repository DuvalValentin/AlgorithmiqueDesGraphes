package graphElements.Elements;

import graphElements.Abstract.AbstractGraphe;
import graphElements.Interfaces.InterfaceGraphe;

public class Graphe<S> extends AbstractGraphe<S,Arc<S>> implements Cloneable,InterfaceGraphe<S>
{
	//Constructeurs
	public Graphe()
	{
		super();
		setGamma(new EnsembleArc<S>());
	}
	public Graphe(EnsembleSommet<S> x,EnsembleArc<S> gamma)
	{
		super(new EnsembleSommet<S>(x),new EnsembleArc<S>(gamma));
	}
	public Graphe(Graphe<S> G)
	{
		super(G);
	}
	//Getter
	@Override
	public EnsembleArc<S> getGamma()
	{
		return new EnsembleArc<S>((EnsembleArc<S>)super.getGamma());
	}
	//Ajout d'élément
	@Override
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		ajouteArc(new Arc<S>(depart,arrivee));
	}
}