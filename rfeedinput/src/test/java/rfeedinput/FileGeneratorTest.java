package rfeedinput;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FileGeneratorTest {

	private DataLakeClientFactory dataLakeClientFactory; 
	
	private static int NSIM = 500;
	private static int PERIODS = 1;
	private static int NUMRISKFACTORS = 10;
	
    @BeforeEach
    void setUp() 
    {
    	dataLakeClientFactory = new DataLakeClientFactory();
    }
    
    @DisplayName("Prepare to generate StratifyBaseFactorFile")
    @Nested
    class StratifyBaseFactorFileGeneratorTest 
    {
    	StratifyBaseFactorFileGenerator stratifyBaseFactorFileGenerator;
    	
        @BeforeEach
        void setUp() 
        {
        	stratifyBaseFactorFileGenerator = new StratifyBaseFactorFileGenerator(dataLakeClientFactory, NSIM, PERIODS);
        }
        
        @Test
        @DisplayName("Generating StratifyBaseFactorFile")
        public void testStratifyBaseFactorFileGenerate() throws IOException 
        {
        	stratifyBaseFactorFileGenerator.generateFile();
        }
        
    }
    
    @DisplayName("Prepare to generate RiskFactorTimeSeriesFileGenerator")
    @Nested
    class RiskFactorTimeSeriesFileGeneratorTest 
    {
    	RiskFactorTimeSeriesFileGenerator riskFactorTimeSeriesFileGenerator;
    	
        @BeforeEach
        void setUp() 
        {
        	riskFactorTimeSeriesFileGenerator = new RiskFactorTimeSeriesFileGenerator(dataLakeClientFactory, NUMRISKFACTORS);
        }
        
        @Test
        @DisplayName("Generating RiskFactorTimeSeriesFile")
        public void testStratifyBaseFactorFileGenerate() throws IOException 
        {
        	riskFactorTimeSeriesFileGenerator.generateFile();
        }
        
    }

}
