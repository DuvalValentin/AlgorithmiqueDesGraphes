package graphElements.Interfaces;

import java.util.HashSet;

public interface InterfaceEnsemble<E>
{
	//Cette Interface est pour des objets encapsulant un HashSet appellé ensemble.
	//Getter
	HashSet<E> getEnsemble();//Donne une copie de l'ensemble
	//Autres
	boolean isEmpty();//Rend true si l'ensemble est vide
	void clear();//Vide l'ensemble
}