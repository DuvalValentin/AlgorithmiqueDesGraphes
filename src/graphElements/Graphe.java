package graphElements;

public class Graphe<S>
{
	private EnsembleSommet<S> X;
	private EnsembleArc<S> Gamma;
	
	public Graphe(EnsembleSommet<S> x,EnsembleArc<S> gamma)
	{
		this.setX(x);
		this.setGamma(gamma);
	}
	
	@Override
	public String toString()
	{
		String str="(X="+this.getX().toString()+", Gamma="+this.getGamma().toString()+")";
		return str;
	}
	
	public EnsembleSommet<S> getX() 
	{
		return X;
	}
	public EnsembleArc<S> getGamma() 
	{
		return Gamma;
	}
	
	public void setX(EnsembleSommet<S> x) 
	{
		X = x;
	}
	public void setGamma(EnsembleArc<S> gamma) 
	{
		Gamma = gamma;
	}

}

