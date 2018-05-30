package randoms;

import java.util.Arrays;
import java.util.Random;

public class FemaleNames {

    String[] data;

    public FemaleNames(String[] data) {
        this.data = data;
    }

    public String getRandom(){
        int rnd = new Random().nextInt(data.length);
        return data[rnd];
    }

    @Override
    public String toString() {
        return "FemaleNames{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
