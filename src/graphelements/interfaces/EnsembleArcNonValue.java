package graphelements.interfaces;

public interface EnsembleArcNonValue<S>extends EnsembleArc<S,Arc<S>>, OperationsElementairesEnsembleArcNonValue<S>
{
	static <S> EnsembleArcNonValue<S> union(EnsembleArcNonValue<S> ensemble1, EnsembleArcNonValue<S> ensemble2)
	{
		return (EnsembleArcNonValue<S>)EnsembleArc.union(ensemble1,ensemble2);
	}
	static <S> EnsembleArcNonValue<S> intersection(EnsembleArcNonValue<S> ensemble1, EnsembleArcNonValue<S> ensemble2)
	{
		return (EnsembleArcNonValue<S>)EnsembleArc.intersection(ensemble1,ensemble2);
	}
	void ajouteElement(Sommet<S> depart, Sommet<S> arrivee);// Ajoute l'arc
	void ajouteElement(S idDepart, S isArrivee);
}
