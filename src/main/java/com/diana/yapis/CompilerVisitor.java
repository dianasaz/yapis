package com.diana.yapis;


import com.diana.yapis.gen.GrammarBaseVisitor;
import com.diana.yapis.gen.GrammarParser;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CompilerVisitor extends GrammarBaseVisitor<String> {
    private List<String> errors = new ArrayList<>();
    private List<String> globalListVars = new ArrayList<>();
    private List<String> globalElementVars = new ArrayList<>();

    @Override
    public String visitProgram(GrammarParser.ProgramContext ctx) {
        String buffer = Constant.PROGRAM;

        String buffer1 = "";
        for (GrammarParser.FunctionDefineContext context : ctx.functionDefine()) {
            buffer1 += visitFunctionDefine(context);
        }

        buffer += visitBlock(ctx.block());
        buffer += buffer1;
        buffer += "}";

        String error = "";
        if (!errors.isEmpty()) {
            for (String string : errors) {
                error += string + "\n";
            }
            JOptionPane.showMessageDialog(null, error);
            throw new RuntimeException(error);
        }

        return buffer;
    }

    @Override
    public String visitBlock(GrammarParser.BlockContext ctx) {
        Memory.getInstance().incrementStack();

        if (!globalListVars.isEmpty()) {
            for (String s : globalListVars) {
                Memory.getInstance().addList(s);
            }
        }

        if (!globalElementVars.isEmpty()) {
            for (String s : globalElementVars) {
                Memory.getInstance().addElement(s);
            }
        }

        globalListVars.clear();
        globalElementVars.clear();

        String buffer = "{";
        for (GrammarParser.ContentContext context : ctx.content()) {
            buffer += visitContent(context);
        }

        buffer += "}";
        Memory.getInstance().decrementStack();
        return buffer;
    }

    @Override
    public String visitPrint(GrammarParser.PrintContext ctx) {
        if (ctx.STRING() != null) {
            return "System.out.println(" + ctx.STRING().getText() + ");";
        }

        if (ctx.NAME() != null) {
            String s = ctx.NAME().getText();
            if (!(Memory.getInstance().containsElement(s) | Memory.getInstance().containsList(s))) {
                errors.add("Variable " + s + " doesn't exists - (Print)");
            }
            return "System.out.println(" + s + ");";
        }

        if (ctx.size() != null) {
            return "System.out.println(" + visitSize(ctx.size()) + ");";
        }

        return "";
    }

    @Override
    public String visitContent(GrammarParser.ContentContext ctx) {
        if (ctx.print() != null) {
            return visitPrint(ctx.print());
        }

        if (ctx.functionCall() != null) {
            return visitFunctionCall(ctx.functionCall());
        }

        if (ctx.forCycle() != null) {
            return visitForCycle(ctx.forCycle());
        }

        if (ctx.elementDeclaration() != null) {
            return visitElementDeclaration(ctx.elementDeclaration());
        }

        if (ctx.listDeclaration() != null) {
            return visitListDeclaration(ctx.listDeclaration());
        }

        if (ctx.ifBlock() != null) {
            return visitIfBlock(ctx.ifBlock());
        }

        if (ctx.add() != null) {
            return visitAdd(ctx.add());
        }

        if (ctx.remove() != null) {
            return visitRemove(ctx.remove());
        }

        if (ctx.clear() != null) {
            return visitClear(ctx.clear());
        }

        if (ctx.concate() != null) {
            return visitConcate(ctx.concate());
        }

        return "";
    }

    @Override
    public String visitType(GrammarParser.TypeContext ctx) {
        if (ctx.ELEMENT() != null) {
            return "Element";
        }

        if (ctx.LIST() != null) {
            return "List";
        }

        return "";
    }

    @Override
    public String visitFunctionDefine(GrammarParser.FunctionDefineContext ctx) {
        String functionName = ctx.NAME().get(0).getText();
        if (Memory.getInstance().containsFunction(functionName)) {
            errors.add("Function " + functionName + " already exists - (FunctionDefine)");
        }

        Memory.getInstance().addFunction(functionName);

        String buffer = "private void " + functionName + "(";

        for (int index = 1; index < ctx.NAME().size(); index++) {
            String type = visitType(ctx.type(index - 1));
            String name = ctx.NAME().get(index).getText();

            if (type.equals("List")) {
                globalListVars.add(name);
            } else {
                globalElementVars.add(name);
            }

            if (index > 1) {
                buffer += ",";
            }

            buffer += type + " " + name;
        }

        buffer += ") throws Exception";
        buffer += visitBlock(ctx.block());
        return buffer;
    }

    @Override
    public String visitFunctionCall(GrammarParser.FunctionCallContext ctx) {
        String functionName = ctx.NAME().get(0).getText();
        if (!Memory.getInstance().containsFunction(functionName)) {
            errors.add("Function " + functionName + " doesn't exists - (FunctionCall)");
        }

        String buffer = functionName + "(";

        for (int index = 1; index < ctx.NAME().size(); index++) {
            String name = ctx.NAME().get(index).getText();
            if (!(Memory.getInstance().containsElement(name) | Memory.getInstance().containsList(name))) {
                errors.add("Variable " + name + " doesn't exists - (FunctionCall)");
            }

            if (index > 1) {
                buffer += ",";
            }

            buffer += name;
        }

        buffer += ");";
        return buffer;
    }

    @Override
    public String visitForCycle(GrammarParser.ForCycleContext ctx) {
        String name1 = ctx.NAME().get(0).getText();
        String name2 = ctx.NAME().get(1).getText();

        if (!Memory.getInstance().containsList(name1)) {
            errors.add("Variable list " + name1 + " doesn't exist - (ForCycle)");
        }

        if (Memory.getInstance().containsElement(name2)) {
            errors.add("Variable element" + name2 + " already exists - (ForCycle)");
        }

        globalElementVars.add(name2);

        String buffer = "for (Element " + name2 + ":" + name1 + ".getList()" + ")";
        buffer += visitBlock(ctx.block());

        return buffer;
    }

    @Override
    public String visitElementDeclaration(GrammarParser.ElementDeclarationContext ctx) {
        String name = ctx.NAME().get(0).getText();
        String buffer = "";

            if (Memory.getInstance().containsElement(name)) {
                errors.add("Variable element " + name + " already exists - (ElementDeclaration)");
            }
            Memory.getInstance().addElement(name);
            buffer += "Element " + name + " =";

        if (ctx.STRING() != null) {
            buffer += " new Element(" + ctx.STRING().getText() + ");";
        } else if (ctx.get() != null) {
            buffer += " new Element(" + visitGet(ctx.get()) + ");";
        } else {
            String name2 = ctx.NAME().get(1).getText();
            if (!Memory.getInstance().containsElement(name2)) {
                errors.add("Variable element " + name2 + " doesn't exists - (ElementDeclaration)");
            }
            buffer += " new Element(" + name2 + ".toString());";
        }

        return buffer;
    }

    @Override
    public String visitListDeclaration(GrammarParser.ListDeclarationContext ctx) {
        String buffer = "";

        if (ctx.NAME().size() == 2) {
            String name1 = ctx.NAME().get(0).getText();
            String name2 = ctx.NAME().get(1).getText();

            if (!Memory.getInstance().containsList(name2)) {
                errors.add("Variable list " + name2 + " doesn't exists - (ListDeclaration)");
            }
                if (Memory.getInstance().containsList(name1)) {
                    errors.add("Variable list " + name1 + " already exists - (ListDeclaration)");
                }
                Memory.getInstance().addList(name1);
                buffer += "List " + name1 + "=" + "new List(" + name2 + ".getList());";
        } else {
            String name = ctx.NAME().get(0).getText();
            if (Memory.getInstance().containsList(name)) {
                errors.add("Variable list " + name + " already exists - (ListDeclaration)");
            }
            Memory.getInstance().addList(name);
            buffer += "List " + name + "= new List();";
        }

        return buffer;
    }

    @Override
    public String visitEqualNames(GrammarParser.EqualNamesContext ctx) {
        String name1 = ctx.NAME().get(0).getText();
        String name2 = ctx.NAME().get(1).getText();
        String buffer = "";

        if (Memory.getInstance().containsList(name1) && Memory.getInstance().containsList(name2)) {
            buffer += name1 + ".getList().equals(" + name2 + ".getList())";
        } else if (Memory.getInstance().containsElement(name1) && Memory.getInstance().containsElement(name2)) {
            buffer += name1 + ".toString().equals(" + name2 + ".toString())";
        } else {
            errors.add("Variables " + name1 + " and " + name2 + " can't be compared - (EqualNames)");
        }

        return buffer;
    }

    @Override
    public String visitCompare(GrammarParser.CompareContext ctx) {
        if (ctx.equalNames() != null) {
            return visitEqualNames(ctx.equalNames());
        }

        if (ctx.contains() != null) {
            return visitContains(ctx.contains());
        }

        if (ctx.is_empty() != null) {
            return visitIs_empty(ctx.is_empty());
        }

        if (ctx.compareEQ() != null) {
            return visitCompareEQ(ctx.compareEQ());
        }

        if (ctx.compareGE() != null) {
            return visitCompareGE(ctx.compareGE());
        }

        if (ctx.compareGT() != null) {
            return visitCompareGT(ctx.compareGT());
        }

        if (ctx.compareLE() != null) {
            return visitCompareLE(ctx.compareLE());
        }

        if (ctx.compareLT() != null) {
            return visitCompareLT(ctx.compareLT());
        }

        return "";
    }

    @Override
    public String visitIfBlock(GrammarParser.IfBlockContext ctx) {
        String buffer = "";
        buffer += "if(" + visitCompare(ctx.compare()) + ")" + visitBlock(ctx.block());
        if (ctx.elseBlock() != null) {
            buffer += visitElseBlock(ctx.elseBlock());
        }
        return buffer;
    }

    @Override
    public String visitElseBlock(GrammarParser.ElseBlockContext ctx) {
        String buffer = "else";
        buffer += visitBlock(ctx.block());
        return buffer;
    }

    @Override
    public String visitContains(GrammarParser.ContainsContext ctx) {
        String name1 = ctx.NAME().get(0).getText();
        String name2 = ctx.NAME().get(1).getText();

        if (!Memory.getInstance().containsList(name1)) {
            errors.add("Variable list " + name1 + " doesn't exists - (Contains)");
        }

        if (!Memory.getInstance().containsElement(name2)) {
            errors.add("Variable element " + name2 + " doesn't exists - (Contains)");
        }

        return name1 + ".contains(" + name2 + ")";
    }

    @Override
    public String visitIs_empty(GrammarParser.Is_emptyContext ctx) {
        String name1 = ctx.NAME().getText();

        if (!Memory.getInstance().containsList(name1)) {
            errors.add("Variable list " + name1 + " doesn't exists - (Contains)");
        }

        return name1 + ".isEmpty()\n";
    }

    @Override
    public String visitAdd(GrammarParser.AddContext ctx) {
        String name1 = ctx.NAME().get(0).getText();
        String name2 = ctx.NAME().get(1).getText();

        if (!Memory.getInstance().containsList(name1)) {
            errors.add("Variable list " + name1 + " doesn't exists - (Add)");
        }

        if (!Memory.getInstance().containsElement(name2)) {
            errors.add("Variable element " + name2 + " doesn't exists - (Add)");
        }

        return name1 + ".add(" + name2 + ");";
    }

    @Override
    public String visitClear(GrammarParser.ClearContext ctx) {
        String name1 = ctx.NAME().getText();

        if (!Memory.getInstance().containsList(name1)) {
            errors.add("Variable list " + name1 + " doesn't exists - (Clear)");
        }

        return name1 + ".clear();";
    }

    @Override
    public String visitGet(GrammarParser.GetContext ctx) {
        String name1 = ctx.NAME().getText();

        if (!Memory.getInstance().containsList(name1)) {
            errors.add("Variable list " + name1 + " doesn't exists - (Get)");
        }

        return name1 + ".get(" + ctx.NUMBER().getText() + ")";
    }

    @Override
    public String visitRemove(GrammarParser.RemoveContext ctx) {
        String name1 = ctx.NAME().get(0).getText();

        if (!Memory.getInstance().containsList(name1)) {
            errors.add("Variable list " + name1 + " doesn't exists - (Remove)");
        }

        String buffer = "";
        if (ctx.NUMBER() != null) {
            buffer += name1 + ".remove(" + ctx.NUMBER().getText() + ");";
        } else {
            String name2 = ctx.NAME().get(1).getText();

            if (!Memory.getInstance().containsElement(name2)) {
                errors.add("Variable element " + name2 + " doesn't exists - (Remove)");
            }

            buffer += name1 + ".remove(" + name2 + ");";
        }

        return buffer;
    }

    @Override
    public String visitSize(GrammarParser.SizeContext ctx) {
        String name1 = ctx.NAME().getText();

        if (!Memory.getInstance().containsList(name1)) {
            errors.add("Variable list " + name1 + " doesn't exists - (Size)");
        }

        return name1 + ".getList().size()\n";
    }

    @Override
    public String visitCompareGE(GrammarParser.CompareGEContext ctx) {
        String num = ctx.NUMBER().getText();
        return visitSize(ctx.size()) + ">=" + num;
    }

    @Override
    public String visitCompareGT(GrammarParser.CompareGTContext ctx) {
        String num = ctx.NUMBER().getText();
        return visitSize(ctx.size()) + ">" + num;
    }

    @Override
    public String visitCompareLE(GrammarParser.CompareLEContext ctx) {
        String num = ctx.NUMBER().getText();
        return visitSize(ctx.size()) + "<=" + num;
    }

    @Override
    public String visitCompareLT(GrammarParser.CompareLTContext ctx) {
        String num = ctx.NUMBER().getText();
        return visitSize(ctx.size()) + "<" + num;
    }

    @Override
    public String visitCompareEQ(GrammarParser.CompareEQContext ctx) {
        String num = ctx.NUMBER().getText();
        return visitSize(ctx.size()) + "==" + num;
    }

    @Override
    public String visitConcate(GrammarParser.ConcateContext ctx) {
        String name1 = ctx.NAME().get(0).getText();
        String name2 = ctx.NAME().get(1).getText();

        String buffer = "";
        if (Memory.getInstance().containsElement(name1) && Memory.getInstance().containsElement(name2)) {
            buffer += name1 + "= new Element(" + name1 + ".toString()+" + name2 + ".toString());";
        } else if (Memory.getInstance().containsList(name1) && Memory.getInstance().containsList(name2)) {
            buffer += name1 + ".getList().addAll(" + name2 + ".getList());";
        } else {
            errors.add("Wrong variables " + name1 + " and " + name2 + " - (Concate)");
        }

        return buffer;
    }
}