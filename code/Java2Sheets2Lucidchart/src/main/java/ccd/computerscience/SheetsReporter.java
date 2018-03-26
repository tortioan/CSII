package ccd.computerscience;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SheetsReporter {

    private static final String APPLICATION_NAME = "Java2Sheets2Lucidchart";

    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/ccd.charles.laheist.Java2Sheets2Lucidchart");

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static HttpTransport HTTP_TRANSPORT;

    private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    private static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in = SheetsReporter.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = null;
        try {
            credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        //System.out.println(
        //        "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    private static Sheets service = null;

    private static Sheets getSheetsService() throws IOException {
        if (service == null) {
            Credential credential = authorize();
            service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        }
        return service;
    }

    private static String spreadsheetId = "1QpSaKr87Fj6NL9Fn5aBV42dbh7kFe-xyXdL7kS6jrfE";

    public static void printCurrentTable() {
        String range = "Model Data!A2:C";
        try {
            ValueRange response = getSheetsService().spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            if (values == null || values.size() == 0) {
                System.out.println("No data found.");
            } else {
                System.out.printf("%30s %35s %30s\n","Title","Module Name","Level of Completion");
                for (List row : values) {
                    System.out.printf("%30s %35s %30s\n", row.get(0), row.get(1), row.get(2) );
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private static String findModule(String modulename) {
        String range = "Model Data!B:B";
        int row_count = 0;
        try {
            ValueRange response = getSheetsService().spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            if (values == null || values.size() == 0) {
                System.out.println("No data found.");
            } else {
                for (List row : values) {
                    //System.out.println("Comparing: " + modulename + " to " + row.get(0).toString() + modulename.compareToIgnoreCase(row.get(0).toString()) );
                    if ( modulename.compareToIgnoreCase(row.get(0).toString()) == 0 )
                        return "Model Data!C" + (row_count+1) + ":C" + (row_count+1);
                    ++row_count;
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return "";
    }

    public static int updateReadiness(String module, int value) {
        String range = findModule(module);
        //System.out.println("Updating: " + module + " with value " + value + " at location " + range );
        if ( range.length() < 1 ) {
            return -1;
        }
        List<List<Object>> newvalues = Arrays.asList(
                Arrays.asList( ((Object) new Integer(value)) ) );
        ValueRange updateCell = new ValueRange().setValues(newvalues);
        String valueInputOption = "RAW";
        try {
            Sheets.Spreadsheets.Values.Update request =
                    getSheetsService().spreadsheets().values().update(spreadsheetId, range, updateCell);
            request.setValueInputOption(valueInputOption);
            UpdateValuesResponse uresponse = request.execute();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return 0;
    }
}
