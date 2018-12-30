package graphElements;

public class Graphe<S> extends AbstractGraphe<S,Arc<S>> implements Cloneable
{
	
	//Constructeurs
	public Graphe()
	{
		super();
	}
	
	public Graphe(EnsembleSommet<S> x,EnsembleArc<S> gamma)
	{
		super(x,gamma);
	}
	//TODO Pareil que le clonnage => problème ?
	public Graphe(Graphe<S> G)
	{
		super(G);
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
}