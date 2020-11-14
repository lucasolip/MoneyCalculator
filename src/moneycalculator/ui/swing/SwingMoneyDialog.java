package moneycalculator.ui.swing;

import java.awt.Component;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDialog;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private final Currency[] currencies;
    private String amount;
    private Currency currencyFrom;
    private Currency currencyTo;

    public SwingMoneyDialog(List<Currency> currencies) {
        this.currencies = currencies.toArray(new Currency[0]);
        this.add(amount());
        this.add(currencyFrom());
        this.add(currencyTo());
    }
    
    @Override
    public Money get() {
        return new Money(currencyFrom, Double.parseDouble(amount));
    }
    
    public Currency getCurrencyFrom() {
        return currencyFrom;
    }
    
    public Currency getCurrencyTo() {
        return currencyTo;
    }

    private Component amount() {
        JTextField textField = new JTextField("100");
        textField.setColumns(10);
        textField.getDocument().addDocumentListener(amountChanged());
        amount = textField.getText();
        return textField;
    }

    private Component currencyFrom() {
        JComboBox combo = new JComboBox(currencies);
        combo.addItemListener(currencyFromChanged());
        currencyFrom = (Currency) combo.getSelectedItem();
        return combo;
    }
    
    private Component currencyTo() {
        JComboBox combo = new JComboBox(currencies);
        combo.addItemListener(currencyToChanged());
        currencyTo = (Currency) combo.getSelectedItem();
        return combo;
    }

    private DocumentListener amountChanged() {
        return new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                try {
                    Document d = e.getDocument();
                    amount = d.getText(0, d.getLength());
                } catch (BadLocationException exc) {}
            }
            
            public void insertUpdate(DocumentEvent e) {
                try {
                    Document d = e.getDocument();
                    amount = d.getText(0, d.getLength());
                } catch (BadLocationException exc) {}
            }
            
            public void removeUpdate(DocumentEvent e) {
                try {
                    Document d = e.getDocument();
                    amount = d.getText(0, d.getLength());
                } catch (BadLocationException exc) {}
            }
        };
    }

    private ItemListener currencyFromChanged() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.DESELECTED) return;
                currencyFrom = (Currency) itemEvent.getItem();
            }
        };
    }
    private ItemListener currencyToChanged() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.DESELECTED) return;
                currencyTo = (Currency) itemEvent.getItem();
            }
        };
    }
    
}
