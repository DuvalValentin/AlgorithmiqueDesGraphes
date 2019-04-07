package graphElements.Interfaces;


public interface InterfaceGrapheNonValue<S> extends InterfaceGraphe<S,InterfaceArc<S>>, InterfaceOperationsElementairesEnsembleArcNonValue<S>
{
	//Getter
	public InterfaceEnsembleArcNonValue<S> getGamma();//Renvoi une copie de Gamma
	
	static <S> InterfaceGrapheNonValue<S> union(InterfaceGrapheNonValue<S> Graphe1,InterfaceGrapheNonValue<S> Graphe2)
	{
		return (InterfaceGrapheNonValue<S>) InterfaceGraphe.union(Graphe1, Graphe2);
	}
}