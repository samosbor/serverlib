package randoms;

import java.util.Arrays;
import java.util.Random;

public class MaleNames {

    String[] data;

    public MaleNames(String[] data) {
        this.data = data;
    }

    public String getRandom(){
        int rnd = new Random().nextInt(data.length);
        return data[rnd];
    }

    @Override
    public String toString() {
        return "MaleNames{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
