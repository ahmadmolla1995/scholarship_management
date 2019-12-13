package TreeSetExample;

import jdk.internal.reflect.Reflection;

import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static <T> Set<T> unionTreeSets(Set<T> first, Set<T> second) {
        Set<T> unionSet = new TreeSet<T>(first);
        unionSet.addAll(second);
        return unionSet;
    }

    public static <T> Set<T> intersectionTreeSets(Set<T> first, Set<T> second) {
        Set<T> intersectionSet = new TreeSet<T>();
        for (T element : first)
            if (second.contains(element))
                intersectionSet.add(element);
        return intersectionSet;
    }

    public static void main(String[] args) {
        TreeSet<Character> first = new TreeSet<Character>();
        TreeSet<Character> second = new TreeSet<Character>();

        first.add('a');
        first.add('b');
        first.add('c');
        first.add('d');
        first.add('f');
        first.add('h');
        first.add('m');
        first.add('n');
        first.add('o');
        first.add('p');

        second.add('a');
        second.add('c');
        second.add('f');
        second.add('h');
        second.add('n');
        second.add('z');
        second.add('w');
        second.add('p');
        second.add('q');
        second.add('r');

        System.out.println("first:" + first);
        System.out.println("second: " + second);
        System.out.println("Union of first and second: " + unionTreeSets(first, second));
        System.out.println("Intersection of first and second: " + intersectionTreeSets(first, second));
    }
}
