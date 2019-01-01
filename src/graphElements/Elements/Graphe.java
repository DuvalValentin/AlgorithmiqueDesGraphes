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
		super(x,gamma);
	}
	public Graphe(Graphe<S> G)
	{
		super(G);
	}
	
	@Override
	public EnsembleArc<S> getGamma()
	{
		return (EnsembleArc<S>)super.getGamma();
	}
	
	//Clonage
	@Override
	public Graphe<S> clone()
	{
		return (new Graphe<S>(getX().clone(),(EnsembleArc<S>)getGamma().clone()));
	}
	
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		ajouteArc(new Arc<S>(depart,arrivee));
	}
	@Override
	public void supprArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		((EnsembleArc<S>)Gamma).supprArc(depart, arrivee);
	}
}