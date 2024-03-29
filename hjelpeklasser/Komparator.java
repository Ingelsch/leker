package hjelpeklasser;

import java.util.Comparator;

@FunctionalInterface                // legges i mappen hjelpeklasser
public interface Komparator<T> extends Comparator<T>      // et funksjonsgrensesnitt
{//Programkode 1.4.6 a)
	int compare(T x, T y);            // en abstrakt metode

	// Statiske metoder:

	public static <T extends Comparable<? super T>> Komparator<T> naturligOrden()
	{
		return (x,y) -> x.compareTo(y);
	}

	public static <T extends Comparable<? super T>> Komparator<T> omvendtOrden()
	{
		return (x, y) -> y.compareTo(x);
	}

	public static <T, R extends Comparable<? super R>>
	Komparator<T> orden(Funksjon<? super T, ? extends R> velger)
	{
		return (x, y) -> velger.anvend(x).compareTo(velger.anvend(y));
	}

	public static <T, R> Komparator<T> orden
			(Funksjon<? super T, ? extends R> velger, Komparator<? super R> c)
	{
		return (x, y) -> c.compare(velger.anvend(x), velger.anvend(y));
	}

	// Default metoder

	default Komparator<T> deretter(Komparator<? super T> c)
	{
		return (x, y) ->
		{
			int k = compare(x, y);
			return k != 0 ? k : c.compare(x, y);
		};
	}

	default <R extends Comparable<? super R>>
	Komparator<T> deretter(Funksjon<? super T, ? extends R> velger)
	{
		return (x, y) ->
		{
			int k = compare(x, y);
			return k != 0 ? k : velger.anvend(x).compareTo(velger.anvend(y));
		};
	}

	default <R> Komparator<T>
	deretter(Funksjon<? super T, ? extends R> velger, Komparator<? super R> c)
	{
		return (x, y) ->
		{
			int k = compare(x, y);
			return k != 0 ? k : c.compare(velger.anvend(x), velger.anvend(y));
		};
	}

	default Komparator<T> omvendt()
	{
		return (x, y) -> compare(y, x);  // bytter x og y
	}

}

