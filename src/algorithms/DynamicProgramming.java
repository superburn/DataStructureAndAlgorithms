package algorithms;


//动态规划方法通常用来求解最优化问题
//动态规划算法
//1.刻画一个最优解的结构特征
//2.递归地定义最优解的值
//3.计算最优解的值，通常采用自底向上的方法
//4.利用计算出的信息构造一个最优解
public class DynamicProgramming {
/////////////////////////////////////////////钢条切割///////////////////////////////////////////////
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

//////////////////////////////////////////////矩阵链乘法/////////////////////////////////////////////////////
//矩阵链乘法问题可描述如下：给定n个矩阵的链<A1,A2,...An>，矩阵Ai的规模为Pi-1*Pi(1<=i<=n)，求完全括号化方案，使得计算乘积
//A1A2...An所需标量乘法次数最少。注意，求解矩阵乘法问题并不是要真正进行矩阵相乘运算，我们的目标只是确定代价最低的计算顺序。
//如果矩阵链为<A1,A2,A3,A4>，则共有5种完全括号化的矩阵乘积链：
//(A1(A2(A3A4)))
//(A1((A2A3)A4))
//((A1A2)(A3A4))
//((A1(A2A3))A4)
//(((A1A2)A3)A4)
//
//两个矩阵A和B只有相容，即A的列数等于B的行数时，才能相乘。如果A是p*q的矩阵，B是q*r的矩阵，那么乘积C是p*r的矩阵
//我们以矩阵<A1,A2,A3>相乘为例，来说明不同的加括号方式会导致不同的计算代价。假设三个矩阵的规模分别为10*100，100*5，5*50。
//如果按((A2A2)A3)的顺序计算，为计算A1A2(规模10*5)，需要做10*100*5=5000次标量乘法，
//再与A3相乘又需要做10*5*50=2500次标量乘法，共需7500次标量乘法
//如果按(A2(A2A3))的顺序计算，为计算A2A3(规模100*50)，需要做100*5*50=25000次标量乘法，
//A1再与之相乘又需10*100*50=50000次标量乘法，共需75000次标量乘法。因此，第一种顺序计算矩阵链乘积要比第二种顺序快10倍。

//为了构造一个矩阵链乘法问题实例的最优解，我们可以将问题划分为两个子问题(AiAi+1...Ak和Ak+1Ak+2...Aj)的最优括号化
//令m[i,j]表示计算矩阵Ai..j所需标量乘法次数的最小值，那么，原问题的最优解——计算A1..n所需的最低代价就是m[1,n]。
//m[i,i]=0
//m[i,j]就等于计算Ai..k和Ak+1..j的代价加上两者相乘的代价的最小值。由于矩阵Ai的大小为Pi-1*Pi，易知Ai..k与Ak+1..j相乘的代价
//为Pi-1PkPj次标量乘法运算。因此，我们得到
//m[i,j]=m[i,k]+m[k+1,j]+Pi-1PkPj
//AiAi+1...Aj最小代价括号化方案的递归求解公式变为：

//        0                             如果i=j
//m[i,j]{
//        min{m[i,k]+m[k+1,j]+Pi-1PkPj} 如果i<j,i<=k<=j
//
//我们用s[i,j]保存AiAi+1...A最优括号化方案的分割点位置k。
//
//下面给出的过程MATRIX-CHAIN-ORDER实现了自底向上表格法。此过程假定矩阵Ai的规模为Pi-1*Pi(i=1,2,...,n)。它的输入是一个
//序列P=<P0,P1,...Pn>，其长度P.length=n+1。过程用一个辅助表m[1..n,1..n]来保存代价m[i,j]，用另一个辅助表s[1..n-1,2..n]
//记录最优值m[i,j]对应的分割点k。我们就可以利用表s构造最优解。
//
//MATRIX-CHAIN-ORDER(P)
//1 n=P.length-1
//2 let m[1..n,1..n] and s[1..n-1,2..n] be new tables
//3 for i=1 to n
//4    m[i,i]=0
//5 for l=2 to n
//6    for i=1 to n-l+1
//7       j=i+l-1
//8       m[i,j]=positive infinity
//9       for k=i to j-1
//10         q=m[i,k]+m[k+1,j]+Pi-1PkPj
//11         if q<m[i,j]
//12            m[i,j]=q
//13            s[i,j]=k
//14 return m and s

//在5～13行for循环的第一个循化步中，对所有i=1,2,...,n-1计算m[i,i+1]（长度l=2的链的最小计算代价）
//在第二个循化步中，对所有i=1,2,...,n-2计算m[i,i+2]（长度l=3的链的最小计算代价）
//
//构造最优解
//PRINT-OPTIMAL-PARENS(s,i,j)
//1 if i==j
//2    print "A"i
//3 else print "("
//4    PRINT-OPTIMAL-PARENS(s,i,s[i,j])
//5    PRINT-OPTIMAL-PARENS(s,s[i,j]+1,j)
//6    print ")"

//例如PRINT-OPTIMAL-PARENS(s,1,6)输出((A1(A2A3))((A4A5)A6)

//MEMOIZED-MATRIX-CHAIN(P)
//1 n=P.length-1
//2 let m[1..n,1,,n] be a new table
//3 for i=1 to n
//4    for j=i to n
//5       m[i,j] = positive infinity
//6 return LOOKUP-CHAIN(m,P,1,n)
//
//LOOKUP-CHAIN(m,P,i,j)
//1 if m[i,j] < positive infinity
//2 if i==j
//3    m[i,j] = 0
//4 else for k=i to j-1
//5         q=LOOKUP-CHAIN(m,P,i,k)+LOOKUP-CHAIN(m,p,k,j-1)+Pi-1PkPj
//6         if q<m[i,j]
//7            m[i,j]=q
//8 return m[i,j]

///////////////////////////////////////最长公共子序列 LCS/////////////////////////////////////////////
//最长公共子序列问题：给定两个序列X=<X1,X2,...Xm>和Y=<Y1,Y2,...Yn>,求X和Y长度最长的公共子序列。
//给定一个序列X=<X1,X2,...Xm>，另一个序列Z=<Z1,Z2,...Zk>满足如下条件时称为X的子序列，即存在一个严格递增的X的下标序列
//<i1,i2,..,ik>,对所有j=1,2,...,k，满足xi=zj。例如，Z=<B,C,D,B>是X=<A,B,C,B,D,A,B>的子序列，对应的下标序列为
//<2,3,5,7>。给定两个序列X和Y，如果Z既是X的子序列，也是Y的子序列，我们称它是X和Y的公共子序列。
//前缀：给定一个序列X=<X1,X2,...Xm>，对i=0,1,...m，定义X的第i前缀为Xi=<1,X2,...Xi>。例如，若X=<A,B,C,B,D,A,B>，
//则X4=<A,B,C,B>，X0为空串
//定理（LCS的最优子结构）令X=<X1,X2,...Xm>和Y=<Y1,Y2,...Yn>为两个序列，Z=<Z1,Z2,...Zk>为X和Y的任意LCS。
//1.如果Xm=Yn，则Zk=Xm=Yn且Zk-1是Xm-1和Yn-1的一个LCS
//2.如果Xm！=Yn，那么则Zk！=Xm意味着Z是Xm-1和Yn的一个LCS
//3.如果Xm！=Yn，那么则Zk！=Yn意味着Z是Xm和Yn-1的一个LCS
//
//如果Xm=Yn，我们应该求解Xm-1和Yn-1的一个LCS。将Xm=Yn追加到这个LCS的末尾，就得到X和Y的一个LCS。如果Xm！=Yn，我们必须求解两个
//子问题：求Xm-1和Y的一个LCS与X和Yn-1的一个LCS。两个LCS较长者即为X和Y的一个LCS。
//我们定义c[i,j]表示Xi和Yj的LCS的长度。如果i=0或j=0，即一个序列长度为0，那么LCS的长度为0.
//
//        0                       若i=0或j=0
//c[i,j]={c[i-1,j-1]+1            若i,j>0且Xi=Yj
//        max(c[i,j-1],c[i-1,j]   若i,j>0且Xi！=Yj
//
//动态规划自底向上
//LCS-LENGTH(X,Y)
//1  m=X.length
//2  n=Y.length
//3  let c[0..m,0..n] be a new table
//4  for i=1 to m
//5     c[i,0]=0
//6  for j=1 to n
//7     c[0,j]=0
//8     for i=1 to m
//9        i=1 to n
//10    if Xi=Yj
//11       c[i,j]=c[i-1,j-1]+1
//12    elseif c[i-1,j] >= c[i,j-1]
//13       c[i,j]=c[i-1,j]
//14    else c[i,j]=c[i,j-1]
//15  return c
//
//过程的运行时间为O(mn)
}
