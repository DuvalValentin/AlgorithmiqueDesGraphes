package graphelements.interfaces;

public interface InterfaceGrapheNonValue<S> extends InterfaceGraphe<S,InterfaceArc<S>>, InterfaceOperationsElementairesEnsembleArcNonValue<S>
{
	//Getter
	InterfaceEnsembleArcNonValue<S> getGamma();//Renvoi une copie de Gamma
	
	static <S> InterfaceGrapheNonValue<S> union(InterfaceGrapheNonValue<S> graphe1,InterfaceGrapheNonValue<S> graphe2)
	{
		return (InterfaceGrapheNonValue<S>) InterfaceGraphe.union(graphe1, graphe2);
	}
	
	void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee);
	void ajouteArc(S idDepart, S idArrivee);
	
}