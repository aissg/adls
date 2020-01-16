package rfeedinput;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Random;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.IfExists;

public class StratifyBaseFactorFileGenerator {
	
	private static String  FILE_PATH = "/input_data/jay/sc_date_500k.csv";	
	private DataLakeClientFactory clientFactory;
	private Map<Integer, String> wedensdaysMap;
	private int wedensdayMapSize;
	
	private int nSim;
	private int periods;

	
	
	public StratifyBaseFactorFileGenerator(DataLakeClientFactory clientFactory,
											int nSim,
											int periods) {
		super();
		this.clientFactory = clientFactory;
		this.nSim = nSim;
		this.periods = periods;
		this.wedensdaysMap = LocalDateUtil.getAllWedensdaysSequentialMapFrom2007To2018();
		this.wedensdayMapSize = wedensdaysMap.size();

	} 
	
	public void generateFile() throws IOException
	{
		ADLStoreClient client = clientFactory.getClient();
		client.delete(FILE_PATH);
		System.out.println(FILE_PATH + " cleaned up");
		
		OutputStream stream = client.createFile(FILE_PATH, IfExists.OVERWRITE  );
		PrintStream out = new PrintStream(stream);
		
		for (int i = 1; i <= nSim; i++) 
		{
			for(int j=1; j <periods; j++)
			{
				String value = wedensdaysMap.get(new Random().nextInt(wedensdayMapSize));
				out.println(String.format("%d,%d,%s", i, j, value));
			}
		}
		out.close();
		System.out.println(FILE_PATH + "File created.");
		

	}

}
