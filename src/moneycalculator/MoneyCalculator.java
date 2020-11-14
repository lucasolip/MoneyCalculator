package moneycalculator;

import moneycalculator.persistence.CurrencyListLoader;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.persistence.file.FileCurrencyListLoader;
import moneycalculator.persistence.rest.RestExchangeRateLoader;

public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyListLoader currencyListLoader = new FileCurrencyListLoader("currencies.txt");
        ExchangeRateLoader exchangeRateLoader = new RestExchangeRateLoader();
        
        MoneyCalculatorFrame moneyCalculatorFrame = new MoneyCalculatorFrame(currencyListLoader.currencies(), exchangeRateLoader);
    }
    
}
