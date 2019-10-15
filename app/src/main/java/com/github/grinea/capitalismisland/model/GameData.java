package com.github.grinea.capitalismisland.model;

import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.github.grinea.capitalismisland.view.StatsObs;

import java.util.LinkedList;
import java.util.List;

public class GameData
{

    private static GameData instance = null;
    private Settings settings;
    private GameMap map;
    private int money;
    private int gameTime;
    private int nRes;
    private int nCom;
    private int income;
    private List<StatsObs> obs;
    private GameDatabase db;

    private GameData()
    {
        settings = new Settings();
        map = new GameMap(Math.max(settings.getMapWidth(), 3), Math.max(settings.getMapHeight(), 3));
        money = settings.getInitialMoney();
        gameTime = 0;
        nRes = 0;
        nCom = 0;
        income = 0;
        obs = new LinkedList<>();
    }

    public static GameData getInstance()
    {
        if (instance == null)
        {
            instance = new GameData();
        }

        return instance;
    }

    public void loadDB(GameDatabase db)
    {

        this.db = db;

        SettingsCursor setCur = db.settingsQuery();
        setCur.moveToFirst();
        if (setCur.getCount() > 0)
        {
            System.out.println(settings.getMapWidth());
            settings.setMapWidth(setCur.mapWidth());
            settings.setMapHeight(setCur.mapHeight());
            settings.setInitialMoney(setCur.initCash());
        }
        setCur.close();

        StateCursor staCur = db.stateQuery();
        staCur.moveToFirst();
        if (staCur.getCount() > 0)
        {
            money = staCur.getMoney();
            gameTime = staCur.getTime();
            nRes = staCur.getRes();
            nCom = staCur.getCom();
            income = staCur.getIncome();
        }
        staCur.close();

        MapCursor mapCur = db.mapQuery();
        if (mapCur.getCount() > 0)
        {
            mapCur.moveToFirst();
            System.out.println(mapCur.getCount());
            map = new GameMap(settings.getMapWidth(),settings.getMapHeight());
            while(!mapCur.isAfterLast())
            {
                MapElement mapElem = mapCur.getElement();
                if (mapElem.getPos() == 0)
                {
                    System.out.println(mapElem.toString());
                }
                if (mapElem.getStructure() != null)
                {
                    build(mapElem.getStructure());
                }

                map.setElement(mapElem);
                mapCur.moveToNext();
            }

        }
        else
        {
            for(int ii = 0; ii < map.getCount(); ii++)
            {
                db.insertMapElement(map.getElement(ii));
            }
        }
        mapCur.close();

    }

    public void setSettings(Settings settings)
    {
        this.settings = settings;
        db.setSettings(settings.getMapHeight(), settings.getMapWidth(), settings.getInitialMoney());
    }

    public int getIncome()
    {
        return income;
    }

    public void resetData()
    {
        map = new GameMap(Math.max(settings.getMapWidth(), 3), Math.max(settings.getMapHeight(), 3));
        money = settings.getInitialMoney();
        gameTime = 0;
        nRes = 0;
        nCom = 0;
        income = 0;

        db.reset();
    }

    public void setObs(StatsObs observer)
    {
        obs.add(observer);
    }

    public GameMap getMap()
    {
        return map;
    }

    public int getMoney()
    {
        return money;
    }

    public int getGameTime()
    {
        return gameTime;
    }

    public int getPop()
    {
        return settings.getFamilySize() * nRes;
    }

    public double getEmploy()
    {
        if (nRes == 0)
        {
            return 0;
        }
        else
        {
            return Math.min(1, nCom * settings.getShopSize() / getPop());
        }
    }

    public Settings getSettings()
    {
        return settings;
    }

    public void build(Structure structure)
    {
        switch (structure.getType())
        {
            case 1:
                nRes++;
                break;
            case 2:
                nCom++;
                break;
        }

        money -= structure.getCost();

        for (StatsObs o : obs)
        {
            o.moneyUpdate(money);
            o.popUpdate(getPop());
            o.employmentUpdate(nRes * settings.getFamilySize(), nCom * settings.getShopSize());
        }

        db.setState(gameTime, money, nRes, nCom, income);
    }

    public void demolish(Structure structure)
    {
        switch (structure.getType())
        {
            case 1:
                nRes--;
                break;
            case 2:
                nCom--;
                break;
        }

        for (StatsObs o : obs)
        {
            o.popUpdate(getPop());
            o.employmentUpdate(nRes * settings.getFamilySize(), nCom * settings.getShopSize());
        }

        db.setState(gameTime, money, nRes, nCom, income);
    }

    public void timeStep()
    {

        income = 0;
        gameTime++;

        if (getPop() != 0)
        {
            income = (int) (getPop() * (getEmploy() * settings.getSalary() * settings.getTaxRate() - settings.getServiceCost()));
            money += income;
        }

        db.setState(gameTime, money, nRes, nCom, income);

        for (StatsObs o : obs)
        {
            o.incomeUpdate(income);
            o.timeUpdate(gameTime);
            o.moneyUpdate(money);
        }
    }

    public void updateMapElement(MapElement elem)
    {
        db.updateMapElement(elem);
    }
}
