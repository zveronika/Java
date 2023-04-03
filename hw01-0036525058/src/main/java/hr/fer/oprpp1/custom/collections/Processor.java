package hr.fer.oprpp1.custom.collections;

/**
 * The Processor is a model of an object capable of performing some operation on
 * the passed object.
 *
 * Class {@code Processor} represents an conceptual contract between clients
 * which will have objects to be processed, and each concrete {@code Processor}
 * which knows how to perform the selected operation. This idea allows us s to
 * write generic and reusable code.
 *
 * @author Veronika Žunar
 *
 */

public class Processor
{
    /**
     * Method representing performance of some operation on the passed object.
     *
     * Every class extending {@code Processor} must provide an implementation of
     * method {@code process}.
     *
     * @param value to be processed
     */
    public void process(Object value) {

    }
}
