import java.util.*;
public class Desert{
  	public static int dir[][]={{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String []args){
    	Scanner sc=new Scanner(System.in);
      	int n=sc.nextInt();
      	sc.nextLine();
      	char [][]desert=new char [n][n];
      	for(int i=0;i<n;i++){
        	String []temp=sc.nextLine().split(" ");
          	for(int j=0;j<n;j++){
            	desert[i][j]=temp[j].charAt(0);
            }
        }
      	System.out.print(solve(n,desert));
    }
  public static int solve(int n,char [][]ar){
  	int st[]=find(ar,'S');
    int ed[]=find(ar,'E');
    if(st[0]==-1 || ed[0]==-1)return -1;
    int dp[][]=new int [n][n];
    for(int i[]:dp)Arrays.fill(i,Integer.MAX_VALUE);
    dp[st[0]][st[1]]=0;
    PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[2]-b[2]);
    pq.add(new int[]{st[0],st[1],0});
    
    while(!pq.isEmpty()){
    	int curr[]=pq.poll();
      	int x=curr[0],y=curr[1], water=curr[2];
      	
      	if(x==ed[0] && y==ed[1])return water;
      	if(water > dp[x][y])continue;
      
      	for(int d[]:dir){
        	int dx=x+d[0], dy=y+d[1];
          	if(dx>=0 && dx<n && dy>=0 && dy<n && ar[dx][dy]!='M'){
            	int newWater=water;
              	if(ar[x][y] !='T' || ar[dx][dy]!='T')newWater+=1;
              
              if(newWater<dp[dx][dy]){
                dp[dx][dy]=newWater;
                pq.add(new int[]{dx,dy,newWater});
              }
            }
        }
    }
    return -1;
  }
  public static int[] find(char [][]ar,char t){
  	for(int i=0;i<ar.length;i++){
    	for(int j=0;j<ar.length;j++){
        	if(ar[i][j]==t)return new int[] {i,j};
        }
    }
    return new int[] {-1,-1};
  }
}