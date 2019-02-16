package graphElements.Interfaces;

import graphElements.Abstract.AbstractEnsembleArc;

public interface InterfaceGrapheNonValue<S> extends InterfaceGraphe<S,InterfaceArc<S>>, InterfaceOperationsElementairesEnsembleArcNonValue<S>
{
	//Getter
	public AbstractEnsembleArc<S,InterfaceArc<S>> getGamma();//Renvoi une copie de Gamma
}