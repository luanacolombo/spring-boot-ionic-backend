package com.example.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) { //decodeParam descodificar um parametro
		try {
			return URLDecoder.decode(s, "UTF-8"); //caso de algum erro nesse processo
		} 
		catch (UnsupportedEncodingException e) {
			return ""; //retorna uma String vazia
		}
	}
	
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(","); //split é uma função que pega o string e recorta ele em pedacinhos com base no caracter passado
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i])); //parseInt converte o elemento na posição i do vetor p/ inteiro
		}
		return list;
		//ou troca o códito todo por Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

}
