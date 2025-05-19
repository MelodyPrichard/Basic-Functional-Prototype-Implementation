# Basic-Functional-Prototype-Implementation
This Basic Functional Prototype serves as a compact inventory management system designed to help small businesses track and organize their stock. The software allows users to add items with details like name, quantity, price, and category, while providing sorting capabilities through an efficient quicksort algorithm. Built with JavaFX, the graphical interface offers intuitive controls for adding, viewing, saving, and loading inventory data, which is stored in an ArrayList and persisted to a file for future access. The implementation demonstrates key object-oriented principles, including generics and inheritance, with a base InventoryItem class extended by specialized categories like ElectronicsItem and GroceryItem. The system efficiently handles core inventory tasks while maintaining a structure that supports future expansion, such as adding new item types or advanced features like search functionality. By combining file I/O, GUI interaction, and optimized sorting, this prototype provides a functional foundation that balances usability with technical robustness.
File I/O:


File I/O: The system reads and writes inventory data to "inventory.txt"

GUI: Uses JavaFX with a clean interface for adding/viewing items

ArrayList: The inventory is stored in an ArrayList<InventoryItem>

Sorting Algorithm: Implements quicksort (O(n log n) average case) with three sort options

Generics: InventoryManager uses generics to work with InventoryItem and its subclasses

Inheritance: ElectronicsItem and GroceryItem extend the base InventoryItem class

Implemented using BufferedReader and BufferedWriter for reading/writing fitness data to a local file.



Graphical User Interface:

Built using JavaFX. Includes:

TextFields for workout/measurement input

Buttons to add, sort, and save/load entries

ListView to display fitness records

ArrayList Usage:

All fitness entries are stored in an ArrayList<Entry>.

Sorting Algorithm:

Implemented mergeSort with the following note:

// Merge sort used due to its stable O(n log n) performance in worst-case scenarios

Generics & Inheritance:

EntryManager<T extends Entry> allows flexibility to manage different entry types.

WorkoutEntry and BodyStatEntry extend Entry and include fitness-specific fields.

Added Deque for recent items (ArrayDeque from Java API).
Custom LowStockLinkedList for tracking low inventory.
Optimized search with HashMap<String, InventoryItem>.
New UI buttons for accessing these features.

Search by ID:

Type product ID in search box

BST returns result instantly

Browse by Category:

Select category from dropdown

Hash table shows all matching items


GitHub Updates
Added InventoryBST.java
Implemented category hash table
Updated GUI with new search features
Added performance benchmarks
