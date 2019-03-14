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
	public Cout(InterfaceCout cout)
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
	//toString/equals/hashCode
	@Override
	public String toString()
	{
		return ""+valeur;
	}
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null)
		{
			if(obj.getClass()==getClass())
			{
				if(((Cout)obj).getValeur()==(getValeur()))
				{
					result=true;
				}
			}
		}
		
		return result;
	}
	@Override
	public int hashCode()
	{
		return (int)valeur;
	}
}
