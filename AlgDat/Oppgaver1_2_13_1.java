package algDat;

import hjelpeklasser.*;

import java.util.Arrays;

/**
 * Created by Inge on 16.09.2015.
 */
public class Oppgaver1_2_13_1
{
    public static void main(String[] args)
    {
        int[] a = {3,1,4,9,7,10,8,6,5,2};        // permutasjon av tallene fra 1 til 10
        Tabell.nestePermutasjon(a);              // lager neste permutasjon
        System.out.println(Arrays.toString(a));  // [3, 1, 4, 9, 8, 2, 5, 6, 7, 10]*/
    }
}
/*  int[] a = {3,1,4,9,7,10,8,6,5,2};        // permutasjon av tallene fra 1 til 10
  Tabell.nestePermutasjon(a);              // lager neste permutasjon
  System.out.println(Arrays.toString(a));  // [3, 1, 4, 9, 8, 2, 5, 6, 7, 10]*/

/*      int n = 100000;         // tabellstørrelse
        int antall = 1000;      // antall gjentagelser

        long tid = 0;           // for tidsmåling

        int a[] = Tabell.randPerm(n);  // en permutasjon av 1, . .  n

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) Tabell.nestMaks1(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks1-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) Tabell.nestMaks(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks1-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) Tabell.nestMaks2(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks2-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) Tabell.nestMaks3(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks3-metoden: " + tid + " millisek");*/