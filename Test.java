import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/*
猜数游戏:
在(1,100)中任选两个整数,将两数之和告知A,两数之积告知B,
A断言B不知两数具体何值,B由此得出两数,求解方案如下:显然由A的言论可知,
这两个数显然不能都为素数,不然A也不敢断言,(因为都为素数则B就可以推出来了,)
先根据规则将所有可能都列举出来,然后分为偶数和奇数两组,分别相加,将符合规则的两数筛选出来
然而他们的积的分解则有多种情况,重组后的和还须判断是否满足题目条件,所以将他们积相同的全部筛选掉,
然后放到一个新的map中,OK就这样
 */
public class Test {
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
        List<Integer> lists = new LinkedList<>();
        for(int i = 5;i<=197;i+=2){
            if(!isPrimeNumber(i-2)) lists.add(i);
        }
          int o = 0;
        for(Integer l : lists){
            o++;
            System.out.print(l+" ");
            if(o%20==0)
                System.out.println();
        }
        /*
        用来存放筛选后的键值,键为两数组成的字符串,value为两数乘积
         */
        Map<String,Integer> maps = new HashMap<>();
        Map<String,Integer> maps2 = new HashMap<>();
        for(int i = 2;i<=98;i+=2){
            for(int j = i+1;j<=99;j+=2){
                for(Integer t : lists){
                    if((i+j)==t){
                        maps.put(i+","+j,i*j);
                    }
                }
            }
        }
        System.out.println();
        int m = 0;
        for(String key : maps.keySet()){
            int count = 0;
            for(String key2 : maps.keySet()){
                if(maps.get(key)==maps.get(key2)){
                    count++;
                }
                }
            if(count==1){
                m++;
                System.out.println(maps.get(key)+":");
                maps2.put(key,maps.get(key));
                System.out.println("key值: "+key+"  "+"value值: "+maps.get(key));
            }
            }
            System.out.println("一共找到"+m+"组");
            for(String key : maps2.keySet()){
                System.out.println(key+" : "+maps2.get(key));
            }
        }
    }

