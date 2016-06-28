package com.data.huffman;

public class Test {
	public static void main(String[] args) {
		//压缩字符串
		String text = "AAAABBBCDDBBAAA";
		//对应的权值
		int[] weights = {7,5,1,2};
		
		HuffmanTree huffman = new HuffmanTree(weights);
		
		System.out.println(huffman.toString());
		//压缩后的编码
		String compressed = huffman.encode(text);
		
		System.out.println("将"+text+"压缩为"+compressed+", "+compressed.length()+"位");
		System.out.println("将"+compressed+"解码为"+huffman.decode(compressed));
		
//		System.out.println('A'-'A');
		System.out.println("ABCD".indexOf('A'));
		System.out.println("ABCD".charAt(0));
	}
}
