package graphelements.interfaces;

public interface OperationsElementairesEnsembleSommet<S>
{
	// Obtenir un sommet
	Sommet<S> pickSommet();// Rend une copie du "premier" sommet de l'ensemble
	// Test d'existence d'un sommet
	// Un sommet A existe si il y a un sommet B dans l'ensemble tel que A equals B
	boolean existeSommet(Sommet<S> sommet);// Rend true si le sommet existe
	// Ajout et suppression de sommet
	// TODO transformer ajouteSommet en ajouteElement et le mettre dans
	// iterfaceensemblesommet
	void ajouteSommet(S id);
}
