package erp.core;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import erp.core.exception.UnrecognizedEntryException;
import erp.core.exception.parserExceptions.IntegerException;
import erp.core.exception.parserExceptions.StringException;

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
        } catch(StringException | IntegerException e){
            throw new UnrecognizedEntryException("Erro ao processar linha " + line + "Erro = " + e.getMessage() );
        }
    }
    private void checkCorrectNumberOfFields(String[] components, int expectedSize, String line) throws UnrecognizedEntryException{
        if(components.length != expectedSize)
            throw new UnrecognizedEntryException("Número inválido de campos (" + components.length + ") na linha: " + line);
    }
    // COMPANY:nome
    private void parseCompany(String[] components, String line) throws UnrecognizedEntryException, StringException, IntegerException {
        checkCorrectNumberOfFields(components, 2, line );
        _erpMan.registerCompany(components[1]);
    }

     // IMPORTED:nome:prec¸oBase:categoria:prazo:paisOrigem:taxa
    private void parseImported(String[] components, String line) throws UnrecognizedEntryException, StringException, IntegerException {
        checkCorrectNumberOfFields(components, 7, line);
        _erpMan.registerImportedProduct(components[1], Integer.valueOf(components[2]), ProductCategory.valueOf(components[3]),
                                     Integer.valueOf(components[4]), components[5], Double.valueOf(components[6]));
    }


  // NATIONAL:nome:prec¸oBase:categoria:prazo
    private void parseNational(String[] components, String line) throws UnrecognizedEntryException, StringException, IntegerException {
        checkCorrectNumberOfFields(components, 5, line);
        _erpMan.registerNationalProduct(components[1], Integer.valueOf(components[2]), ProductCategory.valueOf(components[3]),
                                     Integer.valueOf(components[4]));
    }
  
  // CLIENT:nome:email
    private void parseClient(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 3, line);
        _erpMan.registerClient(components[1], components[2]);
    }

  // SUPPLY:idEmpresa:idProduto:unidades:factor
    private void parseSupply(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 5, line);
        _erpMan.updateStock(Integer.valueOf(components[1]), Integer.valueOf(components[2]),
                         Integer.valueOf(components[3]), Double.valueOf(components[4]));
    }
 
  // RATE:modalidade:custo:dias;
    private void parseRate(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 4, line);
        _erpMan.registeShippingRate(components[1], Integer.valueOf(components[2]), Integer.valueOf(components[3]));
    }
}
