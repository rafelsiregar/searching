import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int BinarySearch(int[] a, int key){
        int low = 0;
        int high = a.length-1;
        while(low<=high){
            int mid = low+(high-low)/2;
            //Kalau langsung ketemu
            if(a[mid]==key)
                return mid;
            //Kalau ada di sebelahnya
            else if(a[mid]<key && key<a[mid-1])
                return mid;
            else if(a[mid]>key && key>=a[mid+1])
                return mid+1;
            //Partisi sebelah kiri
            else if(a[mid]<key)
                high = mid-1;
            //Partisi sebelah kanan
            else if(a[mid]>key)
                low = mid+1;
        }
        //Kalau tidak ketemu
        return -1;
    }
    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        //Daftar Ranking
        int[] anothers_rank = new int[scores.length];
        int[] alices_rank = new int[alice.length];
        anothers_rank[0] = 1;
        //Ranking orang lain
        for(int i=1;i<scores.length;i++){
            if(scores[i]==scores[i-1])
                anothers_rank[i] = anothers_rank[i-1];
            else
                anothers_rank[i] = anothers_rank[i-1]+1;
        }
        //Ranking Alice
        for(int i=0;i<alice.length;i++){
            //Kalau Alice paling tinggi
            if(alice[i]>scores[0]) 
                alices_rank[i] = 1;
            //Kalau Alice paling rendah
            else if(alice[i]<scores[scores.length-1]) 
                alices_rank[i] = anothers_rank[anothers_rank.length-1]+1;
            //Kalau ditengah-tengah
            else {
                int index = BinarySearch(scores, alice[i]);
                alices_rank[i] = anothers_rank[index];
            }
        }
        return alices_rank;   
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
