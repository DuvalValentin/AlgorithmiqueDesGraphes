package graphElements.Elements;

import graphElements.Interfaces.InterfaceSommet;


public class Sommet<S> implements InterfaceSommet<S>
{
	//attributes
	private S id;
	
	public Sommet(S s)
	{
		setId(s);
	}
	
	public Sommet(Sommet<S> sommet)
	{
		setId(sommet.getId());
	}

	public S getId() 
	{
		return id;
	}

	public void setId(S s) 
	{
		assert s!= null: "Objet null passé en paramètre";
		id = s;
	}
	
	@SuppressWarnings("unchecked")
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
			if(((Sommet<S>)obj).getId().equals(getId()))
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
	public Sommet<S> clone()
	{
		return new Sommet<S>(this);
	}
	
	@Override
	public int hashCode()
	{
		return getId().hashCode();
	}
	
	@Override
	public String toString()
	{
		return getId().toString();
	}
}

