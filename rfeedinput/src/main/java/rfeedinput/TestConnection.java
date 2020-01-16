package rfeedinput;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.oauth2.AccessTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.ClientCredsTokenProvider;

public class TestConnection {

    public static void main(String[] args) {
        try {

        	//lpajavaapp,  "98d22757-ddc9-4da1-9284-b8cb37c57f32" / "bAtnysoESii8cxlQ/1IXxdWf84KpppJuG8kgEdz4hGI="
        	// to get it working, in subscroption, add controlbutor role to the app. 
        	String clientId = "8d6ebe83-c3d9-45e0-8af8-44b161c82202";
            String authTokenEndpoint = "https://login.microsoftonline.com/ce61bf39-0079-493e-ac3d-5c91e990aedd/oauth2/token";
            String clientKey = "rq24ptvM35TURDqoy+t/XRA77rEiguOTFLICHJhtwzA=";

            AccessTokenProvider provider = new ClientCredsTokenProvider(authTokenEndpoint, clientId, clientKey);
            String accountFQDN = "lpadatalake.azuredatalakestore.net";  // full account FQDN, not just the account name
            ADLStoreClient client = ADLStoreClient.createClient(accountFQDN, provider);
            
            
            InputStream in = client.getReadStream("/input_data/ASX.INDEX.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ( (line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            System.out.println();
            System.out.println("File contents read.");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

    }
}