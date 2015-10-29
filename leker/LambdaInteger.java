package leker;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by inge on 29.10.2015.
 */
public class LambdaInteger
{
	public static int sum(List<Integer> numbers, Predicate<Integer> p)
	{
		int total = 0;
		for (int n : numbers)
		{
			if (p.test(n))
			{
				total += n;
			}
		}
		return total;
	}

	public static void main(String... args)
	{
		List<Integer> numbers = Arrays.asList(-3, 1, 2, 3, 4, 5, 6);

		System.out.println(sum(numbers, n -> true));
		System.out.println(sum(numbers, n -> (n & 1) != 0)); //summerer alle oddetall i lista
		System.out.println(sum(numbers, n -> (n % 2) == 1)); //summerer alle positive oddetall i lista
		System.out.println(sum(numbers, n -> n > 3));
	}
}
