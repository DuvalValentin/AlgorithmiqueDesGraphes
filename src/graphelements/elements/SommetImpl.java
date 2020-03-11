package graphelements.elements;

import graphelements.interfaces.Sommet;

/**
 * The implementation of the Sommet interface
 * 
 * @author valentin
 *
 * @param <S>
 */
public class SommetImpl<S> implements Sommet<S>
{
	// Id
	private S id;

	// Constructeur
	public SommetImpl(S s)
	{
		setId(s);
	}
	public SommetImpl(Sommet<S> sommet)
	{
		setId(sommet.getId());
	}
	// Getter
	@Override
	public S getId()
	{
		return id;
	}
	// Setter
	@Override
	public void setId(S s)
	{
		id=s;
	}
	// toString/equals/hashCode
	@Override
	public String toString()
	{
		return id.toString();
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null&&obj.getClass()==getClass()&&((SommetImpl<S>)obj).getId().equals(id))
		{
			result=true;
		}
		return result;
	}
	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
}
