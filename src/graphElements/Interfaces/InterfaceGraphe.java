package graphElements.Interfaces;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleArc;

public interface InterfaceGraphe<S> extends InterfaceAbstractGraphe<S,Arc<S>>, InterfaceEnsembleArc<S>
{
	public EnsembleArc<S> getGamma();
}
