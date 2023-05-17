package it.camp.fifopriority.core;

import java.util.LinkedList;
import java.util.List;

/**
 * Kolejka priorytetowa.
 * Napisz klase it.camp.fifopriority.Core.FIFOPriority która przechowuje Stringi.
 * Kolejka ma działać zgodnie z zasadami kolejki FIFO
 * jednak elementy mają mieć priorytety.
 * Klasa posiada metodę push(String element, int priority).
 * Metoda dodaje element na koniec kolejki z zadanym priorytetem
 * (0 najmniejszy priorytet, 99 najwyższy priorytet).
 * Klasa posiada metodę popFirst() zwracającą pierwszego stringa w kolejce,
 * popHighThree() zwracającą jednego z 3 pierwszych Stringów -
 * tego o najwyższym priorytecie,
 * jeśli są dwa elementy o tym samym priorytecie
 * zwracany jest ten który stoi bliżej "wyjścia" z kolejki.
 * Klasa powinna posiadać również metodę popHighest()
 * zwracajacą stringa o najwyższym priorytecie w całej kolejce.
 * Jeśli jest wiecej niż jeden element o najwyższym priorytecie
 * metoda zwraca element który stoi najbliżej "wyjścia" z kolejki
 */
public class FIFOPriority {
    private static final FIFOPriority instance = new FIFOPriority();
    private final List<QueueElement> queue;

    private FIFOPriority() {
        this.queue = new LinkedList<>();
    }

    public void push(String element, int priority) {
        this.queue.add(new QueueElement(element, priority));
    }

    public String popFirst() {
        if (this.queue.isEmpty()) {
            return null;
        }
        QueueElement queueElement = queue.get(0);
        this.queue.remove(queueElement);
        return queueElement.getValue();
    }

    public String popHighThree() {
        if (this.queue.isEmpty()) {
            return null;
        }
        if (testFor1and2elementList() != null) {
            return testFor1and2elementList().getValue();
        }
        QueueElement max = this.queue.get(2);
        for (int i = 1; i >= 0; i--) {
            if (this.queue.get(i).getPriority() >= max.getPriority()) {
                max = this.queue.get(i);
            }
        }
        this.queue.remove(max);
        return max.getValue();
    }

    public String popHighest() {
        if (this.queue.isEmpty()) {
            return null;
        }
        if (testFor1and2elementList() != null) {
            return testFor1and2elementList().getValue();
        }
        QueueElement max = this.queue.get(this.queue.size() - 1);
        for (int i = this.queue.size() - 2; i >= 0; i--) {
            if (this.queue.get(i).getPriority() >= max.getPriority()) {
                max = this.queue.get(i);
            }
        }
        this.queue.remove(max);
        return max.getValue();
    }


    private QueueElement testFor1and2elementList() {
        if (this.queue.size() == 1) {
            QueueElement queueElement = queue.get(0);
            this.queue.remove(0);
            return queueElement;
        }
        if (this.queue.size() == 2) {
            QueueElement queueElement = this.queue.get(
                    (this.queue.get(0).getPriority() >= this.queue.get(1).getPriority() ? 0 : 1));
            this.queue.remove(queueElement);
            return queueElement;
        }
        return null;
    }

    public void listQueue() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queue.size(); i++) {
            sb.append(i + 1).append(". P:")
                    .append(queue.get(i).getPriority())
                    .append(" ")
                    .append(queue.get(i).getValue())
                    .append("\n");
        }
        System.out.println(sb);
    }

    public static FIFOPriority getInstance() {
        return instance;
    }

    private static class QueueElement {
        private final String value;
        private final int priority;

        private QueueElement(String value, int priority) {
            this.value = value;
            this.priority = setPriority(priority);
        }

        private String getValue() {
            return value;
        }

        private int getPriority() {
            return priority;
        }

        private int setPriority(int priority) {
            if (priority > 99) {
                return 99;
            }
            if (priority < 0) {
                return 0;
            }
            return priority;
        }
    }
}