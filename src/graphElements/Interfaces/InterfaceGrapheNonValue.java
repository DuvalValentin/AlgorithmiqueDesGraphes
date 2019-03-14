package graphElements.Interfaces;


public interface InterfaceGrapheNonValue<S> extends InterfaceGraphe<S,InterfaceArc<S>>, InterfaceOperationsElementairesEnsembleArcNonValue<S>
{
	//Getter
	public InterfaceEnsembleArcNonValue<S> getGamma();//Renvoi une copie de Gamma
}