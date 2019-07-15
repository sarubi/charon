package org.wso2.charon3.core.aParser;/* -----------------------------------------------------------------------------
 * Parser.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.5
 * Produced : Mon Jul 15 14:23:14 IST 2019
 *
 * -----------------------------------------------------------------------------
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class Parser {
    private Parser() {
    }

    static public Rule parse(String rulename, String string) throws IllegalArgumentException, ParserException {
        return parse(rulename, string, false);
    }

    static public Rule parse(String rulename, InputStream in)
            throws IllegalArgumentException, IOException, ParserException {
        return parse(rulename, in, false);
    }

    static public Rule parse(String rulename, File file) throws IllegalArgumentException, IOException, ParserException {
        return parse(rulename, file, false);
    }

    static private Rule parse(String rulename, String string, boolean trace)
            throws IllegalArgumentException, ParserException {
        if (rulename == null)
            throw new IllegalArgumentException("null rulename");
        if (string == null)
            throw new IllegalArgumentException("null string");

        ParserContext context = new ParserContext(string, trace);

        Rule rule = null;
        if (rulename.equalsIgnoreCase("PATH"))
            rule = Rule_PATH.parse(context);
        else if (rulename.equalsIgnoreCase("attributePath"))
            rule = Rule_attributePath.parse(context);
        else if (rulename.equalsIgnoreCase("valuePath"))
            rule = Rule_valuePath.parse(context);
        else if (rulename.equalsIgnoreCase("valueFilter"))
            rule = Rule_valueFilter.parse(context);
        else if (rulename.equalsIgnoreCase("attributeExpression"))
            rule = Rule_attributeExpression.parse(context);
        else if (rulename.equalsIgnoreCase("filter"))
            rule = Rule_filter.parse(context);
        else if (rulename.equalsIgnoreCase("filterDash"))
            rule = Rule_filterDash.parse(context);
        else if (rulename.equalsIgnoreCase("compareValue"))
            rule = Rule_compareValue.parse(context);
        else if (rulename.equalsIgnoreCase("compareOperation"))
            rule = Rule_compareOperation.parse(context);
        else if (rulename.equalsIgnoreCase("attributeName"))
            rule = Rule_attributeName.parse(context);
        else if (rulename.equalsIgnoreCase("nameChar"))
            rule = Rule_nameChar.parse(context);
        else if (rulename.equalsIgnoreCase("subAttribute"))
            rule = Rule_subAttribute.parse(context);
        else if (rulename.equalsIgnoreCase("URI"))
            rule = Rule_URI.parse(context);
        else if (rulename.equalsIgnoreCase("SP"))
            rule = Rule_SP.parse(context);
        else if (rulename.equalsIgnoreCase("alpha"))
            rule = Rule_alpha.parse(context);
        else if (rulename.equalsIgnoreCase("digit"))
            rule = Rule_digit.parse(context);
        else if (rulename.equalsIgnoreCase("string"))
            rule = Rule_string.parse(context);
        else if (rulename.equalsIgnoreCase("char"))
            rule = Rule_char.parse(context);
        else if (rulename.equalsIgnoreCase("escape"))
            rule = Rule_escape.parse(context);
        else if (rulename.equalsIgnoreCase("quotation-mark"))
            rule = Rule_quotation_mark.parse(context);
        else if (rulename.equalsIgnoreCase("unescaped"))
            rule = Rule_unescaped.parse(context);
        else if (rulename.equalsIgnoreCase("hexDigit"))
            rule = Rule_hexDigit.parse(context);
        else if (rulename.equalsIgnoreCase("false"))
            rule = Rule_false.parse(context);
        else if (rulename.equalsIgnoreCase("null"))
            rule = Rule_null.parse(context);
        else if (rulename.equalsIgnoreCase("true"))
            rule = Rule_true.parse(context);
        else if (rulename.equalsIgnoreCase("number"))
            rule = Rule_number.parse(context);
        else if (rulename.equalsIgnoreCase("exp"))
            rule = Rule_exp.parse(context);
        else if (rulename.equalsIgnoreCase("frac"))
            rule = Rule_frac.parse(context);
        else if (rulename.equalsIgnoreCase("int"))
            rule = Rule_int.parse(context);
        else if (rulename.equalsIgnoreCase("decimal-point"))
            rule = Rule_decimal_point.parse(context);
        else if (rulename.equalsIgnoreCase("digit1-9"))
            rule = Rule_digit1_9.parse(context);
        else if (rulename.equalsIgnoreCase("e"))
            rule = Rule_e.parse(context);
        else if (rulename.equalsIgnoreCase("minus"))
            rule = Rule_minus.parse(context);
        else if (rulename.equalsIgnoreCase("plus"))
            rule = Rule_plus.parse(context);
        else if (rulename.equalsIgnoreCase("zero"))
            rule = Rule_zero.parse(context);
        else
            throw new IllegalArgumentException("unknown rule");

        if (rule == null) {
            throw new ParserException("rule \"" + context.getErrorStack().peek() + "\" failed", context.text,
                    context.getErrorIndex(), context.getErrorStack());
        }

        if (context.text.length() > context.index) {
            ParserException primaryError = new ParserException("extra data found", context.text, context.index,
                    new Stack<String>());

            if (context.getErrorIndex() > context.index) {
                ParserException secondaryError = new ParserException(
                        "rule \"" + context.getErrorStack().peek() + "\" failed", context.text,
                        context.getErrorIndex(), context.getErrorStack());

                primaryError.initCause(secondaryError);
            }

            throw primaryError;
        }

        return rule;
    }

    static private Rule parse(String rulename, InputStream in, boolean trace)
            throws IllegalArgumentException, IOException, ParserException {
        if (rulename == null)
            throw new IllegalArgumentException("null rulename");
        if (in == null)
            throw new IllegalArgumentException("null input stream");

        int ch = 0;
        StringBuffer out = new StringBuffer();
        while ((ch = in.read()) != -1)
            out.append((char) ch);

        return parse(rulename, out.toString(), trace);
    }

    static private Rule parse(String rulename, File file, boolean trace)
            throws IllegalArgumentException, IOException, ParserException {
        if (rulename == null)
            throw new IllegalArgumentException("null rulename");
        if (file == null)
            throw new IllegalArgumentException("null file");

        BufferedReader in = new BufferedReader(new FileReader(file));
        int ch = 0;
        StringBuffer out = new StringBuffer();
        while ((ch = in.read()) != -1)
            out.append((char) ch);

        in.close();

        return parse(rulename, out.toString(), trace);
    }
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
