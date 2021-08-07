/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galaxy_craft.mor;

import java.util.Map;

/**
 *
 * @author Bryce Browner
 */
public class Index {
    
    Map<String, Asset> objects;
    
    public class Asset {
        String hash;
        long size;
    }
}
