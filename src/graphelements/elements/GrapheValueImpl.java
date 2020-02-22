package graphelements.elements;

import java.util.Optional;
import factory.Factory;
import graphelements.abstracts.AbstractGraphe;
import graphelements.interfaces.*;

public class GrapheValueImpl<S>extends AbstractGraphe<S,ArcValue<S>> implements GrapheValue<S>
{
	// Constructeur
	public GrapheValueImpl()
	{
		super();
		setGamma(Factory.ensembleArcValue());
	}
	public GrapheValueImpl(EnsembleSommet<S> ensembleSommet, EnsembleArcValue<S> gamma)
	{
		super(ensembleSommet,gamma);
	}
	public GrapheValueImpl(GrapheValue<S> g)
	{
		super(g);
	}
	// Getters
	@Override
	public EnsembleArcValue<S> getGamma()
	{
		return Factory.ensembleArcValue((EnsembleArcValue<S>)super.getGamma());
	}
	@Override
	public Optional<Cout> getCout(Sommet<S> depart, Sommet<S> arrivee)
	{
		return ((EnsembleArcValue<S>)gamma).getCout(depart,arrivee);
	}
	// Setter
	@Override
	public boolean setValeur(Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		return ((EnsembleArcValue<S>)gamma).setValeur(depart,arrivee,cout);
	}
	// Ajout d'éléments
	@Override
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		ajouteArc(Factory.arcValue(depart,arrivee,cout));
	}
}