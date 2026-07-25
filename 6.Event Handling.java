import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends Frame implements ActionListener {

    TextField t1, t2;
    Label result;
    Button add, sub, mul, div, clear;

    public SimpleCalculator() {

        setTitle("Simple Calculator");
        setLayout(new FlowLayout());

        add(new Label("First Number:"));
        t1 = new TextField(10);
        add(t1);

        add(new Label("Second Number:"));
        t2 = new TextField(10);
        add(t2);

        add = new Button("Add");
        sub = new Button("Subtract");
        mul = new Button("Multiply");
        div = new Button("Divide");
        clear = new Button("Clear");

        add(add);
        add(sub);
        add(mul);
        add(div);
        add(clear);

        result = new Label("Result: ");
        add(result);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        clear.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(350, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == clear) {
                t1.setText("");
                t2.setText("");
                result.setText("Result: ");
                return;
            }

            double a = Double.parseDouble(t1.getText());
            double b = Double.parseDouble(t2.getText());
            double res = 0;

            if (e.getSource() == add)
                res = a + b;
            else if (e.getSource() == sub)
                res = a - b;
            else if (e.getSource() == mul)
                res = a * b;
            else if (e.getSource() == div) {
                if (b == 0) {
                    result.setText("Cannot divide by zero");
                    return;
                }
                res = a / b;
            }

            result.setText("Result: " + res);

        } catch (Exception ex) {
            result.setText("Invalid Input");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
