public class HashTable {

    private static final int DEFAULT_SIZE = 13;

    //ADD FUNCTIONS -- existing methods too long
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
            System.out.println("Entry for " + key + " created");
        } else {
            HashEntry currentEntry = phoneBook[hashIndex];

            while (currentEntry.getNext() != null && currentEntry.getKey() != key) {
                currentEntry = currentEntry.getNext();
            }

            if (currentEntry.getKey().equals(key)) {
                currentEntry.setValue(value);
                System.out.println("Entry updated!");
            } else {
                currentEntry.setNext(new HashEntry(key, value));
                System.out.println("Entry for " + key + " created");
            }
        }
    }

    public void delete(String firstName, String lastName) {
        int hashIndex = hashName(firstName, lastName);
        String key = firstName + " " + lastName;
        System.out.println("Trying to delete " + key + " from index " + hashIndex + "...");

        if (phoneBook[hashIndex] != null) {
            HashEntry currentEntry = phoneBook[hashIndex];
            HashEntry previousEntry = null;

            while (currentEntry.getNext() != null && currentEntry.getKey() != key) {
                previousEntry = currentEntry;
                currentEntry = currentEntry.getNext();
            }

            if (currentEntry.getKey().equals(key)) {
                if (previousEntry == null) {
                    phoneBook[hashIndex] = currentEntry.getNext();
                }
                else {
                    previousEntry.setNext(currentEntry.getNext());
                }
            }

            System.out.println("Successfully deleted entry for " + key);

        } else {
            System.out.println("Cannot delete: no such entry at index " + hashIndex);
        }
    }

    public void lookup(String firstName, String lastName) {
        int hashIndex = hashName(firstName, lastName);
        String key = firstName + " " + lastName;

        System.out.println("Trying to look up " + key + " at index " + hashIndex + " ...");

        if (phoneBook[hashIndex] == null)
            System.out.println("Cannot look up: no such entry at index " + hashIndex);
        else {
            HashEntry currentEntry = phoneBook[hashIndex];

            while (currentEntry != null && !currentEntry.getKey().equals(key)) {
                System.out.println("Found entry for " + currentEntry.getKey());
                currentEntry = currentEntry.getNext();
                System.out.println("Trying next link in the chain... ");
            }

            if (currentEntry == null)
                System.out.println("Entry not found in this chain.");
            else
                System.out.println("Found correct entry: " + currentEntry.getKey() + " " + currentEntry.getValue());
        }
    }

    public void printTable() {
        StringBuilder sb = new StringBuilder("Table snapshot: ");

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