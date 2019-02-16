package graphElements.Interfaces;

import graphElements.Abstract.AbstractEnsembleArc;

public interface InterfaceGrapheValue<S> extends InterfaceGraphe<S,InterfaceArcValue<S>>, InterfaceOperationsElementairesEnsembleArcValue<S>
{
	//Getter
	public AbstractEnsembleArc<S,InterfaceArcValue<S>> getGamma();//Renvoie une copie de Gamma
}