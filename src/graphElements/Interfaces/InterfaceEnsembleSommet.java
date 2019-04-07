package graphElements.Interfaces;


public interface InterfaceEnsembleSommet<S> extends InterfaceOperationsElementairesEnsembleSommet<S>, InterfaceEnsemble<InterfaceSommet<S>>
{
	static <S> InterfaceEnsembleSommet<S> union(InterfaceEnsembleSommet<S> ensemble1,InterfaceEnsembleSommet<S> ensemble2)
	{
		return (InterfaceEnsembleSommet<S>)InterfaceEnsemble.union(ensemble1, ensemble2);
	}
	
	static <S> InterfaceEnsembleSommet<S> intersection(InterfaceEnsembleSommet<S> ensemble1,InterfaceEnsembleSommet<S> ensemble2)
	{
		return (InterfaceEnsembleSommet<S>)InterfaceEnsemble.intersection(ensemble1, ensemble2);
	}

}
