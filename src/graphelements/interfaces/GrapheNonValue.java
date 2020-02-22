package graphelements.interfaces;

public interface GrapheNonValue<S>extends Graphe<S,Arc<S>>, OperationsElementairesEnsembleArcNonValue<S>
{
	// Getter
	EnsembleArcNonValue<S> getGamma();// Renvoi une copie de Gamma
	static <S> GrapheNonValue<S> union(GrapheNonValue<S> graphe1, GrapheNonValue<S> graphe2)
	{
		return (GrapheNonValue<S>)Graphe.union(graphe1,graphe2);
	}
	void ajouteArc(Sommet<S> depart, Sommet<S> arrivee);
	void ajouteArc(S idDepart, S idArrivee);
}