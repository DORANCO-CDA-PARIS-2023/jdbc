package doranco;


import doranco.service.ActionData;
import doranco.service.ActionType;
import doranco.service.DaoCLI;
import doranco.service.DataType;

import java.util.Scanner;

public class App {

    public static void main( String[] args ) {
        DaoCLI daoCLI = new DaoCLI(System.in);
        daoCLI.start();
    }

}
