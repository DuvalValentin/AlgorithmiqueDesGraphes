package graphElements.Elements;

public class Arc<S>
{
	private Sommet<S> depart;
	private Sommet<S> arrivee;
	
	public Arc(Sommet<S> dep, Sommet<S> arr)
	{
		setDepart(dep);
		setArrivee(arr);
	}
	
	@Override
	public String toString()
	{
		String str="("+getDepart().getId()+","+getArrivee().getId()+")";
		return str;
	}
	
	
	public Sommet<S> getDepart() 
	{
		return depart;
	}
	public Sommet<S> getArrivee() 
	{
		return arrivee;
	}
	public void setDepart(Sommet<S> depart) 
	{
		this.depart = depart;
	}
	public void setArrivee(Sommet<S> arrivee) 
	{
		this.arrivee = arrivee;
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
			if(((Arc<S>)obj).getDepart().equals(getDepart())&&((Arc<S>)obj).getArrivee().equals(getArrivee()))
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
		return getArrivee().hashCode()+getDepart().hashCode();
	}

}

