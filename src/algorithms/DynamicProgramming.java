package algorithms;


//动态规划方法通常用来求解最优化问题
//动态规划算法
//1.刻画一个最优解的结构特征
//2.递归地定义最优解的值
//3.计算最优解的值，通常采用自底向上的方法
//4.利用计算出的信息构造一个最优解
public class DynamicProgramming {
    //钢条切割
    //给定一段长度为n英寸的钢条和一个价格表Pi(i=1,2,...,n)，求切割钢条方案，使得销售收益Rn最大。
    //注意，如果长度为n英寸的钢条的价格Pn足够大，最优解可能就是完全不需要切割
    // 长度i   1 | 2 | 3 | 4 | 5 | 6 | 7 |
    //————————————————————————————————————
    // 价格Pi  1 | 5 | 8 | 9 | 10| 17| 17|

    //长度为n英寸的钢条共有2^n-1种不同的切割方案，因为在距离钢条左端i(i=1,2,...,n)英寸处，我们总是可以选择切割或不切割。
    //一般地，对于Rn（n>=1），Rn = max（Pn,R1+Rn-1,R2+Rn-2,...,Rn-1+R1)

    //递归求解法：我们将钢条从左边切割下长度为i的一段，只对右边剩下的长度为n-i的一段继续进行切割（递归求解），
    //对左边的一段则不再进行切割。Rn = max（Pi+Rn-i)

    //自顶向下递归实现
    //CUT-ROD(P,n)
    // 1 if n==0
    // 2    return 0
    // 3 q = negative infinity
    // 4 for i = 1 to n
    // 5    q = max(q,P[i]+CUT-ROD(P,n-i))
    // 6 return q

    //效率差，原因在于CUT-ROD反复地用相同的参数值对自身进行递归调用，即它反复求解相同的子问题，
    //考察了所有2^n-1种可能的切割方案，运行时间为n的指数函数。

    //动态规划方法
    //动态规划方法仔细安排求解顺序，对每个子问题只求解一次，并将结果保存下来。如果随后再次需要此子问题的解，
    //只需查找保存的结果，而不必重新计算。因此动态规划方法是付出额外的内存空间来节省计算时间，是典型的时空权衡的例子。

    //自顶向下 O(n^2)
    //MEMOIZED-CUT-ROD(P,n)
    //1 let r[0..n] be a array
    //2 for i = 0 to n
    //3    r[i] = negative infinity
    //4 return MEMOIZED-CUT-ROD-AUX(P,n,r)

    //MEMOIZED-CUT-ROD-AUX(P,n,r)
    //1 if r[n] >= 0
    //2    return r[n]
    //3 if n == 0
    //4    q = 0
    //5 else q = negative infinity
    //6    for i = 1 to n
    //7       q = max(q,P[i]+MEMOIZED-CUT-ROD-AUX(P,n-i,r))
    //8 r[n] = q
    //9 return q

    //自底向上 O(n^2)
    //BOTTOM-UP-CUT-ROD(P,n)
    //1 let r[0..n] be a new array
    //2 r[0] = 0
    //3 for j = 1 to n
    //4    q = negative infinity
    //5    for i = 1 to j
    //6       q = max(q,p[i]+r[j-i])
    //7    r[j] = q
    //8 return r[n]

    //重构解 输出最优解
    //EXTENDED-BOTTOM-UP-CUT-ROD(P,n)
    //1 let r[0..n] and s[0..n] be new arrays
    //2 r[0] = 0
    //3 for j = 1 to n
    //4    q = negative infinity
    //5    for i = 1 to j
    //6        if q < p[i] + r[j-i]
    //7           q = p[i] + r[j-i]
    //8           s[j] = i
    //9           r[j] = q
    //10 return r and s

    //输出长度为n的钢条的完整的最优切割方案
    //PRINT-CUT-ROD-SOLUTION(P,n)
    //1 (r,s) = EXTENDED-BOTTOM-UP-CUT-ROD(P,n)
    //2 while n>0
    //3    print s[n]
    //4    n = n - s[n]
    //对于前文给出的钢条切割的实例，EXTENDED-BOTTOM-UP-CUT-ROD(P,7)，会输出最优方案R7切割出的两段钢条的长度1和6
    //
    //  i | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
    //—————————————————————————————————————
    //r[i]| 0 | 1 | 5 | 8 | 10| 13| 17| 18|
    //s[i]| 0 | 1 | 2 | 3 | 2 | 2 | 6 | 1 |
}
