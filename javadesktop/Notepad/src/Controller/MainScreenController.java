package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import View.MainScreen;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

/**
 *
 * @author ANH DUC
 */
public class MainScreenController {

    private MainScreen mainScreen;
    private boolean isNewFile;
    private String originalText;
    private final UndoManager undoManager;
    private String findString;
    private boolean findDown;
    private String filePath;
    private boolean matchcase;
    private boolean wrapAround;

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    public boolean isWrapAround() {
        return wrapAround;
    }

    public void setWrapAround(boolean wrapAround) {
        this.wrapAround = wrapAround;
    }

    public String getFindString() {
        return findString;
    }

    public void setFindString(String findString) {
        this.findString = findString;
    }

    public boolean isFindDown() {
        return findDown;
    }

    public void setFindDown(boolean findDown) {
        this.findDown = findDown;
    }

    public boolean isMatchcase() {
        return matchcase;
    }

    public void setMatchcase(boolean matchcase) {
        this.matchcase = matchcase;
    }

    public MainScreenController() {
        mainScreen = new MainScreen();
        filePath = "";
        findDown = true;
        originalText = "";
        isNewFile = true;
        matchcase = true;
        undoManager = new UndoManager();
        // set JFrame Center
        mainScreen.setLocationRelativeTo(null);
        // add list event
        ListAllEvent();
        UndoManager_Document();
        mainScreen.setVisible(true);
    }

    private void ListAllEvent() {
        mainScreen.getjMenuItem_Copy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jMenuItem_Copy();
            }
        });
        mainScreen.getjMenuItem_Cut().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jMenuItem_Cut();
            }
        });
        mainScreen.getjMenuItem_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jMenuItem_Exit();
            }
        });
        mainScreen.getjMenuItem_Find().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jMenuItem_Find();
            }
        });
        mainScreen.getjMenuItem_Font().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // show font screen
                FontController fontController = new FontController(MainScreenController.this);
            }
        });
        mainScreen.getjMenuItem_New().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jMenuNew();
            }
        });
        mainScreen.getjMenuItem_Open().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jMenuOpen();
            }
        });
        mainScreen.getjMenuItem_Paste().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // paste text
                mainScreen.getjTextArea_Notepad().paste();
            }
        });
        mainScreen.getjMenuItem_Redo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Redo text if canRedo
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            }
        });
        mainScreen.getjMenuItem_Replace().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Replace text
                ReplaceScreenController replaceScreenController = new ReplaceScreenController(MainScreenController.this);
            }
        });
        mainScreen.getjMenuItem_Save().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jMenuSave();
            }
        });
        mainScreen.getjMenuItem_SaveAs().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                saveAs();
            }
        });
        mainScreen.getjMenuItem_SelectAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // select all text
                mainScreen.getjTextArea_Notepad().selectAll();
            }
        });
        mainScreen.getjMenuItem_Undo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Undo text if canUndo
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });
        mainScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                // check save file or not
                if (!isSave()) {
                    // DO_NOTHING_ON_CLOSE
                    mainScreen.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    return;
                }
                System.exit(0);
            }
        });
        mainScreen.getjMenuEdit().addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent me) {
                jMenuEdit();
            }

            @Override
            public void menuDeselected(MenuEvent me) {
            }

            @Override
            public void menuCanceled(MenuEvent me) {
            }
        });
    }

    private void saveAs() {
        JFileChooser fc = new JFileChooser();
        // add default JFileChooser is txt file
        fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        // yes option
        if (fc.showSaveDialog(mainScreen) == JFileChooser.APPROVE_OPTION) {
            // save originalText to current text notepad
            originalText = mainScreen.getjTextArea_Notepad().getText();
            String filepath = fc.getSelectedFile().getPath() + ".txt";
            FileWriter fileWriter = new FileWriter(filepath, originalText);
            // write file
            fileWriter.WriteFile();
            mainScreen.setTitle(fc.getSelectedFile().getName() + " (MTE) ");
            isNewFile = false;
        }
    }

    private void jMenuOpen() {
        // check save file or not
        if (!isSave()) {
            return;
        }
        JFileChooser chooser = new JFileChooser();
        // add default JFileChooser is txt file
        chooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        if (chooser.showSaveDialog(mainScreen) == JFileChooser.APPROVE_OPTION) {
            mainScreen.getjTextArea_Notepad().setText("");
            try {
                // get path of choose file
                filePath = chooser.getSelectedFile().getPath();
                Scanner scanner = new Scanner(new FileReader(chooser.getSelectedFile().getPath()));
                // write file to text notepad
                while (scanner.hasNext()) {
                    mainScreen.getjTextArea_Notepad().append(scanner.nextLine() + "\n");
                }
                // set title jframe again
                mainScreen.setTitle(chooser.getSelectedFile().getName() + " (MTE) ");
                // Set not new file
                isNewFile = false;
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Cannot open file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jMenuNew() {
        // check save file or not
        if (!isSave()) {
            return;
        }
        // set text to notepad
        mainScreen.getjTextArea_Notepad().setText("");
        // set original text again
        originalText = "";
        // set new file again
        isNewFile = true;
    }

    private void jMenuSave() {
        // check edit file
        if (!originalText.equals(mainScreen.getjTextArea_Notepad().getText())) {
            // check not file 
            if (!isNewFile) {
                try {
                    originalText = mainScreen.getjTextArea_Notepad().getText();
                    // save in old file
                    FileWriter wf = new FileWriter(filePath, originalText);
                    wf.WriteFile();

                } catch (Exception e) {
                }
            } // check new file 
            else {
                JFileChooser fc = new JFileChooser();
                // add default JFileChooser is txt file
                fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
                // Yes option
                if (fc.showSaveDialog(mainScreen) == JFileChooser.APPROVE_OPTION) {
                    try {
                        // set originalText to current text in notepad
                        originalText = mainScreen.getjTextArea_Notepad().getText();
                        // set file path to save
                        filePath = fc.getSelectedFile().getPath() + ".txt";
                        FileWriter wf = new FileWriter(filePath, originalText);
                        // write file with file path selected
                        wf.WriteFile();
                        // set file new is false
                        isNewFile = false;
                        // set title 
                        mainScreen.setTitle(fc.getSelectedFile().getName());
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    private void jMenuEdit() {
        boolean isEmpty = mainScreen.getjTextArea_Notepad().getText().equals("");
        // display find item if text is not empty
        mainScreen.getjMenuItem_Find().setEnabled(!isEmpty);
        boolean isSelect = !(mainScreen.getjTextArea_Notepad().getSelectedText() == null);
        // display cut item if hightlight text
        mainScreen.getjMenuItem_Cut().setEnabled(isSelect);
        // display copy item if hightlight text
        mainScreen.getjMenuItem_Copy().setEnabled(isSelect);
        boolean checkPaste = true;
        try {
            try {
                checkPaste = !Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).equals("");
            } catch (UnsupportedFlavorException ex) {
                checkPaste = false;
            }
            mainScreen.getjMenuItem_Paste().setEnabled(checkPaste);
            // if can Redo
            if (undoManager.canRedo()) {
                // Enabled getjMenuItem_Redo
                mainScreen.getjMenuItem_Redo().setEnabled(true);
            } else {
                // if cannot Redo Disabled getjMenuItem_Redo
                mainScreen.getjMenuItem_Redo().setEnabled(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if can Undo
        if (undoManager.canUndo()) {
            // Enabled getjMenuItem_Undo
            mainScreen.getjMenuItem_Undo().setEnabled(true);
        } else {
            // Disabled getjMenuItem_Undo
            mainScreen.getjMenuItem_Undo().setEnabled(false);
        }
    }

    private void jMenuItem_Find() {
        // open find form
        FindScreenController screenController = new FindScreenController(MainScreenController.this);
    }

    private void jMenuItem_Exit() {
        // check save file or not
        if (!isSave()) {
            return;
        }
        System.exit(0);
    }

    private void jMenuItem_Copy() {
        // copy event
        mainScreen.getjTextArea_Notepad().copy();
    }

    private void jMenuItem_Cut() {
        // cut event
        mainScreen.getjTextArea_Notepad().cut();
    }

    private void UndoManager_Document() {
        Document document = mainScreen.getjTextArea_Notepad().getDocument();
        // add undoManager for jTextArea_Notepad
        document.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent uee) {
                //undoableEdit for jTextArea_Notepad
                undoManager.addEdit(uee.getEdit());
            }
        }
        );
    }

    private boolean isSave() {
        // check whether file is edit or not
        if (!originalText.equals(mainScreen.getjTextArea_Notepad().getText())) {
            int result;
            Object[] chooses = {"Save",
                "Don't save",
                "Cancel"};
            // if new file
            if (isNewFile) {
                // new file display message to save
                result = JOptionPane.showOptionDialog(mainScreen, "Do you want to save changes MTE", "MTE",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, chooses, chooses[0]);
            } else {
                // not new file display message to save in old folder
                result = JOptionPane.showOptionDialog(mainScreen, "Do you want to save changes to " + filePath, "MTE",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, chooses, chooses[0]);
            }
            // Yes message option
            if (result == JOptionPane.YES_OPTION) {
                // if new file
                if (isNewFile) {
                    // create new destination to save file
                    JFileChooser fc = new JFileChooser();
                    // add default JFileChooser is txt file
                    fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
                    if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        try {
                            filePath = fc.getSelectedFile().getPath();
                            // write new file with file path selected
                            FileWriter wf = new FileWriter(filePath + ".txt", mainScreen.getjTextArea_Notepad().getText());
                            wf.WriteFile();
                        } catch (Exception e) {
                        }
                    }
                } // Not new file
                else {
                    try {
                        // write file to old file text
                        FileWriter wf = new FileWriter(filePath, mainScreen.getjTextArea_Notepad().getText());
                        wf.WriteFile();
                    } catch (Exception e) {
                    }
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                // click cancel button to return
                return false;
            }
        }
        return true;
    }

    public int findNext() {

        // get String of text notepad
        String txtNotepad = mainScreen.getjTextArea_Notepad().getText();

        // if matchcase selected
        if (matchcase) {
            // if txtNotead not contain findString show not found
            if (!txtNotepad.contains(findString)) {
                return -1;
            }
        } else {
            // if matchcase not selected and txtNotead not contain findString show not found
            if (!txtNotepad.toUpperCase().contains(findString.toUpperCase())) {
                return -1;
            }
        }
        // get position cursor position in jTextArea
        int lastIndex = mainScreen.getjTextArea_Notepad().getCaretPosition();

        // get position of hightlight start
        int selectionStart = mainScreen.getjTextArea_Notepad().getSelectionStart();
        //get position of hightlight end
        int selectionEnd = mainScreen.getjTextArea_Notepad().getSelectionEnd();
        // if find up
        if (findDown) {
            // if hightlight text case set start find position
            if (selectionStart != selectionEnd) {
                lastIndex = selectionStart + 1;
            }
            if (matchcase) {
                // find index of findString from lastIndex
                lastIndex = txtNotepad.indexOf(findString, lastIndex);
            } else {
                // convert txtNotepad and findString to Uppercase and find index of findString from lastIndex
                lastIndex = txtNotepad.toUpperCase().indexOf(findString.toUpperCase(), lastIndex);
            }
            // if wrapAround
            if (wrapAround) {
                // if lastIndex = -1
                if (lastIndex == -1) {
                    // set Caret position = 0
                    mainScreen.getjTextArea_Notepad().setCaretPosition(0);
                    return 0;
                }
            }
        } else {
            // if hightlight text case
            if (selectionStart != selectionEnd) {
                // start find position
                lastIndex = selectionStart - 1;
            }
            if (matchcase) {
                // find first "findString" backward from last index 
                lastIndex = txtNotepad.lastIndexOf(findString, lastIndex);
            } else {
                // convert txtNotepad and findString to Uppercase and find index of findString backward from lastIndex 
                lastIndex = txtNotepad.toUpperCase().lastIndexOf(findString.toUpperCase(), lastIndex);
            }
            // if wrapAround
            if (wrapAround) {
                // if lastIndex = -1
                if (lastIndex == -1) {
                    // set Caret position = last text
                    mainScreen.getjTextArea_Notepad().setCaretPosition(mainScreen.getjTextArea_Notepad().getText().length());
                    return 0;
                }
            }
        }

        return lastIndex;
    }

    public void findNextSelecttion() {
        // hightlight next found text
        if (findNext() != -1) {
            mainScreen.getjTextArea_Notepad().select(findNext(), findNext() + findString.length());
        } else {
            // not found
            JOptionPane.showMessageDialog(null, "Not found" + " \"" + findString + "\"", "MTE", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void ReplaceText(String content) {
        // if text not selected
        if (mainScreen.getjTextArea_Notepad().getSelectedText() == null) {
            // hightlight content  
            findNextSelecttion();
        }
        // replace contect to text selected 
        if (mainScreen.getjTextArea_Notepad().getSelectedText() != null) {
            // if matchcase
            if (matchcase) {
                // if text selected equal findString
                if (mainScreen.getjTextArea_Notepad().getSelectedText().equals(findString)) {
                    // replace text
                    mainScreen.getjTextArea_Notepad().replaceSelection(content);
                    // find text next to highlight
                    findNextSelecttion();
                } else {
                    // find text next to highlight
                    findNextSelecttion();
                    mainScreen.getjTextArea_Notepad().replaceSelection(content);
                    // find text next to highlight
                    findNextSelecttion();
                }
            } else {
                // not matchcase selected text is equalsIgnoreCase content
                if (mainScreen.getjTextArea_Notepad().getSelectedText().equalsIgnoreCase(findString)) {
                    // replace text
                    mainScreen.getjTextArea_Notepad().replaceSelection(content);
                    // find text next to highlight
                    findNextSelecttion();
                } else {// not equalsIgnoreCase
                    // find text next to highlight
                    findNextSelecttion();
                    // replace text
                    mainScreen.getjTextArea_Notepad().replaceSelection(content);
                    // find text next to highlight
                    findNextSelecttion();
                }
            }

        }

    }

    public void ReplaceAll(String replaceWith) {
        if (matchcase) {
            // replace all
            String txt_replace = mainScreen.getjTextArea_Notepad().getText().replaceAll(findString, replaceWith);
            mainScreen.getjTextArea_Notepad().setText(txt_replace);
        } else {
            // replace all
            String txt_replace = mainScreen.getjTextArea_Notepad().getText().replaceAll(findString, replaceWith);
            txt_replace = txt_replace.replaceAll(findString.toLowerCase(), replaceWith);
            txt_replace = txt_replace.replaceAll(findString.toUpperCase(), replaceWith);
            mainScreen.getjTextArea_Notepad().setText(txt_replace);
        }
    }

}
