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
			ajouteElement(arc);
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
	public void ajouteElement(InterfaceArc<S> arc)
	{
		super.ajouteElement(Factory.arcNonValue(arc));
	}
	@Override
	public void ajouteElement(InterfaceSommet<S> depart,InterfaceSommet<S>arrivee)
	{
		ajouteElement(Factory.arcNonValue(depart, arrivee));
	}
	@Override
	public void ajouteElement(S idDepart, S idArrivee)
	{
		ajouteElement(Factory.arcNonValue(idDepart, idArrivee));
	}
	@Override
	public void supprElement(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		supprElement(Factory.arcNonValue(depart, arrivee));
	}
}