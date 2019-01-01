package graphElements.Abstract;

import graphElements.Elements.Arc;
import graphElements.Elements.EnsembleSommet;
import graphElements.Elements.Graphe;
import graphElements.Elements.Sommet;
import graphElements.Interfaces.InterfaceAbstractGraphe;

public abstract class AbstractGraphe<S,A extends Arc<S>> implements Cloneable, InterfaceAbstractGraphe<S, A>
{
	protected EnsembleSommet<S> X;
	protected AbstractEnsembleArc<S,A> Gamma;
	
	//Constructeurs
	public AbstractGraphe(){setX(new EnsembleSommet<S>());}//TODO pk je suis obligé de le mettre ?
	
	public AbstractGraphe(EnsembleSommet<S> x,AbstractEnsembleArc<S,A> gamma)
	{
		setX(x);
		setGamma(gamma);
	}
	
	public AbstractGraphe(AbstractGraphe<S, A> G)
	{
		setX(G.getX());
		setGamma(G.getGamma());
	}
	
	//ToString
	@Override
	public String toString() //toString sans le coût
	{
		String str="["+X.toString()+" | "+Gamma.toString()+"]";
		return str;
	}
	
	//Getters -------------------------------------------------------------
	@Override
	public EnsembleSommet<S> getX() 
	{
		return X.clone();
	}
	@Override
	public AbstractEnsembleArc<S, A> getGamma()  //ens-sommet(G) ; list-sommet(G)
	{
		return Gamma.clone();
	}
	//Setters ------------------------------------------------------------
	protected void setX(EnsembleSommet<S> x) 
	{
		X = x.clone();
	}
	protected void setGamma(AbstractEnsembleArc<S,A> gamma) 
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
	@Override
	public boolean existSommet (Sommet<S> sommet)
	{
		return X.contains(sommet);
	}
	
	@Override
	public boolean existeArc (Arc<S> arc)
	{
		return Gamma.existeArc(arc);
	}

	@Override
	public boolean existeArc (Sommet<S> arrivee,Sommet<S> depart)
	{
		return Gamma.existeArc(arrivee, depart);
	}
	

	@Override
	public boolean existeBoucle()
	{
		return Gamma.existeBoucle();
	}
	
	//Vérification des éléments

	@Override
	public boolean ajoutableArc(Arc<S> arc)
	{
		boolean ajoutable;
		if(existSommet(arc.getArrivee()) && existSommet(arc.getDepart()))
		{
			if(null!=Gamma)
			{
				ajoutable= !existeArc(arc);
			}
			else
			{
				ajoutable=true;
			}
		}
		else
		{
			ajoutable=false;
		}
		return ajoutable;
	}
	
	@Override
	public boolean correctGamma(AbstractEnsembleArc<S, A> gamma)
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
	
	@Override
	public boolean isEmpty()
	{
		return X.isEmpty()&&Gamma.isEmpty();
	}
	
	//Liste des prédecesseurs et successeurs
	@Override
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		return Gamma.listSucc(sommet);
	}
	
	@Override
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		return Gamma.listPred(sommet);
	}
	//Liste des entrées sorties TODO sorties
	@Override
	public EnsembleSommet<S> pointsEntree()
	{
		EnsembleSommet<S> Ent=new EnsembleSommet<S>(X);
		for(Sommet<S> S : X)
		{
			for(A Arc : Gamma)
			{
				if(Arc.getArrivee().equals(S))
				{
					Ent.supprSommet(S);
					break;
				}
			}
		}
		return Ent;
	}
	@Override
	public EnsembleSommet<S> pointsSortie()
	{
		EnsembleSommet<S> Sor=new EnsembleSommet<S>(X);
		for(Sommet<S> S : X)
		{
			for(A Arc : Gamma)
			{
				if(Arc.getDepart().equals(S))
				{
					Sor.supprSommet(S);
					break;
				}
			}
		}
		return Sor;
	}
	
	
	//Ajouts et suppressions d'éléments
	@Override
	public void ajouteSommet(Sommet<S> sommet)
	{
		X.ajouteSommet(sommet);
	}
	
	@Override
	public void supprSommet(Sommet<S> sommet)
	{
		//TODO demander à papa pour l'itérateur qui ne marche pas sur une variable privée
		for(A arc : getGamma())
		{
			if(arc.getDepart()==sommet || arc.getArrivee()==sommet)
			{
				supprArc(arc);
			}
		}
		X.supprSommet(sommet);
	}

	@Override
	public void ajouteArc(A arc)
	{
		if (ajoutableArc(arc))
		{
			Gamma.ajouteArc(arc);
		}
	}

	@Override
	public void supprArc(A arc)
	{
		Gamma.supprArc(arc);
	}

	
	
	//Union
	//TODO implémenter l'union pour les ensemble en général
	@Override
	public void union(InterfaceAbstractGraphe<S, A> G)
	{
		for(Sommet<S> S : G.getX())
		{
			ajouteSommet(S);
		}
		for(A Arc : G.getGamma())
		{
			ajouteArc(Arc);
		}
	}
	
	@Override
	public Sommet<S> firstSommet()
	{
		return X.firstSommet();
	}
}
