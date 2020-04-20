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
		if(first == 0){//position zero - jump to the last index
			arr[first] = item;
			first = arr.length -1;
		}
		else{
			arr[first] = item;
			first--;
		}
		size++;
	}

	public void addLast(Item item){
		//last and first should not point at same box
		if((last + 1) % arr.length == first){
			this.resize(arr.length * 2);
		}
		if(last == arr.length-1){
			arr[last] = item;
			last = 0;
		}
		else{
			arr[last] = item;
			last++;
		}
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
		Item temp = first == arr.length-1? arr[0] : arr[first+1];
		if(this.isEmpty()){
			throw new IllegalArgumentException("Array is empty");
		}
		if(first == arr.length-1){
			arr[0] = null;//null for generics 
			first = 0;
		}
		else{
			arr[first+1] = null;//null for generics
			first++;
		}
		size--;
		if(size < arr.length/4){
			this.resize(arr.length/2);
		}
		return temp;
	}

	public Item removeLast(){
		Item temp = last==0? arr[arr.length-1] : arr[last-1];
		if(this.isEmpty()){
			throw new IllegalArgumentException("Array is empty");
		}
		if(last == 0){
			arr[arr.length-1] = null;//null for generics 
			last = arr.length-1;
		}
		else{
			arr[last-1] = null;//null for generics
			last++;
		}
		size--;
		if(size < arr.length/4){
			this.resize(arr.length/2);
		}
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