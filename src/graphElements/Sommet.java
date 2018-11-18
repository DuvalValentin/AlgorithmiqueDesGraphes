package graphElements;


public class Sommet<S>
{
	//attributes
	private S id;
	
	public Sommet(S s)
	{
		this.setId(s);
	}

	public S getId() 
	{
		return id;
	}

	public void setId(S s) 
	{
		this.id = s;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean callback;
		if(obj.getClass()!=this.getClass())
		{
			callback=false;
		}
		else
		{
			if(((Sommet<S>)obj).getId().equals(this.getId()))
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
		return this.getId().hashCode();
	}
	
	@Override
	public String toString()
	{
		return this.getId().toString();
	}
}

