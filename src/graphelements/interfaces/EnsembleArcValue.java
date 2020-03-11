package graphelements.interfaces;

public interface EnsembleArcValue<S>extends EnsembleArc<S,ArcValue<S>>, OperationsElementairesEnsembleArcValue<S>
{
	static <S> EnsembleArcValue<S> union(EnsembleArcValue<S> ensemble1, EnsembleArcValue<S> ensemble2)
	{
		return (EnsembleArcValue<S>)EnsembleArc.union(ensemble1,ensemble2);
	}
	static <S> EnsembleArcValue<S> intersection(EnsembleArcValue<S> ensemble1, EnsembleArcValue<S> ensemble2)
	{
		return (EnsembleArcValue<S>)EnsembleArc.intersection(ensemble1,ensemble2);
	}
	void ajouteElement(Sommet<S> depart, Sommet<S> arrivee, Float cout);
}