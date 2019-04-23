package hw5;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class PhoneUtil {
	public static void prependOne(Map<String, BigInteger> m){
		for(Map.Entry<String, BigInteger> entry : m.entrySet()){
			BigInteger x=entry.getValue().add(BigDecimal.valueOf(1e10).toBigInteger());
			entry.setValue(x);
		}
	}
}
