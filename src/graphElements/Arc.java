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

}

