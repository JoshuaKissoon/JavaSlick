/**
 * A class to add key value pairs to JComboBox
 * 
 * @author Joshua Kissoon * 
 * @since 20130918
 * 
 * @updated 20150715 to handle objects of different types
 */
package com.joshuakissoon.slick;

public class KeyValue<TypeA, TypeB>
{

    private final TypeA key;
    private final TypeB value;

    public KeyValue(TypeA key, TypeB value)
    {
        this.key = key;
        this.value = value;
    }
    
    public TypeA getKey()
    {
        return key;
    }
    
    public TypeB getValue()
    {
        return value;
    }


    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof KeyValue)
        {
            KeyValue kv = (KeyValue) obj;
            return (kv.value.equals(this.value));
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }
}
