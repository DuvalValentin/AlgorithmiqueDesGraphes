package graphElements;

public class Graphe<S> implements Cloneable
{
	private EnsembleSommet<S> X;
	private EnsembleArc<S> Gamma;
	
	//Constructeurs
	public Graphe(EnsembleSommet<S> x,EnsembleArc<S> gamma)
	{
		setX(x);
		setGamma(gamma);
	}
	//TODO Pareil que le clonnage => problème ?
	public Graphe(Graphe<S> G)
	{
		setX(G.getX());
		setGamma(G.getGamma());
	}
	
	//Clonage
	@Override
	public Graphe<S> clone()
	{
		return (new Graphe<S>(X.clone(),Gamma.clone()));
	}
	
	//ToString
	@Override
	public String toString() //toString sans le coût
	{
		String str="["+X.toString()+" | "+Gamma.toString()+"]";
		return str;
	}
	
	
	
	
	//Getters -------------------------------------------------------------
	
	public EnsembleSommet<S> getX() 
	{
		return X.clone();
	}
	public EnsembleArc<S> getGamma()  //ens-sommet(G) ; list-sommet(G)
	{
		return Gamma.clone();
	}
	//Setters ------------------------------------------------------------
	private void setX(EnsembleSommet<S> x) 
	{
		X = x.clone();
	}
	private void setGamma(EnsembleArc<S> gamma) 
	{
		if (correctGamma(gamma))
		{
			Gamma = gamma.clone();
		}
		assert gamma.equals(Gamma) : "Le gamma passé en paramètre n'est pas correct";
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
		boolean result;
		if(null==Gamma)
		{
			result=false;
		}
		else
		{
			result= Gamma.contains(arc);
		}
		return result;
		
		
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
	
	//Vérification des éléments
	public boolean ajoutableArc(Arc<S> arc)
	{
		return (existSommet(arc.getArrivee()) && existSommet(arc.getDepart()) && !existArc(arc));
	}
	
	public boolean correctGamma(EnsembleArc<S> gamma)
	{
		boolean result=true;
		
		for(Arc<S> arc : gamma)
		{
			if(!ajoutableArc(arc))
			{
				result=false;
			}
		}
		return result;
	}
	
	/*public boolean correctCoût(CoûtsArcs<S> coût)
	{
		boolean result=true;
		if(Gamma.size()==coût.size())
		{
			for(Arc<S> arcCoût : coût.keySet())
			{
				if(!Gamma.contains(arcCoût))
				{
					result=false;
				}
			}
		}
		else
		{
			result=false;
		}
		return result;		
	}*/
	
	//Liste des prédecesseurs et successeurs
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		return Gamma.listSucc(sommet);
	}
	
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		return Gamma.listPred(sommet);
	}
	
	//Ajouts et suppressions d'éléments
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
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		ajouteArc(new Arc<S>(depart,arrivee));
	}
	
	public void supprArc(Arc<S> arc)
	{
		Gamma.remove(arc);
	}
	public void supprArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		supprArc(new Arc<S>(depart,arrivee));
	}
	
	//Union
	public Graphe<S> union(Graphe<S> G)
	{
		Graphe<S> U = new Graphe<S>(this);
		for(Sommet<S> S : G.getX())
		{
			U.ajouteSommet(S);
		}
		for(Arc<S> A : G.getGamma())
		{
			U.ajouteArc(A);
		}
		return U;
	}
}