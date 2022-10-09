package concurrent;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ReqThread extends Thread{ //ReqThread significa "Requester Thread", em menção das requisições que serão executadas em cada thread
	private String number = new String(); //Número que será passado na requisição para validação
	
	//Construtor
	public ReqThread(String number) {
		this.number = number;
	}
	
	//Sobrescrição do método run()
	@Override
	public void run() {
		try {
			//Antes de executar a requisição, a thread dorme por 200ms. O objetivo disso é para que a API não identifique essas requisições como um ataque e o IP seja bloqueado
			Thread.sleep(200); 

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
		
		//Possíveis exceções
		catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}