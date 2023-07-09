package compiler;
import gen.CListener;
import gen.CParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

class SymbolTable implements CListener {
    int count = 0;
    Scope current = null;
    ArrayList<Scope> nested = new ArrayList<>();
    @Override
    public void enterPrimaryExpression(CParser.PrimaryExpressionContext ctx) {

    }

    @Override
    public void exitPrimaryExpression(CParser.PrimaryExpressionContext ctx) {

    }

    @Override
    public void enterPostfixExpression(CParser.PostfixExpressionContext ctx) {

        if (!ctx.getText().contains("(") && !ctx.getText().contains("[")) {
            if (ctx.primaryExpression() != null && ctx.primaryExpression().Identifier() != null) {
                String name = ctx.primaryExpression().Identifier().getText();
                if (current != null) {
                    if (name != null && ctx.primaryExpression().start != null) {
                        current.print_error_type(name, ctx.primaryExpression().start.getLine(), ctx.primaryExpression().start.getCharPositionInLine());
                    }
                }
            }
        }
        else if(ctx.getText().contains("(")){
            String funcName = ctx.primaryExpression().getText();
            String par ="";
            int ix = 0;
            for (int i =0 ; i<ctx.argumentExpressionList().get(0).assignmentExpression().size() ; i++){
                String name = ctx.argumentExpressionList().get(0).assignmentExpression().get(i).getText();
                int index = name.indexOf('[');
                if (index >= 0) {
                    name = name.substring(0, index);
                }

                if(ix>0)
                    par+=",";
                par += "[" + current.type_identification(name) + ", index: " + ix + "]";
                ix++;



            }
            current.print_error_function_parameter(funcName , par , ctx.start.getLine() , ctx.start.getCharPositionInLine());
        }
    }


    @Override
    public void exitPostfixExpression(CParser.PostfixExpressionContext ctx) {

    }

    @Override
    public void enterArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {

    }

    @Override
    public void exitArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {

    }

    @Override
    public void enterUnaryExpression(CParser.UnaryExpressionContext ctx) {


    }

    @Override
    public void exitUnaryExpression(CParser.UnaryExpressionContext ctx) {

    }

    @Override
    public void enterUnaryOperator(CParser.UnaryOperatorContext ctx) {

    }

    @Override
    public void exitUnaryOperator(CParser.UnaryOperatorContext ctx) {

    }

    @Override
    public void enterCastExpression(CParser.CastExpressionContext ctx) {

    }

    @Override
    public void exitCastExpression(CParser.CastExpressionContext ctx) {

    }

    @Override
    public void enterMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {

    }

    @Override
    public void exitMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {

    }

    @Override
    public void enterAdditiveExpression(CParser.AdditiveExpressionContext ctx) {

    }

    @Override
    public void exitAdditiveExpression(CParser.AdditiveExpressionContext ctx) {

    }

    @Override
    public void enterShiftExpression(CParser.ShiftExpressionContext ctx) {

    }

    @Override
    public void exitShiftExpression(CParser.ShiftExpressionContext ctx) {

    }

    @Override
    public void enterRelationalExpression(CParser.RelationalExpressionContext ctx) {

    }

    @Override
    public void exitRelationalExpression(CParser.RelationalExpressionContext ctx) {

    }

    @Override
    public void enterEqualityExpression(CParser.EqualityExpressionContext ctx) {

    }

    @Override
    public void exitEqualityExpression(CParser.EqualityExpressionContext ctx) {

    }

    @Override
    public void enterAndExpression(CParser.AndExpressionContext ctx) {

    }

    @Override
    public void exitAndExpression(CParser.AndExpressionContext ctx) {

    }

    @Override
    public void enterExclusiveOrExpression(CParser.ExclusiveOrExpressionContext ctx) {

    }

    @Override
    public void exitExclusiveOrExpression(CParser.ExclusiveOrExpressionContext ctx) {

    }

    @Override
    public void enterInclusiveOrExpression(CParser.InclusiveOrExpressionContext ctx) {

    }

    @Override
    public void exitInclusiveOrExpression(CParser.InclusiveOrExpressionContext ctx) {

    }

    @Override
    public void enterLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {

    }

    @Override
    public void exitLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {

    }

    @Override
    public void enterLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {

    }

    @Override
    public void exitLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {

    }

    @Override
    public void enterConditionalExpression(CParser.ConditionalExpressionContext ctx) {

    }

    @Override
    public void exitConditionalExpression(CParser.ConditionalExpressionContext ctx) {

    }

    @Override
    public void enterAssignmentExpression(CParser.AssignmentExpressionContext ctx) {
        if(ctx.unaryExpression()!=null) {
            String var1 = ctx.unaryExpression().getText();
            String var2 = ctx.assignmentExpression().getText();
            String var1_type = current.type_identification(var1);
            String var2_type = current.type_identification(var2);
            int l = ctx.start.getLine();
            int inx = ctx.start.getCharPositionInLine();
            current.print_error_equal(var1_type, var2_type, l, inx);
        }
    }

    @Override
    public void exitAssignmentExpression(CParser.AssignmentExpressionContext ctx) {

    }

    @Override
    public void enterAssignmentOperator(CParser.AssignmentOperatorContext ctx) {

    }

    @Override
    public void exitAssignmentOperator(CParser.AssignmentOperatorContext ctx) {

    }

    @Override
    public void enterExpression(CParser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(CParser.ExpressionContext ctx) {

    }

    @Override
    public void enterConstantExpression(CParser.ConstantExpressionContext ctx) {

    }

    @Override
    public void exitConstantExpression(CParser.ConstantExpressionContext ctx) {

    }

    @Override
    public void enterDeclaration(CParser.DeclarationContext ctx) {
        String type = ctx.declarationSpecifiers().getText();
        if (ctx.initDeclaratorList() != null) {
            for (int i = 0; i < ctx.initDeclaratorList().initDeclarator().size(); i++) {
                if (ctx.initDeclaratorList().initDeclarator().get(i).initializer() != null) {
                    String var1 = ctx.initDeclaratorList().initDeclarator().get(i).declarator().getText();
                    if (var1.contains("[")) {
                        type += " array";
                    }
                    String var = ctx.initDeclaratorList().initDeclarator().get(i).initializer().getText();
                    String var_type = current.type_identification(var);
                    int l = ctx.initDeclaratorList().initDeclarator().get(i).initializer().start.getLine();
                    int inx = ctx.initDeclaratorList().initDeclarator().get(i).initializer().start.getCharPositionInLine();
                    current.print_error_equal(type, var_type, l, inx);
                }
            }

        }
    }
    @Override
    public void exitDeclaration(CParser.DeclarationContext ctx) {

    }

    @Override
    public void enterDeclarationSpecifiers(CParser.DeclarationSpecifiersContext ctx) {

    }

    @Override
    public void exitDeclarationSpecifiers(CParser.DeclarationSpecifiersContext ctx) {

    }

    @Override
    public void enterDeclarationSpecifier(CParser.DeclarationSpecifierContext ctx) {

    }

    @Override
    public void exitDeclarationSpecifier(CParser.DeclarationSpecifierContext ctx) {

    }

    @Override
    public void enterInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {

    }

    @Override
    public void exitInitDeclaratorList(CParser.InitDeclaratorListContext ctx) {

    }

    @Override
    public void enterInitDeclarator(CParser.InitDeclaratorContext ctx) {

    }

    @Override
    public void exitInitDeclarator(CParser.InitDeclaratorContext ctx) {

    }

    @Override
    public void enterStorageClassSpecifier(CParser.StorageClassSpecifierContext ctx) {

    }

    @Override
    public void exitStorageClassSpecifier(CParser.StorageClassSpecifierContext ctx) {

    }

    @Override
    public void enterTypeSpecifier(CParser.TypeSpecifierContext ctx) {

    }

    @Override
    public void exitTypeSpecifier(CParser.TypeSpecifierContext ctx) {

    }

    @Override
    public void enterStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {

    }

    @Override
    public void exitStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {

    }

    @Override
    public void enterStructOrUnion(CParser.StructOrUnionContext ctx) {

    }

    @Override
    public void exitStructOrUnion(CParser.StructOrUnionContext ctx) {

    }

    @Override
    public void enterStructDeclarationList(CParser.StructDeclarationListContext ctx) {

    }

    @Override
    public void exitStructDeclarationList(CParser.StructDeclarationListContext ctx) {

    }

    @Override
    public void enterStructDeclaration(CParser.StructDeclarationContext ctx) {

    }

    @Override
    public void exitStructDeclaration(CParser.StructDeclarationContext ctx) {

    }

    @Override
    public void enterSpecifierQualifierList(CParser.SpecifierQualifierListContext ctx) {

    }

    @Override
    public void exitSpecifierQualifierList(CParser.SpecifierQualifierListContext ctx) {

    }

    @Override
    public void enterStructDeclaratorList(CParser.StructDeclaratorListContext ctx) {

    }

    @Override
    public void exitStructDeclaratorList(CParser.StructDeclaratorListContext ctx) {

    }

    @Override
    public void enterStructDeclarator(CParser.StructDeclaratorContext ctx) {

    }

    @Override
    public void exitStructDeclarator(CParser.StructDeclaratorContext ctx) {

    }

    @Override
    public void enterEnumSpecifier(CParser.EnumSpecifierContext ctx) {

    }

    @Override
    public void exitEnumSpecifier(CParser.EnumSpecifierContext ctx) {

    }

    @Override
    public void enterEnumeratorList(CParser.EnumeratorListContext ctx) {

    }

    @Override
    public void exitEnumeratorList(CParser.EnumeratorListContext ctx) {

    }

    @Override
    public void enterEnumerator(CParser.EnumeratorContext ctx) {

    }

    @Override
    public void exitEnumerator(CParser.EnumeratorContext ctx) {

    }

    @Override
    public void enterEnumerationConstant(CParser.EnumerationConstantContext ctx) {

    }

    @Override
    public void exitEnumerationConstant(CParser.EnumerationConstantContext ctx) {

    }

    @Override
    public void enterTypeQualifier(CParser.TypeQualifierContext ctx) {

    }

    @Override
    public void exitTypeQualifier(CParser.TypeQualifierContext ctx) {

    }

    @Override
    public void enterDeclarator(CParser.DeclaratorContext ctx) {

    }

    @Override
    public void exitDeclarator(CParser.DeclaratorContext ctx) {

    }

    @Override
    public void enterDirectDeclarator(CParser.DirectDeclaratorContext ctx) {

    }

    @Override
    public void exitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {

    }

    @Override
    public void enterNestedParenthesesBlock(CParser.NestedParenthesesBlockContext ctx) {

    }

    @Override
    public void exitNestedParenthesesBlock(CParser.NestedParenthesesBlockContext ctx) {

    }

    @Override
    public void enterPointer(CParser.PointerContext ctx) {

    }

    @Override
    public void exitPointer(CParser.PointerContext ctx) {

    }

    @Override
    public void enterTypeQualifierList(CParser.TypeQualifierListContext ctx) {

    }

    @Override
    public void exitTypeQualifierList(CParser.TypeQualifierListContext ctx) {

    }

    @Override
    public void enterParameterTypeList(CParser.ParameterTypeListContext ctx) {

    }

    @Override
    public void exitParameterTypeList(CParser.ParameterTypeListContext ctx) {

    }

    @Override
    public void enterParameterList(CParser.ParameterListContext ctx) {

    }

    @Override
    public void exitParameterList(CParser.ParameterListContext ctx) {

    }

    @Override
    public void enterParameterDeclaration(CParser.ParameterDeclarationContext ctx) {

    }

    @Override
    public void exitParameterDeclaration(CParser.ParameterDeclarationContext ctx) {

    }

    @Override
    public void enterIdentifierList(CParser.IdentifierListContext ctx) {

    }

    @Override
    public void exitIdentifierList(CParser.IdentifierListContext ctx) {

    }

    @Override
    public void enterTypeName(CParser.TypeNameContext ctx) {

    }

    @Override
    public void exitTypeName(CParser.TypeNameContext ctx) {

    }

    @Override
    public void enterTypedefName(CParser.TypedefNameContext ctx) {

    }

    @Override
    public void exitTypedefName(CParser.TypedefNameContext ctx) {

    }

    @Override
    public void enterInitializer(CParser.InitializerContext ctx) {

    }

    @Override
    public void exitInitializer(CParser.InitializerContext ctx) {

    }

    @Override
    public void enterInitializerList(CParser.InitializerListContext ctx) {

    }

    @Override
    public void exitInitializerList(CParser.InitializerListContext ctx) {

    }

    @Override
    public void enterDesignation(CParser.DesignationContext ctx) {

    }

    @Override
    public void exitDesignation(CParser.DesignationContext ctx) {

    }

    @Override
    public void enterDesignatorList(CParser.DesignatorListContext ctx) {

    }

    @Override
    public void exitDesignatorList(CParser.DesignatorListContext ctx) {

    }

    @Override
    public void enterDesignator(CParser.DesignatorContext ctx) {

    }

    @Override
    public void exitDesignator(CParser.DesignatorContext ctx) {

    }

    @Override
    public void enterStatement(CParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(CParser.StatementContext ctx) {

    }

    @Override
    public void enterLabeledStatement(CParser.LabeledStatementContext ctx) {

    }

    @Override
    public void exitLabeledStatement(CParser.LabeledStatementContext ctx) {

    }

    @Override
    public void enterCompoundStatement(CParser.CompoundStatementContext ctx) {

    }

    @Override
    public void exitCompoundStatement(CParser.CompoundStatementContext ctx) {

    }

    @Override
    public void enterBlockItemList(CParser.BlockItemListContext ctx) {

    }

    @Override
    public void exitBlockItemList(CParser.BlockItemListContext ctx) {

    }

    @Override
    public void enterBlockItem(CParser.BlockItemContext ctx) {

    }

    @Override
    public void exitBlockItem(CParser.BlockItemContext ctx) {

    }

    @Override
    public void enterExpressionStatement(CParser.ExpressionStatementContext ctx) {

    }

    @Override
    public void exitExpressionStatement(CParser.ExpressionStatementContext ctx) {

    }

    @Override
    public void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        count++;
        if(count>1){
            Scope nes1 = new Scope("nested",ctx.start.getLine() , current);
            current = nes1;
            nested.add(nes1);
            for(int i=0 ; i<ctx.statement().size();i++){
                if(ctx.statement(i).compoundStatement()!=null){
                for(int j =0 ; j<ctx.statement(i).compoundStatement().blockItemList().blockItem().size();j++) {
                    if (ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration() != null) {
                        if (ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList() != null) {
                            for (int z = 0; z < ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList().initDeclarator().size(); z++) {
                                String name = ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList().initDeclarator(z).declarator().directDeclarator().Identifier().getText();
                                String type = ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().declarationSpecifiers().getText();
                                if (ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList().initDeclarator(z).declarator().directDeclarator().Constant().size() != 0) {
                                    type += " array";
                                    String length = ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList().initDeclarator(z).declarator().directDeclarator().Constant().toString();
                                    char save = length.charAt(1);
                                    type += ", length = " + save;
                                }
                                int l = ctx.start.getLine();
                                int idx = ctx.start.getCharPositionInLine();
                                if (nes1.print_error_field(name, l, idx) == 1)
                                    nes1.insert("Field_" + name, "methodParamField(name: " + name + ") (type: " + type + ")");
                                else
                                    nes1.insert("Field_" + name + "_" + l + "_" + idx, "methodParamField(name: " + name + ") (type: " + type + ")");
                            }
                        } else {
                            int size = ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().declarationSpecifiers().declarationSpecifier().size();
                            String name = ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().declarationSpecifiers().declarationSpecifier(size - 1).getText();
                            String type = ctx.statement(i).compoundStatement().blockItemList().blockItem(j).declaration().declarationSpecifiers().declarationSpecifier(size - 2).getText();
                            int l = ctx.start.getLine();
                            int idx = ctx.start.getCharPositionInLine();
                            if (nes1.print_error_field(name, l, idx) == 1)
                                nes1.insert("Field_" + name, "methodParamField(name: " + name + ") (type: " + type + ")");
                            else
                                nes1.insert("Field_" + name + "_" + l + "_" + idx, "methodParamField(name: " + name + ") (type: " + type + ")");
                        }
                    }

                    }
                }
            }
        }
    }

    @Override
    public void exitSelectionStatement(CParser.SelectionStatementContext ctx) {

        count--;
        current = current.getParent();
    }

    @Override
    public void enterIterationStatement(CParser.IterationStatementContext ctx) {
        count++;

        if(count>1){
            Scope nes2 = new Scope("nested",ctx.start.getLine() , current);
            current = nes2;
            nested.add(nes2);
            for(int j =0 ; j<ctx.statement().compoundStatement().blockItemList().blockItem().size();j++){
                if(ctx.statement().compoundStatement().blockItemList().blockItem(j).declaration()!=null){
                    if(ctx.statement().compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList()!=null) {
                        for (int z = 0; z < ctx.statement().compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList().initDeclarator().size(); z++) {
                            String name = ctx.statement().compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList().initDeclarator(z).declarator().directDeclarator().Identifier().getText();
                            String type = ctx.statement().compoundStatement().blockItemList().blockItem(j).declaration().declarationSpecifiers().getText();
                            if (ctx.statement().compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList().initDeclarator(z).declarator().directDeclarator().Constant().size() != 0) {
                                type += " array";
                                String length = ctx.statement().compoundStatement().blockItemList().blockItem(j).declaration().initDeclaratorList().initDeclarator(z).declarator().directDeclarator().Constant().toString();
                                char save = length.charAt(1);
                                type += ", length = " + save;

                            }

                            int l = ctx.start.getLine();
                            int idx = ctx.start.getCharPositionInLine();
                            if (nes2.print_error_field(name, l, idx) == 1)
                                nes2.insert("Field_" + name, "methodParamField(name: " + name + ") (type: " + type + ")");
                            else
                                nes2.insert("Field_" + name + "_" + l + "_" + idx, "methodParamField(name: " + name + ") (type: " + type + ")");
                        }
                    }
                }
            }




        }

        if(ctx.For()!=null){
                String type = ctx.forCondition().forDeclaration().declarationSpecifiers().declarationSpecifier(0).getText();

            for (int i = 0; i < ctx.forCondition().forDeclaration().initDeclaratorList().initDeclarator().size(); i++) {

                String name = ctx.forCondition().forDeclaration().initDeclaratorList().initDeclarator().get(i).declarator().directDeclarator().Identifier().getText();


                current.insert("Field_" + name, "methodField(name: " + name + ") (type: " + type + ")");



            }

        }
    }

    @Override
    public void exitIterationStatement(CParser.IterationStatementContext ctx) {

        count--;
        current = current.getParent();
    }

    @Override
    public void enterForCondition(CParser.ForConditionContext ctx) {

    }

    @Override
    public void exitForCondition(CParser.ForConditionContext ctx) {

    }

    @Override
    public void enterForDeclaration(CParser.ForDeclarationContext ctx) {

    }

    @Override
    public void exitForDeclaration(CParser.ForDeclarationContext ctx) {

    }

    @Override
    public void enterForExpression(CParser.ForExpressionContext ctx) {

    }

    @Override
    public void exitForExpression(CParser.ForExpressionContext ctx) {

    }

    @Override
    public void enterJumpStatement(CParser.JumpStatementContext ctx) {

    }

    @Override
    public void exitJumpStatement(CParser.JumpStatementContext ctx) {

    }

    @Override
    public void enterProgram(CParser.ProgramContext ctx) {
        Scope program = new Scope("program");
        current = program ;
        String type="";
        for (int i=0 ; i<ctx.functionDefinition().size();i++)
        {
            String name = String.valueOf(ctx.functionDefinition(i).declarator().directDeclarator().directDeclarator().Identifier());
            String retType = String.valueOf(ctx.functionDefinition(i).typeSpecifier().getText());
            if(ctx.functionDefinition(i).declarator().directDeclarator().parameterTypeList()!= null)
            {
                for(int j=0 ; j< ctx.functionDefinition(i).declarator().directDeclarator().parameterTypeList().parameterList().parameterDeclaration().size() ; j++){
                    if(j!=0)
                        type += ",";
                    type += "["+ ctx.functionDefinition(i).declarator().directDeclarator().parameterTypeList().parameterList().parameterDeclaration(j).declarationSpecifiers().getText();
                    if(ctx.functionDefinition(i).declarator().directDeclarator().parameterTypeList().parameterList().parameterDeclaration(j).declarator().directDeclarator().Constant().size()!=0)
                        type += " array";
                    type += ", index: "+j+"]";

                }


                if(program.print_error_method(name ,ctx.functionDefinition(i).declarator().directDeclarator().directDeclarator().start.getLine() ,ctx.start.getCharPositionInLine())==1){
                    program.insert("Method_"+name,"Method (name: "+name+") (return type:"+retType+") (parameter list:" +type+")");

                }
                else{
                    program.insert("Method_"+name+"_"+ctx.start.getLine()+"_"+ctx.start.getCharPositionInLine(),"Method (name: "+name+") (return type:"+retType+") (parameter list:" +type+")");
                }
            }
            else {

                if(program.print_error_method(name,ctx.functionDefinition(i).declarator().directDeclarator().directDeclarator().start.getLine(),ctx.start.getCharPositionInLine())==1){
                program.insert("Method_"+name,"Method (name: "+name+") (return type:"+retType+")");}
                else{
                    program.insert("Method_"+name+"_"+ctx.start.getLine()+"_"+ctx.start.getCharPositionInLine(),"Method (name: "+name+") (return type:"+retType+")");
                }
            }
        }
        System.out.println(program.toString());



    }

    @Override
    public void exitProgram(CParser.ProgramContext ctx) {
    int i = 0 ;
        while (nested.size()>0){
            System.out.println(nested.get(i));
            nested.remove(i);
        }


    }

    @Override
    public void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {

        Scope func = new Scope(ctx.declarator().directDeclarator().directDeclarator().getText(),ctx.start.getLine(),current);
        current = func;

        if(!ctx.declarator().directDeclarator().getText().contains("()")){
            for (int j = 0; j < ctx.declarator().directDeclarator().parameterTypeList().parameterList().parameterDeclaration().size(); j++) {
                String parField = ctx.declarator().directDeclarator().parameterTypeList().parameterList().parameterDeclaration(j).declarator().directDeclarator().Identifier().getText();
                String type = ctx.declarator().directDeclarator().parameterTypeList().parameterList().parameterDeclaration(j).declarationSpecifiers().getText();
                if(ctx.declarator().directDeclarator().parameterTypeList().parameterList().parameterDeclaration(j).declarator().directDeclarator().Constant().size()!=0) {
                    type += " array";
                    String length = ctx.declarator().directDeclarator().parameterTypeList().parameterList().parameterDeclaration(j).declarator().directDeclarator().Constant().toString();
                    char save = length.charAt(1);
                    type += ", length = "+ save;
                }
                int l =ctx.start.getLine();
                int idx = ctx.start.getCharPositionInLine();
                if(func.print_error_field(parField,l,idx)==1) {
                    func.insert("Field_" + parField, "methodParamField(name: " + parField + ") (type: " + type + ")");
                }
                else{
                    func.insert("Field_" + parField+"_"+l+"_"+idx, "methodParamField(name: " + parField + ") (type: " + type + ")");
                }
            }
        }
        if(ctx.compoundStatement().blockItemList()!= null) {
            for (int i = 0; i < ctx.compoundStatement().blockItemList().blockItem().size(); i++) {
                if (ctx.compoundStatement().blockItemList().blockItem(i).declaration() != null) {
                    if(ctx.compoundStatement().blockItemList().blockItem(i).declaration().initDeclaratorList()!=null) {
                        for (int j = 0; j < ctx.compoundStatement().blockItemList().blockItem(i).declaration().initDeclaratorList().initDeclarator().size(); j++) {
                            String metField = String.valueOf(ctx.compoundStatement().blockItemList().blockItem(i).declaration().initDeclaratorList().initDeclarator(j).declarator().directDeclarator().Identifier().getText());
                            String type = ctx.compoundStatement().blockItemList().blockItem(i).declaration().declarationSpecifiers().getText();
                            if (ctx.compoundStatement().blockItemList().blockItem(i).declaration().initDeclaratorList().initDeclarator(j).declarator().directDeclarator().Constant().size() != 0) {
                                type += " array";
                                String length = ctx.compoundStatement().blockItemList().blockItem(i).declaration().initDeclaratorList().initDeclarator(j).declarator().directDeclarator().Constant().toString();
                                char save = length.charAt(1);
                                type += ", length = " + save;
                            }
                            int l = ctx.compoundStatement().blockItemList().blockItem().get(i).declaration().initDeclaratorList().start.getLine();
                            int idx = ctx.compoundStatement().blockItemList().blockItem().get(i).declaration().initDeclaratorList().start.getCharPositionInLine();
                            if (func.print_error_field(metField, l, idx) == 1) {
                                func.insert("Field_" + metField, "methodField(name: " + metField + ") (type: " + type + ")");
                            } else {
                                func.insert("Field_" + metField + "_" + l + "_" + idx, "methodField(name: " + metField + ") (type: " + type + ")");
                            }
                        }
                    }
                }
            }
        }
        if(ctx.typeSpecifier()!=null) {
            String retType = ctx.typeSpecifier().getText();
            String retVar = "";
            for (int i = 0; i < ctx.compoundStatement().blockItemList().blockItem().size(); i++) {
                if(ctx.compoundStatement().blockItemList().blockItem().get(i).statement()!=null) {
                    if(ctx.compoundStatement().blockItemList().blockItem().get(i).statement().jumpStatement()!=null) {
                        retVar = ctx.compoundStatement().blockItemList().blockItem().get(i).statement().jumpStatement().expression().getText();


                        if (retVar.matches("\\d+")){
                            if(!retType.equals("int")){
                                System.out.println("Error210 : in line"+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+", ReturnType of this method must be "+retType);
                            }
                        }
                        else{
                            func.print_error_return_type(func.type_identification(retVar), retType, ctx.start.getLine() , ctx.start.getCharPositionInLine());
                        }

                    }
                }
            }
        }
        System.out.println(func.toString());

    }

    @Override
    public void exitFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        if(current!=null&&current.getParent()!=null)  {
            current = current.getParent();
        }
    }

    @Override
    public void enterDeclarationList(CParser.DeclarationListContext ctx) {

    }

    @Override
    public void exitDeclarationList(CParser.DeclarationListContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

}

class Scope {

    Scope parent ;

    public Scope getParent() {
        return parent;
    }

    String name = "";
   int line = 1;
    Map<String, String> table = new HashMap<String, String>();
    public Scope(String name) {
        this.name = name;
    }

    public Scope(String name, int line , Scope parent) {
        this.name = name;
        this.line = line;
        this.parent = parent ;
    }

    void insert (String idefName, String attributes){
        table.put(idefName , attributes);

    }
    void lookup (String idefName){
        table.remove(idefName);
    }

    public String toString() {
        return "------------- " + name + " : " + line + " -------------\n" +
                printItems() +
                "-----------------------------------------\n";
    }
    void print_error_type(String idefName , int l , int idx){
        Scope scope=this;
        while (scope!=null) {
            if (scope.table.containsKey("Field_" + idefName)) {
                return;
            }
            scope=scope.getParent();
        }
        System.err.println("Error106 : in line " + l +":"+idx+ ", Can not find Variable " + idefName+".");
        System.out.println();

    }
    int print_error_method(String idefName , int l , int idx){
        if(table.containsKey("Method_"+idefName)){
            System.err.println("Error102 : in line"+ l + ":"+idx+", method "+idefName+"has been defined already.\n");
            return 0;
        }
        return 1;
    }
    void print_error_equal(String type1, String type2, int l, int idx){
        if(type1 != null && !type1.equals(type2)&& type2!=null)
//            System.out.println("type2"+type2);
            System.err.println("Error 230: in line"+l+":"+idx+", Incompatible types :"+type1+" can not be converted to "+type2+".");
    }
    int print_error_field(String idefName , int l , int idx ){
        if(table.containsKey("Field_"+idefName)){
            System.err.print("Error104 : in line"+ l +":"+idx+", field "+idefName+" has been defined already.\n");
            return 0;
        }
        return 1;

    }
    String type_identification(String input) {

        if (input.matches("[0-9]+")) {
             return "int";
        }
        else if (input.matches("[0-9]+\\.[0-9]+")) {
            return "float";
        } else if (input.matches("\".*\"")) {
             return "String";
        } else if (input.matches("'.'")) {
            return "char";
        } else if (input.matches("\\{\\s*'.'\\s*(,\\s*'.'\\s*)*\\}")) {
           return "char array";
        } else if (input.matches("\\{[0-9,\\s]+\\}")) {
           return "int array";
        } else if (input.matches("\\{\".*\",\\s*\".*\"\\}")) {
           return "String array";
        }else if (input.matches("\\{[0-9.,\\s]+\\}")) {
            return "float array";
        }

        else if (table.containsKey("Field_" + input)) {
            String value = table.get("Field_" + input);
            int start = value.indexOf("(type: ") + 7;
            int end = value.indexOf(",", start);

            if (end == -1) {
                end = value.indexOf(")", start);
            }

            if (end != -1) {
                String result = value.substring(start, end);
                return result;
            }
        }

        return null;
    }
    void print_error_return_type(String retVar , String retType , int l  , int idx ){
        if(!retType.equals(retVar)){
            System.err.println("Error210 : in line"+l+":"+idx+", ReturnType of this method must be "+retType+".");
        }
    }

    void print_error_function_parameter(String name , String par , int l , int c ){
        String par1 = "";
        Scope scope=this;
        while (scope!=null) {
            if (scope.table.containsKey("Method_" + name)) {
                String value = scope.table.get("Method_" + name);
                int start = value.indexOf("list:") + 5;
                int end = value.indexOf(")", start);
                par1 += value.substring(start, end);
            }
            scope=scope.getParent();
        }

        if(!par1.equals(par)){

            System.err.println("Error220 : in line "+ l+":"+c+", Mismatch arguments.");

        }
    }
    String printItems(){
        String itemsStr = "";
        for (Map.Entry<String,String> entry : table.entrySet()) {
            itemsStr += "Key = " + entry.getKey() + " | Value = " + entry.getValue()
                    + "\n";
        }
        return itemsStr;
    }



}
