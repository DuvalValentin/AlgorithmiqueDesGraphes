package graphElements.Elements;

import graphElements.Abstract.AbstractGraphe;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceGrapheNonValue;

public class GrapheNonValue<S> extends AbstractGraphe<S,InterfaceArc<S>> implements InterfaceGrapheNonValue<S>
{
	//Constructeurs
	public GrapheNonValue()
	{
		super();
		setGamma(new EnsembleArcNonValue<S>());
	}
	public GrapheNonValue(EnsembleSommet<S> x,EnsembleArcNonValue<S> gamma)
	{
		super(new EnsembleSommet<S>(x),new EnsembleArcNonValue<S>(gamma));
	}
	public GrapheNonValue(GrapheNonValue<S> G) 
	{
		super(G);
	}
	//Getter
	@Override
	public EnsembleArcNonValue<S> getGamma()
	{
		return new EnsembleArcNonValue<S>((EnsembleArcNonValue<S>)super.getGamma());
	}
	
	//Ajout d'élément
	@Override
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		ajouteArc(new Arc<S>(depart,arrivee));
	}
	@Override
	public GrapheNonValue<S> clone()
	{
		return new GrapheNonValue<S>(this);
	}
}