package com.data.huffman;

/**
 * HuffmanTree类
 * @author zifeng
 *
 */
public class HuffmanTree {
	//字符集
	private String charset;
	//静态三叉链表数组
	private TriElement[] huftree;
	
	/**
	 * @param weights:各字符节点的权值数组
	 */
	public HuffmanTree(int[] weights) {
		this.charset = "";
		
		//默认字符集是从‘A’开始的weight.length个字符
		for (int i = 0; i < weights.length; i++) {
			this.charset += (char)('A'+i);
		}
		
		int n = weights.length;
		
		this.huftree = new TriElement[2*n-1];
		
		for (int i = 0; i < n; i++) {
			this.huftree[i] = new TriElement(weights[i]);
		}
		
		for (int i = n; i < 2*n-1; i++) {
			//最小和次小的权值，初值为最大的整数
			int min1 = Integer.MAX_VALUE, min2 = min1;
			
			//最小和次小权值的下标
			int x1 = -1, x2 = -1;
			
			for (int j = 0; j < i; j++) {
				//无父母的节点
				if(this.huftree[j].parent == -1){
					//小于最小权值的节点
					if(this.huftree[j].data<min1){
						min2 = min1;
						x2 = x1;
						
						min1 = this.huftree[j].data;
						x1 = j;
					}else if(this.huftree[j].data<min2){
						//小于次小权值的节点
						min2 = huftree[j].data;
						x2 = j;
					}
				}
			}
			//合并两棵最小的子树，约定左孩子为最小
			this.huftree[x1].parent = i;
			this.huftree[x2].parent = i;
			//构造新的节点
			this.huftree[i] = new TriElement(min1+min2,-1,x1,x2);
		}
	}
	
	/**
	 * @param i 第i个字符的索引
	 * @return 字符编码——一字符串形式返回
	 */
	public String getCode(int i){
		int n = 8;
		char hufcode[] = new char[n];
		int child = i,parent = this.huftree[child].parent;
		
		for ( i = n-1; parent != -1; i--) {
			//由叶子节点向上直到根节点，反序储存编码
			hufcode[i] = (huftree[parent].left == child) ? '0' : '1' ;
			child = parent;
			parent = huftree[child].parent;
		}
		
		//由hufcode数组从i+1开始的n-1-i个字符构造串
		return new String(hufcode,i+1,n-1-i);
	}
	
	/**
	 * return Huffman编码以字符串形式输出
	 */
	public String toString(){
		String str = "Huffman数的节点数组：";
		for (int i = 0; i < this.huftree.length; i++) {
			str += this.huftree[i].toString()+", ";
		}
		
		str += "\nHuffman 编码：";
		
		for (int i = 0; i < this.charset.length(); i++) {
			str += this.charset.charAt(i)+"："+getCode(i)+", ";
		}
		return str;
	}
	
	/**
	 * @param text
	 * @return 压缩后的编码
	 */
	public String encode(String text){
		String compressed = "";
		//被压缩的数据以字符串显示
		for (int i = 0; i < text.length(); i++) {
			//默认字符串是从A开始的n个字符
			compressed += getCode(text.charAt(i)-'A');
		}
		return compressed;
	}
	
	/**
	 * @param compressed
	 * @return 解码的数据
	 */
	public String decode(String compressed){
		String text = "";
		//搜索一条从根到达叶子的路径
		int node = this.huftree.length-1;
		
		for (int i = 0; i < compressed.length(); i++) {
			if(compressed.charAt(i) == '0'){
				node = huftree[node].left;
			}else{
				node = huftree[node].right;
			}
			
			if(huftree[node].isLeaf()){
				//获得一个字符
				text += this.charset.charAt(node);
				//再次从根开始搜索
				node = huftree.length - 1;
			}
		}
		return text;
	}
}
