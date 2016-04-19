package com.myjavatest.PokerTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by M on 16/4/18.
 */
public class Poker {
    //用来翻译扑克牌map
    private static HashMap<String, String> pokerContent = new HashMap<String, String>();
    //牌的副数
    private static String numOfCoper;
    //牌
    private static ArrayList<String> poker = new ArrayList<String>();
    //玩家Map
    private static HashMap<Integer, Player> playerMap = new HashMap<Integer, Player>();
    //玩牌类型
    private static PlayType playType;

    public static String getNumOfCoper() {
        return numOfCoper;
    }

    public static HashMap<Integer, Player> getPlayerMap() {
        return playerMap;
    }

    public static ArrayList<String> getPoker() {
        return poker;
    }

    public static HashMap<String, String> getPokerContent() {
        return pokerContent;
    }

    public Poker(PlayType playType) {
        //绑定扑克牌
        bindPokerContent(playType);
        this.playType = playType;
    }

    /**
     * 按枚举类型,选择相应的方法初始化扑克牌,及装牌
     *
     * @param playtype 枚举,玩的扑克牌类型
     */
    private void bindPokerContent(PlayType playtype) {
        String sortOfPoker = playtype.getSortOfPoker();
        String numOfCoper = playtype.getNumOfCoper();
        //配牌
        if (sortOfPoker.equals("1")) {
            sortPokerByDouDiZhu();
        } else if (sortOfPoker.equals("2")) {

        } else {
            //报错
        }

        //装牌+洗牌
        getShufflePoker(numOfCoper);
    }

    /**
     * 按牌的副数,得到装好的扑克牌
     *
     * @param numOfCoper 牌的副数
     */
    private void getShufflePoker(String numOfCoper) {
        for (int x = 0; x < Integer.valueOf(numOfCoper); x++) {
            for (int y = 0; y < pokerContent.size(); y++) {
                poker.add(String.valueOf(y));
            }
        }
        Collections.shuffle(poker);
    }

    /**
     * 按斗地主顺序,排序初始化扑克牌
     */
    private void sortPokerByDouDiZhu() {
        String[] colors = {"梅花", "黑桃", "方片", "红桃"};
        String[] numPokers = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        int index = 0;
        for (String numPoker : numPokers) {
            for (String color : colors) {
                pokerContent.put(String.valueOf(index), color.concat(numPoker));
                index++;
            }
        }
        pokerContent.put(String.valueOf(index), "小王");
        index++;
        pokerContent.put(String.valueOf(index), "大王");
    }

    /**
     * 发牌方法,将牌发给玩家,并留底牌
     */
    public void givePoker() {
        if (playType.numOfPlayer == null || playType.numOfPlayer.equals(0)) {
            System.out.println("请先初始化扑克牌类型!");
            return;
        }

        for (int i = 0; i < Integer.valueOf(playType.numOfPlayer); i++) {
            Player player = new Player();
            //发牌
            for (int x = Integer.valueOf(playType.numOfDiPai); x < poker.size(); x++) {
                if (x % Integer.valueOf(playType.numOfPlayer) == i) {
                    player.myPoker.add(Integer.valueOf(poker.get(x)));
                }
            }
            //底牌
            if (player.diPai == null || player.diPai.size() == 0) {
                for (int y = 0; y < Integer.valueOf(playType.numOfDiPai); y++) {
                    player.diPai.add(Integer.valueOf(poker.get(y)));
                }
            }
            playerMap.put(i, player);
        }
    }

    //看牌方法
    public void lookPoker() {
        if (playerMap == null || playerMap.size() == 0) {
            System.out.println("您还没有完成发牌!!");
        }

        System.out.println("您玩的牌类型为," + playType.nameOfPlay + ",玩家数共," + playType.numOfPlayer + "位,各玩家牌情况如下:");

        for (int i = 0; i < Integer.valueOf(playType.numOfPlayer); i++) {
            System.out.print("玩家" + (i + 1) + "的牌为:{");
            Collections.sort(playerMap.get(i).myPoker);
            for (Integer poke : playerMap.get(i).myPoker) {
                System.out.print(pokerContent.get(String.valueOf(poke)) + " ");
            }
            System.out.println("}");
        }

        System.out.print("底牌为:{");
        Collections.sort(playerMap.get(0).diPai);
        for (Integer poke : playerMap.get(0).diPai) {
            System.out.print(pokerContent.get(String.valueOf(poke)) + " ");
        }
        System.out.println("}");
    }

    /**
     * 枚举:控制牌类型
     */
    public enum PlayType {
        //斗地主,1副牌
        douDiZhu1("斗地主1副牌", "1", "1", "3", "3"),
        //斗地主,2副牌
        douDiZhu2("斗地主2副牌", "1", "2", "4", "8"),
        //欢乐升级,1副牌
        huanLeShenJi1("欢乐升级1副牌", "2", "1", "4", "0"),
        //欢乐升级,2副牌
        huanLeShenJi2("欢乐升级2副牌", "2", "1", "4", "0");

        //牌的名字
        private String nameOfPlay;
        //牌的排序方式
        private String sortOfPoker;
        //牌的副数
        private String numOfCoper;
        //玩家个数
        private String numOfPlayer;
        //底牌张数
        private String numOfDiPai;

        PlayType(String nameOfPlay, String sortOfPoker, String numOfCoper, String numOfPlayer, String numOfDiPai) {
            this.nameOfPlay = nameOfPlay;
            this.sortOfPoker = sortOfPoker;
            this.numOfCoper = numOfCoper;
            this.numOfPlayer = numOfPlayer;
            this.numOfDiPai = numOfDiPai;
        }

        public String getSortOfPoker() {
            return this.sortOfPoker;
        }

        public String getNumOfCoper() {
            return this.numOfCoper;
        }

        public String getNumOfPlayer() {
            return this.numOfPlayer;
        }

        public String getNumOfDiPai() {
            return this.numOfDiPai;
        }

        public String getNameOfPlay() {
            return this.nameOfPlay;
        }
    }

}
