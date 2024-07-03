import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public Calculator() {
        createUI();
    }

    private void createUI() {
        // Create the display field
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create buttons
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Layout the components in the frame
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);

        // Frame settings
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789".contains(command)) {
            if (startNewNumber) {
                display.setText(command);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if ("+-*/".contains(command)) {
            firstNumber = Double.parseDouble(display.getText());
            operator = command;
            startNewNumber = true;
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(display.getText());
            double result = calculate(firstNumber, secondNumber, operator);
            display.setText(String.valueOf(result));
            startNewNumber = true;
        } else if (command.equals("C")) {
            display.setText("");
            firstNumber = 0;
            secondNumber = 0;
            operator = "";
            startNewNumber = true;
        }
    }

    private double calculate(double num1, double num2, String op) {
        switch (op) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/": return num2 == 0 ? 0 : num1 / num2; // Handle division by zero
            default: return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}