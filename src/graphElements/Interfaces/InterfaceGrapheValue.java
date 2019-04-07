package graphElements.Interfaces;


public interface InterfaceGrapheValue<S> extends InterfaceGraphe<S,InterfaceArcValue<S>>, InterfaceOperationsElementairesEnsembleArcValue<S>
{
	//Getter
	public InterfaceEnsembleArc<S,InterfaceArcValue<S>> getGamma();//Renvoie une copie de Gamma
	
	static <S> InterfaceGrapheValue<S> union(InterfaceGrapheValue<S> Graphe1,InterfaceGrapheValue<S> Graphe2)
	{
		return (InterfaceGrapheValue<S>) InterfaceGraphe.union(Graphe1, Graphe2);
	}
}