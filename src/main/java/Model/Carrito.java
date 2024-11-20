package Model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Item> items;

    public Carrito() {
        items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        items.add(item);
    }

    public void eliminarItem(String nombre) {
        items.removeIf(item -> item.getNombre().equals(nombre));
    }

    public double getTotalCarrito() {
        return items.stream().mapToDouble(Item::getTotal).sum();
    }

    public List<Item> getItems() {
        return items;
    }
}
