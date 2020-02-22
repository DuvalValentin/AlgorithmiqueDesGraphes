package graphelements.interfaces;

import factory.Factory;

public interface Cout
{
	// Un Cout a un attribut valeur qui est un flottant
	// Getter
	float getValeur();// Renvoie la valeur
	// Setter
	void setValeur(float valeur);// Modifie la valeur
	// Somme de deux couts
	public static Cout somme(Cout c1, Cout c2)
	{
		float somme=c1.getValeur()+c2.getValeur();
		return Factory.cout(somme);
	}
}