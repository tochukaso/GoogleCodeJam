package past;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PastGoogle2013_1A_a {
    
    private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\A-large-practice.in";

//  private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\A-small-practice.in";

//    private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\googleCodeJam\\past1a_test.txt";
    
    private static final String OUTPUT_PATH = "C:\\Users\\Yasu\\Downloads\\googleCodeJam\\past1a_test_out.txt";

    int N;

    List<String> qList;
    List<String> answerList = new ArrayList<String>();
    
    public static void main(String[] args) {
        PastGoogle2013_1A_a app = new PastGoogle2013_1A_a();
        
        app.solve();
    }
    /***
     * 最大公約数を求める関数
     * @param n1
     * @param n2
     * @return
     */
    private static long gcd(long n1, long n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }

    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(INPUT_PATH))));
            N = readInt();
            
            for (int i = 0; i < N; i++) {

                long[] line = readLongArray();
                long sum = 0;
                
                BigInteger limit = BigInteger.valueOf(line[1]);
                BigInteger div = BigInteger.valueOf(2);
                sum = 0;

                long l = line[0];
                
                long t = (l + 1)*(l + 1) - (l*l);
                
                long max = line[1];
                
                long min = 0;
                long n = 0;
                while(min + 1 < max) {
                    n = (max + min) / 2;
                    BigInteger black = BigInteger.valueOf(t + (t + 4 * (n - 1))).multiply(BigInteger.valueOf(n)).divide(div);

                    if(black.compareTo(limit) > 0) {
                        max = n;
                    } else {
                        min = n;
                    }

                }
                
                answerList.add(String.valueOf(min));
            
            }
            writeFile(OUTPUT_PATH, answerList);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception igunore) {}
        }
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

    BufferedReader br = null;

    private int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private int[] readIntArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        int[] out = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            out[i] = Integer.parseInt(s[i]);
        }
        return out;
    }

    private long[] readLongArray() throws IOException {
        String[] s = readStrArray();
        int cnt = s.length;
        long[] out = new long[cnt];
        for (int i = 0; i < cnt; i++) {
            out[i] = Long.parseLong(s[i]);
        }
        return out;
    }

    
    private String[] readStrArray() throws IOException {
        String[] s = br.readLine().split(" ");
        return s;
    }


}

