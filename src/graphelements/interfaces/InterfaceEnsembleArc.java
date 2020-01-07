package graphelements.interfaces;

public interface InterfaceEnsembleArc<S,A extends InterfaceArc<S>> extends InterfaceOperationsElementairesEnsembleArc<S>,InterfaceEnsemble<A>
{
	@SuppressWarnings("unchecked")
	static <S,A extends InterfaceArc<S>> InterfaceEnsembleArc<S,A> union(InterfaceEnsembleArc<S,A> ensemble1,InterfaceEnsembleArc<S,A> ensemble2)
	{
		return (InterfaceEnsembleArc<S,A>)InterfaceEnsemble.union(ensemble1, ensemble2);
	}
	
	@SuppressWarnings("unchecked")
	static <S,A extends InterfaceArc<S>> InterfaceEnsembleArc<S,A> intersection(InterfaceEnsembleArc<S,A> ensemble1,InterfaceEnsembleArc<S,A> ensemble2)
	{
		return (InterfaceEnsembleArc<S,A>)InterfaceEnsemble.intersection(ensemble1, ensemble2);
	}
	
	void supprElement(InterfaceSommet<S>depart, InterfaceSommet<S>arrivee);
	
}