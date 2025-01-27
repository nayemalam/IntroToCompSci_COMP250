package hashMap;

/*	
* 	 STUDENT NAME      :  NAYEM ALAM
*    STUDENT ID        :  260743549
*   
*   If you have any issues that you wish the T.A.s to consider, then you
*   should list them here.   If you discussed on the assignment in depth 
*   with another student, then you should list that student's name here.   
*   We insist that you each write your own code.   But we also expect 
*   (and indeed encourage) that you discuss some of the technical
*   issues and problems with each other, in case you get stuck.    

*   
*/

import java.util.ArrayList;
import java.util.Iterator;


class MyHashTable<K,V> {
	/*
	 *   Number of entries in the HashTable. 
	 */
	private int entryCount = 0;

	/*
	 * Number of buckets. The constructor sets this variable to its initial value,
	 * which eventually can get changed by invoking the rehash() method.
	 */
	private int numBuckets;

	/**
	 * Threshold load factor for rehashing.
	 */
	private final double MAX_LOAD_FACTOR=0.75;

	/**
	 *  Buckets to store lists of key-value pairs.
	 *  Traditionally an array is used for the buckets and
	 *  a linked list is used for the entries within each bucket.   
	 *  We use an Arraylist rather than an array, since the former is simpler to use in Java.   
	 */

	ArrayList< HashLinkedList<K,V> >  buckets;

	/* 
	 * Constructor.
	 * 
	 * numBuckets is the initial number of buckets used by this hash table
	 */

	MyHashTable(int numBuckets) {

		//  ADD YOUR CODE BELOW HERE
		this.numBuckets = numBuckets;
		this.buckets = new ArrayList<HashLinkedList<K,V>>(numBuckets);
		for(int i = 0; i < numBuckets; i++) {
			HashLinkedList<K,V> tmp = new HashLinkedList<K,V>();
			//buckets.add(i,tmp);
			this.buckets.add(tmp);
		}

		//  ADD YOUR CODE ABOVE HERE

	}

	/**
	 * Given a key, return the bucket position for the key. 
	 */
	private int hashFunction(K key) {

		return  Math.abs( key.hashCode() ) % numBuckets ;
	}

	/**
	 * Checking if the hash table is empty.  
	 */

	public boolean isEmpty()
	{
		if (entryCount == 0)
			return true;
		else
			return(false);
	}

	/**
	 *   return the number of entries in the hash table.
	 */

	public int size()
	{
		return(entryCount);
	}

	/**
	 * Adds a key-value pair to the hash table. If the load factor goes above the 
	 * MAX_LOAD_FACTOR, then call the rehash() method after inserting. 
	 * 
	 *  If there was a previous value for the given key in this hashtable,
	 *  then overwrite it with new value and return the old value.
	 *  Otherwise return null.   
	 */

	public  V  put(K key, V value) {

		//  ADD YOUR CODE BELOW HERE
        int newVal = hashFunction(key);

        if (buckets.get(newVal) == null) {
        		buckets.set(newVal, new HashLinkedList<K,V>());
        }
        
        if (containsKey(key)) {
            V oldVal = buckets.get(newVal).getListNode(key).getValue();
            buckets.get(newVal).getListNode(key).SetValue(value);
            return oldVal;
        }

        buckets.get(newVal).add(key, value);
        entryCount++;

        if ((double)entryCount / (double)numBuckets > MAX_LOAD_FACTOR) {
        		rehash();
        }
        
        return null;
		//  ADD YOUR CODE ABOVE HERE
		
	}

	/**
	 * Retrieves a value associated with some given key in the hash table.
     Returns null if the key could not be found in the hash table)
	 */
	public V get(K key) {

		//  ADD YOUR CODE BELOW HERE
		
        if (containsKey(key) != true) {
        		return null;
        }
        else {
        		return buckets.get(hashFunction(key)).getListNode(key).getValue();
        }
		//  ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Removes a key-value pair from the hash table.
	 * Return value associated with the provided key.   If the key is not found, return null.
	 */
	public V remove(K key) {

		//  ADD YOUR CODE BELOW HERE

        if (containsKey(key) != true) {
        		return null;
        } else {
        		entryCount--;
            return buckets.get(hashFunction(key)).remove(key).getValue();
        }


		//  ADD  YOUR CODE ABOVE HERE
	}

	/*
	 *  This method is used for testing rehash().  Normally one would not provide such a method. 
	 */

	public int getNumBuckets(){
		return numBuckets;
	}

	/*
	 * Returns an iterator for the hash table. 
	 */

	public MyHashTable<K, V>.HashIterator  iterator(){
		return new HashIterator();
	}

	/*
	 * Removes all the entries from the hash table, 
	 * but keeps the number of buckets intact.
	 */
	public void clear()
	{
		for (int ct = 0; ct < buckets.size(); ct++){
			buckets.get(ct).clear();
		}
		entryCount=0;		
	}

	/**
	 *   Create a new hash table that has twice the number of buckets.
	 */


	public void rehash() {
		//   ADD YOUR CODE BELOW HERE
		//MyHashTable<K,V> bigHash = new MyHashTable<K,V>((buckets.size()*2));
		
		this.entryCount = 0;
		ArrayList<HashLinkedList<K,V>> newList = new ArrayList<HashLinkedList<K,V>>(2 * numBuckets);
        
		HashIterator Entries = iterator();
        this.buckets = newList;
        this.numBuckets = 2 * numBuckets;
        
		for (int i = 0; i < (this.numBuckets); i++) {
			newList.add(new HashLinkedList<K,V>());
		}
		
        while (Entries.hasNext()) {
            HashNode<K,V> node = Entries.next();
            put(node.getKey(), node.getValue());
        }
	}
		//   ADD YOUR CODE ABOVE HERE

	/*
	 * Checks if the hash table contains the given key.
	 * Return true if the hash table has the specified key, and false otherwise.
	 */

	public boolean containsKey(K key)
	{
		int hashValue = hashFunction(key);
		if(buckets.get(hashValue).getListNode(key) == null){
			return false;
		}
		return true;
	}

	/*
	 * return an ArrayList of the keys in the hashtable
	 */

	public ArrayList<K>  keys()
	{

		ArrayList<K>  listKeys = new ArrayList<K>();

		//   ADD YOUR CODE BELOW HERE
        HashIterator Iterator = iterator();

        while (Iterator.hasNext() == true) {
            listKeys.add(Iterator.next().getKey());
        }

        return listKeys;

		//   ADD YOUR CODE ABOVE HERE
		//return null;   //  CODE STUB.   REMOVE THIS LINE.
	}

	/*
	 * return an ArrayList of the values in the hashtable
	 */
	public ArrayList <V> values()
	{
		ArrayList<V>  listValues = new ArrayList<V>();

		//   ADD YOUR CODE BELOW HERE
        HashIterator Iterator = iterator();
        
        while (Iterator.hasNext()) {
            listValues.add(Iterator.next().getValue());
        }

        return listValues;

		//   ADD YOUR CODE ABOVE HERE
		//return null; //CODE STUB. REMOVE THIS LINE.
	}

	@Override
	public String toString() {
		/*
		 * Implemented method. You do not need to modify.
		 */
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buckets.size(); i++) {
			sb.append("Bucket ");
			sb.append(i);
			sb.append(" has ");
			sb.append(buckets.get(i).size());
			sb.append(" entries.\n");
		}
		sb.append("There are ");
		sb.append(entryCount);
		sb.append(" entries in the hash table altogether.");
		return sb.toString();
	}

	/*
	 *    Inner class:   Iterator for the Hash Table.
	 */
	public class HashIterator implements  Iterator<HashNode<K,V> > {
		HashLinkedList<K,V>  allEntries;

		/**
		 * Constructor:   make a linkedlist (HashLinkedList) 'allEntries' of all the entries in the hash table
		 */
		public  HashIterator()
		{

			//  ADD YOUR CODE BELOW HERE
            allEntries = new HashLinkedList<K,V>();
            
            for (int i = 0; i < numBuckets; i++) {
                HashNode<K,V> nextNode = buckets.get(i).getHead();
                
                while (nextNode != null) {
                    allEntries.add(nextNode.getKey(), nextNode.getValue());
                    nextNode = nextNode.getNext();
                }
            }
			//  ADD YOUR CODE ABOVE HERE

		}

		//  Override
		@Override
		public boolean hasNext()
		{
			return !allEntries.isEmpty();
		}

		//  Override
		@Override
		public HashNode<K,V> next()
		{
			return allEntries.removeFirst();
		}

		@Override
		public void remove() {
			// not implemented,  but must be declared because it is in the Iterator interface

		}		
	}

}
