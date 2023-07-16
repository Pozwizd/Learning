package hw3;

import java.lang.reflect.Field;
import java.util.*;


class JsonConverter {


    private static final int INDENT_LEVEL = 4;
    private static final String INDENT_SPACE = " ";


    public static String convertToJson(Object object, int indent) throws IllegalAccessException {
        if (object == null) {
            return "null";
        }

        if (object instanceof String) {
            return "\"" + object + "\"";
        }

        if (object instanceof Number || object instanceof Boolean) {
            return object.toString();
        }

        if (object instanceof Collection) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(System.lineSeparator());
            int i = 0;
            Iterator<?> iterator = ((Collection<?>) object).iterator();
            while (iterator.hasNext()) {
                sb.append(indentString(indent + INDENT_LEVEL));
                sb.append(convertToJson(iterator.next(), indent + INDENT_LEVEL));
                if (iterator.hasNext()) {
                    sb.append(",");
                }
                sb.append(System.lineSeparator());
                i++;
            }
            sb.append(indentString(indent));
            sb.append("]");
            return sb.toString();
        }

        if (object instanceof Iterable) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(System.lineSeparator());
            int i = 0;
            for (Object o : (Iterable<?>) object) {
                sb.append(indentString(indent + INDENT_LEVEL));
                sb.append(convertToJson(o, indent + INDENT_LEVEL));
                if (i != ((Iterable<?>) object).spliterator().estimateSize() - 1) {
                    sb.append(",");
                }
                sb.append(System.lineSeparator());
                i++;
            }
            sb.append(indentString(indent));
            sb.append("]");
            return sb.toString();
        }

        if (object instanceof Map) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append(System.lineSeparator());
            int i = 0;
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) object).entrySet()) {
                sb.append(indentString(indent + INDENT_LEVEL));
                sb.append("\"" + entry.getKey() + "\": ");
                sb.append(convertToJson(entry.getValue(), indent + INDENT_LEVEL));
                if (i != ((Map<?, ?>) object).size() - 1) {
                    sb.append(",");
                }
                sb.append(System.lineSeparator());
                i++;
            }
            sb.append(indentString(indent));
            sb.append("}");
            return sb.toString();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(System.lineSeparator());
        int i = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            sb.append(indentString(indent + INDENT_LEVEL));
            sb.append("\"" + field.getName() + "\": ");
            if (!field.getType().isPrimitive() && !field.getType().equals(String.class)) {
                sb.append(convertToJson(field.get(object), indent + INDENT_LEVEL));
            } else {
                sb.append(convertToJsonPrimitive(field.get(object)));
            }
            if (i != object.getClass().getDeclaredFields().length - 1) {
                sb.append(",");
            }
            sb.append(System.lineSeparator());
            i++;
        }
        sb.append(indentString(indent));
        sb.append("}");
        return sb.toString();
    }

    public static String convertToJson(Object object) throws IllegalAccessException {
        return convertToJson(object, 0);
    }


    private static String indentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append(INDENT_SPACE);
        }
        return sb.toString();
    }

    private static String convertToJsonPrimitive(Object object) {
        if (object == null) {
            return "null";
        }

        if (object instanceof Number || object instanceof Boolean) {
            return object.toString();
        }

        if (object instanceof String) {
            return "\"" + object + "\"";
        }

        return "";
    }
}

class Person {
    private String firstName;
    private String lastName;
    private int age;
    private boolean isMarried;
    private Address address;
    private List<String> phoneNumbers;
    private Map<String, String> emailAddresses;
    private Set<String> hobbies;

    public Person(String firstName, String lastName, int age, boolean isMarried, Address address, List<String> phoneNumbers, Map<String, String> emailAddresses, Set<String> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isMarried = isMarried;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.emailAddresses = emailAddresses;
        this.hobbies = hobbies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Map<String, String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Map<String, String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<String> hobbies) {
        this.hobbies = hobbies;
    }
}

class Address {
    private String street;
    private String city;
    private String state;
    private int zipCode;

    public Address(String street, String city, String state, int zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}

public class Task6 {
    public static void main(String[] args) throws IllegalAccessException {
        Address address = new Address("123 Main St", "Anytown", "CA", 123);
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("555-1234");
        phoneNumbers.add("555-5678");
        Map<String, String> emailAddresses = new HashMap<>();
        emailAddresses.put("home", "john.doe@example.com");
        emailAddresses.put("work", "jdoe@example.com");
        Set<String> hobbies = new HashSet<>();
        hobbies.add("reading");
        hobbies.add("hiking");
        Person person = new Person("John", "Doe", 30, true, address, phoneNumbers, emailAddresses, hobbies);


        String json = JsonConverter.convertToJson(person);
        System.out.println(json);
    }
}

