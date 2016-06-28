package com.data.huffman;

public class Test {
	public static void main(String[] args) {
		//ѹ���ַ���
		String text = "AAAABBBCDDBBAAA";
		//��Ӧ��Ȩֵ
		int[] weights = {7,5,1,2};
		
		HuffmanTree huffman = new HuffmanTree(weights);
		
		System.out.println(huffman.toString());
		//ѹ����ı���
		String compressed = huffman.encode(text);
		
		System.out.println("��"+text+"ѹ��Ϊ"+compressed+", "+compressed.length()+"λ");
		System.out.println("��"+compressed+"����Ϊ"+huffman.decode(compressed));
		
//		System.out.println('A'-'A');
		System.out.println("ABCD".indexOf('A'));
		System.out.println("ABCD".charAt(0));
	}
}
