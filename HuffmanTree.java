package com.data.huffman;

/**
 * HuffmanTree��
 * @author zifeng
 *
 */
public class HuffmanTree {
	//�ַ���
	private String charset;
	//��̬������������
	private TriElement[] huftree;
	
	/**
	 * @param weights:���ַ��ڵ��Ȩֵ����
	 */
	public HuffmanTree(int[] weights) {
		this.charset = "";
		
		//Ĭ���ַ����Ǵӡ�A����ʼ��weight.length���ַ�
		for (int i = 0; i < weights.length; i++) {
			this.charset += (char)('A'+i);
		}
		
		int n = weights.length;
		
		this.huftree = new TriElement[2*n-1];
		
		for (int i = 0; i < n; i++) {
			this.huftree[i] = new TriElement(weights[i]);
		}
		
		for (int i = n; i < 2*n-1; i++) {
			//��С�ʹ�С��Ȩֵ����ֵΪ��������
			int min1 = Integer.MAX_VALUE, min2 = min1;
			
			//��С�ʹ�СȨֵ���±�
			int x1 = -1, x2 = -1;
			
			for (int j = 0; j < i; j++) {
				//�޸�ĸ�Ľڵ�
				if(this.huftree[j].parent == -1){
					//С����СȨֵ�Ľڵ�
					if(this.huftree[j].data<min1){
						min2 = min1;
						x2 = x1;
						
						min1 = this.huftree[j].data;
						x1 = j;
					}else if(this.huftree[j].data<min2){
						//С�ڴ�СȨֵ�Ľڵ�
						min2 = huftree[j].data;
						x2 = j;
					}
				}
			}
			//�ϲ�������С��������Լ������Ϊ��С
			this.huftree[x1].parent = i;
			this.huftree[x2].parent = i;
			//�����µĽڵ�
			this.huftree[i] = new TriElement(min1+min2,-1,x1,x2);
		}
	}
	
	/**
	 * @param i ��i���ַ�������
	 * @return �ַ����롪��һ�ַ�����ʽ����
	 */
	public String getCode(int i){
		int n = 8;
		char hufcode[] = new char[n];
		int child = i,parent = this.huftree[child].parent;
		
		for ( i = n-1; parent != -1; i--) {
			//��Ҷ�ӽڵ�����ֱ�����ڵ㣬���򴢴����
			hufcode[i] = (huftree[parent].left == child) ? '0' : '1' ;
			child = parent;
			parent = huftree[child].parent;
		}
		
		//��hufcode�����i+1��ʼ��n-1-i���ַ����촮
		return new String(hufcode,i+1,n-1-i);
	}
	
	/**
	 * return Huffman�������ַ�����ʽ���
	 */
	public String toString(){
		String str = "Huffman���Ľڵ����飺";
		for (int i = 0; i < this.huftree.length; i++) {
			str += this.huftree[i].toString()+", ";
		}
		
		str += "\nHuffman ���룺";
		
		for (int i = 0; i < this.charset.length(); i++) {
			str += this.charset.charAt(i)+"��"+getCode(i)+", ";
		}
		return str;
	}
	
	/**
	 * @param text
	 * @return ѹ����ı���
	 */
	public String encode(String text){
		String compressed = "";
		//��ѹ�����������ַ�����ʾ
		for (int i = 0; i < text.length(); i++) {
			//Ĭ���ַ����Ǵ�A��ʼ��n���ַ�
			compressed += getCode(text.charAt(i)-'A');
		}
		return compressed;
	}
	
	/**
	 * @param compressed
	 * @return ���������
	 */
	public String decode(String compressed){
		String text = "";
		//����һ���Ӹ�����Ҷ�ӵ�·��
		int node = this.huftree.length-1;
		
		for (int i = 0; i < compressed.length(); i++) {
			if(compressed.charAt(i) == '0'){
				node = huftree[node].left;
			}else{
				node = huftree[node].right;
			}
			
			if(huftree[node].isLeaf()){
				//���һ���ַ�
				text += this.charset.charAt(node);
				//�ٴδӸ���ʼ����
				node = huftree.length - 1;
			}
		}
		return text;
	}
}
