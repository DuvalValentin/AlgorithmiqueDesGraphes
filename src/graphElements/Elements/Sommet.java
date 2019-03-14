package graphElements.Elements;

import graphElements.Interfaces.InterfaceSommet;

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
		//assert s!= null: "Objet null passé en paramètre";
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
		if(obj!=null)
		{
			if(obj.getClass()==getClass())
			{
				if(((Sommet<S>)obj).getId().equals(getId()))
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
		return getId().hashCode();
	}
}

