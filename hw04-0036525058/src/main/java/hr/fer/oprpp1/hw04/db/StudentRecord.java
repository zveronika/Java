package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**
 * Class {@link StudentRecord} represents info for each student in database.
 * Instances of this class represent records for each student. There can not
 * exist multiple records for the same student.
 *
 * @author Veronika Žunar
 *
 */
public class StudentRecord {

    /**
     * Variable jmbag represents unique ID for each student in database.
     */
    private String jmbag;

    /**
     * Variable lastName assigned to a student represents his last name.
     */
    private String lastName;

    /**
     * Variable firstName assigned to a student represents his first name.
     */
    private String firstName;

    /**
     * Variable finalGrade assigned to a student represents his final grade.
     */
    private int finalGrade;

    /**
     * Constructor for StudentRecord instance. Every instance consists of following
     * informations for a certain student: jmbag, lastName, firstName and
     * finalGrade.
     *
     * @param jmbag      for a certain student
     * @param firstname  which belongs to same student
     * @param lastname   which belongs to same student
     * @param finalGrade assigned to same student
     */
    public StudentRecord(String jmbag, String lastname, String firstname, int finalGrade) {
        this.jmbag = jmbag;
        this.lastName = lastname;
        this.firstName = firstname;
        this.finalGrade = finalGrade;
    }

    /**
     * Getter for student's jmbag from the record.
     *
     * @return the jmbag
     */
    public String getJmbag() {
        return jmbag;
    }

    /**
     * Getter for student's last name from the record.
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for student's first name from the record.
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for student's final grade from the record.
     *
     * @return the finalGrade
     */
    public int getFinalGrade() {
        return finalGrade;
    }

    /**
     * Overridden method that returns a hash code for certain student generated from
     * his jmbag.
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(jmbag);
    }

    /**
     * Overridden method equals so that the two students are treated as equal if
     * jmbags are equal using their hash codes.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentRecord other = (StudentRecord) obj;
        return Objects.equals(jmbag, other.jmbag);
    }

    @Override
    public String toString() {
        return "StudentRecord{" +
                "jmbag='" + jmbag + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", finalGrade=" + finalGrade +
                '}';
    }
}
