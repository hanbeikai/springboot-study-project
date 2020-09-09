package com.beikai.springboottestdemo.designPattern.statePattern.before;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2020/9/8
 * Time: 9:37 上午
 * Description: 使用状态模式之前的方式 通过if 来判断条件
 */
public class GameMachine {

    // 售罄
    final static int SOLD_OUT = 0;
    // 没有 25 分钱
    final static int NO_QUARTER = 1;
    // 有 25 分钱
    final static int HAS_QUARTER = 2;
    // 售出糖果
    final static int SOLD = 3;

    // 初始状态是 售罄
    int state = SOLD_OUT;
    // 糖果的数量
    int count = 0;

    public GameMachine(int count) {
        // 初始化 糖果数量
        this.count = count;
        if (this.count > 0) {
            // 如果糖果数量 大于 0, 则设置糖果机状态为 没有 金额不足
            state = NO_QUARTER;
        }
    }

    // 投入硬币
    public void insertQuarter() {
        // 判断 当前的状态
        if (state == HAS_QUARTER) {
            System.out.println("您已经投过币了 ... ");
        } else if (state == NO_QUARTER) {
            state = HAS_QUARTER;
            System.out.println("收到一个硬币 ... ");
        } else if (state == SOLD_OUT) {
            System.out.println("糖果已经售完了 ... ");
        } else if (state == SOLD) {
            System.out.println("您已经买过一个糖果了,请稍等 ... ");
        }
    }

    // 退回硬币
    public void ejectQuarter() {
        // 判断 当前的状态
        if (state == HAS_QUARTER) {
            System.out.println("正在退币 ... ");
            state = NO_QUARTER;
        } else if (state == NO_QUARTER) {
            System.out.println("您没有投币 ... ");
        } else if (state == SOLD_OUT) {
            System.out.println("您不可以退币,因为还没有投币 ... ");
        } else if (state == SOLD) {
            System.out.println("您已经转动曲轴了,不能再退币了 ... ");
        }
    }

    // 转动曲轴
    public void turnCrank() {
        // 判断 当前的状态
        if (state == HAS_QUARTER) {
            System.out.println("您转动了曲轴 ... ");
            state = SOLD;
            dispense();
        } else if (state == NO_QUARTER) {
            System.out.println("您转动曲轴了,但是没有投币 ... ");
        } else if (state == SOLD_OUT) {
            System.out.println("您已经转动曲轴了,但是机器中已经没有糖果了 ... ");
        } else if (state == SOLD) {
            System.out.println("就算您转动两次曲轴,也得不到两个糖果 ... ");
        }
    }

    // 发放糖果
    public void dispense() {
        // 判断 当前的状态
        if (state == HAS_QUARTER) {
            System.out.println("没有糖果发放了 ... ");
        } else if (state == NO_QUARTER) {
            System.out.println("首先,您需要付钱 ... ");
        } else if (state == SOLD_OUT) {
            System.out.println("没有糖果发放了 ... ");
        } else if (state == SOLD) {
            System.out.println("一个糖果正在发放 ... ");
            // 库存减一
            count -= 1;
            if (count == 0) {
                // 如果库存为0,设置为库存售罄
                state = SOLD_OUT;
            } else {
                // 如果还未售罄,重置当前机器状态为 没有硬币
                state = NO_QUARTER;
            }
        }
    }

    @Override
    public String toString() {
        return "GameMachine{" +
                "state=" + state +
                ", count=" + count +
                '}';
    }
}
