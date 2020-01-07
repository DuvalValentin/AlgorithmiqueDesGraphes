package graphelements.interfaces;

import factory.Factory;

public interface InterfaceCout
{
	//Un Cout a un attribut valeur qui est un flottant
	//Getter
	float getValeur();//Renvoie la valeur
	//Setter
	void setValeur(float valeur);//Modifie la valeur
//Somme de deux couts
	public static InterfaceCout somme(InterfaceCout c1, InterfaceCout c2)
	{
		float somme=c1.getValeur()+c2.getValeur();
		return Factory.cout(somme);
	}
}