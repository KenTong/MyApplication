package lab.app_recycleview;

import java.util.Arrays;

public class Source {

    public static ItemData[] itemsData = {
            new ItemData("Image", "http://lorempixel.com/600/250/"),
            new ItemData("Sports", "http://lorempixel.com/600/250/sports/"),
            new ItemData("Dummy", "http://lorempixel.com/600/200/sports/Dummy-Text/"),
            new ItemData("Nature", "http://lorempixel.com/600/200/nature/"),
            new ItemData("Food", "http://lorempixel.com/600/200/food/"),
            new ItemData("Abstract", "http://lorempixel.com/600/250/abstract/"),
            new ItemData("City", "http://lorempixel.com/600/250/city/"),
            new ItemData("Animal", "http://lorempixel.com/600/250/animal/"),
            new ItemData("People", "http://lorempixel.com/600/250/people/"),
            new ItemData("Business", "http://lorempixel.com/600/250/business/"),
            new ItemData("Cats", "http://lorempixel.com/600/250/cats/"),
            new ItemData("Nightlife", "http://lorempixel.com/600/250/nightlife/"),
            new ItemData("Fashion", "http://lorempixel.com/600/250/fashion/"),
            new ItemData("Transport", "http://lorempixel.com/600/250/transport/"),
            new ItemData("Technics", "http://lorempixel.com/600/250/technics/"),

    };

    public static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
}
