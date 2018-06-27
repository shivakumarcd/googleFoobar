package GoogleChallenge1till2;

import java.util.ArrayList;

/**
 * Created by ShIvS on 6/10/2017.
 */
public class Challenge4 {
    public static void main(String[] args) {
        System.out.println("\n"+answer(new int[]{1,1,1,2,2,4,1}));

    }
    public static int answer(int[] l)
    {
      //  int count = 0;
        ArrayList<int[]> triplets = new ArrayList<int[]>();

       for(int i=0;i<l.length;i++)
       {
           int element = l[i];
           int repetationCount = 1;
           if(element!=-1)
           {
               for (int j = i + 1; j < l.length; j++) {
                   {
                       if(l[j]==element && repetationCount>=2)
                           l[j] = -1;
                       else if (l[j] == element)
                           repetationCount++;
                   }
               }
           }
       }

       ArrayList<Integer> intList = new ArrayList<Integer>();

     for(int i=0;i<l.length;i++) {
         if(l[i]!=-1) {
             intList.add(l[i]);
             System.out.print(l[i] + ",");
         }
     }
        l = null;//for safety
        if(intList.size()>=3)
            for(int i=0; i<intList.size()-2;i++)
            {
                    for(int j=i+1;j<intList.size()-1;j++)
                    {
                            if(intList.get(j) %intList.get(i)==0)
                                for(int k=j+1;k<intList.size();k++)
                                {
                                    if(intList.get(k)% intList.get(j)==0 ) {
                                        //   System.out.println("[" + l[i] + "," + l[j] + "," + l[k] + "]");
                                        int [] threes = {intList.get(i),intList.get(j),intList.get(k)};

                                        boolean present = false;
                                        for(int[] array :triplets)
                                        {
                                            if(array[0]==threes[0] && array[1]==threes[1] &&array[2]==threes[2])
                                                present = true;
                                        }
                                        if(!present)
                                            triplets.add(threes);
                                        // count++;
                                    }

                                }
                    }
            }
      /* System.out.println("\n"+l.length);
       if(l.length>=3)
       for(int i=0; i<l.length-2;i++)
       {
            if(l[i]>0)
                for(int j=i+1;j<l.length-1;j++)
                {
                    if(l[j]>0)
                        if(l[j]%l[i]==0)
                        for(int k=j+1;k<l.length;k++)
                        {
                            if(l[k]>=0 && l[k]%l[j]==0 ) {
                             //   System.out.println("[" + l[i] + "," + l[j] + "," + l[k] + "]");
                                int [] threes = {l[i],l[j],l[k]};

                                boolean present = false;
                                for(int[] array :triplets)
                                {
                                    if(array[0]==threes[0] && array[1]==threes[1] &&array[2]==threes[2])
                                        present = true;
                                }
                                if(!present)
                                    triplets.add(threes);
                               // count++;
                            }

                        }
                }
        }*/
        return triplets.size();
    }
    /*public static int[] removeTrippleRepeatations(int []s){
        int [] k = new int[s.length];
        k[0]=s[0];


        return k;
    }
    public boolean containsTwise(int element, int[] array)
    {
        int repeatationCount = 0;
        for(int i=0;i<array.length;i++)
        {
            if(array[i]==element)
                repeatationCount++;
        }
        if(repeatationCount>=2)
            return true;
        else
            return false;
    }*/
}
