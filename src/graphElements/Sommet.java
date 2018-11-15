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
	
	/*@Override
	public boolean equals(Object obj)
	{
		boolean callback;
		if(obj.getClass()==this.getClass())
		{
			callback=false;
		}
		else
		{
			if(((Sommet<S>)obj).getId()==this.getId())
			{
				callback=false;
			}
			else
			{
				callback=true;
			}
		}
		return callback;
	}*/
	
	@Override
	public String toString()
	{
		return this.getId().toString();
	}
}

