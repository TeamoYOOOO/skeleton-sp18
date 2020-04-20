public class ArrayDeque<Item>{
	private int size;
	private int first;//pointer
	private int last;//pointer
	private Item[] arr;

	public ArrayDeque(){
		size = 0;
		arr = (Item[]) new Object[8];
	}

	public ArrayDeque(int cap){
		size = 0;
		arr = (Item[]) new Object[cap];
	}

	public void addFirst(Item item){
		//last and first should not point at same box
		if((last + 1) % arr.length == first){
			this.resize(arr.length * 2);
		}
		arr[first] = item;
		fist = (first-1)%arr.length;
		size++;
	}

	public void addLast(Item item){
		//last and first should not point at same box
		if((last + 1) % arr.length == first){
			this.resize(arr.length * 2);
		}
		arr[last] = item;
		last = (last+1) % arr.length;
		size++;
	}

	private void resize(int length){
		Item[] temp = (Item[])new Object[length];
		if(first < last){
			System.arraycopy(arr, first + 1, temp, first + 1, size);
		}
		else{
			System.arraycopy(arr, 0, temp, 0, last);
			System.arraycopy(arr, first + 1, temp, temp.length - (arr.length - first) + 1, arr.length - first + 1);
		}
		arr = temp;
	}

	public boolean isEmpty(){
		return size == 0;
		//return first == (last+1)%arr.length;
	}

	public int size(){
		return size;
	}

	public void printDeque(){
		if(first < last){
			for(int x = first+1; x < last-1; x++){
				System.out.print(arr[x] + " ");
			}
			System.out.print(arr[last-1]);
		}
		else{
			for(int x = first+1; x < arr.length; x++){
				System.out.print(arr[x] + " ");
			}
			for(int x = 0; x < last-1; x++){
				System.out.print(arr[x] + " ");
			}
			System.out.print(arr[last-1]);
		}
	}

	public Item removeFirst(){
		if(this.isEmpty()){
			throw new IllegalArgumentException("Array is empty");
		}
		Item temp = this.get((first+1)%arr.length);
		arr[(first+1)%arr.length] = null;
		arr = (first+1)%arr.length;
		return temp;
	}

	public Item removeLast(){
		if(this.isEmpty()){
			throw new IllegalArgumentException("Array is empty");
		}
		Item temp = this.get((last-1)%arr.length);
		arr[(last-1)%arr.length] = null;
		arr = (last-1)%arr.length;
		return temp;
	}

	public Item get(int index){
		return arr[index];
	}

	/**
	public void addFirst(T item): Adds an item of type T to the front of the deque.
	public void addLast(T item): Adds an item of type T to the back of the deque.
	public boolean isEmpty(): Returns true if deque is empty, false otherwise.
	public int size(): Returns the number of items in the deque.
	public void printDeque(): Prints the items in the deque from first to last, separated by a space.
	public T removeFirst(): Removes and returns the item at the front of the deque. If no such item exists, returns null.
	public T removeLast(): Removes and returns the item at the back of the deque. If no such item exists, returns null.
	public T get(int index): Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!

	**/



}