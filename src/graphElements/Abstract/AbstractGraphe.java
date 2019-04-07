package graphElements.Abstract;

import java.util.HashSet;

import factory.Factory;
import graphElements.Interfaces.InterfaceArc;
import graphElements.Interfaces.InterfaceEnsembleArc;
import graphElements.Interfaces.InterfaceEnsembleSommet;
import graphElements.Interfaces.InterfaceGraphe;
import graphElements.Interfaces.InterfaceSommet;

public abstract class AbstractGraphe<S,A extends InterfaceArc<S>> implements InterfaceGraphe<S,A>
{
	//Ensembles -------------------------------------------------------------
	protected InterfaceEnsembleSommet<S> X;
	protected InterfaceEnsembleArc<S,A> Gamma;
	//Constructeurs ---------------------------------------------------------
	public AbstractGraphe(){setX(Factory.ensembleSommet());}
	public AbstractGraphe(InterfaceEnsembleSommet<S> x,InterfaceEnsembleArc<S,A> gamma)
	{
		setX(x);
		setGamma(gamma);
	}
	public AbstractGraphe(InterfaceGraphe<S,A> G)
	{
		setX(G.getX());
		setGamma(G.getGamma());
	}
	//Getters ---------------------------------------------------------------
	@Override
	public InterfaceEnsembleSommet<S> getX() 
	{
		return Factory.ensembleSommet(X);
	}
	@Override
	public InterfaceEnsembleArc<S,A> getGamma()
	{
		return Factory.ensembleArc(Gamma);
	}
	//Setters --------------------------------------------------------------
	protected void setX(InterfaceEnsembleSommet<S> x) 
	{
		X = Factory.ensembleSommet(x);
	}
	protected void setGamma(InterfaceEnsembleArc<S,A> gamma)
	{
		if (correctGamma(gamma))
		{
			Gamma = gamma;
		}
	}
	//Existences d'arc et de sommets ---------------------------------------
	@Override
	public boolean existeSommet (InterfaceSommet<S> sommet)
	{
		return X.existeSommet(sommet);
	}
	@Override
	public boolean existeArc (InterfaceArc<S> arc)
	{
		return Gamma.existeArc(arc);
	}
	@Override
	public boolean existeArc (InterfaceSommet<S> arrivee,InterfaceSommet<S> depart)
	{
		return Gamma.existeArc(arrivee, depart);
	}
	@Override
	public boolean existeBoucle()
	{
		return Gamma.existeBoucle();
	}
	@Override
	public boolean existeBoucle(InterfaceSommet<S> sommet)
	{
		return Gamma.existeBoucle(sommet);
	}
	//Vérification des éléments -------------------------------------------
	@Override
	public boolean ajoutableArc(A arc)
	{
		boolean ajoutable;
		if(existeSommet(arc.getArrivee()) && existeSommet(arc.getDepart()))
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
	public boolean correctGamma(InterfaceEnsembleArc<S,A> gamma)
	{
		boolean result=true;
		for(A arc : gamma.getEnsemble())
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
		return X.getEnsemble().isEmpty();
	}
	//Selections d'éléments -----------------------------------------------
	//Selction d'un élément
	@Override
	public InterfaceSommet<S> pickSommet()
	{
		return X.pickSommet();
	}
	//Liste des prédecesseurs et successeurs
	@Override
	public InterfaceEnsembleSommet<S> listSucc(InterfaceSommet<S> sommet)
	{
		return Gamma.listSucc(sommet);
	}
	@Override
	public InterfaceEnsembleSommet<S> listPred(InterfaceSommet<S> sommet)
	{
		return Gamma.listPred(sommet);
	}
	//Liste des points d'entrées et de sorties
	@Override
	public InterfaceEnsembleSommet<S> pointsEntree()
	{
		InterfaceEnsembleSommet<S> Ent=Factory.ensembleSommet(X);
		for(InterfaceSommet<S> S : X.getEnsemble())
		{
			for(A Arc : Gamma.getEnsemble())
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
	public InterfaceEnsembleSommet<S> pointsSortie()
	{
		InterfaceEnsembleSommet<S> Sor=Factory.ensembleSommet(X);
		for(InterfaceSommet<S> S : X.getEnsemble())
		{
			for(A Arc : Gamma.getEnsemble())
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
	//Ajouts et suppressions d'éléments ----------------------------------------
	//Sommet
	@Override
	public void ajouteSommet(InterfaceSommet<S> sommet)
	{
		X.ajouteSommet(sommet);
	}
	@Override
	public void supprSommet(InterfaceSommet<S> sommet)
	{
		HashSet<A> ens=new HashSet<A>(Gamma.getEnsemble());
		for(A arc : ens)
		{
			if(arc.getDepart().equals(sommet) || arc.getArrivee().equals(sommet))
			{
				supprArc(arc);
			}
		}
		X.supprSommet(sommet);
	}
	//Arc
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
	@Override
	public void supprArc(InterfaceSommet<S> depart, InterfaceSommet<S> arrivee)
	{
		Gamma.supprArc(depart, arrivee);
	}
	//toString equals et hashCode ------------------------------------------------------------
	@Override
	public String toString()
	{
		return "["+X.toString()+" | "+Gamma.toString()+"]";
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null)
		{
			if(obj.getClass()==getClass())
			{
				if(((AbstractGraphe<S,A>)obj).getX().equals(X)&&((AbstractGraphe<S,A>)obj).getGamma().equals(Gamma))
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
}