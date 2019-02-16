package algorithme;

import java.util.LinkedList;
import java.util.Stack;

import graphElements.Elements.*;

public class Parcours
{
	public static <S,A extends Arc<S>> void DFS (GrapheNonValue<S> G)
	{
		EnsembleArcNonValue<S> V; //Ensemble des arcs visités
		EnsembleSommet<S> D;//Ensemble des sommets non visités
		EnsembleSommet<S> T=new EnsembleSommet<S>(); //Ensemble des sommets visités
		EnsembleSommet<S> Y;//Ensemble de successeur de y
		EnsembleSommet<S> Z;//Ensemble de successeur de z
		Stack<Sommet<S>> A=new Stack<Sommet<S>>();//Pile 
		Sommet<S>y;
		Sommet<S>z;
		//Init
		D=new EnsembleSommet<S>(G.getX());//Ensemble de sommets non visité
		V=new EnsembleArcNonValue<S>();//Ensemble d'arc parcourus TODO à changer pour que DFS puisse prendre un AbstractGraph en entrée
		GrapheNonValue<S> Gavisiter=new GrapheNonValue<S>(G);//TODO à changer pour que DFS puisse prendre un AbstractGraph en entrée

		while(!D.isEmpty())
		{
			A.push(Gavisiter.firstSommet());
			D.supprSommet(Gavisiter.firstSommet());
			while(!A.isEmpty())
			{
				y=A.peek();
				Y=Gavisiter.listSucc(y);
				if(!Y.isEmpty())
				{
					z=Y.firstSommet();
					Arc<S> arc =new Arc<S>(y,z);//A TODO changer pour pouvoir utiliser AbstractGraphe
					Gavisiter.supprArc(arc);
					V.ajouteArc(arc);
					if (D.existeSommet(z))
					{
						D.supprSommet(z);
						Z=Gavisiter.listSucc(z);
						if (!Z.isEmpty())
						{
							A.push(z);
						}
						else
						{
							T.ajouteSommet(z);
						}
					}
				}
				else
				{
					T.ajouteSommet(y);
					A.pop();
				}
			}
		}
		assert G.getGamma().equals(V) : "DFS ne marche pas";
		assert G.getX().equals(T) : "DFS ne marche pas";
	}
	
	public static <S> void WFS (GrapheNonValue<S> G, Sommet<S> x)
	{
		EnsembleArcNonValue<S> V;//Ensemble des arcs parcours
		EnsembleSommet<S> D;//Ensemble des sommets non visités
		EnsembleSommet<S> T;//Ensemble des sommets visités
		EnsembleSommet<S> S;//Liste de successeur de y
		LinkedList<Sommet<S>> A=new LinkedList<Sommet<S>>();//File
		Sommet<S>y;
		//Init
		D=new EnsembleSommet<S> (G.getX());
		D.supprSommet(x);
		A.add(x);
		T=new EnsembleSommet<S>();
		V=new EnsembleArcNonValue<S>();
		while(!A.isEmpty())
		{
			y=A.poll();
			T.ajouteSommet(y);
			S=G.listSucc(y);
			for (Sommet<S> z : S.getEnsemble())
			{
				Arc<S> arc = new Arc<S>(y,z);
				V.ajouteArc(arc);
				if (D.existeSommet(z))
				{
					D.supprSommet(z);
					A.add(z);
				}
			}
		}
		//assert G.getGamma().equals(V) : "WFS ne marche pas";
	}
}
