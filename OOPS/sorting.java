package OOPS;

import java.util.*;
class sorting{
  public static void main(String args[]){

    int ary[]={2,3,1,2,4,3};
  //   Selection sort
             for(int i=0;i<ary.length-1;i++){
                 int key=i;
                 for(int j=i+1;j<ary.length;j++){
                     if(ary[j]<ary[key]){key=j;
                     int temp=ary[j];
                     ary[j]=ary[i];
                     ary[i]=temp;
                     System.out.println(Arrays.toString(ary));
                 }}
         }
      
    


      //  insertion sort
        for(int i=1;i<ary.length;i++){
            int j=i-1;
            int key=ary[i];
            while(j>=0 && key<ary[j]){
                ary[j+1]=ary[j];
                j--;
            }
            ary[j+1]=key;
            System.out.println(Arrays.toString(ary));
        }

  }
}

