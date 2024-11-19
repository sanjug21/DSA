package questions;

// import java.util.Scanner;


class array{
    public static void main(String []args){
        // int nums[]={2,3,1,2,4,3};
        // int target=7;
        //  int ary[]={5,4,1,3,2};



    //    //  Selection sort
    //          for(int i=0;i<ary.length-1;i++){
    //              int key=i;
    //              for(int j=i+1;j<ary.length;j++){
    //                  if(ary[j]<ary[key]){key=j;
                     
    //              }}
    //              int temp=ary[key];
    //                  ary[key]=ary[i];
    //                  ary[i]=temp;
    //                   System.out.println(Arrays.toString(ary));
    //      }
    //         System.out.println("\n \n \n");
    


    //    // insertion sort
    //     for(int i=1;i<ary.length;i++){
    //         int j=i-1;
    //         int key=ary[i];
    //         while(j>=0 && key<ary[j]){
    //             ary[j+1]=ary[j];
    //             j--;
    //         }
    //         ary[j+1]=key;
    //         System.out.println(Arrays.toString(ary));
    //     }




    // question
    // int ans=Integer.MAX_VALUE,min=0,n=nums.length,sum=0,total=0;
    //     for(int i=0;i<n;i++){
    //         int j=i;
    //         total+=nums[i];
    //         while(j<n){
    //             sum+=nums[j++];
                
    //             if(sum>=target){
    //                 min=j-i;
    //                 if(ans>min)ans=min;
    //                 System.out.println(ans);
    //                 break;
    //             }
    //         }      
    //         sum=0;
    //     }
    //     if(total<target)System.out.println(0);




    // question
    // int a[]={-4,-1,0,3,10};
    // for(int i=0;i<a.length;i++){
    //     a[i]=a[i]*a[i];

    // }
    // for(int i=1;i<a.length;i++){
    //         int j=i-1;
    //         int key=a[i];
    //         while(j>=0 && key<a[j]){
    //             a[j+1]=a[j];
    //             j--;
    //         }
    //         a[j+1]=key;
            
    //     }
    //     System.out.println(Arrays.toString(a));



        // question
        // int a[]={0,1,1,0,1,0,0};
        // int count=0;
        // for(int i:a)if(i==0)count++;
        // for(int i=0;i<a.length;i++){
        //     if(count>0){
        //         a[i]=0;
        //         count--;
        //     }
        //     else a[i]=1;
        // }
        // System.out.println(Arrays.toString(a));



        //question
        // int[][]a={{1,2,3},{4,5,6},{7,8,9}};
        // for(int i=0;i<a.length;i++){
        //     for(int j=i+1;j<a.length;j++){
        //         int temp=a[i][j];
        //         a[i][j]=a[j][i];
        //         a[j][i]=temp;
        //     }
        // }
       //          for(int i=0;i<a.length;i++){
        //             for(int j=0;j<=i;j++){
        //                 System.out.print(a[i][j]);
        
        // }
        // System.out.println(" ");}


        // question
        // int a[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        // int cmin=0,rmin=0;
        // int cmax=a[0].length-1,rmax=a.length-1;
        // while(cmin<=cmax && rmin<=rmax){
        //     for(int i=cmin;i<=cmax;i++){System.out.print(a[rmin][i]+" ");}
        //     rmin++;
        //     for(int i=rmin;i<=rmax;i++){System.out.print(a[i][cmax]+" ");}
        //     cmax--;
        //     if(rmin<=rmax){
        //     for(int i=cmax;i>=cmin;i--){System.out.print(a[rmax][i]+" ");}
        //     rmax--;}
        //     if(cmin<=cmax){
        //     for(int i=rmax;i>=rmin;i--){System.out.print(a[i][cmin]+" ");}
        //     cmin++;}

        // }
       // int arr[]={1,0,2,3,0,4,5,0};

        // int n=arr.length;
        // int k=n-1,i=0;
        // while(i<n-1){
        //     if(arr[i]==0){
                
        //         int j=i+1;
                
        //     while(j!=k){
        //         arr[k]=arr[k-1];
        //         k--;
        //          //  System.out.println(Arrays.toString(arr));
        //     }
        //     arr[i+1]=0;
        //     i+=2;
        //     System.out.println(Arrays.toString(arr));
        //     }
        //     else i++;
        // }



        // int a=9,b=9;
        // a=b++;
        // b=a++;
        // b=++b;
        // System.out.println(a+"\n"+b);
  
    }}