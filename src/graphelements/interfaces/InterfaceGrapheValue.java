package graphelements.interfaces;


public interface InterfaceGrapheValue<S> extends InterfaceGraphe<S,InterfaceArcValue<S>>, InterfaceOperationsElementairesEnsembleArcValue<S>
{
	//Getter
	public InterfaceEnsembleArc<S,InterfaceArcValue<S>> getGamma();//Renvoie une copie de Gamma
	
	static <S> InterfaceGrapheValue<S> union(InterfaceGrapheValue<S> graphe1,InterfaceGrapheValue<S> graphe2)
	{
		return (InterfaceGrapheValue<S>) InterfaceGraphe.union(graphe1, graphe2);
	}
	void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee,InterfaceCout cout);
}