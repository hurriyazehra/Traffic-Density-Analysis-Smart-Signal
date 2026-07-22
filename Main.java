import java.util.ArrayList;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

class User {
    private String name;
    private int age;

    // Parameterized Constructor
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Display User Information
    public void DisplayUser() {
        System.out.println("User Details");
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
    }
}

// Parent Class
class StudyTask {
    protected String subject;
    protected int hours;

    // Constructor
    public StudyTask(String subject, int hours) {
        this.subject = subject;
        this.hours = hours;
    }

    // Method
    public void showTask() {
        System.out.println("Subject: " + subject);
        System.out.println("Hours: " + hours);
    }
}

// Child Class 1
class ExamTask extends StudyTask {
    private String examdate;

    public ExamTask(String subject, int hours, String examdate) {
        super(subject, hours);
        this.examdate = examdate;
    }

    @Override
    public void showTask() {
        System.out.println("Exam Task");
        System.out.println("Subject: " + subject);
        System.out.println("Hours: " + hours);
        System.out.println("Exam Date: " + examdate);
    }
}

// Child Class 2
class AssignmentTask extends StudyTask {
    private String submissiondate;

    public AssignmentTask(String subject, int hours, String submissiondate) {
        super(subject, hours);
        this.submissiondate = submissiondate;
    }

    @Override
    public void showTask() {
        System.out.println("Assignment Task");
        System.out.println("Subject: " + subject);
        System.out.println("Hours: " + hours);
        System.out.println("Submission Date: " + submissiondate);
    }
}

// Planner Class
class Planner {

    ArrayList<StudyTask> tasks = new ArrayList<>();

    // Add Task
    public void addTask(StudyTask task) {
        tasks.add(task);
        System.out.println("\nTask Added Successfully <3");
    }

    // Display All Tasks
    public void showTask() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo Task Added Yet!");
            return;
        }

        System.out.println("\nAll Tasks");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\nTask " + (i + 1));
            tasks.get(i).showTask();
        }
    }

    // Delete Task
    public void deletetask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("\nTask Deleted Successfully!");
        } else {
            System.out.println("\nInvalid Task Number!");
        }
    }
}

// Main Class
public class Main {

    // UI-only components used to create the rounded dashboard appearance.
    static class RoundedPanel extends JPanel {
        private Color color;
        private int radius;

        RoundedPanel(Color color, int radius) {
            this.color = color;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(color);
            graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            graphics2D.dispose();
            super.paintComponent(graphics);
        }
    }

    static class RoundedButton extends JButton {
        RoundedButton(String text, Color background, Color foreground) {
            super(text);
            setBackground(background);
            setForeground(foreground);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(getModel().isPressed() ? getBackground().darker() : getBackground());
            graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            graphics2D.dispose();
            super.paintComponent(graphics);
        }
    }

    static class RoundedTextField extends JTextField {
        RoundedTextField(int columns) {
            super(columns);
            setOpaque(false);
            setFont(new Font("Segoe UI", Font.PLAIN, 13));
            setBorder(BorderFactory.createEmptyBorder(9, 12, 9, 12));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
            graphics2D.setColor(new Color(232, 222, 229));
            graphics2D.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
            graphics2D.dispose();
            super.paintComponent(graphics);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String name = JOptionPane.showInputDialog(null, "Enter student name:", "Student Profile", JOptionPane.QUESTION_MESSAGE);
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "A student name is required.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String ageText = JOptionPane.showInputDialog(null, "Enter student age:", "Student Profile", JOptionPane.QUESTION_MESSAGE);
            int age;
            try {
                age = Integer.parseInt(ageText);
                if (age <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Enter a valid positive age.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            User u = new User(name.trim(), age);
            Planner p = new Planner();

            JFrame frame = new JFrame("Smart Study Planner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1050, 680);
            frame.setLocationRelativeTo(null);

            Color background = new Color(255, 250, 252);
            Color pink = new Color(234, 145, 174);
            Color softPink = new Color(255, 235, 241);
            Color palePink = new Color(252, 244, 247);
            Color text = new Color(58, 48, 54);
            Color mutedText = new Color(131, 116, 123);

            JPanel content = new JPanel(new BorderLayout(22, 22));
            content.setBackground(background);
            content.setBorder(BorderFactory.createEmptyBorder(26, 32, 28, 32));

            JLabel title = new JLabel("Smart Study Planner");
            title.setFont(new Font("Segoe UI", Font.BOLD, 28));
            title.setForeground(text);

            JLabel subtitle = new JLabel("Plan with focus. Progress with confidence.");
            subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            subtitle.setForeground(mutedText);

            JPanel titleArea = new JPanel(new GridLayout(2, 1, 0, 3));
            titleArea.setOpaque(false);
            titleArea.add(title);
            titleArea.add(subtitle);

            RoundedPanel profile = new RoundedPanel(softPink, 22);
            profile.setLayout(new GridLayout(2, 1, 0, 5));
            profile.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
            profile.setPreferredSize(new Dimension(270, 78));
            JLabel profileTitle = new JLabel("Student Profile");
            profileTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
            profileTitle.setForeground(new Color(154, 80, 104));
            JLabel profileDetails = new JLabel("Name: " + name.trim() + "     |     Age: " + age);
            profileDetails.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            profileDetails.setForeground(text);
            profile.add(profileTitle);
            profile.add(profileDetails);

            JPanel top = new JPanel(new BorderLayout(18, 0));
            top.setOpaque(false);
            top.add(titleArea, BorderLayout.WEST);
            top.add(profile, BorderLayout.EAST);
            content.add(top, BorderLayout.NORTH);

            RoundedPanel form = new RoundedPanel(palePink, 24);
            form.setLayout(new GridBagLayout());
            form.setBorder(BorderFactory.createEmptyBorder(20, 22, 20, 22));
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(6, 8, 6, 8);
            constraints.anchor = GridBagConstraints.WEST;

            JComboBox<String> typeBox = new JComboBox<>(new String[]{"Exam", "Assignment"});
            typeBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            typeBox.setBackground(Color.WHITE);
            typeBox.setBorder(BorderFactory.createLineBorder(new Color(232, 222, 229)));
            JTextField subjectField = new RoundedTextField(14);
            JTextField hoursField = new RoundedTextField(6);
            JTextField dateField = new RoundedTextField(12);
            JButton addButton = new RoundedButton("+  Add Task", pink, Color.WHITE);

            JLabel formTitle = new JLabel("Add a new task");
            formTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
            formTitle.setForeground(text);
            constraints.gridx = 0; constraints.gridy = 0; constraints.gridwidth = 5;
            constraints.insets = new Insets(0, 8, 14, 8);
            form.add(formTitle, constraints);
            constraints.gridwidth = 1;
            constraints.insets = new Insets(6, 8, 6, 8);

            constraints.gridx = 0; constraints.gridy = 1; form.add(new JLabel("Task type"), constraints);
            constraints.gridx = 1; form.add(typeBox, constraints);
            constraints.gridx = 2; form.add(new JLabel("Subject"), constraints);
            constraints.gridx = 3; form.add(subjectField, constraints);
            constraints.gridx = 0; constraints.gridy = 2; form.add(new JLabel("Study hours"), constraints);
            constraints.gridx = 1; form.add(hoursField, constraints);
            constraints.gridx = 2; form.add(new JLabel("Date"), constraints);
            constraints.gridx = 3; form.add(dateField, constraints);
            constraints.gridx = 4; form.add(addButton, constraints);

            for (java.awt.Component component : form.getComponents()) {
                if (component instanceof JLabel && component != formTitle) {
                    component.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    component.setForeground(mutedText);
                }
            }

            DefaultTableModel model = new DefaultTableModel(new String[]{"#", "Task Type", "Subject", "Study Hours"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            JTable table = new JTable(model);
            table.setRowHeight(38);
            table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            table.setForeground(text);
            table.setSelectionBackground(softPink);
            table.setSelectionForeground(text);
            table.setShowVerticalLines(false);
            table.setGridColor(new Color(245, 237, 240));
            table.getTableHeader().setBackground(Color.WHITE);
            table.getTableHeader().setForeground(mutedText);
            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
            table.getTableHeader().setPreferredSize(new Dimension(0, 36));

            JLabel status = new JLabel("Ready to add a task.");
            status.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            status.setForeground(text);
            JButton deleteButton = new RoundedButton("Delete selected", new Color(255, 235, 239), new Color(183, 83, 107));

            addButton.addActionListener(event -> {
                String subject = subjectField.getText().trim();
                String date = dateField.getText().trim();
                int hours;

                if (subject.isEmpty() || date.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Enter a subject and date.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    hours = Integer.parseInt(hoursField.getText().trim());
                    if (hours <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "Study hours must be a positive whole number.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                StudyTask task;
                if (typeBox.getSelectedItem().equals("Exam")) {
                    task = new ExamTask(subject, hours, date);
                } else {
                    task = new AssignmentTask(subject, hours, date);
                }
                p.addTask(task);

                model.setRowCount(0);
                for (int i = 0; i < p.tasks.size(); i++) {
                    StudyTask savedTask = p.tasks.get(i);
                    String taskType = savedTask instanceof ExamTask ? "Exam" : "Assignment";
                    model.addRow(new Object[]{i + 1, taskType, savedTask.subject, savedTask.hours});
                }

                subjectField.setText("");
                hoursField.setText("");
                dateField.setText("");
                status.setText("Task added successfully.");
                JOptionPane.showMessageDialog(frame, "Task added successfully!", "Smart Study Planner", JOptionPane.INFORMATION_MESSAGE);
            });

            deleteButton.addActionListener(event -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Select a task to delete.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                p.deletetask(selectedRow);
                model.setRowCount(0);
                for (int i = 0; i < p.tasks.size(); i++) {
                    StudyTask savedTask = p.tasks.get(i);
                    String taskType = savedTask instanceof ExamTask ? "Exam" : "Assignment";
                    model.addRow(new Object[]{i + 1, taskType, savedTask.subject, savedTask.hours});
                }
                status.setText("Task deleted successfully.");
                JOptionPane.showMessageDialog(frame, "Task deleted successfully!", "Smart Study Planner", JOptionPane.INFORMATION_MESSAGE);
            });

            RoundedPanel tableCard = new RoundedPanel(Color.WHITE, 24);
            tableCard.setLayout(new BorderLayout(0, 12));
            tableCard.setBorder(BorderFactory.createEmptyBorder(20, 22, 18, 22));
            JLabel tableTitle = new JLabel("Your study tasks");
            tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
            tableTitle.setForeground(text);
            JScrollPane tableScroll = new JScrollPane(table);
            tableScroll.setBorder(BorderFactory.createEmptyBorder());
            tableScroll.getViewport().setBackground(Color.WHITE);
            tableCard.add(tableTitle, BorderLayout.NORTH);
            tableCard.add(tableScroll, BorderLayout.CENTER);

            JPanel center = new JPanel(new BorderLayout(18, 18));
            center.setOpaque(false);
            center.add(form, BorderLayout.NORTH);
            center.add(tableCard, BorderLayout.CENTER);
            content.add(center, BorderLayout.CENTER);

            RoundedPanel bottom = new RoundedPanel(Color.WHITE, 20);
            bottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 10));
            bottom.setOpaque(false);
            bottom.setBorder(BorderFactory.createEmptyBorder(2, 12, 2, 12));
            bottom.add(status);
            bottom.add(deleteButton);
            content.add(bottom, BorderLayout.SOUTH);

            frame.setContentPane(content);
            frame.setVisible(true);
        });
    }
}
