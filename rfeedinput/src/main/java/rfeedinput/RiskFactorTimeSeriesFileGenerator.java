package rfeedinput;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.IfExists;

public class RiskFactorTimeSeriesFileGenerator {
	
	private static String  FILE_PATH = "/input_data/jay/rf_timseries.csv";	
	private DataLakeClientFactory clientFactory;
	private List<String> wedensdaysList;
	
	private int riskFactorLength;

	
	
	public RiskFactorTimeSeriesFileGenerator(DataLakeClientFactory clientFactory, int riskFactorLength) {
		super();
		this.clientFactory = clientFactory;
		this.wedensdaysList = LocalDateUtil.getAllWedensdaysSequentialListFrom2007To2018();
		this.riskFactorLength = riskFactorLength;

	} 
	
	public void generateFile() throws IOException
	{
		ADLStoreClient client = clientFactory.getClient();
		client.delete(FILE_PATH);
		System.out.println(FILE_PATH + " cleaned up");
		
		OutputStream stream = client.createFile(FILE_PATH, IfExists.OVERWRITE  );
		PrintStream out = new PrintStream(stream);
		
		StringBuffer line = new StringBuffer("sc_date");
		
		IntStream.range(0, riskFactorLength).forEach(i -> {line.append(",RF").append(new Integer(i).toString());});
		out.println(line);
		
		wedensdaysList.stream().forEach(date -> {
			
			StringBuffer line1 = new StringBuffer(date);

			IntStream.range(0, riskFactorLength).forEach(i -> {line1.append(",").append(new Double(new Random().nextDouble()).toString());});
			out.println(line1); });
		
		out.close();
		System.out.println(FILE_PATH + "File created.");
		

	}

}
