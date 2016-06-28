package com.data.huffman;

/**
 * 二叉树的静态三叉链表类（用来储存Huffman树）
 * @author zifeng
 *
 */
public class TriElement {
	int data;
	int parent,left,right;
	
	/**
	 * @param data:数据域
	 * @param parent:父节点
	 * @param left:左子节点
	 * @param right:右子节点
	 */
	public TriElement(int data,int parent,int left,int right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	public TriElement(int data){
		this(data,-1,-1,-1);
	}
	
	public String toString(){
		return "("+this.data+","
				+this.parent+","+this.left+","+this.right+")";
	}
	
	public boolean isLeaf(){
		return this.left==-1 && this.right == -1;
	}
}
