package hw2.Methods;

public class GenericsExample<T extends Number> {
    private T content;

    public GenericsExample(T content) {
        this.content = content;
    }

    public static void main(String[] args) {
        GenericsExample<Integer> intBox = new GenericsExample<>(123);
        GenericsExample<Double> doubleBox = new GenericsExample<>(3.14);
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}