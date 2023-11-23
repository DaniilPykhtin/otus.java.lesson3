package ru.otus.daniil.lessons;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ItemsWebApplication implements MyWebApplication {
    private String name;
    private List<Item> items;
    private ItemsStorage itemsStorage;

    public ItemsWebApplication(ItemsStorage itemsStorage) {
        this.name = "Items Web Application";
        this.itemsStorage = itemsStorage;
        items = itemsStorage.get();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    private void setDefaultsItems() {
        items = List.of(
                new Item(1L, "Box"),
                new Item(2L, "Phone"),
                new Item(3L, "Table"),
                new Item(4L, "Monitor"),
                new Item(5L, "Chair"));
    }

    @Override
    public void execute(Request request, OutputStream output) throws IOException {

        if (items == null || items.size() == 0) {

            System.out.println("Предметы по умолчанию");
            setDefaultsItems();
            itemsStorage.put(items);
        } else {
            System.out.println("Предметы из базы");
        }

        StringBuilder builder = new StringBuilder("[ ");
        for (int i = 0; i < items.size(); i++) {
            builder.append("{ \"id\": ").append(items.get(i).getId()).append(", \"title\": \"").append(items.get(i).getTitle()).append("\" }");
            if (i < items.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(" ]");

        output.write(("" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Access-Control-Allow-Origin: *\r\n" +
                "\r\n" +
                builder
        ).getBytes(StandardCharsets.UTF_8));
    }
}
