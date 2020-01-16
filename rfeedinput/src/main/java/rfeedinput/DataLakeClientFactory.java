package rfeedinput;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.oauth2.AccessTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.ClientCredsTokenProvider;

public class DataLakeClientFactory {

	private ADLStoreClient client;
	
	private static String CLIENT_ID =  "8d6ebe83-c3d9-45e0-8af8-44b161c82202";
	private static String CLIENT_KEY = "rq24ptvM35TURDqoy+t/XRA77rEiguOTFLICHJhtwzA=";
	private static String AD_TOKEN_END_POINT = "https://login.microsoftonline.com/ce61bf39-0079-493e-ac3d-5c91e990aedd/oauth2/token";
	private static String ACCOUNT_FQDN =  "lpadatalake.azuredatalakestore.net";
	
	public DataLakeClientFactory()
	{
        AccessTokenProvider provider = new ClientCredsTokenProvider(AD_TOKEN_END_POINT, CLIENT_ID, CLIENT_KEY);
        this.client = ADLStoreClient.createClient(ACCOUNT_FQDN, provider);
	}

	public ADLStoreClient getClient() {
		return client;
	}

	public void setClient(ADLStoreClient client) {
		this.client = client;
	}
	

}
