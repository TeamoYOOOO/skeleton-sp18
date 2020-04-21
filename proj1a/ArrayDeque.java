public class ArrayDeque<T>{
	private int size;
	private int first;//pointer
	private int last;//pointer
	private T[] arr;

	//constructor
	public ArrayDeque(){
		size = 0;
		arr = (T[]) new Object[8];
		first = 0;
		last = 1;
	}

	public void addFirst(T item){
		//last and first should not point at same box
		if((last + 1) % arr.length == first){
			this.resize(arr.length * 2);
		}
		arr[first] = item;
		first = ((first-1)%arr.length + arr.length)%arr.length;
		size++;
	}

	public void addLast(T item){
		//last and first should not point at same box
		if((last + 1) % arr.length == first){
			this.resize(arr.length * 2);
		}
		arr[last] = item;
		last = (last+1) % arr.length;
		size++;
	}

	private void resize(int length){
		T[] temp = (T[])new Object[length];
		if(first < last){
			System.arraycopy(arr, first + 1, temp, first + 1, size);
		}
		else{
			System.arraycopy(arr, 0, temp, 0, last);
			System.arraycopy(arr, (first + 1), temp, (temp.length - (arr.length -(first+1))), (arr.length-(first + 1)));
			first = temp.length - (arr.length - first);
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

	public T removeFirst(){
		if(this.isEmpty()){
			throw new IllegalArgumentException("Array is empty");
		}
		T temp = this.get((first+1)%arr.length);
		arr[(first+1)%arr.length] = null;
		first = (first+1)%arr.length;
		size--;
		if(size < arr.length/4){
			this.resize(arr.length/2);
		}
		return temp;
	}

	public T removeLast(){
		if(this.isEmpty()){
			throw new IllegalArgumentException("Array is empty");
		}
		T temp = this.get(((last-1)%arr.length+arr.length)%arr.length);
		arr[((last-1)%arr.length + arr.length)% arr.length] = null;
		last = (last-1)%arr.length;
		size--;
		if(size < arr.length/4){
			this.resize(arr.length/2);
		}

		return temp;
	}

	public T get(int index){
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
        arr.addLast(9);
        arr.addLast(10);
        arr.addLast(11);
        arr.addLast(12);
        arr.addLast(13);
        arr.addLast(14);
        arr.removeLast();
        arr.removeFirst();
        arr.addFirst(100);
        arr.addLast(50);
        arr.addLast(60);
        arr.addFirst(200);
		System.out.println(arr.size());
        for(int i = 0; i < 32; i++){
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }



}