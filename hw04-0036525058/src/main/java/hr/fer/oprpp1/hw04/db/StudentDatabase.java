package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class {@link StudentDatabase} represents a model of iterable list of
 * studentRecords filled with rows from database.txt. Main work is done in
 * constructor where mentioned list is filled with student records and their
 * uniqueness and right format is checked.
 * 
 * 
 * @author Veronika Žunar
 *
 */
public class StudentDatabase {

	/**
	 * Variable studentRecords represents an internal list of student records.
	 */
	private List<StudentRecord> studentRecords;

	/**
	 * Variable fastRetrieval represents a map of JMBAG (String) - index (Integer)
	 * values for faster check of existing jmbags in database.
	 */
	private Map<String, Integer> fastRetrieval;

	/**
	 * Public constructor for {@link StudentDatabase}. It must get a list of String
	 * objects (the content of database.txt, each string represents one row of the
	 * database file). It creates an internal list of student records. Additionally,
	 * it creates an index for fast retrieval of student records when jmbag is known
	 * (we used map for this). Also, constructor checks that previously mentioned
	 * conditions are satisfied (no duplicate jmbags, valid grades). If not, program
	 * will be terminated with appropriate message to user.
	 * 
	 * @param lines list of String objects - rows from "database.txt"
	 * @throws NullPointerException if given list is null
	 * 
	 */
	public StudentDatabase(List<String> lines) {
		if (lines == null)
			throw new NullPointerException("Read lines are null !");

		this.studentRecords = new ArrayList<>();
		this.fastRetrieval = new HashMap<>();

		Integer index = 0;
		for (String line : lines) {
			String[] attributes = line.split("\t");
			if (attributes.length != 4) {
				throw new StudentDatabaseException(
						"Format of line in database is not acceptable - there are not 4 elements !");
			}

			String jmbag = attributes[0].trim();
			if (fastRetrieval.containsKey(jmbag)) {
				throw new StudentDatabaseException("Given JMBAG already exists !");
			}

			String lastName = attributes[1].trim();
			String firstName = attributes[2].trim();

			int tryGrade = Integer.parseInt(attributes[3].trim());
			try {
				if (tryGrade < 1 || tryGrade > 5)
					throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				throw new StudentDatabaseException("Final grade is not in the given range [1, 5]!");
			}
			int finalGrade = tryGrade;

			studentRecords.add(new StudentRecord(jmbag, lastName, firstName, finalGrade));
			fastRetrieval.put(jmbag, index);
			index++;
		}
	}

	/**
	 * This method uses index to obtained requested record. It uses fastRetrieval
	 * Map, and if record does not exists (method get then returns null), our method
	 * also returns null.
	 * 
	 * 
	 * This operation is done in O(1) complexity.
	 * 
	 * @param jmbag
	 * @return
	 */
	public StudentRecord forJMBAG(String jmbag) {
		Integer index = fastRetrieval.get(jmbag);
		if (index == null)
			return null;
		return studentRecords.get(index);
	}

	/**
	 * The method filter loops through all student records in its internal list. It
	 * calls accepts method on given filter-object with current record. Each record
	 * for which accepts returns true is added to temporary list and this list is
	 * then returned by the filter method
	 * 
	 * @param filter checks if record is acceptable
	 * @return list of StudentRecords that are accepted by filter
	 */
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> filteredRecords = new ArrayList<>();
		for (StudentRecord r : studentRecords) {
			if (filter.accepts(r))
				filteredRecords.add(r);
		}
		return filteredRecords;
	}
}
