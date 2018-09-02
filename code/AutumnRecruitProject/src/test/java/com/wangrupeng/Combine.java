package com.wangrupeng;

//求排列数组合数
public class Combine
{
    //求排列数
    public static int A(int up,int bellow)
    {
        int result=1;
        for(int i=up;i>0;i--)
        {
            result*=bellow;
            bellow--;
        }
        return result;
    }
    //求组合数，这个也不需要了。定义式，不使用互补率
    public static int C2(int up,int below)
    {
//			int denominator=factorial(up);//分母up的阶乘
        //分母
        int denominator=A(up,up);//A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
        //分子
        int numerator=A(up,below);//分子的排列数
        return numerator/denominator;

    }
    public static int C(int up,int below)//应用组合数的互补率简化计算量
    {
        int helf=below/2;
        if(up>helf)
        {
            System.out.print(up+"---->");
            up=below-up;
            System.out.print(up+"\n");

        }
        int denominator=A(up,up);//A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
        //分子
        int numerator=A(up,below);//分子的排列数
        return numerator/denominator;

    }
    public static void main(String[] args)
    {
        for(int i=1;i<=6;i++)
        {
            System.out.println("A("+i+",6)="+A(i,6));
        }
        for(int i=1;i<=6;i++)
        {
            System.out.println("C("+i+",6)="+C(i,6));
        }
//			int m=15/2;//int型会向前取整
//			System.out.println("m="+m);//m=7
//			for(int i=10;i<=20;i++)
//			{
//				m=i/2;
//				System.out.println("i="+i+",m="+m);
//			}
        System.out.println("C(5,6)="+C(5,6));//6
    }
}
