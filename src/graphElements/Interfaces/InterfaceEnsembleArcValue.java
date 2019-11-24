package graphElements.Interfaces;


public interface InterfaceEnsembleArcValue<S,AV extends InterfaceArcValue<S>> extends InterfaceEnsembleArc<S,InterfaceArcValue<S>>, InterfaceOperationsElementairesEnsembleArcValue<S>
{
	@SuppressWarnings("unchecked")
	static <S,AV extends InterfaceArcValue<S>> InterfaceEnsembleArcValue<S,AV> union(InterfaceEnsembleArcValue<S,AV> ensemble1,InterfaceEnsembleArcValue<S,AV> ensemble2)
	{
		return (InterfaceEnsembleArcValue<S,AV>)InterfaceEnsembleArc.union(ensemble1, ensemble2);
	}
	
	@SuppressWarnings("unchecked")
	static <S,AV extends InterfaceArcValue<S>> InterfaceEnsembleArcValue<S,AV> intersection(InterfaceEnsembleArcValue<S,AV> ensemble1,InterfaceEnsembleArcValue<S,AV> ensemble2)
	{
		return (InterfaceEnsembleArcValue<S,AV>)InterfaceEnsembleArc.intersection(ensemble1, ensemble2);
	}
	void ajouteElement(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout);
}