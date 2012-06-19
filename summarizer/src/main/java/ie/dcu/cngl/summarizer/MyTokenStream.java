package ie.dcu.cngl.summarizer;

import ie.dcu.cngl.tokeniser.TokenInfo;

import java.io.IOException;
import java.util.Iterator;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

public class MyTokenStream extends TokenStream {
    CharTermAttribute charTermAtt;
    OffsetAttribute offsetAtt;

    final Iterator<TokenInfo> listOfTokens;

    MyTokenStream(Iterator<TokenInfo> tokenList) {
        listOfTokens = tokenList;
        charTermAtt = addAttribute(CharTermAttribute.class);
        offsetAtt = addAttribute(OffsetAttribute.class);

    }

    @Override
    public boolean incrementToken() throws IOException {
        if(listOfTokens.hasNext()) {
            super.clearAttributes();
            TokenInfo myToken = listOfTokens.next();
            charTermAtt.setLength(0);
            charTermAtt.append(myToken.getValue());
            offsetAtt.setOffset(myToken.getStart(), myToken.getStart()+myToken.getLength());
            return true;
        }
        return false;
    }
}