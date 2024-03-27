import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class App {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        BufferedReader reader = null;

        try {
            // URL del endpoint al que deseas hacer la solicitud GET
            String url = "http://localhost:3000/login";

            // Crea una solicitud GET
            HttpGet httpGet = new HttpGet(url);

            // Ejecuta la solicitud y obtiene la respuesta
            HttpResponse response = httpClient.execute(httpGet);

            // Obtiene el cuerpo de la respuesta
            HttpEntity entity = response.getEntity();
            reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuffer responseBuffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuffer.append(line);
            }

            // Imprime la respuesta
            System.out.println("Código de respuesta: " + response.getStatusLine().getStatusCode());
            System.out.println("Respuesta del servidor:");
            System.out.println(responseBuffer.toString());
        } catch (IOException e) {
            // Manejar el error de resolución de nombres
            System.err.println("Error al resolver el nombre del host:");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
