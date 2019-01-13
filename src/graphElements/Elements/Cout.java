package graphElements.Elements;

import graphElements.Interfaces.InterfaceCout;

//TODO Cout sera inutile dès que la méthode getCout de EnsembleArcValue sera améliorée

public class Cout implements InterfaceCout
{
	//Valeur
	private float valeur;
	//Constructeur
	public Cout()
	{
		valeur=0;
	}
	public Cout(float valeur)
	{
		setValeur(valeur);
	}
	public Cout(Cout cout)
	{
		setValeur(cout.getValeur());
	}
	//Getter
	@Override
	public float getValeur()
	{
		return valeur;
	}
	//Setter
	@Override
	public void setValeur(float valeur)
	{
		this.valeur = valeur;
	}
	//Somme de deux couts
	public static Cout somme(Cout c1, Cout c2)
	{
		float somme=c1.getValeur()+c2.getValeur();
		return new Cout(somme);
	}
	//toString/equals/hashCode
	@Override
	public String toString()
	{
		return ""+valeur;
	}
	@Override
	public boolean equals(Object obj)
	{
		boolean callback;
		if(obj.getClass()!=getClass())
		{
			callback=false;
		}
		else
		{
			if(((Cout)obj).getValeur()==(getValeur()))
			{
				callback=true;
			}
			else
			{
				callback=false;
			}
		}
		return callback;
	}
	@Override
	public int hashCode()
	{
		return (int)valeur;
	}
}
