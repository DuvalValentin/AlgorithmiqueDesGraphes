package graphElements.Elements;

public class Arc<S>
{
	private Sommet<S> depart;
	private Sommet<S> arrivee;
	
	public Arc(Sommet<S> dep, Sommet<S> arr)
	{
		setDepart(dep.clone());
		setArrivee(arr.clone());
	}
	
	public Arc(Arc<S> arc)
	{
		setDepart(arc.getDepart());
		setArrivee(arc.getArrivee());
	}
	
	@Override
	public String toString()
	{
		String str="("+getDepart().getId()+","+getArrivee().getId()+")";
		return str;
	}
	
	
	public Sommet<S> getDepart() 
	{
		return depart.clone();
	}
	public Sommet<S> getArrivee() 
	{
		return arrivee.clone();
	}
	public void setDepart(Sommet<S> depart) 
	{
		this.depart = depart.clone();
	}
	public void setArrivee(Sommet<S> arrivee) 
	{
		this.arrivee = arrivee.clone();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result;
		if(obj.getClass()!=getClass())
		{
			result=false;
		}
		else
		{
			if(((Arc<S>)obj).getDepart().equals(getDepart())&&((Arc<S>)obj).getArrivee().equals(getArrivee()))
			{
				result=true;
			}
			else
			{
				result=false;
			}
		}
		return result;
	}
	
	@Override
	public Arc<S> clone()
	{
		return new Arc<S>(this);
	}
	
	@Override
	public int hashCode()
	{
		return getArrivee().hashCode()+getDepart().hashCode();
	}

}

