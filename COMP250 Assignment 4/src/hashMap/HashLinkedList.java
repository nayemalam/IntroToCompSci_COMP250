package hashMap;

/*	
* 	 STUDENT NAME      :  NAYEM ALAM
*   STUDENT ID        :  260743549 
*   
*   If you have any issues that you wish the T.A.s to consider, then you
*   should list them here.   If you discussed on the assignment in depth 
*   with another student, then you should list that student's name here.   
*   We insist that you each write your own code.   But we also expect 
*   (and indeed encourage) that you discuss some of the technical
*   issues and problems with each other, in case you get stuck.    

*   
*/

public class HashLinkedList<K,V>{
	/*
	 * Fields
	 */
	private HashNode<K,V> head;

	private Integer size;

	/*
	 * Constructor
	 */

	HashLinkedList(){
		head = null;
		size = 0;
	}


	/*
	 *Add (Hash)node at the front of the linked list
	 */

	public void add(K key, V value){ 
		// ADD CODE BELOW HERE
        HashNode<K,V> prevNode = head; //previous Node
        
        head = new HashNode<K,V>(key, value);
        head.next = prevNode;
        size++;

		// ADD CODE ABOVE HERE
	}

	/*
	 * Get Hash(node) by key
	 * returns the node with key
	 */

	public HashNode<K,V> getListNode(K key){
		// ADD CODE BELOW HERE
		HashNode<K,V> currN = head;
		
		if (head == null) {
			return null;
	       }

		while (currN != null) {
			if (key.equals(currN.getKey())) {
				return currN;
			}else {
				currN = currN.getNext();
			}
		}
	        return null;
		// ADD CODE ABOVE HERE
	}


	/*
	 * Remove the head node of the list
	 * Note: Used by remove method and next method of hash table Iterator
	 */

	public HashNode<K,V> removeFirst(){
		// ADD CODE BELOW HERE
		HashNode<K,V> currN = head;
	
		if(head == null) {
			return null;
		} 
		else {
			head = currN.next;
			size--;
			return currN;
		}

		
		// ADD CODE ABOVE HERE
		//return null; //CODE STUB.. REMOVE THIS LINE
	}

	/*
	 * Remove Node by key from linked list 
	 */

	public HashNode<K,V> remove(K key){
		// ADD CODE BELOW HERE

        if (head == null) {
        		return null;
        }

        if (key.equals(head.getKey())) {
        		return removeFirst();
        }

        HashNode<K,V> prevNode = head; // previous node
        HashNode<K,V> currN = prevNode.getNext(); //current node

        while (currN != null) {
        		if (key.equals(currN.getKey())) {
        			prevNode.next = currN.getNext();
                size--;
                
                return currN;
            }else {
            		prevNode = currN;
                currN = currN.getNext();
            }
        }

        return null;
		// ADD CODE ABOVE HERE
		//return null; // removing failed
	}



	/*
	 * Delete the whole linked list
	 */
	public void clear(){
		head = null;
		size = 0;
	}
	/*
	 * Check if the list is empty
	 */

	boolean isEmpty(){
		return size == 0? true:false;
	}

	int size(){
		return this.size;
	}

	//ADD YOUR HELPER  METHODS BELOW THIS

	//clones a linked list 
	public HashLinkedList<K,V> clone (HashLinkedList<K,V> list){
		
		HashLinkedList<K,V>  var1 = new HashLinkedList<K,V>();
		HashNode<K,V> var1p = list.head; //clone variable (var 1 prime)
		
		while(var1p != null){ 
			
	        HashNode<K,V> newNode = var1p.clone(var1p);  
	        var1.add(newNode.getKey(), newNode.getValue());
			var1p= var1p.next;
		}
		
		return var1;
	}
	
    HashNode<K,V> getHead() {
        return head;
    }

	//ADD YOUR HELPER METHODS ABOVE THIS


}
