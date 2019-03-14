package graphElements.Interfaces;


public interface InterfaceArc<S>
{
	//Un arc est composé d'un sommet de départ et d'un sommet d'arrivée
	//Getters et Setters
	InterfaceSommet<S> getDepart();//Rend une copie du sommet de départ
	InterfaceSommet<S> getArrivee();//Rend une copie du sommet d'arrivée
	void setDepart(InterfaceSommet<S> depart);//Change le sommet de départ
	void setArrivee(InterfaceSommet<S> arrivee);//Change le sommet d'arrivée
//Un arc A est le même qu'un arc B si A et B ont le même départ et arrivée
	boolean memeArc(InterfaceArc<S> arc);//Rend true si l'arc est le même 
	boolean memeArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee);//Pareil avec deux sommets en entrée
}
