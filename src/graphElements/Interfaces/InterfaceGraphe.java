package graphElements.Interfaces;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleArc;

public interface InterfaceGraphe<S> extends InterfaceAbstractGraphe<S,Arc<S>>, InterfaceEnsembleArc<S>
{
	//Getter
	public EnsembleArc<S> getGamma();//Renvoi une copie de Gamma
}