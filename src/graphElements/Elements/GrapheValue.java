package graphElements.Elements;

import graphElements.Abstract.AbstractGraphe;
import graphElements.Interfaces.*;

public class GrapheValue<S> extends AbstractGraphe<S,InterfaceArcValue<S>> implements InterfaceGrapheValue<S>
{
	//Constructeur
	public GrapheValue()
	{
		super();
		setGamma(new EnsembleArcValue<S>());
	}
	public GrapheValue(EnsembleSommet<S> x,EnsembleArcValue<S> gamma)
	{
		super(x,gamma);
	}
	public GrapheValue(GrapheValue<S> G)
	{
		super(G);
	}
	//Getters
	@Override
	public EnsembleArcValue<S> getGamma()
	{
		return new EnsembleArcValue<S>((EnsembleArcValue<S>)super.getGamma());
	}
	@Override
	public Cout getCout(Sommet<S> depart, Sommet<S> arrivee)
	{
		return ((EnsembleArcValue<S>)Gamma).getCout(depart,arrivee);
	}
	//Setter
	@Override
	public boolean setValeur(Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		return ((EnsembleArcValue<S>)Gamma).setValeur(depart,arrivee,cout);
	}
	//Ajout d'éléments
	@Override
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee,Cout cout)
	{
		ajouteArc(new ArcValue<S>(depart,arrivee,cout));
	}
	

	@Override
	public GrapheValue<S> clone()
	{
		return new GrapheValue<S>(this);
	}
}