package ThugOfWar;

import java.util.*;
class Main
{
    public static void main(String arg[])
    {
        Scanner sc = new Scanner (System.in);
        int numCases = sc.nextInt();
        for(int i=0;i<numCases;i++){
            int numPeople=sc.nextInt();
            int weights[]=new int[numPeople];
            for(int j=0;j<numPeople;j++){
                weights[j]=sc.nextInt();
            }
            computeWeight(numPeople,weights);
            if(i!=numCases-1)
                System.out.println();
        }
    }

    public static void computeWeight(int numPeople, int[] weights)
    {
        int sum=0;
        for(int i:weights)
            sum+=i;
        boolean possibleWeight[]=new boolean [45001];
        long people[]=new long[45001];
        //this array is used to keep track of how many people are needed to form weight k
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
                if(numPeople%2==0 && ((people[i]&((long)1<<(numPeople/2)))!=0)){
                    System.out.println(""+i+" "+(sum-i)); return;
                }else if(numPeople%2==1 && (((people[i]&((long)1<<(numPeople/2)))!=0)||
                        ((people[i]&((long)1<<(numPeople/2+1)))!=0))){
                    System.out.println(""+i+" "+(sum-i)); return;
                }
            }
        }
    }
}
