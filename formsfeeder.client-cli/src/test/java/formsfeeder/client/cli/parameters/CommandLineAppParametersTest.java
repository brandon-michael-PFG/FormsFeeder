package formsfeeder.client.cli.parameters;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandLineAppParametersTest {
	private static final String HOST_LOCATION_SHORT_OPTION = "-h";
	private static final String HOST_LOCATION_LONG_OPTION = "--host";
	private static final String CONTEXT_ROOT_SHORT_OPTION = "-c";
	private static final String CONTEXT_ROOT_LONG_OPTION = "-contextroot";
	private static final String USER_CREDENTIALS_SHORT_OPTION = "-u";
	private static final String USER_CREDENTIALS_LONG_OPTION = "--user";
	private static final String HEADER_SHORT_OPTION = "-hdr";
	private static final String HEADER_LONG_OPTION = "--header";
	private static final String DATA_SOURCE_SHORT_OPTION = "-d";
	private static final String DATA_SOURCE_LONG_OPTION = "--data";	
	private static final String OUTPUT_LOCATION_SHORT_OPTION = "-o";
	private static final String OUTPUT_LOCATION_LONG_OPTION = "--output";
	private static final String PLUGIN_SHORT_OPTION = "-p";
	private static final String PLUGIN_LONG_OPTION = "--plugin";
	private static final String VERBOSE_SHORT_OPTION = "-v";	
	private static final String VERBOSE_LONG_OPTION = "--verbose";
	
	private static final String HOST_MACHINE_NAME = "localhost";
	private static final String HOST_PORT = "8080";
	private static final String HOST_LOCATION_PARAM = "http://" + HOST_MACHINE_NAME + ":" + HOST_PORT;
	private static final String CONTEXT_ROOT_PARAM = "/context/root/";
	private static final String USER_CREDENTIALS_USERNAME = "username";
	private static final String USER_CREDENTIALS_PASSWORD = "password";
	private static final String HEADER_1_KEY = "header1";
	private static final String HEADER_1_VALUE = "headervalue1";
	private static final String HEADER_1_PARAM = HEADER_1_KEY + "=" + HEADER_1_VALUE;
	private static final String HEADER_2_KEY = "header2";
	private static final String HEADER_2_VALUE = "headervalue2";
	private static final String HEADER_2_PARAM = HEADER_2_KEY + "=" + HEADER_2_VALUE;
	private static final String USER_CREDENTIALS_PARAM = USER_CREDENTIALS_USERNAME + ":" + USER_CREDENTIALS_PASSWORD;
	private static final String OUTPUT_LOCATION_PARAM = "output/output.txt";
	private static final String PLUGIN_PARAM = "Debug";
	private static final String DATA_SOURCE_1_NAME = "datasource1";
	private static final String DATA_SOURCE_2_NAME = "datasource2";
	private static final String DATA_SOURCE_3_NAME = "datasource3";
	private static final String DATA_SOURCE_1_VALUE = "textString1";
	private static final String DATA_SOURCE_2_VALUE = "@data/data2.dat";
	private static final String DATA_SOURCE_3_VALUE = "textString3";
	private static final String DATA_SOURCE_1_PARAM = DATA_SOURCE_1_NAME + "=" + DATA_SOURCE_1_VALUE;
	private static final String DATA_SOURCE_2_PARAM = DATA_SOURCE_2_NAME + "=" + DATA_SOURCE_2_VALUE;
	private static final String DATA_SOURCE_3_PARAM = DATA_SOURCE_3_NAME + "=" + DATA_SOURCE_3_VALUE;
 
	private static final String VALID_HOST_LOCATION_SHORT_PAIR = HOST_LOCATION_SHORT_OPTION + " " + HOST_LOCATION_PARAM;
	private static final String VALID_CONTEXT_ROOT_SHORT_PAIR = CONTEXT_ROOT_SHORT_OPTION + " " + CONTEXT_ROOT_PARAM;
	private static final String VALID_USER_CREDENTIALS_SHORT_PAIR = USER_CREDENTIALS_SHORT_OPTION + " " + USER_CREDENTIALS_PARAM;
	private static final String VALID_HEADER_1_SHORT_PAIR = HEADER_SHORT_OPTION + " " + HEADER_1_PARAM;
	private static final String VALID_HEADER_2_SHORT_PAIR = HEADER_SHORT_OPTION + " " + HEADER_2_PARAM;
	private static final String VALID_OUTPUT_LOCATION_SHORT_PAIR = OUTPUT_LOCATION_SHORT_OPTION + " " + OUTPUT_LOCATION_PARAM;
	private static final String VALID_PLUGIN_SHORT_PAIR = PLUGIN_SHORT_OPTION + " " + PLUGIN_PARAM;
	private static final String DATA_SOURCE_1_SHORT_PAIR = DATA_SOURCE_SHORT_OPTION + " " + DATA_SOURCE_1_PARAM;
	private static final String DATA_SOURCE_2_SHORT_PAIR = DATA_SOURCE_SHORT_OPTION + " " + DATA_SOURCE_2_PARAM;
	private static final String DATA_SOURCE_3_SHORT_PAIR = DATA_SOURCE_SHORT_OPTION + " " + DATA_SOURCE_3_PARAM;

	private static final String VALID_HOST_LOCATION_LONG_PAIR = HOST_LOCATION_LONG_OPTION + " " + HOST_LOCATION_PARAM;
	private static final String VALID_CONTEXT_ROOT_LONG_PAIR = CONTEXT_ROOT_LONG_OPTION + " " + CONTEXT_ROOT_PARAM;
	private static final String VALID_USER_CREDENTIALS_LONG_PAIR = USER_CREDENTIALS_LONG_OPTION + " " + USER_CREDENTIALS_PARAM;
	private static final String VALID_HEADER_1_LONG_PAIR = HEADER_LONG_OPTION + " " + HEADER_1_PARAM;
	private static final String VALID_HEADER_2_LONG_PAIR = HEADER_LONG_OPTION + " " + HEADER_2_PARAM;
	private static final String VALID_OUTPUT_LOCATION_LONG_PAIR = OUTPUT_LOCATION_LONG_OPTION + " " + OUTPUT_LOCATION_PARAM;
	private static final String VALID_PLUGIN_LONG_PAIR = PLUGIN_LONG_OPTION + " " + PLUGIN_PARAM;
	private static final String DATA_SOURCE_1_LONG_PAIR = DATA_SOURCE_LONG_OPTION + " " + DATA_SOURCE_1_PARAM;
	private static final String DATA_SOURCE_2_LONG_PAIR = DATA_SOURCE_LONG_OPTION + " " + DATA_SOURCE_2_PARAM;
	private static final String DATA_SOURCE_3_LONG_PAIR = DATA_SOURCE_LONG_OPTION + " " + DATA_SOURCE_3_PARAM;

	private static final String VALID_SHORT_ARG_STRING = 
			 VALID_HOST_LOCATION_SHORT_PAIR + " "
		   + VALID_CONTEXT_ROOT_SHORT_PAIR + " "
	       + VALID_USER_CREDENTIALS_SHORT_PAIR + " "
		   + VALID_HEADER_1_SHORT_PAIR + " "
		   + VALID_HEADER_2_SHORT_PAIR + " "
		   + VALID_OUTPUT_LOCATION_SHORT_PAIR + " "
		   + VALID_PLUGIN_SHORT_PAIR + " "
		   + DATA_SOURCE_1_SHORT_PAIR + " "
		   + DATA_SOURCE_2_SHORT_PAIR + " "
		   + DATA_SOURCE_3_SHORT_PAIR + " "
		   + VERBOSE_SHORT_OPTION
		   ;

	private static final String VALID_LONG_ARG_STRING  =
				 VALID_USER_CREDENTIALS_LONG_PAIR + " "
			   + VERBOSE_LONG_OPTION + " "
		       + DATA_SOURCE_1_LONG_PAIR + " "
		       + DATA_SOURCE_2_LONG_PAIR + " "
			   + VALID_CONTEXT_ROOT_LONG_PAIR + " "
			   + VALID_HEADER_2_LONG_PAIR + " "
		       + DATA_SOURCE_3_LONG_PAIR + " "
		       + VALID_HOST_LOCATION_LONG_PAIR + " "
			   + VALID_PLUGIN_LONG_PAIR + " "
			   + VALID_OUTPUT_LOCATION_LONG_PAIR + " "
			   + VALID_HEADER_1_LONG_PAIR + " "
			   ;	// Intentionally using different orders in the different variations.
	
	private static final String VALID_MIXED_ARG_STRING = 
			     VERBOSE_SHORT_OPTION + " "
			   + VALID_USER_CREDENTIALS_SHORT_PAIR + " "
			   + VALID_OUTPUT_LOCATION_LONG_PAIR + " "
			   + VALID_HEADER_1_LONG_PAIR + " "
			   + DATA_SOURCE_1_LONG_PAIR + " "
			   + VALID_CONTEXT_ROOT_SHORT_PAIR + " "
			   + DATA_SOURCE_2_SHORT_PAIR + " "
			   + VALID_PLUGIN_LONG_PAIR + " "
			   + DATA_SOURCE_3_SHORT_PAIR + " "
			   + VALID_HOST_LOCATION_LONG_PAIR + " "
			   + VALID_HEADER_2_SHORT_PAIR
			   ;
	
	private static final String NO_OPTIONAL_ARGS_STRING = 
		     VALID_HOST_LOCATION_LONG_PAIR + " "
		   + VALID_PLUGIN_SHORT_PAIR
		     ;

	@ParameterizedTest
	@ValueSource(strings = { 
			VALID_SHORT_ARG_STRING, 
			VALID_LONG_ARG_STRING,
			VALID_MIXED_ARG_STRING,
			})
	void testParseArgs(String cmdLine) throws Exception {
		final String[] args = breakSimpleString(cmdLine);
		final AppParameters underTest = CommandLineAppParameters.parseArgs(args);

		// Everything should be present.
		assertAll(
				()->assertTrue(underTest.output().isPresent()),
				()->assertTrue(underTest.authParameters().isPresent()),
				()->assertEquals(3, underTest.dataSourceInfos().size()),
				()->assertTrue(underTest.headers().isPresent()),
				()->assertTrue(underTest.contextRoot().isPresent())
				);
		Path outputPath = underTest.output().get();
		AuthParameters.BasicAuthParameters authParams = ((AuthParameters.BasicAuthParameters)underTest.authParameters().get());
		List<DataSourceInfo> dataSourceInfos = underTest.dataSourceInfos();
		
		assertAll(
				()->assertFalse(underTest.hostParameters().useSsl()),
				()->assertEquals(HOST_MACHINE_NAME , underTest.hostParameters().hostName()),
				()->assertEquals(Integer.valueOf(HOST_PORT), underTest.hostParameters().hostPort()),
				()->assertEquals(CONTEXT_ROOT_PARAM, underTest.contextRoot().get()),
				()->assertEquals(Paths.get(OUTPUT_LOCATION_PARAM).toString(), outputPath.toString()),
				()->assertEquals(USER_CREDENTIALS_USERNAME, authParams.username()),
				()->assertEquals(USER_CREDENTIALS_PASSWORD, authParams.password()),
				()->assertEquals(HEADER_1_VALUE, underTest.headers().get().get(HEADER_1_KEY)),
				()->assertEquals(HEADER_2_VALUE, underTest.headers().get().get(HEADER_2_KEY)),
				()->assertEquals(DATA_SOURCE_1_NAME, dataSourceInfos.get(0).name()),
				()->assertEquals(DATA_SOURCE_1_VALUE, dataSourceInfos.get(0).value()),
				()->assertEquals(DATA_SOURCE_2_NAME, dataSourceInfos.get(1).name()),
				()->assertEquals(Paths.get(DATA_SOURCE_2_VALUE.substring(1)), dataSourceInfos.get(1).path()),
				()->assertEquals(DATA_SOURCE_3_NAME, dataSourceInfos.get(2).name()),
				()->assertEquals(DATA_SOURCE_3_VALUE, dataSourceInfos.get(2).value()),
				()->assertEquals(PLUGIN_PARAM, underTest.plugin()),
				()->assertTrue(underTest.verbose())
				);
	}

	@ParameterizedTest
	@ValueSource(strings = { 
			NO_OPTIONAL_ARGS_STRING
			})
	void testParseArgs_NoOptional(String cmdLine) throws Exception {
		final String[] args = breakSimpleString(cmdLine);
		final AppParameters underTest = CommandLineAppParameters.parseArgs(args);

		assertAll(
				()->assertFalse(underTest.hostParameters().useSsl()),
				()->assertEquals(HOST_MACHINE_NAME , underTest.hostParameters().hostName()),
				()->assertEquals(Integer.valueOf(HOST_PORT), underTest.hostParameters().hostPort()),
				()->assertFalse(underTest.authParameters().isPresent()),
				()->assertEquals(0, underTest.dataSourceInfos().size()),
				()->assertFalse(underTest.output().isPresent()),
				()->assertFalse(underTest.verbose())
				);
	}
	
	private String[] breakSimpleString(String cmdLine) {
		return cmdLine.split(" ");
	}
	
	@Test
	void testGetUsage() {
		String result = CommandLineAppParameters.getUsage();;
		
		assertAll(
				()->assertTrue(result.contains(HOST_LOCATION_SHORT_OPTION), "Expected '" + result + "' to contain '" + HOST_LOCATION_SHORT_OPTION + "'."),
				()->assertTrue(result.contains(CONTEXT_ROOT_SHORT_OPTION), "Expected '" + result + "' to contain '" + CONTEXT_ROOT_SHORT_OPTION + "'."),
				()->assertTrue(result.contains(USER_CREDENTIALS_SHORT_OPTION), "Expected '" + result + "' to contain '" + USER_CREDENTIALS_SHORT_OPTION + "'."),
				()->assertTrue(result.contains(HEADER_SHORT_OPTION), "Expected '" + result + "' to contain '" + HEADER_SHORT_OPTION + "'."),
				()->assertTrue(result.contains(DATA_SOURCE_SHORT_OPTION), "Expected '" + result + "' to contain '" + DATA_SOURCE_SHORT_OPTION + "'."),
				()->assertTrue(result.contains(OUTPUT_LOCATION_SHORT_OPTION), "Expected '" + result + "' to contain '" + OUTPUT_LOCATION_SHORT_OPTION + "'."),
				()->assertTrue(result.contains(VERBOSE_SHORT_OPTION), "Expected '" + result + "' to contain '" + VERBOSE_SHORT_OPTION + "'.")
				);
	}

	@ParameterizedTest
	@ValueSource(strings = { 
			VALID_SHORT_ARG_STRING + " -b",
			VALID_SHORT_ARG_STRING + " -b foo",
			VALID_SHORT_ARG_STRING + " --badArg",
			VALID_SHORT_ARG_STRING + " --badArg foo",
			})
	void testBadParameter(String cmdLine) {
		final String[] args = breakSimpleString(cmdLine);
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->CommandLineAppParameters.parseArgs(args));
		String msg = ex.getMessage();
		assertNotNull(msg);
		assertTrue(msg.contains("Unrecognized option"), "Expected '" + msg + "' to contain 'Unrecognized option'.");
	}
	
	@ParameterizedTest
	@ValueSource(strings = { VALID_USER_CREDENTIALS_SHORT_PAIR + " " + VALID_HOST_LOCATION_SHORT_PAIR, VALID_PLUGIN_LONG_PAIR + " " + VALID_USER_CREDENTIALS_SHORT_PAIR })
	void testMissingParameter(String cmdLine) {
		final String[] args = breakSimpleString(cmdLine);
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->CommandLineAppParameters.parseArgs(args));
		String msg = ex.getMessage();
		assertNotNull(msg);
		assertTrue(msg.contains("Missing required option"), "Expected '" + msg + "' to contain 'Missing required option'.");
	}

}
