// MenuTest.java
public class MenuTest {

    public static void main(String[] args) {
        testAddMenuItem();
        testRemoveMenuItem();
        testMenuHierarchy();
    }

    // Test adding a menu item
    private static void testAddMenuItem() {
        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open File", "openFile()");

        fileMenu.add(openItem);

        System.out.println("Test Add Menu Item:");
        fileMenu.display();
        System.out.println("---------------------------");
    }

    // Test removing a menu item
    private static void testRemoveMenuItem() {
        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open File", "openFile()");
        MenuItem saveItem = new MenuItem("Save File", "saveFile()");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        System.out.println("Test Remove Menu Item Before Removal:");
        fileMenu.display();

        fileMenu.remove(openItem);

        System.out.println("Test Remove Menu Item After Removal:");
        fileMenu.display();
        System.out.println("---------------------------");
    }

    // Test menu hierarchy
    private static void testMenuHierarchy() {
        // Create menu items
        MenuItem openItem = new MenuItem("Open File", "openFile()");
        MenuItem saveItem = new MenuItem("Save File", "saveFile()");
        MenuItem exitItem = new MenuItem("Exit", "exitApplication()");
        MenuItem copyItem = new MenuItem("Copy", "copy()");
        MenuItem pasteItem = new MenuItem("Paste", "paste()");

        // Create submenus
        Menu fileMenu = new Menu("File");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        Menu editMenu = new Menu("Edit");
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Create main menu
        Menu mainMenu = new Menu("Main Menu");
        mainMenu.add(fileMenu);
        mainMenu.add(editMenu);

        System.out.println("Test Menu Hierarchy:");
        mainMenu.display();
        System.out.println("---------------------------");
    }
}