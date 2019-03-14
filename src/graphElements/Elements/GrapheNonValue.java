package graphElements.Elements;

import factory.Factory;
import graphElements.Abstract.AbstractGraphe;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArcNonValue;
import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceGrapheNonValue;
import graphElements.Interfaces.InterfaceSommet;

public class GrapheNonValue<S> extends AbstractGraphe<S,InterfaceArc<S>> implements InterfaceGrapheNonValue<S>
{
	//Constructeurs
	public GrapheNonValue()
	{
		super();
		setGamma(Factory.ensembleArcNonValue());
	}
	public GrapheNonValue(InterfaceEnsembleSommet<S> x,InterfaceEnsembleArcNonValue<S> gamma)
	{
		super(Factory.ensembleSommet(x),Factory.ensembleArcNonValue(gamma));
	}
	public GrapheNonValue(InterfaceGrapheNonValue<S> G) 
	{
		super(G);
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
}