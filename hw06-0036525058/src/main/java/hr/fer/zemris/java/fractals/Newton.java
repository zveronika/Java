package hr.fer.zemris.java.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

/**
 * @author Veronika Žunar
 */
public class Newton {

    /**
     *
     */
    private static double CONVERGENCE_THRESHOLD = 1e-3;
    /**
     *
     */
    private static double ROOT_THRESHOLD = 2e-3;
    /**
     *
     */
    private static int MAX_ITER = 4096;

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
        System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");

        Scanner sc = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        String in;
        do {
            System.out.printf("Root %d> ", input.size() + 1);
            in = sc.nextLine().trim();
            if (!in.toLowerCase().equals("done"))
                input.add(in);
        } while (!in.toLowerCase().equals("done"));

        Complex[] roots = fromStringToComplex(input);

//		for (Complex r : roots)
//				System.out.println(r);

        System.out.println("Image of fractal will appear shortly. Thank you.");
        FractalViewer.show(new NewtonProducer(new ComplexRootedPolynomial(Complex.ONE, roots)));
        sc.close();

    }

    /**
     * @param input
     * @return
     */
    private static Complex[] fromStringToComplex(List<String> input) {
        Complex[] roots = new Complex[input.size()];
        double re, im;
        String reV, imV;

        int i = 0;

        List<String> newInput = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String root : input) {
            String[] nospace = root.split(" ");
            sb.setLength(0);
            for (int j = 0; j < nospace.length; j++) {
                sb.append(nospace[j]);
            }
            newInput.add(sb.toString());
        }

        for (String root : newInput) {
            int iIndex = root.indexOf("i");
            if (iIndex == -1) {
                if (root.startsWith("-")) {
                    reV = root.substring(1);
                    if (reV.isEmpty())
                        re = 0;
                    else
                        re = (-1) * Double.parseDouble(reV);
                } else if (root.startsWith("+")) {
                    reV = root.substring(1);
                    if (reV.isEmpty())
                        re = 0;
                    else
                        re = Double.parseDouble(reV);
                } else {
                    reV = root.substring(0);
                    if (reV.isEmpty())
                        re = 0;
                    else
                        re = Double.parseDouble(reV);
                }
                im = 0;

            } else if (iIndex == 0) {
                re = 0;
                imV = root.substring(1);
                if (imV.isEmpty())
                    im = 1;
                else
                    im = Double.parseDouble(imV);
            } else {
                if (root.startsWith("-")) {
                    reV = root.substring(1, iIndex - 1);
                    if (reV.isEmpty())
                        re = 0;
                    else
                        re = (-1) * Double.parseDouble(reV);
                } else {
                    reV = root.substring(0, iIndex - 1);
                    if (reV.isEmpty() || reV.equals("+"))
                        re = 0;
                    else
                        re = Double.parseDouble(reV);
                }

                if (root.charAt(iIndex - 1) == '-') {
                    imV = root.substring(iIndex + 1);
                    if (imV.isEmpty())
                        im = -1;
                    else
                        im = (-1) * Double.parseDouble(imV);
                } else {
                    imV = root.substring(iIndex + 1);
                    if (imV.isEmpty())
                        im = 1;
                    else
                        im = Double.parseDouble(imV);
                }
            }

            roots[i] = new Complex(re, im);
            System.out.println(roots[i]);
            i++;
        }
        return roots;
    }

    /**
     * @author Veronika Žunar
     */
    public static class NewtonProducer implements IFractalProducer {

        /**
         *
         */
        private ComplexRootedPolynomial rootedPolynomial;
        /**
         *
         */
        private ComplexPolynomial polynomial;

        /**
         * @param rootedPolynomial
         */
        public NewtonProducer(ComplexRootedPolynomial rootedPolynomial) {
            this.rootedPolynomial = rootedPolynomial;
            this.polynomial = rootedPolynomial.toComplexPolynom();
        }

        /**
         *
         */
        public void produce(double reMin, double reMax, double imMin, double imMax, int width, int height,
                            long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {
            System.out.println("Zapocinjem izracun...");
            short m = (short) (polynomial.order() + 1);
            int offset = 0;
            short[] data = new short[width * height];
            for (int y = 0; y < height; y++) {
                if (cancel.get())
                    break;
                for (int x = 0; x < width; x++) {
                    double cre = x / (width - 1.0) * (reMax - reMin) + reMin;
                    double cim = (height - 1.0 - y) / (height - 1) * (imMax - imMin) + imMin;
                    Complex zn = new Complex(cre, cim);
                    double module = 0;
                    int iters = 0;
                    do {
                        Complex numerator = polynomial.apply(zn);
                        Complex denominator = polynomial.derive().apply(zn);
                        Complex znold = zn;
                        Complex fraction = numerator.divide(denominator);
                        zn = zn.sub(fraction);
                        module = znold.sub(zn).module();
                        iters++;
                    } while (iters < MAX_ITER && module > CONVERGENCE_THRESHOLD);
                    int index = rootedPolynomial.indexOfClosestRootFor(zn, ROOT_THRESHOLD);
                    data[offset++] = (short) (index + 1);
                }
            }
            System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
            observer.acceptResult(data, m, requestNo);
        }
    }
}
