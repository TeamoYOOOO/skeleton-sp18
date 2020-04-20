public class LinkedListDeque<T> {

	private DLNode head;
	private int size;

	private class DLNode {
		private T val;
		private DLNode last;
		private DLNode next;

		public DLNode() {
			val = null;
			last = null;
			next = null;
		}

		public DLNode(T item) {
			val = item;
			last = null;
			next = null;
		}

	}

	public LinkedListDeque() {
		head = new DLNode();
		head.next = head;
		head.last = head;
		size = 0;
	}

	public void addFirst(T item) {
		DLNode firstNode = new DLNode(item);
		firstNode.next = head.next;
		firstNode.last = head;
		firstNode.next.last = firstNode;
		head.next = firstNode;
		size += 1;
	}

	public void addLast(T item) {
		DLNode lastNode = new DLNode(item);
		lastNode.last = head.last;
		lastNode.next = head;
		lastNode.last.next = lastNode;
		head.last = lastNode;
		size += 1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void printDeque() {
		DLNode p = head.next;
		while (p != head) {
			System.out.print(p.val);
			System.out.print(" ");
		}
	}

	public T removeFirst() {
		DLNode p = head.next;
		head.next.next.last = head;
		head.next = head.next.next;
		size -= 1;
		return p.val;
	}

	public T removeLast() {
		DLNode p = head.last;
		head.last.last.next = head;
		head.last = head.last.last;
		return p.val;
	}

	public T get(int index) {
		DLNode p = head.next;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.val;
	}

	public T getRecursiveFromNode(DLNode p, int index) {
		if (index == 0) {
			return p.val;
		}
		return getRecursiveFromNode(p.next, index - 1);
	}

	public T getRecursive(int index) {
		return getRecursiveFromNode(head.next, index);
	}
}