package graphelements.interfaces;

public interface OperationsElementairesEnsembleArc<S>
{
	// Liste des successeurs et prédessesseurs
	EnsembleSommet<S> listSucc(Sommet<S> sommet);// Liste des successeurs
	EnsembleSommet<S> listPred(Sommet<S> sommet);// Liste des prédessesseurs
	// Test de l'existence d'arcs et de boucles
	// Un arc A existe si il existe un arc B dans l'ensemble tel que A et B ont le
	// même départ et arrivée
	boolean existeArc(Arc<S> arc);// Rend true si l'arc existe
	boolean existeArc(Sommet<S> arrivee, Sommet<S> depart);// Pareil mais avec deux sommets en entrée
	boolean existeBoucle();// Rend vrai si il existe un arc tel que depart==arrivee
	boolean existeBoucle(Sommet<S> sommet);// Rend vrai si il existe un arc (sommet,sommet)
	// Ajout et suppression d'arcs
	// L'arc peut être ajouté seulement si il n'existe pas déja, l'arc est supprimé seulement si il existe
	// void ajouteElement(A arc);//Ajoute l'arc
	// void supprArc(A arc);//Supprime l'arc
	// Pareil mais avec deux sommets en entrée*/
}