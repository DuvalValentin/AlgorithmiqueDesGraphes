package graphElements.Elements;

import graphElements.Interfaces.InterfaceCout;

public class Cout implements InterfaceCout
{
	private float valeur;
	
	public Cout()
	{
		valeur=0;
	}
	
	public Cout(float valeur)
	{
		this.valeur=valeur;
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
	public float getValeur()
	{
		return valeur;
	}
	@Override
	public void setValeur(float valeur)
	{
		this.valeur = valeur;
	}
	
	public static Cout somme(Cout c1, Cout c2)
	{
		float somme=c1.getValeur()+c2.getValeur();
		return new Cout(somme);
	}
}
