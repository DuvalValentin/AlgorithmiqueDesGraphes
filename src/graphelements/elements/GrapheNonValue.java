package graphelements.elements;

import factory.Factory;
import graphelements.abstracts.AbstractGraphe;
import graphelements.interfaces.InterfaceArc;
import graphelements.interfaces.InterfaceEnsembleArcNonValue;
import graphelements.interfaces.InterfaceEnsembleSommet;
import graphelements.interfaces.InterfaceGrapheNonValue;
import graphelements.interfaces.InterfaceSommet;

public class GrapheNonValue<S> extends AbstractGraphe<S,InterfaceArc<S>> implements InterfaceGrapheNonValue<S>
{
	//Constructeurs
	public GrapheNonValue()
	{
		super();
		setGamma(Factory.ensembleArcNonValue());
	}
	public GrapheNonValue(InterfaceEnsembleSommet<S> ensembleSommet,InterfaceEnsembleArcNonValue<S> gamma)
	{
		super(Factory.ensembleSommet(ensembleSommet),Factory.ensembleArcNonValue(gamma));
	}
	public GrapheNonValue(InterfaceGrapheNonValue<S> grapheNonValue) 
	{
		super(grapheNonValue);
	}
	//Getter
	@Override
	public InterfaceEnsembleArcNonValue<S> getGamma()
	{
		return Factory.ensembleArcNonValue((InterfaceEnsembleArcNonValue<S>)super.getGamma());
	}
	
	//Ajout d'élément
	@Override
	public void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		ajouteArc(Factory.arcNonValue(depart, arrivee));
	}
	@Override
	public void ajouteArc(S idDepart, S idArrivee)
	{
		ajouteArc(Factory.arcNonValue(idDepart, idArrivee));
	}
}