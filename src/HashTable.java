public class HashTable {

    private static final int DEFAULT_SIZE = 13;

    private HashEntry[] phoneBook  = new HashEntry[DEFAULT_SIZE];

    public HashTable() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            phoneBook[i] = null;
        }
    }

    public int hashName(String firstName, String lastName) {
        String fullName = (firstName + " " + lastName).toUpperCase();
        int hashedCatName = fullName.hashCode();
        int hashIndex = hashedCatName % DEFAULT_SIZE;

        if (hashIndex < 0) { hashIndex += DEFAULT_SIZE; };

        return hashIndex;
    }

    public void insert(String firstName, String lastName, String phoneNumber, String email) {
        int hashIndex = hashName(firstName, lastName);
        String value = phoneNumber + " " + email;
        String key = firstName + " " + lastName;

        if (phoneBook[hashIndex] == null) {
            phoneBook[hashIndex] = new HashEntry(key, value);
            System.out.println("Entry for " + key + " created at index " + hashIndex);
        } else {
            HashEntry currentEntry = traverseChainOfEntries(phoneBook[hashIndex], key);

            if (currentEntry.getKey().equals(key)) {
                currentEntry.setValue(value);
                System.out.println("Entry for " + key + " updated at index " + hashIndex);
            } else {
                currentEntry.setNext(new HashEntry(key, value));
                System.out.println("Entry for " + key + " created at index " + hashIndex);
            }
        }
    }

    private HashEntry traverseChainOfEntries(HashEntry currentEntry, String key) {
        if (currentEntry != null) {
            while (currentEntry.getNext() != null && !currentEntry.getKey().equals(key)) {
                currentEntry = currentEntry.getNext();
            }
        }

        return currentEntry;
    }

    public void delete(String firstName, String lastName) {
        int hashIndex = hashName(firstName, lastName);
        String key = firstName + " " + lastName;

        if (phoneBook[hashIndex] == null) {
            System.out.println("Cannot delete " + key + " at index " + hashIndex);
        } else {
            HashEntry currentEntry = phoneBook[hashIndex];
            HashEntry previousEntry = null;

            while (currentEntry.getNext() != null && currentEntry.getKey() != key) {
                previousEntry = currentEntry;
                currentEntry = currentEntry.getNext();
            }

            if (previousEntry == null) {
                phoneBook[hashIndex] = currentEntry.getNext();
            } else {
                previousEntry.setNext(currentEntry.getNext());
            }

            System.out.println("Successfully deleted entry for " + key + " at index " + hashIndex);
        }
    }

    public void lookup(String firstName, String lastName) {
        int hashIndex = hashName(firstName, lastName);
        String key = firstName + " " + lastName;

        HashEntry currentEntry = traverseChainOfEntries(phoneBook[hashIndex], key);

        if (currentEntry != null && currentEntry.getKey().equals(key)) {
            System.out.println("Found " + currentEntry);
        } else {
            System.out.println("Cannot find " + key + " at index " + hashIndex);
        }
    }

    public void printTable() {
        StringBuilder sb = new StringBuilder("Table snapshot: ");
        sb.append(System.getProperty("line.separator"));

        for (int i = 0; i < 13; i++) {
            appendChainOfEntries(sb, i);
        }

        System.out.print(sb.toString());
    }

    private void appendChainOfEntries(StringBuilder sb, int i) {
        HashEntry entry = phoneBook[i];

        sb.append(i + ": ");

        if (entry != null) {
            appendNextEntry(sb, entry);
        } else {
            sb.append("null");
        }

        sb.append(System.getProperty("line.separator"));
    }

    private void appendNextEntry(StringBuilder sb, HashEntry entry) {
        sb.append(entry.toString());

        if (entry.getNext() != null) {
            sb.append(" -> ");
            appendNextEntry(sb, entry.getNext());
        }
    }
}