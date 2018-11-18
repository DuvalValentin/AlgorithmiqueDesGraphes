package graphElements;

public class EnsembleSommet<S> extends Ensemble<Sommet<S>>
{
	public EnsembleSommet()
	{
		super();
	}
	public EnsembleSommet(Ensemble<Sommet<S>> ensemble)
	{
		super(ensemble);
	}

	private static final long serialVersionUID = 7278825382690341067L;
	
	@Override
	public EnsembleSommet<S> clone ()
	{
		return (EnsembleSommet<S>)super.clone();
	}
	//existsSommet=>contains
}
