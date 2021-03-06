package com.aaronjwood.portauthority.response;

import java.util.ArrayList;
import java.util.Map;

public interface MainAsyncResponse {

    /**
     * Delegate to handle array list outputs
     *
     * @param output
     */
    void processFinish(ArrayList<Map<String, String>> output);

    /**
     * Delegate to handle integer outputs
     *
     * @param output
     */
    void processFinish(int output);

    /**
     * Delegate to handle string outputs
     *
     * @param output
     */
    void processFinish(String output);
}
