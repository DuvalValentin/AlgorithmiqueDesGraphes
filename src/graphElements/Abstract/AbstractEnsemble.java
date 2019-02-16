package graphElements.Abstract;

import java.util.HashSet;

import graphElements.Interfaces.InterfaceEnsemble;
public abstract class AbstractEnsemble<E> implements InterfaceEnsemble<E>
{
	//L'ensemble encapsulé
	protected HashSet<E> ensemble;
	//Constructeur
	public AbstractEnsemble ()
	{
		ensemble=new HashSet<E>();
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
	/*
	public void intersection(AbstractEnsemble<E> Ensemble)
	{
		//TODO problème ici on ne peut pas changer un ensemble sur lequel on applique un for
		//FIXME 
		for(E element : ensemble)
		{
			if(!Ensemble.ensemble.contains(element))
			{
				ensemble.remove(element);
			}
		}
	}*/
	@Override
	public String toString()
	{
		StringBuffer str=new StringBuffer("{");
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
		if(obj!=null)
		{
			if(obj.getClass()==getClass())
			{

				if(((AbstractEnsemble<E>)obj).ensemble.equals(ensemble))
				{
					result=true;
				}
			}

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