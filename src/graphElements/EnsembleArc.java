package graphElements;

public class EnsembleArc<S> extends Ensemble<Arc<S>>
{
	public EnsembleArc(Ensemble<Arc<S>> ensemble)
	{
		super(ensemble);
	}
	public EnsembleArc()
	{
		super();
	}

	private static final long serialVersionUID = -4099925554493145279L;
	
	
	public EnsembleSommet<S> listSucc(Sommet<S> sommet)
	{
		EnsembleSommet<S> XSucc=new EnsembleSommet<S>();
		for (Arc<S> arc : this)
		{
			if (arc.getDepart()==sommet)
			{
				XSucc.add(arc.getArrivee());
			}
		}
		return XSucc;
	}
	
	public EnsembleSommet<S> listPred(Sommet<S> sommet)
	{
		EnsembleSommet<S> XPred=new EnsembleSommet<S>();
		for (Arc<S> arc : this)
		{
			if (arc.getArrivee()==sommet)
			{
				XPred.add(arc.getDepart());
			}
		}
		return XPred;
	}
	
	public void add(Sommet<S> depart,Sommet<S>arrivee)
	{
		add(new Arc<S>(depart,arrivee));
	}
	@Override
	public EnsembleArc<S> clone ()
	{
		return (EnsembleArc<S>)super.clone();
	}
}
