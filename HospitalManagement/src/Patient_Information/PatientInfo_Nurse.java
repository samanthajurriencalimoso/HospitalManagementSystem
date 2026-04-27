package Patient_Information;


import javax.swing.*;
import java.awt.*;

/*Make it so that instead of JFrame. It should be JPanel.
    Make it a little bigger in size so that it accommodates the whole right side MAIN PANEL. 

*/


public class PatientInfo_Nurse extends JPanel {
    
    public PatientInfo_Nurse(){
        
        setLayout(null);
        setBounds(0, 0, 1620, 930);
       
        Font titleFont = new Font("Calibri", Font.BOLD, 16);
        Font bodyFont = new Font("Calibri", Font.PLAIN, 13);
                
        //patient profile card
        RoundedPanel profileCard = new RoundedPanel("#E9ECEE", Color.GRAY, 20, 1);
        profileCard.setBounds(30, 20, 1000, 120);
        profileCard.setLayout(null);
        add(profileCard);

        ImageIcon img = new ImageIcon(getClass().getResource("/resources/PATIENT.PHOTO.png"));
        Image imgScaled = img.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);

        JLabel pic = new JLabel(new ImageIcon(imgScaled));
        pic.setBounds(10, 20, 100, 100);
        profileCard.add(pic);

        
        JLabel name = new JLabel("Joshua Santos");
        name.setFont(new Font("Calibri", Font.BOLD, 18));
        name.setBounds(120, 20, 300, 25);
        profileCard.add(name);

        JLabel status = new JLabel("Status: Observation");
        status.setForeground(Color.RED);
        status.setBounds(120, 45, 200, 20);
        profileCard.add(status);

        JLabel info = new JLabel("ID: P-10021   |   Age: 29   |   Blood: O+");
        info.setBounds(120, 70, 400, 20);
        profileCard.add(info);

        JButton edit = new JButton("Edit");
        edit.setBounds(850, 30, 100, 30);
        profileCard.add(edit);

        JButton notes = new JButton("Notes");
        notes.setBounds(850, 70, 100, 30);
        profileCard.add(notes);

        //details panel
        RoundedPanel details = new RoundedPanel("#E9ECEE", Color.GRAY, 20, 1);
        details.setBounds(30, 160, 600, 200);
        details.setLayout(null);
        add(details);

        JLabel lblDetails = new JLabel("Patient Details");
        lblDetails.setFont(titleFont);
        lblDetails.setBounds(20, 10, 200, 20);
        details.add(lblDetails);

        JTextArea detailText = new JTextArea("Address: Binan, Laguna\n" +"Phone: +63 923 456 789");
        detailText.setFont(bodyFont);
        detailText.setBounds(20, 40, 550, 140);
        detailText.setEditable(false);
        detailText.setOpaque(false);
        details.add(detailText);
        
        //patient allergy
        JLabel lblAllergy = new JLabel("Allergies");
        lblAllergy.setBounds(20, 85, 150, 20);
        details.add(lblAllergy);

        JTextArea allergyText = new JTextArea("- Penicillin\n- Dust Allergy");
        allergyText.setBounds(20, 120, 170, 110);
        allergyText.setEditable(false);
        allergyText.setOpaque(false);
        allergyText.setFont(bodyFont);
        details.add(allergyText);

        // patient conditions
        JLabel lblCondition = new JLabel("Conditions");
        lblCondition.setBounds(210, 85, 150, 20);
        details.add(lblCondition);

        JTextArea conditionText = new JTextArea("- Diabetes\n- Hypertension");
        conditionText.setBounds(210, 120, 170, 110);
        conditionText.setEditable(false);
        conditionText.setOpaque(false);
        conditionText.setFont(bodyFont);
        details.add(conditionText);

        //patient medication
        JLabel lblMed = new JLabel("Medication Intake");
        lblMed.setBounds(400, 85, 180, 20);
        details.add(lblMed);

        JTextArea medText = new JTextArea("- Metformin (Daily)\n- Ibuprofen (As needed)");
        medText.setBounds(400, 120, 170, 110);
        medText.setEditable(false);
        medText.setOpaque(false);
        medText.setFont(bodyFont);
        details.add(medText);

        // doctor notes
        RoundedPanel doctorNotes = new RoundedPanel("#FFFFFF", Color.GRAY, 20, 1);
        doctorNotes.setBounds(30, 380, 600, 220);
        doctorNotes.setLayout(null);
        add(doctorNotes);

        JLabel lblNotes = new JLabel("Doctor's Notes");
        lblNotes.setFont(titleFont);
        lblNotes.setBounds(20, 10, 200, 20);
        doctorNotes.add(lblNotes);

        JTextArea notesText = new JTextArea("Patient recovering well post consultation.\n" +"Blood pressure stabilizing.\n\n" +"Recommendation:\nContinue medication and follow-up next week.");
        notesText.setFont(bodyFont);
        notesText.setBounds(20, 40, 550, 150);
        notesText.setEditable(false);
        notesText.setOpaque(false);
        doctorNotes.add(notesText);

        
        // patient vitals
        RoundedPanel vitals = new RoundedPanel("#A3CAE9", Color.BLACK, 20, 1);
        vitals.setBounds(650, 160, 380, 140);
        vitals.setLayout(null);
        add(vitals);

        JLabel lblVitals = new JLabel("Vitals Snapshot");
        lblVitals.setFont(titleFont);
        lblVitals.setBounds(20, 10, 200, 20);
        vitals.add(lblVitals);

        JTextArea vText = new JTextArea("BP: 120/80 mmHg\nHR: 72 bpm\nTemp: 36.8°C\nSpO2: 98%");
        vText.setFont(bodyFont);
        vText.setBounds(20, 40, 300, 80);
        vText.setEditable(false);
        vText.setOpaque(false);
        vitals.add(vText);
        
        // emergency contact
        RoundedPanel emergency = new RoundedPanel("#ACACAC", Color.BLACK, 20, 1);
        emergency.setBounds(650, 320, 380, 140);
        emergency.setLayout(null);
        add(emergency);

        JLabel lblEmergency = new JLabel("Emergency Contact");
        lblEmergency.setFont(titleFont);
        lblEmergency.setBounds(20, 10, 200, 20);
        emergency.add(lblEmergency);

        JTextArea eText = new JTextArea("Gabriella Santos\nMother\n+63 912 345 6789");
        eText.setFont(bodyFont);
        eText.setBounds(20, 40, 300, 80);
        eText.setEditable(false);
        eText.setOpaque(false);
        emergency.add(eText);

        //appointment
        RoundedPanel appointment = new RoundedPanel("#A3CAE9", Color.BLACK, 20, 1);
        appointment.setBounds(650, 480, 380, 120);
        appointment.setLayout(null);
        add(appointment);

        JLabel lblApp = new JLabel("Next Appointment");
        lblApp.setFont(titleFont);
        lblApp.setBounds(20, 10, 200, 20);
        appointment.add(lblApp);

        JTextArea appText = new JTextArea("Aug 27 - 10:00 AM\nGeneral Checkup");
        appText.setFont(bodyFont);
        appText.setBounds(20, 40, 300, 60);
        appText.setEditable(false);
        appText.setOpaque(false);
        appointment.add(appText);

    }
}
