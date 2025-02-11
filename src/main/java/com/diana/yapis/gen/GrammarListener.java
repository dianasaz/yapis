// Generated from D:/diana/yapis/src/main/java/com/diana/yapis\Grammar.g4 by ANTLR 4.8
package com.diana.yapis.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(GrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(GrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(GrammarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(GrammarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(GrammarParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(GrammarParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(GrammarParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(GrammarParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#functionDefine}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefine(GrammarParser.FunctionDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#functionDefine}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefine(GrammarParser.FunctionDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(GrammarParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(GrammarParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#forCycle}.
	 * @param ctx the parse tree
	 */
	void enterForCycle(GrammarParser.ForCycleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#forCycle}.
	 * @param ctx the parse tree
	 */
	void exitForCycle(GrammarParser.ForCycleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#elementDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterElementDeclaration(GrammarParser.ElementDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#elementDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitElementDeclaration(GrammarParser.ElementDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#listDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterListDeclaration(GrammarParser.ListDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#listDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitListDeclaration(GrammarParser.ListDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#equalNames}.
	 * @param ctx the parse tree
	 */
	void enterEqualNames(GrammarParser.EqualNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#equalNames}.
	 * @param ctx the parse tree
	 */
	void exitEqualNames(GrammarParser.EqualNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#contains}.
	 * @param ctx the parse tree
	 */
	void enterContains(GrammarParser.ContainsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#contains}.
	 * @param ctx the parse tree
	 */
	void exitContains(GrammarParser.ContainsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#is_empty}.
	 * @param ctx the parse tree
	 */
	void enterIs_empty(GrammarParser.Is_emptyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#is_empty}.
	 * @param ctx the parse tree
	 */
	void exitIs_empty(GrammarParser.Is_emptyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterCompare(GrammarParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitCompare(GrammarParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void enterIfBlock(GrammarParser.IfBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void exitIfBlock(GrammarParser.IfBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#elseBlock}.
	 * @param ctx the parse tree
	 */
	void enterElseBlock(GrammarParser.ElseBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#elseBlock}.
	 * @param ctx the parse tree
	 */
	void exitElseBlock(GrammarParser.ElseBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(GrammarParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(GrammarParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#clear}.
	 * @param ctx the parse tree
	 */
	void enterClear(GrammarParser.ClearContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#clear}.
	 * @param ctx the parse tree
	 */
	void exitClear(GrammarParser.ClearContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#get}.
	 * @param ctx the parse tree
	 */
	void enterGet(GrammarParser.GetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#get}.
	 * @param ctx the parse tree
	 */
	void exitGet(GrammarParser.GetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#remove}.
	 * @param ctx the parse tree
	 */
	void enterRemove(GrammarParser.RemoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#remove}.
	 * @param ctx the parse tree
	 */
	void exitRemove(GrammarParser.RemoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#size}.
	 * @param ctx the parse tree
	 */
	void enterSize(GrammarParser.SizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#size}.
	 * @param ctx the parse tree
	 */
	void exitSize(GrammarParser.SizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#compareGE}.
	 * @param ctx the parse tree
	 */
	void enterCompareGE(GrammarParser.CompareGEContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#compareGE}.
	 * @param ctx the parse tree
	 */
	void exitCompareGE(GrammarParser.CompareGEContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#compareGT}.
	 * @param ctx the parse tree
	 */
	void enterCompareGT(GrammarParser.CompareGTContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#compareGT}.
	 * @param ctx the parse tree
	 */
	void exitCompareGT(GrammarParser.CompareGTContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#compareLE}.
	 * @param ctx the parse tree
	 */
	void enterCompareLE(GrammarParser.CompareLEContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#compareLE}.
	 * @param ctx the parse tree
	 */
	void exitCompareLE(GrammarParser.CompareLEContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#compareLT}.
	 * @param ctx the parse tree
	 */
	void enterCompareLT(GrammarParser.CompareLTContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#compareLT}.
	 * @param ctx the parse tree
	 */
	void exitCompareLT(GrammarParser.CompareLTContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#compareEQ}.
	 * @param ctx the parse tree
	 */
	void enterCompareEQ(GrammarParser.CompareEQContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#compareEQ}.
	 * @param ctx the parse tree
	 */
	void exitCompareEQ(GrammarParser.CompareEQContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#concate}.
	 * @param ctx the parse tree
	 */
	void enterConcate(GrammarParser.ConcateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#concate}.
	 * @param ctx the parse tree
	 */
	void exitConcate(GrammarParser.ConcateContext ctx);
}