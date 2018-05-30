package randoms;

import java.util.Arrays;
import java.util.Random;

public class Locations {

    Location[] data;

    public Locations(Location[] data) {
        this.data = data;
    }

    public Location getRandom(){
        int rnd = new Random().nextInt(data.length);
        return data[rnd];
    }

    @Override
    public String toString() {
        return "Locations{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
