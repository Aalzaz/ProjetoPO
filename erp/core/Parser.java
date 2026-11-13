package erp.core;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import erp.core.exception.InvalidCompanyNameException;
import erp.core.exception.UnrecognizedEntryException;

class Parser {
    ErpManager _erpMan;
    
    Parser(ErpManager erpMan){
        _erpMan = erpMan;
    }

    void parserFile(String fileName) throws IOException, UnrecognizedEntryException {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            
            String line;

            while ((line = in.readLine()) != null) {
                parseLine(line);
            }
        }
    }

    private void parseLine(String line) throws UnrecognizedEntryException{
        String[] components = line.split(":");
        try{
            switch (components[0]) {
                case "COMPANY":
                    parseCompany(components, line);
                    break;
                case "IMPORTED":
                    parseImported(components, line);
                    break;
                case "NATIONAL":
                    parseNational(components, line);
                    break;
                case "CLIENT":
                    parseClient(components, line);
                    break;
                case "SUPPLY":
                    parseSupply(components, line);
                    break;
                case "RATE":
                    parseRate(components, line);
                    break;
                default:
                    throw new UnrecognizedEntryException("Entrada inválida: " + line);
            }
        } catch(InvalidCompanyNameException e){
            throw new UnrecognizedEntryException("Erro ao processar linha " + line + "Erro = " + e.getMessage() );
        }
    }
    private void checkCorretNumberOfFields(String[] components, String line, int expectedSize) throws UnrecognizedEntryException{
        if(components.length != expectedSize)
            throw new UnrecognizedEntryException("Número inválido de campos (" + components.length + ") na linha: " + line);
    }
    private void parseCompany(String[] components, String line) throws UnrecognizedEntryException {
        checkCorretNumberOfFields(components, line, 2 );
        _erpMan.registerCompany(components[1]);
    }
}
