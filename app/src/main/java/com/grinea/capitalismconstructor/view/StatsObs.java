package com.grinea.capitalismconstructor.view;

/*
 * Observer interface for classes that will need to operate on gamestate changes
 */

public interface StatsObs
{
    void timeUpdate(int time);

    void moneyUpdate(int money);

    void incomeUpdate(int income);

    void popUpdate(int pop);

    void employmentUpdate(int pop, int jobs);
}
