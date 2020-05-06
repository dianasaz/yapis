package com.diana.yapis;


import com.diana.yapis.gen.GrammarLexer;
import com.diana.yapis.gen.GrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String FILE_NAME = "program4.txt";
    private static final String COMPILATED_FILE_PATH = Main.class.getClassLoader().getResource("").getPath() +
            "../../" +
            "src/main/java/com/diana/yapis/output/";

    public static void main(String[] args) {
        try {
            File file = new File(
                    Main.class.getClassLoader().getResource(FILE_NAME).getFile()
            );

            GrammarLexer lexer = new GrammarLexer(new ANTLRInputStream(new FileReader(file)));
            GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.program();
            CompilerVisitor visitor = new CompilerVisitor();
            String code = (String) visitor.visit(tree);

            File outputFile = new File(COMPILATED_FILE_PATH + "Program.java");
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write(code);
            fileWriter.close();
            File elementFile = new File(COMPILATED_FILE_PATH + "Element.java");
            fileWriter = new FileWriter(elementFile);
            fileWriter.write(Constant.CLASS_ELEMENT);
            fileWriter.close();
            File listFile = new File(COMPILATED_FILE_PATH +  "List.java");
            fileWriter = new FileWriter(listFile);
            fileWriter.write(Constant.CLASS_LIST);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}