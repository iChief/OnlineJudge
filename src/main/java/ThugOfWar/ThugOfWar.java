package ThugOfWar;

import java.util.*;

/**
 * Thug Of War (10032)
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=973
 *
 * @author Ahmed Schahadeh (UVa: iChief)
 */

class Main {
    /**
     * Es sollen zwei Teams geformt werden bei denen der Unterschied der Personen nicht grösser ist als 1. Das Gewicht
     * der einzelnen Teammitglieder soll so gut wie möglich ausgeglichen sein.
     * Es wird zunächst die gesammte Anzahl von Personen ausgeslsen und dannach die einzelnen Gewichte der reihe nach
     * abgespeichert. Da das Gewicht der Personen so ausgeglichen wie möglich sein soll wird dann das Gewicht berechnet.
     * Dafür benötigt man die Anzahl Personen und alle dazugehörigen Gewichte. Da musste ich ganz viel StackOverflow
     * und einen Kumpel zu rate ziehen weil ichs einfach nicht gebacken gekriegt habe!!
     */
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        int numCases = sc.nextInt();
        for(int i=0;i<numCases;i++){
            int numberOfPeople=sc.nextInt();
            int weights[]=new int[numberOfPeople];
            for(int j=0;j<numberOfPeople;j++){
                weights[j]=sc.nextInt();
            }
            computeWeight(numberOfPeople,weights);
            if(i!=numCases-1)
                System.out.println();
        }
    }

    public static void computeWeight(int numberOfPeople, int[] weights)
    {
        int sum=0;
        for(int i:weights)
            sum+=i;
        boolean possibleWeight[]=new boolean [25000];
        long people[]=new long[25000];
        //Dieses Array wird gebraucht um nach zu verfolgen wie viele Personen für das Gewicht k benötigt werden
        possibleWeight[0]=true; people[0]=1;

        for(int i=0;i<weights.length;i++){
            for(int j=possibleWeight.length-1;j>=0;j--){
                if(possibleWeight[j]){
                    possibleWeight[j+weights[i]]=true;
                    people[j+weights[i]]|=(people[j]<<1);
                }
            }
        }

        for(int i=sum/2;i>=0;i--){
            if(possibleWeight[i]){
                if(numberOfPeople%2==0 && ((people[i]&((long)1<<(numberOfPeople/2)))!=0)){
                    System.out.println(""+i+" "+(sum-i)); return;
                }else if(numberOfPeople%2==1 && (((people[i]&((long)1<<(numberOfPeople/2)))!=0)||
                        ((people[i]&((long)1<<(numberOfPeople/2+1)))!=0))){
                    System.out.println(""+i+" "+(sum-i)); return;
                }
            }
        }
    }
}
