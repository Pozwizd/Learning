package hw1.Behavioral.Iterator;

interface Iterator {
    boolean hacNext();

    Object next();
}

interface Collection {
    Iterator getIterator();
}

class JavaDevaloper implements Collection {
    String name;//fffh
    private String[] skills;

    public JavaDevaloper(String name, String[] skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public Iterator getIterator() {
        return new SkillIterator();
    }

    private class SkillIterator implements Iterator {
        int Index;

        @Override
        public boolean hacNext() {
            return Index < skills.length;
        }

        @Override
        public Object next() {
            return skills[Index++];
        }
    }
}

public class Example {
    public static void main(String[] args) {
        String[] skills = {"Java", "Spring"};
        JavaDevaloper javaDevaloper = new JavaDevaloper("Roman", skills);

        Iterator iterator = javaDevaloper.getIterator();
        System.out.println("Developer: " + javaDevaloper.getName());
        System.out.println("Skills: ");

        while (iterator.hacNext()) {
            System.out.println(iterator.next().toString() + " ");
        }
    }
}
