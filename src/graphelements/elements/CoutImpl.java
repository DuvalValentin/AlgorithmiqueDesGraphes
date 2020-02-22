package graphelements.elements;

import graphelements.interfaces.Cout;

//TODO Cout sera inutile dès que la méthode getCout de EnsembleArcValue sera améliorée
public class CoutImpl implements Cout
{
	// Valeur
	private float valeur;

	// Constructeur
	public CoutImpl()
	{
		valeur=0;
	}
	public CoutImpl(float valeur)
	{
		setValeur(valeur);
	}
	public CoutImpl(Cout cout)
	{
		setValeur(cout.getValeur());
	}
	// Getter
	@Override
	public float getValeur()
	{
		return valeur;
	}
	// Setter
	@Override
	public void setValeur(float valeur)
	{
		this.valeur=valeur;
	}
	// toString/equals/hashCode
	@Override
	public String toString()
	{
		return ""+valeur;
	}
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null&&obj.getClass()==getClass()&&((CoutImpl)obj).getValeur()==(getValeur()))
		{
			result=true;
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return (int)valeur;
	}
}
