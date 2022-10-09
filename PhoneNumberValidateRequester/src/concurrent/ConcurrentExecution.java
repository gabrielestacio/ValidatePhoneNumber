package concurrent;

import java.util.ArrayList;

import suportclasses.Numbers;

public class ConcurrentExecution{ //Classe que executa a versão concorrente do programa
	static ArrayList<ReqThread> threads = new ArrayList<ReqThread>(); //Lista que armazenará as threads criadas para execução das requisições
	
	//Criação de cada thread, recebendo como parâmetro o número a ser validado
	public static void createThreads(ArrayList<String> numbers) {
		for(int i = 0; i < numbers.size(); i++) {
			threads.add(new ReqThread(numbers.get(i)));
		}
	}
	
	//Inicialização de todas as threads
	public static void startThreads() {
		for(int i = 0; i < threads.size(); i++) {
			threads.get(i).start();
		}
	}
	
	//Main
	public static void main(String[] args) {
		//Criação de uma lista automaticamente gerada de números que serão passados como requisição à API
		Numbers list = new Numbers(args[0], Integer.parseInt(args[1]));
		
		//Execução concorrente da validação de cada número presente na lista
		createThreads(list.getNumbers());
		startThreads();
	}
}