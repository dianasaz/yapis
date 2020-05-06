package com.diana.yapis.output;

public class Program {

    public static void main(String[] args) {
        try {
            new Program().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws Exception{System.out.println("program4");Element el1 = new Element("element1");Element el2 = new Element("element2");Element el3 = new Element("element3");List l1= new List();l1.add(el1);l1.add(el2);if(l1.contains(el1)){if(l1.contains(el3)){}else{funck1(l1);}}}private void funck1(List l) throws Exception{if(l.getList().size()
>0){if(l.getList().size()
==2){Element el1 = new Element("1");Element el2 = new Element("1");if(el1.toString().equals(el2.toString())){List s=new List(l.getList());if(s.getList().equals(l.getList())){System.out.println("Complete");}else{System.out.println("No complete");}}}}else{System.out.println("eto nikogda ne vipolnitsya");}}}