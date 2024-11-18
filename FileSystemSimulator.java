import java.util.ArrayList;
import java.util.List;

public class FileSystemSimulator {

    // Base Node Class
    public static class Node {
        String name;
        boolean isFile; // True if it's a file, false if it's a directory
        List<Node> children; // Only used for directories

        // Constructor for a directory
        public Node(String name) {
            this.name = name;
            this.isFile = false;
            this.children = new ArrayList<>();
        }

        // Constructor for a file
        public Node(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.children = isFile ? null : new ArrayList<>();
        }
    }

    // Method to add a file or directory to a parent directory
    public static void addNode(Node parent, String name, boolean isFile) {
        if (parent.isFile) {
            System.out.println("Cannot add to a file: " + parent.name);
            return;
        }
        Node newNode = new Node(name, isFile);
        parent.children.add(newNode);
    }

    // Recursive method to print the file system structure
    public static void printTree(Node node, String indent) {
        System.out.println(indent + (node.isFile ? "[File] " : "[Dir] ") + node.name);
        if (!node.isFile) {
            for (Node child : node.children) {
                printTree(child, indent + "  "); // Increase indentation for children
            }
        }
    }

    public static void main(String[] args) {
        // Root directory
        Node root = new Node("root");

        // Add directories and files
        addNode(root, "documents", false); // Add a "documents" directory
        addNode(root, "pictures", false); // Add a "pictures" directory
        addNode(root, "readme.txt", true); // Add a "readme.txt" file

        // Add files to "documents"
        Node documents = root.children.get(0);
        addNode(documents, "resume.pdf", true);
        addNode(documents, "notes.txt", true);

        // Add files and directories to "pictures"
        Node pictures = root.children.get(1);
        addNode(pictures, "vacation", false); // Add a "vacation" directory
        addNode(pictures, "cat.jpg", true); // Add a "cat.jpg" file

        // Add files to "vacation"
        Node vacation = pictures.children.get(0);
        addNode(vacation, "beach.png", true);
        addNode(vacation, "mountain.jpg", true);

        // Print the file system structure
        printTree(root, "");
    }
}