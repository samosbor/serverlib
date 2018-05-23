package service;

import request.LoadRequest;
import result.LoadResult;

/**
 * Created by samosbor on 5/18/18.
 */

public class Load {
    /**
     * The constructor for the load service object
     */
    public Load() {
    }

    /**
     * Clears all data from the database (just like the /clear API), and then loads the
     posted user, person, and event data into the database.
     * @param request
     * @return
     */
    public LoadResult load(LoadRequest request){
        return null;
    }
}
