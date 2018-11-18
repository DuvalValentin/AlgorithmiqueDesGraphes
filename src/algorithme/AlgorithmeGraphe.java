package algorithme;

import java.util.LinkedList;
import java.util.Stack;

import graphElements.*;

public class AlgorithmeGraphe
{
	@SuppressWarnings("unchecked")
	public static <S> void /*EnsembleArc<S>*/ DFS (Graphe<S> G, Sommet<S> x)
	{
		EnsembleArc<S> V; //Ensemble des arcs visités
		EnsembleSommet<S> D;//Ensemble des sommets non visités
		EnsembleSommet<S> T=new EnsembleSommet<S>(); //Ensemble des sommets visités
		EnsembleSommet<S> X; //X,Y,Z liste non visités à partir de x,y et z respectivement
		EnsembleSommet<S> Y;
		EnsembleSommet<S> Z;
		Stack<Sommet<S>> A=new Stack<Sommet<S>>(); 
		Sommet<S>y;
		Sommet<S>z;
		
		
		//Init
		D=new EnsembleSommet<S>(G.getX());
		D.remove(x);
		V=new EnsembleArc<S>();
		X=G.listSucc(x);
		
		if (X.isEmpty())
		{
			T.add(x);
		}
		else
		{
			A.push(x);
			T.clear();
		}
		Graphe<S> Gavisiter=G.clone();
		while(!A.isEmpty())
		{
			y=A.peek();
			Y=Gavisiter.listSucc(y);
			if(!Y.isEmpty())
			{
				Sommet<S>[] Ytab=new Sommet[Y.size()]; 
				z=Y.toArray(Ytab)[0];
				Arc<S> arc =new Arc<S>(y,z);
				Gavisiter.supprarc(arc);
				V.add(arc);
				if (D.contains(z))
				{
					D.remove(z);
					Z=Gavisiter.listSucc(z);
					if (!Z.isEmpty())
					{
						A.push(z);
					}
					else
					{
						T.add(z);
					}
				}
			}
			else
			{
				T.add(y);
				A.pop();
			}
		}
		//return V; afin d'effecuer des test
	}
	
	public static <S> void/*EnsembleArc<S>*/  WFS (Graphe<S> G, Sommet<S> x)
	{
		EnsembleArc<S> V;
		EnsembleSommet<S> D;
		EnsembleSommet<S> T;
		EnsembleSommet<S> S;
		LinkedList<Sommet<S>> A=new LinkedList<Sommet<S>>();
		Sommet<S>y;
		//Init
		D=new EnsembleSommet<S> (G.getX());
		D.remove(x);
		A.add(x);
		T=new EnsembleSommet<S>();
		V=new EnsembleArc<S>();
		while(!A.isEmpty())
		{
			y=A.poll();
			T.add(y);
			S=G.listSucc(y);
			for (Sommet<S> z : S)
			{
				Arc<S> arc = new Arc<S>(y,z);
				V.add(arc);
				if (D.contains(z))
				{
					D.remove(z);
					A.add(z);
				}
			}
		}
		//return V; //à décommenter pour effectuer des tests
	}
}
