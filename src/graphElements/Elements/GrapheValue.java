package graphElements.Elements;

import graphElements.Abstract.AbstractGraphe;
import graphElements.Interfaces.*;

public class GrapheValue<S> extends AbstractGraphe<S,ArcValue<S>> implements InterfaceGrapheValue<S>
{
	public GrapheValue()
	{
		super();
		setGamma(new EnsembleArcValue<S>());
	}
	public GrapheValue(EnsembleSommet<S> x,EnsembleArcValue<S> gamma)
	{
		super(x,gamma);
	}
	public GrapheValue(GrapheValue<S> G)
	{
		super(G);
	}
	
	@Override
	public EnsembleArcValue<S> getGamma()
	{
		return (EnsembleArcValue<S>)super.getGamma();
	}
	
	/*@Override
	public boolean getCout(Sommet<S> depart, Sommet<S> arrivee,Cout cout)
	{
		return ((EnsembleArcValue<S>)Gamma).getCout(depart,arrivee,cout);
	}*/
	
	@Override
	public Cout getCout(Sommet<S> depart, Sommet<S> arrivee)
	{
		return ((EnsembleArcValue<S>)Gamma).getCout(depart,arrivee);
	}
	
	@Override
	public void ajouteArc(Sommet<S> depart, Sommet<S> arrivee,Cout cout)
	{
		ajouteArc(new ArcValue<S>(depart,arrivee,cout));
	}
	
	//@Override
	public void supprArc(Sommet<S>depart,Sommet<S>arrivee)
	{
		Cout cout=getCout(depart,arrivee);
		if(cout!=null)
		{
			ArcValue<S> suppr=new ArcValue<S>(depart,arrivee,cout);
			supprArc(suppr);
		}
	}
	@Override
	public boolean setCout(Sommet<S> depart, Sommet<S> arrivee, Cout cout)
	{
		return ((EnsembleArcValue<S>)Gamma).setCout(depart,arrivee,cout);
	}
}