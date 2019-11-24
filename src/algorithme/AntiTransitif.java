package algorithme;

import factory.Factory;
import graphElements.Interfaces.*;

public class AntiTransitif
{
	private AntiTransitif(){}
	
	public static <S> InterfaceGrapheNonValue<S> TauMinalite(InterfaceGrapheNonValue<S> G)
	{
		InterfaceEnsembleSommet<S>X=G.getX();
		InterfaceGrapheNonValue<S> RW=Factory.grapheNonValue(G);
		InterfaceGrapheNonValue<S> TauMin=Factory.grapheNonValue(G);//Graphe tauMinimal
		
		for(InterfaceSommet<S> z : X.getEnsemble()) 
		{
			for(InterfaceSommet<S> x : X.getEnsemble())
			{
				if(RW.existeArc(x,z))
				{
					for(InterfaceSommet<S> y : X.getEnsemble())
					{
						if(RW.existeArc(z,y))
						{
							RW.ajouteArc(x, y);
							TauMin.supprArc(x, y);
						}
					}
				}
			}
		}
		return TauMin;
	}
}
