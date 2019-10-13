package com.github.grinea.capitalismisland.model;

import com.github.grinea.capitalismisland.view.StatsObs;

import java.util.LinkedList;
import java.util.List;

public class GameData {

    private static GameData instance = null;
    private Settings settings;
    private GameMap map;
    private int money;
    private int gameTime;
    private int nRes;
    private int nCom;
    privaye int income;
    private List<StatsObs> obs;

    private GameData() {
        settings = new Settings();
        map = new GameMap(Math.max(settings.getMapWidth(), 3), Math.max(settings.getMapHeight(), 3));
        money = settings.getInitialMoney();
        gameTime = 0;
        nRes = 0;
        nCom = 0;
        obs = new LinkedList<>();
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }

        return instance;
    }

    public void setSettings(Settings settings)
    {
        this.settings = settings;
    }

    public int getIncome()
    {
        return income;
    }

    public void setIncome(int income)
    {
        this.income = income;
    }

    public void resetData()
    {
        map = new GameMap(Math.max(settings.getMapWidth(),3), Math.max(settings.getMapHeight(),3));
        money = settings.getInitialMoney();
        gameTime = 0;
        nRes = 0;
        nCom = 0;
        income = 0;

        //todo dumpster everything
    }

    public void setObs(StatsObs observer) {
        obs.add(observer);
    }

    public GameMap getMap() {
        return map;
    }

    public int getMoney() {
        return money;
    }

    public int getGameTime() {
        return gameTime;
    }

    public Settings getSettings() {
        return settings;
    }

    public void build(Structure structure) {
        switch (structure.getType()) {
            case 1:
                nRes++;
                break;
            case 2:
                nCom++;
                break;
        }

        money -= structure.getCost();

        for (StatsObs o : obs) {
            o.moneyUpdate(money);
            o.popUpdate(nRes * settings.getFamilySize());
            o.employmentUpdate(
                    nRes * settings.getFamilySize(),
                    nCom * settings.getShopSize());
        }
    }

    public void demolish(Structure structure) {
        switch (structure.getType()) {
            case 1:
                nRes--;
                break;
            case 2:
                nCom--;
                break;
        }
    }

    public void timeStep()
    {

        int income = 0;
        gameTime++;

        int pop = nRes * settings.getFamilySize();

        if (pop != 0)
        {
            double empRate = Math.min(1, nCom * settings.getShopSize() / pop);
            income = (int) (pop * (empRate * settings.getSalary() *
                    settings.getTaxRate() - settings.getServiceCost()));
            money += income;
        }

        for (StatsObs o : obs) {
            o.incomeUpdate(income);
            o.timeUpdate(gameTime);
            o.moneyUpdate(money);
        }
    }
}
