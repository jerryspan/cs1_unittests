import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PrimesTest {
    @Test
    @DisplayName("Tests your isPrime method. It should return true if a a given number is a prime.")
    public void testIsPrime() {
        int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
        int[] notPrimes = { 4, 6, 12, 15, 21 };

        for (int prime : primes) {
            Assert.assertTrue(prime + " is a prime!", Primes.isPrime(prime));
        }
        for (int notPrime : notPrimes) {
            Assert.assertFalse(notPrime + " is a not a prime!", Primes.isPrime(notPrime));
        }
    }

    @Test
    @DisplayName("Tests your countPrimes method. It should output the number of primes between 0 and N")
    public void testCountPrimes() {
        int[] inputs = { 15, 150, 56 };
        int[] outputs = { 5, 34, 15 };
        String message = "I expected %d primes between 0 and %d, you say: %d";
        for (int i = 0; i < inputs.length; i++) {
            var result = Primes.countPrimes(inputs[i]);
            Assert.assertEquals(String.format(message, outputs[i], inputs[i], result), outputs[i], result);
        }
    }
}
