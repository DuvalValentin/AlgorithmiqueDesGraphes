package graphElements.Interfaces;

import graphElements.Elements.Sommet;

public interface InterfaceArc<S>
{
	//Un arc est composé d'un sommet de départ et d'un sommet d'arrivée
	//Getters et Setters
	Sommet<S> getDepart();//Rend une copie du sommet de départ
	Sommet<S> getArrivee();//Rend une copie du sommet d'arrivée
	void setDepart(Sommet<S> depart);//Change le sommet de départ
	void setArrivee(Sommet<S> arrivee);//Change le sommet d'arrivée
//Un arc A est le même qu'un arc B si A et B ont le même départ et arrivée
	boolean memeArc(InterfaceArc<S> arc);//Rend true si l'arc est le même 
	boolean memeArc(Sommet<S> depart, Sommet<S> arrivee);//Pareil avec deux sommets en entrée
}
