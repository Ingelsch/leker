package hjelpeklasser;

/**
 * Created by inge on 18.11.2015.
 */

import java.util.NoSuchElementException;

public class TabellToveiskø<T> implements Toveiskø<T>
{
	private T[] a;
	private int fra, til;

	private T[] utvidTabell(int lengde)
	{
		T[] b = (T[]) new Object[lengde];  // ny tabell

		// kopierer intervallet a[fra:a.lengde> over i b
		System.arraycopy(a, fra, b, 0, a.length - fra);

		// kopierer intervallet a[0:fra> over i b
		System.arraycopy(a, 0, b, a.length - fra, fra);

		fra = 0;
		til = a.length;

		return b;
	}

	public TabellToveiskø(int størrelse)
	{
		a = (T[]) new Object[størrelse + 1];
		fra = til = 0;
	}

	public TabellToveiskø()
	{
		this(8);
	}

	public int antall()
	{
		if (fra <= til)
		{
			return til - fra;
		}
		else
		{
			return a.length + til - fra;
		}
	}

	public boolean tom()
	{
		return fra == til;
	}

	public void leggInnSist(T t)
	{
		a[til++] = t;
		if (til == a.length)
		{
			til = 0;
		}
		if (fra == til)
		{
			a = utvidTabell(2 * a.length);
		}
	}

	public void leggInnFørst(T t)
	{
		if (fra == 0)
		{
			fra = a.length - 1;
		}
		else
		{
			fra--;
		}
		a[fra] = t;
		if (fra == til)
		{
			a = utvidTabell(2 * a.length);  // dobler tabellen
		}
	}

	public T kikkFørst()
	{
		if (tom())
		{
			throw new NoSuchElementException("Køen er tom!");
		}
		return a[fra];
	}

	public T kikkSist()
	{
		if (fra == til)
		{
			throw new NoSuchElementException("Køen er tom!");
		}
		if (til == 0)
		{
			return a[a.length - 1];
		}
		else
		{
			return a[til - 1];
		}
	}

	public T taUtFørst()
	{
		if (tom())
		{
			throw new NoSuchElementException("Køen er tom!");
		}

		T temp = a[fra];
		a[fra] = null;
		fra++;
		if (fra == a.length)
		{
			fra = 0;
		}
		return temp;
	}

	public T taUtSist()
	{
		if (fra == til)
		{
			throw new NoSuchElementException("Køen er tom!");
		}
		if (til == 0)
		{
			til = a.length - 1;
		}
		else
		{
			til--;
		}
		T temp = a[til];
		a[til] = null;
		return temp;
	}

	public void nullstill()
	{
		while (fra != til)
		{
			a[fra++] = null;
			if (fra == a.length)
			{
				fra = 0;
			}
		}
	}

	public String toString()
	{
		if (tom())
		{
			return "[]";
		}

		StringBuilder s = new StringBuilder();
		s.append('[');

		s.append(a[fra]);

		if (fra < til)
		{
			for (int i = fra + 1; i < til; i++)
			{
				s.append(',').append(' ').append(a[i]);
			}
		}
		else
		{
			for (int i = fra + 1; i < a.length; i++)
			{
				s.append(',').append(' ').append(a[i]);
			}
			for (int i = 0; i < til; i++)
			{
				s.append(',').append(' ').append(a[i]);
			}
		}
		s.append(']');

		return s.toString();
	}

} // TabellToveiskø

