package hubberspot;

//implementerer Linked List, enkeltlenket liste
public class EnkeltLenketListe
{
	//a static inner(private) class ListNode
	private static class ListNode
	{
		private int data;
		private ListNode neste;

		public ListNode(int data)
		{
			this.data = data;
			this.neste = null;
		}
	}

	public ListNode settInnForrest(ListNode hode, int data)
	{
		ListNode nyNode = new ListNode(data);
		if (hode == null)
		{
			return nyNode;
		}
		nyNode.neste = hode;
		hode = nyNode;
		return hode; //viser nytt hode, med ny node forrest
	}

	public ListNode slettFørste(ListNode hode)
	{
		if (hode == null)
		{
			return hode;
		}
		ListNode tmp = hode;
		hode = hode.neste;
		tmp.neste = null;
		return tmp;
	}

	public ListNode settInnBakerst(ListNode hode, int data)
	{
		ListNode nyNode = new ListNode(data);
		if (hode == null)
		{
			return nyNode;
		}
		ListNode denne = hode;
		while (null != denne.neste)
		{
			denne = denne.neste; //traverserer denne noden til dennes neste
		}
		denne.neste = nyNode;
		return hode;
	}

	public ListNode slettSiste(ListNode hode)
	{
		if (hode == null)
		{
			return hode;
		}
		ListNode siste = hode;
		ListNode nestSiste = null;
		while (siste.neste != null)
		{
			nestSiste = siste;
			siste = siste.neste;
		}
		nestSiste.neste = null;
		return siste;
	}

	public ListNode settInnVedPosisjon(ListNode hode, int data, int posisjon)
	{
		//perform boundary checks
		int str = lengde(hode);

		if (posisjon > str + 1 || posisjon < 1)
		{
			System.out.println("Invalid posisjon!");
			return hode;
		}
		ListNode nyNode = new ListNode(data);
		if (posisjon == 1)
		{
			nyNode.neste = hode;
			return nyNode;
		}
		else
		{
			ListNode forrige = hode;
			int antall = 1;
			while (antall < posisjon - 1)
			{
				forrige = forrige.neste;
				antall++;
			}
			ListNode denne = forrige.neste;
			nyNode.neste = denne;
			forrige.neste = nyNode;
			return hode;
		}
	}

	public ListNode slettVedGittPosisjon(ListNode hode, int posisjon)
	{
		int str = lengde(hode);
		if (posisjon > str || posisjon < 1)
		{
			System.out.println("Invalid posisjon.");
			return hode;
		}
		if (posisjon == 1)
		{
			ListNode tmp = hode;
			hode = hode.neste;
			tmp.neste = null;
			return tmp;
		}
		else
		{
			ListNode forrige = hode;
			int antall = 1;
			while (antall < posisjon - 1)
			{
				forrige = forrige.neste;
				antall++;
			}
			ListNode denne = forrige.neste;
			denne.neste = null;
			return denne;
		}
	}

	//Man har et ListNode, skriv ut alle nodene
	public void vis(ListNode hode)
	{
		if (hode == null)
		{
			return;
		}
		ListNode denne = hode;

		//loop each element in linket list till end
		//last node points to null
		while (denne != null)
		{
			System.out.print(denne.data + " --> ");//print denne element's data

			denne = denne.neste;//move to neste element
		}
		System.out.print(denne); //here, denne will be null
	}

	//Man har et ListNode hode, finn ut lengde på lenket liste
	public static int lengde(ListNode head)
	{
		if (head == null)
		{
			return 0;
		}
		//create a antall variable to hold lengde
		int antall = 0;

		//loop through each element till list ends
		ListNode denne = head;
		while (denne != null)
		{
			antall++;
			//move to neste node
			denne = denne.neste;
		}
		return antall;//lengde of linked list
	}


	public static void main(String[] args)
	{//let's create a linked list
		//10 --> 8 -->1 -->11 --> null
		//10 as hode node of linked list

		ListNode hode = new ListNode(10);
		ListNode andre = new ListNode(8);
		ListNode tredje = new ListNode(1);
		ListNode fjerde = new ListNode(11);
		ListNode femte = new ListNode(19);


		//Attach them together to form a list
		hode.neste = andre;//10,8
		andre.neste = tredje;//10,8,1
		tredje.neste = fjerde;//10,8,1,11
		fjerde.neste = femte;//10,8,1,11,19,null

		EnkeltLenketListe ELliste = new EnkeltLenketListe();
		ELliste.vis(hode);

		System.out.println();
		System.out.print("\nLengde på lenket liste: " + EnkeltLenketListe.lengde(hode) + "\n\n");


		//settInnForrest
		ListNode nyNode = ELliste.settInnForrest(hode, 15);
		ELliste.vis(nyNode);
		System.out.println();
		//System.out.print("\nLengde på lenket liste: " + EnkeltLenketListe.lengde(nyNode) + "\n\n");

		//settInnBakerst
		ListNode nyBakNode = ELliste.settInnBakerst(hode, 17);
		ELliste.vis(nyBakNode);
		System.out.println();
		//System.out.print("\nLengde på lenket liste: " + EnkeltLenketListe.lengde(nyBakNode) + "\n\n");

		//settInnVedPosisjon
		ListNode nyIVP = ELliste.settInnVedPosisjon(hode, 14, 5);
		ELliste.vis(nyIVP);
		System.out.println();
		//System.out.print("\nLengde på lenket liste: " + EnkeltLenketListe.lengde(nyIVP) + "\n\n");

		//slettFørste
		/*ListNode slettF = ELliste.slettFørste(hode);
		System.out.print("Den første slettes " + slettF.data); //skriver data som hører til sist slettede
		System.out.println();*/
		//System.out.print("\nLengde på lenket liste: " + EnkeltLenketListe.lengde(slettF) + "\n\n");

		//slettSiste
		ListNode slettS = ELliste.slettSiste(hode);
		System.out.print("Den siste slettes: " + slettS.data); //skriver data som hører til sist slettede
		System.out.println();
		//System.out.print("\nLengde på lenket liste: " + EnkeltLenketListe.lengde(slettS) + "\n\n");

		//slettAngitt
		/*ListNode slettAngitt = ELliste.slettVedGittPosisjon(hode,3);
		System.out.print("Denne her slettes " + slettAngitt.data);*/
	}
}