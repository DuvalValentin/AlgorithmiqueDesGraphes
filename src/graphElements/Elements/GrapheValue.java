package graphElements.Elements;

import java.util.Optional;

import factory.Factory;
import graphElements.Abstract.AbstractGraphe;
import graphElements.Interfaces.*;

public class GrapheValue<S> extends AbstractGraphe<S,InterfaceArcValue<S>> implements InterfaceGrapheValue<S>
{
	//Constructeur
	public GrapheValue()
	{
		super();
		setGamma(Factory.ensembleArcValue());
	}
	public GrapheValue(InterfaceEnsembleSommet<S> x,InterfaceEnsembleArcValue<S> gamma)
	{
		super(x,gamma);
	}
	public GrapheValue(InterfaceGrapheValue<S> G)
	{
		super(G);
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
		return ((InterfaceEnsembleArcValue<S>)Gamma).getCout(depart,arrivee);
	}
	//Setter
	@Override
	public boolean setValeur(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout)
	{
		return ((InterfaceEnsembleArcValue<S>)Gamma).setValeur(depart,arrivee,cout);
	}
	//Ajout d'éléments
	@Override
	public void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee,InterfaceCout cout)
	{
		ajouteArc(Factory.arcValue(depart, arrivee, cout));
	}
}