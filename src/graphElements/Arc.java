package graphElements;


public class Arc <S>
{
	private Sommet<S> depart;
	private Sommet<S> arrivee;
	
	public Arc(Sommet<S> dep, Sommet<S> arr)
	{
		this.setDepart(dep);
		this.setArrivee(arr);
	}
	
	@Override
	public String toString()
	{
		String str="("+this.getDepart().getId()+","+this.getArrivee().getId()+")";
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
	public void setArrivee(Sommet<S> arivee) 
	{
		this.arrivee = arivee;
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
			if(((Arc<S>)obj).getDepart().equals(this.getDepart())&&((Arc<S>)obj).getArrivee().equals(this.getArrivee()))
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
		return this.getArrivee().hashCode()+this.getDepart().hashCode();
	}

}

