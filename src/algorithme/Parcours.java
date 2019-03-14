package algorithme;

import java.util.LinkedList;
import java.util.Stack;

import factory.Factory;
import graphElements.Interfaces.*;

public class Parcours
{
	@SuppressWarnings("unchecked")
	public static <S,A extends InterfaceArc<S>> void DFS (InterfaceGraphe<S,A> G) throws Exception
	{
		InterfaceEnsembleArc<S,A> V; //Ensemble des arcs visités
		InterfaceEnsembleSommet<S> D;//Ensemble des sommets non visités
		InterfaceEnsembleSommet<S> T=Factory.ensembleSommet(); //Ensemble des sommets visités
		InterfaceEnsembleSommet<S> Y;//Ensemble de successeur de y
		InterfaceEnsembleSommet<S> Z;//Ensemble de successeur de z
		Stack<InterfaceSommet<S>> A=new Stack<InterfaceSommet<S>>();//Pile 
		InterfaceSommet<S>y;
		InterfaceSommet<S>z;
		//Init
		D=Factory.ensembleSommet(G.getX());//Ensemble de sommets non visité
		V=Factory.ensembleArc(G.getGamma().getClass().getSimpleName());
		InterfaceGraphe<S,A> Gavisiter = Factory.graphe(G);

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
					A arc = (A) Factory.arc(G.getClass().getSimpleName(), y, z,Factory.cout());
					Gavisiter.supprArc( arc);
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
					Gavisiter.supprSommet(y);
					T.ajouteSommet(y);
					A.pop();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <S,A extends InterfaceArc<S>> void WFS (InterfaceGrapheNonValue<S> G, InterfaceSommet<S> x)
	{
		InterfaceEnsembleArc<S,A> V;//Ensemble des arcs parcours
		InterfaceEnsembleSommet<S> D;//Ensemble des sommets non visités
		InterfaceEnsembleSommet<S> T;//Ensemble des sommets visités
		InterfaceEnsembleSommet<S> S;//Liste de successeur de y
		LinkedList<InterfaceSommet<S>> A=new LinkedList<InterfaceSommet<S>>();//File
		InterfaceSommet<S>y;
		//Init
		D=Factory.ensembleSommet(G.getX());
		D.supprSommet(x);
		A.add(x);
		T=Factory.ensembleSommet();
		V=Factory.ensembleArc(G.getGamma().getClass().getSimpleName());
		while(!A.isEmpty())
		{
			y=A.poll();
			T.ajouteSommet(y);
			S=G.listSucc(y);
			for (InterfaceSommet<S> z : S.getEnsemble())
			{
				A arc = (A) Factory.arc(G.getClass().getSimpleName(), y, z, Factory.cout());
				V.ajouteArc(arc);
				if (D.existeSommet(z))
				{
					D.supprSommet(z);
					A.add(z);
				}
			}
		}
	}
}