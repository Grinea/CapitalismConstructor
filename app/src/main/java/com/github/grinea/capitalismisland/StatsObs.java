package com.github.grinea.capitalismisland;

public interface StatsObs
{
    void timeUpdate(int time);
    void moneyUpdate(int money);
    void incomeUpdate(int income);
    void popUpdate(int pop);
    void employmentUpdate(int pop, int jobs);
}
