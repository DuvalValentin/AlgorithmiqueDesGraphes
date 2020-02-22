package graphelements.interfaces;

public interface EnsembleSommet<S>extends OperationsElementairesEnsembleSommet<S>, Ensemble<Sommet<S>>
{
	static <S> EnsembleSommet<S> union(EnsembleSommet<S> ensemble1, EnsembleSommet<S> ensemble2)
	{
		return (EnsembleSommet<S>)Ensemble.union(ensemble1,ensemble2);
	}
	static <S> EnsembleSommet<S> intersection(EnsembleSommet<S> ensemble1, EnsembleSommet<S> ensemble2)
	{
		return (EnsembleSommet<S>)Ensemble.intersection(ensemble1,ensemble2);
	}
}
