package graphelements.interfaces;

public interface EnsembleArc<S,A extends Arc<S>>extends OperationsElementairesEnsembleArc<S>, Ensemble<A>
{
	@SuppressWarnings("unchecked")
	static <S,A extends Arc<S>> EnsembleArc<S,A> union(EnsembleArc<S,A> ensemble1, EnsembleArc<S,A> ensemble2)
	{
		return (EnsembleArc<S,A>)Ensemble.union(ensemble1,ensemble2);
	}
	@SuppressWarnings("unchecked")
	static <S,A extends Arc<S>> EnsembleArc<S,A> intersection(EnsembleArc<S,A> ensemble1, EnsembleArc<S,A> ensemble2)
	{
		return (EnsembleArc<S,A>)Ensemble.intersection(ensemble1,ensemble2);
	}
	void supprElement(Sommet<S> depart, Sommet<S> arrivee);
}