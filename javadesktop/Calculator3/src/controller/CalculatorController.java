/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.CalculatorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author ANH DUC
 */
public class CalculatorController {

    boolean newNumber = true;
    String operator = "";
    BigDecimal result = new BigDecimal("0");
    BigDecimal Storenum = new BigDecimal("0");
    CalculatorView calculatorView = new CalculatorView();

    public CalculatorController() {
        calculatorView.setVisible(true);
        calculatorView.setLocationRelativeTo(null);
        calculatorView.getBtnNum0().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum1().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum2().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum3().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum4().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum5().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum6().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum7().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum8().addActionListener(new btnNumberListener());
        calculatorView.getBtnNum9().addActionListener(new btnNumberListener());
        calculatorView.getBtnPerform().addActionListener(new btnEqualListener());
        calculatorView.getBtnAdd().addActionListener(new btnAddListener());
        calculatorView.getBtnDiv().addActionListener(new btnDivListener());
        calculatorView.getBtnMulti().addActionListener(new btnMultiListener());
        calculatorView.getBtnSub().addActionListener(new btnSubListener());
        calculatorView.getBtnDot().addActionListener(new btnDotListener());
        calculatorView.getBtnSwap().addActionListener(new btnSwapActionListener());
        calculatorView.getBtnFlip().addActionListener(new btnFlipActionListener());
        calculatorView.getBtnPercent().addActionListener(new btnPercentActionListener());
        calculatorView.getButtonClear().addMouseListener(new btnMouseActionListener());
        calculatorView.getBtnSquareRoot().addActionListener(new btnSquareRootActionListener());
        calculatorView.getBtnMAdd().addActionListener(new btnMAddActionListener());
        calculatorView.getBtnMSub().addActionListener(new btnMSubActionListener());
        calculatorView.getBtnMR().addActionListener(new btnMRActionListener());
        calculatorView.getBtnMC().addActionListener(new btnMCActionListener());
    }

    class btnMCActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ButtonMC();
        }
        
    }
    class btnMRActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(ButtonMR());      
        }
        
    }
    class btnMSubActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "="));
            ButtonMSub(calculatorView.getTxtDisplay());
        }
    }

    class btnMAddActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "="));
            ButtonMAdd(calculatorView.getTxtDisplay());
        }
    }

    class btnSquareRootActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "="));
            calculatorView.setTxtDisplay(CalculateSequare());
        }
    }

    class btnMouseActionListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            calculatorView.setTxtDisplay(ButtonClear());
        }

        @Override
        public void mousePressed(MouseEvent me) {
            return;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            return;
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            return;
        }

        @Override
        public void mouseExited(MouseEvent me) {
            return;
        }

    }

    class btnSubListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "-"));
        }

    }

    class btnMultiListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "x"));
        }
    }

    class btnDivListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "÷"));
        }

    }

    class btnAddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "+"));
        }
    }

    class btnPercentActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "="));
            calculatorView.setTxtDisplay(ButtonPercent());
        }
    }

    class btnFlipActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "="));
            calculatorView.setTxtDisplay(oneDivided());
        }
    }

    class btnSwapActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "="));
            calculatorView.setTxtDisplay(abstractNumber());
        }

    }

    class btnNumberListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(inputDigit(calculatorView.getTxtDisplay(), ae.getActionCommand()));
        }
    }

    class btnEqualListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(Calculate(calculatorView.getTxtDisplay(), "="));
        }
    }

    class btnDotListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatorView.setTxtDisplay(ButtonDot(calculatorView.getTxtDisplay()));
        }

    }

    public String ButtonClear() {
        Storenum = new BigDecimal("0");
        result = new BigDecimal("0");
        operator = "";
        newNumber = true;
        return ("0");
    }
  
    public String inputDigit(String text, String input) {
        if (newNumber) {
            newNumber = false;
            return input;
        } else {
            if (text.length() > 12) {
                return text;
            }
            if (text.equals("0")) {
                return input;
            } else {
                return (text + input);
            }
        }
    }

    public String Calculate(String text, String currentOperation) {
        String output = text;
        if (!newNumber) {
            BigDecimal decimal = new BigDecimal(text);
            switch (operator) {
                case "+":
                    result = result.add(decimal);
                    break;
                case "-":
                    result = result.subtract(decimal);
                    break;
                case "x":
                    result = result.multiply(decimal);
                    break;
                case "÷":
                    if (text.equals("0")) {
                        output = "Error";
                    } else {
                        result = result.divide(decimal, 12, RoundingMode.HALF_UP).stripTrailingZeros();
                    }
                    break;
                default:
                    result = decimal;
                    break;
            }
        }
        if (!output.equals("Error")) {
            output = String.valueOf(result);
        }
        operator = currentOperation;
        newNumber = true;
        return output;
    }

    public String CalculateSequare() {
        if (result.compareTo(new BigDecimal("0")) == -1) {
            return "Error";
        } else {
            Double num = Math.sqrt(result.doubleValue());
            result = new BigDecimal(num);
            result = result.divide(new BigDecimal("1"), 12, RoundingMode.HALF_UP);
            newNumber = true;
            return String.valueOf(result);
        }
    }

    public String ButtonDot(String text) {
        if (!newNumber) {
            if (text.contains(".")) {
                return text;
            } else {
                return text + ".";
            }
        } else {
            newNumber = false;
            return "0.";
        }
    }

    public String abstractNumber() {
        result = new BigDecimal("0").subtract(result);
        newNumber = true;
        return String.valueOf(result);
    }

    public String oneDivided() {
        if (result.compareTo(new BigDecimal("0")) == -1) {
            return "Error";
        } else {
            BigDecimal one = new BigDecimal("1");
            result = one.divide(result, 12, RoundingMode.HALF_UP).stripTrailingZeros();
            newNumber = true;
            return String.valueOf(result);
        }
    }

    public void btnMPLus(String text) {
        if (!text.equals("Error")) {
            Storenum = Storenum.add(new BigDecimal(text));
            newNumber = true;
        }
    }

    public void btnMSub(String text) {
        if (!text.equals("Error")) {
            Storenum = Storenum.subtract(new BigDecimal(text));
            newNumber = true;
        }
    }

    public String ButtonMR() {
        result = Storenum;
        newNumber = true;
        return String.valueOf(Storenum);
    }

    public String ButtonPercent() {
        result = result.divide(new BigDecimal("100"));
        newNumber = true;
        return String.valueOf(result);
    }

    public void ButtonMC() {
        Storenum = new BigDecimal("0");
        newNumber = true;
    }

    public void ButtonMAdd(String text) {
        if (!text.equals("Error")) {
            Storenum = Storenum.add(new BigDecimal(text));
            newNumber = true;
        }
    }

    public void ButtonMSub(String text) {
        if (!text.equals("Error")) {
            Storenum = Storenum.subtract(new BigDecimal(text));
            newNumber = true;
        }
    }
}
