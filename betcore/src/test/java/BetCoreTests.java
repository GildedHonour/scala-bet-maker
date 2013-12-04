package com.betmonster.betcore;

import org.junit.Test;
import static org.junit.Assert.*;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class BetCoreTests {

    @Test
    public void testBetCore() {
        
        System.out.println("test ");

        ObjectMapper mapper = new ObjectMapper(); 

        String data = FileUtils.readFile("test.json");

        try
        {
            System.out.println("MarketGroup loading");
            MarketGroup mg = mapper.readValue(data, MarketGroup.class);
            System.out.println(mg);
            System.out.println("MarketGroup loaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}