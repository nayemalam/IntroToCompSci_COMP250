package hashMap;

/*	
* 	STUDENT NAME    	 :  NAYEM ALAM
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

public  class HashNode<K,V> implements Cloneable{
	private V value;
	private K key;
	public HashNode<K,V> next;

	/*
	 * Constructor.
	 */

	HashNode(K key, V value){
		this.value = value;
		this.key = key;
		this.next = null;
	}

	//ADD HELPER METHODS BELOW HERE
	public HashNode<K,V> clone(HashNode<K,V> element) {
		
		HashNode<K,V> copy = new HashNode<K,V>(element.key, element.value);
		
		return copy;
	}
	
    public void SetValue(V value) {
        this.value = value;
    }
	// Hint: Implement a  'Public' clone() method

	//ADD HELPER METHODS ABOVE HERE

	/*
	 * Returns this hash entry's value. 
	 * Assume entry is not null.
	 */

	public V getValue(){
		return this.value;
	}
	/*
	 * Returns this hash nodes's key.  
	 * Assume entry is not null.
	 * @return This hash nodes's key
	 */

	public K getKey(){
		return this.key;
	}
	/*
	 * Return Next node
	 */
	HashNode<K,V> getNext(){
		return this.next;
	}

	@Override
	public String toString() {
		/*
		 * Implemented method. You do not need to modify.
		 */
		return key.toString()+" : " + value.toString();
		//return  value.toString();
	}

}
