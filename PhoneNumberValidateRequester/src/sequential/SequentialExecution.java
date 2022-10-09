package sequential;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import suportclasses.Numbers;

public class SequentialExecution{ //Classe que executa a versão sequencial do programa
	
	//Método que constrói e envia a mensagem HTTP com a requisição, além de receber e processar a resposta enviada pela API e exibí-la em tela
	public static void makeRequest(String number) throws IOException, InterruptedException {
		//Construção e envio da mensagem HTTP a ser passada como requisição à API, contendo: chave de identificação e url do host, número a ser validado, país ao qual o número pertence e o método HTTP a ser executado
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://phonenumbervalidatefree.p.rapidapi.com/ts_PhoneNumberValidateTest.jsp?number=%2B"+number+"&country=BR"))
				.header("X-RapidAPI-Key", "2bfbb6b1dcmsh7265e24daba4478p1c81edjsn75312e13d747")
				.header("X-RapidAPI-Host", "phonenumbervalidatefree.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		//Recebimento da resposta enviada pela API
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		//Exibição em tela do conteúdo da resposta enviada pela API
		System.out.println(response.body());
	}
	
	//Main
	public static void main(String[] args) throws IOException, InterruptedException {
		//Criação de uma lista automaticamente gerada de números que serão passados como requisição à API
		Numbers list = new Numbers(args[0], Integer.parseInt(args[1]));
		
		//Execução sequencial da validação de cada número presente na lista
		for(int i = 0; i < list.getNumbers().size(); i++) {
			makeRequest(list.getNumbers().get(i));
		}
	}
}