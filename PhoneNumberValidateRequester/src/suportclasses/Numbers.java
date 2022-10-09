package suportclasses;

import java.util.ArrayList;

public class Numbers{ //Esta classe gera uma lista de números a serem validados em sequência de acordo com uma quantidade selecionada pelo usuário
ArrayList<String> numbers = new ArrayList<String>(); //Lista que armazena os números gerados
	
	//Construtor
	public Numbers(String starting_number, int quantity) {
		numbers.add(starting_number); //O primeiro número a ser adicionado é o número inicial passado pelo usuário. Todos os próximos números serão gerados a partir deste
		
		//Laço que gera os números restantes a serem validados
		for(int i = 1; i < quantity; i++) {
			numbers.add(Long.toString(Long.parseLong(starting_number) + i));
		}
	}

	//Getter
	public ArrayList<String> getNumbers() {
		return numbers;
	}

	//Setter
	public void setNumbers(ArrayList<String> numbers) {
		this.numbers = numbers;
	}
}