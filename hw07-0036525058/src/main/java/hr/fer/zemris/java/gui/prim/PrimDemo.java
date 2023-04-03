package hr.fer.zemris.java.gui.prim;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrimDemo extends JFrame {

    public PrimDemo() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        initGUI();
    }

    private static class PrimListModel implements ListModel<Integer> {

        private java.util.List<Integer> values;
        private List<ListDataListener> listeners;

        public PrimListModel() {
            this.values = new ArrayList<>();
            this.listeners = new ArrayList<>();
            values.add(1);
        }

        @Override
        public int getSize() {
            return values.size();
        }

        @Override
        public Integer getElementAt(int index) {
            return values.get(index);
        }

        @Override
        public void addListDataListener(ListDataListener l) {
            listeners.add(l);
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
            listeners.remove(l);
        }

        public void next() {
            int pos = values.size();
            int val = values.get(pos - 1);
            do {
                val++;
            } while (!isPrime(val));

            values.add(val);

            ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, pos, pos);
            for (ListDataListener l : listeners) {
                l.intervalAdded(event);
            }
        }

        public boolean isPrime(int num) {
            boolean flag = true;
            for (int i = 2; i <= num / 2; ++i) {
                if (num % i == 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }

    private void initGUI() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        PrimListModel model = new PrimListModel();

        JList<Integer> list1 = new JList<>(model);
        JList<Integer> list2 = new JList<>(model);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 0));
        JButton next = new JButton("Sljedeći");
        next.addActionListener( e -> {
            model.next();
        });
        bottomPanel.add(next);

        JPanel central = new JPanel(new GridLayout(1, 0));
        central.add(new JScrollPane(list1));
        central.add(new JScrollPane(list2));

        cp.add(central, BorderLayout.CENTER);
        cp.add(bottomPanel, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PrimDemo().setVisible(true);
        });
    }
}
