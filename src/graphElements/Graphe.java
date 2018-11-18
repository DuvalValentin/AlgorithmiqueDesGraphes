package graphElements;

public class Graphe<S> implements Cloneable
{
	private EnsembleSommet<S> X;
	private EnsembleArc<S> Gamma;
	
	//Constructeur
	public Graphe(EnsembleSommet<S> x,EnsembleArc<S> gamma)
	{
		this.setX(x);
		this.setGamma(gamma);
	}
	//Clonage
	@Override
	public Graphe<S> clone()
	{
		return (new Graphe<S> (this.getX().clone(),this.getGamma().clone()));
	}
	
	//ToString
	@Override
	public String toString()
	{
		String str="["+this.getX().toString()+" | "+this.getGamma().toString()+"]";
		return str;
	}
	
	//Getters/setters
	public EnsembleSommet<S> getX() 
	{
		return X;
	}
	public EnsembleArc<S> getGamma()  //ens-sommet(G) ; list-sommet(G)
	{
		return Gamma;
	}
	
	public void setX(EnsembleSommet<S> x) 
	{
		X = x;
	}
	public void setGamma(EnsembleArc<S> gamma) 
	{	
		Gamma = gamma;
	}
	
	//Equals et hash code
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null)
		{
			if(obj.getClass()==this.getClass())
			{

				if(((Graphe<S>)obj).getX().equals(this.getX())&&((Graphe<S>)obj).getGamma().equals(this.getGamma()))
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
		return this.getX().hashCode()+this.getGamma().hashCode();
	}
	
	
	//Opération élémentaires
	
	//Existences d'arc et de sommets
	public boolean existSommet (Sommet<S> sommet)
	{
		return this.getX().contains(sommet);
	}
	
	public boolean existArc (Arc<S> arc)
	{
		return this.getGamma().contains(arc);
	}
	public boolean existArc (Sommet<S> arrivee, Sommet<S> depart)
	{
		Arc<S> arc=new Arc<S>(arrivee,depart);
		return this.getGamma().contains(arc);
	}
	
	public boolean existeBoucle(Sommet<S> sommet)
	{
		return this.existArc(sommet, sommet);
	}
	
	public boolean ajoutableArc(Arc<S> arc)
	{
		return (existSommet(arc.getArrivee()) && existSommet(arc.getDepart()) && !existArc(arc));
	}
	
	//
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		return this.getGamma().listSucc(sommet);
	}
	
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		return this.getGamma().listPred(sommet);
	}
	
	public void ajouteArc(Arc<S> arc)
	{
		if (this.ajoutableArc(arc))
		{
			this.getGamma().add(arc);
		}
	}
	public void supprarc(Arc<S> arc)
	{
		this.getGamma().remove(arc);
	}

}

