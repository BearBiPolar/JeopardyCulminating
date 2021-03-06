import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JPanel;
import javax.swing.JFrame;
/**
 * Settings, helps to customize various aspects of the program.
 *
 * @author Frank Lai
 * @version 2018-06-08
 */
public class SettingsDialog extends JDialog
{
    private static final int DIALOG_WIDTH = 500;
    private static final int DIALOG_HEIGHT = 400;

    private static final String TITLE = "Settings";
    private static final boolean MODALITY_TYPE = true;

    // GUI elements.
    private JPanel parameterPanel;
    private JComboBox parameterPanel_questionNumberList;
    private JLabel parameterPanel_questionNumberList_label;
    private JComboBox parameterPanel_answerNumberList;
    private JLabel parameterPanel_answerNumberList_label;
    private JComboBox parameterPanel_playerNumberList;
    private JLabel parameterPanel_playerNumberList_label;

    private JPanel audioPanel;
    private JCheckBox audioPanel_isMuteCheckBox;

    private JPanel themePanel;

    private JPanel controlPanel;
    private JButton controlPanel_applyButton;
    private JButton controlPanel_cancelButton;
    // Instance.
    private int previousQuestionNumberList;
    private int previousAnswerNumberList;
    private int previousPlayerNumberList;
    private boolean isMute;

    /**
     * Creates a new settings dialog.
     */
    public SettingsDialog()
    {
        setTitle(TITLE);
        setModal(MODALITY_TYPE);
        setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        setLayout(new BorderLayout());

        // Create panels.
        parameterPanel = createParameterPanel();
        audioPanel = createAudioPanel();
        themePanel = createThemePanel();
        controlPanel = createControlPanel();

        add(parameterPanel,BorderLayout.WEST);
        add(audioPanel,BorderLayout.CENTER);
        add(themePanel,BorderLayout.EAST);
        add(controlPanel,BorderLayout.SOUTH);
        // Ensure final validation.
        pack();
    } // end of constructor SettingsDialog()

    /**
     * Revisibilizes the dialog.
     */
    public void showDialog()
    {
        setVisible(true);
    } // end of method showDialog()

    /**
     * Invisibilizes the dialog.
     */
    public void hideDialog()
    {
        setVisible(false);
    } // end of method hideDialog()

    /**
     * Returns the number of questions selected by the dialog.
     * 
     * @return the number of questions
     */
    public int getQuestionCount()
    {
        return parameterPanel_questionNumberList.getSelectedIndex() + 1;
    } // end of getQuestionCount()

    /**
     * Returns the number of answers selected by the dialog.
     * 
     * @return the number of answers
     */
    public int getAnswerCount()
    {
        return parameterPanel_answerNumberList.getSelectedIndex() + 1;
    } // end of getQuestionCount()
    
    /**
     * Returns the player of players selected by the dialog.
     * 
     * @return the number of players
     */
    public int getPlayerCount()
    {
        return parameterPanel_playerNumberList.getSelectedIndex() + 1;
    } // end of getPlayerCount()
    
    /**
     * Returns whether or not the audio is muted.
     * 
     * @return whether or not the audio is muted
     */
    public boolean getMuted()
    {
        return isMute;
    } // end of getMuted()

    /*
     * Creates a new parameter panel.
     */
    private JPanel createParameterPanel()
    {
        JPanel skeletonPanel = new JPanel();
        // Number of questions and answers.
        String[] parameterPossibleText = {"One","Two","Three","Four","Five","Six"};
        String[] parameterPossiblePlayerText = {"One","Two","Three","Four","Five","Six","Seven","Eight"};
        previousQuestionNumberList = 5;
        previousAnswerNumberList = 4;
        previousPlayerNumberList = 3;
        
        // Questions.
        parameterPanel_questionNumberList_label = new JLabel("Questions");
        skeletonPanel.add(parameterPanel_questionNumberList_label);
        parameterPanel_questionNumberList = new JComboBox(parameterPossibleText);
        parameterPanel_questionNumberList.setSelectedIndex(previousQuestionNumberList);
        skeletonPanel.add(parameterPanel_questionNumberList);

        // Answers.
        parameterPanel_answerNumberList_label = new JLabel("Answers");
        skeletonPanel.add(parameterPanel_answerNumberList_label);
        parameterPanel_answerNumberList = new JComboBox(parameterPossibleText);
        parameterPanel_answerNumberList.setSelectedIndex(previousAnswerNumberList);
        skeletonPanel.add(parameterPanel_answerNumberList);
        
        // Players.
        parameterPanel_playerNumberList_label = new JLabel("Players");
        skeletonPanel.add(parameterPanel_playerNumberList_label);
        parameterPanel_playerNumberList = new JComboBox(parameterPossiblePlayerText);
        parameterPanel_playerNumberList.setSelectedIndex(previousPlayerNumberList);
        skeletonPanel.add(parameterPanel_playerNumberList);
        
        // Cool group borders.
        skeletonPanel.setBorder(BorderFactory.createTitledBorder("Jeopardy Paremeters"));

        return skeletonPanel;
    } // end of method createParameterPanel()

    /*
     * Creates a new audio panel.
     */
    private JPanel createAudioPanel()
    {
        JPanel skeletonPanel = new JPanel();

        isMute = false;
        // Audio Checkbox
        audioPanel_isMuteCheckBox = new JCheckBox("Audio On");
        audioPanel_isMuteCheckBox.addItemListener(new SettingsDialog_audioPanel_isMuteCheckBoxListener());
        skeletonPanel.add(audioPanel_isMuteCheckBox);

        // Media set value slider.

        // Cool group borders.
        skeletonPanel.setBorder(BorderFactory.createTitledBorder("Audio"));

        return skeletonPanel;
    } // end of method createParameterPanel()

    /*
     * Creates a new theme panel.
     */
    private JPanel createThemePanel()
    {
        JPanel skeletonPanel = new JPanel();

        // Cool group borders.
        skeletonPanel.setBorder(BorderFactory.createTitledBorder("Theme"));

        return skeletonPanel;
    } // end of method createParameterPanel()

    /*
     * Creates a new control panel.
     */
    private JPanel createControlPanel()
    {
        JPanel skeletonPanel = new JPanel();

        SettingsDialog_controlPanelListener actionListener = new SettingsDialog_controlPanelListener();
        // Apply button.
        controlPanel_applyButton = new JButton("Apply Changes");
        controlPanel_applyButton.addActionListener(actionListener);
        skeletonPanel.add(controlPanel_applyButton);
        // Cancel button.
        controlPanel_cancelButton = new JButton("Cancel");
        controlPanel_cancelButton.addActionListener(actionListener);
        skeletonPanel.add(controlPanel_cancelButton);
        
        return skeletonPanel;
    } // end of method createControlPanel()

    /*
     * Hide and clears all options to default.
     */
    private void hideAndClear()
    {
        setVisible(false);

        // Clear.
        parameterPanel_questionNumberList.setSelectedIndex(previousQuestionNumberList);
        parameterPanel_answerNumberList.setSelectedIndex(previousAnswerNumberList);
        parameterPanel_playerNumberList.setSelectedIndex(previousPlayerNumberList);
        audioPanel_isMuteCheckBox.setSelected(!isMute);

    } // end of method hideAndClear()

    /*
     * ActionListener class that handles the submit or cancel buttons.
     */
    private class SettingsDialog_controlPanelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();
            if (source == controlPanel_applyButton)
            {
                // Apply options, simply hide.
                previousQuestionNumberList = getQuestionCount();
                previousAnswerNumberList = getAnswerCount();
                previousPlayerNumberList = getPlayerCount();
                isMute = !audioPanel_isMuteCheckBox.isSelected();
                

                Jeopardy.setPlayerCount(getPlayerCount());
                Jeopardy.window.setAnswerCount(getAnswerCount());
                Jeopardy.window.setQuestionCount(getQuestionCount());
                Jeopardy.window.setPlayerCount(getPlayerCount());
                
                // Reset everything.
                Jeopardy.window.resetEverything();
                
                hideDialog();
            }
            else if (source == controlPanel_cancelButton)
            {
                // Hide and then reset everything.
                hideAndClear();
            } // end of if (source == controlPanel_applyButton)
        }// end of ActionPerformed(ActionEvent event)
    } // end of class SettingsDialog_controlPanelListener()

    /*
     * ItemListener class exclusively for the audio checkbox.
     */
    private class SettingsDialog_audioPanel_isMuteCheckBoxListener implements ItemListener
    {
        /**
         * Invoked on anything changing with the mute checkbox.
         * 
         * @param event the thing that happens
         */
        public void itemStateChanged(ItemEvent event) {                 
            // Check if mute or not, and then start or stop audio accordingly to reflect that.
            if(audioPanel_isMuteCheckBox.isSelected())
            {
                // Play audio.
                Jeopardy.playTheme();
            }
            else 
            {
                // Mute audio.
                Jeopardy.stopTheme();
            } // end of if(audioPanel_isMuteCheckBox.isSelected())
        }  
    } // end of class SettingsDialog_audioPanel_isMuteCheckBoxListener() extends ItemListener
    /*
     * ItemListener class exclusively for the audio slider.
     */
    
} // end of class SettingsDialog extends JDialog
