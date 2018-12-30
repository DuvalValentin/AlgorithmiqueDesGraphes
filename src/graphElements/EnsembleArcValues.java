package graphElements;

public class EnsembleArcValues<S> extends AbstractEnsembleArc<S,ArcValue<S>> 
{
	private static final long serialVersionUID = -7163498825360866323L;
	
	public EnsembleArcValues(Ensemble<ArcValue<S>> ensemble)
	{
		super(ensemble);
	}
	public EnsembleArcValues()
	{
		super();
	}
	
	public void add(Sommet<S> depart,Sommet<S>arrivee,Cout value)
	{
		boolean absent=true;
		for(ArcValue<S> AV : this)
		{
			if(AV.getDepart()==depart&&AV.getArrivee()==arrivee)
			{
				absent=false;
			}
		}
		if(absent)
		{
			add(new ArcValue<S>(depart,arrivee,value));
		}
	}
	
	
	public boolean getValue(Sommet<S> depart, Sommet<S> arrivee,Cout result)
	{
		boolean succes=false;
		for(ArcValue<S> arcV : this)
		{
			if (arcV.getDepart()==depart && arcV.getArrivee()==arrivee)
			{
				result.setValeur(arcV.getCout().getValeur());
				succes=true;
				break;
			}
		}
		return succes;
	}
}