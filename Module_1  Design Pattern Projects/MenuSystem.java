import java.util.ArrayList;
import java.util.List;

// Component Interface
interface MenuComponent {
    void display();
}

// Leaf Class: Represents individual menu items
class MenuItem implements MenuComponent {
    private String name;
    private String action;

    public MenuItem(String name, String action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public void display() {
        System.out.println("  Item: " + name + " | Action: " + action);
    }
}

// Composite Class: Represents a menu that can contain other menus or items
class Menu implements MenuComponent {
    private String name;
    private List<MenuComponent> menuComponents = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    @Override
    public void display() {
        System.out.println("\nMenu: " + name);
        for (MenuComponent menuComponent : menuComponents) {
            menuComponent.display();
        }
    }
}

// Client Class
public class MenuSystem {
    public static void main(String[] args) {
        // Create individual menu items
        MenuItem item1 = new MenuItem("Open File", "openFile()");
        MenuItem item2 = new MenuItem("Save File", "saveFile()");
        MenuItem item3 = new MenuItem("Exit", "exitApplication()");

        // Create submenus and add items
        Menu fileMenu = new Menu("File");
        fileMenu.add(item1);
        fileMenu.add(item2);
        fileMenu.add(item3);

        MenuItem item4 = new MenuItem("Copy", "copy()");
        MenuItem item5 = new MenuItem("Paste", "paste()");

        Menu editMenu = new Menu("Edit");
        editMenu.add(item4);
        editMenu.add(item5);

        // Create main menu and add submenus
        Menu mainMenu = new Menu("Main Menu");
        mainMenu.add(fileMenu);
        mainMenu.add(editMenu);

        // Display the entire menu structure
        mainMenu.display();
    }
}