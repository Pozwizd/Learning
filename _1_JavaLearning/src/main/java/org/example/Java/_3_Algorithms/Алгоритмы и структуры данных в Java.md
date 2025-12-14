## Введение

**Алгоритмы и структуры данных** — это основа computer science и эффективного программирования. Правильный выбор структуры данных и алгоритма может улучшить производительность приложения в сотни и тысячи раз.[^1][^2][^3][^4]

### Зачем изучать алгоритмы?

- **Эффективность**: правильный алгоритм может выполнять задачу за миллисекунды вместо часов
- **Масштабируемость**: код должен работать не только на малых данных, но и на миллионах записей
- **Собеседования**: алгоритмические задачи — стандарт технических интервью в IT
- **Понимание**: глубокое знание того, как работают встроенные структуры данных Java[^1][^2][^3][^4]


## 1. Анализ сложности алгоритмов

### 1.1 Big O нотация

**Big O нотация** — это математический способ описания того, как время выполнения или потребление памяти алгоритма растёт с увеличением размера входных данных. Она описывает **наихудший случай** выполнения.[^5][^6]

### 1.2 Основные типы сложности

**O(1) — Константная сложность**

Время выполнения не зависит от размера входных данных.[^5][^6]

```java
// Доступ к элементу массива по индексу
int[] arr = {1, 2, 3, 4, 5};
int element = arr[^3]; // O(1) - всегда одна операция

// HashMap get
HashMap<String, Integer> map = new HashMap<>();
int value = map.get("key"); // O(1) в среднем
```

**O(log n) — Логарифмическая сложность**

Время растёт логарифмически. При удвоении данных время увеличивается на константу. Типичный пример — бинарный поиск.[^6][^5]

```java
// Бинарный поиск в отсортированном массиве
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1; // не найден
}
// Сложность: O(log n)
// На каждом шаге делим массив пополам
```

**O(n) — Линейная сложность**

Время выполнения пропорционально размеру входных данных.[^5][^6]

```java
// Поиск элемента в массиве
public int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) return i;
    }
    return -1;
}
// Сложность: O(n) - в худшем случае проверяем все элементы

// Сумма элементов массива
public int sum(int[] arr) {
    int total = 0;
    for (int num : arr) {
        total += num;
    }
    return total;
}
```

**O(n log n) — Линейно-логарифмическая сложность**

Типична для эффективных алгоритмов сортировки: Merge Sort, Quick Sort.[^7][^8][^5]

```java
// Merge Sort
public void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        
        mergeSort(arr, left, mid);        // O(log n) уровней рекурсии
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);     // O(n) на каждом уровне
    }
}
// Общая сложность: O(n log n)
```

**O(n²) — Квадратичная сложность**

Время выполнения пропорционально квадрату размера входных данных. Характерна для вложенных циклов.[^8][^7][^5]

```java
// Bubble Sort - пузырьковая сортировка
public void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {          // O(n)
        for (int j = 0; j < n - i - 1; j++) {  // O(n)
            if (arr[j] > arr[j + 1]) {
                // swap
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
// Сложность: O(n²)
```

**O(2^n) — Экспоненциальная сложность**

Крайне неэффективна. Каждое добавление элемента удваивает время выполнения. Типична для наивных рекурсивных решений.

```java
// Наивное вычисление чисел Фибоначчи
public int fibonacci(int n) {
    if (n <= 1) return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
}
// Сложность: O(2^n) - катастрофически медленно для больших n
```

**O(n!) — Факториальная сложность**

Самая высокая степень роста. Встречается при переборе всех перестановок.

### 1.3 Пространственная сложность

Пространственная сложность описывает, сколько дополнительной памяти требует алгоритм.[^5]

```java
// O(1) space - константная память
public int findMax(int[] arr) {
    int max = arr[^0];
    for (int num : arr) {
        if (num > max) max = num;
    }
    return max;
}

// O(n) space - линейная память
public int[] copyArray(int[] arr) {
    int[] copy = new int[arr.length]; // выделяем память
    for (int i = 0; i < arr.length; i++) {
        copy[i] = arr[i];
    }
    return copy;
}
```


## 2. Массивы и строки

### 2.1 Массивы в Java

Массив — базовая структура данных с фиксированным размером и O(1) доступом по индексу.[^3][^4]

```java
// Создание массива
int[] numbers = new int[^5];           // массив из 5 элементов
int[] primes = {2, 3, 5, 7, 11};      // инициализация

// Операции
numbers[^0] = 10;          // O(1) - доступ/изменение
int first = numbers[^0];   // O(1)
int length = numbers.length; // O(1)
```

**Преимущества**: быстрый доступ O(1), компактное хранение
**Недостатки**: фиксированный размер, медленная вставка/удаление O(n)

### 2.2 Техника Two Pointers

Использование двух указателей для эффективного решения задач на массивах.

```java
// Задача: проверить, является ли строка палиндромом
public boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    
    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}
// Сложность: O(n) time, O(1) space

// Задача: найти пару чисел с заданной суммой в отсортированном массиве
public int[] twoSum(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left < right) {
        int sum = arr[left] + arr[right];
        if (sum == target) {
            return new int[]{left, right};
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    return new int[]{-1, -1};
}
// Сложность: O(n)
```


### 2.3 Sliding Window (Скользящее окно)

Техника для задач на подмассивы/подстроки с условиями.

```java
// Задача: максимальная сумма подмассива размера k
public int maxSumSubarray(int[] arr, int k) {
    int maxSum = 0;
    int windowSum = 0;
    
    // Первое окно
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }
    maxSum = windowSum;
    
    // Скользим окном
    for (int i = k; i < arr.length; i++) {
        windowSum += arr[i] - arr[i - k]; // добавляем новый, убираем старый
        maxSum = Math.max(maxSum, windowSum);
    }
    
    return maxSum;
}
// Сложность: O(n) вместо O(n*k) наивного подхода
```


### 2.4 Prefix Sum

Предварительное вычисление кумулятивных сумм для быстрых запросов на диапазонах.

```java
// Подготовка prefix sum
public int[] buildPrefixSum(int[] arr) {
    int[] prefix = new int[arr.length + 1];
    for (int i = 0; i < arr.length; i++) {
        prefix[i + 1] = prefix[i] + arr[i];
    }
    return prefix;
}

// Сумма элементов от индекса left до right за O(1)
public int rangeSum(int[] prefix, int left, int right) {
    return prefix[right + 1] - prefix[left];
}
```


## 3. Связные списки

### 3.1 Односвязный список (Singly Linked List)

```java
class ListNode {
    int val;
    ListNode next;
    
    ListNode(int val) {
        this.val = val;
    }
}

class LinkedList {
    private ListNode head;
    
    // Добавление в начало - O(1)
    public void addFirst(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
    }
    
    // Поиск - O(n)
    public boolean contains(int val) {
        ListNode current = head;
        while (current != null) {
            if (current.val == val) return true;
            current = current.next;
        }
        return false;
    }
    
    // Удаление узла - O(n)
    public void remove(int val) {
        if (head == null) return;
        
        if (head.val == val) {
            head = head.next;
            return;
        }
        
        ListNode current = head;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }
}
```


### 3.2 Fast \& Slow Pointers (Floyd's Cycle Detection)

```java
// Поиск цикла в связном списке
public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;        // медленный на 1 шаг
        fast = fast.next.next;   // быстрый на 2 шага
        
        if (slow == fast) {
            return true; // встретились - есть цикл
        }
    }
    return false;
}

// Найти середину списка
public ListNode findMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow; // slow будет в середине
}
```


### 3.3 Разворот связного списка

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode current = head;
    
    while (current != null) {
        ListNode nextTemp = current.next;
        current.next = prev;
        prev = current;
        current = nextTemp;
    }
    
    return prev; // новая голова
}
// Сложность: O(n) time, O(1) space
```


## 4. Стек и очередь

### 4.1 Stack (LIFO — Last In, First Out)

Стек работает по принципу "последний вошел — первый вышел", как стопка тарелок.[^9][^10]

```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();

// Основные операции
stack.push(1);        // Добавить на вершину - O(1)
stack.push(2);
stack.push(3);

int top = stack.peek();  // Посмотреть верхний элемент - O(1)
int removed = stack.pop();  // Удалить и вернуть верхний - O(1)
boolean empty = stack.isEmpty(); // Проверка на пустоту - O(1)
```

**Применение стека: проверка сбалансированности скобок**

```java
public boolean isValidParentheses(String s) {
    Stack<Character> stack = new Stack<>();
    
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '{' || c == '[') {
            stack.push(c);
        } else {
            if (stack.isEmpty()) return false;
            
            char top = stack.pop();
            if (c == ')' && top != '(') return false;
            if (c == '}' && top != '{') return false;
            if (c == ']' && top != '

### 4.2 Queue (FIFO — First In, First Out)

Очередь работает по принципу "первый вошел — первый вышел", как очередь в магазине.[^9][^10]

```java
import java.util.Queue;
import java.util.LinkedList;

Queue<Integer> queue = new LinkedList<>();

// Основные операции
queue.offer(1);  // Добавить в конец - O(1)
queue.offer(2);
queue.offer(3);

int front = queue.peek();  // Посмотреть первый элемент - O(1)
int removed = queue.poll(); // Удалить и вернуть первый - O(1)
boolean empty = queue.isEmpty();
```

**Применение: BFS (Breadth-First Search)**

```java
public void bfs(TreeNode root) {
    if (root == null) return;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        System.out.print(node.val + " ");
        
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```


### 4.3 Priority Queue (Heap)

Очередь с приоритетом — элементы извлекаются по приоритету, а не по порядку добавления.

```java
import java.util.PriorityQueue;

// Min-Heap (по умолчанию)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(5);
minHeap.offer(2);
minHeap.offer(8);
System.out.println(minHeap.poll()); // 2 (минимальный)

// Max-Heap (с компаратором)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
maxHeap.offer(5);
maxHeap.offer(2);
maxHeap.offer(8);
System.out.println(maxHeap.poll()); // 8 (максимальный)
```


## 5. Хеш-таблицы

### 5.1 HashMap — принцип работы

HashMap использует хеш-функцию для преобразования ключа в индекс массива, обеспечивая O(1) доступ в среднем случае.[^11][^12][^13]

```java
HashMap<String, Integer> map = new HashMap<>();

// Основные операции
map.put("Alice", 25);      // O(1) average
map.put("Bob", 30);
map.put("Charlie", 35);

int age = map.get("Alice");          // O(1) average
boolean exists = map.containsKey("Bob"); // O(1) average
map.remove("Charlie");               // O(1) average

// Перебор
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```


### 5.2 Коллизии и их разрешение

**Коллизия** возникает, когда два разных ключа имеют одинаковый хеш-код.[^11][^14][^13][^15]

**Методы разрешения:**

1. **Метод цепочек (Chaining)**: элементы с одинаковым хешем хранятся в связном списке
2. **Открытая адресация**: при коллизии ищется следующая свободная ячейка

В Java 8+ HashMap использует цепочки, но если цепочка становится слишком длинной (>8 элементов), она превращается в сбалансированное дерево для улучшения производительности до O(log n).[^12][^13][^11]

### 5.3 Применение HashMap

```java
// Подсчет частоты элементов
public Map<Character, Integer> countFrequency(String s) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char c : s.toCharArray()) {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
    return freq;
}

// Two Sum задача
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    return new int[]{-1, -1};
}
// Сложность: O(n) вместо O(n²) наивного подхода
```


## 6. Деревья

### 6.1 Бинарное дерево

Бинарное дерево — иерархическая структура, где каждый узел имеет максимум два потомка.[^16][^17][^18][^19]

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}
```


### 6.2 Обходы дерева

**Inorder (левый - корень - правый)**

```java
public void inorder(TreeNode root) {
    if (root == null) return;
    
    inorder(root.left);
    System.out.print(root.val + " ");
    inorder(root.right);
}
// Для BST дает отсортированный порядок
```

**Preorder (корень - левый - правый)**

```java
public void preorder(TreeNode root) {
    if (root == null) return;
    
    System.out.print(root.val + " ");
    preorder(root.left);
    preorder(root.right);
}
```

**Postorder (левый - правый - корень)**

```java
public void postorder(TreeNode root) {
    if (root == null) return;
    
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.val + " ");
}
```

**Level-order (по уровням) — использует BFS**

```java
public void levelOrder(TreeNode root) {
    if (root == null) return;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        System.out.print(node.val + " ");
        
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```


### 6.3 Binary Search Tree (BST)

BST — бинарное дерево, где для каждого узла: все элементы левого поддерева меньше узла, все элементы правого — больше.[^16][^17][^18][^20]

```java
class BST {
    TreeNode root;
    
    // Вставка - O(log n) average, O(n) worst
    public TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        }
        
        return node;
    }
    
    // Поиск - O(log n) average, O(n) worst
    public boolean search(TreeNode node, int val) {
        if (node == null) return false;
        
        if (val == node.val) return true;
        if (val < node.val) return search(node.left, val);
        return search(node.right, val);
    }
}
```


### 6.4 AVL дерево

AVL-дерево — самобалансирующееся BST, где разница высот левого и правого поддеревьев любого узла не превышает 1.[^16][^20][^21]

**Преимущества**: гарантированный O(log n) для всех операций
**Применение**: когда критична предсказуемая производительность

### 6.5 Heap (куча)

Heap — полное бинарное дерево, где каждый родитель либо больше (Max-Heap), либо меньше (Min-Heap) своих потомков.

```java
// Min-Heap в Java через PriorityQueue
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Операции - O(log n)
minHeap.offer(5);
minHeap.offer(3);
minHeap.offer(8);

int min = minHeap.peek();  // 3 (минимум)
int removed = minHeap.poll(); // Извлечь минимум
```

**Применение**:

- Priority Queue
- Heap Sort
- Top K элементов
- Median нахождение


## 7. Графы

### 7.1 Представление графов

**Список смежности** (предпочтительно для разреженных графов).[^22][^23]

```java
// Граф как HashMap с списками соседей
Map<Integer, List<Integer>> graph = new HashMap<>();

// Добавление ребра
public void addEdge(int from, int to) {
    graph.putIfAbsent(from, new ArrayList<>());
    graph.putIfAbsent(to, new ArrayList<>());
    graph.get(from).add(to);
    // Для неориентированного графа:
    // graph.get(to).add(from);
}
```

**Матрица смежности** (для плотных графов).

```java
int[][] adjMatrix = new int[n][n];
// adjMatrix[i][j] = 1 если есть ребро от i к j
```


### 7.2 DFS (Depth-First Search) — поиск в глубину

```java
```

public void dfs(int node, Set<Integer> visited, Map<Integer, List<Integer>> graph) {

```
    visited.add(node);
    System.out.print(node + " ");
    
    for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
        if (!visited.contains(neighbor)) {
            dfs(neighbor, visited, graph);
        }
    }
}
// Сложность: O(V + E), где V - вершины, E - рёбра
```

**Применение DFS**:

- Поиск путей
- Топологическая сортировка
- Проверка связности
- Поиск компонент связности


### 7.3 BFS (Breadth-First Search) — поиск в ширину

```java
public void bfs(int start, Map<Integer, List<Integer>> graph) {
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    
    queue.offer(start);
    visited.add(start);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.print(node + " ");
        
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
    }
}
// Сложность: O(V + E)
```

**Применение BFS**:

- Кратчайший путь в невзвешенном графе
- Поиск ближайших узлов
- Level-order обход[^22][^23]


### 7.4 Алгоритм Дейкстры

Находит кратчайшие пути от одной вершины до всех остальных в графе с неотрицательными весами.[^22][^23][^24]

```java
public Map<Integer, Integer> dijkstra(int start, Map<Integer, Map<Integer, Integer>> graph) {
    Map<Integer, Integer> distances = new HashMap<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[^1] - b[^1]);
    
    pq.offer(new int[]{start, 0});
    distances.put(start, 0);
    
    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int node = current[^0];
        int dist = current[^1];
        
        if (dist > distances.getOrDefault(node, Integer.MAX_VALUE)) continue;
        
        for (Map.Entry<Integer, Integer> neighbor : graph.getOrDefault(node, new HashMap<>()).entrySet()) {
            int nextNode = neighbor.getKey();
            int weight = neighbor.getValue();
            int newDist = dist + weight;
            
            if (newDist < distances.getOrDefault(nextNode, Integer.MAX_VALUE)) {
                distances.put(nextNode, newDist);
                pq.offer(new int[]{nextNode, newDist});
            }
        }
    }
    
    return distances;
}
// Сложность: O((V + E) log V) с приоритетной очередью
```


## 8. Алгоритмы сортировки

### 8.1 Простые сортировки O(n²)

**Bubble Sort (пузырьковая)**

```java
public void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // swap
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
// Сложность: O(n²), простая, но неэффективная
```

**Selection Sort (сортировка выбором)**

```java
public void selectionSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        // swap
        int temp = arr[minIdx];
        arr[minIdx] = arr[i];
        arr[i] = temp;
    }
}
```

**Insertion Sort (сортировка вставками)**

```java
public void insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        int key = arr[i];
        int j = i - 1;
        
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}
// Эффективна для почти отсортированных данных
```


### 8.2 Эффективные сортировки O(n log n)

**Merge Sort (сортировка слиянием)**

```java
public void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

private void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    
    int[] L = new int[n1];
    int[] R = new int[n2];
    
    for (int i = 0; i < n1; i++) L[i] = arr[left + i];
    for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];
    
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k++] = L[i++];
        } else {
            arr[k++] = R[j++];
        }
    }
    
    while (i < n1) arr[k++] = L[i++];
    while (j < n2) arr[k++] = R[j++];
}
// Сложность: O(n log n) гарантированно, стабильная
```

**Quick Sort (быстрая сортировка)**

```java
public void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

private int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    
    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            // swap
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    
    return i + 1;
}
// Сложность: O(n log n) average, O(n²) worst
// На практике быстрее Merge Sort
```


### 8.3 Встроенная сортировка Java

```java
int[] arr = {5, 2, 8, 1, 9};

// Для примитивов - Dual-Pivot QuickSort
Arrays.sort(arr); // O(n log n)

// Для объектов - TimSort (гибрид Merge Sort и Insertion Sort)
Integer[] objArr = {5, 2, 8, 1, 9};
Arrays.sort(objArr);

// С компаратором
Arrays.sort(objArr, (a, b) -> b - a); // по убыванию
```


## 9. Рекурсия и Backtracking

### 9.1 Основы рекурсии

```java
// Факториал
public int factorial(int n) {
    if (n <= 1) return 1;  // base case
    return n * factorial(n - 1); // recursive case
}

// Fibonacci
public int fib(int n) {
    if (n <= 1) return n;
    return fib(n - 1) + fib(n - 2);
}
// Сложность: O(2^n) - очень медленно!
```


### 9.2 Backtracking

Backtracking — техника перебора с откатом при неудачном выборе.

**Генерация всех подмножеств**

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, 0);
    return result;
}

```

private void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {

```
    result.add(new ArrayList<>(temp));
    
    for (int i = start; i < nums.length; i++) {
        temp.add(nums[i]);           // choose
        backtrack(result, temp, nums, i + 1); // explore
        temp.remove(temp.size() - 1); // unchoose (backtrack)
    }
}
```

**Генерация перестановок**

```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums);
    return result;
}

```

private void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums) {

```
    if (temp.size() == nums.length) {
        result.add(new ArrayList<>(temp));
        return;
    }
    
    for (int num : nums) {
        if (temp.contains(num)) continue;
        temp.add(num);
        backtrack(result, temp, nums);
        temp.remove(temp.size() - 1);
    }
}
```


## 10. Динамическое программирование

Динамическое программирование (DP) — метод решения сложных задач путем разбиения их на более простые подзадачи.[^25][^26]

**Ключевые свойства**:

1. **Overlapping subproblems** — подзадачи повторяются
2. **Optimal substructure** — оптимальное решение состоит из оптимальных решений подзадач

### 10.1 Fibonacci с мемоизацией

```java
// Наивная рекурсия - O(2^n)
public int fibNaive(int n) {
    if (n <= 1) return n;
    return fibNaive(n - 1) + fibNaive(n - 2);
}

// Мемоизация (Top-Down DP) - O(n)
public int fibMemo(int n) {
    int[] memo = new int[n + 1];
    Arrays.fill(memo, -1);
    return fibHelper(n, memo);
}

private int fibHelper(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != -1) return memo[n];
    
    memo[n] = fibHelper(n - 1, memo) + fibHelper(n - 2, memo);
    return memo[n];
}

// Табуляция (Bottom-Up DP) - O(n)
public int fibDP(int n) {
    if (n <= 1) return n;
    
    int[] dp = new int[n + 1];
    dp[^0] = 0;
    dp[^1] = 1;
    
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    
    return dp[n];
}
```


### 10.2 Задача о рюкзаке (0/1 Knapsack)

```java
public int knapsack(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    int[][] dp = new int[n + 1][capacity + 1];
    
    for (int i = 1; i <= n; i++) {
        for (int w = 1; w <= capacity; w++) {
            if (weights[i - 1] <= w) {
                // Либо берем предмет, либо не берем
                dp[i][w] = Math.max(
                    values[i - 1] + dp[i - 1][w - weights[i - 1]], // берем
                    dp[i - 1][w]                                    // не берем
                );
            } else {
                dp[i][w] = dp[i - 1][w];
            }
        }
    }
    
    return dp[n][capacity];
}
// Сложность: O(n * capacity)
```


### 10.3 Longest Common Subsequence (LCS)

```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    
    return dp[m][n];
}
```


### 10.4 Coin Change

```java
// Минимальное количество монет для набора суммы
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[^0] = 0;
    
    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (coin <= i) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    
    return dp[amount] > amount ? -1 : dp[amount];
}
```


## 11. Жадные алгоритмы

Жадный алгоритм делает локально оптимальный выбор на каждом шаге, надеясь получить глобально оптимальное решение.[^24][^27]

### 11.1 Activity Selection Problem

```java
class Activity {
    int start, finish;
    Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}

public int maxActivities(Activity[] activities) {
    // Сортируем по времени окончания
    Arrays.sort(activities, (a, b) -> a.finish - b.finish);
    
    int count = 1;
    int lastFinish = activities[^0].finish;
    
    for (int i = 1; i < activities.length; i++) {
        if (activities[i].start >= lastFinish) {
            count++;
            lastFinish = activities[i].finish;
        }
    }
    
    return count;
}
// Сложность: O(n log n)
```


### 11.2 Fractional Knapsack

```java
class Item {
    int value, weight;
    double ratio;
    
    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.ratio = (double) value / weight;
    }
}

public double fractionalKnapsack(Item[] items, int capacity) {
    // Сортируем по убыванию отношения ценность/вес
    Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
    
    double totalValue = 0;
    int remainingCapacity = capacity;
    
    for (Item item : items) {
        if (remainingCapacity >= item.weight) {
            totalValue += item.value;
            remainingCapacity -= item.weight;
        } else {
            // Берем часть предмета
            totalValue += item.ratio * remainingCapacity;
            break;
        }
    }
    
    return totalValue;
}
```


## Заключение и план изучения

### Рекомендуемая последовательность изучения

**Фаза 1: Основы (1-2 месяца)**

- Big O нотация и анализ сложности
- Массивы и строки
- Two Pointers, Sliding Window
- Связные списки
- Стек и очередь

**Фаза 2: Промежуточный уровень (2-3 месяца)**

- Хеш-таблицы (HashMap, HashSet)
- Рекурсия
- Бинарные деревья и обходы
- Binary Search Tree
- Базовые алгоритмы сортировки

**Фаза 3: Продвинутый уровень (3-4 месяца)**

- Графы (DFS, BFS)
- Динамическое программирование
- Продвинутые деревья (AVL, Heap)
- Backtracking
- Алгоритм Дейкстры
- Жадные алгоритмы


### Практические рекомендации

**Ежедневная практика:**

- Решайте 1-2 задачи на LeetCode/HackerRank каждый день
- Начинайте с Easy, переходите к Medium
- Повторяйте решенные задачи через неделю

**Паттерны задач:**

- Two Pointers: ~20 задач
- Sliding Window: ~15 задач
- Binary Search: ~20 задач
- DFS/BFS: ~25 задач
- Dynamic Programming: ~30 задач

**Ресурсы:**

- **LeetCode**: основная платформа для практики
- **HackerRank**: альтернативная платформа
- **Книга**: "Cracking the Coding Interview"
- **Книга**: "Структуры данных и алгоритмы в Java" — Роберт Лафоре
- **Курсы**: Algorithms Specialization (Coursera)


### Типичные ошибки новичков

❌ Попытка запомнить все алгоритмы наизусть
❌ Решение задач без понимания сложности
❌ Игнорирование edge cases
❌ Отсутствие практики написания кода от руки

✅ **Правильный подход:**

- Понимайте принцип работы алгоритма
- Анализируйте сложность каждого решения
- Решайте задачи в категориях (все задачи на Sliding Window, затем на DP)
- Практикуйте объяснение решений вслух


### Подготовка к собеседованиям

**Структура технического интервью:**

1. **Уточнение задачи** (2-3 мин)
2. **Обсуждение подхода** (5-7 мин)
3. **Написание кода** (15-20 мин)
4. **Тестирование и оптимизация** (5-7 мин)

**Ключевые навыки:**

- Умение думать вслух
- Анализ сложности O()
- Оптимизация решения
- Обработка edge cases

Алгоритмы и структуры данных — это не просто теория для собеседований. Это фундаментальные знания, которые делают вас лучшим разработчиком, способным писать эффективный, масштабируемый код. Каждая минута, потраченная на изучение этих концепций, окупится многократно в вашей карьере Java developer.
<span style="display:none">[^28][^29][^30]</span>

<div align="center">⁂</div>

[^1]: https://optima.study/ru/algoritmi-ta-strukturi-danih-java-distanciyniy-kurs

[^2]: https://foxminded.ua/ru/algorithms-data-structures/

[^3]: https://prog.academy/algo

[^4]: https://edu.cbsystematics.com/ru/courses/algorithms-and-data-structures

[^5]: https://habr.com/ru/articles/782608/

[^6]: https://www.8host.com/blog/kak-rabotaet-big-o-notaciya-v-javascript/

[^7]: https://tproger.ru/articles/algoritmy-sortirovki-na-java-s-primerami

[^8]: https://habr.com/ru/companies/selectel/articles/851206/

[^9]: https://foxminded.ua/ru/stek-java/

[^10]: https://www.youtube.com/watch?v=As3Pc2tw_Tg

[^11]: https://foxminded.ua/ru/hashmap-v-java/

[^12]: https://javarush.com/groups/posts/2496-podrobnihy-razbor-klassa-hashmap

[^13]: https://foxminded.ua/ru/kak-rabotaet-hashmap-v-java/

[^14]: https://ru.wikipedia.org/wiki/Хеш-таблица

[^15]: http://neerc.ifmo.ru/wiki/index.php?title=Разрешение_%D0%BA%D0%BE%D0%BB%D0%BB%D0%B8%D0%B7%D0%B8%D0%B9\&mobileaction=toggle_view_desktop

[^16]: https://foxminded.ua/ru/binarnoe-derevo/

[^17]: https://javarush.com/groups/posts/3111-strukturih-dannihkh-dvoichnoe-derevo-v-java

[^18]: https://habr.com/ru/articles/267855/

[^19]: https://habr.com/ru/articles/65617/

[^20]: https://drukarnia.com.ua/articles/strukturi-danikh-binarni-dereva-jZ56d

[^21]: https://itvdn.com/ru/video/algorithms-and-data-structures-in-c-sharp/binary-search-tree

[^22]: https://algorithmica.org/tg/bfs

[^23]: https://habr.com/ru/companies/timeweb/articles/751762/

[^24]: https://habr.com/ru/articles/120343/

[^25]: https://foxminded.ua/ru/metod-dinamicheskogo-programmirovaniya/

[^26]: https://dou.ua/lenta/columns/about-dynamic-programming/

[^27]: https://sprintcode.pro/ru/blog/greedy-algorithm

[^28]: https://www.youtube.com/playlist?list=PL4_hYwCyhAvbPIeVQRbuctLAiOkiCxH-v

[^29]: https://www.youtube.com/watch?v=jlheNrmPIQQ

[^30]: https://qna.habr.com/q/931993

