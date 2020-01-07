package graphelements.elements;

import graphelements.interfaces.InterfaceSommet;

public class Sommet<S> implements InterfaceSommet<S>
{
	//Id
	private S id;
	//Constructeur
	public Sommet(S s)
	{
		setId(s);
	}
	public Sommet(InterfaceSommet<S> sommet)
	{
		setId(sommet.getId());
	}
	//Getter
	@Override
	public S getId() 
	{
		return id;
	}
	//Setter
	@Override
	public void setId(S s) 
	{
		id = s;
	}
	//toString/equals/hashCode
	@Override
	public String toString()
	{
		return getId().toString();
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null&&obj.getClass()==getClass()&&((Sommet<S>)obj).getId().equals(getId()))
		{
			result=true;
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return getId().hashCode();
	}
}

