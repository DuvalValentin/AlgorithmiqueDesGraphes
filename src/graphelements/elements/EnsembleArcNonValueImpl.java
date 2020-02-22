package graphelements.elements;

import java.util.HashSet;
import factory.Factory;
import graphelements.abstracts.AbstractEnsembleArc;
import graphelements.interfaces.Arc;
import graphelements.interfaces.EnsembleArcNonValue;
import graphelements.interfaces.Sommet;

public class EnsembleArcNonValueImpl<S>extends AbstractEnsembleArc<S,Arc<S>> implements EnsembleArcNonValue<S>
{
	// Constructeur
	public EnsembleArcNonValueImpl(EnsembleArcNonValue<S> ensemble)
	{
		for(Arc<S> arc : ensemble.getEnsemble())
		{
			ajouteElement(arc);
		}
	}
	public EnsembleArcNonValueImpl()
	{
		super();
	}
	// Getter
	@Override
	public HashSet<Arc<S>> getEnsemble()
	{
		return new HashSet<>(ensemble);
	}
	// Ajout et suppression d'éléments
	@Override
	public void ajouteElement(Arc<S> arc)
	{
		super.ajouteElement(Factory.arcNonValue(arc));
	}
	@Override
	public void ajouteElement(Sommet<S> depart, Sommet<S> arrivee)
	{
		ajouteElement(Factory.arcNonValue(depart,arrivee));
	}
	@Override
	public void ajouteElement(S idDepart, S idArrivee)
	{
		ajouteElement(Factory.arcNonValue(idDepart,idArrivee));
	}
	@Override
	public void supprElement(Sommet<S> depart, Sommet<S> arrivee)
	{
		supprElement(Factory.arcNonValue(depart,arrivee));
	}
}