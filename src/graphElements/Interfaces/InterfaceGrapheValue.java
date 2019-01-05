package graphElements.Interfaces;

import graphElements.Elements.ArcValue;
import graphElements.Elements.EnsembleArcValue;

public interface InterfaceGrapheValue<S> extends InterfaceAbstractGraphe<S,ArcValue<S>>, InterfaceEnsembleArcValue<S>
{
	public EnsembleArcValue<S> getGamma();
}