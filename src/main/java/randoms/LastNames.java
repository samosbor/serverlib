package randoms;

import java.util.Arrays;
import java.util.Random;

public class LastNames {

    String[] data;

    public LastNames(String[] data) {
        this.data = data;
    }

    public String getRandom(){
        int rnd = new Random().nextInt(data.length);
        return data[rnd];
    }

    @Override
    public String toString() {
        return "LastNames{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
