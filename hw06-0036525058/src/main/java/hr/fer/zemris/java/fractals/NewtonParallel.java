package hr.fer.zemris.java.fractals;

import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewtonParallel {

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

    private static int N = 0;
    private static int K = 0;

    private static ComplexRootedPolynomial rootedPolynomial;
    private static ComplexPolynomial polynomial;

    public static void main(String[] args) {
        System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
        System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");
        Scanner sc = new Scanner(System.in);

        if (args.length > 1) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("--workers=")) {
                    N = Integer.parseInt(args[i].substring(args[i].indexOf("=") + 1));
                } else if (args[i].equals("-w")) {
                    N = Integer.parseInt(args[i + 1]);
                    i++;
                } else if (args[i].startsWith("--tracks=")) {
                    K = Integer.parseInt(args[i].substring(args[i].indexOf("=") + 1));
                } else if (args[i].equals("-t")) {
                    N = Integer.parseInt(args[i + 1]);
                    i++;
                } else
                    throw new IllegalArgumentException("Invalid arguments are given.");
            }
        }
        if (N <= 0)
            N = Runtime.getRuntime().availableProcessors();
        if (K == 0)
            K = 4 * Runtime.getRuntime().availableProcessors();

        List<String> input = new ArrayList<>();
        String in;
        do {
            System.out.printf("Root %d> ", input.size() + 1);
            in = sc.nextLine().trim();
            if (!in.toLowerCase().equals("done"))
                input.add(in);
        } while (!in.toLowerCase().equals("done"));

        Complex[] roots = fromStringToComplex(input);

        rootedPolynomial = new ComplexRootedPolynomial(Complex.ONE, roots);
        polynomial = rootedPolynomial.toComplexPolynom();

        System.out.println("Image of fractal will appear shortly. Thank you.");
        FractalViewer.show(new NewtonParallelProducer(rootedPolynomial, polynomial));
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

            roots[i++] = new Complex(re, im);
        }
        return roots;
    }


    public static class PosaoIzracuna implements Runnable {
        double reMin;
        double reMax;
        double imMin;
        double imMax;
        int width;
        int height;
        int yMin;
        int yMax;
        int m;
        short[] data;
        AtomicBoolean cancel;
        public static PosaoIzracuna NO_JOB = new PosaoIzracuna();

        private PosaoIzracuna() {
        }

        public PosaoIzracuna(double reMin, double reMax, double imMin,
                             double imMax, int width, int height, int yMin, int yMax,
                             int m, short[] data, AtomicBoolean cancel) {
            super();
            this.reMin = reMin;
            this.reMax = reMax;
            this.imMin = imMin;
            this.imMax = imMax;
            this.width = width;
            this.height = height;
            this.yMin = yMin;
            this.yMax = yMax;
            this.m = m;
            this.data = data;
            this.cancel = cancel;
        }

        @Override
        public void run() {
            for (int y = yMin; y <= yMax; y++) {
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
                    data[y*height + x] = (short) (index + 1);
                }
            }
        }
    }


    /**
     *
     */
    public static class NewtonParallelProducer implements IFractalProducer {

        /**
         *
         */
        private ComplexRootedPolynomial rootedPolynomial;
        /**
         *
         */
        private ComplexPolynomial polynomial;

        private int workers;

        private int tracks;

        /**
         * @param rootedPolynomial
         * @param polynomial
         */
        public NewtonParallelProducer(ComplexRootedPolynomial rootedPolynomial, ComplexPolynomial polynomial) {
            this.rootedPolynomial = rootedPolynomial;
            this.polynomial = polynomial;
            this.workers = N;
            this.tracks = K;
        }

        /**
         *
         */
        public void produce(double reMin, double reMax, double imMin, double imMax, int width, int height,
                            long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {
            System.out.println("Zapocinjem izracun...");
            short m = (short) (polynomial.order() + 1);
            short[] data = new short[width * height];

            if (K < 1)
                K = 1;
            if (K > height)
                K = height;

            final int brojTraka = K;
            int brojYPoTraci = height / brojTraka;

            final BlockingQueue<PosaoIzracuna> queue = new LinkedBlockingQueue<>();

            Thread[] radnici = new Thread[N];
            for (int i = 0; i < radnici.length; i++) {
                radnici[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            PosaoIzracuna p = null;
                            try {
                                p = queue.take();
                                if (p == PosaoIzracuna.NO_JOB) break;
                            } catch (InterruptedException e) {
                                continue;
                            }
                            p.run();
                        }
                    }
                });
            }
            for (int i = 0; i < radnici.length; i++) {
                radnici[i].start();
            }

            for (int i = 0; i < brojTraka; i++) {
                int yMin = i * brojYPoTraci;
                int yMax = (i + 1) * brojYPoTraci - 1;
                if (i == brojTraka - 1) {
                    yMax = height - 1;
                }
                PosaoIzracuna posao = new PosaoIzracuna(reMin, reMax, imMin, imMax, width, height, yMin, yMax, m, data, cancel);
                while (true) {
                    try {
                        queue.put(posao);
                        break;
                    } catch (InterruptedException e) {
                    }
                }
            }
            for (int i = 0; i < radnici.length; i++) {
                while (true) {
                    try {
                        queue.put(PosaoIzracuna.NO_JOB);
                        break;
                    } catch (InterruptedException e) {
                    }
                }
            }

            for (int i = 0; i < radnici.length; i++) {
                while (true) {
                    try {
                        radnici[i].join();
                        break;
                    } catch (InterruptedException e) {
                    }
                }
            }

            System.out.println("Racunanje gotovo. Idem obavijestiti promatraca tj. GUI!");
            observer.acceptResult(data, m, requestNo);
        }

    }
}
