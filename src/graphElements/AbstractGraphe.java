package graphElements;

public abstract class AbstractGraphe<S,A extends Arc<S>> implements Cloneable
{
	private EnsembleSommet<S> X;
	private AbstractEnsembleArc<S,A> Gamma;
	
	//Constructeurs
	public AbstractGraphe(){setX(new EnsembleSommet<S>());}//TODO pk je suis obligé de le mettre ?
	
	public AbstractGraphe(EnsembleSommet<S> x,AbstractEnsembleArc<S,A> gamma)
	{
		setX(x);
		setGamma(gamma);
	}
	//TODO Pareil que le clonnage => problème ?
	public AbstractGraphe(AbstractGraphe<S,A> G)
	{
		setX(G.getX());
		setGamma(G.getGamma());
	}
	
	//Clonage
	//TODO réimplémenter clone()
	//@Override
	//public abstract AbstractGraphe<S,A> clone();
	
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
	public AbstractEnsembleArc<S,A> getGamma()  //ens-sommet(G) ; list-sommet(G)
	{
		return Gamma.clone();
	}
	//Setters ------------------------------------------------------------
	private void setX(EnsembleSommet<S> x) 
	{
		X = x.clone();
	}
	private void setGamma(AbstractEnsembleArc<S,A> gamma) 
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
	
	public boolean correctGamma(AbstractEnsembleArc<S,A> gamma)
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
	
	public boolean isEmpty()
	{
		return X.isEmpty()&&Gamma.isEmpty();
	}
	
	//Liste des prédecesseurs et successeurs
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		return Gamma.listSucc(sommet);
	}
	
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		return Gamma.listPred(sommet);
	}
	//Liste des entrées sorties
	public EnsembleSommet<S> pointsEntree()
	{
		EnsembleSommet<S> E=new EnsembleSommet<S>(X);
		for(Sommet<S> S : X)
		{
			for(A Arc : Gamma)
			{
				if(Arc.getArrivee().equals(S))
				{
					E.remove(S);
					break;
				}
			}
		}
		return E;
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
	public void ajouteArc(A arc)
	{
		if (ajoutableArc(arc))
		{
			Gamma.add(arc);
		}
	}
	/*//TODO méthode différente pour chaque enfant
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		ajouteArc(new Arc<S>(depart,arrivee));
	}*/
	
	public void supprArc(Arc<S> arc)
	{
		Gamma.remove(arc);
	}
	public void supprArc(Sommet<S> depart, Sommet<S> arrivee)
	{
		supprArc(new Arc<S>(depart,arrivee));
	}
	
	//Union
	public void union(AbstractGraphe<S,A> G)
	{
		for(Sommet<S> S : G.getX())
		{
			this.ajouteSommet(S);
		}
		for(A Arc : G.getGamma())
		{
			this.ajouteArc(Arc);
		}
	}
}
