package graphElements.Elements;

import java.util.Optional;

import factory.Factory;
import graphElements.Abstract.AbstractGraphe;
import graphElements.Interfaces.*;

public class GrapheValue<S,AV extends InterfaceArcValue<S>> extends AbstractGraphe<S,InterfaceArcValue<S>> implements InterfaceGrapheValue<S,AV>
{
	//Constructeur
	public GrapheValue()
	{
		super();
		setGamma(Factory.ensembleArcValue());
	}
	public GrapheValue(InterfaceEnsembleSommet<S> x,InterfaceEnsembleArcValue<S,AV> gamma)
	{
		super(x,gamma);
	}
	public GrapheValue(InterfaceGrapheValue<S,AV> G)
	{
		super(G);
	}
	//Getters
	@SuppressWarnings("unchecked")
	@Override
	public InterfaceEnsembleArcValue<S,AV> getGamma()
	{
		return Factory.ensembleArcValue((InterfaceEnsembleArcValue<S,AV>)super.getGamma());
	}
	@SuppressWarnings("unchecked")
	@Override
	public Optional<InterfaceCout> getCout(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		return ((InterfaceEnsembleArcValue<S,AV>)Gamma).getCout(depart,arrivee);
	}
	//Setter
	@SuppressWarnings("unchecked")
	@Override
	public boolean setValeur(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout)
	{
		return ((InterfaceEnsembleArcValue<S,AV>)Gamma).setValeur(depart,arrivee,cout);
	}
	//Ajout d'éléments
	@Override
	public void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee,InterfaceCout cout)
	{
		ajouteArc(Factory.arcValue(depart, arrivee, cout));
	}
}