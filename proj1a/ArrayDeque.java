public class ArrayDeque<Item>{
	private int size;
	private int first;//pointer
	private int last;//pointer
	private Item[] arr;

	//constructor
	public ArrayDeque(){
		size = 0;
		arr = (Item[]) new Object[8];
		first = 0;
		last = 0;
	}

	public ArrayDeque(int cap){
		size = 0;
		arr = (Item[]) new Object[cap];
		first = 0;
		last = 0;
	}

	public void addFirst(Item item){
		//last and first should not point at same box
		if((last + 1) % arr.length == first){
			this.resize(arr.length * 2);
		}
		arr[first] = item;
		first = ((first-1)%arr.length + arr.length)%arr.length;
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
			System.arraycopy(arr, (first + 1), temp, (temp.length - (arr.length -(first+1))), (arr.length-(first + 1)));
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
		first = (first+1)%arr.length;
		return temp;
	}

	public Item removeLast(){
		if(this.isEmpty()){
			throw new IllegalArgumentException("Array is empty");
		}
		Item temp = this.get((last-1)%arr.length);
		arr[((last-1)%arr.length + arr.length)% arr.length] = null;
		last = (last-1)%arr.length;
		return temp;
	}

	public Item get(int index){
		return arr[index];
	}

	public static void main (String[] args){
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        arr.addFirst(1);
        arr.addFirst(2);
        arr.addFirst(3);
        arr.addFirst(4);
        arr.addFirst(5);
        arr.addFirst(6);
        arr.addFirst(7);
        arr.addFirst(8);
		System.out.println(arr.size());
        for(int i = 0; i < 16; i++){
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }



}