package com.xquant.platform.component.darren.vo;

/**
 * To model the message carrying the name, you can create a plain old Java object 
 * with a name property and a corresponding getName() method
 * @author Administrator
 *
 */
public class HelloMessage {

    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
