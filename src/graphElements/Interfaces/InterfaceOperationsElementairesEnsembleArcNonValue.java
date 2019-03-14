package graphElements.Interfaces;

public interface InterfaceOperationsElementairesEnsembleArcNonValue<S> extends InterfaceOperationsElementairesEnsembleArc<S,InterfaceArc<S>>
{
	//Ajout d'élément
	void ajouteArc(InterfaceSommet<S>depart,InterfaceSommet<S>arrivee);//Ajoute l'arc
}
