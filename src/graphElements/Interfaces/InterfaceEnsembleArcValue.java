package graphElements.Interfaces;


public interface InterfaceEnsembleArcValue<S> extends InterfaceEnsembleArc<S,InterfaceArcValue<S>>, InterfaceOperationsElementairesEnsembleArcValue<S>
{
	static <S> InterfaceEnsembleArcValue<S> union(InterfaceEnsembleArcValue<S> ensemble1,InterfaceEnsembleArcValue<S> ensemble2)
	{
		return (InterfaceEnsembleArcValue<S>)InterfaceEnsembleArc.union(ensemble1, ensemble2);
	}
	
	static <S> InterfaceEnsembleArcValue<S> intersection(InterfaceEnsembleArcValue<S> ensemble1,InterfaceEnsembleArcValue<S> ensemble2)
	{
		return (InterfaceEnsembleArcValue<S>)InterfaceEnsembleArc.intersection(ensemble1, ensemble2);
	}
}