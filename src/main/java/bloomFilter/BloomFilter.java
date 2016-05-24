package bloomFilter;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.BitSet;

/**
 * Created by Akky on 22-05-2016.
 */
public class BloomFilter<T> {

    private final HashFunction function1 = Hashing.murmur3_32(17);
    private final HashFunction function2 = Hashing.murmur3_32(31);
    private final int bits;
    private final BitSet bitSet;
    private final int size;
    private int noOfHash = 7;
    private double bitsFactor = 9.6;
    private double falsePositiveProbability = .01d;


    public BloomFilter(int size) {
        this.size = size;
        bits = (int) (size * bitsFactor);
        bitSet = new BitSet(bits);
    }

    public BloomFilter(int size, double falsePositiveProbability) {
        if (falsePositiveProbability > 1 || falsePositiveProbability < 0) {
            throw new IllegalArgumentException();
        }
        this.falsePositiveProbability = falsePositiveProbability;
        double k = (Math.log(falsePositiveProbability) / -0.69314718055994530941723212145818d);
        noOfHash = (int) Math.ceil(k);
        bitsFactor = k / 0.69314718055994530941723212145818d;
        this.size = size;
        bits = (int) (size * bitsFactor);
        bitSet = new BitSet(bits);
    }

    private int generateFunction(T t) {
        return 0;

    }

    public boolean add(T t) {
        HashCode hashCode1 = function1.hashLong(t.hashCode());
        HashCode hashCode2 = function2.hashLong(t.hashCode());

        for (int i = 1; i <= noOfHash; i++) {
            int hashCode = getGeneratedHash(i, hashCode1, hashCode2);
            bitSet.set(hashCode);
        }

        return true;
    }

    private int getGeneratedHash(int hashFunctionIndex, HashCode hashCode1, HashCode hashCode2) {
        return Math.abs(hashCode1.asInt() + hashFunctionIndex * (hashCode2.asInt())) % bits;
    }


    public boolean contains(T t) {
        boolean result = true;
        HashCode hashCode1 = function1.hashLong(t.hashCode());
        HashCode hashCode2 = function2.hashLong(t.hashCode());

        for (int i = 1; i <= noOfHash; i++) {
            int hashCode = getGeneratedHash(i, hashCode1, hashCode2);
            result &= bitSet.get(hashCode);
            if (!result) {
                return false;
            }
        }

        return result;

    }

    public int getSize() {
        return size;
    }

    public double getFalsePositiveProbability() {
        return falsePositiveProbability;
    }

}
