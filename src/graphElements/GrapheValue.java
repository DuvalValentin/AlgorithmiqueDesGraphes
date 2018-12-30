package graphElements;

public class GrapheValue<S> extends AbstractGraphe<S,ArcValue<S>>
{
	public GrapheValue()
	{
		super();
	}
	
	public GrapheValue(EnsembleSommet<S> x,EnsembleArcValues<S> gamma)
	{
		super(x,gamma);
	}
	
	public GrapheValue(GrapheValue<S> G)
	{
		super(G);
	}
	
//Un cast est effectué mais j'aimerais trouver mieux
	@Override
	public EnsembleArcValues<S> getGamma()
	{
		return (EnsembleArcValues<S>)super.getGamma();
	}
	
	public boolean getCout(Sommet<S> depart, Sommet<S> arrivee,Cout cout)
	{
		return getGamma().getValue(depart,arrivee,cout);
	}
	
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		ajouteArc(new ArcValue<S>(depart,arrivee,cout));
	}
}
