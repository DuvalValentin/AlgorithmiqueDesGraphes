package graphElements.Interfaces;


public interface InterfaceEnsembleArcNonValue<S> extends InterfaceEnsembleArc<S,InterfaceArc<S>>, InterfaceOperationsElementairesEnsembleArcNonValue<S>
{
	static <S> InterfaceEnsembleArcNonValue<S> union(InterfaceEnsembleArcNonValue<S> ensemble1,InterfaceEnsembleArcNonValue<S> ensemble2)
	{
		return (InterfaceEnsembleArcNonValue<S>)InterfaceEnsembleArc.union(ensemble1, ensemble2);
	}
	
	static <S> InterfaceEnsembleArcNonValue<S> intersection(InterfaceEnsembleArcNonValue<S> ensemble1,InterfaceEnsembleArcNonValue<S> ensemble2)
	{
		return (InterfaceEnsembleArcNonValue<S>)InterfaceEnsembleArc.intersection(ensemble1, ensemble2);
	}
	
}
