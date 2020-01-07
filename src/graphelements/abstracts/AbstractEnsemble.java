package graphelements.abstracts;

import java.util.HashSet;

import graphelements.interfaces.InterfaceEnsemble;
public abstract class AbstractEnsemble<E> implements InterfaceEnsemble<E>
{
	//L'ensemble encapsulé
	protected HashSet<E> ensemble;
	//Constructeur
	public AbstractEnsemble ()
	{
		ensemble=new HashSet<>();
	}
	@Override
	public boolean isEmpty()
	{
		return ensemble.isEmpty();
	}
	@Override
	public void clear()
	{
		ensemble.clear();
	}
	
	@Override
	public boolean contient(InterfaceEnsemble<E> ensemble)
	{
		return this.equals(InterfaceEnsemble.union(this, ensemble));
	}
	
	@Override
	public String toString()
	{
		StringBuilder str=new StringBuilder("{");
		if(!ensemble.isEmpty())
		{
			for(E e: ensemble)
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
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj!=null&&obj.getClass()==getClass()&&((AbstractEnsemble<E>)obj).ensemble.equals(ensemble))
		{
					result=true;
		}
		return result;
	}
	
	@Override
	public int hashCode()
	{
		int somme=0;
		for(E element : ensemble)
		{
			somme+=element.hashCode();
		}
		return somme;
	}
}