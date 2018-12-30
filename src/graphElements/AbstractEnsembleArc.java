package graphElements;

public abstract class AbstractEnsembleArc<S,A extends Arc<S>> extends Ensemble<A>
{
	public AbstractEnsembleArc(Ensemble<A> ensemble)
	{
		super(ensemble);
	}
	public AbstractEnsembleArc()
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
	
	@SuppressWarnings("unchecked")
	@Override
	public  AbstractEnsembleArc<S,A> clone ()
	{
		return (AbstractEnsembleArc<S,A>)super.clone();
	}//doir être reféfinie
}