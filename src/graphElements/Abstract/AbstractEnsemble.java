package graphElements.Abstract;

import java.util.HashSet;
public abstract class AbstractEnsemble<E> extends HashSet<E> //possibilité de le passer en abstract
{
	private static final long serialVersionUID = -4354387895748449845L;

	public AbstractEnsemble ()
	{
		super();
	}
	public AbstractEnsemble (AbstractEnsemble<E> ensemble)
	{
		for (E e : ensemble)
		{
			add(e);
		}
	}
	
	@Override
	public String toString()
	{
		StringBuffer str=new StringBuffer("{");
		if(!isEmpty())
		{
			for(E e: this)
			{
				str=str.append(e.toString()+",");
			}
			str.setCharAt(str.length()-1, '}');
		}
		else
		{
			str.append("}");
		}
		
		return str.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AbstractEnsemble<E> clone()
	{
		return (AbstractEnsemble<E>)super.clone();
	}
	
	public void union(AbstractEnsemble<E> ensemble)
	{
		for(E element : ensemble)
		{
			add(element);
		}
	}
}
