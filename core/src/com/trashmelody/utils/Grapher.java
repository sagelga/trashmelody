package com.trashmelody.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.grapher.graphviz.GraphvizGrapher;
import com.google.inject.grapher.graphviz.GraphvizModule;

import java.io.*;

public class Grapher {
    public void graph(String filename, Injector demoInjector) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File(filename), "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Injector injector = Guice.createInjector(new GraphvizModule());
        GraphvizGrapher grapher = injector.getInstance(GraphvizGrapher.class);
        grapher.setOut(out);
        grapher.setRankdir("TB");
        try {
            grapher.graph(demoInjector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
