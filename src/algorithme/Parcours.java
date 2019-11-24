package algorithme;

import java.util.LinkedList;
import java.util.Stack;

import factory.Factory;
import graphElements.Interfaces.*;

public class Parcours
{
	private Parcours(){}
	
	@SuppressWarnings("unchecked")
	public static <S,A extends InterfaceArc<S>> void DFS (InterfaceGraphe<S,A> G)
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
			A.push(Gavisiter.pickSommet());
			D.supprElement(Gavisiter.pickSommet());
			while(!A.isEmpty())
			{
				y=A.peek();
				Y=Gavisiter.listSucc(y);
				if(!Y.isEmpty())
				{
					z=Y.pickSommet();
					A arc = (A) Factory.arc(G.getClass().getSimpleName(), y, z,Factory.cout());
					Gavisiter.supprArc( arc);
					V.ajouteElement(arc);
					if (D.existeSommet(z))
					{
						D.supprElement(z);
						Z=Gavisiter.listSucc(z);
						if (!Z.isEmpty())
						{
							A.push(z);
						}
						else
						{
							T.ajouteElement(z);
						}
					}
				}
				else
				{
					Gavisiter.supprSommet(y);
					T.ajouteElement(y);
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
		D.supprElement(x);
		A.add(x);
		T=Factory.ensembleSommet();
		V=Factory.ensembleArc(G.getGamma().getClass().getSimpleName());
		while(!A.isEmpty())
		{
			y=A.poll();
			T.ajouteElement(y);
			S=G.listSucc(y);
			for (InterfaceSommet<S> z : S.getEnsemble())
			{
				A arc = (A) Factory.arc(G.getClass().getSimpleName(), y, z, Factory.cout());
				V.ajouteElement(arc);
				if (D.existeSommet(z))
				{
					D.supprElement(z);
					A.add(z);
				}
			}
		}
	}
}