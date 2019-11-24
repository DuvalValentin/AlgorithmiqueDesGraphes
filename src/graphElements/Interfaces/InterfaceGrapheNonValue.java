package graphElements.Interfaces;

public interface InterfaceGrapheNonValue<S> extends InterfaceGraphe<S,InterfaceArc<S>>, InterfaceOperationsElementairesEnsembleArcNonValue<S>
{
	//Getter
	InterfaceEnsembleArcNonValue<S> getGamma();//Renvoi une copie de Gamma
	
	static <S> InterfaceGrapheNonValue<S> union(InterfaceGrapheNonValue<S> Graphe1,InterfaceGrapheNonValue<S> Graphe2)
	{
		return (InterfaceGrapheNonValue<S>) InterfaceGraphe.union(Graphe1, Graphe2);
	}
	
	void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee);
	void ajouteArc(S idDepart, S idArrivee);
	
}