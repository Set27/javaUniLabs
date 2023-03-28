import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class RandomNumberClient {
    private JTextField inputField;
    private JTextArea outputArea;

    public static void main(String[] args) throws IOException {
        new RandomNumberClient().run();
    }

    public void run() throws IOException {
        JFrame frame = new JFrame("Random Number Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel inputLabel = new JLabel("Enter a positive integer:");
        inputField = new JTextField(10);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());

        JLabel outputLabel = new JLabel("Generated Random Numbers:");
        outputArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new GenerateButtonListener());

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);
        frame.add(generateButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private class GenerateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                String input = inputField.getText();
                Socket socket = new Socket("localhost", 2348
                );
                PrintWriter out = new PrintWriter
                        (socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println(input);

                String output = in.readLine();
                outputArea.setText(output);

                out.close();
                in.close();
                socket.close();
            } catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(null, "Unknown host: localhost.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not connect to server.");
            }
        }
    }
}