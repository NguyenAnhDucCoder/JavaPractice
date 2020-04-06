package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import View.ReplaceScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ANH DUC
 */
public class ReplaceScreenController {

    private final ReplaceScreen replaceScreen;
    private final MainScreenController mainScreenController;

    public ReplaceScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
        replaceScreen = new ReplaceScreen(mainScreenController.getMainScreen(), true);
        // add event
        Event();
        // set center Replace
        replaceScreen.setLocationRelativeTo(null);
        replaceScreen.setVisible(true);
    }

    private void Event() {
        replaceScreen.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnCancel();
            }
        });
        replaceScreen.getBtnFindNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnFind();
            }
        });
        replaceScreen.getBtnReplace().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnReplace();
            }
        });
        replaceScreen.getBtnReplace_All().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnReplace_All();
            }
        });
    }

    private void btnReplace_All() {
        mainScreenController.setMatchcase(replaceScreen.getMatchCase().isSelected());
        // set find String with text file
        mainScreenController.setFindString(replaceScreen.getTxtFindWhat().getText());
        // replace all
        mainScreenController.ReplaceAll(replaceScreen.getTxtReplaceWith().getText());
    }

    private void btnReplace() {
        // set value wrapAround
        mainScreenController.setWrapAround(replaceScreen.getWrapAround().isSelected());
        // set value matchcase
        mainScreenController.setMatchcase(replaceScreen.getMatchCase().isSelected());
        // set findDown is RadDown value
        mainScreenController.setFindDown(true);
        // set find String to text find
        mainScreenController.setFindString(replaceScreen.getTxtFindWhat().getText());
        // Replace text
        mainScreenController.ReplaceText(replaceScreen.getTxtReplaceWith().getText());
    }

    private void btnCancel() {
        // dispose jdialog
        replaceScreen.dispose();
    }

    private void btnFind() {
        mainScreenController.setWrapAround(replaceScreen.getWrapAround().isSelected());
        mainScreenController.setMatchcase(replaceScreen.getMatchCase().isSelected());
        // set finddown to rad direction
        mainScreenController.setFindDown(true);
        // set findString is TxtFindWhat
        mainScreenController.setFindString(replaceScreen.getTxtFindWhat().getText());
        // find next hightlight
        mainScreenController.findNextSelecttion();
    }

}
