package graphelements.elements;

import java.util.Optional;

import factory.Factory;
import graphelements.abstracts.AbstractGraphe;
import graphelements.interfaces.*;

public class GrapheValue<S> extends AbstractGraphe<S,InterfaceArcValue<S>> implements InterfaceGrapheValue<S>
{
	//Constructeur
	public GrapheValue()
	{
		super();
		setGamma(Factory.ensembleArcValue());
	}
	public GrapheValue(InterfaceEnsembleSommet<S> ensembleSommet,InterfaceEnsembleArcValue<S> gamma)
	{
		super(ensembleSommet,gamma);
	}
	public GrapheValue(InterfaceGrapheValue<S> g)
	{
		super(g);
	}
	//Getters
	@Override
	public InterfaceEnsembleArcValue<S> getGamma()
	{
		return Factory.ensembleArcValue((InterfaceEnsembleArcValue<S>)super.getGamma());
	}
	@Override
	public Optional<InterfaceCout> getCout(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		return ((InterfaceEnsembleArcValue<S>)gamma).getCout(depart,arrivee);
	}
	//Setter
	@Override
	public boolean setValeur(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout)
	{
		return ((InterfaceEnsembleArcValue<S>)gamma).setValeur(depart,arrivee,cout);
	}
	//Ajout d'éléments
	@Override
	public void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee,InterfaceCout cout)
	{
		ajouteArc(Factory.arcValue(depart, arrivee, cout));
	}
}