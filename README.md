# Sorting Visualizer

## Описание

Sorting Visualizer — это JavaFX приложение для визуализации алгоритмов сортировки. Приложение позволяет пользователю визуально наблюдать процесс сортировки различных алгоритмов, таких как пузырьковая сортировка, сортировка вставками и быстрая сортировка.

## Структура проекта

Проект организован в несколько основных директорий:

- `src/main/java/com/edu`: Основная директория с классами приложения.
    - **`SortingVisualizerApp.java`**: Главный класс JavaFX приложения. Отвечает за создание интерфейса, управление алгоритмами сортировки и визуализацию шагов сортировки.
        - Подробнее: [SortingVisualizerApp](src/main/java/com/edu/README.md#sortingvisualizerapp)
    - **`Utility.java`**: Утилитарный класс для подсветки элементов и очистки эффектов подсветки.
        - Подробнее: [Utility](src/main/java/com/edu/README.md#utility)

- `src/main/java/com/edu/algorithms`: Директория с реализациями алгоритмов сортировки.
    - **`SortAlgorithm.java`**: Интерфейс для всех алгоритмов сортировки.
        - Подробнее: [SortAlgorithm](src/main/java/com/edu/README.md#sortalgorithm)
    - **`BubbleSort.java`**: Реализация пузырьковой сортировки.
        - Подробнее: [BubbleSort](src/main/java/com/edu/README.md#bubblesort)
    - **`InsertionSort.java`**: Реализация сортировки вставками.
        - Подробнее: [InsertionSort](src/main/java/com/edu/README.md#insertionsort)
    - **`QuickSort.java`**: Реализация быстрой сортировки.
        - Подробнее: [QuickSort](src/main/java/com/edu/README.md#quicksort)

## Инструкция по запуску

Проект использует Maven для управления зависимостями и сборки. Чтобы запустить приложение, выполните следующие шаги:

1. Убедитесь, что у вас установлен Java Development Kit (JDK) версии 8 или выше и Maven.
2. Скачайте проект и распакуйте его.
3. Откройте командную строку или терминал и перейдите в корневую директорию проекта.
4. Выполните команду Maven для запуска приложения:

   ```bash
   mvn javafx:run
   ```

   Эта команда запустит JavaFX приложение, используя настройки, указанные в файле `pom.xml`.

## Добавление новых алгоритмов сортировки

Чтобы добавить новый алгоритм сортировки в проект, выполните следующие шаги:

1. **Создайте новый класс алгоритма сортировки**:

    - Класс должен реализовывать интерфейс `SortAlgorithm`.
    - Реализуйте методы `sortStep`, `reset`, `getOperationCount`, `resetOperationCount`, и `getName`.

   Пример реализации:
   ```java
   package com.edu.algorithms;

   import com.edu.Utility;
   import javafx.scene.shape.Rectangle;

   public class MySortAlgorithm implements SortAlgorithm {
       // Реализация методов
   }
   ```

2. **Обновите `SortingVisualizerApp`**:

    - Добавьте новый алгоритм в метод `createAlgorithmSelector` в классе `SortingVisualizerApp`.

   Пример:
   ```java
   ComboBox<SortAlgorithm> algorithmSelector = new ComboBox<>();
   algorithmSelector.getItems().addAll(new BubbleSort(), new InsertionSort(), new QuickSort(), new MySortAlgorithm());
   ```

3. **Подсветка шагов**:

   Убедитесь, что метод `sortStep` в вашем алгоритме корректно вызывает `Utility.highlightBars` и `Utility.clearBarEffects` для визуализации шагов сортировки.

   Пример использования подсветки:
   ```java
   if (bars[j].getHeight() > bars[j + 1].getHeight()) {
       Utility.highlightBars(bars, j, j + 1); // Подсветка активных баров
       // Смена высоты баров
   }
   ```

4. **Добавьте документацию для нового алгоритма**:

    - В директории `src/main/java/com/edu/README.md` добавьте описание вашего алгоритма.

## Лицензия

Этот проект лицензирован под MIT License. См. файл [LICENSE](LICENSE) для получения дополнительной информации.

---

### Документация для классов

- **[SortingVisualizerApp](src/main/java/com/edu/README.md#sortingvisualizerapp)**: Подробное описание главного класса приложения.
- **[Utility](src/main/java/com/edu/README.md#utility)**: Описание утилитарных функций.
- **[SortAlgorithm](src/main/java/com/edu/algorithms/README.md#sortalgorithm)**: Описание интерфейса для алгоритмов сортировки.
- **[BubbleSort](src/main/java/com/edu/algorithms/README.md#bubblesort)**: Описание реализации пузырьковой сортировки.
- **[InsertionSort](src/main/java/com/edu/algorithms/README.md#insertionsort)**: Описание реализации сортировки вставками.
- **[QuickSort](src/main/java/com/edu/algorithms/README.md#quicksort)**: Описание реализации быстрой сортировки.
