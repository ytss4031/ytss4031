import java.util.*;
/*
猜数游戏:
本题为广为流传的一经典逻辑推理题目，原题如下：
孙膑，庞涓都是鬼谷子的徒弟。一天鬼谷子出了这道题目：
他从2到99中选出两个不同的整数，把积告诉孙，把和告诉庞；
庞说：我虽然不能确定这两个数是什么，但是我肯定你也不知道这两个数是什么。
孙说：我本来的确不知道，但是听你这么一说，我现在能够确定这两个数字了。
庞说：既然你这么说，我现在也知道这两个数字是什么了。
请问这两个数字是什么？为什么？
例:
11:对应的有:
 2 9
 3 8
 4 7
 5 6
17:对应的有:
 2 15
 3 14
 4 13
 5 12
 6 11
 7 10
 8 9
庞涓由11判断对方无法知道两个数(因为11无法被拆分为两个素数),但是当把这个信息告诉对方后,对方便依此判断出来了,
说明 5 6不符合条件,只剩下另外三种情况,庞涓此时无法判断是哪三种,但是他却直接判断出来了,
说明只剩下一种情况,再来看17这种情况,当庞涓听完孙膑说的话后,做了排除之后,
发现只剩下了一种情况,(4,13),很显然庞涓能判断出来,故本题答案为(4,13)
下面的程序计算的是孙膑说完话后,庞涓依此作出判断,排除不符合条件之后的情况
 */
public class Test2 {
    /*
    判断是否为素数,是就返回true 否就返回false
     */
    static boolean isPrimeNumber(double a){
        for(int i = 3;i<=(int) Math.sqrt(a);i+=2){
            if(a%i == 0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        /**
         * 将和的所有的情况都放进去,(11,17.......)
         * 要求就是和不能被拆分为两个素数,
         * 和很显然不能为偶数,
         * 也不能写成2+奇素数的形式
         */
        List<Integer> lists = new ArrayList<>();
        for(int i = 5;i<=197;i+=2){
            if(!isPrimeNumber(i-2)) lists.add(i);
        }
        int o = 0;
        for(Integer k : lists){
            o++;
            System.out.print(k+" ");
            if(o%20==0)
                System.out.println();
        }
        /*
           根据上面的形式判断,和不可能为两个偶数相加,所以采用偶数和奇数相加
           根据和来作匹配,用来存放匹配后的键值,键为两数组成的字符串,value为两数乘积
         */
        Map<String,Integer> maps = new HashMap<>();
        Map<String,Integer> maps2 = new HashMap<>();
        for(int i = 2;i<=98;i+=2){
            for(int j = 3;j<=99;j+=2){
                for(Integer t : lists){
                    if((i+j)==t){
                        maps.put(i+","+j,i*j);
                    }
                }
            }
        }
        System.out.println();
        /**
         * 这个相当于孙膑说完话后,庞涓排除用的,
         *  就是把所有情况都列出来,
         * 把积相同的剔除出去,尽管如此还有完全排除,
         */
        for(String key : maps.keySet()){
            int count = 0;
            for(String key2 : maps.keySet()){
                if(maps.get(key).equals(maps.get(key2))){
                    count++;
                }
            }
            if(count==1){
                 maps2.put(key,maps.get(key));
            }
        }
        Map<String,Integer> maps3 = new HashMap<>();
        Map<String,Integer> maps5 =new HashMap<>();
        for(int i = 2;i<=98;i++){
            for(int j = i+1;j<=99;j++){
                for(String key : maps2.keySet())
                    if((i*j)==maps2.get(key)) {
                        maps3.put(i+","+j,i*j);
                        maps5.put(i+","+j,i+j);
                    }
            }
        }

        Map<String,Integer> maps4 = new HashMap<>();
        for(String key : maps3.keySet()){
            int st = 0;
            for(String key3 : maps3.keySet()){
                if(maps3.get(key).equals(maps3.get(key3))){
                    st++;
                }
            }
            if(st>1) maps4.put(key,maps5.get(key));
        }
        Map<String,Integer> maps6 = new HashMap<>();
        for(Integer s : lists){
            for(String key : maps4.keySet()){
                if(maps4.get(key).equals(s)) {
                    maps6.put(key,maps4.get(key));
                }
            }
        }
        List<Integer> list2 = new ArrayList<>();
        for(String key : maps6.keySet()){
            list2.add(maps6.get(key));
        }
        List<Integer> w = new ArrayList<>();
        w = list2;
        for(int i = 0; i<list2.size(); i++){
            for(int j=i+1;j<list2.size();j++){
                if(list2.get(i)==list2.get(j)) {
                    w.remove(j);
                }
            }
        }
        Collections.sort(w);
        for(Integer i : w){
            System.out.println(i+":");
            for(String key6 : maps6.keySet()){
                if(i==maps6.get(key6)){
                    System.out.println("key值: "+key6+"  "+"value值: "+maps6.get(key6));
                }
            }
        }
    }
}

