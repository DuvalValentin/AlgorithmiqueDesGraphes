package graphElements.Interfaces;

import graphElements.Elements.ArcValue;
import graphElements.Elements.EnsembleArcValue;

public interface InterfaceGrapheValue<S> extends InterfaceAbstractGraphe<S,ArcValue<S>>, InterfaceEnsembleArcValue<S>
{
	//Getter
	public EnsembleArcValue<S> getGamma();//Renvoie une copie de Gamma
}