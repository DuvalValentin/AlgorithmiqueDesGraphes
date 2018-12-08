package graphElements;

import java.util.HashSet;
public class Ensemble<E> extends HashSet<E>
{
	private static final long serialVersionUID = -4354387895748449845L;

	public Ensemble ()
	{
		super();
	}
	public Ensemble (Ensemble<E> ensemble)
	{
		for (E e : ensemble)
		{
			add(e);
		}
	}
	
	public String toString()
	{
		StringBuffer str=new StringBuffer("{");
		for(E e: this)
		{
			str=str.append(e.toString()+",");
		}
		str.setCharAt(str.length()-1, '}');
		return str.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Ensemble<E> clone()
	{
		return (Ensemble<E>)super.clone();
	}
}
