import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import oshi.SystemInfo;

public class Base64Class {

  private final String KEY = CPUid();

  public String encrypt(final String text) {
    return Base64.encodeBase64String(xor(text.getBytes()));
  }

  public String decrypt(final String hash) {
    return new String(xor(Base64.decodeBase64(hash.getBytes())), StandardCharsets.UTF_8);
  }

  private byte[] xor(final byte[] input) {
    final byte[] output = new byte[input.length];
    final byte[] secret = KEY.getBytes();
    int spos = 0;
    for (int pos = 0; pos < input.length; ++pos) {
      output[pos] = (byte) (input[pos] ^ secret[spos]);
      spos += 1;
      if (spos >= secret.length) {
        spos = 0;
      }
    }
    return output;
  }

  private String CPUid() {
    SystemInfo si = new SystemInfo();
    String processorId = si.getHardware().getProcessor().toString();
    String[] cpuId = processorId.split("\\s+"); //cpuId[26]
    System.out.println(cpuId[26]);
    return cpuId[26];
  }

  public void getCPUid(){
    CPUid();
  }
}
