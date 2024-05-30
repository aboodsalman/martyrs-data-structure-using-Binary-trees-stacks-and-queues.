package Phase2;

public class BinarySearchTree extends BaseBinaryTree implements BSTIF{
	
	public TNode find(Object data) {
		return find(data, this.getRoot());
	}
	private TNode find(Object data, TNode node) {
		if(node == null) return null;
		if(node.equals(data)) return node;
		if(node.compareTo(data)>0) return find(data, node.getLeft());
		return find(data, node.getRight());
	}
	
	public Object insert(Object data) {
		return insert(data, this.getRoot());
	}
	private Object insert(Object data, TNode node) {
		TNode newNode = new TNode(data);
		if(node==null) {
			this.setRoot(newNode);
			return newNode.getData();
		}
		else if(node.compareTo(newNode.getData())>0) {
			if(node.getLeft()==null) node.setLeft(newNode);
			else return insert(data, node.getLeft());
			return newNode.getData();
		}
		else if(node.compareTo(newNode.getData())<0){
			if(node.getRight()==null) node.setRight(newNode);
			else return insert(data, node.getRight());
			return newNode.getData();
		}
		else return node.getData();
	}
	
	public void delete(Object data) {
		this.setRoot(delete(data, this.getRoot()));
	}
	private TNode delete(Object data, TNode node) {
		if(node==null) return null;
		if(node.compareTo(data)<0) node.setRight(delete(data, node.getRight()));
		else if(node.compareTo(data)>0) node.setLeft(delete(data, node.getLeft()));
		else if(node.getRight()==null && node.getLeft()==null) node=null;
		else if(node.getLeft()==null) node=node.getRight();
		else if(node.getRight()==null) node=node.getLeft();
		else deleteNodeWithTwoChildren(node);
		return node;
	}
	private void deleteNodeWithTwoChildren(TNode node) {
		TNode suc = getMin(node.getRight());
		delete(suc.getData(), this.getRoot());
		node.setData(suc.getData());
	}
	private TNode getMin(TNode node) {
		if(node.getLeft()==null) return node;
		return getMin(node.getLeft());
	}
	
	public void traverseInOrder() {
		traverseInOrder(this.getRoot());
	}
	private void traverseInOrder(TNode node) {
		if(node==null) return;
		traverseInOrder(node.getLeft());
		System.out.println(node.getData()+ " " + ((Date)node.getData()).getMartyrs().size());
		traverseInOrder(node.getRight());
	}
	
	public int size(TNode node) {
		if(node==null) return 0;
		return 1+size(node.getLeft())+size(node.getRight());
	}
	
	public int countLeafs(TNode node) {
		if(node==null) return 0;
		if(node.getLeft()==null && node.getRight()==null) return 1;
		return countLeafs(node.getLeft())+countLeafs(node.getRight());
	}
	
	public int countMartyrs(TNode node) {
		if(node==null) return 0;
		return countMartyrsDate(((Location)node.getData()).getDates().getRoot())+countMartyrs(node.getLeft())+countMartyrs(node.getRight());
	}
	
	private int countMartyrsDate(TNode node) {
		if(node==null) return 0;
		return ((Date)node.getData()).getMartyrs().size()+1+countMartyrsDate(node.getLeft())+countMartyrsDate(node.getRight());
	}
	
	public Date getEarliest(TNode node) {
		if(node==null) return null;
		if(node.getLeft()==null) return (Date)node.getData();
		return getEarliest(node.getLeft());
	}
	
	public Date getLatest(TNode node) {
		if(node==null) return null;
		if(node.getRight()==null) return (Date)node.getData();
		return getLatest(node.getRight());
	}
	
	public String getMaxDate() {
		if(this.getRoot()==null) return "None";
		Queue q = new Queue();
		q.enQueue(this.getRoot());
		int mx = getMaxMartyrs(this.getRoot());
		String s="";
		while(!q.isEmpty()) {
			TNode d = (TNode)q.deQueue();
			if(((Date)d.getData()).getMartyrs().size()==mx) s+=(Date)d.getData()+",";
			if(d.getLeft()!=null) q.enQueue(d.getLeft());
			if(d.getRight()!=null) q.enQueue(d.getRight());
		}
		if(!s.isBlank()) return s.substring(0, s.length() - 1);
		return "None";
	}
	private int getMaxMartyrs(TNode node) {
		if(node==null) return 0;
		return Math.max(Math.max(getMaxMartyrs(node.getLeft()), getMaxMartyrs(node.getRight())), ((Date)node.getData()).getMartyrs().size());
	}
}
