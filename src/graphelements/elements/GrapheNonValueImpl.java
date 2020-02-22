package graphelements.elements;

import factory.Factory;
import graphelements.abstracts.AbstractGraphe;
import graphelements.interfaces.Arc;
import graphelements.interfaces.EnsembleArcNonValue;
import graphelements.interfaces.EnsembleSommet;
import graphelements.interfaces.GrapheNonValue;
import graphelements.interfaces.Sommet;

public class GrapheNonValueImpl<S>extends AbstractGraphe<S,Arc<S>> implements GrapheNonValue<S>
{
	// Constructeurs
	public GrapheNonValueImpl()
	{
		super();
		setGamma(Factory.ensembleArcNonValue());
	}
	public GrapheNonValueImpl(EnsembleSommet<S> ensembleSommet, EnsembleArcNonValue<S> gamma)
	{
		super(Factory.ensembleSommet(ensembleSommet),Factory.ensembleArcNonValue(gamma));
	}
	public GrapheNonValueImpl(GrapheNonValue<S> grapheNonValue)
	{
		super(grapheNonValue);
	}
	// Getter
	@Override
	public EnsembleArcNonValue<S> getGamma()
	{
		return Factory.ensembleArcNonValue((EnsembleArcNonValue<S>)super.getGamma());
	}
	// Ajout d'élément
	@Override
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		ajouteArc(Factory.arcNonValue(depart,arrivee));
	}
	@Override
	public void ajouteArc(S idDepart, S idArrivee)
	{
		ajouteArc(Factory.arcNonValue(idDepart,idArrivee));
	}
}