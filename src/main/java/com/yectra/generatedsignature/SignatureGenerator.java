package com.yectra.generatedsignature;

import java.io.File;
import java.io.FileReader;
import java.io.ObjectStreamException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyRep;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.bc.BcPEMDecryptorProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SignatureGenerator {

	@Value("${consumerIdd}")
	private String consumerIdd;
	@Value("${priviateKeyVersionn}")
	private String priviateKeyVersionn;
	@Value("${filepathh}")
	private String filepathh;
	@Value("${filepasswordd}")
	private String filepasswordd;

	public String generateSignature(String timestamp) throws InvalidKeyException, NoSuchAlgorithmException,
			ObjectStreamException, UnsupportedEncodingException, SignatureException {
		SignatureGenerator generator = new SignatureGenerator();

		String consumerId = consumerIdd;
		String priviateKeyVersion = priviateKeyVersionn;
		String key = "-----BEGIN RSA PRIVATE KEY-----\r\n" + "Proc-Type: 4,ENCRYPTED\r\n"
				+ "DEK-Info: DES-EDE3-CBC,5F0A14554F20D928\r\n" + "\r\n"
				+ "9339zTanzEomfYOo/8c8mz7r2cI7FXtrl8VnEBtZv7w+M4Saj1xy9wAPLHVC7Zbj\r\n"
				+ "/dVgmamMzbzWzn3rPMlsJUOVVLF5U7KeKj0E6Sc7a1tRuyaXJm5BTd+vQAy46JET\r\n"
				+ "aOwvP3r+3nx/GjLLQ3zjXfOwEZFpZxczRFpx1EF1SLtMHxqBNdjOye3iCiOxGgxC\r\n"
				+ "c5qD1pTMdSH1S9dqHkXFuEPIxjUhVSlceVIbfPsMweLq/hB5SiBvAOP5DCP3Cl6D\r\n"
				+ "OYJCUGhwpW5ndZhxtK4Ft8SSFRd9ThjOAMEtVpYZrnYkbTgFSf31W0jheJd2neuJ\r\n"
				+ "OzZ/8imXtjhbkV2Nc33qT3BPJowzNOKWwbRA6ek1vt0xSfL6DhMM0VxRe0l2YfPq\r\n"
				+ "RX4dqN1zg2Sqqw+fSWhTq2Wd87bbDUApxqZ53/S1fHyqsnrp+uDm1YQCbadRtUMu\r\n"
				+ "SQ1vCfrzleyx2su1JxNCNClPEXeKO4YjBVNd81z3JmV803OIcP0vLO83Ej4X/aOm\r\n"
				+ "85jcYotpqgVPuxXtQlLgY4rXSjkq12nk4sJWHWaR3ctMWUhyBoykSPsWuKZP1dVr\r\n"
				+ "ygwCz9dL52aOgn3Bln4OPWlM/T3ysIeinKa4X+EKHpAMA8N3HG2NnG5rORLiPeqq\r\n"
				+ "iPKRyWb5VK1Lgjw50SkqDi7jrhYytV0Qsf7C8QU0Q0DFBGKYA5K7xOVOPteZw5zr\r\n"
				+ "+GHK31d33ITya5NJ06LKjtP2VHZT0hpn1GnXy2oASRlWitKG4JCzJEFV2rYBknWV\r\n"
				+ "qfaeput7k9D3y01tTJXrTTQZRU9SQRJHwCulxyKw4cND/a+uSWUFNyNZCascyqAq\r\n"
				+ "mrEiOPx/ds+IHsSycyl+v9CmjKOQiM9beMf/cq/6+ZAIpaJ0R4234rSKM9wTz0T4\r\n"
				+ "YpUSlaT2PWEe3qyG26A0ULpWiPwqh23tFeVsnW+npX0Dm92hfO9UNFRuW7jV7Rq1\r\n"
				+ "xzsU7AW/CA1jXUBcVDs9hV/CqGgtasklhvZ75EyTZO2pEx/3C3Pg5++YV1JChJmf\r\n"
				+ "R6mQz3bWJw8kpZJdF2G8aXeg4EtTYlP+pbm0L6g3W91fO+E+oIkYIw4uLdpg37MA\r\n"
				+ "7WzSGgzHfhcwtSNKTkV0DqoMB8WVirdCEdX6Ee6cHlp8P70Fu+Yxng0QCv8oNkqv\r\n"
				+ "pMWtTJaWkFu4OlHQCH8ziD05cpf8KdbKB4kIBI7mGt/hD11og1fvlQIKvd71j+UV\r\n"
				+ "n2+oXLiZV1H1GIrrQg5H4EVhtJugMDLjCTx+y1H2537bVjUZApyNwSIdsLc3wPkI\r\n"
				+ "NcxESo4YfITZ3Z1ZiBcfOHd4+E8iRvq+kLB2dgqhGYpNmuLvJU/gOZ3gG4X+N8eP\r\n"
				+ "oJ15QX5bhmanEu53hxPfljUFzdgAiA6R9F8s3WejzdALgpyxCAn1paS7QY0//ZLo\r\n"
				+ "YOKQQinmpkhe1fUocYYB+BcTLETJfP2GM9UGeoOvrRmcPJnhpTSX3AmGHqItxeuO\r\n"
				+ "ThISZNjw661MSREnaPbv34C+tVZwMnPwFYFmdzpQzVfjNznEGS7Kn/RV8b6pJ33s\r\n"
				+ "C33u1EACuul+lUFngrUyaPR8qzB3ix7KSYcrAYNQGozo6ydvBbQvmvkp4feH+JiB\r\n"
				+ "-----END RSA PRIVATE KEY-----\r\n";

		long intimestamp = System.currentTimeMillis();

		System.out.println("consumerId: " + consumerId);
		System.out.println("intimestamp: " + intimestamp);

		Map<String, String> map = new HashMap<>();
		map.put("WM_CONSUMER.ID", consumerId);
		map.put("WM_CONSUMER.INTIMESTAMP", Long.toString(intimestamp));
		map.put("WM_SEC.KEY_VERSION", priviateKeyVersion);

		String[] array = canonicalize(map);

		String data = null;

		data = generator.generateSignature(key, array[1], filepathh, filepasswordd);
		System.out.println(data);
		return data;
	}

	private String generateSignature(String key, String stringToSign, String filepathh, String filepasswordd)
			throws NoSuchAlgorithmException, ObjectStreamException, InvalidKeyException, UnsupportedEncodingException,
			SignatureException {
		Signature signatureInstance = Signature.getInstance("SHA256WithRSA");

		String signatureString = null;
		File file = new File(filepathh);
		String password = filepasswordd;
		try (PEMParser pemParser = new PEMParser(new FileReader(file))) {

			PEMEncryptedKeyPair encKeyPair = (PEMEncryptedKeyPair) pemParser.readObject();
			PEMKeyPair keyPair = encKeyPair.decryptKeyPair(new BcPEMDecryptorProvider(password.toCharArray()));

			JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
			PrivateKeyInfo privKeyInfo = keyPair.getPrivateKeyInfo();
			PrivateKey privKey = converter.getPrivateKey(privKeyInfo);
			signatureInstance.initSign(privKey);

			byte[] bytesToSign = stringToSign.getBytes("UTF-8");
			signatureInstance.update(bytesToSign);
			byte[] signatureBytes = signatureInstance.sign();

			signatureString = Base64.encodeBase64String(signatureBytes);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return signatureString;
	}

	private String[] canonicalize(Map<String, String> headersToSign) {
		StringBuffer canonicalizedStrBuffer = new StringBuffer();
		StringBuffer parameterNamesBuffer = new StringBuffer();
		Set<String> keySet = headersToSign.keySet();

		// Create sorted key set to enforce order on the key names
		SortedSet<String> sortedKeySet = new TreeSet<String>(keySet);
		for (String key : sortedKeySet) {
			Object val = headersToSign.get(key);
			parameterNamesBuffer.append(key.trim()).append(";");
			canonicalizedStrBuffer.append(val.toString().trim()).append("\n");
		}
		return new String[] { parameterNamesBuffer.toString(), canonicalizedStrBuffer.toString() };
	}

	class ServiceKeyRep extends KeyRep {
		private static final long serialVersionUID = -7213340660431987616L;

		public ServiceKeyRep(Type type, String algorithm, String format, byte[] encoded) {
			super(type, algorithm, format, encoded);
		}

		protected Object readResolve() throws ObjectStreamException {
			return super.readResolve();
		}
	}
}
