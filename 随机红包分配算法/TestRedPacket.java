package com.oppein.tableau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestRedPacket {

    /**
     * 特定区间[min,max]随机分配m个红包，总额不超过n
     * @param _redPacket
     * @return
     */
    public static double getRandomMoney(RedPacket _redPacket){
        if(_redPacket.remainSize == 1){
            _redPacket.remainSize --;
            return Math.round(_redPacket.remainMoney*100)/100;
        }
        double min = _redPacket.min;
        double max = _redPacket.max;
        double avg = _redPacket.remainMoney/_redPacket.remainSize;
        double avg1 = (min+max)/2;
        Random random = new Random();
        double money = random.nextDouble()*(max-min)+min;
        if (avg > avg1) {
            money = money<=avg1?avg:money;
        }
        if(avg < avg1){
            money = money>=avg1?avg1:money;
        }
        if (avg == avg1) {
            money = money<=min?min:money;
            money = money>=max?max:money;
        }
        money = Math.floor(money*100)/100;
        _redPacket.remainMoney -= money;
        _redPacket.remainSize --;
        return money;
    }

    public static void main(String[] args) throws InterruptedException {
        RedPacket redPacket = new RedPacket(60,10,0.3,7);
        List<Double> list = new ArrayList<Double>();
        while (redPacket.remainSize>0){
            double money = getRandomMoney(redPacket);
            list.add(money);
            System.out.println(money);
//            Thread.sleep(2000);
        }
        double toast = 0;
        for(Double m:list){
            toast += m.doubleValue();
        }
        System.out.println("总共："+toast);
    }
}

class RedPacket{
    public double remainMoney;
    public double remainSize;
    public double min;
    public double max;

    public RedPacket(double remainMoney, double remainSize, double min, double max) {
        this.remainMoney = remainMoney;
        this.remainSize = remainSize;
        this.min = min;
        this.max = max;
    }
}
