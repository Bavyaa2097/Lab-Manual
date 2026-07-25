import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistration extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5;
    JTextField t1, t2;
    JComboBox<String> course;
    JRadioButton male, female;
    JCheckBox java, python;
    JButton submit, clear;
    JTextArea area;
    ButtonGroup bg;

    public StudentRegistration() {

        setTitle("Student Registration Form");
        setLayout(new FlowLayout());

        l1 = new JLabel("Roll Number:");
        t1 = new JTextField(15);

        l2 = new JLabel("Name:");
        t2 = new JTextField(15);

        l3 = new JLabel("Course:");
        String c[] = {"Computer Science", "IT", "ECE", "EEE"};
        course = new JComboBox<>(c);

        l4 = new JLabel("Gender:");
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        l5 = new JLabel("Skills:");
        java = new JCheckBox("Java");
        python = new JCheckBox("Python");

        submit = new JButton("Submit");
        clear = new JButton("Clear");

        area = new JTextArea(8, 30);

        add(l1); add(t1);
        add(l2); add(t2);
        add(l3); add(course);
        add(l4); add(male); add(female);
        add(l5); add(java); add(python);
        add(submit); add(clear);
        add(area);

        submit.addActionListener(this);
        clear.addActionListener(this);

        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {

            if (t1.getText().isEmpty() || t2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all mandatory fields");
                return;
            }

            String gender = male.isSelected() ? "Male" : "Female";

            String skills = "";
            if (java.isSelected()) skills += "Java ";
            if (python.isSelected()) skills += "Python";

            area.setText(
                "Student Details\n\n" +
                "Roll No : " + t1.getText() +
                "\nName : " + t2.getText() +
                "\nCourse : " + course.getSelectedItem() +
                "\nGender : " + gender +
                "\nSkills : " + skills
            );

        } else {

            t1.setText("");
            t2.setText("");
            bg.clearSelection();
            java.setSelected(false);
            python.setSelected(false);
            course.setSelectedIndex(0);
            area.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentRegistration();
    }
}
