package Google1B;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;

import java.math.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Google1B_a {
    
    private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\A-large-practice_1b.in";

//  private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\A-small-practice.in";

//    private static final String INPUT_PATH = "C:\\Users\\Yasu\\Downloads\\googleCodeJam\\q1_test.txt";
    
    private static final String OUTPUT_PATH = "C:\\Users\\Yasu\\Downloads\\googleCodeJam\\q1_test_out.txt";

    int N;

    List<String> qList;
    List<String> answerList = new ArrayList<String>();
    
    public static void main(String[] args) {
        Google1B_a app = new Google1B_a();
        
        app.newSolve();
    }

    static final String WON = "Fegla Won";

    public void newSolve() { 
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(INPUT_PATH))));
            N = readInt();
            for (int testCase = 0; testCase < N; testCase++) {
                
                int stringCnt = readInt();
                
                String[] stringArray = new String[stringCnt];
                for (int i = 0; i < stringCnt; i++) {
                    stringArray[i] = br.readLine();
                }
                
                List<Character> list = new ArrayList<Character>();
                char[] charArray = stringArray[0].toCharArray();
                
                
                int[] indexArray = new int[stringCnt];
                
                char before = charArray[0];
                list.add(before);
                for (int i = 1; i < charArray.length; i++) {
                    if (before != charArray[i]) {
                        list.add( charArray[i] );
                        before = charArray[i];
                    }
                }

                int moveSum = 0;
                boolean isFegalo = false;
                for (char c: list) {

                    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                    int maxMatch = 0;
                    for (int i = 0; i < stringCnt; i++) {
                        int matchCnt = 0;
                        
                        for (int j = indexArray[i]; j < stringArray[i].length() ; j++) {
                            if (stringArray[i].charAt(j) != c) break;
                            indexArray[i]++;
                            matchCnt++;
                        }
                        if (matchCnt == 0) {
                            isFegalo = true;
                            break;
                        }
                        Integer cnt = map.get(matchCnt);
                        if (cnt == null) cnt = 0;
                        cnt++;
                        map.put(matchCnt, cnt);
                        maxMatch = Math.max(maxMatch, matchCnt);
                    }
                    
                    int minMoveSum = 100000;
                    for (int i = 1; i <= maxMatch; i++) {
                        int roopMoveSum = 0;
                        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                            if (i != entry.getKey()) {
                                roopMoveSum += ((int) abs(entry.getKey() - i) * entry.getValue());
                            }
                        }
                        minMoveSum = Math.min(minMoveSum, roopMoveSum);
                    }
                    moveSum += minMoveSum;
                }
                for (int i = 0; i < stringCnt; i++) {
                    System.out.println(indexArray[i] + " : " + stringArray[i].length());
                    if (indexArray[i] != stringArray[i].length()) {
                        isFegalo = true;
                        break;
                    }
                }
                
                
                System.out.println(moveSum);
                answerList.add(isFegalo ? WON : String.valueOf(moveSum));
                
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
    
    public void solve() { 
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(INPUT_PATH))));
            N = readInt();
            for (int testCase = 0; testCase < N; testCase++) {
                System.out.println(testCase);

                int sCnt = readInt();
                
                String[] sA = new String[sCnt];
                for (int i = 0; i < sCnt; i++) {
                    sA[i] = br.readLine();
                }
                int min = Integer.MIN_VALUE - 100000;

                Set<StringBuffer> kSet = new HashSet<StringBuffer>();

                String mS = "";

                String simpleStr = null;
                boolean isF = false;
                
                
                for (int i = 0; i < sCnt; i++) {
                    String s = sA[i];
                    char c = s.charAt(0);
                    char[] cA = s.toCharArray();
                    StringBuffer simple = new StringBuffer("");
                    StringBuffer adder = new StringBuffer("");
                    adder.append(c);
                    for (int j = 1; j < cA.length; j++) {
                        char next = cA[j];
                        if (c != next) {
                            simple.append(c);
                            c = next;
                        }
                    }
                    simple.append(c);
                    //                    kSet.addAll(list);
                   
                    if (simpleStr == null) {
                        simpleStr = simple.toString();
                    } else {
                        if (!simpleStr.equals(simple.toString())) {
                            isF = true;
                            break;
                        }
                    }
                }
                
                int first = 0;
                int second = 0;
                
                String s1 = sA[0];
                String s2 = sA[1];
                int total = 0;
                
                char b = s1.charAt(first);
                while(true) {
                    char c = s1.charAt(first);
                    if (c != s2.charAt(second)) {
                        total ++;
                        if (b == c) {
                            first ++;
                        } else {
                            second ++;
                        }
                    } else {
                        first ++;
                        second ++;
                        b = c;
                    }
                    
                    if (first + 1> s1.length()) {
                        total += abs(s2.length() - second);
                        break;
                    } else if (second + 1> s2.length()) {
                        total += abs(s1.length() - first);
                        break;
                    }
                }
                
/**                
                if (!isF) {
                    for (int i = 0; i < sCnt; i++) {
                        String s = sA[i];
                        int sum = 0;
                        for (int j = 0; j < sCnt; j++) {
                            String x = sA[j];
                            int maxLength = max(s.length(), x.length());
                            
                            int[][] dp = new int[s.length() + 1][x.length() + 1];
                            
                            for (int k = 0; k < s.length(); k++) {
                                for (int l = 0; l < x.length(); l++) {
                                    
                                    if (s.charAt(k) == x.charAt(l)) {
                                        dp[k + 1][l + 1] = dp[k][l] + 1;
                                    } else {
                                        dp[k + 1][l + 1] = max(dp[k + 1][l], dp[k][l + 1]);
                                    }
                                }
                            }
                            
                            int lenSa = abs(s.length() - x.length());
                            
                            int sa =  (maxLength - dp[s.length()][x.length()]);
                            
//                            sum += ((sa - lenSa) * 2 + lenSa);
                            sum += sa;
                        }
                        min = min(min, sum);
                    }
                }
**/                
                
                answerList.add(isF ? WON : String.valueOf(total));
            
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

    private long readLong() throws IOException {
        return Long.parseLong(br.readLine());
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

    private Integer[] convIntArray(int[] arg) {
        int len = arg.length;
        Integer[] res = new Integer[len];
        for (int i = 0; i < len; i++) {
            res[i] = arg[i];
        }
        return res;
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

    void generate(int[] p, int L, int R) {
        if (L == R) { // all numbers are set
          // do something with permutation in array p[]
            System.out.println(deepToString(convIntArray(p)));
        } else { // numbers at positions [0, L-1] are set, try to set L-th position
          for (int i = L; i <= R; i++) {
            int tmp = p[L]; p[L] = p[i]; p[i] = tmp;
            generate(p, L+1, R);
            tmp = p[L]; p[L] = p[i]; p[i] = tmp;
          }
        }
     }

    /***
     * Å‘åŒö–ñ”‚ð‹‚ß‚éŠÖ”
     * @param n1
     * @param n2
     * @return
     */
    private static long gcd(long n1, long n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }
    private static int gcd(int n1, int n2) {
        return (n2 == 0)?n1:gcd(n2, n1%n2);
    }

}

