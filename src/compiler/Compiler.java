package compiler;
import gen.CLexer;
import gen.CListener;
import gen.CParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.IOException;


public class Compiler {
    public static void main (String[] args) throws IOException {
        CharStream stream = CharStreams.fromFileName("sample/test.cl");
        CLexer Lexer = new CLexer(stream);
        TokenStream tokens = new CommonTokenStream(Lexer);
        CParser parser = new CParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        CListener Listener = new Listener();
        CListener Listener2 = new SymbolTable();
        walker.walk(Listener, tree);
        walker.walk(Listener2, tree);
    }
}
