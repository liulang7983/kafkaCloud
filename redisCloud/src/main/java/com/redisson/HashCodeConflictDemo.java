package com.redisson;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ming.li
 * @date 2023/2/27 9:18
 */
public class HashCodeConflictDemo
{
    public static void main(String[] args)
    {
        Set<Integer> hashCodeSet = new HashSet<>();

        for (int i = 0; i <200000; i++) {
            int hashCode = new Object().hashCode();
            if(hashCodeSet.contains(hashCode)) {
                System.out.println("出现了重复的hashcode: "+hashCode+"\t 运行到"+i);
                break;
            }
            hashCodeSet.add(hashCode);
        }
        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());
        System.out.println("柳柴".hashCode());
        System.out.println("柴柕".hashCode());
    }
}