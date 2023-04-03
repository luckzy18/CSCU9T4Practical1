// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField sport=new JTextField(5);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labSport=new JLabel("Stroke:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton remove=new JButton("Remove");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("find all");
    private JPanel sportInfo=new JPanel();
    String[] ar={"Swimming","Running","Cycling"};
    private JComboBox sportSelection=new JComboBox(ar);

    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        findAllByDate.addActionListener(this);
        add(findAllByDate);
        remove.addActionListener(this);
        add(remove);
        sportSelection.addActionListener(this);
        add(sportSelection);
        sportInfo.add(labSport);
        sportInfo.add(sport);
        add(sportInfo);

        lookUpByDate.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate){
            message= findAllByDate();
        }
        if (event.getSource() == sportSelection){
           display(sportSelection.getSelectedItem());
           return;
        }
        if (event.getSource() == remove){

            message= remove();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String remove(){
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        String n = name.getText();
        outputArea.setText("looking up record ...");
        String message=myAthletes.delete(n,d,m,y);
        return message;
    }

    public void display(Object sport){
        String selectedSport= (String) sport;
        if(sport.equals("Swimming")){
            labSport.setText("Stroke");
        }else if(sport.equals("Cycling")){
            labSport.setText("incline");
        }else if(sport.equals("Running")){
            labSport.setText("incline");
        }
    }
    public String addEntry(String what) {
        String message;
        System.out.println("Trying to add "+what+" entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        String selectedSport=(String) sportSelection.getSelectedItem();
        Entry e=new Entry(n, d, m, y, h, mm, s, km);
        if(selectedSport.equals("Swimming")){
            String stroke=sport.getText();
            e=new Swim(n, d, m, y, h, mm, s, km,stroke);
        }else if(selectedSport.equals("Cycling")){
            double incline=Double.parseDouble(sport.getText());
            e=new Cycle(n, d, m, y, h, mm, s, km,incline);
        }else if(selectedSport.equals("Cycling")){
            double incline=Double.parseDouble(sport.getText());
            e=new Run(n, d, m, y, h, mm, s, km,incline);
        }
        message=myAthletes.addEntry(e);
        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public String findAllByDate(){
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up records ...");
        String message = myAthletes.findAllByDate(d, m, y);
        return message;
    }
    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        sport.setText("");
    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

