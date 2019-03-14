package graphElements.Interfaces;

public interface InterfaceOperationsElementairesEnsembleArcValue<S> extends InterfaceOperationsElementairesEnsembleArc<S,InterfaceArcValue<S>>
{
	//getter
	InterfaceCout getCout(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee);//Rend le cout d'un arc, null si l'arc n'existe pas
	//setter
	boolean setValeur(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout);//Modifie la valeur du cout d'un arc
	//ajout d'élément
	void ajouteArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee, InterfaceCout cout);//Ajout l'arc
}
