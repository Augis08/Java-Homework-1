import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    public static int calculate(List<Integer> numbers) {
//        int total = 0;
//        for (int number : numbers) {
//            total += number;
//        }
        int total = numbers.stream()
                .reduce(0, (x, y) -> x + y);
        return total;
    }

    public static Set<String> getKidNames(List<Person> people) {
//        Set<String> kids = new HashSet<>();
//        for (Person person : people) {
//            if (person.getAge() < 18) {
//                kids.add(person.getName());
//            }
//        }
        Set<String> kids = people.stream()
                .filter(person -> person.getAge() < 18)
                .map(Person::getName)
                .collect(Collectors.toSet());
        return kids;
    }

    public static Person getOldestPerson(List<Person> people) {
//        Person oldestPerson = new Person("", 0);
//        for (Person person : people) {
//            if (person.getAge() > oldestPerson.getAge()) {
//                oldestPerson = person;
//            }
        Person oldestPerson = people.stream()
                .max(Comparator.comparing(Person::getAge))
                .get();

        return oldestPerson;
    }

    public static List<String> transform(List<List<String>> collection) {
//        List<String> newCollection = new ArrayList<>();
//        for (List<String> subCollection : collection) {
//            for (String value : subCollection) {
//                newCollection.add(value);
//            }
//        }
        List<String> newCollection = collection.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return newCollection;
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
//        Map<Boolean, List<Person>> map = new HashMap<>();
//        map.put(true, new ArrayList<>());
//        map.put(false, new ArrayList<>());
//        for (Person person : people) {
//            map.get(person.getAge() >= 18).add(person);
//        }
        Map<Boolean, List<Person>> map = new HashMap<>();
        map.put(true, new ArrayList<>());
        map.put(false, new ArrayList<>());
        people.forEach(person -> map.get(person.getAge() >= 18)
                .add(person));
        return map;
    }

    public static Map<String, List<Person>> groupByNationality(List<Person> people) {
//        Map<String, List<Person>> map = new HashMap<>();
//        for (Person person : people) {
//            if (!map.containsKey(person.getNationality())) {
//                map.put(person.getNationality(), new ArrayList<>());
//            }
//            map.get(person.getNationality()).add(person);
//        }
        Map<String, List<Person>> map = people.stream()
                .collect(Collectors.groupingBy(Person :: getNationality));
        return map;
    }

    public static String namesToString(List<Person> people) {
//        String label = "Names: ";
//        StringBuilder sb = new StringBuilder(label);
//        for (Person person : people) {
//            if (sb.length() > label.length()) {
//                sb.append(", ");
//            }
//            sb.append(person.getName());
//        }
//        sb.append(".");
        String sb = people.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "Names: ","."));
        return sb.toString();
    }

    public static String getString(List<Integer> list) {

        List<String> list1 = new ArrayList<>();
        for (Integer integer : list) {
            int i = integer;
            String a;
            if (i < 10) {
                a = "o" + i;
            } else {
                a = "e" + i;
            }
            list1.add(a);
        }

        String l = list1.stream()
                .collect(Collectors.joining(","));

        return l;
    }

    public static void main(String[] args){

        List <Integer> list = Arrays.asList(3,44);
        System.out.println(getString(list));


    }
}
