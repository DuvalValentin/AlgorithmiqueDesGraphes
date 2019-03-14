package graphElements.Interfaces;


public interface InterfaceGrapheValue<S> extends InterfaceGraphe<S,InterfaceArcValue<S>>, InterfaceOperationsElementairesEnsembleArcValue<S>
{
	//Getter
	public InterfaceEnsembleArc<S,InterfaceArcValue<S>> getGamma();//Renvoie une copie de Gamma
}