package com.myjavatest.MainTest;

import com.myjavatest.PokerTest.Poker;

/**
 * Created by M on 16/4/19.
 */
public class MainTestForPoker {
    public static void main(String[] args) {
        Poker poker=new Poker(Poker.PlayType.douDiZhu1);

        poker.givePoker();
        poker.lookPoker();

//        TreeSet<String> treeSet = new TreeSet<String>();
//        String s1="15";
//        String s2="13";
//        String s3="12";
//        String s4="14";
//        String s5="11";
//
//        treeSet.add(s1);
//        treeSet.add(s2);
//        treeSet.add(s3);
//        treeSet.add(s4);
//        treeSet.add(s5);
//
//        for(String s : treeSet){
//            System.out.print(s+" ");
//        }
    }
}
