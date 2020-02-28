package factory;

import graphelements.elements.*;
import graphelements.interfaces.*;

@SuppressWarnings("unchecked")
public class Factory
{
	private Factory()
	{}
	// Sommets
	public static <S> Sommet<S> sommet(Sommet<S> sommet)
	{
		return new SommetImpl<>(sommet);
	}
	public static <S> Sommet<S> sommet(S value)
	{
		return new SommetImpl<>(value);
	}
	// Arc
	// TODO améliorer méthodes de créations dynamiques de factory
	public static <S> Arc<S> arc(String className, Sommet<S> depart, Sommet<S> arrivee)
	{
		Arc<S> arc;
		if(className.equals("ArcImpl")||className.equals("GrapheNonValueImpl"))
		{
			arc=Factory.arcNonValue(depart,arrivee);
		}
		else // if (className.equals("ArcValue")||className.equals("GrapheValue"))
		{
			arc=Factory.arcValue(depart,arrivee,cout());
		}/*
			 * else { throw new Exception("pas marché"); }
			 */
		return arc;
	}
	public static <S> Arc<S> arcNonValue(Sommet<S> depart, Sommet<S> arrivee)
	{
		return new ArcImpl<>(depart,arrivee);
	}
	public static <S> Arc<S> arcNonValue(Arc<S> arc)
	{
		return new ArcImpl<>(arc);
	}
	public static <S> Arc<S> arcNonValue(S idDepart, S idArrivee)
	{
		return new ArcImpl<>(idDepart,idArrivee);
	}
	public static <S> ArcValue<S> arcValue(Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		return new ArcValueImpl<>(depart,arrivee,cout);
	}
	public static <S> ArcValue<S> arcValue(ArcValue<S> arc)
	{
		return new ArcValueImpl<>(arc);
	}
	// EnsembleSommet
	public static <S> EnsembleSommet<S> ensembleSommet()
	{
		return new EnsembleSommetImpl<>();
	}
	public static <S> EnsembleSommet<S> ensembleSommet(EnsembleSommet<S> ensembleSommet)
	{
		return new EnsembleSommetImpl<>(ensembleSommet);
	}
	// EnsembleArc
	public static <S,A extends Arc<S>> EnsembleArc<S,A> ensembleArc(String className)
	{
		EnsembleArc<S,A> ensembleArc;
		if(className.equals("EnsembleArcNonValueImpl"))
		{
			ensembleArc=(EnsembleArc<S,A>)Factory.ensembleArcNonValue();
		}
		else // if(className.equals("EnsembleArcValueImpl"))
		{
			ensembleArc=(EnsembleArc<S,A>)Factory.ensembleArcValue();
		}
		/*
		 * else { throw new Exception("pas marché"); }
		 */
		return ensembleArc;
	}
	public static <S> EnsembleArcNonValue<S> ensembleArcNonValue()
	{
		return new EnsembleArcNonValueImpl<>();
	}
	public static <S> EnsembleArcNonValue<S> ensembleArcNonValue(EnsembleArcNonValue<S> ensembleArcNonValue)
	{
		return new EnsembleArcNonValueImpl<>(ensembleArcNonValue);
	}
	public static <S,A extends Arc<S>> EnsembleArc<S,A> ensembleArc(EnsembleArc<S,A> gamma) // throws
																																													// Exception
	{
		EnsembleArc<S,A> ensembleArc;
		String className=gamma.getClass().getSimpleName();
		if(className.equals("EnsembleArcNonValueImpl"))
		{
			ensembleArc=(EnsembleArc<S,A>)Factory.ensembleArcNonValue((EnsembleArcNonValue<S>)gamma);
		}
		else // if (className.equals("EnsembleArcValueImpl"))
		{
			ensembleArc=(EnsembleArc<S,A>)Factory.ensembleArcValue((EnsembleArcValue<S>)gamma);
		}
		/*
		 * else { throw new Exception("pas marché"); }
		 */
		return ensembleArc;
	}
	public static <S> EnsembleArcValue<S> ensembleArcValue(EnsembleArcValue<S> ensembleArcValue)
	{
		return new EnsembleArcValueImpl<>(ensembleArcValue);
	}
	public static <S> EnsembleArcValue<S> ensembleArcValue()
	{
		return new EnsembleArcValueImpl<>();
	}
	// Graphe
	public static <S,A extends Arc<S>> Graphe<S,A> graphe(String classGraph) // throws
																																						// Exception
	{
		Graphe<S,A> retour;
		if(classGraph.equals("GrapheNonValueImpl"))
		{
			retour=(Graphe<S,A>)Factory.grapheNonValue();
		}
		else // if(classGraph.equals("GrapheValue"))
		{
			retour=(Graphe<S,A>)Factory.grapheValue();
		}
		/*
		 * else { throw new Exception("pas maché"); }
		 */
		return retour;
	}
	public static <S,A extends Arc<S>> Graphe<S,A> graphe(Graphe<S,A> graphe)
	{
		Graphe<S,A> retour;
		String classGraph=graphe.getClass().getSimpleName();
		if(classGraph.equals("GrapheNonValueImpl"))
		{
			retour=(Graphe<S,A>)Factory.grapheNonValue((GrapheNonValueImpl<S>)graphe);
		}
		else // if (classGraph.equals("GrapheValue"))
		{
			retour=(Graphe<S,A>)Factory.grapheValue((GrapheValueImpl<S>)graphe);
		}
		/*
		 * else { throw new Exception("pas maché"); }
		 */
		return retour;
	}
	public static <S,A extends Arc<S>> Graphe<S,A> graphe(EnsembleSommet<S> X, EnsembleArc<S,A> Gamma)
	{
		Graphe<S,A> retour;
		String classGamma=Gamma.getClass().getSimpleName();
		if(classGamma.equals("EnsembleArcNonValueImpl"))
		{
			retour=(Graphe<S,A>)Factory.grapheNonValue(X,(EnsembleArcNonValue<S>)Gamma);
		}
		else
		{
			retour=(Graphe<S,A>)Factory.grapheValue(X,(EnsembleArcValue<S>)Gamma);
		}
		return retour;
	}
	public static <S> GrapheNonValue<S> grapheNonValue(EnsembleSommet<S> X, EnsembleArcNonValue<S> Gamma)
	{
		return new GrapheNonValueImpl<>(X,Gamma);
	}
	public static <S> GrapheNonValue<S> grapheNonValue(GrapheNonValue<S> g)
	{
		return new GrapheNonValueImpl<>(g);
	}
	public static <S> GrapheNonValue<S> grapheNonValue()
	{
		return new GrapheNonValueImpl<>();
	}
	public static <S> GrapheValue<S> grapheValue(GrapheValue<S> g)
	{
		return new GrapheValueImpl<>(g);
	}
	public static <S> GrapheValue<S> grapheValue()
	{
		return new GrapheValueImpl<>();
	}
	public static <S> GrapheValue<S> grapheValue(EnsembleSommet<S> X, EnsembleArcValue<S> Gamma)
	{
		return new GrapheValueImpl<>(X,Gamma);
	}
	// Cout
	public static Cout cout(Cout cout)
	{
		return new CoutImpl(cout);
	}
	public static Cout cout(float valeur)
	{
		return new CoutImpl(valeur);
	}
	public static Cout cout()
	{
		return new CoutImpl();
	}
	// Autres
	public static <S> TableauPlusCC<S> tableauPlusCC(Sommet<S> principal, GrapheValue<S> G)
	{
		return new TableauPlusCCImpl<>(principal,G);
	}
	public static <S> TableauPlusCC<S> tableauPlusCC(Sommet<S> principal)
	{
		return new TableauPlusCCImpl<>(principal);
	}
	public static <S> CFC<S> CFC(EnsembleSommet<S> X)
	{
		return new CFCImpl<>(X);
	}
	public static <E,S> Ensemble<E> ensemble(Ensemble<E> ensemble)
	{
		Ensemble<E> ensembleRetour;
		String className=ensemble.getClass().getSimpleName();
		if(className.equals("EnsembleArcNonValueImpl"))
		{
			ensembleRetour=(Ensemble<E>)Factory.ensembleArcNonValue((EnsembleArcNonValue<S>)ensemble);
		}
		else if(className.equals("EnsembleArcValueImpl"))
		{
			ensembleRetour=(Ensemble<E>)Factory.ensembleArcValue((EnsembleArcValue<S>)ensemble);
		}
		else // if(className.equals("EnsembleSommet"))
		{
			ensembleRetour=(Ensemble<E>)Factory.ensembleSommet((EnsembleSommet<S>)ensemble);
		}
		return ensembleRetour;
	}
}