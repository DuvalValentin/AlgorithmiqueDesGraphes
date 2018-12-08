package graphElements;

public class Graphe<S> implements Cloneable
{
	private EnsembleSommet<S> X;
	private EnsembleArc<S> Gamma;
	
	//Constructeur
	public Graphe(EnsembleSommet<S> x,EnsembleArc<S> gamma)
	{
		setX(x.clone());
		setGamma(gamma.clone());
	}
	//Clonage
	@Override
	public Graphe<S> clone()
	{
		return (new Graphe<S>(X.clone(),Gamma.clone()));
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
		return X.clone();
	}
	public EnsembleArc<S> getGamma()  //ens-sommet(G) ; list-sommet(G)
	{
		return Gamma.clone();
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
			if(obj.getClass()==getClass())
			{

				if(((Graphe<S>)obj).getX().equals(X)&&((Graphe<S>)obj).getGamma().equals(Gamma))
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
		return X.hashCode()+Gamma.hashCode();
	}
	
	
	//Opération élémentaires
	
	//Existences d'arc et de sommets
	public boolean existSommet (Sommet<S> sommet)
	{
		return X.contains(sommet);
	}
	
	public boolean existArc (Arc<S> arc)
	{
		return Gamma.contains(arc);
	}
	public boolean existArc (Sommet<S> arrivee, Sommet<S> depart)
	{
		Arc<S> arc=new Arc<S>(arrivee,depart);
		return Gamma.contains(arc);
	}
	
	public boolean existeBoucle(Sommet<S> sommet)
	{
		return existArc(sommet, sommet);
	}
	
	public boolean ajoutableArc(Arc<S> arc)
	{
		return (existSommet(arc.getArrivee()) && existSommet(arc.getDepart()) && !existArc(arc));
	}
	
	//
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		return Gamma.listSucc(sommet);
	}
	
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		return Gamma.listPred(sommet);
	}
	
	public void ajouteSommet(Sommet<S> sommet)
	{
		X.add(sommet);
	}
	
	public void supprSommet(Sommet<S> sommet)
	{
		//TODO demander à papa pour l'itérateur qui ne marche pas sur une variable privée
		for(Arc<S> arc : getGamma())
		{
			if(arc.getDepart()==sommet || arc.getArrivee()==sommet)
			{
				supprArc(arc);
			}
		}
		X.remove(sommet);
	}
	public void ajouteArc(Arc<S> arc)
	{
		if (ajoutableArc(arc))
		{
			Gamma.add(arc);
		}
	}
	public void supprArc(Arc<S> arc)
	{
		Gamma.remove(arc);
	}

}

