package erp.core;

import erp.core.exception.parserExceptions.StringException;
import erp.core.exception.parserExceptions.IntegerException;

public interface Verifier {
    default String verifiedName(String name, String origin) throws StringException{
		if(name.length() == 0)
			throw new StringException(origin);
		return name;
	}
    default int verifiedPositiveInt(int i, String origin) throws IntegerException{
        if(i <= 0)
            throw new IntegerException(origin, false);
        return i;  
    }
    default int verifiedZeroOrMoreInt(int i, String origin) throws IntegerException{
        if(i < 0)
            throw new IntegerException(origin, true);
        return i;  
    }
}
