package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import View.FindScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ANH DUC
 */
public class FindScreenController {

    private final FindScreen findScreen;
    private final MainScreenController mainScreenController;

    public FindScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController; 
        findScreen = new FindScreen(mainScreenController.getMainScreen(), true);
        // set Hightlight text to txtFind
        if (mainScreenController.getMainScreen().getjTextArea_Notepad().getSelectedText() != null) {
            findScreen.getTxtFind().setText(mainScreenController.getMainScreen().getjTextArea_Notepad().getSelectedText());
        } // not Hightlight text set findString to txtFind
        else {
            findScreen.getTxtFind().setText(MainScreenController.findString);
        }
        // call event
        Event();
        // set center form
        findScreen.setLocationRelativeTo(null);
        findScreen.setVisible(true);
    }

    private void Event() {
        findScreen.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnCancelEvent();
            }
        });
        findScreen.getBtnFindNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnFindNextEvent();
            }
        });
    }

    private void btnCancelEvent() {
        //dispose jdialog
        findScreen.dispose();
    }

    private void btnFindNextEvent() {
        MainScreenController.wrapAround = findScreen.getWrapAround().isSelected();
        MainScreenController.matchcase = findScreen.getMatchCase().isSelected();
        // set findDown is RadDown value
        MainScreenController.findDown = findScreen.getRadDown().isSelected();
        // set find String to text find
        MainScreenController.findString = findScreen.getTxtFind().getText();
        // find next hightlight text
        mainScreenController.findNextSelecttion();
    }
}
