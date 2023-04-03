package hr.fer.oprpp1.hw04.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StudentDB {

    private static final String FILE = "./database.txt";

    public static void main(String[] args) {
        List<String> lines;
        StudentDatabase studentDatabase;
        try {
            lines = Files.readAllLines(Paths.get(FILE), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        try {
            studentDatabase = new StudentDatabase(lines);
        } catch (RuntimeException ex) {
            System.err.println(ex.getMessage());
            return;
        }

        if(studentDatabase == null) {
            System.out.println("Terminating program. Student Database is null.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("> ");
            String query = sc.nextLine().trim();

            if(query.toUpperCase().equals("EXIT")) {
                System.out.println("My work here is done!");
                break;
            }
            if(!query.toUpperCase().startsWith("QUERY") || query.length() < 5) {
                System.out.println("Invalid query command!");
                continue;
            }

            query = query.substring(5).trim();
            List<StudentRecord> fetchedRecords = new ArrayList<>();
            try {
                QueryParser parser = new QueryParser(query.trim());
                if (parser.isDirectQuery()) {
                    StudentRecord direct = studentDatabase.forJMBAG(parser.getQueriedJMBAG());
                    System.out.println("Using index for record retrieval.");
                }
                fetchedRecords.addAll(studentDatabase.filter(new QueryFilter(parser.getQuery())));
                if(fetchedRecords.size() > 0)
                    formatAndPrint(fetchedRecords);
                System.out.printf("Records selected: %d.\n", fetchedRecords.size());
            } catch (Exception e) {
                //System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }
        sc.close();
    }

    private static void formatAndPrint(List<StudentRecord> fetchedRecords) {
        Objects.requireNonNull(fetchedRecords);
        StringBuilder sb = new StringBuilder();
        int jmbagWidth = 0;
        int firstNameWidth = 0;
        int lastNameWidth = 0;
        for(StudentRecord sr : fetchedRecords) {
            if(sr.getJmbag().length() + 2 > jmbagWidth)
                jmbagWidth = sr.getJmbag().length() + 2;
            if(sr.getFirstName().length() + 2 > firstNameWidth)
                firstNameWidth = sr.getFirstName().length() + 2;
            if(sr.getLastName().length() + 2 > lastNameWidth)
                lastNameWidth = sr.getLastName().length() + 2;
        }
        border(sb, jmbagWidth, lastNameWidth, firstNameWidth, true);
        for(StudentRecord sr : fetchedRecords) {
            sb.append("| ").append(sr.getJmbag());
            int space = jmbagWidth - (sr.getJmbag().length() + 1);
            for(int i = 0; i < space; i++)
                sb.append(" ");
            sb.append("| ").append(sr.getLastName());
            space = lastNameWidth - (sr.getLastName().length() + 1);
            for(int i = 0; i < space; i++)
                sb.append(" ");
            sb.append("| ").append(sr.getFirstName());
            space = firstNameWidth - (sr.getFirstName().length() + 1);
            for(int i = 0; i < space; i++)
                sb.append(" ");
            sb.append("| ").append(sr.getFinalGrade()).append(" |").append("\n");
        }
        border(sb, jmbagWidth, lastNameWidth, firstNameWidth, false);
        System.out.println(sb.toString());
    }

    private static void border(StringBuilder sb, int jmbagWidth, int lastNameWidth, int firstNameWidth, boolean first) {
        sb.append("+");
        for(int i = 0; i < jmbagWidth; i++)
            sb.append("=");
        sb.append("+");
        for(int i = 0; i < lastNameWidth; i++)
            sb.append("=");
        sb.append("+");
        for(int i = 0; i < firstNameWidth; i++)
            sb.append("=");
        sb.append("+");
        for(int i = 0; i < 3; i++)
            sb.append("=");
        sb.append("+");

        if(first)
            sb.append("\n");
    }

}
