package Phase2;

public class Stack {
	SingleLinkedList stack;

	public Stack() {
		stack = new SingleLinkedList();
	}

	public boolean push(Object obj) {
		stack.addFirstStack(obj);
		return true;
	}
	
	public boolean isEmpty() {
		return stack.getFirst()==null;
	}

	public Object peek() {
		if (stack.size() == 0)
			return null;
		return stack.getFirst();
	}

	public Object pop() {
		if (stack.size() == 0)
			return null;
		Object res = stack.getFirst().getKey();
		stack.removeFirst();
		return res;
	}

	public void printStack() {
		Stack s2 = new Stack();
		while (this.getStack().size() != 0) {
			System.out.println(this.peek());
			s2.push(this.pop());
		}
		while (s2.getStack().size() != 0)
			this.push(s2.pop());
	}
	
	public void delete(Object data) {
		Stack s = new Stack();
		while(!this.isEmpty()) {
			if(((SNode)this.peek()).getKey().equals(data)) {
				this.pop();
				break;
			}
			else s.push(this.pop());
		}
		while(!s.isEmpty()) this.push(s.pop());
	}

//	public static String getPostFix(String[] arr) {
//		Stack nums = new Stack();
//		Stack ops = new Stack();
//		String temp;
//		for(int i=0; i<arr.length; i++) {
//			if(arr[i].equals("(")) ops.push(arr[i]);
//			else if(arr[i].equals("+")) {
//				while(!ops.peek().equals("(")) {
//					temp=nums.pop()+"";
//					nums.push(""+nums.pop()+temp+ops.pop());
//				}
//				ops.push(arr[i]);
//			} else if(arr[i].equals("-")) {
//				while(!ops.peek().equals("(")) {
//					temp=nums.pop()+"";
//					nums.push(""+nums.pop()+temp+ops.pop());
//				}
//				ops.push(arr[i]);
//			} else if(arr[i].equals("*")) {
//				while(ops.peek().equals("/") || ops.peek().equals("%")) {
//					temp=nums.pop()+"";
//					nums.push(""+nums.pop()+temp+ops.pop());
//				}
//				ops.push(arr[i]);
//			} else if(arr[i].equals("/")) {
//				while(ops.peek().equals("*") || ops.peek().equals("%")) {
//					temp=nums.pop()+"";
//					nums.push(""+nums.pop()+temp+ops.pop());
//				}
//				ops.push(arr[i]);
//			} else if(arr[i].equals("%")) {
//				while(ops.peek().equals("*") || ops.peek().equals("/")) {
//					temp=nums.pop()+"";
//					nums.push(""+nums.pop()+temp+ops.pop());
//				}
//				ops.push(arr[i]);
//			} else if(arr[i].equals(")")) {
//				while(!ops.peek().equals("(")) {
//					temp=nums.pop()+"";
//					nums.push(""+nums.pop()+temp+ops.pop());
//				}
//				ops.pop();
//			} else nums.push(arr[i]);
//		}
//		return (String)nums.pop();
//	}
//	
//	public static int calc(String[] arr) {
//		StackList stack = new StackList();
//		String temp;
//		for(int i=0; i<arr.length; i++) {
//			if(arr[i].equals("+")) {
//				temp=stack.pop()+"";
//				stack.push(Integer.parseInt(stack.pop()+"")+Integer.parseInt(temp));
//			} else if(arr[i].equals("-")) {
//				temp=stack.pop()+"";
//				stack.push(Integer.parseInt(stack.pop()+"")-Integer.parseInt(temp));
//			} else if(arr[i].equals("*")) {
//				temp=stack.pop()+"";
//				stack.push(Integer.parseInt(stack.pop()+"")*Integer.parseInt(temp));
//			} else if(arr[i].equals("/")) {
//				temp=stack.pop()+"";
//				stack.push(Integer.parseInt(stack.pop()+"")/Integer.parseInt(temp));
//			} else if(arr[i].equals("%")) {
//				temp=stack.pop()+"";
//				stack.push(Integer.parseInt(stack.pop()+"")%Integer.parseInt(temp));
//			} else stack.push(Integer.parseInt(arr[i]));
//		}
//		return Integer.parseInt(""+stack.pop());
//	}
//	
//	
	public void pushSorted(Object data, Stack s) {
		if(this.isEmpty()) {
			this.push(data);
			if(!s.isEmpty()) pushSorted(s.pop(), s);
			return;
		}
		else if(((SNode)this.peek()).compareTo(data)<0) {
			s.push(this.pop());
			pushSorted(data, s);
		}
		else {
			this.push(data);
			if(!s.isEmpty()) pushSorted(s.pop(), s);
		}
	} 
//	
//	
//	public void printReversed(StackList s) {
//		if(this.isEmpty()) return;
//		Object r = this.pop();
//		printReversed(s);
//		System.out.println(r);
//		this.push(r);
//	}
//	public void reverse(StackList s) {
//		if(s.isEmpty()) return;
//		Object data = s.pop();
//		reverse(s);
//		s.push(data);
//		return;
//	}
//	public void removeDuplicate(StackList s) {
//		if(this.isEmpty()) return;
//		Object data=this.pop();
//		if(!s.find(data)) s.push(data);
//		this.removeDuplicate(s);
//		if(!s.isEmpty()) this.push(s.pop());
//		return;
//	}
//	public boolean find(Object data) {
//		if(this.isEmpty()) return false;
//		StackList s = new StackList();
//		while(!this.isEmpty()) {
//			if(this.peek().equals(data)) {
//				while(!s.isEmpty()) this.push(s.pop());
//				return true;
//			}
//			s.push(this.pop());
//		}
//		while(!s.isEmpty()) this.push(s.pop());
//		return false;
//	}

	// public void sortStack() {
	// Stack s2 = new Stack(this.getSize());
	// s2.push(this.pop());
	// while(!this.isEmpty()) {
	// Object temp = this.pop();
	// while(!s2.isEmpty() && s2.peek().compareTo(temp)>0) {
	// this.push(s2.pop());
	// }
	// s2.push(temp);
	// }
	// while (!s2.isEmpty()) {
	// this.push(s2.pop());
	// }
	// }
	// public boolean insertSorted(Object obj) {
	// if(isFull()) return false;
	// Stack s2 = new Stack(this.getSize());
	// while(!this.isEmpty() && this.peek().compareTo(obj)<0) {
	// s2.push(this.pop());
	// }
	// while(!s2.isEmpty()) this.push(s2.pop());
	// }
	// public Stack mergeSorted(Stack s2) {A
	// Stack mergedStack = new Stack(this.getSize()+s2.getSize());
	// while(!s2.isEmpty()) mergedStack.insertSorted(s2.pop());
	// while(!this.isEmpty()) mergedStack.insertSorted(this.pop());
	// return mergedStack;
	//
	// }
	public SingleLinkedList getStack() {
		return stack;
	}

	public void setStack(SingleLinkedList stack) {
		this.stack = stack;
	}

	public int getSize() {
		return stack.size();
	}
	public void clear() {
		stack = new SingleLinkedList();
	}

}