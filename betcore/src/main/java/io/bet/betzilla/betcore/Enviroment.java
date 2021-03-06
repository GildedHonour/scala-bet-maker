package io.bet.betzilla.betcore;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class Enviroment {
    @Getter
    @Setter
    private HashMap<String, String> envs = new HashMap<String, String>();

    public boolean isEqual(String key, String value) {
        if (!this.envs.containsKey(key)) {
            System.out.println("Missing env key:" + key);
            return false;
        }

        String lvalue = this.envs.get(key);
        if (lvalue.equals(value))
            return true;

        return false;
    }

    public Double getDouble(String key) {
        return getDouble(key, null);
    }

    public Double getDouble(String key, Double defaultValue) {
        if (!this.envs.containsKey(key))
            return defaultValue;

        return Double.parseDouble(this.envs.get(key));
    }

    public int getInt(String key) {
        return (int) Integer.parseInt(this.envs.get(key));
    }

    public void set(String key, String value) {
        envs.put(key, value);
    }
}