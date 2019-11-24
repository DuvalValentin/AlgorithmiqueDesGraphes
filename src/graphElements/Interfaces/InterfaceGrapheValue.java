package graphElements.Interfaces;


public interface InterfaceGrapheValue<S,AV extends InterfaceArcValue<S>> extends InterfaceGraphe<S,InterfaceArcValue<S>>, InterfaceOperationsElementairesEnsembleArcValue<S>
{
	//Getter
	public InterfaceEnsembleArc<S,InterfaceArcValue<S>> getGamma();//Renvoie une copie de Gamma
	
	@SuppressWarnings("unchecked")
	static <S,AV extends InterfaceArcValue<S>> InterfaceGrapheValue<S,AV> union(InterfaceGrapheValue<S,AV> Graphe1,InterfaceGrapheValue<S,AV> Graphe2)
	{
		return (InterfaceGrapheValue<S,AV>) InterfaceGraphe.union(Graphe1, Graphe2);
	}
	void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee,InterfaceCout cout);
}