package Google1A;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;

public class Google1A_b {

//  private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\A-large.in";

//  private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\A-small-attempt0.in";

    private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\googleCodeJam\\q1_test.txt";
    
    private static final String OUTPUT_PATH = "C:\\Users\\Yasu\\Downloads\\googleCodeJam\\q1_test_out.txt";

    BufferedReader bufferedReader;

    int N;
    
    List<String> qList;

    List<String> answerList = new ArrayList<String>();
    
    static final String IMPOSSIPLE = "NOT POSSIBLE";

    int INF = 10000;
    
    public static void main(String[] args) {
        Google1A_b app = new Google1A_b();
        app.solve();
    }

    public void solve() { 
        File file = new File(INPUT_PATH);
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            N = Integer.parseInt(bufferedReader.readLine());
            
            for (int i = 0; i < N; i++) {
                int cnt = Integer.parseInt(bufferedReader.readLine());
                
                int[] array = new int[cnt];
                for (int j = 0; j < cnt; j++) {
//                    array[]
                }

//                uf.
                
                String[] line = bufferedReader.readLine().split(" ");
                
                

//                answerList.add(min == INF ? IMPOSSIPLE : String.valueOf(min));
            }
            
            writeFile(OUTPUT_PATH, answerList);
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception igunore) {
                igunore.printStackTrace();
            }
        }

    }

    BitSet[] addBit(BitSet adder, BitSet[] addest) {
        BitSet[] res = new BitSet[addest.length];
        
        for (int i = 0; i < addest.length; i++) {
            res[i] = addest[i].get(0, addest[i].length());
            res[i].xor(adder);
        }
        
        return res;
        
    }
    
    boolean check(BitSet[] comp1, BitSet[] comp2) {
        
        boolean[] usedArray = new boolean[comp1.length];
        for (BitSet c1 : comp1) {
            boolean isUpdate = false;
            for (int i = 0; i < comp2.length; i++) {
                if (usedArray[i]) continue;
                BitSet c2 = comp2[i];
                
                if (c1.equals(c2)) {
                    usedArray[i] = true;
                    isUpdate = true;
                    break;
                }
                
            }
            if (!isUpdate) {
                return false;
            }
        }
        
        return true;
        
    }
    
    
    private static final String FORMAT = "Case #{0}: {1}";

    public static void writeFile(String filePath, List<String> solve) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File (filePath))));

            for (int i = 0; i < solve.size(); i++) {
                
                Object[] ans = new Object[2];
                
                ans[0] = String.valueOf(i + 1);
                ans[1] = solve.get(i);
                String write = MessageFormat.format(FORMAT, ans);
                
                bufferedWriter.write(write);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (Exception igunore) {
                igunore.printStackTrace();
            }
        }
    }

    private int[] readIntArray() throws IOException {
        String[] s = bufferedReader.readLine().split(" ");

        int cnt = s.length;
        int[] out = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            out[i] = Integer.parseInt(s[i]);
        }

        return out;
    }

    private String[] readStrArray() throws IOException {
        String[] s = bufferedReader.readLine().split(" ");
        return s;
    }

    
    private BitSet[] readBitArray(int length) throws IOException {
        String[] arr = readStrArray();
        
        BitSet[] res = new BitSet[arr.length];

        for (int i = 0; i < res.length; i++) {
            res[i] = convBitSet(arr[i]);
        }
        
        return res;
    }

    private BitSet convBitSet(String s) {
        
        BitSet b = new BitSet();
        
        char[] cA = s.toCharArray();
        for (int i = 0; i < cA.length; i++) {
            b.set(i, cA[i] == '1');
        }

        return b;
    }
    
}

/**
 * プログラミングコンテストチャレンジブック第2版
 * unionFind
 * @author Yasu
 *
 */
class UnionFind {
    private int[] id;     // id[i] = parent of i
    private byte[] rank;  // rank[i] = rank of subtree rooted at i (cannot be more than 31)
    private int count;    // number of components

    /**
     * Initializes an empty union-find data structure with <tt>N</tt>
     * isolated components <tt>0</tt> through <tt>N-1</tt>
     * @throws java.lang.IllegalArgumentException if <tt>N &lt; 0</tt>
     * @param N the number of sites
     */
    public UnionFind(int N) {
        if (N < 0) throw new IllegalArgumentException();
        count = N;
        id = new int[N];
        rank = new byte[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Returns the component identifier for the component containing site <tt>p</tt>.
     * @param p the integer representing one object
     * @return the component identifier for the component containing site <tt>p</tt>
     * @throws java.lang.IndexOutOfBoundsException unless <tt>0 &le; p &lt; N</tt>
     */
    public int find(int p) {
        if (p < 0 || p >= id.length) throw new IndexOutOfBoundsException();
        while (p != id[p]) {
            id[p] = id[id[p]];    // path compression by halving
            p = id[p];
        }
        return p;
    }

    /**
     * Returns the number of components.
     * @return the number of components (between <tt>1</tt> and <tt>N</tt>)
     */
    public int count() {
        return count;
    }
  
    /**
     * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return true if the two sites <tt>p</tt> and <tt>q</tt> are in the same component; false otherwise
     * @throws java.lang.IndexOutOfBoundsException unless
     *      both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

  
    /**
     * Merges the component containing site <tt>p</tt> with the 
     * the component containing site <tt>q</tt>.
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws java.lang.IndexOutOfBoundsException unless
     *      both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
     */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[i] < rank[j]) id[i] = j;
        else if (rank[i] > rank[j]) id[j] = i;
        else {
            id[j] = i;
            rank[i]++;
        }
        count--;
    }


    /**
     * Reads in a an integer <tt>N</tt> and a sequence of pairs of integers
     * (between <tt>0</tt> and <tt>N-1</tt>) from standard input, where each integer
     * in the pair represents some site;
     * if the sites are in different components, merge the two components
     * and print the pair to standard output.
     */
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(100);
        
        for (int i = 40; i < 60; i++) {
            uf.connected(i, i + 20);
//            uf.union(i, i + 20);
        }
        
        for (int i = 0; i < uf.count(); i++) {
            System.out.println(uf.find(i));
        }
    }
}


