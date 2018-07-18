package com.wangrupeng.basic.queue.priority;

import java.util.*;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Customer> customers = new PriorityQueue<>(10);
        Random random = new Random();
        for (int i = 0;i < 10;i++) {
            customers.add(new Customer(new Integer(random.nextInt(100)), "customer-" + i));
        }

        for (int i = 0;i < 10;i++) {
            Customer customer = customers.poll();
            System.out.println(customer.getName() + " " +  customer.getId());
        }

        PriorityQueue<Customer> customers1 = new PriorityQueue<>(10, idComparator);
        for (int i = 0;i < 10;i++) {
            customers1.add(new Customer(new Integer(random.nextInt(100)), "customer-" + i));
        }

        for (int i = 0;i < 10;i++) {
            Customer customer = customers1.poll();
            System.out.println(customer.getName() + " " +  customer.getId());
        }
    }

    public static Comparator<Customer> idComparator = new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o2.getId() - o1.getId();
        }
    };
}
