package graphElements;

public class EnsembleArc<S> extends AbstractEnsembleArc<S,Arc<S>>
{
	private static final long serialVersionUID = -8258552281988584936L;
	
	public EnsembleArc(Ensemble<Arc<S>> ensemble)
	{
		super(ensemble);
	}
	public EnsembleArc()
	{
		super();
	}
	
	public void add(Sommet<S> depart,Sommet<S>arrivee)
	{
		add(new Arc<S>(depart,arrivee));
	}
//TODO à iplémenter dans ensemble arc values
	
	@Override
	public EnsembleArc<S> clone ()
	{
		return (EnsembleArc<S>)super.clone();
	}
	

}
