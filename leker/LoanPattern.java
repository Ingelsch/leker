package leker;

import java.util.function.Consumer;

/**
 * Created by inge on 29.10.2015.
 */
public class LoanPattern
{
	public static class Resource
	{
		public Resource()
		{
			System.out.println("Opening resource");

		}

		public void operate()
		{
			System.out.println("Operating on resource");
		}

		public void dispose()
		{
			System.out.println("Disposing of resource");
		}

		public static void withResource(Consumer<Resource> consumer)
		{
			Resource r = new Resource();
			try
			{
				consumer.accept(r);
			}
			finally
			{
				r.dispose();
			}
		}
	}

	public static void main(String[] args)
	{
		Resource.withResource(r -> r.operate());
	}
}
