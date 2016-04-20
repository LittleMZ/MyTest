package com.myjavatest.MainTest;

import com.myjavatest.PokerTest.Poker;

/**
 * Created by M on 16/4/19.
 */
public class MainTestForPoker {
    public static void main(String[] args) {

        Poker poker=new Poker(Poker.PlayType.douDiZhu2);
        poker.givePoker();
        poker.lookPoker();
    }
}
