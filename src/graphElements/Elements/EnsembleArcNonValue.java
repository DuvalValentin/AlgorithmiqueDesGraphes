package graphElements.Elements;

import java.util.HashSet;

import factory.Factory;
import graphElements.Abstract.AbstractEnsembleArc;//Héritage
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArcNonValue;
import graphElements.Interfaces.InterfaceSommet;

public class EnsembleArcNonValue<S> extends AbstractEnsembleArc<S,InterfaceArc<S>> implements InterfaceEnsembleArcNonValue<S>
{
	//Constructeur
	public EnsembleArcNonValue(InterfaceEnsembleArcNonValue<S> Ensemble)
	{
		for (InterfaceArc<S> arc : Ensemble.getEnsemble())
		{
			ajouteArc(arc);
		}
	}
	public EnsembleArcNonValue()
	{
		super();
	}
	//Getter
	@Override
	public HashSet<InterfaceArc<S>> getEnsemble()
	{
		return new HashSet<InterfaceArc<S>>(ensemble);
	}
	//Ajout et suppression d'éléments
	@Override
	public void ajouteArc(InterfaceArc<S> arc)
	{
		super.ajouteArc(Factory.arcNonValue(arc));
	}
	
	@Override
	public void ajouteArc(InterfaceSommet<S> depart,InterfaceSommet<S>arrivee)
	{
		ajouteArc(Factory.arcNonValue(depart, arrivee));
	}
	@Override
	public void supprArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		supprArc(Factory.arcNonValue(depart, arrivee));
	}
}