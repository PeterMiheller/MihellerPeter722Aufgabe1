import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    public class Informationen{
        private int id;
        private String patientName;
        private String symphom;
        private String diagnose;
        private String date;
        private String ort;

        public Informationen(int id, String patientName, String symphom, String diagnose, String date, String ort) {
            this.id = id;
            this.patientName = patientName;
            this.symphom = symphom;
            this.diagnose = diagnose;
            this.date = date;
            this.ort = ort;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getDiagnose() {
            return diagnose;
        }

        public void setDiagnose(String diagnose) {
            this.diagnose = diagnose;
        }

        public String getSymphom() {
            return symphom;
        }

        public void setSymphom(String symphom) {
            this.symphom = symphom;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getOrt() {
            return ort;
        }

        public void setOrt(String ort) {
            this.ort = ort;
        }
    }

    public List<Informationen> lesenDateiXML(String dateiname) throws IOException {
        List<Informationen> eintraege = new ArrayList<>();
        try {
            // Initialize the XML parser
            File xmlFile = new File(dateiname);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Normalize the XML structure
            document.getDocumentElement().normalize();

            // Get all "Eintrag" elements
            NodeList nodeList = document.getElementsByTagName("Eintrag");

            // Iterate through each "Eintrag"
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Extract values from the XML
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent().trim());
                    String patient = element.getElementsByTagName("patient").item(0).getTextContent().trim();
                    String symptom = element.getElementsByTagName("symptom").item(0).getTextContent().trim();
                    String diagnose = element.getElementsByTagName("diagnose").item(0).getTextContent().trim();
                    String date = element.getElementsByTagName("date").item(0).getTextContent().trim();
                    String krankhaus = element.getElementsByTagName("krankhouse").item(0).getTextContent().trim();

                    // Create a new PunkteEintrag and add it to the list
                    eintraege.add(new Informationen(id, patient, symptom, diagnose, date,krankhaus));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Fehler beim Lesen der XML-Datei: " + e.getMessage());
        }
        return eintraege;
    }
    public static void main(String[] args) {

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.


    }
}

