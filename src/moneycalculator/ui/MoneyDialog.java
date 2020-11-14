package moneycalculator.ui;

import moneycalculator.model.Money;
import moneycalculator.model.Currency;

public interface MoneyDialog {
    public Money get();
    public Currency getCurrencyFrom();
    public Currency getCurrencyTo();
}
