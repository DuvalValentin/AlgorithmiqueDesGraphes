package graphelements.interfaces;

public interface GrapheValue<S>extends Graphe<S,ArcValue<S>>, OperationsElementairesEnsembleArcValue<S>
{
	// Getter
	public EnsembleArc<S,ArcValue<S>> getGamma();// Renvoie une copie de Gamma
	static <S> GrapheValue<S> union(GrapheValue<S> graphe1, GrapheValue<S> graphe2)
	{
		return (GrapheValue<S>)Graphe.union(graphe1,graphe2);
	}
	void ajouteArc(Sommet<S> depart, Sommet<S> arrivee, Cout cout);
}