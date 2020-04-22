public class Main {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        hashTable.insert("Bob", "Smith", "555-235-1111", "bsmith@somewhere.com");
        hashTable.insert("Jane", "Williams", "555-235-1112", "jw@something.com");
        hashTable.insert("Mohammed", "al-Salam", "555-235-1113", "mas@someplace.com");
        hashTable.insert("Pat", "Jones", "555-235-1114", "pjones@homesweethome.com");
        hashTable.insert("Billy", "Kidd", "555-235-1115", "billy_the_kid@nowhere.com");
        hashTable.insert("H.", "Houdini", "555-235-1116", "houdini@noplace.com");
        hashTable.insert("Jack", "Jones", "555-235-1117", "jjones@hill.com");
        hashTable.insert("Jill", "Jones", "555-235-1118", "jillj@hill.com");
        hashTable.insert("John", "Doe", "555-235-1119", "jdoe@somedomain.com");
        hashTable.insert("Jane", "Doe", "555-235-1120", "jdoe@somedomain.com");

        hashTable.lookup("Pat", "Jones");
        hashTable.lookup("Billy", "Kidd");

        hashTable.delete("John", "Doe");

        hashTable.insert("Test", "Case", "555-235-1121", "Test_Case@testcase.com");
        hashTable.insert("Jo", "Wu", "555-235-1123", "wu@h.com");
        hashTable.insert("Millard", "Fillmore", "555-235-1124", "millard@theactualwhitehouse.us");
        hashTable.insert("Bob", "vanDyke", "555-235-1125", "vandyke@nodomain.com");
        hashTable.insert("Upside", "Down", "555-235-1126", "upsidedown@rightsideup.com");

        hashTable.lookup("Jack", "Jones");

        hashTable.delete("Jill", "Jones");
        hashTable.delete("John", "Doe");

        hashTable.lookup("Jill", "Jones");
        hashTable.lookup("John", "Doe");

        hashTable.printTable();
    }
}
