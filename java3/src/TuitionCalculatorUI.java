import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;

public class TuitionCalculatorUI extends JFrame implements ActionListener {
    private int fullTimeCost = 1000;
    private int remoteCost = 500;
    private int morningCost = 0;
    private int afternoonCost = 100;
    private int eveningCost = 200;

    private JComboBox<String> timeChoiceComboBox;
    private JComboBox<String> formChoiceComboBox;
    private ButtonGroup roleButtonGroup;
    private JRadioButton adminRadioButton;
    private JButton calculateButton;
    private JButton editConstantsButton;
    private JLabel dateLabel;
    private JLabel dateErrorLabel; // new error message label
    private JLabel timeChoiceLabel;
    private JLabel formChoiceLabel;
    private JLabel costsLabel;
    private JLabel roleLabel;
    private JTextField dateField;

    public TuitionCalculatorUI() {
        // Set up the frame
        setTitle("Tuition Calculator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Set up the components
        roleLabel = new JLabel("Select your role:");
        add(roleLabel);

        JPanel rolePanel = new JPanel();
        roleButtonGroup = new ButtonGroup();
        adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.addActionListener(this);
        roleButtonGroup.add(adminRadioButton);
        rolePanel.add(adminRadioButton);
        JRadioButton userRadioButton = new JRadioButton("User");
        userRadioButton.addActionListener(this);
        roleButtonGroup.add(userRadioButton);
        rolePanel.add(userRadioButton);
        add(rolePanel);

        dateLabel = new JLabel("Date of Event (YYYY-MM-DD):");
        dateErrorLabel = new JLabel("");
        add(dateErrorLabel);
        add(dateLabel);
        dateField = new JTextField(10);
        add(dateField);

        timeChoiceLabel = new JLabel("Time of Event:");

        add(timeChoiceLabel);
        timeChoiceComboBox = new JComboBox<String>(new String[]{"Morning", "Afternoon", "Evening"});
        add(timeChoiceComboBox);

        formChoiceLabel = new JLabel("Form of Training:");
        add(formChoiceLabel);
        formChoiceComboBox = new JComboBox<String>(new String[]{"Full-Time", "Remote"});
        add(formChoiceComboBox);

        calculateButton = new JButton("Calculate");
        add(calculateButton);
        calculateButton.addActionListener(this);

        editConstantsButton = new JButton("Edit Constants");
        add(editConstantsButton);
        editConstantsButton.addActionListener(this);

        costsLabel = new JLabel();
        add(costsLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminRadioButton) {
            // Show the "Edit Constants" button
            editConstantsButton.setVisible(true);
            timeChoiceComboBox.setVisible(false);
            formChoiceComboBox.setVisible(false);
            calculateButton.setVisible(false);
            dateLabel.setVisible(false);
            dateErrorLabel.setVisible(false);
            timeChoiceLabel.setVisible(false);
            formChoiceLabel.setVisible(false);
            costsLabel.setVisible(false);
            roleLabel.setVisible(false);
            dateField.setVisible(false);
        } else {
            // Hide the "Edit Constants" button
            editConstantsButton.setVisible(false);
            timeChoiceComboBox.setVisible(true);
            formChoiceComboBox.setVisible(true);
            calculateButton.setVisible(true);
            dateLabel.setVisible(true);
            dateErrorLabel.setVisible(true);
            timeChoiceLabel.setVisible(true);
            formChoiceLabel.setVisible(true);
            costsLabel.setVisible(true);
            roleLabel.setVisible(true);
            dateField.setVisible(true);
        }

        // Get the user input


        String timeChoice = (String) timeChoiceComboBox.getSelectedItem();
        int timeCost;
        switch (timeChoice) {
            case "Morning":
                timeCost = morningCost;
                break;
            case "Afternoon":
                timeCost = afternoonCost;
                break;
            case "Evening":
                timeCost = eveningCost;
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid time choice.");
                return;
        }

        String formChoice = (String) formChoiceComboBox.getSelectedItem();
        int formCost;
        switch (formChoice) {
            case "Full-Time":
                formCost = fullTimeCost;
                break;
            case "Remote":
                formCost = remoteCost;
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid form choice.");
                return;
        }

        int totalCost = timeCost + formCost;
        String dateStr = ((JTextField) getContentPane().getComponent(4)).getText();
        LocalDate date;

        if(e.getSource() == calculateButton) {
            try {
                date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException ex) {
                dateErrorLabel.setText("Invalid date format. Please use YYYY-MM-DD.");
                return;
            }
            costsLabel.setText("Total Cost: $" + totalCost);
        }
        dateErrorLabel.setText(""); // clear any previous error message

        if (e.getSource() == editConstantsButton){
            String fullTimeCostStr = JOptionPane.showInputDialog("Enter full-time cost:");
            fullTimeCost = Integer.parseInt(fullTimeCostStr);
            String remoteCostStr = JOptionPane.showInputDialog("Enter remote cost:");
            remoteCost = Integer.parseInt(remoteCostStr);

            String morningCostStr = JOptionPane.showInputDialog("Enter morning cost:");
            morningCost = Integer.parseInt(morningCostStr);

            String afternoonCostStr = JOptionPane.showInputDialog("Enter afternoon cost:");
            afternoonCost = Integer.parseInt(afternoonCostStr);

            String eveningCostStr = JOptionPane.showInputDialog("Enter evening cost:");
            eveningCost = Integer.parseInt(eveningCostStr);

            JOptionPane.showMessageDialog(this, "Constants updated.");
        }
    }

    public static void main(String[] args) {
        // Use the EDT (Event Dispatch Thread) to create the GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TuitionCalculatorUI();
            }
        });

    }
}